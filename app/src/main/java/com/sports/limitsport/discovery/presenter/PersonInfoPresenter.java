package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.discovery.ui.IPersonInfoView;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/2.
 */

public class PersonInfoPresenter {
    private IPersonInfoView mIPersonInfoView;

    public PersonInfoPresenter(IPersonInfoView mIPersonInfoView) {
        this.mIPersonInfoView = mIPersonInfoView;
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getUserInfo(hashMap), new NetSubscriber<UserInfoResponse>() {
            @Override
            public void response(UserInfoResponse response) {
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showUserInfo(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    public void clear() {
        mIPersonInfoView = null;
    }
}
