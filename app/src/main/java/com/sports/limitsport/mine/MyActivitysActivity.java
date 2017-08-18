package com.sports.limitsport.mine;

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
import com.sports.limitsport.activity.PaySuccessActivity;
import com.sports.limitsport.activity.presenter.PayPresenter;
import com.sports.limitsport.activity.ui.IPayOrderView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.MyActivitysAdapter;
import com.sports.limitsport.model.EventBusOrder;
import com.sports.limitsport.model.MyCollectActivityResponse;
import com.sports.limitsport.model.OrdersList;
import com.sports.limitsport.model.OrdersListResponse;
import com.sports.limitsport.model.PayOrderResponse;
import com.sports.limitsport.model.SelectTicket;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.ToolsUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/7.
 * 我的活动
 */

public class MyActivitysActivity extends BaseActivity implements IPayOrderView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ry_activity)
    RecyclerView ryActivity;
    @BindView(R.id.view_nonet)
    RelativeLayout viewNonet;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private MyActivitysAdapter adapter;
    private int pageNumber = 1;
    private int totalSize;
    private List<OrdersList> data = new ArrayList<>();
    private PayPresenter mPresenter;
    private OrdersList orderList;
    private boolean isRefresh = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivitys);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (mPresenter == null) mPresenter = new PayPresenter(this);
        initView();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRefresh) {
            isRefresh = false;
            rlAll.autoRefresh();
        }
    }

    @Subscribe
    public void isStatusChange(EventBusOrder param) {
        if (param != null && param.isChange) {
            isRefresh = true;
        }
    }

    private void initView() {
        tvFocusHouse.setText("我的活动");
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
        ryActivity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new MyActivitysAdapter(data);
        adapter.bindToRecyclerView(ryActivity);

        adapter.setEmptyView(emptyView);
        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrdersList tmp = (OrdersList) adapter.getItem(position);
                Intent intent = new Intent(MyActivitysActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderNo", tmp.getOrderNo());
                intent.putExtra("isJoin", tmp.getIsJoin());
                startActivity(intent);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                orderList = (OrdersList) adapter.getItem(position);
                if (orderList != null) {
                    if (orderList.getIsJoin() == 1) { //已参加
                        gotoEditDongtai();
                    } else if ("0".equals(orderList.getOrderStatus())) {
                        gotoPay(orderList.getOrderInfo());
                    }
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
        }, ryActivity);
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
        getOrders();
    }

    private void refresh() {
        pageNumber = 1;
        getOrders();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    private void getOrders() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getOrders(hashMap), new NetSubscriber<OrdersListResponse>() {
            @Override
            public void response(OrdersListResponse response) {
                if (response.getData() != null) {
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
                    if (e != null && (e.getClass().getName().equals(UnknownHostException.class.getName())
                            || e.getClass().getName().equals(SocketTimeoutException.class.getName())
                            || e.getClass().getName().equals(ConnectException.class.getName()))) {
                        viewNonet.setVisibility(View.VISIBLE);
                    }
                }

//                ToastUtil.showFalseToast(getContext(), "加载失败");
//                adapter.loadMoreFail();
            }
        });
    }

    /**
     * 去支付
     */
    private void gotoPay(String orderInfo) {
        if (mPresenter != null) {
            mPresenter.aliPay(orderInfo);
        }
    }

    /**
     * 前往编辑动态页
     */
    private void gotoEditDongtai() {
        Intent intent = new Intent(this, EditNewDongTaiActivity.class);
        startActivity(intent);
    }


    @Override
    public void showPayOrderResult(PayOrderResponse response) {

    }

    @Override
    public void showPayResult(boolean isSuccess, String OrderNo) {
        if (isSuccess) {
            goToPayResult(0, null);
        }
    }

    @Override
    public void showPayOrderResultFail(Throwable e) {

    }

    /**
     * 前往支付结果页
     *
     * @param type 0 成功 1失败
     */
    private void goToPayResult(int type, String errormsg) {
        Intent intent = new Intent(this, PaySuccessActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("errorMsg", errormsg);

        intent.putExtra("title", orderList.getName());
        intent.putExtra("imgCover", orderList.getCoverUrl());
        SelectTicket selectTicket = new SelectTicket();
        selectTicket.num = orderList.getNumber();
        selectTicket.name = orderList.getTicketsName();
        selectTicket.totalPrice = orderList.getTotalMoney();
        intent.putExtra("selectTicket", selectTicket);
        String startTime = orderList.getStartDate() + " " + UnitUtil.stringToWeek(orderList.getWeek()) + " " + orderList.getStartTime();
        intent.putExtra("startTime", startTime);
        intent.putExtra("address", orderList.getAddress());
        intent.putExtra("from", 1);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
