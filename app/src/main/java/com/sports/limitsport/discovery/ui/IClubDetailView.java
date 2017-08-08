package com.sports.limitsport.discovery.ui;

import com.sports.limitsport.model.ClubDetailResponse;
import com.sports.limitsport.model.ClubMembersResponse;

/**
 * Created by liuworkmac on 17/8/7.
 */

public interface IClubDetailView {
    void showClubDetail(ClubDetailResponse response);

    void showClubMembers(ClubMembersResponse response);
}
