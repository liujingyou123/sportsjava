package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.discovery.adapter.SlidingTabPagerAdapter;
import com.sports.limitsport.util.SlidingTagPagerItem;
import com.sports.limitsport.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/7.
 * 我的收藏
 */

public class MyCollectionActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.id_tab)
    SlidingTabLayout idTab;
    @BindView(R.id.id_view_pager)
    ViewPager idViewPager;
    private List<SlidingTagPagerItem> mTab = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    private void initView() {
        tvFocusHouse.setText("我的收藏");
        mTab.add(new CollectPageItem("活动", "0"));
        mTab.add(new CollectPageItem("精彩秀", "1"));
        mTab.add(new CollectPageItem("动态", "2"));
        idViewPager.setAdapter(new SlidingTabPagerAdapter(this.getSupportFragmentManager(), mTab));
        idTab.setViewPager(idViewPager);
        idViewPager.setCurrentItem(0);
    }
}
