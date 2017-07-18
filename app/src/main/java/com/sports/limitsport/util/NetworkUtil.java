package com.sports.limitsport.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    private static final String TAG = NetworkUtil.class.getSimpleName();

    public static boolean isNetworkConnected(Context context) {
        boolean ret = true;
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager == null) {
                ret = false;
            } else {
                NetworkInfo networkinfo = manager.getActiveNetworkInfo();
                if (networkinfo == null || !networkinfo.isAvailable() || !networkinfo.isConnectedOrConnecting()) {
                    ret = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }
        return ret;
    }
}
