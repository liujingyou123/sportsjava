package com.sports.limitsport.net;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.log.XLog;

import rx.Subscriber;

/**
 * Created by liuworkmac on 17/9/1.
 */

public abstract class NoTimeOutSubscriber<T> extends Subscriber<T> {
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
            onError(new Throwable(((BaseResponse) response).getErrMsg()));
            return;
        }

        response(response);
    }

    public abstract void response(T response);

}
