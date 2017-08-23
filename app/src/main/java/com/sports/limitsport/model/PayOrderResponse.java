package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/7/27.
 */

public class PayOrderResponse extends BaseResponse{
    public DataBean data;

    public static class DataBean {
        public String orderInfo;
        public String orderNo;
        public String isFree;
    }
}
