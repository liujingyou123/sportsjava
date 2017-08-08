package com.sports.limitsport.net;


import com.sports.limitsport.base.BaseResponse;

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
            return;
        }

        response(response);
    }

    public abstract void response(T response);
}
