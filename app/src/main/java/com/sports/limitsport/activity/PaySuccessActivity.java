package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.presenter.PaySuccessPresenter;
import com.sports.limitsport.activity.ui.IPaySuccessView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.main.MainActivity;
import com.sports.limitsport.mine.OrderDetailActivity;
import com.sports.limitsport.model.PayFinishResponse;
import com.sports.limitsport.model.SelectTicket;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/27.
 */

public class PaySuccessActivity extends BaseActivity implements IPaySuccessView {
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.imv_cover)
    ImageView imvCover;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_tickets)
    TextView tvTickets;
    @BindView(R.id.tv_tickets_num)
    TextView tvTicketsNum;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sub_title)
    TextView tvSubTitle;
    @BindView(R.id.tv_look)
    TextView tvLook;
    private String id; //活动ID
    private String title;//活动title
    private String imgCover;//活动封面
    private String startTime; //活动时间
    private String address; // 地址
    private int from;//来源：（1:从订单列表页 2:详情页进入，0:从支付页进入）
    private int type;
    private String errorMsg;
    private PaySuccessPresenter mPresenter;
    private SelectTicket selectTicket;
    private String orderNo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccess);
        ButterKnife.bind(this);
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            type = intent.getIntExtra("type", 0);
            errorMsg = intent.getStringExtra("errorMsg");
            title = intent.getStringExtra("title");
            imgCover = intent.getStringExtra("imgCover");
            selectTicket = (SelectTicket) intent.getSerializableExtra("selectTicket");
            startTime = intent.getStringExtra("startTime");
            address = intent.getStringExtra("address");
            orderNo = intent.getStringExtra("orderNo");
            from = intent.getIntExtra("from", 0);
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new PaySuccessPresenter(this);
        }

    }

    private void initView() {
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        doResult();
    }

    /**
     * 0:成功 1:失败
     */
    private void doResult() {
        if (type == 0) { // 成功
            tvFocusHouse.setText("支付成功");
            tvTitle.setText("恭喜您！购票成功！");
            tvTitle.setEnabled(true);
            tvSubTitle.setText("请注意活动时间以免错过活动");
            tvLook.setVisibility(View.VISIBLE);
        } else if (type == 1) {
            tvFocusHouse.setText("支付失败");
            tvTitle.setText("哎呀！购票失败！");
            tvTitle.setEnabled(false);
            tvSubTitle.setText(errorMsg);
            tvLook.setVisibility(View.GONE);
        }

        Batman.getInstance().fromNet(imgCover, imvCover);
        tvName.setText(title);
        tvTime.setText(startTime);
        tvAddress.setText(address);
        tvTickets.setText(selectTicket.totalPrice + " " + selectTicket.name);
        tvTicketsNum.setText("x" + selectTicket.num);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_back, R.id.tv_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                if (from == 1 || from == 2) {
                    finish();
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_look:
                gotoOrderDetail();
                break;
        }
    }

    @Override
    public void showPayResult(PayFinishResponse response) {

    }

    private void gotoOrderDetail() {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("orderNo", orderNo);
        startActivity(intent);
        if (type == 1) {
            this.finish();
        }
    }
}
