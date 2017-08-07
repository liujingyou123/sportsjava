package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.IActivityListView;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.ActivityResponse;
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
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getActivityList(hashMap), new NetSubscriber<ActivityResponse>() {
            @Override
            public void response(ActivityResponse response) {
                if (mIActivityListView != null) {
                    mIActivityListView.showList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityListView != null) {
                    mIActivityListView.onError(e);
                }
            }
        });

    }

    public void clear() {
        mIActivityListView = null;
    }
}
