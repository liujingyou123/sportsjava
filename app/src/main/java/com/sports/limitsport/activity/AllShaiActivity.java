package com.sports.limitsport.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.AllShaiAdapter;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.notice.EditNewDongTaiActivity;
import com.sports.limitsport.util.MyTestData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/23.
 * 大家都在晒
 */

public class AllShaiActivity extends BaseActivity {
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ry_all)
    RecyclerView ryAll;
    private AllShaiAdapter allShaiAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allshai);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        imvFocusHouseBack.setVisibility(View.VISIBLE);
        tvFocusHouse.setText("大家都在晒");
        initRy();
    }

    private void initRy() {
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_with_btn_full, null);
        emptyView.findViewById(R.id.tv_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoEditDongtai();
            }
        });
        ryAll.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        allShaiAdapter = new AllShaiAdapter(MyTestData.getData());
        allShaiAdapter.bindToRecyclerView(ryAll);
        allShaiAdapter.setEmptyView(emptyView);
        allShaiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                gotoDongtaiDetail();
            }
        });

    }

    /**
     * 前往动态详情页
     */
    private void gotoDongtaiDetail() {
        Intent intent = new Intent(this, TongTaiDetailActivity.class);
        startActivity(intent);
    }

    /**
     * 前往编辑动态页面
     */
    private void gotoEditDongtai() {
        Intent intent = new Intent(this, EditNewDongTaiActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.imv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.imv_right:
                gotoEditDongtai();
                break;
        }
    }
}
