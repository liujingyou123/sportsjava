package com.sports.limitsport.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ActivityDiscussAdapter;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.StatusBarUtil;
import com.sports.limitsport.view.ActivityDetailHeaderView;
import com.sports.limitsport.view.PositionScrollView;
import com.sports.limitsport.view.PostionRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ActivityDetailActivity extends BaseActivity {

    @BindView(R.id.rlv_discuss)
    PostionRecyclerView rlvDiscuss;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ActivityDetailHeaderView activityDetailHeaderView;
    private ActivityDiscussAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init();
        StatusBarUtil.setTranslucent(this);
    }


    private void init() {
        activityDetailHeaderView = new ActivityDetailHeaderView(this);
        setViewType(1);
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_text, null);
        rlvDiscuss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ActivityDiscussAdapter(MyTestData.getData());
        adapter.addHeaderView(activityDetailHeaderView);
        adapter.setHeaderAndEmpty(true);
        adapter.bindToRecyclerView(rlvDiscuss);
//        ryMine.setAdapter(mineAdapter);
        adapter.setEmptyView(emptyView);

//        rlvDiscuss.scrollToPosition(0);

        rlvDiscuss.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                setColor();
            }
        });
    }

    private void setColor() {
        int[] location = new int[2];
        activityDetailHeaderView.getLocationInWindow(location);

        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);


        if (location[1] < statusBarHeight) {
            rlTitle.setBackgroundColor(Color.parseColor("#000000"));
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            rlTitle.setBackgroundColor(Color.parseColor("#00000000"));
            tvTitle.setVisibility(View.GONE);
        }
    }


    /**
     * type: 0 video 1:图片
     */
    public void setViewType(int type) {
        if (activityDetailHeaderView != null) {
            activityDetailHeaderView.setViewType(type);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @OnClick({R.id.imv_back, R.id.btn_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
                finish();
                break;
            case R.id.btn_done:
                Intent intentSign = new Intent(this, SignUpActivity.class);
                startActivity(intentSign);
                break;
        }
    }
}