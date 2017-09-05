package com.sports.limitsport.discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.discovery.adapter.FindClubAdapter;
import com.sports.limitsport.discovery.model.FindClubSection;
import com.sports.limitsport.discovery.presenter.FindClubPresenter;
import com.sports.limitsport.discovery.ui.IFindClubView;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.Club;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
import com.sports.limitsport.view.SpacesItemHDecoration;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 发现俱乐部
 */

public class FindClubActivity extends BaseActivity implements IFindClubView {
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_clubs)
    RecyclerView rlClubs;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;

    private FindClubAdapter adapter;
    private List<Club> clubs = new ArrayList<>();
    private List<Club> clubsTmp = new ArrayList<>();

    private FindClubPresenter mPresenter;
    private int pageNumber = 1;
    private int totalSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findclub);
        ButterKnife.bind(this);
        initView();
        showClubs();

        getData();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new FindClubPresenter(this);
        }
//        mPresenter.getTodayClubsList();
        rlAll.autoRefresh();
    }

    private void initView() {
        tvFocusHouse.setText("发现俱乐部");
        tvFocusRight.setText("创建");
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right, R.id.tv_reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                Intent intent = new Intent(this, CreateClubActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_reload:
                viewNonet.setVisibility(View.GONE);
                rlAll.autoRefreshDelay();
                break;
        }
    }

    private void showClubs() {
        rlClubs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new FindClubAdapter(clubs);
        adapter.bindToRecyclerView(rlClubs);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Club club = (Club) adapter.getItem(position);
                if (club != null) {
                    Intent intent = new Intent(FindClubActivity.this, ClubDetailActivity.class);
                    intent.putExtra("id", club.getId());
                    startActivity(intent);
                }

            }
        });

        SpacesItemHDecoration decoration = new SpacesItemHDecoration(16);
        rlClubs.addItemDecoration(decoration);

        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlClubs);
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
            mPresenter.getAllClubsList(pageNumber);

        }
    }

    private void refresh() {
        if (mPresenter != null) {
            pageNumber = 1;
            mPresenter.getTodayClubsList();
        }
    }


    @Override
    public void showAllClubsList(ClubListResponse response) {
        if (response != null && response.getData() != null && response.getData().getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {

                List<Club> tmps = response.getData().getData();
                clubs.clear();
                clubsTmp.addAll(tmps);

                clubs.addAll(clubsTmp);
                adapter.setNewData(clubs);
                adapter.disableLoadMoreIfNotFullPage();

//                adapter.notifyDataSetChanged();
                rlAll.refreshComplete();

                if (adapter.getData().size() >= totalSize) {
                    adapter.loadMoreEnd();
                }
            } else {
                List<Club> tmps = response.getData().getData();
                if (tmps != null && tmps.size() > 0) {
                    clubsTmp.addAll(tmps);
                    adapter.addData(clubsTmp);
                }
                if (adapter.getData().size() >= totalSize) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
                }
            }
        }
    }


    @Override
    public void showTodayClubsList(ClubListResponse response) {
        if (response != null && response.getData() != null && response.getData().getData() != null && response.getData().getData().size() > 0) {

            Club club = new Club();
            club.isHeader = true;
            club.setClubName("今日推荐");

            List<Club> tmps = response.getData().getData();

            clubsTmp.clear();
            clubsTmp.add(0, club);
            clubsTmp.addAll(1, tmps);

            Club clubAll = new Club();
            clubAll.isHeader = true;
            clubAll.setClubName("全部俱乐部");
            clubsTmp.add(clubAll);
            mPresenter.getAllClubsList(pageNumber);

        }
    }

    @Override
    public void onError(Throwable e) {
        if (adapter.isLoading()) {
            adapter.loadMoreFail();
        } else {
            if (rlAll.isRefreshing()) {
                rlAll.refreshComplete();
            }
            if (e != null && (e.getClass().getName().equals(UnknownHostException.class.getName()) || e.getClass().getName().equals(SocketTimeoutException.class.getName()))) {
                viewNonet.setVisibility(View.VISIBLE);
            }
        }
    }


    private List<FindClubSection> clubToSection(List<Club> data) {
        List<FindClubSection> ret = new ArrayList<>();
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                FindClubSection findClubSection = new FindClubSection(data.get(i));
                ret.add(findClubSection);
            }
        }
        return ret;
    }
}
