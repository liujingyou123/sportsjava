package com.sports.limitsport.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.sports.limitsport.aliyunoss.AliOss;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.net.NetworkClient;
import com.sports.limitsport.util.SharedPrefsUtil;
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
        AliOss.getInstance().init(this);
        NetworkClient.init(this);
        Batman.getInstance().init(this);
        UMShareAPI.get(this);
        SharedPrefsUtil.initSharedPrefers(this);
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }


    {
        PlatformConfig.setWeixin("wxed8eeb25f2298870", "87bc38ce0b305d763912b0efb3356b64");
        PlatformConfig.setQQZone("1106349224", "I9TV3SLS0ojCXKS0");
        PlatformConfig.setSinaWeibo("1565300412", "15731b921e940e85128a647859354045", "http://sns.whalecloud.com");
    }

    public static LimitSportApplication getInstance() {
        return limitsportapplication;
    }
}
