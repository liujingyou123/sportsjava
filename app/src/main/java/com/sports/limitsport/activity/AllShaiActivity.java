package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.AllShaiAdapter;
import com.sports.limitsport.activity.presenter.AllShaiPresenter;
import com.sports.limitsport.activity.ui.IAllShaiView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 大家都在晒
 */

public class AllShaiActivity extends BaseActivity implements IAllShaiView {
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ry_all)
    RecyclerView ryAll;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private AllShaiAdapter allShaiAdapter;
    private AllShaiPresenter mPresenter;
    private int pageNumber = 1;
    private String id;//活动ID
    private List<DongTaiList> data = new ArrayList<>();
    private int totalSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allshai);
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
            mPresenter = new AllShaiPresenter(this);
        }
        mPresenter.getAllShai(id, pageNumber);
    }

    private void initView() {
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        tvFocusHouse.setText("大家都在晒");
        initRy();
    }

    private void initRy() {
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_with_btn_full, null);
        emptyView.findViewById(R.id.tv_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoEditDongtai();
            }
        });
        ryAll.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        allShaiAdapter = new AllShaiAdapter(data);
        allShaiAdapter.bindToRecyclerView(ryAll);
        allShaiAdapter.setEmptyView(emptyView);
        allShaiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                gotoDongtaiDetail();
            }
        });

        allShaiAdapter.setLoadMoreView(new CustomLoadMoreView());

        allShaiAdapter.disableLoadMoreIfNotFullPage();
        allShaiAdapter.setEnableLoadMore(true);
        allShaiAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, ryAll);
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
            mPresenter.getAllShai(id, pageNumber);
        }
    }

    private void refresh() {
        pageNumber = 1;
        if (mPresenter != null) {
            mPresenter.getAllShai(id, pageNumber);
        }
    }


    /**
     * 前往动态详情页
     */
    private void gotoDongtaiDetail() {
        Intent intent = new Intent(this, DongTaiDetailActivity.class);
        startActivity(intent);
    }

    /**
     * 前往编辑动态页面
     */
    private void gotoEditDongtai() {
        Intent intent = new Intent(this, EditNewDongTaiActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_right:
                gotoEditDongtai();
                break;
        }
    }

    @Override
    public void showAllShiList(DongTaiListResponse response) {
        if (response != null && response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (rlAll.isRefreshing()) {
                data.clear();
                data.addAll(response.getData().getData());
                allShaiAdapter.notifyDataSetChanged();
                rlAll.refreshComplete();
            } else {
                allShaiAdapter.addData(response.getData().getData());
                if (allShaiAdapter.getData().size() >= totalSize) {
                    allShaiAdapter.loadMoreEnd();
                } else {
                    allShaiAdapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void onError(Throwable e) {

    }
}
