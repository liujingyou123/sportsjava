package com.sports.limitsport.discovery.ui;

import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.FineShowListResponse;
import com.sports.limitsport.model.MyCollectFineShowResponse;
import com.sports.limitsport.model.NewPersonListResponse;

/**
 * Created by liuworkmac on 17/7/24.
 */

public interface IHotNewsView {
    void showClubsList(ClubListResponse response);

    void showAdvList(AdvertiseInfoResponse response);

    void showFineShowList(MyCollectFineShowResponse response);

    void showNewPersonList(NewPersonListResponse response);
}
