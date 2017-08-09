package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.discovery.ui.INewNewsView;
import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.ToolsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/24.
 * 发现 动态
 */

public class NewNewsPresenter {
    private INewNewsView mINewNewsView;

    public NewNewsPresenter(INewNewsView mINewNewsView) {
        this.mINewNewsView = mINewNewsView;
    }

    /**
     * 获取轮播图片
     */
    public void getAdvList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("adType", "2");
        hashMap.put("position", "2");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getAdvList(hashMap), new NoneNetSubscriber<AdvertiseInfoResponse>() {
            @Override
            public void response(AdvertiseInfoResponse response) {
                if (mINewNewsView != null) {
                    mINewNewsView.showAdvList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
//        getTestAdvData();
    }

    /**
     * 动态列表
     *
     * @param pageNumber
     */
    public void getDongTaiList(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "0");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getDongTaiList(hashMap), new NoneNetSubscriber<DongTaiListResponse>() {
            @Override
            public void response(DongTaiListResponse response) {
                if (mINewNewsView != null) {
                    mINewNewsView.showAllDongTai(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINewNewsView != null) {
                    mINewNewsView.onError(e);
                }
            }
        });
    }

    /**
     * 点赞
     *
     * @param articleId
     * @param type      1:精彩秀点赞 2:动态点赞 3:评论点赞
     */
    public void praise(final String articleId, final String type) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", type);

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).praise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mINewNewsView != null && response.isSuccess()) {
                    mINewNewsView.onPraiseResult(true, articleId, type);
                } else {
                    if (mINewNewsView != null) {
                        mINewNewsView.onPraiseResult(false, articleId, type);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINewNewsView != null) {
                    mINewNewsView.onPraiseResult(false, articleId, type);
                }
            }
        });
    }

    /**
     * 取消点赞
     *
     * @param articleId
     */
    public void cancelPraise(final String articleId, final String type) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("articleId", articleId);
        hashMap.put("praiseType", type);

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).cancelPraise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mINewNewsView != null && response.isSuccess()) {
                    mINewNewsView.onCancelPraiseResult(true, articleId, type);
                } else {
                    if (mINewNewsView != null) {
                        mINewNewsView.onCancelPraiseResult(false, articleId, type);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mINewNewsView != null) {
                    mINewNewsView.onCancelPraiseResult(false, articleId, type);
                }
            }
        });
    }

    private void getTestAdvData() {
        AdvertiseInfoResponse response = new AdvertiseInfoResponse();
        List<AdvertiseInfoResponse.DataBean> datas = new ArrayList<>();
        for (int i = 0; i < MyTestData.getData().size(); i++) {
            AdvertiseInfoResponse.DataBean dataBean = new AdvertiseInfoResponse.DataBean();
            dataBean.setAdPicUrl(MyTestData.getData().get(i));
            datas.add(dataBean);
        }
        response.setData(datas);
        if (mINewNewsView != null) {
            mINewNewsView.showAdvList(response);
        }

    }

    public void clear() {
        mINewNewsView = null;
    }
}
