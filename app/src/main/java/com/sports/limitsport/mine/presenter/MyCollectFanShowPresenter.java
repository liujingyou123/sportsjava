package com.sports.limitsport.mine.presenter;

import com.sports.limitsport.mine.ui.IMyCollectFineShowView;
import com.sports.limitsport.model.FineShowListResponse;
import com.sports.limitsport.model.MyCollectFineShowResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/4.
 */

public class MyCollectFanShowPresenter {
    IMyCollectFineShowView mIMyCollectFineShowView;

    public MyCollectFanShowPresenter(IMyCollectFineShowView mIMyCollectFineShowView) {
        this.mIMyCollectFineShowView = mIMyCollectFineShowView;
    }

    /**
     * 获取精彩秀推荐列表
     */
    public void getFineShow(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber+"");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getMyCollectFineShowList(hashMap), new NetSubscriber<MyCollectFineShowResponse>() {
            @Override
            public void response(MyCollectFineShowResponse response) {
                if (mIMyCollectFineShowView != null) {
                    mIMyCollectFineShowView.showFineShow(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMyCollectFineShowView != null) {
                    mIMyCollectFineShowView.onError(e);
                }
            }
        });
    }

    public void clear(){
        mIMyCollectFineShowView = null;
    }
}
