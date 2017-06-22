package com.sports.limitsport.base;

import android.app.Application;

import com.sports.limitsport.image.Batman;
import com.sports.limitsport.net.NetworkClient;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class LimitSportApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkClient.init(this);
        Batman.getInstance().init(this);
    }
}
