package com.sports.limitsport.mine.presenter;

import android.view.View;

import com.sports.limitsport.mine.ui.IMineView;
import com.sports.limitsport.model.EventBusNewNotice;
import com.sports.limitsport.model.NewNoticeResponse;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class MinePresenter {
    IMineView mIMineView;

    public MinePresenter(IMineView mIMineView) {
        this.mIMineView = mIMineView;
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", "");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getUserInfo(hashMap), new NetSubscriber<UserInfoResponse>() {
            @Override
            public void response(UserInfoResponse response) {
                if (mIMineView != null) {
                    mIMineView.showUserInfo(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    /**
     * 获取新通知
     */
    public void getNewNotice() {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getNewNotice(), new LoadingNetSubscriber<NewNoticeResponse>() {
            @Override
            public void response(NewNoticeResponse response) {

                if (mIMineView != null) {
                    mIMineView.showNewNotice(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    public void clear() {
        mIMineView = null;
    }
}
