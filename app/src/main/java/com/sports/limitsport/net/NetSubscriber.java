package com.sports.limitsport.net;

import android.content.Intent;
import android.text.TextUtils;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.base.LimitSportApplication;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.TokenTimeOutEvent;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by liuworkmac on 17/1/18.
 */
public abstract class NetSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {


        XLog.e(e.getMessage());

    }

    @Override
    public void onNext(T response) {
        if (response == null) {
            onError(new Throwable("null response"));
            return;
        }
        if (response instanceof BaseResponse && !((BaseResponse) response).isSuccess()) {
            String errorMsg = ((BaseResponse) response).getErrMsg();
            if (TextUtils.isEmpty(errorMsg)) {
                errorMsg = "success is false";
            }
            onError(new Throwable(errorMsg));
            error(response);
            XLog.e(errorMsg);
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
