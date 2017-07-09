package com.sports.limitsport.discovery;

import android.support.v4.app.Fragment;

import com.sports.limitsport.util.SlidingTagPagerItem;

/**
 * Created by liuworkmac on 17/6/29.
 */

class FindPageItem extends SlidingTagPagerItem {
    public FindPageItem(String mTitle, String mMsg) {
        super(mTitle, mMsg);
    }
    @Override
    public Fragment createFragment() {

        Fragment fragment = null;
        if ("0".equals(getMsg())) {
            fragment = new HotNewsFragment();
        } else if ("1".equals(getMsg())) {
            fragment = new NewNewsFragment();
        }
//
//        Bundle bundle = new Bundle();
//        bundle.putString("type", getMsg());
//        fragment.setArguments(bundle);
//

        return fragment;
    }
}
