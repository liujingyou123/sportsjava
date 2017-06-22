package com.sports.limitsport.net;


/**
 * Created by liuworkmac on 17/1/19.
 */
public abstract class LoadingNetSubscriber<T> extends NetSubscriber<T>{

    @Override
    public void onStart() {
        super.onStart();
//        LoadingDialogUtil.getInstance().showLoading();
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
//        LoadingDialogUtil.getInstance().hideLoading();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
//        LoadingDialogUtil.getInstance().hideLoading();
    }
}
