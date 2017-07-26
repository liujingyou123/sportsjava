package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.IActivityDetailView;
import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/25.
 */

public class ActivityDetailPresenter {
    private IActivityDetailView mIActivityDetailView;

    public ActivityDetailPresenter(IActivityDetailView mIActivityDetailView) {
        this.mIActivityDetailView = mIActivityDetailView;
    }

    public void getActivityDetail(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getActivityDetail(hashMap), new NetSubscriber<ActivityDetailResponse>() {
            @Override
            public void response(ActivityDetailResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.showActivityDetail(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.onError(e);
                }
            }
        });
    }

    /**
     * 大家都在晒
     *
     * @param id
     */
    public void getAllShai(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("activityId", id);
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTaiList(hashMap), new NoneNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.showAllShiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.onError(e);
                }
            }
        });
    }

    /**
     * 评论列表
     * @param id
     */
    public void getCommentList(String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", id);
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getCommentList(hashMap), new NetSubscriber<CommentListResponse>() {
            @Override
            public void response(CommentListResponse response) {
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.showCommentList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIActivityDetailView != null) {
                    mIActivityDetailView.onError(e);
                }
            }
        });
    }

    public void clear() {
        mIActivityDetailView = null;
    }
}
