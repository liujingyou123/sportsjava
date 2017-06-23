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
import com.sports.limitsport.activity.model.SignUpUser;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;

import java.util.ArrayList;
import java.util.List;

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
        List<SignUpUser> dataShai = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            SignUpUser shai = new SignUpUser();
            shai.imgUser = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg";
            dataShai.add(shai);
        }

        rlSignupList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SignUpAdapter(dataShai);
        rlSignupList.setAdapter(adapter);

//        SpacesItemVDecoration decoration = new SpacesItemVDecoration(100);
//        ryAll.addItemDecoration(decoration);

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

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
