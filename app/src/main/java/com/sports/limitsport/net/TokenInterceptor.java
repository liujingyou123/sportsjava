package com.sports.limitsport.net;

import android.text.TextUtils;
import android.util.Log;


import java.io.IOException;
import java.lang.reflect.Field;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liuworkmac on 17/5/15.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String tokenKey = getTokenKey();
        Request newRequest = chain.request();
        Request.Builder newBuilder = newRequest.newBuilder();
        if (!TextUtils.isEmpty(tokenKey)) {
            newBuilder.addHeader("X-token", tokenKey);
//            newRequest = newRequest.newBuilder()
//                    .addHeader("X-token", tokenKey).build();
        }

        newBuilder.addHeader("Content-Type", "application/json");
//        newRequest = newRequest.newBuilder().addHeader("Content-Type", "application/json").build();
        newRequest = newBuilder.build();
        MediaType mediaType = newRequest.body().contentType();
        try {
            if (null != mediaType) {
                Field field = mediaType.getClass().getDeclaredField("mediaType");
                field.setAccessible(true);
                field.set(mediaType, "application/json");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return chain.proceed(newRequest);
    }

    private String getTokenKey() {
        String token = null;
//        try {
//            if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().data != null) {
//                token = SharedPrefsUtil.getUserInfo().data.accessToken;
//            }
//        } catch (Exception e) {
//            token = "";
//            e.printStackTrace();
//        }
//        Log.d(getClass().getSimpleName(), "token-->" + token);
        return "1BBE46B083F46901E9D05014CE792C08";//"0CB9F815528983E3707F944A9113AADD"
    }
}
