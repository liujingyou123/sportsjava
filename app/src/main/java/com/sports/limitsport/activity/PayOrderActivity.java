package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.presenter.PayPresenter;
import com.sports.limitsport.activity.ui.IPayOrderView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.OrderRequest;
import com.sports.limitsport.model.PayOrderResponse;
import com.sports.limitsport.model.SelectTicket;
import com.sports.limitsport.model.SignList;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.NumCheckView;
import com.sports.limitsport.view.OrderInfoView;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/27.
 */

public class PayOrderActivity extends BaseActivity implements IPayOrderView {

    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_ticket_select_num)
    TextView tvTicketSelectNum;
    @BindView(R.id.ncv)
    NumCheckView ncv;
    @BindView(R.id.ll_orders)
    LinearLayout llOrders;
    @BindView(R.id.tv_price_bottom)
    TextView tvPriceBottom;

    private String id; //活动ID
    private String title;//活动title
    private String imgCover;//活动封面
    private String startTime; //活动时间
    private String address; // 地址
    private SelectTicket selectTicket;

    private List<SignList> mData = new ArrayList<>();
    private PayPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payorder);
        ButterKnife.bind(this);
        getIntentData();
        initRecy();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            title = intent.getStringExtra("title");
            imgCover = intent.getStringExtra("imgCover");
            selectTicket = (SelectTicket) intent.getSerializableExtra("selectTicket");
            startTime = intent.getStringExtra("startTime");
            address = intent.getStringExtra("address");
        }
    }


    private void initRecy() {
        tvFocusHouse.setText("订单支付");
        Batman.getInstance().fromNet(imgCover, imvCover);

        tvName.setText(title);

        showSelectTicket();

        if (selectTicket != null) {
            ncv.setDefaultNum(selectTicket.num);
            ncv.setMaxNum(selectTicket.maxNum);

            for (int i = 0; i < selectTicket.num; i++) {
                addOrders();
            }

        }
        ncv.setNumWitdh(UnitUtil.dip2px(this, 38));
        ncv.setImvAddResource(R.drawable.bg_nc_pay_add);
        ncv.setImvSubResource(R.drawable.bg_nc_pay_sub);
        ncv.setNumTextSize(12);
        ncv.setMinNum(1);
        ncv.setOnNumChangedListener(new NumCheckView.OnNumChangedListener() {
            @Override
            public void onNumChanged(int num) {
                selectTicket.num = num;
                showSelectTicket();
            }

            @Override
            public void isAdd(boolean isAdd) {
                if (isAdd) {
                    addOrders();
                } else {
                    reduceOrder();
                }
            }
        });
    }

    private void showSelectTicket() {
        if (selectTicket != null) {
            tvTicketSelectNum.setText(selectTicket.name + " x" + selectTicket.num);
            BigDecimal numB = new BigDecimal(selectTicket.num);
            BigDecimal numPrice = new BigDecimal(selectTicket.price);
            selectTicket.totalPrice = UnitUtil.formatSNum(numB.multiply(numPrice).toString());
            tvPriceBottom.setText("¥" + selectTicket.totalPrice);
        }
    }

    private void addOrders() {
        SignList orderInfo = new SignList();
        int childViewCount = llOrders.getChildCount();
        orderInfo.id = childViewCount + 1;
        OrderInfoView view = new OrderInfoView(this);
        view.showOrderInfo(orderInfo);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llOrders.addView(view, lp);
    }

    private void reduceOrder() {
        int childCount = llOrders.getChildCount();
        llOrders.removeViewAt(childCount - 1);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                payOrder();
                break;
        }
    }

    private boolean check() {
        mData.clear();
        boolean ret = true;
        for (int i = 0; i < llOrders.getChildCount(); i++) {
            View chidView = llOrders.getChildAt(i);
            if (chidView instanceof OrderInfoView) {
                SignList signList = ((OrderInfoView) chidView).getOrderInfo();
                if (TextViewUtil.isEmpty(signList.name) || TextViewUtil.isEmpty(signList.idCard) || TextViewUtil.isEmpty(signList.phone)) {
                    ToastUtil.showFalseToast(PayOrderActivity.this, "请填写完整用户信息");
                    ret = false;
                    break;
                }

                mData.add(signList);
            }
        }
        return ret;
    }

    private void payOrder() {
        if (check()) {
            OrderRequest request = new OrderRequest();
            request.id = id;
            request.ticketId = selectTicket.id;
//            request.totalAmount = selectTicket.totalPrice;
//            request.receiptAmount = selectTicket.totalPrice;
            //TODO 测试
            request.totalAmount = "0.01";
            request.receiptAmount = "0.01";
            request.signList = mData;
            request.number = ncv.getNum() + "";

            if (mPresenter == null) {
                mPresenter = new PayPresenter(this);
            }

            mPresenter.payOrder(request);
        }
    }

    @Override
    public void showPayOrderResult(PayOrderResponse response) {
        if (response != null && response.data != null) {
            if (mPresenter != null) {
                mPresenter.aliPay(response.data.orderInfo);
            }
        } else {
            goToPayResult(1, response.getErrMsg());
        }
    }

    /**
     * 支付之后
     *
     * @param isSuccess
     * @param orderNo
     */
    @Override
    public void showPayResult(boolean isSuccess, String orderNo) {
        if (isSuccess) {
            goToPayResult(0, null);
        }
    }

    @Override
    public void showPayOrderResultFail(Throwable e) {
        if (e != null && (e.getClass().getName().equals(UnknownHostException.class.getName())
                || e.getClass().getName().equals(SocketTimeoutException.class.getName())
                || e.getClass().getName().equals(ConnectException.class.getName()))) {
            ToastUtil.showFalseToast(this, "网络有问题呦～");
        } else {
            ToastUtil.showFalseToast(this, e != null ? e.getMessage() : "订单提交失败,请稍后重试");
        }
    }

    /**
     * 前往支付结果页
     *
     * @param type 0 成功 1失败
     */
    private void goToPayResult(int type, String errormsg) {
        Intent intent = new Intent(this, PaySuccessActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("errorMsg", errormsg);

        intent.putExtra("title", title);
        intent.putExtra("imgCover", imgCover);
        intent.putExtra("selectTicket", selectTicket);

        intent.putExtra("startTime", startTime);
        intent.putExtra("address", address);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }
        mPresenter = null;
    }
}
