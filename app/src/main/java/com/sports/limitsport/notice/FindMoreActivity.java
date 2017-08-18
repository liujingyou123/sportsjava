package com.sports.limitsport.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.AllShaiActivity;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.model.RecomendFriendsListResponse;
import com.sports.limitsport.model.RecommendFriendsList;
import com.sports.limitsport.notice.adapter.MyNoticeRecommendAdapter;
import com.sports.limitsport.notice.presenter.FindMoreFriendsPresenter;
import com.sports.limitsport.notice.ui.IFindMoreFriendsView;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
import com.sports.limitsport.view.NoticeViewHeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/13.
 * 关注好友，发现更多
 */

public class FindMoreActivity extends BaseActivity implements IFindMoreFriendsView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv_recommend)
    RecyclerView rlvRecommend;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private MyNoticeRecommendAdapter adapterRecommend;
    private List<RecommendFriendsList> data = new ArrayList<>();
    private FindMoreFriendsPresenter mPresenter;
    private int pageNumber = 1;
    private int totalSize;
    private NoticeViewHeaderView headerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_more);
        ButterKnife.bind(this);
        initView();
        getIntentData();
    }

    private void getIntentData() {
        if (mPresenter == null) {
            mPresenter = new FindMoreFriendsPresenter(this);
        }
        mPresenter.getRecommendFriends(pageNumber);
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    private void initView() {
        tvFocusHouse.setText("关注好友");
        headerView = new NoticeViewHeaderView(this);
        View headTip = LayoutInflater.from(this).inflate(R.layout.view_notice_empty_tip, null);
        headerView.setCloseClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterRecommend.removeHeaderView(headerView);
            }
        });
        headerView.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        headerView.setMoreText("邀请好友");
        headerView.setTip("发现更多的好友，去微信寻找小伙伴吧。");
        rlvRecommend.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterRecommend = new MyNoticeRecommendAdapter(data);
        adapterRecommend.bindToRecyclerView(rlvRecommend);
        adapterRecommend.addHeaderView(headerView);
        adapterRecommend.addHeaderView(headTip);
        adapterRecommend.setLoadMoreView(new CustomLoadMoreNoEndView());
        adapterRecommend.disableLoadMoreIfNotFullPage();
        adapterRecommend.setEnableLoadMore(true);
        adapterRecommend.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent = new Intent(FindMoreActivity.this, LoginActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent = new Intent(FindMoreActivity.this, IdentifyMainActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                } else {
                    RecommendFriendsList recommendFriendsList = (RecommendFriendsList) adapter.getItem(position);
                    if (recommendFriendsList != null) {
                        if (0 == recommendFriendsList.getAttentionFlag()) {
                            if (mPresenter != null) {
                                mPresenter.foucesFans(recommendFriendsList.getId() + "");
                            }
                        } else {
                            Intent intent = new Intent(FindMoreActivity.this, PersonInfoActivity.class);
                            intent.putExtra("userId", recommendFriendsList.getId() + "");
                            startActivity(intent);
                        }
                    }
                }

            }
        });
        adapterRecommend.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlvRecommend);
        rlAll.setEnableLoadMore(false);

        rlAll.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                XLog.e("onRefreshing");
                refresh();
            }
        });
    }

    private void loadMore() {
        if (mPresenter != null) {
            pageNumber++;
            mPresenter.getRecommendFriends(pageNumber);
        }


    }

    private void refresh() {
        if (mPresenter != null) {
            pageNumber = 1;
            mPresenter.getRecommendFriends(pageNumber);
        }
    }

    @Override
    public void showFriendsList(RecomendFriendsListResponse response) {
        if (response != null && response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                data.clear();
                data.addAll(response.getData().getData());
                adapterRecommend.notifyDataSetChanged();
                rlAll.refreshComplete();
            } else {
                adapterRecommend.addData(response.getData().getData());
                if (adapterRecommend.getData().size() >= totalSize) {
                    adapterRecommend.loadMoreEnd();
                } else {
                    adapterRecommend.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (rlAll.isRefreshing()) {
            rlAll.refreshComplete();
        } else {
            if (adapterRecommend.isLoading()) {
                adapterRecommend.loadMoreFail();
            }
        }
    }

    @Override
    public void onFocusReslult(boolean b, String id) {
        if (b) {
            ToastUtil.showTrueToast(this, "关注成功");
            doFuces(id);
        } else {
            ToastUtil.showTrueToast(this, "关注失败");
        }
    }

    private void doFuces(String mid) {
        for (int i = 0; i < adapterRecommend.getData().size(); i++) {
            if (mid.equals(adapterRecommend.getData().get(i).getId() + "")) {
                adapterRecommend.getData().get(i).setAttentionFlag(1);
                adapterRecommend.notifyDataSetChanged();
                break;
            }
        }
    }
}
