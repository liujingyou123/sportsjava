package com.sports.limitsport.mine.presenter;

import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.mine.ui.IOrderDetailView;
import com.sports.limitsport.model.MyCollectFineShowResponse;
import com.sports.limitsport.model.OrderDetailResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NetSubscriber;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/8/16.
 */

public class OrderDetailPresenter {
    private IOrderDetailView mIOrderDetailView;

    public OrderDetailPresenter(IOrderDetailView mIOrderDetailView) {
        this.mIOrderDetailView = mIOrderDetailView;
    }

    /**
     * 获取订单详情
     *
     * @param orderId
     */
    public void getOrderDetail(String orderId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderId", orderId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).getOrderDetail(hashMap), new NetSubscriber<OrderDetailResponse>() {
            @Override
            public void response(OrderDetailResponse response) {
                if (mIOrderDetailView != null) {
                    mIOrderDetailView.showOrderDetail(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    /**
     * 取消订单
     *
     * @param orderId
     */
    public void cancelOrder(String orderId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderId", orderId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).cancelOrder(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIOrderDetailView != null) {
                    mIOrderDetailView.cancelOrder(response.success);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIOrderDetailView != null) {
                    mIOrderDetailView.cancelOrder(false);
                }
            }
        });
    }

    /**
     * 退款
     *
     * @param orderId
     */
    public void reFundOrder(String orderId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("orderId", orderId);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).reFundOrder(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mIOrderDetailView != null) {
                    mIOrderDetailView.refundOrderResult(response.success);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIOrderDetailView != null) {
                    mIOrderDetailView.refundOrderResult(false);
                }
            }
        });
    }

    public void clear() {
        mIOrderDetailView = null;
    }
}