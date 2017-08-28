package com.sports.limitsport.notice;

import android.content.Intent;
import android.graphics.Color;
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
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.FansList;
import com.sports.limitsport.model.FansListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.notice.adapter.MyFocusPersonSelectAdapter;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/14.
 * 我关注的人选择
 */

public class SelectMyFocusPersonActivity extends BaseActivity {
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;

    private MyFocusPersonSelectAdapter adapter;
    private List<FansList> data = new ArrayList<>();
    private int pageNumber = 1;
    private int totalSize;
    private List<FansList> mSelect;
//    private List<Integer> mSelect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmyfocus);
        ButterKnife.bind(this);
        getIntentData();
        initView();
//        getFocusList();
        rlAll.autoRefreshDelay();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            mSelect = (List<FansList>) intent.getSerializableExtra("select");
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right, R.id.tv_reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                List<FansList> mSelect = new ArrayList<>();
                if (adapter.mSelectedItems.size() > 0) {
//                    for (int i = 0; i < adapter.mSelectedPositions.size(); i++) {
//                        FansList fanslist = adapter.getItem(adapter.mSelectedPositions.get(i));
//                        mSelect.add(fanslist);
//                    }
                    Intent intent = new Intent();
                    intent.putExtra("name", (Serializable) adapter.mSelectedItems);
//                    intent.putExtra("select", (Serializable) adapter.mSelectedPositions);
                    setResult(RESULT_OK, intent);
                }
                finish();
                break;
            case R.id.tv_reload:
                viewNonet.setVisibility(View.GONE);
                rlAll.autoRefreshDelay();
                break;
        }
    }

    private void initView() {
        tvFocusHouse.setText("我的关注的人");
        tvFocusRight.setVisibility(View.VISIBLE);
        tvFocusRight.setText("完成");
        tvFocusRight.setTextColor(Color.parseColor("#ffffff"));
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        TextView emptyText = (TextView) emptyView.findViewById(R.id.tv_empty);
        emptyText.setText("还没有关注的人哦～");
        rlv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyFocusPersonSelectAdapter(data);
        if (mSelect != null) {
            adapter.mSelectedItems.addAll(mSelect);
        }
        adapter.bindToRecyclerView(rlv);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setEmptyView(emptyView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FansList fansList = (FansList) adapter.getItem(position);
                if (fansList != null) {
                    gotoPersionInfo(fansList.getId());
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
        getFocusList();

    }

    private void refresh() {
        pageNumber = 1;
        getFocusList();
    }

    private void getFocusList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getMyFoucsList(hashMap), new LoadingNetSubscriber<FansListResponse>() {
            @Override
            public void response(FansListResponse response) {
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
     * 个人主页
     */
    private void gotoPersionInfo(String userId) {
        Intent intent = new Intent(this, PersonInfoActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
