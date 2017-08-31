package com.sports.limitsport.net;

/**
 * Created by liuworkmac on 17/8/15.
 */

public class H5Address {
    private static final String URL_ACTIVITY_DETAIL = "h5/activity/queryActivityInfo/";
    private static final String URL_PERSON_INFO = "h5/personal/queryPersonalInfo/";
    private static final String URL_FINE_SHOW = "h5/wonderfullShow/queryWonderfullShowDetail/";
    private static final String URL_CLUB = "h5/club/queryClubInfo/";
    private static final String URL_DONG_TAI = "/h5/trend/queryTrend/";
    private static final String URL_ADV = "/h5/propaganda/queryPropagandaImg";




    public static String getUrlActivityDetail(String id) {
        return NetUtils.baseUrl() + URL_ACTIVITY_DETAIL + id + "/v1.0.0";
    }

    public static String getUserInforUrl(String userId) {
        return NetUtils.baseUrl() + URL_PERSON_INFO + userId + "/v1.0.0";
    }

    public static String getFineShow(String id) {
        return NetUtils.baseUrl() + URL_FINE_SHOW + id + "/v1.0.0";
    }

    public static String getClub(String id) {
        return NetUtils.baseUrl() + URL_CLUB + id + "/v1.0.0";
    }

    public static String getDongTai(String id) {
        return NetUtils.baseUrl() + URL_DONG_TAI + id + "/v1.0.0";
    }

    public static String getAdv() {
        return NetUtils.baseUrl() + URL_ADV;
    }

}
