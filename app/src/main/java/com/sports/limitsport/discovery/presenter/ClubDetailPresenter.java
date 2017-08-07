package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.discovery.ui.IClubDetailView;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/7.
 */

public class ClubDetailPresenter {
    private IClubDetailView mIClubDetailView;

    public ClubDetailPresenter(IClubDetailView mIClubDetailView) {
        this.mIClubDetailView = mIClubDetailView;
    }

    public void getClubDetail(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clubId", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubDetail(hashMap), new NetSubscriber<ClubListResponse>() {
            @Override
            public void response(ClubListResponse response) {
                if (mIFindClubView != null) {
                    mIFindClubView.showAllClubsList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
    public void clear() {
        mIClubDetailView = null;
    }
}

