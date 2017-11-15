package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.IActivityDetailView;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.ReplayCommentsResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/25.
 */

public class ActivityDetailPresenter {
    private IActivityDetailView mIActivityDetailView;

    public ActivityDetailPresenter(IActivityDetailView mIActivityDetailView) {
        this.mIActivityDetailView = mIActivityDetailView;
    }

    public void getActivityDetail(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getActivityDetail(hashMap), new NetSubscriber<ActivityDetailResponse>() {
            @Override
            public void response(ActivityDetailResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.showActivityDetail(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.onError(e);
                }
            }
        });
    }

    /**
     * 大家都在晒
     *
     * @param id
     */
    public void getAllShai(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("activityId", id);
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "0");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTaiList(hashMap), new NoneNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.showAllShiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.onError(e);
                }
            }
        });
    }

    /**
     * 评论列表
     *
     * @param id
     */
    public void getCommentList(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", id);
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "10");
        hashMap.put("commentType", "3");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getCommentList(hashMap), new NetSubscriber<CommentListResponse>() {
            @Override
            public void response(CommentListResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.showCommentList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.onError(e);
                }
            }
        });
    }


    /**
     * 回复活动评论
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

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).replayComments(hashMap), new NetSubscriber<ReplayCommentsResponse>() {
            @Override
            public void response(ReplayCommentsResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.showReplayComment(true,response.getData());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.showReplayComment(false, null);
                }
            }
        });
    }

    /**
     * 收藏
     *
     * @param id 活动ID
     */
    public void collect(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("collectionId", id);
        hashMap.put("type", "1");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).collect(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.collectReslut(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.collectReslut(false);
                }
            }
        });
    }


    /**
     * 收藏
     *
     * @param id 活动ID
     */
    public void unCollect(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("collectionId", id);
        hashMap.put("type", "1");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).cancelCollect(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.cancelCollectReslut(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.cancelCollectReslut(false);
                }
            }
        });
    }


    public void clear() {
        mIActivityDetailView = null;
    }
}
