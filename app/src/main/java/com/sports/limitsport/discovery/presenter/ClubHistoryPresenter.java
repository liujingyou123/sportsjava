package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.discovery.ui.IClubHistoryView;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/8.
 */

public class ClubHistoryPresenter {
    private IClubHistoryView mIClubHistoryView;

    public ClubHistoryPresenter(IClubHistoryView mIClubHistoryView) {
        this.mIClubHistoryView = mIClubHistoryView;
    }

    public void getDongTaiList(String id, int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clubId", id);
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "3");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTaiList(hashMap), new NoneNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mIClubHistoryView != null) {
                    mIClubHistoryView.showDongTaiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIClubHistoryView != null) {
                    mIClubHistoryView.onError(e);
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
                    if (mIClubHistoryView != null) {
                        mIClubHistoryView.onFocusReslult(true, id);
                    }

                } else {
                    if (mIClubHistoryView != null) {
                        mIClubHistoryView.onFocusReslult(false, id);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIClubHistoryView != null) {
                    mIClubHistoryView.onFocusReslult(false, id);
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
                if (mIClubHistoryView != null && response.isSuccess()) {
                    mIClubHistoryView.showPublishActivityComent(true);
                } else {
                    if (mIClubHistoryView != null) {
                        mIClubHistoryView.showPublishActivityComent(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIClubHistoryView != null) {
                    mIClubHistoryView.showPublishActivityComent(false);
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
                if (mIClubHistoryView != null && response.isSuccess()) {
                    mIClubHistoryView.onPraiseResult(true);
                } else {
                    if (mIClubHistoryView != null) {
                        mIClubHistoryView.onPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIClubHistoryView != null) {
                    mIClubHistoryView.onPraiseResult(false);
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
                if (mIClubHistoryView != null && response.isSuccess()) {
                    mIClubHistoryView.onCancelPraiseResult(true);
                } else {
                    if (mIClubHistoryView != null) {
                        mIClubHistoryView.onCancelPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIClubHistoryView != null) {
                    mIClubHistoryView.onCancelPraiseResult(false);
                }
            }
        });
    }

    public void clear() {
        mIClubHistoryView = null;
    }
}
