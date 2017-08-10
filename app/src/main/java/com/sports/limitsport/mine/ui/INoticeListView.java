package com.sports.limitsport.mine.ui;

import com.sports.limitsport.model.NoticeListResponse;

/**
 * Created by liuworkmac on 17/7/31.
 */

public interface INoticeListView {
    void showNoticeList(NoticeListResponse response);

    void onError(Throwable e);
}
