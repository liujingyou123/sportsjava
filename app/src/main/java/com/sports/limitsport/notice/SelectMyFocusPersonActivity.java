package com.sports.limitsport.notice;

import android.content.Intent;
import android.graphics.Color;
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
import com.sports.limitsport.notice.adapter.MyFocusPersonSelectAdapter;
import com.sports.limitsport.notice.model.FocusPerson;
import com.sports.limitsport.util.MyTestData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/14.
 * 我关注的人选择
 */

public class SelectMyFocusPersonActivity extends BaseActivity {
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv)
    RecyclerView rlv;

    private MyFocusPersonSelectAdapter adapter;
    private List<FocusPerson> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmyfocus);
        ButterKnife.bind(this);
        getTestData();
        initView();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                Intent intent = new Intent();
                intent.putExtra("name", "ricky");
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

    private void initView() {
        tvFocusHouse.setText("我的关注的人");
        tvFocusRight.setVisibility(View.VISIBLE);
        tvFocusRight.setText("完成");
        tvFocusRight.setTextColor(Color.parseColor("#ffffff"));
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        TextView emptyText = (TextView) emptyView.findViewById(R.id.tv_empty);
        emptyText.setText("还没有关注的人哦～");
        rlv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyFocusPersonSelectAdapter(data);
        adapter.bindToRecyclerView(rlv);

        adapter.setEmptyView(emptyView);
    }

    private void getTestData() {
        for (int i = 0; i < 20; i++) {
            FocusPerson focusPerson = new FocusPerson();
            data.add(focusPerson);
        }
    }

}
