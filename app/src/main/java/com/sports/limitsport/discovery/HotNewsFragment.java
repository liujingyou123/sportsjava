package com.sports.limitsport.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.adapter.NewPersionAdapter;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.discovery.presenter.HotNewsPresenter;
import com.sports.limitsport.discovery.ui.IHotNewsView;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.FineShowListResponse;
import com.sports.limitsport.model.NewPersonListResponse;
import com.sports.limitsport.model.SignList;
import com.sports.limitsport.model.SignUpUser;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.HotNewHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/29.
 */

public class HotNewsFragment extends Fragment implements IHotNewsView {
    Unbinder unbinder;
    @BindView(R.id.rlv_new)
    RecyclerView rlvNew;
    private NewPersionAdapter adapter;
    private HotNewsPresenter mPresenter;
    private HotNewHeadView hotNewHeadView;
    private List<SignUpUser> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_news, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new HotNewsPresenter(this);
        }

        mPresenter.getClubsList();
        mPresenter.getAdvList();
        mPresenter.getFineShow();
        mPresenter.getNewPersionList();
    }

    private void initView() {
        hotNewHeadView = new HotNewHeadView(this.getContext());
        rlvNew.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new NewPersionAdapter(data);
        adapter.addHeaderView(hotNewHeadView);
        adapter.setHeaderAndEmpty(true);
        adapter.bindToRecyclerView(rlvNew);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                XLog.e("onItemChildClick");
                SignUpUser item = (SignUpUser) adapter.getItem(position);
                if (item != null) {
                    if ("0".equals(item.getStatus()) || "2".equals(item.getStatus())) { //0:互相不关注 1:我关注他 2:他关注我 3:互相关注
                        gotoPersonInfo(item.getId() + "");
                    }
                }
            }
        });
    }

    @Override
    public void showClubsList(ClubListResponse response) {
        if (hotNewHeadView != null && response.getData() != null) {
            hotNewHeadView.setClubsList(response.getData().getData());
        }
    }

    @Override
    public void showAdvList(AdvertiseInfoResponse response) {
        if (response.getData() != null) {
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < response.getData().size(); i++) {
                tmp.add(response.getData().get(i).getAdPicUrl());
            }

            if (hotNewHeadView != null) {
                hotNewHeadView.setImgUrls(tmp);
            }
        }
    }

    @Override
    public void showFineShowList(FineShowListResponse response) {
        if (response != null) {
            if (hotNewHeadView != null) {
                hotNewHeadView.setFineShowList(response.getData());
            }
        }
    }

    @Override
    public void showNewPersonList(NewPersonListResponse response) {
        if (response != null && response.getData() != null) {
            adapter.addData(response.getData().getData());
        }
    }

    /**
     * 进入个人主页
     */
    private void gotoPersonInfo(String id) {
        Intent intent = new Intent(getContext(), PersonInfoActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.clear();
        }

        mPresenter = null;
    }
}
