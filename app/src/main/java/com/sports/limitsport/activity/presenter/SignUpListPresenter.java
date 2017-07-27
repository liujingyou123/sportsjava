package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.ISignUpListView;
import com.sports.limitsport.model.SignUpListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/27.
 */

public class SignUpListPresenter {
    ISignUpListView mISignUpListView;
    public SignUpListPresenter(ISignUpListView mISignUpListView) {
        this.mISignUpListView = mISignUpListView;
    }

    /**
     * 获取报名列表
     * @param id
     */
    public void getSignUpList(String id, int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("activityId", id);
        hashMap.put("pageNumber", pageNumber+"");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getSignUpList(hashMap), new NetSubscriber<SignUpListResponse>() {
            @Override
            public void response(SignUpListResponse response) {
                if (mISignUpListView != null) {
                    mISignUpListView.showSignUpList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mISignUpListView != null) {
                    mISignUpListView.onError(e);
                }
            }
        });
    }

    public void clear() {
        mISignUpListView = null;
    }
}
