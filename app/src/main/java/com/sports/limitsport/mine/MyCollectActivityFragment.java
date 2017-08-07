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
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.ActivityDetailActivity;
import com.sports.limitsport.activity.ActivityFragment;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.MyCollectActivityAdapter;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.MyCollectActivityResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/7.
 * 我收藏的活动
 */

public class MyCollectActivityFragment extends Fragment {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private MyCollectActivityAdapter adapter;
    private List<Act> data = new ArrayList<>();
    private int pageNumber = 1;
    private int totalSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycollectactivity, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getActivityList();
        return view;
    }

    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        TextView tvGo = (TextView) emptyView.findViewById(R.id.tv_go);
        tvGo.setVisibility(View.GONE);
        tvTip.setText("好像什么都没有～");
        tvGo.setText("去逛逛");
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlv.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new MyCollectActivityAdapter(data);
        adapter.bindToRecyclerView(rlv);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setEmptyView(emptyView);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Act act = (Act) adapter.getItem(position);
                if (act != null) {
                    Intent intent = new Intent(getContext(), ActivityDetailActivity.class);
                    intent.putExtra("id", act.getId() + "");
                    intent.putExtra("week", act.getWeek());
                    intent.putExtra("minMoney", act.getMinMoney());
                    getContext().startActivity(intent);
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

    public void getActivityList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getCollectActivityList(hashMap), new NetSubscriber<MyCollectActivityResponse>() {
            @Override
            public void response(MyCollectActivityResponse response) {
                if (response.getData() != null) {
                    totalSize = response.getData().getTotalSize();
                    if (rlAll.isRefreshing()) {
                        data.clear();
                        data.addAll(response.getData().getData());
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

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (rlAll.isRefreshing()) {
                    rlAll.refreshComplete();
                }

                ToastUtil.showFalseToast(getContext(), "加载失败");
//                adapter.loadMoreFail();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
