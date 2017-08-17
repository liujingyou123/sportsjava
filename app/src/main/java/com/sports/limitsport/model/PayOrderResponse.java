package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/7/27.
 */

public class PayOrderResponse {
    public boolean success;
    public String errMsg;
    public String errCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public DataBean data;

    public static class DataBean {
        public String orderInfo;
        public String orderNo;
    }
}
