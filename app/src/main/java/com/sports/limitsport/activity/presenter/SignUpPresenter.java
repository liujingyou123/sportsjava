package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.ISignUpView;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.TicketListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/27.
 */

public class SignUpPresenter {
    private ISignUpView mISignUpView;

    public SignUpPresenter(ISignUpView mISignUpView) {
        this.mISignUpView = mISignUpView;
    }

    /**
     * 获取活动票种
     * @param id 活动ID
     */
    public void getTicketList(String id) {
        HashMap<String ,String > hashMap = new HashMap<>();
        hashMap.put("id", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getTicketList(hashMap), new NetSubscriber<TicketListResponse>() {
            @Override
            public void response(TicketListResponse response) {
                if (mISignUpView != null) {
                    mISignUpView.showTicketList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }


    public void clear() {
        mISignUpView = null;
    }
}
