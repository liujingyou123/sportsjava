package com.sports.limitsport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticelist);
        ButterKnife.bind(this);
        tvFocusHouse.setText("活动通知");
        testData();
        initView();
    }

    private void initView() {
        ryNotices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new NoticeAdapter(mData);
        ryNotices.setAdapter(adapter);
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
