package com.sports.limitsport.discovery.ui;

import com.sports.limitsport.model.DongTaiListResponse;

/**
 * Created by liuworkmac on 17/8/8.
 */

public interface IClubHistoryView {
    void showDongTaiList(DongTaiListResponse response);

    void onError(Throwable e);

    void onFocusReslult(boolean b, String id);

    void showPublishActivityComent(boolean b);

    void onPraiseResult(boolean b);

    void onCancelPraiseResult(boolean b);
}
