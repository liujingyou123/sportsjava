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
import com.sports.limitsport.main.MainActivity;
import com.sports.limitsport.model.PayFinishResponse;

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

    private String id; //活动ID
    private int type;
    private String errorMsg;
    private PaySuccessPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccess);
        ButterKnife.bind(this);
        getIntentData();
        initView();

//        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            type = intent.getIntExtra("type", 0);
            errorMsg = intent.getStringExtra("errorMsg");
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
        } else if (type == 1) {
            tvFocusHouse.setText("支付失败");
            tvTitle.setText("哎呀！购票失败！");
            tvTitle.setEnabled(false);
            tvSubTitle.setText(errorMsg);
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_back, R.id.tv_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_look:
                break;
        }
    }

    @Override
    public void showPayResult(PayFinishResponse response) {

    }
}
