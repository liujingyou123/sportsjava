package com.sports.limitsport.discovery.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sports.limitsport.util.SlidingTagPagerItem;
import com.sports.limitsport.view.SlidingTabLayout;

import java.util.List;

/**
 * Created by liuworkmac on 16/11/16.
 */
public class SlidingTabPagerAdapter extends FragmentPagerAdapter implements SlidingTabLayout.TabItemName {
    private List<SlidingTagPagerItem> mTab;

    public SlidingTabPagerAdapter(FragmentManager fm, List<SlidingTagPagerItem> tabs) {
        super(fm);
        this.mTab = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (mTab != null && mTab.size() > 0) {
            fragment = mTab.get(position).createFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mTab != null) {
            ret = mTab.size();
        }
        return ret;
    }

    @Override
    public String getTabName(int position) {
        String title = null;
        if (mTab != null && mTab.size() > 0) {
            title = mTab.get(position).getTitle();
        }
        return title;
    }
}
