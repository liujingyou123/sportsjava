package com.sports.limitsport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.mine.adapter.NoticeAdapter;
import com.sports.limitsport.mine.model.NoticeTwo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class NoticeListActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ry_notices)
    RecyclerView ryNotices;
    private NoticeAdapter adapter;
    private List<NoticeTwo> mData = new ArrayList<>();
    private int type; //1:活动通知： 2:系统通知

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticelist);
        ButterKnife.bind(this);
        getInitData();
        testData();
        initView();
    }

    private void getInitData() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", 0);
        }

    }

    private void initView() {
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        TextView textView = (TextView) emptyView.findViewById(R.id.tv_empty);
        if (type ==1) {
            tvFocusHouse.setText("活动通知");
            textView.setText("还没有收到过活动通知哦～");
        } else if (type ==2) {
            tvFocusHouse.setText("系统通知");
            textView.setText("还没有收到过系统通知哦～");
        }
        ryNotices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new NoticeAdapter(mData);
        adapter.bindToRecyclerView(ryNotices);

        adapter.setEmptyView(R.layout.empty_noticelist);
    }

    private void testData() {
        for (int i = 0; i < 5; i++) {
            NoticeTwo tmp = new NoticeTwo();
            mData.add(tmp);
        }
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
