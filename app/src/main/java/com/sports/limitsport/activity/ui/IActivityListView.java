package com.sports.limitsport.activity.ui;

import com.sports.limitsport.model.ActivityResponse;

/**
 * Created by liuworkmac on 17/7/24.
 * 活动列表
 */

public interface IActivityListView {

    void showList(ActivityResponse response);

    void onError(Throwable e);
}
