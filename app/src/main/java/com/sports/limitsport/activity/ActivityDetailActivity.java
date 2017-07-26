package com.sports.limitsport.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ActivityDiscussAdapter;
import com.sports.limitsport.activity.presenter.ActivityDetailPresenter;
import com.sports.limitsport.activity.ui.IActivityDetailView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.CommentList;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.util.StatusBarUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.ActivityDetailHeaderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ActivityDetailActivity extends BaseActivity implements IActivityDetailView {

    @BindView(R.id.rlv_discuss)
    RecyclerView rlvDiscuss;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private ActivityDetailHeaderView activityDetailHeaderView;
    private ActivityDiscussAdapter adapter;
    private String id;
    private ActivityDetailPresenter mPresenter;
    private List<CommentList> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getIntentData();
        init();
        StatusBarUtil.setTranslucent(this);
        getData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ActivityDetailPresenter(this);
        }
        mPresenter.getActivityDetail(id);
        mPresenter.getAllShai(null);
//        mPresenter.getCommentList(id);
    }

    private void init() {
        activityDetailHeaderView = new ActivityDetailHeaderView(this);
        rlvDiscuss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ActivityDiscussAdapter(data);
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

    @Override
    public void showActivityDetail(ActivityDetailResponse response) {
        if (response != null && response.getData() != null) {

            if ("1".equals(response.getData().getStatus())) { //报名中
                rlBottom.setVisibility(View.VISIBLE);
            } else {
                rlBottom.setVisibility(View.GONE);
            }
            response.getData().setId(id);
            activityDetailHeaderView.showDetail(response.getData());
        }
    }

    @Override
    public void showAllShiList(DongTaiListResponse response) {
        if (response != null && response.getData() != null) {
            activityDetailHeaderView.showAllShai(response.getData().getData());
        }
    }

    @Override
    public void showCommentList(CommentListResponse response) {
        if (adapter != null) {
//            adapter.addData(null);
        }

    }

    @Override
    public void onError(Throwable e) {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }
        mPresenter = null;
    }
}