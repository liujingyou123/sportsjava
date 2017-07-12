package com.sports.limitsport.discovery;

import android.support.v4.app.Fragment;

import com.sports.limitsport.util.SlidingTagPagerItem;

/**
 * Created by liuworkmac on 17/7/12.
 */

public class ClubDetailPageItem extends SlidingTagPagerItem {
    public ClubDetailPageItem(String mTitle, String mMsg) {
        super(mTitle, mMsg);
    }

    @Override
    public Fragment createFragment() {
        Fragment fragment = null;
        if ("0".equals(getMsg())) {
            fragment = new TabActivityFragment();
        } else if ("1".equals(getMsg())) {
            fragment = new TabActivityFragment();
        }
        return fragment;
    }
}
