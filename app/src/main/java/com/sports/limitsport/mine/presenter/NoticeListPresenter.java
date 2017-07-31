package com.sports.limitsport.mine.presenter;

import com.sports.limitsport.mine.ui.INoticeListView;
import com.sports.limitsport.model.NoticeListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class NoticeListPresenter {
    private INoticeListView mINoticeListView;

    public NoticeListPresenter(INoticeListView mINoticeListView) {
        this.mINoticeListView = mINoticeListView;
    }

    public void getNoticeList(String type, int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", type);
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getNoticeList(hashMap), new LoadingNetSubscriber<NoticeListResponse>() {
            @Override
            public void response(NoticeListResponse response) {
                if (mINoticeListView != null) {
                    mINoticeListView.showNoticeList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }

    public void clear() {
        mINoticeListView = null;
    }
}
