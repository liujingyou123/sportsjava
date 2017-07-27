package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.discovery.ui.IFindClubView;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/27.
 */

public class FindClubPresenter {
    IFindClubView mIFindClubView;

    public FindClubPresenter(IFindClubView mIFindClubView) {
        this.mIFindClubView = mIFindClubView;
    }

    /**
     * 获取全部俱乐部
     */
    public void getAllClubsList(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "1");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubList(hashMap), new NetSubscriber<ClubListResponse>() {
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

    /**
     * 获取推荐俱乐部
     */
    public void getTodayClubsList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "2");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubList(hashMap), new NetSubscriber<ClubListResponse>() {
            @Override
            public void response(ClubListResponse response) {
                if (mIFindClubView != null) {
                    mIFindClubView.showTodayClubsList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
