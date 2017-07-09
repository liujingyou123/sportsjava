package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.main.IdentifyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/6.
 * 设置
 */

public class SetActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        tvFocusHouse.setText("设置");
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                exitApp();
                break;
        }
    }

    private void exitApp() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                test();
            }
        });
        dialog.show();
    }

    private void test() {
        Intent intent = new Intent(this, IdentifyActivity.class);
        startActivity(intent);
    }
}
