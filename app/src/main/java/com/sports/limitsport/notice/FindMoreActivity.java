package com.sports.limitsport.notice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.notice.adapter.MyNoticeRecommendAdapter;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.NoticeViewHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/13.
 * 关注好友，发现更多
 */

public class FindMoreActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv_recommend)
    RecyclerView rlvRecommend;
    private MyNoticeRecommendAdapter adapterRecommend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_more);
        ButterKnife.bind(this);
        initView();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    private void initView() {
        tvFocusHouse.setText("关注好友");
        NoticeViewHeaderView headerView = new NoticeViewHeaderView(this);
        headerView.setCloseClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterRecommend.removeAllHeaderView();
            }
        });
        headerView.setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        headerView.setTip("发现更多的好友，去微信寻找小伙伴吧。");
        headerView.setTvRecommendVisible(View.VISIBLE);
        rlvRecommend.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterRecommend = new MyNoticeRecommendAdapter(MyTestData.getData());
        adapterRecommend.bindToRecyclerView(rlvRecommend);
        adapterRecommend.addHeaderView(headerView);
        adapterRecommend.disableLoadMoreIfNotFullPage();
        adapterRecommend.setEnableLoadMore(true);
        adapterRecommend.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
            }
        }, rlvRecommend);
//        rlAll.setEnableLoadMore(false);
    }
}
