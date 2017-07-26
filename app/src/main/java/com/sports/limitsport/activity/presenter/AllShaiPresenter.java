package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.IAllShaiView;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/26.
 */

public class AllShaiPresenter {
    private IAllShaiView mIAllShaiView;

    public AllShaiPresenter(IAllShaiView mIAllShaiView) {
        this.mIAllShaiView = mIAllShaiView;
    }

    /**
     * 大家都在晒
     *
     * @param id
     */
    public void getAllShai(String id, int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("activityId", id);
        hashMap.put("pageNumber", pageNumber+"");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "0");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTaiList(hashMap), new NoneNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mIAllShaiView != null) {
                    mIAllShaiView.showAllShiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIAllShaiView != null) {
                    mIAllShaiView.onError(e);
                }
            }
        });
    }

    public void clear() {
        mIAllShaiView = null;
    }
}
