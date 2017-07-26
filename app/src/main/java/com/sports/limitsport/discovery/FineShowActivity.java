package com.sports.limitsport.discovery;

import android.content.Intent;
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
import com.sports.limitsport.discovery.presenter.FineShowPresenter;
import com.sports.limitsport.discovery.ui.IFineShowView;
import com.sports.limitsport.mine.adapter.MyCollectFanShowAdapter;
import com.sports.limitsport.model.FineShowList;
import com.sports.limitsport.model.FineShowListResponse;
import com.sports.limitsport.util.MyTestData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 * 精彩秀列表
 */

public class FineShowActivity extends BaseActivity implements IFineShowView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rclv)
    RecyclerView rclv;
    private MyCollectFanShowAdapter adapter;
    private FineShowPresenter mPresenter;
    private List<FineShowList> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fineshow);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvFocusHouse.setText("精彩秀");
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
        rclv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new MyCollectFanShowAdapter(data);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(FineShowActivity.this, FineShowDetailActivity.class);
                startActivity(intent);
            }
        });
        adapter.bindToRecyclerView(rclv);

        adapter.setEmptyView(emptyView);

    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showFineShow(FineShowListResponse response) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }

        mPresenter = null;
    }
}
