package com.sports.limitsport.activity.presenter;

import android.view.View;

import com.sports.limitsport.activity.ui.IAllShaiView;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.EventBusUserModel;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/26.
 */

public class AllShaiPresenter {
    private IAllShaiView mIAllShaiView;

    public AllShaiPresenter(IAllShaiView mIAllShaiView) {
        this.mIAllShaiView = mIAllShaiView;
    }

    /**
     * 大家都在晒
     *
     * @param id
     */
    public void getAllShai(String id, int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("activityId", id);
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "0");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTaiList(hashMap), new NoneNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mIAllShaiView != null) {
                    mIAllShaiView.showAllShiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIAllShaiView != null) {
                    mIAllShaiView.onError(e);
                }
            }
        });
    }

    /**
     * 0:添加 1:移除 2:取消关注
     **/
    public void foucesFans(String userId, final String id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "0");
        hashMap.put("receiveUserId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).focusFans(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    if (mIAllShaiView != null) {
                        mIAllShaiView.onFocusReslult(true, id);
                    }

                } else {
                    if (mIAllShaiView != null) {
                        mIAllShaiView.onFocusReslult(false, id);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIAllShaiView != null) {
                    mIAllShaiView.onFocusReslult(false, id);
                }
            }
        });
    }

    /**
     * 发布动态评论
     *
     * @param articleId 评论对象ID
     * @param content   评论内容
     */
    public void publishActivityComment(String articleId, String content) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("content", content);
        hashMap.put("commentType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).publistComments(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIAllShaiView != null && response.isSuccess()) {
                    mIAllShaiView.showPublishActivityComent(true);
                } else {
                    if (mIAllShaiView != null) {
                        mIAllShaiView.showPublishActivityComent(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIAllShaiView != null) {
                    mIAllShaiView.showPublishActivityComent(false);
                }
            }
        });
    }

    /**
     * 点赞
     *
     * @param articleId
     */
    public void praise(String articleId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).praise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIAllShaiView != null && response.isSuccess()) {
                    mIAllShaiView.onPraiseResult(true);
                } else {
                    if (mIAllShaiView != null) {
                        mIAllShaiView.onPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIAllShaiView != null) {
                    mIAllShaiView.onPraiseResult(false);
                }
            }
        });
    }

    /**
     * 取消点赞
     *
     * @param articleId
     */
    public void cancelPraise(String articleId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).cancelPraise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIAllShaiView != null && response.isSuccess()) {
                    mIAllShaiView.onCancelPraiseResult(true);
                } else {
                    if (mIAllShaiView != null) {
                        mIAllShaiView.onCancelPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIAllShaiView != null) {
                    mIAllShaiView.onCancelPraiseResult(false);
                }
            }
        });
    }

    public void clear() {
        mIAllShaiView = null;
    }


}
