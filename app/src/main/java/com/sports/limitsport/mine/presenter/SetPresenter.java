package com.sports.limitsport.mine.presenter;

import com.sports.limitsport.mine.ui.ISetView;
import com.sports.limitsport.model.UserSettingInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class SetPresenter {
    private ISetView mISetView;

    public SetPresenter(ISetView mISetView) {
        this.mISetView = mISetView;
    }

    public void getUserSettingInfo() {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getUserSettingInfo(), new NetSubscriber<UserSettingInfoResponse>() {
            @Override
            public void response(UserSettingInfoResponse response) {
                if (mISetView != null) {
                    mISetView.showUserSettingInfo(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    public void clear() {
        mISetView = null;
    }
}
