package com.sports.limitsport.discovery.ui;

import com.sports.limitsport.model.MyCollectFineShowResponse;

/**
 * Created by liuworkmac on 17/7/26.
 */

public interface IFineShowView {
    void showFineShow(MyCollectFineShowResponse response);

    void onError(Throwable e);
}
