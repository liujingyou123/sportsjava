package com.sports.limitsport.discovery.ui;

import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.FineShowDetailResponse;
import com.sports.limitsport.model.PraiseListResponse;

/**
 * Created by liuworkmac on 17/8/4.
 */

public interface IFineShowDetailView {

    void showCommentList(CommentListResponse response);

    void onError(Throwable e);

    void showDetail(FineShowDetailResponse response);

    void onFocusReslult(boolean b);

    void onPraiseResult(boolean b, String articleId, String type);

    void onCancelPraiseResult(boolean b, String articleId, String type);

    void showPublishActivityComent(boolean b);

    void showReplayComment(boolean b);

    void collectReslut(boolean b);

    void cancelCollectReslut(boolean b);

    void showPraiseList(PraiseListResponse response);

    void onDetailError(Throwable e);
}
