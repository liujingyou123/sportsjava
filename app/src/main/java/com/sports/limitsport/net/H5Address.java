package com.sports.limitsport.net;

/**
 * Created by liuworkmac on 17/8/15.
 */

public class H5Address {
    private static final String URL_ACTIVITY_DETAIL = "h5/activity/queryActivityInfo/";

    public static String getUrlActivityDetail(String id) {
        return NetUtils.baseUrl()+URL_ACTIVITY_DETAIL+id+"/v1.0.0";
    }

}
