package com.sports.limitsport.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.presenter.OrderDetailPresenter;
import com.sports.limitsport.mine.ui.IOrderDetailView;
import com.sports.limitsport.model.OrderDetailResponse;
import com.sports.limitsport.model.SignList;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.OrderInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/7.
 * 订单详情
 */

public class OrderDetailActivity extends BaseActivity implements IOrderDetailView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ll_orders)
    LinearLayout llOrders;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_showDong)
    TextView tvShowDong;
    @BindView(R.id.ll_order_pay)
    LinearLayout llOrderPay;
    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.imv_cover)
    ImageView imvCover;

    private String type; // 1: 待付款, 2:已报名（可退款）3:已参加 4:已退款 5: 已取消
    private OrderDetailPresenter mPresenter;
    private String orderId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        ButterKnife.bind(this);
        getIntentData();
        initView();
        for (int i = 0; i < 5; i++) {
            showOrders();
        }

        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getStringExtra("orderId");
            type = intent.getStringExtra("type");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new OrderDetailPresenter(this);
        }

        mPresenter.getOrderDetail(orderId);
    }

    private void initView() {
        tvFocusHouse.setText("订单详情");


        showStatusView();

        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imvCover);

    }

    private void showOrders() {
        SignList orderInfo = new SignList();
        int childViewCount = llOrders.getChildCount();
        orderInfo.id = childViewCount + 1;
        orderInfo.name = "中国";
        orderInfo.idCard = " 410311199001025648";
        orderInfo.phone = "18798769870";
        OrderInfoView view = new OrderInfoView(this);
        view.showOrderInfo(orderInfo, false);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llOrders.addView(view, lp);

    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_cancel, R.id.tv_pay, R.id.tv_showDong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_cancel:
                showCancelDialog();
                break;
            case R.id.tv_pay:
                break;
            case R.id.tv_showDong:
                if ("2".equals(type)) {
                    if (mPresenter != null) {
                        mPresenter.reFundOrder(orderId);
                    }
                } else if ("3".equals(type)) {
                    gotoEditDongTai();
                }
                break;
        }
    }

    private void showStatusView() {
        if (TextViewUtil.isEmpty(type)) {
            return;
        }
        if ("1".equals(type)) {
            showPrePayOrder();
        } else if ("2".equals(type)) {
            showPayOrder();
        } else if ("3".equals(type)) {
            showPlayed();
        } else if ("4".equals(type)) {
            showPayBack();
        } else if ("5".equals(type)) {
            showCancle();
        }
    }

    /**
     * 待付款
     */
    private void showPrePayOrder() {
        tvOrderStatus.setText("待付款");
        tvShowDong.setVisibility(View.GONE);
        llOrderPay.setVisibility(View.VISIBLE);
        tvNotice.setVisibility(View.GONE);
        tvPayTime.setVisibility(View.GONE);
        tvPayType.setVisibility(View.GONE);
    }

    /**
     * 已报名（可退款，已付款）
     */
    private void showPayOrder() {
        tvOrderStatus.setText("已报名");
        tvShowDong.setVisibility(View.VISIBLE);
        tvShowDong.setText(" 退款 ");
        tvShowDong.setTextColor(Color.parseColor("#FFA5A4A4"));
        tvShowDong.setBackgroundResource(R.drawable.bg_btn_gray);
        llOrderPay.setVisibility(View.GONE);
        tvNotice.setVisibility(View.VISIBLE);
        tvPayTime.setVisibility(View.VISIBLE);
        tvPayType.setVisibility(View.VISIBLE);
    }

    /**
     * 已参加
     */
    private void showPlayed() {
        tvOrderStatus.setText("已参加");
        tvShowDong.setVisibility(View.VISIBLE);
        tvShowDong.setText("秀动态");
        tvShowDong.setTextColor(Color.parseColor("#FFFFFFFF"));
        tvShowDong.setBackgroundResource(R.drawable.bg_done);
        llOrderPay.setVisibility(View.GONE);
        tvNotice.setVisibility(View.VISIBLE);
        tvPayTime.setVisibility(View.VISIBLE);
        tvPayType.setVisibility(View.VISIBLE);
    }

    /**
     * 已退款
     */
    private void showPayBack() {
        tvOrderStatus.setText("已退款");
        tvShowDong.setVisibility(View.GONE);
        llOrderPay.setVisibility(View.GONE);
        tvNotice.setVisibility(View.VISIBLE);
        tvPayTime.setVisibility(View.VISIBLE);
        tvPayType.setVisibility(View.VISIBLE);
    }

    /**
     * 已取消
     */
    private void showCancle() {
        tvOrderStatus.setText("已取消");
        tvShowDong.setVisibility(View.GONE);
        llOrderPay.setVisibility(View.GONE);
        tvNotice.setVisibility(View.VISIBLE);
        tvPayTime.setVisibility(View.GONE);
        tvPayType.setVisibility(View.GONE);
    }

    private void showCancelDialog() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setMessage("您确定要取消订单吗？");
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                if (mPresenter != null) {
                    mPresenter.cancelOrder(orderId);
                }
            }
        });
        dialog.show();
    }

    /**
     * 前往发动态
     */
    private void gotoEditDongTai() {
        Intent intent = new Intent(this, EditNewDongTaiActivity.class);
        startActivity(intent);
    }

    @Override
    public void showOrderDetail(OrderDetailResponse response) {

    }

    @Override
    public void cancelOrder(boolean success) {
        if (success) {
            //TODO 更新订单状态
        } else {
            ToastUtil.showFalseToast(this, "取消订单失败");
        }
    }

    @Override
    public void refundOrderResult(boolean success) {
        if (success) {
//TODO 更新订单状态
        } else {
            ToastUtil.showFalseToast(this, "退款失败");
        }
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
