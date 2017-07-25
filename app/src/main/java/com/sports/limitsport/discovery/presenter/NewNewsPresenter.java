package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.discovery.ui.INewNewsView;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.ToolsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/24.
 * 发现 动态
 */

public class NewNewsPresenter {
    private INewNewsView mINewNewsView;

    public NewNewsPresenter(INewNewsView mINewNewsView) {
        this.mINewNewsView = mINewNewsView;
    }

    /**
     * 获取轮播图片
     */
    public void getAdvList() {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("adType", "2");
//        hashMap.put("position", "2");
//        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getAdvList(hashMap), new NoneNetSubscriber<AdvertiseInfoResponse>() {
//            @Override
//            public void response(AdvertiseInfoResponse response) {
//                if (mINewNewsView != null) {
//                    mINewNewsView.showAdvList(response);
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//            }
//        });
        getTestAdvData();
    }

    private void getTestAdvData() {
        AdvertiseInfoResponse response = new AdvertiseInfoResponse();
        List<AdvertiseInfoResponse.DataBean> datas = new ArrayList<>();
        for (int i = 0; i< MyTestData.getData().size(); i++) {
            AdvertiseInfoResponse.DataBean dataBean = new AdvertiseInfoResponse.DataBean();
            dataBean.setAdPicUrl(MyTestData.getData().get(i));
            datas.add(dataBean);
        }
        response.setData(datas);
        if (mINewNewsView != null) {
            mINewNewsView.showAdvList(response);
        }

    }

    public void clear() {
        mINewNewsView = null;
    }
}
