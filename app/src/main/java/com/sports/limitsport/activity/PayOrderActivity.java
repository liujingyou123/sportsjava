package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.model.OrderInfo;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.NumCheckView;
import com.sports.limitsport.view.OrderInfoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/27.
 */

public class PayOrderActivity extends BaseActivity {

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


    private List<OrderInfo> mData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payorder);
        ButterKnife.bind(this);
        initRecy();
    }

    private void initRecy() {
        tvFocusHouse.setText("订单支付");
        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imvCover);

        ncv.setNumWitdh(UnitUtil.dip2px(this, 38));
        ncv.setImvAddResource(R.drawable.bg_nc_pay_add);
        ncv.setImvSubResource(R.drawable.bg_nc_pay_sub);
        ncv.setNumTextSize(12);
        ncv.setMinNum(1);
        ncv.setOnNumChangedListener(new NumCheckView.OnNumChangedListener() {
            @Override
            public void onNumChanged(int num) {

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

    private void addOrders() {
        OrderInfo orderInfo = new OrderInfo();
        int childViewCount = llOrders.getChildCount();
        orderInfo.id = childViewCount + 1;
        OrderInfoView view = new OrderInfoView(this);
        view.showOrderInfo(orderInfo);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llOrders.addView(view, lp);

        mData.add(orderInfo);
    }

    private void reduceOrder() {
        int childCount = llOrders.getChildCount();
        mData.remove(childCount - 1);
        llOrders.removeViewAt(childCount - 1);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                Intent intent = new Intent(this, PaySuccessActivity.class);
                startActivity(intent);
                break;
        }
    }


}
