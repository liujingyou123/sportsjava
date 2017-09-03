package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.FansAdapter;
import com.sports.limitsport.model.EventBusComment;
import com.sports.limitsport.model.EventBusFansNews;
import com.sports.limitsport.model.FansList;
import com.sports.limitsport.model.FansListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import org.greenrobot.eventbus.EventBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/6.
 * 粉丝
 */

public class FansFragment extends Fragment {
    @BindView(R.id.rl_fans)
    RecyclerView rlFans;
    Unbinder unbinder;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private FansAdapter adapter;
    private List<FansList> data = new ArrayList<>();
    private int pageNumber = 1;
    private int totalSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fans, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rlAll.autoRefreshDelay();
    }

    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        emptyView.findViewById(R.id.tv_go).setVisibility(View.GONE);
//                .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        rlFans.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new FansAdapter(data);
        adapter.bindToRecyclerView(rlFans);

        adapter.setEmptyView(emptyView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FansList fansList = (FansList) adapter.getItem(position);
                if (fansList != null) {
                    Intent intent = new Intent(getContext(), PersonInfoActivity.class);
                    intent.putExtra("userId", fansList.getId());
                    startActivity(intent);
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
        }, rlFans);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void loadMore() {
        pageNumber++;
        getFansList("0");

    }

    private void refresh() {
        pageNumber = 1;
        getFansList("1");
    }

    private void getFansList(final String updateReadFlag) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        hashMap.put("updateReadFlag", updateReadFlag);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getMyFansList(hashMap), new LoadingNetSubscriber<FansListResponse>() {
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

//                        if ("1".equals(updateReadFlag)) {
//                            EventBusFansNews param = new EventBusFansNews();
//                            param.hasFansNew = false;
//                            EventBus.getDefault().post(param);
//                        }
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
                    if (e != null && (e.getClass().getName().equals(UnknownHostException.class.getName())
                            || e.getClass().getName().equals(SocketTimeoutException.class.getName())
                            || e.getClass().getName().equals(ConnectException.class.getName()))) {
                        viewNonet.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @OnClick(R.id.tv_reload)
    public void onClick() {
        viewNonet.setVisibility(View.GONE);
        rlAll.autoRefreshDelay();
    }
}
