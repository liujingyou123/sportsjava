package com.sports.limitsport.mine.ui;

import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.model.NewNoticeResponse;
import com.sports.limitsport.model.UserInfoResponse;

/**
 * Created by liuworkmac on 17/7/31.
 */

public interface IMineView {
    void showUserInfo(UserInfoResponse response);

    void showNewNotice(NewNoticeResponse response);

    void showDongTaiList(DongTaiListResponse response);

    void onError(Throwable e);

    void showPublishActivityComent(boolean b);

    void onPraiseResult(boolean b);

    void onCancelPraiseResult(boolean b);
}
