package com.sports.limitsport.activity.ui;

import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiDetailResponse;

/**
 * Created by liuworkmac on 17/8/3.
 */

public interface IDongTaiDetailView {

    void showDetail(DongTaiDetailResponse response);

    void onError(Throwable e);

    void onFocusReslult(boolean b);

    void onPraiseResult(boolean b, String id, String type);

    void onCancelPraiseResult(boolean b, String id, String type);

    void showReplayComment(boolean b);

    void showPublishActivityComent(boolean b);

    void showCommentList(CommentListResponse response);
}
