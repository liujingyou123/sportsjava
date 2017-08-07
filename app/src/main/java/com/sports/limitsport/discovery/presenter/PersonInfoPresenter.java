package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.discovery.ui.IPersonInfoView;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.model.MyCollectActivityResponse;
import com.sports.limitsport.model.UserInfo;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/2.
 */

public class PersonInfoPresenter {
    private IPersonInfoView mIPersonInfoView;

    public PersonInfoPresenter(IPersonInfoView mIPersonInfoView) {
        this.mIPersonInfoView = mIPersonInfoView;
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getUserInfo(hashMap), new NetSubscriber<UserInfoResponse>() {
            @Override
            public void response(UserInfoResponse response) {
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showUserInfo(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    /**
     * 参加的活动
     *
     * @param userId
     */
    public void getActivityList(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", 1 + "");
        hashMap.put("pageSize", "1");
        hashMap.put("userId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getJoinActivityList(hashMap), new NetSubscriber<ActivityResponse>() {
            @Override
            public void response(ActivityResponse response) {
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showActivityList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.onError(e, "activity");
                }
            }
        });
    }

    /**
     * 参加的俱乐部
     */
    public void getAllClubsList(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "2");
        hashMap.put("type", "4");
        hashMap.put("userId", userId);

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubList(hashMap), new NetSubscriber<ClubListResponse>() {
            @Override
            public void response(ClubListResponse response) {
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.showClubsList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIPersonInfoView != null) {
                    mIPersonInfoView.onError(e, "club");
                }

            }
        });
    }

    public void clear() {
        mIPersonInfoView = null;
    }
}
