package com.sports.limitsport.util;

import com.sports.limitsport.log.XLog;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by liuworkmac on 17/5/25.
 * 重试
 */

public class RetryWithDelay implements Func1<Observable<? extends Throwable>, Observable<?>> {
    private int maxRetries = 3;
    private int retryDelayMillis = 500;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
    }

    public RetryWithDelay() {
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {
        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
            @Override
            public Observable<?> call(Throwable throwable) {
                if (++retryCount <= maxRetries) {
                    // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                    XLog.e("get error, it will try after " + retryDelayMillis
                            + " millisecond, retry count " + retryCount);
                    return Observable.timer(retryDelayMillis,
                            TimeUnit.MILLISECONDS);
                }
                // Max retries hit. Just pass the error along.
                return Observable.error(throwable);
            }
        });
    }
}
