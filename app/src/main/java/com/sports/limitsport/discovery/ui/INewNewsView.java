package com.sports.limitsport.discovery.ui;

import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.DongTaiListResponse;

/**
 * Created by liuworkmac on 17/7/24.
 */

public interface INewNewsView {
    void showAdvList(AdvertiseInfoResponse response);

    void showAllDongTai(DongTaiListResponse response);

    void onError(Throwable e);

    void onCancelPraiseResult(boolean b, String articleId, String type);

    void onPraiseResult(boolean b, String articleId, String type);
}
