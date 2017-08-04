package com.sports.limitsport.mine.ui;

import com.sports.limitsport.model.DongTaiListResponse;

/**
 * Created by liuworkmac on 17/8/4.
 */

public interface IMyCollectDongTaiView {

    void showDongTaiList(DongTaiListResponse response);

    void onError(Throwable e);

    void onFocusReslult(boolean b, String id);

    void showPublishActivityComent(boolean b);

    void onPraiseResult(boolean b);

    void onCancelPraiseResult(boolean b);
}
