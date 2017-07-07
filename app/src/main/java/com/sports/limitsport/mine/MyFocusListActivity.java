package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.mine.adapter.MyFocusAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/6.
 */

public class MyFocusListActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rv_focus)
    RecyclerView rvFocus;
    private MyFocusAdapter adapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfocus);
        ButterKnife.bind(this);
        testData();
        initView();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    private void initView() {
        tvFocusHouse.setText("我的关注");
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_commentlist, null);
        emptyView.findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rvFocus.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyFocusAdapter(data);
        adapter.bindToRecyclerView(rvFocus);

        adapter.setEmptyView(emptyView);


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

    private void testData() {
        for (int i = 0; i < 15; i++) {
            data.add(""+i);
        }
    }
}
