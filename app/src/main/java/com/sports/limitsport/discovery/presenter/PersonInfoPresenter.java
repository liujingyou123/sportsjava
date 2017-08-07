package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.discovery.ui.IPersonInfoView;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.MyCollectActivityResponse;
import com.sports.limitsport.model.UserInfo;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/2.
 */

public class PersonInfoPresenter {
    private IPersonInfoView mIPersonInfoView;

    public PersonInfoPresenter(IPersonInfoView mIPersonInfoView) {
        this.mIPersonInfoView = mIPersonInfoView;
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getUserInfo(hashMap), new NetSubscriber<UserInfoResponse>() {
            @Override
            public void response(UserInfoResponse response) {
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showUserInfo(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    /**
     * 参加的活动
     *
     * @param userId
     */
    public void getActivityList(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", 1 + "");
        hashMap.put("pageSize", "1");
        hashMap.put("userId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getJoinActivityList(hashMap), new NetSubscriber<ActivityResponse>() {
            @Override
            public void response(ActivityResponse response) {
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showActivityList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.onError(e, "activity");
                }
            }
        });
    }

    /**
     * 参加的俱乐部
     */
    public void getAllClubsList(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "2");
        hashMap.put("type", "4");
        hashMap.put("userId", userId);

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubList(hashMap), new NetSubscriber<ClubListResponse>() {
            @Override
            public void response(ClubListResponse response) {
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showClubsList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.onError(e, "club");
                }

            }
        });
    }

    /**
     * 动态
     *
     * @param pageNumber
     */
    public void getDongTaiList(int pageNumber, String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "1");
        hashMap.put("userId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTaiList(hashMap), new LoadingNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showDongTaiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.onError(e, "dongtai");
                }
            }
        });
    }

    /**
     * 发布动态评论
     *
     * @param articleId 评论对象ID
     * @param content   评论内容
     */
    public void publishActivityComment(String articleId, String content) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("content", content);
        hashMap.put("commentType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).publistComments(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIPersonInfoView != null && response.isSuccess()) {
                    mIPersonInfoView.showPublishActivityComent(true);
                } else {
                    if (mIPersonInfoView != null) {
                        mIPersonInfoView.showPublishActivityComent(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showPublishActivityComent(false);
                }
            }
        });
    }

    /**
     * 点赞
     * @param articleId
     */
    public void praise(String articleId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).praise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIPersonInfoView != null && response.isSuccess()) {
                    mIPersonInfoView.onPraiseResult(true);
                } else {
                    if (mIPersonInfoView != null) {
                        mIPersonInfoView.onPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.onPraiseResult(false);
                }
            }
        });
    }

    /**
     * 取消点赞
     * @param articleId
     */
    public void cancelPraise(String articleId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).cancelPraise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIPersonInfoView != null && response.isSuccess()) {
                    mIPersonInfoView.onCancelPraiseResult(true);
                } else {
                    if (mIPersonInfoView != null) {
                        mIPersonInfoView.onCancelPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.onCancelPraiseResult(false);
                }
            }
        });
    }

    public void clear() {
        mIPersonInfoView = null;
    }
}
