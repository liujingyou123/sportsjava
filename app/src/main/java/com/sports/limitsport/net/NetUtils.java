package com.sports.limitsport.net;


import com.sports.limitsport.BuildConfig;

public class NetUtils {

//    private static String baseUrl = "http://116.62.20.25/tst/ultimateapp/";
    private static String webUrl = "";

    private static String baseUrl = BuildConfig.BASEURL;



    public static String baseUrl() {
        return baseUrl;
    }
    public static String webUrl() {
        return webUrl;
    }
}
