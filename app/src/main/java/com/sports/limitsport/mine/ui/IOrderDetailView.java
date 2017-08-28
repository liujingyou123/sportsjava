package com.sports.limitsport.mine.ui;

import com.sports.limitsport.model.OrderDetailResponse;

/**
 * Created by liuworkmac on 17/8/16.
 */

public interface IOrderDetailView {
    void showOrderDetail(OrderDetailResponse response);

    void cancelOrder(boolean success);

    void refundOrderResult(boolean success, String erroMsg);
}
