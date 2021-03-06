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
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.discovery.adapter.NewPersionAdapter;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.NewPersonListResponse;
import com.sports.limitsport.model.SignUpUser;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
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
 * Created by liuworkmac on 17/7/11.
 */

public class NewPersonReportActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv_new)
    RecyclerView rlvNew;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;
    private NewPersionAdapter adapter;
    private List<SignUpUser> data = new ArrayList<>();
    private int pageNumber = 1;
    private int totalSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newperson);
        ButterKnife.bind(this);
        initView();
//        getNewPersionList();
        rlAll.autoRefresh();
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
        tvFocusHouse.setText("新人报道");
        rlvNew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new NewPersionAdapter(data);
        adapter.bindToRecyclerView(rlvNew);
        View empptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        ((TextView) empptyView.findViewById(R.id.tv_empty)).setText("暂时没有数据～");
        adapter.setEmptyView(empptyView);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SignUpUser item = (SignUpUser) adapter.getItem(position);
                gotoPersonInfo(item.getId() + "");
            }
        });


        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlvNew);
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
        getNewPersionList();

    }

    private void refresh() {
        pageNumber = 1;
        getNewPersionList();
    }

    /**
     * 获取新人报到
     */
    public void getNewPersionList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getNewPersonList(hashMap), new NetSubscriber<NewPersonListResponse>() {
            @Override
            public void response(NewPersonListResponse response) {
                showNewPersonList(response);
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

    public void showNewPersonList(NewPersonListResponse response) {
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

    /**
     * 进入个人主页
     */
    private void gotoPersonInfo(String id) {
        Intent intent = new Intent(this, PersonInfoActivity.class);
        intent.putExtra("userId", id);
        startActivity(intent);
    }
}
