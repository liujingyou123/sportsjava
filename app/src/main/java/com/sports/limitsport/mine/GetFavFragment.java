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
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.GetFavAdapter;
import com.sports.limitsport.model.HuDongNoticeList;
import com.sports.limitsport.model.HuDongNoticeListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/7/6.
 * 获赞
 */

public class GetFavFragment extends Fragment {
    @BindView(R.id.rl_focus)
    RecyclerView rlFocus;
    Unbinder unbinder;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;

    private GetFavAdapter adapter;
    private List<HuDongNoticeList> data = new ArrayList<>();
    private int pageNumber = 1;
    private int totalSize;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_getfav, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getList();
        return view;
    }


    private void initView() {
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_commentlist, null);
        TextView etEmpty = (TextView) emptyView.findViewById(R.id.tv_empty);
        TextView tvGo = (TextView) emptyView.findViewById(R.id.tv_go);
        if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
            etEmpty.setText("好像什么都没有");
            tvGo.setVisibility(View.GONE);
        }
        tvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditNewDongTaiActivity.class);
                startActivity(intent);
            }
        });
        rlFocus.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new GetFavAdapter(data);
        adapter.bindToRecyclerView(rlFocus);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setEmptyView(emptyView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlFocus);
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
        getList();
    }

    private void refresh() {
        pageNumber = 1;
        getList();
    }

    private void getList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", "3");
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getHuDongList(hashMap), new LoadingNetSubscriber<HuDongNoticeListResponse>() {
            @Override
            public void response(HuDongNoticeListResponse response) {
                if (response != null && response.getData() != null) {
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

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
