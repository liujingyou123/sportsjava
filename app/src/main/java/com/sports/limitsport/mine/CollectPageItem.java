package com.sports.limitsport.mine;

import android.support.v4.app.Fragment;

import com.sports.limitsport.util.SlidingTagPagerItem;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class CollectPageItem extends SlidingTagPagerItem {
    public CollectPageItem(String mTitle, String mMsg) {
        super(mTitle, mMsg);
    }

    @Override
    public Fragment createFragment() {
        Fragment fragment = null;
        if ("0".equals(getMsg())) {
            fragment = new MyCollectActivityFragment();
        } else if ("1".equals(getMsg())) {
            fragment = new MyCollectFanShowFragment();
        } else if ("2".equals(getMsg())) {
            fragment = new MyCollectDongTaiFragment();
        }
        return fragment;
    }
}
