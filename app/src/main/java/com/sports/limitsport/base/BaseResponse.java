package com.sports.limitsport.base;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class BaseResponse {
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
}
