package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/27.
 */

public class PaySuccessActivity extends BaseActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccess);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        tvFocusHouse.setText("支付成功");
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
}
