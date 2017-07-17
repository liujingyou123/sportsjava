package com.sports.limitsport.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.SignUpAdapter;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.util.MyTestData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 报名列表
 */

public class SignUpListActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_signup_list)
    RecyclerView rlSignupList;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;

    private SignUpAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuplist);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvFocusHouse.setText("报名列表");
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        initRy();
    }

    private void initRy() {
        rlSignupList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SignUpAdapter(MyTestData.getData());
        rlSignupList.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                XLog.e("onItemChildClick");
                view.setEnabled(false);
                if (view instanceof TextView) {
                    ((TextView) view).setText("互相关注");
                }
            }
        });
    }


    @OnClick({R.id.imv_focus_house_back, R.id.imv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_share:
                ShareDialog dialog = new ShareDialog(this);
                dialog.show();
                break;
        }
    }
}
