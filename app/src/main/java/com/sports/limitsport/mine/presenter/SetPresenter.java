package com.sports.limitsport.mine.presenter;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.mine.ui.ISetView;
import com.sports.limitsport.model.UserSettingInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

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

    /**
     * 系统通知
     *
     * @param on 是否接收通知(0:接收 1:拒绝)
     */
    public void setSystemNotice(String on) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("receiveNotice", on);

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).setSystemNotice(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mISetView != null) {
                    mISetView.setSystemNotice(response.isSuccess());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mISetView != null) {
                    mISetView.setSystemNotice(false);
                }
            }
        });
    }

    /**
     * 互动消息
     *
     * @param on 是否接收消息(0:接收 1:拒绝)
     */
    public void setMessage(String on) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("receiveNews", on);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).setMessageNotice(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mISetView != null) {
                    mISetView.setMessageNotice(response.isSuccess());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mISetView != null) {
                    mISetView.setMessageNotice(false);
                }
            }
        });
    }

    public void clear() {
        mISetView = null;
    }
}
