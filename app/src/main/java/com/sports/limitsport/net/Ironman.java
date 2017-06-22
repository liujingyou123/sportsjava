package com.sports.limitsport.net;


import android.util.LruCache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sports.limitsport.util.FooAnnotationExclusionStrategy;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ironman {
    private static Ironman ironman = new Ironman();
    private Retrofit retrofit;
    private LruCache<String, Object> cache;

    private Ironman() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new FooAnnotationExclusionStrategy()).create();
        retrofit = new Retrofit.Builder()
                .baseUrl(NetUtils.baseUrl())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(NetworkClient.getOkHttpClient())
                .build();

        cache = new LruCache<String, Object>(Integer.MAX_VALUE);

    }

    public static Ironman getInstance() {
        return ironman;
    }

    public <T> T createService(Class<T> serviceClazz) {
        String name = serviceClazz.getName();
        Object o = cache.get(name);
        T service;
        if (o != null) {
            service = (T) o;
        } else {
            service = retrofit.create(serviceClazz);
            cache.put(name, service);
        }
        return service;
    }

}
