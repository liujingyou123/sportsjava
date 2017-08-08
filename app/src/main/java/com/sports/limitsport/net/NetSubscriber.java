package com.sports.limitsport.net;

import android.text.TextUtils;
import android.util.Log;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.log.XLog;

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


        if (isTokenTimeOut(e)) {
//            RxBus.get().post(RxBusTags.TAG_TIME_OUT, "1");
            //登录超时弹框
//            LoadingDialogUtil.getInstance().showTimeOutDialog();
            return;
        }

        XLog.e(e.getMessage());

//        ToastUtil.show(WinPortApplication.getInstance().getApplicationContext(), e.getMessage());

    }

    private void tokenTimeOutToLogin() {
//        SharedPrefsUtil.clearUserInfo();
//        EventBus.getDefault().post(new TokenTimeOutEvent());
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
    }

    public abstract void response(T response);

    /**
     * token失效 判断
     *
     * @param e
     */
    private boolean isTokenTimeOut(Throwable e) {
        boolean ret = false;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            if (httpException.response() != null && httpException.response().errorBody() != null) {
                try {
                    JSONObject jsonObject = new JSONObject(httpException.response().errorBody().string());
                    if (jsonObject.optInt("errCode") == -99) {
                        Log.e("NetSubscriber =  ", "- 99");
                        ret = true;
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return ret;
    }
}
