package com.sports.limitsport.mine.presenter;

import com.sports.limitsport.mine.ui.IMineView;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

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
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getUserInfo(), new NetSubscriber<UserInfoResponse>() {
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

    public void clear() {
        mIMineView = null;
    }
}
