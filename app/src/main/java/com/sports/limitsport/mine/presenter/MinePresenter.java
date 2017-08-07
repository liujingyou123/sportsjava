package com.sports.limitsport.mine.presenter;

import android.view.View;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.mine.ui.IMineView;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.EventBusNewNotice;
import com.sports.limitsport.model.NewNoticeResponse;
import com.sports.limitsport.model.UserInfoResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class MinePresenter {
    IMineView mIMineView;

    public MinePresenter(IMineView mIMineView) {
        this.mIMineView = mIMineView;
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", "");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getUserInfo(hashMap), new NetSubscriber<UserInfoResponse>() {
            @Override
            public void response(UserInfoResponse response) {
                if (mIMineView != null) {
                    mIMineView.showUserInfo(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    /**
     * 获取新通知
     */
    public void getNewNotice() {
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getNewNotice(), new LoadingNetSubscriber<NewNoticeResponse>() {
            @Override
            public void response(NewNoticeResponse response) {

                if (mIMineView != null) {
                    mIMineView.showNewNotice(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    public void getDongTaiList(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "1");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTaiList(hashMap), new NoneNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mIMineView != null) {
                    mIMineView.showDongTaiList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMineView != null) {
                    mIMineView.onError(e);
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
                if (mIMineView != null && response.isSuccess()) {
                    mIMineView.showPublishActivityComent(true);
                } else {
                    if (mIMineView != null) {
                        mIMineView.showPublishActivityComent(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMineView != null) {
                    mIMineView.showPublishActivityComent(false);
                }
            }
        });
    }

    /**
     * 点赞
     * @param articleId
     */
    public void praise(String articleId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).praise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIMineView != null && response.isSuccess()) {
                    mIMineView.onPraiseResult(true);
                } else {
                    if (mIMineView != null) {
                        mIMineView.onPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMineView != null) {
                    mIMineView.onPraiseResult(false);
                }
            }
        });
    }

    /**
     * 取消点赞
     * @param articleId
     */
    public void cancelPraise(String articleId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", "2");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).cancelPraise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIMineView != null && response.isSuccess()) {
                    mIMineView.onCancelPraiseResult(true);
                } else {
                    if (mIMineView != null) {
                        mIMineView.onCancelPraiseResult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMineView != null) {
                    mIMineView.onCancelPraiseResult(false);
                }
            }
        });
    }

    public void clear() {
        mIMineView = null;
    }
}
