package com.sports.limitsport.mine.presenter;

import com.sports.limitsport.mine.ui.IUserInfoView;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class UserInfoPresenter {
    IUserInfoView mIUserInfoView;

    public UserInfoPresenter(IUserInfoView mIUserInfoView) {
        this.mIUserInfoView = mIUserInfoView;
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getUserInfo(), new NetSubscriber<UserInfoResponse>() {
            @Override
            public void response(UserInfoResponse response) {
                if (mIUserInfoView != null) {
                    mIUserInfoView.showUserInfo(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    public void clear() {
        mIUserInfoView = null;
    }
}
