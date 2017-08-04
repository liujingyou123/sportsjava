package com.sports.limitsport.mine.ui;

import com.sports.limitsport.model.MyCollectFineShowResponse;

/**
 * Created by liuworkmac on 17/8/4.
 */

public interface IMyCollectFineShowView {
    void showFineShow(MyCollectFineShowResponse response);

    void onError(Throwable e);
}
