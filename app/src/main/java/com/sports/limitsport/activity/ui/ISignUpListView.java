package com.sports.limitsport.activity.ui;

import com.sports.limitsport.model.SignUpListResponse;

/**
 * Created by liuworkmac on 17/7/27.
 */

public interface ISignUpListView {
    void showSignUpList(SignUpListResponse response);

    void onError(Throwable e);
}
