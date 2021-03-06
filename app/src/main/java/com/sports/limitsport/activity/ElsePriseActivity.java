package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ElsePriseAdapter;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.PraiseList;
import com.sports.limitsport.model.PraiseListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/17.
 * 他们也觉得赞
 */

public class ElsePriseActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rv_else)
    RecyclerView rvElse;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;
    private ElsePriseAdapter adapter;
    private List<PraiseList> data = new ArrayList<>();
    private int pageNumber = 1;
    private String id;
    private String praiseType;
    private int totalSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elseprise);
        ButterKnife.bind(this);
        getIntentData();
        initView();

        rlAll.autoRefresh();
//        getPraiseList();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            praiseType = intent.getStringExtra("praiseType");
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_reload:
                viewNonet.setVisibility(View.GONE);
                rlAll.autoRefreshDelay();
                break;
        }
    }

    private void initView() {
        tvFocusHouse.setText("他们也觉得赞");
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_commentlist, null);
        emptyView.findViewById(R.id.tv_go).setVisibility(View.GONE);
        ((TextView) emptyView.findViewById(R.id.tv_empty)).setText("还没有人报名，快去抢沙发呦～");

        rvElse.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new ElsePriseAdapter(data);
        adapter.bindToRecyclerView(rvElse);

        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setEmptyView(emptyView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PraiseList item = (PraiseList) adapter.getItem(position);
                if (item != null) {
                    gotoPersonInfo(item.getUserId() + "");
                }
            }
        });

        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rvElse);
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
        pageNumber++;
        getPraiseList();

    }

    private void refresh() {
        pageNumber = 1;
        getPraiseList();
    }


    /**
     * 获取点赞人员列表
     */
    public void getPraiseList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("praiseType", praiseType);
        hashMap.put("trendId", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).praiseList(hashMap), new LoadingNetSubscriber<PraiseListResponse>() {
            @Override
            public void response(PraiseListResponse response) {
                if (response != null && response.getData() != null) {
                    totalSize = response.getData().getTotalSize();
                    if (rlAll.isRefreshing()) {
                        data.clear();
                        data.addAll(response.getData().getData());
                        adapter.setNewData(data);
                        adapter.disableLoadMoreIfNotFullPage();
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

            @Override
            public void onError(Throwable e) {
                super.onError(e);

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
        });
    }

    /**
     * 进入个人主页
     */
    private void gotoPersonInfo(String id) {
        Intent intent = new Intent(this, PersonInfoActivity.class);
        intent.putExtra("userId", id);
        startActivity(intent);
    }
}
