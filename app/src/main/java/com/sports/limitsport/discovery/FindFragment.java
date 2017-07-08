package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.discovery.adapter.SlidingTabPagerAdapter;
import com.sports.limitsport.util.SlidingTagPagerItem;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class FindFragment extends BaseFragment {
    @BindView(R.id.id_tab)
    SlidingTabLayout idTab;
    Unbinder unbinder;
    @BindView(R.id.id_view_pager)
    ViewPager idViewPager;
    private List<SlidingTagPagerItem> mTab = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTab.add(new FindPageItem("头条", "0"));
        mTab.add(new FindPageItem("动态", "1"));
        idViewPager.setAdapter(new SlidingTabPagerAdapter(this.getChildFragmentManager(), mTab));
        idTab.setViewPager(idViewPager, UnitUtil.dip2px(this.getContext(), 134));
        idViewPager.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.imv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_right:
                break;
            case R.id.id_tab:
                break;
        }
    }
}