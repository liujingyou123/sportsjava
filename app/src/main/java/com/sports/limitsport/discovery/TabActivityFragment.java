package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.adapter.ClubActivityAdapter;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.model.ActivityResponse;
import com.sports.limitsport.model.EventBusScroll;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.henrytao.smoothappbarlayout.SmoothAppBarLayout;
import me.henrytao.smoothappbarlayout.base.ObservableFragment;

/**
 * Created by liuworkmac on 17/7/12.
 * 俱乐部在线活动
 */

public class TabActivityFragment extends Fragment implements ObservableFragment {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    Unbinder unbinder;
    private ClubActivityAdapter adapter;
    private int pageNumber = 1;
    private int totalSize;
    private List<Act> data = new ArrayList<>();
    private String clubId;
    private View headView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabactivity, null);
        unbinder = ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        getBundleData();
        initView();
        getActivityList();
        return view;
    }

    @Subscribe
    public void getHeadHeight(EventBusScroll params) {
        if (params != null && params.height > 0) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, params.height);
            headView.setLayoutParams(lp);
        }
    }

    private void getBundleData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            clubId = bundle.getString("id");
        }
    }


    private void initView() {
        if (data != null && data.size() == 0) {
            data.add(null);
        }
        View emptyView = LayoutInflater.from(this.getContext()).inflate(R.layout.empty_noticelist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        tvTip.setText("还没有发布活动哦～");
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        rlv.setLayoutManager(linearLayoutManager);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_head_full, null);
        headView = view.findViewById(R.id.view_full_empty);
        adapter = new ClubActivityAdapter(data);
        adapter.addHeaderView(view);
        adapter.bindToRecyclerView(rlv);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

//        adapter.setEmptyView(R.layout.empty_noticelist);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlv);

//        rlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//
//                    int visiblePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
//                    if (visiblePosition == 0) {
//                        EventBus.getDefault().post(new EventBusScroll());
////                        barLayout.setExpanded(true, true);
//                    }
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                XLog.e("dy = " +dy);
//            }
//        });
    }

    private void loadMore() {
        pageNumber++;
        getActivityList();

    }

    public void getActivityList() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("club", clubId);
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getJoinActivityList(hashMap), new NetSubscriber<ActivityResponse>() {
            @Override
            public void response(ActivityResponse response) {
                if (response.getData() != null) {
                    totalSize = response.getData().getTotalSize();
                }
                if (response.getData() != null) {
                    if (adapter.getData() != null && adapter.getData().size() == 1 && adapter.getData().get(0) == null) {
                        adapter.getData().clear();
                    }
                    adapter.addData(response.getData().getData());
                }
                if (adapter.getData().size() >= totalSize) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View getScrollTarget() {
        return rlv;
    }

    @Override
    public boolean onOffsetChanged(SmoothAppBarLayout smoothAppBarLayout, View target, int verticalOffset) {
        return me.henrytao.smoothappbarlayout.base.Utils.syncOffset(smoothAppBarLayout, target, verticalOffset, getScrollTarget());
    }
}
