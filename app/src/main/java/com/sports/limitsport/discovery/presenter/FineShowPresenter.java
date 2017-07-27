package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.discovery.ui.IFineShowView;
import com.sports.limitsport.model.FineShowListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/26.
 */

public class FineShowPresenter {
    IFineShowView mIFineShowView;

    public FineShowPresenter(IFineShowView mIFineShowView) {
        this.mIFineShowView = mIFineShowView;
    }

    /**
     * 获取精彩秀推荐列表
     */
    public void getFineShow(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber+"");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getFineShowList(hashMap), new NetSubscriber<FineShowListResponse>() {
            @Override
            public void response(FineShowListResponse response) {
                if (mIFineShowView != null) {
                    mIFineShowView.showFineShow(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    public void clear(){
        mIFineShowView = null;
    }

}
