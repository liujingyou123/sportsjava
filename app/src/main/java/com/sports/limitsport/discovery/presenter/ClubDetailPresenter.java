package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.discovery.ui.IClubDetailView;
import com.sports.limitsport.model.ClubDetailResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.ClubMembersResponse;
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

    /**
     * 俱乐部详情
     * @param id
     */
    public void getClubDetail(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clubId", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubDetail(hashMap), new NetSubscriber<ClubDetailResponse>() {
            @Override
            public void response(ClubDetailResponse response) {
                if (mIClubDetailView != null) {
                    mIClubDetailView.showClubDetail(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    public void getMembers(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("clubId", id);
        hashMap.put("ruleType", "0");
        hashMap.put("pageNumber","1");
        hashMap.put("pageSize","10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubMembers(hashMap), new NetSubscriber<ClubMembersResponse>() {
            @Override
            public void response(ClubMembersResponse response) {
                if (mIClubDetailView != null) {
                    mIClubDetailView.showClubMembers(response);
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

