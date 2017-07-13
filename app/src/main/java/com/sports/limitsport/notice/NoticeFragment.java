package com.sports.limitsport.notice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseFragment;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.notice.adapter.MyNoticeDongTaiAdapter;
import com.sports.limitsport.notice.adapter.MyNoticeRecommendAdapter;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.NoticeViewHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class NoticeFragment extends BaseFragment {
    @BindView(R.id.rlv_my_notice)
    RecyclerView rlvMyNotice;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.rl_refresh)
    RelativeLayout rlRefresh;
    Unbinder unbinder;


    private MyNoticeDongTaiAdapter adapter;
    private MyNoticeRecommendAdapter adapterRecommend;

    private boolean isShow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        NoticeViewHeaderView headerView = new NoticeViewHeaderView(getContext());
        headerView.setCloseClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeAllHeaderView();
            }
        });
        headerView.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoticeFragment.this.getContext(), FindMoreActivity.class);
                startActivity(intent);
            }
        });
        rlvMyNotice.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new MyNoticeDongTaiAdapter(MyTestData.getData());
        adapter.bindToRecyclerView(rlvMyNotice);
        adapter.addHeaderView(headerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
            }
        }, rlvMyNotice);
        rlAll.setEnableLoadMore(false);

        rlAll.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                refresh();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (isShow) {
                isShow = false;
                initView();
            } else {
                isShow = true;
                initEmptyView();
            }
        }
    }

    /**
     * 没有关注的时候
     */
    private void initEmptyView() {
        NoticeViewHeaderView headerView = new NoticeViewHeaderView(getContext());
        headerView.setEmpty();
        headerView.setCloseClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterRecommend.removeAllHeaderView();
            }
        });
        rlvMyNotice.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapterRecommend = new MyNoticeRecommendAdapter(MyTestData.getData());
        adapterRecommend.bindToRecyclerView(rlvMyNotice);
        adapterRecommend.addHeaderView(headerView);
        adapterRecommend.disableLoadMoreIfNotFullPage();
        adapterRecommend.setEnableLoadMore(true);
        adapterRecommend.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
            }
        }, rlvMyNotice);
        rlAll.setEnableLoadMore(false);
    }

    private void refresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rlAll.refreshComplete();
                rlRefresh.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rlRefresh.setVisibility(View.GONE);
                    }
                }, 1500);
            }
        }, 2000);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.imv_focus_house_back, R.id.imv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                break;
            case R.id.imv_right:
                Intent intent = new Intent(this.getContext(), EditNewDongTaiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
