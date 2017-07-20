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
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.StatusBarUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.ActivityDetailHeaderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ActivityDetailActivity extends BaseActivity {

    @BindView(R.id.rlv_discuss)
    RecyclerView rlvDiscuss;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
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
        setViewType(0);
        rlvDiscuss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ActivityDiscussAdapter(MyTestData.getData());
        adapter.addHeaderView(activityDetailHeaderView);
        adapter.setHeaderAndEmpty(true);
        adapter.bindToRecyclerView(rlvDiscuss);
        adapter.setEmptyView(R.layout.empty_discuss);

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
        } else {
            rlTitle.setBackgroundColor(Color.parseColor("#00000000"));
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


    @OnClick({R.id.imv_back, R.id.btn_done, R.id.imv_report, R.id.imv_share, R.id.imv_fav})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
                finish();
                break;
            case R.id.btn_done:
                showAlreadySignUpDialog();

                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this);
                dialog.show();
                break;
            case R.id.imv_share:
                ShareDialog dialog1 = new ShareDialog(this);
                dialog1.show();
                break;
            case R.id.imv_fav:
                ToastUtil.showTrueToast(this, "收藏成功");
                break;
        }
    }

    /**
     * 您已报名，还需要添加小伙伴
     */
    private void showAlreadySignUpDialog() {
        NoticeDelDialog dialog = new NoticeDelDialog(this);
        dialog.setMessage("您已报名，还需要添加小伙伴吗？");
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                Intent intentSign = new Intent(ActivityDetailActivity.this, SignUpActivity.class);
                startActivity(intentSign);
            }
        });
        dialog.show();
    }
}