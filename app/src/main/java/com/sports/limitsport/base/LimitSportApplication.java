package com.sports.limitsport.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.net.NetworkClient;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class LimitSportApplication extends Application {
    private static LimitSportApplication limitsportapplication;

    @Override
    public void onCreate() {
        super.onCreate();
        limitsportapplication = this;
        SDKInitializer.initialize(getApplicationContext());
        NetworkClient.init(this);
        Batman.getInstance().init(this);
        UMShareAPI.get(this);

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }


    {
        PlatformConfig.setWeixin("wx504d47f2dd5f9ad9", "a65ea8940a6eec051805e24e4e906d67");
        PlatformConfig.setQQZone("1106108474", "uMa1SFYnLsp4hs0i");
        PlatformConfig.setSinaWeibo("4150536070", "2386b3299bcd0be389a41e9e8436e91f", "http://sns.whalecloud.com");
    }

    public static LimitSportApplication getInstance() {
        return limitsportapplication;
    }
}
