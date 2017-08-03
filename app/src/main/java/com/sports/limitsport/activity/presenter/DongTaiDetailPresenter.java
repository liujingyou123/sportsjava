package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.IDongTaiDetailView;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiDetailResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/3.
 */

public class DongTaiDetailPresenter {
    IDongTaiDetailView mIDongTaiDetailView;

    public DongTaiDetailPresenter(IDongTaiDetailView mIDongTaiDetailView) {
        this.mIDongTaiDetailView = mIDongTaiDetailView;
    }

    /**
     * 评论列表
     *
     * @param id
     */
    public void getCommentList(String id, String pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", id);
        hashMap.put("pageNumber", pageNumber);
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getCommentList(hashMap), new NetSubscriber<CommentListResponse>() {
            @Override
            public void response(CommentListResponse response) {
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.showCommentList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.onError(e);
                }
            }
        });
    }

    public void getDongTaiDetail(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTailDetail(hashMap), new NetSubscriber<DongTaiDetailResponse>() {
            @Override
            public void response(DongTaiDetailResponse response) {
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.showDetail(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.onError(e);
                }
            }
        });
    }

    /**
     * 0:添加 1:移除 2:取消关注
     **/
    public void foucesFans(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "0");
        hashMap.put("receiveUserId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).focusFans(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    if (mIDongTaiDetailView != null) {
                        mIDongTaiDetailView.onFocusReslult(true);
                    }

                } else {
                    if (mIDongTaiDetailView != null) {
                        mIDongTaiDetailView.onFocusReslult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.onFocusReslult(false);
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
                if (mIDongTaiDetailView != null && response.isSuccess()) {
                    mIDongTaiDetailView.onPraiseResult(true);
                } else {
                    if (mIDongTaiDetailView != null) {
                        mIDongTaiDetailView.onPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.onPraiseResult(false);
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
                if (mIDongTaiDetailView != null && response.isSuccess()) {
                    mIDongTaiDetailView.onCancelPraiseResult(true);
                } else {
                    if (mIDongTaiDetailView != null) {
                        mIDongTaiDetailView.onCancelPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.onCancelPraiseResult(false);
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
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.showPublishActivityComent(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.showPublishActivityComent(false);
                }
            }
        });
    }


    /**
     * 回复动态评论
     *
     * @param replyCommentId 回复评论ID
     * @param replyUserId    回复对象ID
     * @param replyUserName  回复对象名称
     * @param replyContent   回复内容
     */
    public void replayComment(String replyCommentId, String replyUserId, String replyUserName, String replyContent) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("replyCommentId", replyCommentId);
        hashMap.put("replyUserId", replyUserId);
        hashMap.put("replyUserName", replyUserName);
        hashMap.put("replyContent", replyContent);

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).replayComments(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.showReplayComment(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIDongTaiDetailView != null) {
                    mIDongTaiDetailView.showReplayComment(false);
                }
            }
        });
    }

    public void clear() {
        mIDongTaiDetailView = null;
    }
}
