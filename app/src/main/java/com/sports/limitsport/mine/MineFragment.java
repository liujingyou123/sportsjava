package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.mine.adapter.MineAdapter;
import com.sports.limitsport.mine.model.Dongtai;
import com.sports.limitsport.mine.model.EventBusUserInfo;
import com.sports.limitsport.mine.presenter.MinePresenter;
import com.sports.limitsport.mine.ui.IMineView;
import com.sports.limitsport.model.EventBusLogin;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.view.MineHeaderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class MineFragment extends BaseFragment implements IMineView {
    @BindView(R.id.ry_mine)
    RecyclerView ryMine;
    Unbinder unbinder;
    private MineAdapter mineAdapter;
    private List<Dongtai> data = new ArrayList<>();
    private MineHeaderView headerView;
    private MinePresenter mPresenter;
    private boolean isGetData = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.e("onResume");
        if (isGetData) {
            isGetData = false;
            getData();
        }
    }

    @Subscribe
    public void getLoginIn(EventBusLogin param) {
        XLog.e("param.isLogin = " + param.isLogin);
        if (param != null && param.isLogin) {
            isGetData = param.isLogin;
        }
    }

    @Subscribe
    public void getUserEdit(EventBusUserInfo param) {
        if (param != null && param.isResfreh) {
            isGetData = param.isResfreh;
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new MinePresenter(this);
        }

        if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 0) {
            mPresenter.getUserInfo();
        } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
            if (headerView != null) {
                headerView.setType(2);
            }
        } else {
            if (headerView != null) {
                headerView.setType(0);
            }
        }
    }

    private void initView() {
        headerView = new MineHeaderView(this.getContext());
        ryMine.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        mineAdapter = new MineAdapter(data);
        mineAdapter.addHeaderView(headerView);
        mineAdapter.setHeaderAndEmpty(true);
        mineAdapter.bindToRecyclerView(ryMine);
        mineAdapter.setEmptyView(R.layout.empty_dongtai);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                Intent intent1 = new Intent(this.getContext(), SetActivity.class);
                startActivity(intent1);
                break;
            case R.id.imv_focus_right:
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.putExtra("type", "login");
                    startActivity(intent);
                    return;
                }
                Intent intent = new Intent(this.getContext(), NoticeFirstLevelActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.clear();
        }

        mPresenter = null;
    }

    @Override
    public void showUserInfo(UserInfoResponse response) {
        if (headerView != null) {
            headerView.setData(response);
        }
    }
}