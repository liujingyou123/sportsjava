package com.sports.limitsport.activity.ui;

import com.sports.limitsport.model.PayOrderResponse;

/**
 * Created by liuworkmac on 17/7/27.
 */

public interface IPayOrderView {
    void showPayOrderResult(PayOrderResponse response);

    void showPayResult(boolean isSuccess, String OrderNo);
    void onError(Throwable e);
}
