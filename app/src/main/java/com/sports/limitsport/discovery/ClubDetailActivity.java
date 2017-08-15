/*
 * Copyright 2016 "Henry Tao <hi@henrytao.me>"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sports.limitsport.discovery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.adapter.ClubMemberAdapter;
import com.sports.limitsport.discovery.adapter.SlidingTabPagerAdapter;
import com.sports.limitsport.discovery.presenter.ClubDetailPresenter;
import com.sports.limitsport.discovery.ui.IClubDetailView;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.model.ClubDetail;
import com.sports.limitsport.model.ClubDetailResponse;
import com.sports.limitsport.model.ClubMemberList;
import com.sports.limitsport.model.ClubMembersResponse;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.SlidingTagPagerItem;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.CreatPersonView;
import com.sports.limitsport.view.SlidingTabLayout;
import com.sports.limitsport.view.SpacesItemHDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.henrytao.smoothappbarlayout.SmoothAppBarLayout;

public class ClubDetailActivity extends BaseActivity implements IClubDetailView {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    //    @BindView(R.id.tab_layout)
//    TabLayout tabLayout;
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.imv_club_logo)
    ImageView imvClubLogo;
    @BindView(R.id.imv_status)
    ImageView imvStatus;
    @BindView(R.id.tv_club_name)
    TextView tvClubName;
    @BindView(R.id.ll_members_two)
    LinearLayout llMembersTwo;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.ll_members)
    LinearLayout llMembers;
    @BindView(R.id.tv_sign_num)
    TextView tvSignNum;
    @BindView(R.id.ll_players)
    LinearLayout llPlayers;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.imv_report)
    ImageView imvReport;
    @BindView(R.id.imv_share)
    ImageView imvShare;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.id_tab)
    SlidingTabLayout idTab;
    @BindView(R.id.btn_done)
    TextView btnDone;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.rl_numbers)
    RecyclerView rlNumbers;
    @BindView(R.id.smooth_app_bar_layout)
    SmoothAppBarLayout smoothAppBarLayout;

//    private ViewPagerAdapter mViewPagerAdapter;
    private String id;//俱乐部ID
    private List<SlidingTagPagerItem> mTab = new ArrayList<>();
    private List<ClubMemberList> data = new ArrayList<>();
    private ClubMemberAdapter adapter;
    private ClubDetailPresenter mPresenter;
    private ClubDetail dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smooth_view_pager_parallax_exit_util_collapsed);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        getData();
//        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        mViewPagerAdapter.onRestoreInstanceState(savedInstanceState);
//        TabActivityFragment fragment = new TabActivityFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("id", id);
//        fragment.setArguments(bundle);
//        mViewPagerAdapter
//                .addFragment("Cat", fragment);
//
//        TabHistoryFragment fragmenth = new TabHistoryFragment();
//        Bundle bundleh = new Bundle();
//        bundleh.putString("id", id);
//        fragmenth.setArguments(bundleh);
//
//
//        mViewPagerAdapter
//                .addFragment("Dog", fragmenth);
//
//        viewPager.setAdapter(mViewPagerAdapter);
//        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
//
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    private void initView() {
        setupViewPager();

        smoothAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset < 0) {
                    rlTop.setBackgroundResource(R.color.bg_title);
                } else {
                    rlTop.setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });


        rlNumbers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter = new ClubMemberAdapter(data);
        adapter.bindToRecyclerView(rlNumbers);

        SpacesItemHDecoration decoration = new SpacesItemHDecoration(3);
        rlNumbers.addItemDecoration(decoration);
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ClubDetailPresenter(this);
        }
        mPresenter.getClubDetail(id);
        mPresenter.getMembers(id);
    }

    /**
     * 设置item
     */
    private void setupViewPager() {
        mTab.add(new ClubDetailPageItem("在线活动", "0", id));
        mTab.add(new ClubDetailPageItem("精彩回顾", "1", id));
        viewPager.setAdapter(new SlidingTabPagerAdapter(this.getSupportFragmentManager(), mTab));
        idTab.setViewPager(viewPager);
//        viewPager.setCurrentItem(0);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share, R.id.tv_more, R.id.imv_club_logo, R.id.tv_sign_num, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this, "1", id);
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
            case R.id.imv_club_logo:
                Intent intent = new Intent(this, ClubBaseInfoActivity.class);
                intent.putExtra("data", (Serializable) dataBean);
                startActivity(intent);
                break;
            case R.id.tv_sign_num:
                Intent intent1 = new Intent(this, ClubMembersActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
                break;
            case R.id.btn_done:
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent2 = new Intent(this, LoginActivity.class);
                    intent2.putExtra("type", "2");
                    startActivity(intent2);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent3 = new Intent(this, IdentifyMainActivity.class);
                    intent3.putExtra("type", "2");
                    startActivity(intent3);
                } else if (dataBean != null && dataBean.getJoinClubFlag() == 0) {
                    if (mPresenter != null) {
                        mPresenter.joinClub(id);
                    }
                } else if (dataBean != null && dataBean.getJoinClubFlag() == 1) {
                    exitDialog();
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        mViewPagerAdapter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showClubDetail(ClubDetailResponse response) {
        if (response != null) {
            this.dataBean = response.getData();
            Batman.getInstance().fromNet(dataBean.getClubImgUrl(), imvCover);
            Batman.getInstance().getImageWithCircle(dataBean.getLogoUrl(), imvClubLogo, R.mipmap.icon_club_defaul, R.mipmap.icon_club_defaul);
            tvClubName.setText(dataBean.getClubName());

            if (dataBean.getManagerList() != null) {
                if (dataBean.getManagerList().size() > 3) {
                    tvMore.setVisibility(View.VISIBLE);
                } else {
                    tvMore.setVisibility(View.GONE);
                }

                for (int i = 0; i < dataBean.getManagerList().size(); i++) {
                    CreatPersonView creatPersonView = new CreatPersonView(this);
                    creatPersonView.setData(dataBean.getManagerList().get(i));

                    if (i < 3) {
                        llMembers.addView(creatPersonView, llMembers.getChildCount() - 1);
                    } else {
                        llMembersTwo.addView(creatPersonView);
                    }
                }
            }

            if (dataBean.getAuthEntity() == 2) {
                imvStatus.setVisibility(View.VISIBLE);
            } else {
                imvStatus.setVisibility(View.GONE);
            }

            if (dataBean.getJoinClubFlag() == 1) { //1:是 0:否
                btnDone.setText("退出俱乐部");
//                if (dataBean.getMemberRule() == 1) { //创始人
//                    llBottom.setVisibility(View.GONE);
//                } else {
//                    llBottom.setVisibility(View.VISIBLE);
//                }
            } else {
                llBottom.setVisibility(View.VISIBLE);
                btnDone.setText("申请加入");
            }


        }
    }

    @Override
    public void showClubMembers(ClubMembersResponse response) {
        if (adapter != null && response != null && response.getData() != null) {
            adapter.addData(response.getData().getData());
            tvSignNum.setText(response.getData().getTotalSize() + "");
        }
    }

    @Override
    public void joinClubResult(boolean isSuccess) {
        if (isSuccess) {
            dataBean.setJoinClubFlag(1);
            btnDone.setText("退出俱乐部");
        } else {
            ToastUtil.showFalseToast(this, "申请失败");
        }
    }

    @Override
    public void quiteClubResult(boolean success) {
        if (success) {
            dataBean.setJoinClubFlag(0);
            btnDone.setText("申请加入");
        } else {
            ToastUtil.showFalseToast(this, "申请失败");
        }
    }

    private void exitDialog() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setMessage("您确定要退出俱乐部吗？");
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                if (mPresenter != null) {
                    mPresenter.quiteClub(id);
                }
            }
        });
        dialog.show();
    }
}
