package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.IActivityDiscussView;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/26.
 * 活动讨论区
 */

public class ActivityDiscussPresenter {
    IActivityDiscussView mIActivityDiscussView;

    public ActivityDiscussPresenter(IActivityDiscussView mIActivityDiscussView) {
        this.mIActivityDiscussView = mIActivityDiscussView;
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
        hashMap.put("commentType", "3");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getCommentList(hashMap), new NetSubscriber<CommentListResponse>() {
            @Override
            public void response(CommentListResponse response) {
                if (mIActivityDiscussView != null) {
                    mIActivityDiscussView.showCommentList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDiscussView != null) {
                    mIActivityDiscussView.onError(e);
                }
            }
        });
    }

    /**
     * 发布活动评论
     *
     * @param articleId 评论对象ID
     * @param content   评论内容
     */
    public void publishActivityComment(String articleId, String content) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("content", content);
        hashMap.put("commentType", "3");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).publistComments(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIActivityDiscussView != null) {
                    mIActivityDiscussView.showPublishActivityComent(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDiscussView != null) {
                    mIActivityDiscussView.showPublishActivityComent(false);
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

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).replayComments(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIActivityDiscussView != null) {
                    mIActivityDiscussView.showReplayComment(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDiscussView != null) {
                    mIActivityDiscussView.showReplayComment(false);
                }
            }
        });
    }

    public void clear() {
        mIActivityDiscussView = null;
    }
}
