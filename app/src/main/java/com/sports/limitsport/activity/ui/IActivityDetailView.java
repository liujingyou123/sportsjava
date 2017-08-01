package com.sports.limitsport.activity.ui;

import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiListResponse;

/**
 * Created by liuworkmac on 17/7/25.
 */

public interface IActivityDetailView {
    void showActivityDetail(ActivityDetailResponse response);

    void showAllShiList(DongTaiListResponse response);

    void showCommentList(CommentListResponse response);

    void showReplayComment(boolean isSuccess);

    void onError(Throwable e);

    void collectReslut(boolean b);

    void cancelCollectReslut(boolean b);
}
