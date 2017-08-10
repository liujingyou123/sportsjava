package com.sports.limitsport.notice.presenter;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.RecomendFriendsListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.notice.ui.IFindMoreFriendsView;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/10.
 */

public class FindMoreFriendsPresenter {
    private IFindMoreFriendsView mIFindMoreFriendsView;

    public FindMoreFriendsPresenter(IFindMoreFriendsView mIFindMoreFriendsView) {
        this.mIFindMoreFriendsView = mIFindMoreFriendsView;
    }

    public void getRecommendFriends(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).recommedFriends(hashMap), new NetSubscriber<RecomendFriendsListResponse>() {
            @Override
            public void response(RecomendFriendsListResponse response) {
                if (mIFindMoreFriendsView != null) {
                    mIFindMoreFriendsView.showFriendsList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIFindMoreFriendsView != null) {
                    mIFindMoreFriendsView.onError(e);
                }
            }
        });
    }

    /**
     * 0:添加 1:移除 2:取消关注
     **/
    public void foucesFans(final String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "0");
        hashMap.put("receiveUserId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).focusFans(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    if (mIFindMoreFriendsView != null) {
                        mIFindMoreFriendsView.onFocusReslult(true, userId);
                    }

                } else {
                    if (mIFindMoreFriendsView != null) {
                        mIFindMoreFriendsView.onFocusReslult(false, userId);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIFindMoreFriendsView != null) {
                    mIFindMoreFriendsView.onFocusReslult(false, userId);
                }
            }
        });
    }

    public void clear() {
        mIFindMoreFriendsView = null;
    }
}
