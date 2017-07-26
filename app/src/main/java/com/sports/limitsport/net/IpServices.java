package com.sports.limitsport.net;

import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiDetailResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.FineShowListResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liuworkmac on 17/7/24.
 */

public interface IpServices {

    //活动列表
    @POST(URLConstants.URL_ACTIVITY_LIST)
    Observable<ActivityResponse> getActivityList(@Body HashMap<String, String> hashMap);

    //活动详情
    @POST(URLConstants.URL_ACTIVITY_DETAIL)
    Observable<ActivityDetailResponse> getActivityDetail(@Body HashMap<String, String> hashMap);

    //俱乐部列表
    @POST(URLConstants.URL_CLUBS_LIST)
    Observable<ClubListResponse> getClubList(@Body HashMap<String, String> hashMap);

    //轮播图
    @POST(URLConstants.URL_ADV_LIST)
    Observable<AdvertiseInfoResponse> getAdvList(@Body HashMap<String, String> hashMap);

    //动态列表
    @POST(URLConstants.URL_DONGTAI_LIST)
    Observable<DongTaiListResponse> getDongTaiList(@Body HashMap<String, String> hashMap);

    //动态详情
    @POST(URLConstants.URL_DONGTAI_DETAIL)
    Observable<DongTaiDetailResponse> getDongTailDetail(@Body HashMap<String, String> hashMap);

    //评论列表
    @POST(URLConstants.URL_COMMENT_LIST)
    Observable<CommentListResponse> getCommentList(@Body HashMap<String, String> hashMap);

    //精彩秀列表
    @POST(URLConstants.URL_FINESHOW_LIST)
    Observable<FineShowListResponse> getFineShowList(@Body HashMap<String, String> hashMap);
}