package com.sports.limitsport.model;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/27.
 * 活动报名订单
 */

public class OrderRequest {
    public String id;
    public String payType = "aliPay";
    public String ticketId;
    public String totalAmount;
    public String orderTime;
    public String receiptAmount;
    public String transactionType = "APP";
    public List<SignList> signList;
}
