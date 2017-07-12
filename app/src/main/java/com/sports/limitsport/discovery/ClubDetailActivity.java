package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.adapter.SlidingTabPagerAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.SlidingTagPagerItem;
import com.sports.limitsport.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 俱乐部详情页
 */

public class ClubDetailActivity extends BaseActivity {
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.tv_club_tip)
    TextView tvClubTip;
    @BindView(R.id.tv_club_name)
    TextView tvClubName;
    @BindView(R.id.ll_members)
    LinearLayout llMembers;
    @BindView(R.id.tv_sign_num)
    TextView tvSignNum;
    @BindView(R.id.ll_players)
    LinearLayout llPlayers;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.id_tab)
    SlidingTabLayout idTab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;
    @BindView(R.id.btn_done)
    TextView btnDone;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private List<SlidingTagPagerItem> mTab = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubdetail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imvCover);

        collapsingToolbar.setTitle(" ");
        setupViewPager();
    }

    /**
     * 设置item
     */
    private void setupViewPager() {
//        ClubsDetailAdapter adapter = new ClubsDetailAdapter(getSupportFragmentManager());
//        adapter.addFragment(new TabActivityFragment(), "在线活动");
//        adapter.addFragment(new TabActivityFragment(), "精彩回顾");
//        viewPager.setAdapter(adapter);
//        setIndicator(this, tabs);

        mTab.add(new ClubDetailPageItem("在线活动", "0"));
        mTab.add(new ClubDetailPageItem("精彩回顾", "1"));
        viewPager.setAdapter(new SlidingTabPagerAdapter(this.getSupportFragmentManager(), mTab));
        idTab.setViewPager(viewPager);
//        viewPager.setCurrentItem(0);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this);
                dialog.show();
                break;
            case R.id.imv_share:
                break;
        }
    }
}
