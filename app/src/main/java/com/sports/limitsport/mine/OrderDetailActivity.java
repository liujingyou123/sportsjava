package com.sports.limitsport.mine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.PaySuccessActivity;
import com.sports.limitsport.activity.presenter.PayPresenter;
import com.sports.limitsport.activity.ui.IPayOrderView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.dialog.RefundTipDialog;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.main.MainActivity;
import com.sports.limitsport.mine.presenter.OrderDetailPresenter;
import com.sports.limitsport.mine.ui.IOrderDetailView;
import com.sports.limitsport.model.EventBusOrder;
import com.sports.limitsport.model.OrderDetail;
import com.sports.limitsport.model.OrderDetailResponse;
import com.sports.limitsport.model.PayOrderResponse;
import com.sports.limitsport.model.SelectTicket;
import com.sports.limitsport.model.SignList;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.OrderInfoView;
import com.sports.limitsport.wxapi.PayResultEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by liuworkmac on 17/7/7.
 * 订单详情
 */

public class OrderDetailActivity extends BaseActivity implements IOrderDetailView, IPayOrderView {
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
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_ordernum)
    TextView tvOrdernum;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_ticket_price)
    TextView tvTicketPrice;
    @BindView(R.id.tv_ticket_num)
    TextView tvTicketNum;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_all_tip)
    TextView tvAllTip;
    @BindView(R.id.tv_ticket_nums)
    TextView tvTicketNums;
    @BindView(R.id.tv_zhifubao)
    TextView tvZhifubao;
    @BindView(R.id.zhifubao)
    RadioButton zhifubao;
    @BindView(R.id.weixin)
    RadioButton weixin;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_all_price)
    TextView tvAllPrice;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;

    //    private String type; // 1: 待付款, 2:已报名（可退款）3:已参加 4:已退款 5: 已取消
    private OrderDetailPresenter mPresenter;
    private PayPresenter mPayPresenter;
    private String orderNo;
    //    private int isJoin;
    private OrderDetail orderDetail;
    private RefundTipDialog dialog;
    private String from;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);
        getIntentData();
        initView();
        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            orderNo = intent.getStringExtra("orderNo");
//            type = intent.getStringExtra("type");
//            isJoin = intent.getIntExtra("isJoin", 0);
            from = intent.getStringExtra("from");

        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new OrderDetailPresenter(this);
        }
        mPresenter.getOrderDetail(orderNo);
    }

    private void initView() {
        tvFocusHouse.setText("订单详情");
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_cancel, R.id.tv_pay, R.id.tv_showDong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                if ("outer".equals(from)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
                break;
            case R.id.tv_cancel:
                showCancelDialog();
                break;
            case R.id.tv_pay:
                if (orderDetail != null) {
                    if (mPayPresenter == null) {
                        mPayPresenter = new PayPresenter(this);
                    }
                    mPayPresenter.pay(orderDetail.getOrderInfo(), orderDetail.getPayType());
                }

                break;
            case R.id.tv_showDong:
                if (orderDetail != null) {
                    if (orderDetail.getIsJoin() == 1) {
                        gotoEditDongTai();
                    } else {
                        showRefundTip();
                    }
                }

                break;
        }
    }

    private void showRefundTip() {
        if (dialog == null) {
            dialog = new RefundTipDialog(this);
            dialog.setDoneClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("1".equals(orderDetail.getOrderStatus())) {
                        if (mPresenter != null) {
                            mPresenter.reFundOrder(orderNo);
                        }
                    }
                }
            });
        }

        if (orderDetail != null) {
            dialog.setPhoneText(orderDetail.getClubPhone());
        }
        if (!dialog.isShowing()) {
            dialog.show();
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

        if ("aliPay".equals(orderDetail.getPayType())) {
            tvZhifubao.setVisibility(View.VISIBLE);
            zhifubao.setVisibility(View.VISIBLE);
            zhifubao.setChecked(true);
            tvWeixin.setVisibility(View.GONE);
            weixin.setVisibility(View.GONE);
        } else {
            tvZhifubao.setVisibility(View.GONE);
            zhifubao.setVisibility(View.GONE);
            tvWeixin.setVisibility(View.VISIBLE);
            weixin.setVisibility(View.VISIBLE);
            weixin.setChecked(true);
        }
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


    /**
     * 已关闭
     */
    private void showOrderClosed() {
        tvOrderStatus.setText("已关闭");
        tvShowDong.setVisibility(View.GONE);
        llOrderPay.setVisibility(View.GONE);
        tvNotice.setVisibility(View.VISIBLE);
        tvPayTime.setVisibility(View.GONE);
        tvPayType.setVisibility(View.GONE);
    }

    /**
     * 退款中
     */
    private void refunding() {
        tvOrderStatus.setText("退款中");
        tvShowDong.setVisibility(View.GONE);
        llOrderPay.setVisibility(View.GONE);
        tvNotice.setVisibility(View.VISIBLE);
        tvPayTime.setVisibility(View.GONE);
        tvPayType.setVisibility(View.GONE);
    }

    private void showOrders(String name, String idCard, String phone) {
        SignList orderInfo = new SignList();
        int childViewCount = llOrders.getChildCount();
        orderInfo.id = childViewCount + 1;
        orderInfo.name = name;
        orderInfo.idCard = idCard;
        orderInfo.phone = phone;
        OrderInfoView view = new OrderInfoView(this);
        view.showOrderInfo(orderInfo, false);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llOrders.addView(view, lp);

    }

    private void showCancelDialog() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setMessage("您确定要取消订单吗？");
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                if (mPresenter != null) {
                    mPresenter.cancelOrder(orderNo);
                }
            }
        });
        dialog.show();
    }

    /**
     * 前往发动态
     */
    private void gotoEditDongTai() {
        if (SharedPrefsUtil.getUserInfo() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("type", "2");
            startActivity(intent);
            return;
        } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
            Intent intent = new Intent(this, IdentifyMainActivity.class);
            intent.putExtra("type", "2");
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, EditNewDongTaiActivity.class);
            startActivity(intent);
        }
    }

    public void timeCountDown(String timeStr) {
        if (TextViewUtil.isEmpty(timeStr)) {
            return;
        }
        long time;
        try {
            time = Long.parseLong(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (time < 0) time = 0;
        final long countTime = time;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take((int) (countTime + 1))
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (orderDetail != null) {
                            orderDetail.setLaveSecond(aLong.toString());
                        }
                        tvPay.setText("支付 " + UnitUtil.secondsToTimeString(aLong));
                    }
                });

    }

    @Override
    public void showOrderDetail(OrderDetailResponse response) {
        orderDetail = response.getData();
        if (orderDetail != null) {
            llContent.setVisibility(View.VISIBLE);
            Batman.getInstance().fromNet(orderDetail.getCoverUrl(), imvCover, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default);
            tvOrdernum.setText("订单编号：" + orderNo);
            tvOrderTime.setText("下单时间：" + orderDetail.getCreateOrderTime());
            tvPayTime.setText("支付时间：" + orderDetail.getPayTime());
            tvName.setText(orderDetail.getName());
            tvTime.setText(orderDetail.getStartDate() + " " + UnitUtil.stringToWeek(orderDetail.getWeek()) + " " + orderDetail.getStartTime());
            tvAddress.setText(orderDetail.getAddress());
            tvTicketPrice.setText("¥" + UnitUtil.formatSNum(orderDetail.getMoneyNews()) + " " + orderDetail.getTicketsName());
            tvTicketNum.setText("x" + orderDetail.getNumber());
            tvAllPrice.setText("¥" + orderDetail.getTotalMoney());

            timeCountDown(orderDetail.getLaveSecond());
            if (orderDetail.getIsJoin() == 1) { //已参加
                showPlayed();
            } else {
                if ("0".equals(orderDetail.getOrderStatus())) {
                    showPrePayOrder();
                } else if ("1".equals(orderDetail.getOrderStatus())) {
                    showPayOrder();
                } else if ("2".equals(orderDetail.getOrderStatus())) {
                    showOrderClosed();
                } else if ("3".equals(orderDetail.getOrderStatus())) {
                    showCancle();
                } else if ("4".equals(orderDetail.getOrderStatus())) {
                    refunding();
                } else if ("5".equals(orderDetail.getOrderStatus())) {
                    showPayBack();
                }
            }

            List<OrderDetail.ApplicantListBean> listBeen = orderDetail.getApplicantList();
            if (listBeen != null) {
                for (int i = 0; i < listBeen.size(); i++) {
                    OrderDetail.ApplicantListBean applicantListBean = listBeen.get(i);
                    if (applicantListBean != null) {
                        showOrders(applicantListBean.getName(), applicantListBean.getIdCard(), applicantListBean.getPhone());
                    }
                }
            }
        }

    }

    @Override
    public void cancelOrder(boolean success) {
        if (success) {
            orderDetail.setOrderStatus("3");
            showCancle();
            EventBusOrder params = new EventBusOrder();
            params.isChange = true;
            EventBus.getDefault().post(params);
        } else {
            ToastUtil.showFalseToast(this, "取消订单失败");
        }
    }

    @Override
    public void refundOrderResult(boolean success, String errorMsg) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (success) {
            orderDetail.setOrderStatus("4");
            refunding();
            EventBusOrder params = new EventBusOrder();
            params.isChange = true;
            EventBus.getDefault().post(params);
        } else {
            ToastUtil.showFalseToast(this, errorMsg);
        }
    }

    @Override
    public void showPayOrderResult(PayOrderResponse response) {

    }

    @Override
    public void showPayResult(boolean isSuccess, String OrderNo) {
        if (isSuccess) {
            orderDetail.setOrderStatus("1");
            showPayOrder();
            goToPayResult(0, null);
            EventBusOrder params = new EventBusOrder();
            params.isChange = true;
            EventBus.getDefault().post(params);
        }
    }

    @Override
    public void showPayOrderResultFail(Throwable e) {

    }

    /**
     * 微信支付结果
     */
    @Subscribe
    public void payResultEvent(PayResultEvent event) {
        switch (event.resultCode) {
            case 0:
                Observable.timer(1500, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        orderDetail.setOrderStatus("1");
                        showPayOrder();
                        goToPayResult(0, null);
                        EventBusOrder params = new EventBusOrder();
                        params.isChange = true;
                        EventBus.getDefault().post(params);
                    }
                });
                break;
            case -1:
                break;
            case -2:
                ToastUtil.show(this, "取消支付");
                break;
        }
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

        intent.putExtra("title", orderDetail.getName());
        intent.putExtra("imgCover", orderDetail.getCoverUrl());
        SelectTicket selectTicket = new SelectTicket();
        selectTicket.num = orderDetail.getNumber();
        selectTicket.name = orderDetail.getTicketsName();
        selectTicket.totalPrice = orderDetail.getTotalMoney();
        intent.putExtra("selectTicket", selectTicket);
        String startTime = orderDetail.getStartDate() + " " + UnitUtil.stringToWeek(orderDetail.getWeek()) + " " + orderDetail.getStartTime();
        intent.putExtra("startTime", startTime);
        intent.putExtra("address", orderDetail.getAddress());
        intent.putExtra("from", 2);
        intent.putExtra("orderNo", orderNo);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.clear();
        }
        mPresenter = null;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;

    }

}
