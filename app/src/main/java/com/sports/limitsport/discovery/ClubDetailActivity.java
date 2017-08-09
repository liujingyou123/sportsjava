package com.sports.limitsport.discovery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.adapter.ClubMemberAdapter;
import com.sports.limitsport.discovery.adapter.SlidingTabPagerAdapter;
import com.sports.limitsport.discovery.presenter.ClubDetailPresenter;
import com.sports.limitsport.discovery.ui.IClubDetailView;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.ClubDetailResponse;
import com.sports.limitsport.model.ClubMemberList;
import com.sports.limitsport.model.ClubMembersResponse;
import com.sports.limitsport.util.SlidingTagPagerItem;
import com.sports.limitsport.view.CreatPersonView;
import com.sports.limitsport.view.SlidingTabLayout;
import com.sports.limitsport.view.SpacesItemHDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 俱乐部详情页
 */

public class ClubDetailActivity extends BaseActivity implements IClubDetailView {
    @BindView(R.id.imv_cover)
    ImageView imvCover;
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
    @BindView(R.id.imv_club_logo)
    ImageView imvClubLogo;
    @BindView(R.id.imv_status)
    ImageView imvStatus;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.imv_report)
    ImageView imvReport;
    @BindView(R.id.imv_share)
    ImageView imvShare;
    @BindView(R.id.imv_publish)
    ImageView imvPublish;
    @BindView(R.id.rl_numbers)
    RecyclerView rlNumbers;
    private List<SlidingTagPagerItem> mTab = new ArrayList<>();
    private String id;//俱乐部ID
    private ClubDetailPresenter mPresenter;
    private ClubDetailResponse.DataBean dataBean;
    private List<ClubMemberList> data = new ArrayList<>();
    private ClubMemberAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubdetail);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ClubDetailPresenter(this);
        }
        mPresenter.getClubDetail(id);
        mPresenter.getMembers(id);
    }

    private void initView() {
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

        rlNumbers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter = new ClubMemberAdapter(data);
        adapter.bindToRecyclerView(rlNumbers);

        SpacesItemHDecoration decoration = new SpacesItemHDecoration(3);
        rlNumbers.addItemDecoration(decoration);
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

    @OnClick({R.id.imv_focus_house_back, R.id.imv_report, R.id.imv_share, R.id.tv_more, R.id.imv_club_logo, R.id.tv_sign_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this, "1", null);
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
                startActivity(intent);
                break;
            case R.id.tv_sign_num:
                Intent intent1 = new Intent(this, ClubMembersActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
                break;
        }
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
            } else {
                btnDone.setText("申请加入");
            }

            tvSignNum.setText(dataBean.getMemberNum() + "");
            if (dataBean.getMemberRule() == 1) { //创始人
//                imvPublish.setVisibility(View.VISIBLE);
                llBottom.setVisibility(View.GONE);
            } else {
//                imvPublish.setVisibility(View.GONE);
                llBottom.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void showClubMembers(ClubMembersResponse response) {
        if (adapter != null && response != null && response.getData() != null) {
            adapter.addData(response.getData().getData());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }

        mPresenter = null;
    }
}
