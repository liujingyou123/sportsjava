package com.sports.limitsport.net;


import android.content.Intent;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.base.LimitSportApplication;
import com.sports.limitsport.model.TokenTimeOutEvent;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import rx.Subscriber;

/**
 * Created by liuworkmac on 17/5/23.
 */

public abstract class NoneNetSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(T response) {
        if (response == null) {
            onError(new Throwable("null response"));
            return;
        }
        if (response instanceof BaseResponse && !((BaseResponse) response).isSuccess()) {
            onError(new Throwable(((BaseResponse) response).getErrMsg()));
            error(response);
            return;
        }

        response(response);
    }

    public void error(T response) {
        if (isTokenTimeOut(response)) {
            tokenTimeOutToLogin();
            ToastUtil.showFalseToast(null, "登录超时,请重新登录");
        }
    }

    public abstract void response(T response);

    private void tokenTimeOutToLogin() {
        SharedPrefsUtil.clearUserInfo();
        EventBus.getDefault().post(new TokenTimeOutEvent());
        Intent intent = new Intent();
        intent.setAction("com.sports.limitsport.loginActivity");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        LimitSportApplication.getInstance().startActivity(intent);
    }

    /**
     * token失效 判断
     */
    private boolean isTokenTimeOut(T response) {
        boolean ret = false;
        if ("999".equals(((BaseResponse) response).errCode)) {
            ret = true;
        }

        return ret;
    }
}
