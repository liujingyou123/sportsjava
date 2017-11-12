package com.sports.limitsport.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ActivityDiscussAdapter;
import com.sports.limitsport.activity.presenter.ActivityDetailPresenter;
import com.sports.limitsport.activity.ui.IActivityDetailView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.dialog.NoticeDelDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.dialog.ShareDialog;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.main.MainActivity;
import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.CommentList;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.net.H5Address;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.StatusBarUtil;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.ActivityDetailHeaderView;
import com.sports.limitsport.view.CustomTypeFaceTextView;

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
    @BindView(R.id.tv_price_bottom)
    CustomTypeFaceTextView tvPriceBottom;
    @BindView(R.id.imv_fav)
    ImageView imvFav;
    @BindView(R.id.btn_done)
    TextView btnDone;
    private ActivityDetailHeaderView activityDetailHeaderView;
    private ActivityDiscussAdapter adapter;
    private String id;
    private ActivityDetailPresenter mPresenter;
    private List<CommentList> data = new ArrayList<>();
    private CommentDialog commentDialog;
    private CommentList commentList;
    private ActivityDetailResponse.DataBean mData;
    //    private String week;
//    private String minMoney;
//    private int ticketNum;
    private ShareDialog shareDialog;
    private String from;


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
//            week = intent.getStringExtra("week");
//            minMoney = intent.getStringExtra("minMoney");
//            ticketNum = intent.getIntExtra("ticketNum", 0);
            from = intent.getStringExtra("from");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ActivityDetailPresenter(this);
        }
        mPresenter.getActivityDetail(id);
        mPresenter.getAllShai(id); //TODO 测试时要传null ，真实要传ID
        mPresenter.getCommentList(id);//TODO 测试时要传"1" ，真实要传ID

    }

    private void init() {
        activityDetailHeaderView = new ActivityDetailHeaderView(this);
        rlvDiscuss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ActivityDiscussAdapter(data);
        adapter.addHeaderView(activityDetailHeaderView);
        adapter.setHeaderAndEmpty(true);
        adapter.bindToRecyclerView(rlvDiscuss);
        adapter.setEmptyView(R.layout.empty_discuss);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent = new Intent(ActivityDetailActivity.this, LoginActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent = new Intent(ActivityDetailActivity.this, IdentifyMainActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                } else {
                    if (commentDialog != null && !commentDialog.isShowing()) {
                        commentList = (CommentList) adapter.getItem(position);
                        commentDialog.show();
                    }
                }

            }
        });

        rlvDiscuss.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                setColor();
            }
        });

        commentDialog = new CommentDialog(this);

        commentDialog.setOkDoneListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog.dismiss();
                if (commentList != null) {
                    mPresenter.replayComment(commentList.getId() + "", commentList.getCommentatorId() + "", commentList.getCommentatorName(), commentDialog.getContent());
                }
            }
        });
    }

    private void setColor() {
        int[] location = new int[2];
        activityDetailHeaderView.getLocationInWindow(location);

        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);

        if (location[1] < statusBarHeight) {
            rlTitle.setBackgroundColor(Color.parseColor("#171717"));
        } else {
            rlTitle.setBackgroundColor(Color.parseColor("#00000000"));
        }
    }

    @OnClick({R.id.imv_back, R.id.btn_done, R.id.imv_report, R.id.imv_share, R.id.imv_fav})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
                if ("outer".equals(from)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
                break;
            case R.id.btn_done:
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent = new Intent(this, IdentifyMainActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                } else {
                    if (mData.getSignStatus() == 0) {
                        gotoSignUpActivity();
                    } else if (mData.getSignStatus() == 1) {
                        showAlreadySignUpDialog();
                    }
                }
                break;
            case R.id.imv_report:
                ReportDialog dialog = new ReportDialog(this, "0", id);
                dialog.show();
                break;
            case R.id.imv_share:
                if (mData != null) {
                    if (shareDialog == null) {
                        shareDialog = new ShareDialog(this);
                    }
                    if (!shareDialog.isShowing()) {
                        shareDialog.setTitle(mData.getName());
                        shareDialog.setDes(mData.getActivityDetail());
                        if (TextViewUtil.isEmpty(mData.getCoverUrl())) { //视频
                            shareDialog.setImage(mData.getActivityVideoImg());
                        } else {
                            shareDialog.setImage(mData.getCoverUrl());
                        }
                        shareDialog.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx930d2f82dab82040&redirect_uri=http%3a%2f%2fapi.ex-fans.com%2ftst%2fultimateapp%2fh5%2factivity%2fqueryActivityInfo%2f125%2fv1.0.0&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
//                        shareDialog.setUrl(H5Address.getUrlActivityDetail(mData.getId()));
                        shareDialog.show();
                    }
                }
                break;
            case R.id.imv_fav:
                if (imvFav.isSelected()) {
                    if (mPresenter != null) {
                        mPresenter.unCollect(id);
                    }
                } else {
                    if (mPresenter != null) {
                        mPresenter.collect(id);
                    }
                }

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
                gotoSignUpActivity();
            }
        });
        dialog.show();
    }

    /**
     * 跳转报名列表页
     */
    private void gotoSignUpActivity() {
        Intent intentSign = new Intent(ActivityDetailActivity.this, SignUpActivity.class);
        intentSign.putExtra("id", mData.getId());
        intentSign.putExtra("title", mData.getName());

        if (TextViewUtil.isEmpty(mData.getCoverUrl())) { //视频
            intentSign.putExtra("imgCover", mData.getActivityVideoImg());
        } else {
            intentSign.putExtra("imgCover", mData.getCoverUrl());
        }
        String startTime = mData.getStartDate() + " " + UnitUtil.stringToWeek(mData.getWeek()) + " " + mData.getStartTime();
        intentSign.putExtra("startTime", startTime);
        intentSign.putExtra("address", mData.getAddress());

        startActivity(intentSign);
    }

    private void setReplayData() {
        for (int i = 0; i < adapter.getData().size(); i++) {

            if (adapter.getData().get(i).equals(commentList)) {
                CommentList.ReplyList replyList = new CommentList.ReplyList();
                replyList.setCommentUserId(SharedPrefsUtil.getUserInfo().getData().getUserId() + "");

                replyList.setCommentUserName(SharedPrefsUtil.getUserInfo().getData().getName() + "");
                replyList.setReplyContent(commentDialog.getContent());
                replyList.setReplyUserName(commentList.getCommentatorName());
                replyList.setReplyCommentId(commentList.getId() + "");
                replyList.setReplyUserId(commentList.getCommentatorId() + "");
                if (adapter.getData().get(i).getReplyList() != null) {
                    adapter.getData().get(i).getReplyList().add(0, replyList);
                } else {
                    List<CommentList.ReplyList> lists = new ArrayList<>();
                    lists.add(replyList);
                    adapter.getData().get(i).setReplyList(lists);
                }
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void showActivityDetail(ActivityDetailResponse response) {
        if (response != null && response.getData() != null) {
            rlBottom.setVisibility(View.VISIBLE);
            mData = response.getData();
            if ("2".equals(response.getData().getStatus())) { //报名中
                if (response.getData().getTicketNum() == 0) { //报名名额已满
                    btnDone.setEnabled(false);
                    ToastUtil.showFalseToast(this, "报名额满");
                    btnDone.setText("报名额满");
                } else {
                    btnDone.setEnabled(true);
                }
                tvPriceBottom.setCustomText(mData.getMoney());
            } else if ("4".equals(response.getData().getStatus())) { //已结束
                btnDone.setEnabled(false);
                ToastUtil.showFalseToast(this, "活动已结束");
                btnDone.setText("活动结束");
                tvPriceBottom.setCustomText(mData.getMoney());
            } else if ("3".equals(response.getData().getStatus())) { //进行中
                btnDone.setEnabled(false);
                btnDone.setText("报名结束");
                ToastUtil.showFalseToast(this, "报名结束");
                tvPriceBottom.setCustomText(mData.getMoney());
            } else {
                btnDone.setEnabled(false);
                ToastUtil.showFalseToast(this, "报名结束");
                btnDone.setText("报名结束");
                tvPriceBottom.setCustomText(mData.getMoney());
            }

//            btnDone.setEnabled(true);

            response.getData().setId(id);
//            response.getData().setWeek(week);
//            response.getData().setMinMoney(minMoney);
//            response.getData().setTicketNum(ticketNum);

            if (UnitUtil.stringToD(mData.getMinMoney()) == 0) {
                tvPriceBottom.setCustomText("￥0");
            }
            activityDetailHeaderView.showDetail(response.getData());

            if ("1".equals(mData.getIsCollection())) {
                imvFav.setSelected(true);
            } else {
                imvFav.setSelected(false);
            }
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
        if (adapter != null && response.getData() != null) {
            adapter.addData(response.getData().getData());
        }

    }

    @Override
    public void showReplayComment(boolean isSuccess) {
        if (isSuccess) {
            ToastUtil.showTrueToast(this, "回复评论成功");
            setReplayData();
        } else {
            ToastUtil.showTrueToast(this, "回复评论失败");
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void collectReslut(boolean b) {
        if (b) {
            ToastUtil.showTrueToast(this, "收藏成功");
            imvFav.setSelected(true);
        } else {
            ToastUtil.showTrueToast(this, "收藏失败");
            imvFav.setSelected(false);
        }
    }

    @Override
    public void cancelCollectReslut(boolean b) {
        if (b) {
            ToastUtil.showTrueToast(this, "已取消收藏");
            imvFav.setSelected(false);
        } else {
            ToastUtil.showTrueToast(this, "取消收藏失败");
            imvFav.setSelected(true);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
        }
        mPresenter = null;
    }
}