package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.mine.adapter.MyClubsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/7.
 * 我参加的俱乐部
 */

public class MyClubsActivity extends BaseActivity {
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    private MyClubsAdapter adapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclubs);
        ButterKnife.bind(this);
        testData();
        initView();
    }

    private void testData() {
        for (int i = 0; i < 10; i++) {
            data.add("" + i);
        }
    }

    private void initView() {
        tvFocusHouse.setText("我参加的俱乐部");
        tvFocusRight.setText("创建");
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_myclub, null);
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_commentlist, null);
        TextView tvTip = (TextView) emptyView.findViewById(R.id.tv_empty);
        TextView tvGo = (TextView) emptyView.findViewById(R.id.tv_go);
        tvTip.setText("好像什么都没有～");
        tvGo.setText("去逛逛");
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyClubsAdapter(data);
        adapter.bindToRecyclerView(rlv);
        adapter.addHeaderView(headerView);
        adapter.setEmptyView(emptyView);

    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                break;
        }
    }
}
