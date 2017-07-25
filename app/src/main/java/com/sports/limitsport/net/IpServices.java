package com.sports.limitsport.net;

import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.AdvertiseInfoResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liuworkmac on 17/7/24.
 */

public interface IpServices {

    //活动里列表
    @POST(URLConstants.URL_ACTIVITY_LIST)
    Observable<ActivityResponse> getActivityList(@Body HashMap<String, String> hashMap);

    //俱乐部列表
    @POST(URLConstants.URL_CLUBS_LIST)
    Observable<ClubListResponse> getClubList(@Body HashMap<String, String> hashMap);

    //轮播图
    @POST(URLConstants.URL_ADV_LIST)
    Observable<AdvertiseInfoResponse> getAdvList(@Body HashMap<String, String> hashMap);
}
