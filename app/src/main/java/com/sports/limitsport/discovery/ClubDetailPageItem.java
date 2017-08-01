package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sports.limitsport.util.SlidingTagPagerItem;

/**
 * Created by liuworkmac on 17/7/12.
 */

public class ClubDetailPageItem extends SlidingTagPagerItem {
    private String id;
    public ClubDetailPageItem(String mTitle, String mMsg, String id) {
        super(mTitle, mMsg);
        this.id = id;
    }

    @Override
    public Fragment createFragment() {
        Fragment fragment = null;
        if ("0".equals(getMsg())) {
            fragment = new TabActivityFragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", id);
            fragment.setArguments(bundle);
        } else if ("1".equals(getMsg())) {
            fragment = new TabHistoryFragment();
        }
        return fragment;
    }
}
