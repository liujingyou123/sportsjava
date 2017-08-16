package com.sports.limitsport.net;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubDetailResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.ClubMemberList;
import com.sports.limitsport.model.ClubMembersResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiDetailResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.DongTaiOrRecommendResponse;
import com.sports.limitsport.model.FansListResponse;
import com.sports.limitsport.model.FineShowDetailResponse;
import com.sports.limitsport.model.FineShowListResponse;
import com.sports.limitsport.model.Hobby;
import com.sports.limitsport.model.HuDongNoticeListResponse;
import com.sports.limitsport.model.MessageResponse;
import com.sports.limitsport.model.MyCollectActivityResponse;
import com.sports.limitsport.model.MyCollectFineShowResponse;
import com.sports.limitsport.model.NewNoticeResponse;
import com.sports.limitsport.model.NoticeListResponse;
import com.sports.limitsport.model.OrderDetailResponse;
import com.sports.limitsport.model.PraiseListResponse;
import com.sports.limitsport.model.RecomendFriendsListResponse;
import com.sports.limitsport.model.UserInfo;
import com.sports.limitsport.model.NewPersonListResponse;
import com.sports.limitsport.model.OrderRequest;
import com.sports.limitsport.model.PayOrderResponse;
import com.sports.limitsport.model.SignUpListResponse;
import com.sports.limitsport.model.TicketListResponse;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.model.UserSettingInfoResponse;

import java.util.HashMap;
import java.util.Map;

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
    Observable<MyCollectFineShowResponse> getFineShowList(@Body HashMap<String, String> hashMap);

    //精彩秀详情
    @POST(URLConstants.URL_FINESHOW_DETAIL)
    Observable<FineShowDetailResponse> getFineShowDetail(@Body HashMap<String, String> hashMap);

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
    @POST(URLConstants.URL_USER_INFO)
    Observable<UserInfoResponse> getUserInfo(@Body HashMap<String, String> param);

    //用户个人设置
    @Headers("Content-Type: application/json")
    @POST(URLConstants.URL_PERSION_SETTING)
    Observable<UserSettingInfoResponse> getUserSettingInfo();

    //设置系统通知开关
    @POST(URLConstants.URL_SET_SYSTEM_NOTICE)
    Observable<BaseResponse> setSystemNotice(@Body HashMap<String, String> param);

    //设置互动消息
    @POST(URLConstants.URL_SET_MESSAGE)
    Observable<BaseResponse> setMessageNotice(@Body HashMap<String, String> param);

    //收藏
    @POST(URLConstants.URL_COLLECT)
    Observable<BaseResponse> collect(@Body HashMap<String, String> param);

    //取消收藏
    @POST(URLConstants.URL_CANCEL_COLLECT)
    Observable<BaseResponse> cancelCollect(@Body HashMap<String, String> param);

    //收藏
    @POST(URLConstants.URL_NOTICE_LIST)
    Observable<NoticeListResponse> getNoticeList(@Body HashMap<String, String> param);

    //互动列表
    @POST(URLConstants.URL_HUDONG_LIST)
    Observable<HuDongNoticeListResponse> getHuDongList(@Body HashMap<String, String> param);

    //举报
    @POST(URLConstants.URL_TIP_OFF)
    Observable<BaseResponse> tipOff(@Body HashMap<String, String> param);

    //参加过的活动
    @POST(URLConstants.URL_JIONED_ACTIVITYS)
    Observable<ActivityResponse> getJoinActivityList(@Body HashMap<String, String> hashMap);

    //添加取消粉丝移除关注接口
    @POST(URLConstants.URL_FOCUS_FANS)
    Observable<BaseResponse> focusFans(@Body HashMap<String, String> hashMap);

    //新消息通知列表
    @Headers("Content-Type: application/json")
    @POST(URLConstants.URL_NEW_NOTICE)
    Observable<NewNoticeResponse> getNewNotice();

    //我的粉丝
    @POST(URLConstants.URL_MY_FANS_LIST)
    Observable<FansListResponse> getMyFansList(@Body HashMap<String, String> hashMap);

    //我的关注
    @POST(URLConstants.URL_MY_FOCUS_LIST)
    Observable<FansListResponse> getMyFoucsList(@Body HashMap<String, String> hashMap);

    //我的关注
    @POST(URLConstants.URL_SMS_CODE)
    Observable<MessageResponse> getSmsCode(@Body HashMap<String, String> hashMap);

    //点赞
    @POST(URLConstants.URL_PRAISE)
    Observable<BaseResponse> praise(@Body HashMap<String, String> hashMap);

    //取消点赞
    @POST(URLConstants.URL_CANCEL_PRAISE)
    Observable<BaseResponse> cancelPraise(@Body HashMap<String, String> hashMap);

    //我收藏动态列表
    @POST(URLConstants.URL_MY_COLLECT_DONGTAI)
    Observable<DongTaiListResponse> getMyCollectDongTaiList(@Body HashMap<String, String> hashMap);

    //我收藏精彩秀列表
    @POST(URLConstants.URL_MY_COLLECT_FINE_SHOW)
    Observable<MyCollectFineShowResponse> getMyCollectFineShowList(@Body HashMap<String, String> hashMap);

    //活动列表
    @POST(URLConstants.URL_MY_COLLECT_ACTIVITYS_SHOW)
    Observable<MyCollectActivityResponse> getCollectActivityList(@Body HashMap<String, String> hashMap);

    //俱乐部详情
    @POST(URLConstants.URL_CLUB_DETAIL)
    Observable<ClubDetailResponse> getClubDetail(@Body HashMap<String, String> hashMap);

    //俱乐部成员列表
    @POST(URLConstants.URL_CLUB_MEMBERS)
    Observable<ClubMembersResponse> getClubMembers(@Body HashMap<String, String> hashMap);

    //发布动态
    @POST(URLConstants.URL_PUBLISH_DONGTAI)
    Observable<BaseResponse> publishDongTai(@Body HashMap<String, Object> hashMap);

    //点赞人员列表
    @POST(URLConstants.URL_PRAISE_LIST)
    Observable<PraiseListResponse> praiseList(@Body HashMap<String, String> hashMap);

    //加入俱乐部
    @POST(URLConstants.URL_JOIN_CLUB)
    Observable<BaseResponse> joinClub(@Body HashMap<String, String> hashMap);

    //退出俱乐部
    @POST(URLConstants.URL_QUIT_CLUB)
    Observable<BaseResponse> quiteClub(@Body HashMap<String, String> hashMap);

    //推荐好友
    @POST(URLConstants.URL_RECOMEND_FRIENDS)
    Observable<RecomendFriendsListResponse> recommedFriends(@Body HashMap<String, String> hashMap);

    //关注的人的动态
    @POST(URLConstants.URL_FOCUS_PERSON_DONG_TAI_LIST)
    Observable<DongTaiListResponse> getFocusPersonDongTaiList(@Body HashMap<String, String> hashMap);

    //关注的人动态或者推荐用户列表
    @POST(URLConstants.URL_RECOMMEND_OR_FOCUS)
    Observable<DongTaiOrRecommendResponse> getRecommendorFocus(@Body HashMap<String, String> hashMap);

    //获取订单详情
    @POST(URLConstants.URL_ORDER_DETAIL)
    Observable<OrderDetailResponse> getOrderDetail(@Body HashMap<String, String> hashMap);

    //取消订单
    @POST(URLConstants.URL_CANCEL_ORDER)
    Observable<BaseResponse> cancelOrder(@Body HashMap<String, String> hashMap);

    //退款
    @POST(URLConstants.URL_REFUND_ORDER)
    Observable<BaseResponse> reFundOrder(@Body HashMap<String, String> hashMap);

    //验签
    @POST(URLConstants.URL_CHECK_ORDER)
    Observable<BaseResponse> checkOrder(@Body Map<String, String> hashMap);
}
