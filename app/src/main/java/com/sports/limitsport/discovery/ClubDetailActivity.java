package com.sports.limitsport.discovery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.adapter.SlidingTabPagerAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.SlidingTagPagerItem;
import com.sports.limitsport.util.StatusBarUtil;
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
    @BindView(R.id.ll_members_two)
    LinearLayout llMembersTwo;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
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

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < 0) {
                    rlTop.setBackgroundResource(R.color.bg_title);
                } else {
                    rlTop.setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });
    }

    /**
     * 设置item
     */
    private void setupViewPager() {
        mTab.add(new ClubDetailPageItem("在线活动", "0"));
        mTab.add(new ClubDetailPageItem("精彩回顾", "1"));
        viewPager.setAdapter(new SlidingTabPagerAdapter(this.getSupportFragmentManager(), mTab));
        idTab.setViewPager(viewPager);
//        viewPager.setCurrentItem(0);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share, R.id.tv_more, R.id.tv_club_tip})
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
            case R.id.tv_more:
                if (tvMore.isSelected()) {
                    tvMore.setText("更多管理着");
                    tvMore.setSelected(false);
                    llMembersTwo.setVisibility(View.GONE);
                } else {
                    tvMore.setText("收起");
                    tvMore.setSelected(true);
                    llMembersTwo.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_club_tip:
                Intent intent = new Intent(this, ClubBaseInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
