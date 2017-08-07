package com.sports.limitsport.discovery.ui;

import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.UserInfoResponse;

/**
 * Created by liuworkmac on 17/8/2.
 */

public interface IPersonInfoView {
    void showUserInfo(UserInfoResponse response);

    void showActivityList(ActivityResponse response);

    void showClubsList(ClubListResponse response);

    void onError(Throwable e, String club);

    void showDongTaiList(DongTaiListResponse response);

    void onPraiseResult(boolean b);

    void onCancelPraiseResult(boolean b);

    void showPublishActivityComent(boolean b);
}
