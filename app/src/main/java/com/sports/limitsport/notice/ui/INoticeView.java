package com.sports.limitsport.notice.ui;

import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.DongTaiOrRecommendResponse;
import com.sports.limitsport.model.RecomendFriendsListResponse;

/**
 * Created by liuworkmac on 17/8/10.
 */

public interface INoticeView {
    void dongTaiList(DongTaiListResponse response);

    void onError(Throwable e);

    void onFocusReslult(boolean b, String id);

    void showPublishActivityComent(boolean b);

    void onPraiseResult(boolean b);

    void onCancelPraiseResult(boolean b);

    void showFriendsList(RecomendFriendsListResponse response);

    void showRecommendOrDongTai(DongTaiOrRecommendResponse response);
}
