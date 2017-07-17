package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ActivityDiscussAdapter;
import com.sports.limitsport.activity.adapter.TicketsTypeAdapter;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.base.SelectEntity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.H5Activity;
import com.sports.limitsport.view.SignUpFooterView;
import com.sports.limitsport.view.SignUpHeadView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 活动报名
 */

public class SignUpActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv_ticket_type)
    RecyclerView rlvTicketType;

    @BindView(R.id.imv_server)
    ImageView imvServer;
    @BindView(R.id.tv_server)
    TextView tvServer;
    private TicketsTypeAdapter adapter;
    private SignUpHeadView signUpHeadView;
    private SignUpFooterView signUpFooterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvFocusHouse.setText("活动报名");

        signUpHeadView = new SignUpHeadView(this);
        signUpFooterView = new SignUpFooterView(this);
        rlvTicketType.setLayoutManager(new GridLayoutManager(this, 2));

        List<SelectEntity> data = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SelectEntity selectEntity = new SelectEntity();
            data.add(selectEntity);
        }
        adapter = new TicketsTypeAdapter(data);
        adapter.bindToRecyclerView(rlvTicketType);
        adapter.setHeaderAndEmpty(true);
        adapter.setHeaderView(signUpHeadView);
        adapter.setFooterView(signUpFooterView);
        adapter.setOnItemSelectClickListener(new BaseSelectionAdapter.OnItemSelectClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                XLog.e("select position = " + position);
            }
        });
        imvServer.setSelected(true);


    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_done, R.id.imv_server, R.id.tv_server})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_done:
                Intent intent = new Intent(this, PayOrderActivity.class);
                startActivity(intent);
                break;
            case R.id.imv_server:
                if (imvServer.isSelected()) {
                    imvServer.setSelected(false);
                } else {
                    imvServer.setSelected(true);
                }
                break;
            case R.id.tv_server:
                gotoServerDeal();
                break;
        }
    }

    /**
     * 服务协议
     */
    private void gotoServerDeal() {
        Intent intent = new Intent(this, H5Activity.class);
        intent.putExtra("type", 1);
        intent.putExtra("url", "https://www.baidu.com");
        startActivity(intent);
    }
}
