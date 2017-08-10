package com.sports.limitsport.notice;

import android.content.Intent;
import android.graphics.Color;
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
import com.sports.limitsport.base.SelectEntity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.notice.adapter.SelectMyJoinAdapter;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
import com.sports.limitsport.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/14.
 * 选择我参加的活动
 */

public class SelectMyJoinActivity extends BaseActivity {
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private SelectMyJoinAdapter adapter;
    private List<Act> data = new ArrayList<>();
    private int pageNumber = 1;
    private int totalSize;
    private int selectPostion = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmyjoinactivity);
        ButterKnife.bind(this);
        getInentData();
        initView();
        getActivityList();
    }

    private void getInentData() {
        Intent intent = getIntent();
        if (intent != null) {
            selectPostion = intent.getIntExtra("positionSelect", -1);
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                if (adapter.mSelectedPositions != null && adapter.mSelectedPositions.size() > 0) {
                    int position = adapter.mSelectedPositions.get(0);
                    Intent intent = new Intent();
                    intent.putExtra("activity", adapter.getData().get(position));
                    intent.putExtra("positionSelect", position);
                    setResult(RESULT_OK, intent);
                    finish();
                }


                break;
        }
    }

    private void initView() {
        tvFocusHouse.setText("我参加的活动");
        tvFocusRight.setVisibility(View.VISIBLE);
        tvFocusRight.setText("完成");
        tvFocusRight.setTextColor(Color.parseColor("#ffffff"));
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        TextView emptyText = (TextView) emptyView.findViewById(R.id.tv_empty);
        emptyText.setText("还没有参加的活动哦～");
        rlv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SelectMyJoinAdapter(data);
        if (selectPostion != -1) {
            adapter.mSelectedPositions.add(selectPostion);
        }
        adapter.bindToRecyclerView(rlv);
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
        }, rlv);
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

    public void getActivityList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
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
                    if (adapter.isLoading()) {
                        adapter.loadMoreFail();
                    }
                }
            }
        });

    }

}
