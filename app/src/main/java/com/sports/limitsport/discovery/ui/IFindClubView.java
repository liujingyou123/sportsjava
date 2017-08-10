package com.sports.limitsport.discovery.ui;

import com.sports.limitsport.model.ClubListResponse;

/**
 * Created by liuworkmac on 17/7/27.
 */

public interface IFindClubView {
    void showAllClubsList(ClubListResponse response);
    void showTodayClubsList(ClubListResponse response);

    void onError(Throwable e);
}
