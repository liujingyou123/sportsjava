package com.sports.limitsport.base;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class BaseResponse {
    public boolean isSuccess;
    public String errMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
