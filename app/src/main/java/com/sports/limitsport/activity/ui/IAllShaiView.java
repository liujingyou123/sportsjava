package com.sports.limitsport.activity.ui;

import com.sports.limitsport.model.DongTaiListResponse;

/**
 * Created by liuworkmac on 17/7/26.
 */

public interface IAllShaiView {
    void showAllShiList(DongTaiListResponse response);

    void onError(Throwable e);

    void onFocusReslult(boolean b, String id);

    void showPublishActivityComent(boolean b);

    void onPraiseResult(boolean b);
}
