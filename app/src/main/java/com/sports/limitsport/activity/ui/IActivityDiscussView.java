package com.sports.limitsport.activity.ui;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.CommentListResponse;

/**
 * Created by liuworkmac on 17/7/26.
 * 活动讨论区
 */

public interface IActivityDiscussView {
    void showCommentList(CommentListResponse response);

    void showPublishActivityComent(boolean isSuccess);

    void showReplayComment(boolean isSuccess, String id);

    void onError(Throwable e);
}
