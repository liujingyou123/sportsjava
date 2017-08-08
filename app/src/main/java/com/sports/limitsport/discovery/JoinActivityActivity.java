package com.sports.limitsport.discovery;

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
import com.sports.limitsport.activity.ActivityDetailActivity;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.MyCollectActivityAdapter;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 个人主页 参加的活动
 */

public class JoinActivityActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlvactivity)
    RecyclerView rlvactivity;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private MyCollectActivityAdapter adapter;
    private String userId;
    private int pageNumber = 1;
    private int totalSize;
    private List<Act> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        getIntentData();
        ButterKnife.bind(this);
        initView();
        getActivityList();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
        }
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    private void initView() {
        tvFocusHouse.setText("参加的活动");

        rlvactivity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        TextView emptyText = (TextView) emptyView.findViewById(R.id.tv_empty);
        emptyText.setText("还没有参加的活动哦～");
        adapter = new MyCollectActivityAdapter(null);
        adapter.bindToRecyclerView(rlvactivity);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());
        adapter.setEmptyView(emptyView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter madapter, View view, int position) {
                XLog.e("position = " + position);
                Act act = (Act) madapter.getItem(position);
                if (act != null) {
                    gotoActivityDetail(act);
                }
            }
        });

        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlvactivity);
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
        getActivityList();
    }

    private void refresh() {
        pageNumber = 1;
        getActivityList();
    }

    /**
     * 前往活动详情
     */
    private void gotoActivityDetail(Act act) {
        Intent intent = new Intent(this, ActivityDetailActivity.class);
        intent.putExtra("id", act.getId() + "");
        intent.putExtra("week", act.getWeek());
        intent.putExtra("minMoney", act.getMinMoney());
        startActivity(intent);
    }

    /**
     * 参加的活动
     */
    public void getActivityList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("userId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getJoinActivityList(hashMap), new NetSubscriber<ActivityResponse>() {
            @Override
            public void response(ActivityResponse response) {
                if (response.getData() != null) {
                    totalSize = response.getData().getTotalSize();
                }
                if (rlAll.isRefreshing()) {
                    if (response.getData() != null) {
                        data.clear();
                        data.addAll(response.getData().getData());
                        adapter.notifyDataSetChanged();
                    }

                    rlAll.refreshComplete();
                } else {
                    if (response.getData() != null) {
                        adapter.addData(response.getData().getData());
                    }
                    if (adapter.getData().size() >= totalSize) {
                        adapter.loadMoreEnd();
                    } else {
                        adapter.loadMoreComplete();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (rlAll.isRefreshing()) {
                    rlAll.refreshComplete();
                } else {
                    adapter.loadMoreFail();
                }
            }
        });
    }
}
