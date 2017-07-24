package com.sports.limitsport.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.net.Ironman;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuworkmac on 17/1/3.
 */
public class ToolsUtil {

    public static String toJsonString(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        XLog.json(json);
        return json;
    }

    public static String toJsonStringWithIgnore(Object object) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new FooAnnotationExclusionStrategy()).create();
        String json = gson.toJson(object);
        XLog.json(json);
        return json;
    }


    public static <T> Observable.Transformer<T, T> applayScheduers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.retryWhen(new RetryWithDelay()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> T createService(Class<T> serviceClazz) {
        return Ironman.getInstance().createService(serviceClazz);
    }

    public static <T> Subscription subscribe(Observable<T> observable, NetSubscriber<T> subscriber) {
        return observable.compose(ToolsUtil.<T>applayScheduers()).subscribe(subscriber);
    }

    public static <T> Subscription subscribe(Observable<T> observable, NoneNetSubscriber<T> subscriber) {
        return observable.compose(ToolsUtil.<T>applayScheduers()).subscribe(subscriber);
    }

    public static Date stringToDate(String timeStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = null;
        try {
            time = df.parse(timeStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    public static String dateToGMTStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        format.setCalendar(cal);
        return format.format(date);
    }
}
