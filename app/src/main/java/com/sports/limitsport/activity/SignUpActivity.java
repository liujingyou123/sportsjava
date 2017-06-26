package com.sports.limitsport.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 活动报名
 */

public class SignUpActivity extends BaseActivity {
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvFocusHouse.setText("活动报名");
        imvFocusHouseBack.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_house})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_house:
                break;
        }
    }
}
