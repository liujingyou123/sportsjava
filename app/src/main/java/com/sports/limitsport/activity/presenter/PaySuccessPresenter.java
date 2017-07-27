package com.sports.limitsport.activity.presenter;

import com.sports.limitsport.activity.ui.IPaySuccessView;

/**
 * Created by liuworkmac on 17/7/27.
 */

public class PaySuccessPresenter {
    IPaySuccessView mIPaySuccessView;

    public PaySuccessPresenter(IPaySuccessView mIPaySuccessView) {
        this.mIPaySuccessView = mIPaySuccessView;
    }

    public void getPayFinish(String id, String orderNo) {

    }

    public void clear() {
        mIPaySuccessView = null;
    }
}
