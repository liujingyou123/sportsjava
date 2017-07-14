package com.sports.limitsport.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.NamesAdapter;
import com.sports.limitsport.activity.adapter.ShallAdapter;
import com.sports.limitsport.activity.adapter.TagDetailAdapter;
import com.sports.limitsport.activity.model.Shai;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.StatusBarUtil;
import com.sports.limitsport.view.ActivityDetailHeaderView;
import com.sports.limitsport.view.PositionScrollView;
import com.sports.limitsport.view.SpacesItemHDecoration;
import com.sports.limitsport.view.tagview.TagCloudLayout;
import com.sports.limitsport.view.video.JCVideoPlayerStandardShowShareButtonAfterFullscreen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ActivityDetailActivity extends BaseActivity {

    private ActivityDetailHeaderView activityDetailHeaderView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        init();
        setViewType(1);
        StatusBarUtil.setTranslucent(this);
    }


    private void init() {
        activityDetailHeaderView = new ActivityDetailHeaderView(this);
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


    @OnClick({R.id.imv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
                finish();
        }
    }
}