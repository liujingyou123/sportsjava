package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sports.limitsport.R;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.EventBusNewNotice;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class NoticeSubFirstFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.imv_huodong_tip)
    ImageView imvHuodongTip;
    @BindView(R.id.imv_sys_tip)
    ImageView imvSysTip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticesubfirst, null);
        unbinder = ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Subscribe
    public void getNewNotice(EventBusNewNotice param) {
        XLog.e("param = " + param);
        if (param != null) {
            if (param.system > 0) {
                imvSysTip.setVisibility(View.VISIBLE);
            }
            if (param.activity > 0) {
                imvHuodongTip.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick({R.id.rl_huodong, R.id.rl_sys})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_huodong:
                gotoList(0);
                break;
            case R.id.rl_sys:
                gotoList(1);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private void gotoList(int type) {
        Intent intent = new Intent(this.getContext(), NoticeListActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
