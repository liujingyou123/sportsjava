package com.sports.limitsport.net;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiDetailResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.FineShowListResponse;
import com.sports.limitsport.model.Hobby;
import com.sports.limitsport.model.UserInfo;
import com.sports.limitsport.model.NewPersonListResponse;
import com.sports.limitsport.model.OrderRequest;
import com.sports.limitsport.model.PayOrderResponse;
import com.sports.limitsport.model.SignUpListResponse;
import com.sports.limitsport.model.TicketListResponse;
import com.sports.limitsport.model.UserInfoResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.Headers;
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

    //发布评论
    @POST(URLConstants.URL_PUBLISH_COMMENTS)
    Observable<BaseResponse> publistComments(@Body HashMap<String, String> hashMap);

    //发布评论
    @POST(URLConstants.URL_REPLAY_COMMENTS)
    Observable<BaseResponse> replayComments(@Body HashMap<String, String> hashMap);

    //票种列表
    @POST(URLConstants.URL_TICKET_LIST)
    Observable<TicketListResponse> getTicketList(@Body HashMap<String, String> hashMap);

    //订单支付
    @POST(URLConstants.URL_PAY_ORDER)
    Observable<PayOrderResponse> payOrder(@Body OrderRequest param);

    //报名列表
    @POST(URLConstants.URL_SIGN_UP_LIST)
    Observable<SignUpListResponse> getSignUpList(@Body HashMap<String, String> param);

    //新人列表
    @POST(URLConstants.URL_NEW_PERSON_LIST)
    Observable<NewPersonListResponse> getNewPersonList(@Body HashMap<String, String> param);

    //登录
    @POST(URLConstants.URL_LOGIN)
    Observable<UserInfo> loginIn(@Body HashMap<String, String> param);

    //个人爱好
    @Headers("Content-Type: application/json")
    @POST(URLConstants.URL_HOBBY_LIST)
    Observable<Hobby> getHobbys();

    //更新用户信息
    @POST(URLConstants.URL_UPDATE_USER_INFO)
    Observable<BaseResponse> updateUserInfo(@Body HashMap<String, String> param);

    //用户信息
    @Headers("Content-Type: application/json")
    @POST(URLConstants.URL_USER_INFO)
    Observable<UserInfoResponse> getUserInfo();
}
