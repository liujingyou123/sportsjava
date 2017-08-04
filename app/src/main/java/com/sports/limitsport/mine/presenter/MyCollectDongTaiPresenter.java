package com.sports.limitsport.mine.presenter;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.mine.ui.IMyCollectDongTaiView;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/4.
 */

public class MyCollectDongTaiPresenter {
    IMyCollectDongTaiView mIMyCollectDongTaiView;

    public MyCollectDongTaiPresenter(IMyCollectDongTaiView mIMyCollectDongTaiView) {
        this.mIMyCollectDongTaiView = mIMyCollectDongTaiView;
    }

    /**
     * 大家都在晒
     * */
    public void getAllShai(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getMyCollectDongTaiList(hashMap), new NoneNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mIMyCollectDongTaiView != null) {
                    mIMyCollectDongTaiView.showDongTaiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMyCollectDongTaiView != null) {
                    mIMyCollectDongTaiView.onError(e);
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
                    if (mIMyCollectDongTaiView != null) {
                        mIMyCollectDongTaiView.onFocusReslult(true, id);
                    }

                } else {
                    if (mIMyCollectDongTaiView != null) {
                        mIMyCollectDongTaiView.onFocusReslult(false, id);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMyCollectDongTaiView != null) {
                    mIMyCollectDongTaiView.onFocusReslult(false, id);
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
                if (mIMyCollectDongTaiView != null && response.isSuccess()) {
                    mIMyCollectDongTaiView.showPublishActivityComent(true);
                } else {
                    if (mIMyCollectDongTaiView != null) {
                        mIMyCollectDongTaiView.showPublishActivityComent(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMyCollectDongTaiView != null) {
                    mIMyCollectDongTaiView.showPublishActivityComent(false);
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
                if (mIMyCollectDongTaiView != null && response.isSuccess()) {
                    mIMyCollectDongTaiView.onPraiseResult(true);
                } else {
                    if (mIMyCollectDongTaiView != null) {
                        mIMyCollectDongTaiView.onPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMyCollectDongTaiView != null) {
                    mIMyCollectDongTaiView.onPraiseResult(false);
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
                if (mIMyCollectDongTaiView != null && response.isSuccess()) {
                    mIMyCollectDongTaiView.onCancelPraiseResult(true);
                } else {
                    if (mIMyCollectDongTaiView != null) {
                        mIMyCollectDongTaiView.onCancelPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMyCollectDongTaiView != null) {
                    mIMyCollectDongTaiView.onCancelPraiseResult(false);
                }
            }
        });
    }

    public void clear() {
        mIMyCollectDongTaiView = null;
    }
}
