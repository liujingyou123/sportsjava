package com.sports.limitsport.notice.presenter;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.DongTaiOrRecommendResponse;
import com.sports.limitsport.model.RecomendFriendsListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.notice.ui.INoticeView;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/10.
 */

public class NoticePersonPresenter {
    private INoticeView mINoticeView;

    public NoticePersonPresenter(INoticeView mINoticeView) {
        this.mINoticeView = mINoticeView;
    }

    public void getRecommendOrDongTai(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getRecommendorFocus(hashMap), new NetSubscriber<DongTaiOrRecommendResponse>() {
            @Override
            public void response(DongTaiOrRecommendResponse response) {
                if (mINoticeView != null) {
                    mINoticeView.showRecommendOrDongTai(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINoticeView != null) {
                    mINoticeView.onError(e);
                }
            }
        });
    }

    public void getRecommendFriends(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).recommedFriends(hashMap), new NetSubscriber<RecomendFriendsListResponse>() {
            @Override
            public void response(RecomendFriendsListResponse response) {
                if (mINoticeView != null) {
                    mINoticeView.showFriendsList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINoticeView != null) {
                    mINoticeView.onError(e);
                }
            }
        });
    }

    public void getFocusPersonDongTai(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getFocusPersonDongTaiList(hashMap), new NetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mINoticeView != null) {
                    mINoticeView.dongTaiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINoticeView != null) {
                    mINoticeView.onError(e);
                }
            }
        });
    }

    /**
     * 0:添加 1:移除 2:取消关注
     **/
    public void foucesFans(String userId, final String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "0");
        hashMap.put("receiveUserId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).focusFans(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    if (mINoticeView != null) {
                        mINoticeView.onFocusReslult(true, id);
                    }

                } else {
                    if (mINoticeView != null) {
                        mINoticeView.onFocusReslult(false, id);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINoticeView != null) {
                    mINoticeView.onFocusReslult(false, id);
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
                if (mINoticeView != null && response.isSuccess()) {
                    mINoticeView.showPublishActivityComent(true);
                } else {
                    if (mINoticeView != null) {
                        mINoticeView.showPublishActivityComent(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINoticeView != null) {
                    mINoticeView.showPublishActivityComent(false);
                }
            }
        });
    }

    /**
     * 点赞
     *
     * @param articleId
     */
    public void praise(String articleId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).praise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mINoticeView != null && response.isSuccess()) {
                    mINoticeView.onPraiseResult(true);
                } else {
                    if (mINoticeView != null) {
                        mINoticeView.onPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINoticeView != null) {
                    mINoticeView.onPraiseResult(false);
                }
            }
        });
    }

    /**
     * 取消点赞
     *
     * @param articleId
     */
    public void cancelPraise(String articleId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).cancelPraise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mINoticeView != null && response.isSuccess()) {
                    mINoticeView.onCancelPraiseResult(true);
                } else {
                    if (mINoticeView != null) {
                        mINoticeView.onCancelPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINoticeView != null) {
                    mINoticeView.onCancelPraiseResult(false);
                }
            }
        });
    }

    public void foucesFansRecommend(final String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "0");
        hashMap.put("receiveUserId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).focusFans(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    if (mINoticeView != null) {
                        mINoticeView.onFocusReslult(true, userId);
                    }

                } else {
                    if (mINoticeView != null) {
                        mINoticeView.onFocusReslult(false, userId);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINoticeView != null) {
                    mINoticeView.onFocusReslult(false, userId);
                }
            }
        });
    }

    public void clear() {
        mINoticeView = null;
    }


}
