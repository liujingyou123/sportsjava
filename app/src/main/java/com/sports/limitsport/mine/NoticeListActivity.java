package com.sports.limitsport.mine;

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
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.NoticeAdapter;
import com.sports.limitsport.mine.presenter.NoticeListPresenter;
import com.sports.limitsport.mine.ui.INoticeListView;
import com.sports.limitsport.model.NoticeList;
import com.sports.limitsport.model.NoticeListResponse;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
import com.sports.limitsport.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class NoticeListActivity extends BaseActivity implements INoticeListView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ry_notices)
    RecyclerView ryNotices;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private NoticeAdapter adapter;
    private List<NoticeList> mData = new ArrayList<>();
    private int type; //0:活动通知： 1:系统通知
    private int pageNumber = 1;
    private NoticeListPresenter mPresenter;
    private int totalSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticelist);
        ButterKnife.bind(this);
        getInitData();
        initView();
        getData();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new NoticeListPresenter(this);
        }
        mPresenter.getNoticeList(type + "", pageNumber);
    }

    private void getInitData() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", 0);
        }

    }

    private void initView() {
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        TextView textView = (TextView) emptyView.findViewById(R.id.tv_empty);
        if (type == 0) {
            tvFocusHouse.setText("活动通知");
            textView.setText("还没有收到过活动通知哦～");
        } else if (type == 1) {
            tvFocusHouse.setText("系统通知");
            textView.setText("还没有收到过系统通知哦～");
        }
        ryNotices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new NoticeAdapter(mData);
        adapter.bindToRecyclerView(ryNotices);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setEmptyView(R.layout.empty_noticelist);

        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, ryNotices);
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
            mPresenter.getNoticeList(type + "", pageNumber);
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            mPresenter.getNoticeList(type + "", pageNumber);
        }
    }


    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showNoticeList(NoticeListResponse response) {
        if (response != null && response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                mData.clear();
                mData.addAll(response.getData().getData());
                adapter.notifyDataSetChanged();
                rlAll.refreshComplete();
            } else {
                adapter.addData(response.getData().getData());
                if (adapter.getData().size() >= totalSize) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
                }
            }
        }
    }
}
