package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.IActivityListView;
import com.sports.limitsport.activity.model.Act;
import com.sports.limitsport.activity.model.ActivityResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/24.
 */

public class ActivityListPresenter {
    private IActivityListView mIActivityListView;

    public ActivityListPresenter(IActivityListView mIActivityListView) {
        this.mIActivityListView = mIActivityListView;
    }

    public void getActivityList(int pageNumber) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("pageNumber", pageNumber + "");
//        hashMap.put("pageSize", "10");
//        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getActivityList(hashMap), new NetSubscriber<ActivityResponse>() {
//            @Override
//            public void response(ActivityResponse response) {
//                if (mIActivityListView != null) {
//                    mIActivityListView.showList(response);
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//        if (mIActivityListView != null) {
//            mIActivityListView.onError(e);
//        }
//            }
//        });

        List<Act> acts = getTestData();

        ActivityResponse response = new ActivityResponse();
        ActivityResponse.DataBean dataBean = new ActivityResponse.DataBean();
        dataBean.setTotalSize(100);
        dataBean.setData(acts);
        response.setData(dataBean);
        if (mIActivityListView != null) {
            mIActivityListView.showList(response);
        }

    }

    public void clear() {
        mIActivityListView = null;
    }


    private List<Act> getTestData() {
        List<Act> mData = new ArrayList<>();
        Act act = new Act();
        act.setCoverUrl("http://img2.imgtn.bdimg.com/it/u=4144902998,2125657744&fm=11&gp=0.jpg");
        mData.add(act);

        Act act2 = new Act();
        act2.setCoverUrl("http://pic.58pic.com/58pic/13/60/97/48Q58PIC92r_1024.jpg");
        mData.add(act2);

        Act act3 = new Act();
        act3.setCoverUrl("http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg");
        mData.add(act3);

        for (int i = 0; i < 4; i++) {
            Act act4 = new Act();
            act4.setCoverUrl("http://sc.jb51.net/uploads/allimg/150623/14-150623111Z1308.jpg");
            mData.add(act4);
        }

        Act act5 = new Act();
        act5.setCoverUrl("http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg");
        mData.add(act5);

        for (int i = 0; i < 5; i++) {
            Act act4 = new Act();
            act4.setCoverUrl("http://pic.58pic.com/58pic/13/60/97/48Q58PIC92r_1024.jpg");
            mData.add(act4);
        }

        for (int i = 0; i < 4; i++) {
            Act act4 = new Act();
            act4.setCoverUrl("http://sc.jb51.net/uploads/allimg/150623/14-150623111Z1308.jpg");
            mData.add(act4);
        }

        for (int i = 0; i < 5; i++) {
            Act act6 = new Act();
            act6.setCoverUrl("http://pic28.photophoto.cn/20130705/0036036843557471_b.jpg");
            mData.add(act6);
        }

        return mData;
    }
}
