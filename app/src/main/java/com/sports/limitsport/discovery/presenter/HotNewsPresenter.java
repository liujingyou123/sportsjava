package com.sports.limitsport.discovery.presenter;

import com.sports.limitsport.model.AdvertiseInfoResponse;
import com.sports.limitsport.model.Club;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.discovery.ui.IHotNewsView;
import com.sports.limitsport.model.FineShowListResponse;
import com.sports.limitsport.model.MyCollectFineShowResponse;
import com.sports.limitsport.model.NewPersonListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.ToolsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/24.
 */

public class HotNewsPresenter {
    private IHotNewsView mIHotNewsView;

    public HotNewsPresenter(IHotNewsView mIHotNewsView) {
        this.mIHotNewsView = mIHotNewsView;
    }

    /**
     * 获取俱乐部
     */
    public void getClubsList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "2");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubList(hashMap), new NoneNetSubscriber<ClubListResponse>() {
            @Override
            public void response(ClubListResponse response) {
                if (mIHotNewsView != null) {
                    mIHotNewsView.showClubsList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
//        getTestData();
    }

    /**
     * 获取轮播图片
     */
    public void getAdvList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("adType", "2");
        hashMap.put("position", "1");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getAdvList(hashMap), new NoneNetSubscriber<AdvertiseInfoResponse>() {
            @Override
            public void response(AdvertiseInfoResponse response) {
                if (mIHotNewsView != null) {
                    mIHotNewsView.showAdvList(response);
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
     * 获取精彩秀推荐列表
     */
    public void getFineShow() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getFineShowList(hashMap), new NoneNetSubscriber<MyCollectFineShowResponse>() {
            @Override
            public void response(MyCollectFineShowResponse response) {
                if (mIHotNewsView != null) {
                    mIHotNewsView.showFineShowList(response);
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
     * 获取新人报到
     */
    public void getNewPersionList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", "1");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getNewPersonList(hashMap), new LoadingNetSubscriber<NewPersonListResponse>() {
            @Override
            public void response(NewPersonListResponse response) {
                if (mIHotNewsView != null) {
                    mIHotNewsView.showNewPersonList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIHotNewsView != null) {
                    mIHotNewsView.onError(e);
                }
            }
        });
//        getTestAdvData();
    }

    private void getTestAdvData() {
        AdvertiseInfoResponse response = new AdvertiseInfoResponse();
        List<AdvertiseInfoResponse.DataBean> datas = new ArrayList<>();
        for (int i=0;i< MyTestData.getData().size();i++) {
            AdvertiseInfoResponse.DataBean dataBean = new AdvertiseInfoResponse.DataBean();
            dataBean.setAdPicUrl(MyTestData.getData().get(i));
            datas.add(dataBean);
        }
        response.setData(datas);
        if (mIHotNewsView != null) {
            mIHotNewsView.showAdvList(response);
        }

    }

    private void getTestData() {
        ClubListResponse response = new ClubListResponse();
        ClubListResponse.DataBean dataBean = new ClubListResponse.DataBean();
        List<Club> clubs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Club club = new Club();
            clubs.add(club);
        }
        dataBean.setData(clubs);
        response.setData(dataBean);

        if (mIHotNewsView != null) {
            mIHotNewsView.showClubsList(response);
        }
    }

    public void clear() {
        mIHotNewsView = null;
    }
}
