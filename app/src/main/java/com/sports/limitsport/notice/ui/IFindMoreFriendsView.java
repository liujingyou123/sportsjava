package com.sports.limitsport.notice.ui;

import com.sports.limitsport.model.RecomendFriendsListResponse;

/**
 * Created by liuworkmac on 17/8/10.
 */

public interface IFindMoreFriendsView {
    void showFriendsList(RecomendFriendsListResponse response);

    void onError(Throwable e);

    void onFocusReslult(boolean b, String id);
}
