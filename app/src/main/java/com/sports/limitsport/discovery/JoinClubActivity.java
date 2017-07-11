package com.sports.limitsport.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.mine.adapter.MyClubsAdapter;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.SpacesItemTopDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 参加的俱乐部
 */

public class JoinClubActivity extends BaseActivity {

    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    private MyClubsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinclub);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvFocusHouse.setText("我参加的俱乐部");
        rlv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new MyClubsAdapter(MyTestData.getData());
        adapter.bindToRecyclerView(rlv);

        SpacesItemTopDecoration decoration = new SpacesItemTopDecoration(60);
        rlv.addItemDecoration(decoration);

    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }
}
