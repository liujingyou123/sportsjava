package com.sports.limitsport.util;

import android.support.v4.app.Fragment;

/**
 * Created by liuworkmac on 16/11/16.
 * 滑动条 实体类
 */
public abstract class SlidingTagPagerItem {
    /*item 的信息*/
    private String mMsg;
    /*item的 title*/
    private String mTitle;

    public SlidingTagPagerItem(String mTitle, String mMsg) {
        this.mMsg = mMsg;
        this.mTitle = mTitle;
    }

    public abstract Fragment createFragment();

    public String getTitle() {
        return mTitle;
    }

    public String getMsg() {
        return mMsg;
    }

}
