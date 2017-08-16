package com.sports.limitsport.discovery;

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
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.discovery.model.FindClubSection;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.MyClubsAdapter;
import com.sports.limitsport.model.Club;
import com.sports.limitsport.model.ClubListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
import com.sports.limitsport.view.SpacesItemTopDecoration;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 参加的俱乐部
 */

public class JoinClubActivity extends BaseActivity {

    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;
    private MyClubsAdapter adapter;
    private List<Club> data = new ArrayList<>();
    private int pageNumber = 1;
    private int totalSize;
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinclub);
        getIntentData();
        ButterKnife.bind(this);
        initView();
//        getClubsList();
        rlAll.autoRefresh();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
        }
    }

    private void initView() {
        tvFocusHouse.setText("参加的俱乐部");

        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_commentlist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        TextView tvGo = (TextView) emptyView.findViewById(R.id.tv_go);
        tvTip.setText("好像什么都没有～");
        tvGo.setText("去逛逛");
        tvGo.setVisibility(View.GONE);
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        rlv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyClubsAdapter(data);
        adapter.bindToRecyclerView(rlv);
        adapter.setEmptyView(emptyView);

        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FindClubSection findClubSection = (FindClubSection) adapter.getItem(position);
                Club club = findClubSection.t;
                if (club != null) {
                    Intent intent = new Intent(JoinClubActivity.this, ClubDetailActivity.class);
                    intent.putExtra("id", club.getId());
                    startActivity(intent);
                }

            }
        });

        SpacesItemTopDecoration decoration = new SpacesItemTopDecoration(60);
        rlv.addItemDecoration(decoration);

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
        getClubsList();
    }

    private void refresh() {
        pageNumber = 1;
        getClubsList();
    }

    public void getClubsList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("type", "4");
        hashMap.put("userId", userId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getClubList(hashMap), new NetSubscriber<ClubListResponse>() {
            @Override
            public void response(ClubListResponse response) {
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
}
