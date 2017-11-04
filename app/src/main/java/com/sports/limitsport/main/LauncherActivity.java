package com.sports.limitsport.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.sports.limitsport.R;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.SpUtil;
import com.sports.limitsport.util.ToolsUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by liuworkmac on 17/7/28.
 */

public class LauncherActivity extends AppCompatActivity {

    private Subscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        init();

    }

    private void init() {

        mSubscription = Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                String interTimes = SpUtil.getInstance().getStringData("interTimes", null);
                if (TextUtils.isEmpty(interTimes)) {

                    Intent intent = new Intent(LauncherActivity.this, AdActivity.class);
                    startActivity(intent);
                    LauncherActivity.this.finish();
                } else {
                    getAdvList();
                }
            }
        });
    }


    /**
     * 获取轮播图片
     */
    public void getAdvList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("adType", "2");
        hashMap.put("position", "0");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getAdvList(hashMap), new NoneNetSubscriber<AdvertiseInfoResponse>() {
            @Override
            public void response(AdvertiseInfoResponse response) {

                if (response.getData() == null || response.getData().size() == 0) {
                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                    startActivity(intent);
                    LauncherActivity.this.finish();
                } else {

                    Intent login = new Intent(LauncherActivity.this, AdvertActivity.class);
                    ArrayList<AdvertiseInfoResponse.DataBean> list = (ArrayList<AdvertiseInfoResponse.DataBean>) response.getData();
                    login.putExtra("data", list);
                    startActivity(login);
                    LauncherActivity.this.finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
//        getTestAdvData();
    }

    @Override
    protected void onDestroy() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
