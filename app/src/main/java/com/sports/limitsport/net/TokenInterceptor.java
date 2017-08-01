package com.sports.limitsport.net;

import android.text.TextUtils;
import android.util.Log;


import com.sports.limitsport.log.XLog;
import com.sports.limitsport.util.SharedPrefsUtil;

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

        }

        newBuilder.addHeader("Content-Type", "application/json");
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
        try {
            if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData() != null) {
                token = SharedPrefsUtil.getUserInfo().getData().getAccessToken();
            }
        } catch (Exception e) {
            token = "";
            e.printStackTrace();
        }
        XLog.d("token-->" + token);
//        Log.d(getClass().getSimpleName(), "token-->" + token);
        return "1BBE46B083F46901E9D05014CE792C08";//"1BBE46B083F46901E9D05014CE792C08"
    }
}
