package com.sports.limitsport.discovery;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.dialog.ReportDialog;
import com.sports.limitsport.discovery.adapter.FineShowCommentAdapter;
import com.sports.limitsport.discovery.presenter.FineShowDetailPresenter;
import com.sports.limitsport.discovery.ui.IFineShowDetailView;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.main.IdentifyMainActivity;
import com.sports.limitsport.main.LoginActivity;
import com.sports.limitsport.model.CommentList;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.model.FineShowDetailResponse;
import com.sports.limitsport.model.PraiseListResponse;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.view.CustomLoadMoreNoEndView;
import com.sports.limitsport.view.FineShowDetailHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/12.
 */

public class FineShowDetailActivity extends BaseActivity implements IFineShowDetailView {
    @BindView(R.id.rlv_comment)
    RecyclerView rlvComment;
    @BindView(R.id.btn_comment)
    TextView btnComment;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    @BindView(R.id.tv_fav)
    ImageView tvFav;
    @BindView(R.id.tv_send)
    TextView tvSend;
    private FineShowCommentAdapter adapter;
    private CommentDialog commentDialog;
    private String id;
    private FineShowDetailHeadView headerView;
    private FineShowDetailPresenter mPresenter;
    private int pageNumber = 1;
    private CommentList commentList;
    private int totalSize;
    private List<CommentList> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fineshow);
        ButterKnife.bind(this);
        getIntentData();
        initView();
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
            mPresenter = new FineShowDetailPresenter(this);
        }

        mPresenter.getFineShowDetail(id);
        mPresenter.getCommentList(id, pageNumber + "");
        mPresenter.getPraiseList(id);
//        rlAll.autoRefresh();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_fav, R.id.btn_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_fav:
                if (tvFav.isSelected()) { //已收藏
                    if (mPresenter != null) {
                        mPresenter.unCollect(id);
                    }
                } else {
                    if (mPresenter != null) {
                        mPresenter.collect(id);
                    }
                }
                break;
            case R.id.btn_comment:
                commentDialog.show();
                commentDialog.setType(1);
                commentDialog.show();
                break;
        }
    }


    private void initView() {

        headerView = new FineShowDetailHeadView(this);
        headerView.setChildClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedPrefsUtil.getUserInfo() == null) {
                    Intent intent = new Intent(FineShowDetailActivity.this, LoginActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                    return;
                } else if (SharedPrefsUtil.getUserInfo() != null && SharedPrefsUtil.getUserInfo().getData().getIsPerfect() == 1) {
                    Intent intent = new Intent(FineShowDetailActivity.this, IdentifyMainActivity.class);
                    intent.putExtra("type", "2");
                    startActivity(intent);
                } else {
                    if (headerView == null) {
                        return;
                    }
                    FineShowDetailResponse.DataBean data = headerView.getData();
                    if (data != null) {
                        if (view.getId() == R.id.tv_focus) {
                            if (data.getAttentionFlag() == 0) {
                                if (mPresenter != null) {
                                    mPresenter.foucesFans(data.getPublishUserId() + "");
                                }
                            } else if (data.getAttentionFlag() == 1) {
                                Intent intent = new Intent(FineShowDetailActivity.this, PersonInfoActivity.class);
                                intent.putExtra("userId", data.getPublishUserId() + "");
                                startActivity(intent);
                            }

                        } else if (view.getId() == R.id.imv_pinglun) {
                            commentDialog.setType(1);
                            commentDialog.show();
                        } else if (view.getId() == R.id.tv_san || view.getId() == R.id.imv_zan) {
                            if ("1".equals(data.getPraiseFlag())) { //1:已点赞 0:未点赞
                                if (mPresenter != null) {
                                    mPresenter.cancelPraise(data.getId() + "", "1");
                                }
                            } else {
                                if (mPresenter != null) {
                                    mPresenter.praise(data.getId() + "", "1");
                                }
                            }
                        } else if (view.getId() == R.id.imv_report) {
                            ReportDialog reportDialog = new ReportDialog(FineShowDetailActivity.this, "2", data.getId() + "");
                            reportDialog.show();
                        }
                    }
                }
            }
        });
        rlvComment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new FineShowCommentAdapter(data);
        adapter.addHeaderView(headerView);
        adapter.setHeaderAndEmpty(true);
        adapter.bindToRecyclerView(rlvComment);
        adapter.setEmptyView(R.layout.empty_no_comment);

        adapter.setLoadMoreView(new CustomLoadMoreNoEndView());

        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlvComment);
        rlAll.setEnableLoadMore(false);

        rlAll.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                XLog.e("onRefreshing");
                refresh();
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                commentList = (CommentList) adapter.getItem(position);

                if (view.getId() == R.id.imv_comment) {
                    commentDialog.setType(2);
                    commentDialog.show();
                } else if (view.getId() == R.id.imv_zan || view.getId() == R.id.tv_san) {
                    if (1 == commentList.getPraiseFlag()) { //1:已点赞 0:未点赞
                        if (mPresenter != null) {
                            mPresenter.cancelPraise(commentList.getId() + "", "3");
                        }
                    } else {
                        if (mPresenter != null) {
                            mPresenter.praise(commentList.getId() + "", "3");
                        }
                    }
                }

            }
        });


        commentDialog = new CommentDialog(this);
        commentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                String content = commentDialog.getContent();
                if (TextUtils.isEmpty(content)) {
                    btnComment.setText("我要来发言…");
//                    btnComment.setTextColor(Color.parseColor("#999999"));
                } else {
                    btnComment.setText(commentDialog.getContent());
//                    btnComment.setTextColor(Color.parseColor("#333333"));
                }
            }
        });

        commentDialog.setOkDoneListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.commentTopic(topicId, commentDialog.getContent());
            }
        });

        commentDialog = new CommentDialog(this);
        commentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                String content = commentDialog.getContent();
                if (TextUtils.isEmpty(content)) {
                    btnComment.setText("");
                    btnComment.setHint("我要来发言…");
                    btnComment.setTextColor(Color.parseColor("#FF444444"));
                    tvSend.setEnabled(false);
                } else {
                    btnComment.setText(commentDialog.getContent());
                    btnComment.setTextColor(Color.parseColor("#ffffff"));
                    tvSend.setEnabled(true);
                }
            }
        });

        commentDialog.setOkDoneListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog.dismiss();
                int type = commentDialog.getType();
                if (1 == type) { //评论动态
                    mPresenter.publishActivityComment(id, commentDialog.getContent());
                } else if (2 == type) { //回复评论
                    mPresenter.replayComment(commentList.getId() + "", commentList.getCommentatorId() + "", commentList.getCommentatorName(), commentDialog.getContent());
                }
            }
        });
    }

    private void loadMore() {
        if (mPresenter != null) {
            pageNumber++;
            mPresenter.getCommentList(id, pageNumber + "");
        }
    }

    private void refresh() {
        if (mPresenter != null) {
            pageNumber = 1;
            mPresenter.getCommentList(id, pageNumber + "");
        }
    }

    @Override
    public void showCommentList(CommentListResponse response) {
        if (response.getData() != null) {
            totalSize = response.getData().getTotalSize();
            if (headerView != null) {
                headerView.setCommentNum(totalSize);
            }
            XLog.e("is resreshing = " + rlAll.isRefreshing());
            if (rlAll.isRefreshing()) {
                data.clear();
                data.addAll(response.getData().getData());
                adapter.setNewData(data);
                adapter.disableLoadMoreIfNotFullPage();
                rlAll.refreshComplete();
            } else {
                adapter.addData(response.getData().getData());
                if (adapter.getData().size() >= totalSize) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (rlAll.isRefreshing()) {
            rlAll.refreshComplete();
        } else {
            rlAll.loadMoreFail();
        }
    }

    @Override
    public void showDetail(FineShowDetailResponse response) {
        if (headerView != null && response != null) {
            headerView.setData(response.getData());
        }

        if (response.getData() != null) {
            if (response.getData().getCollectionFlag() == 1) {
                tvFav.setSelected(true);
            } else {
                tvFav.setSelected(false);
            }
        }
    }

    @Override
    public void onFocusReslult(boolean b) {
        if (b) {
            ToastUtil.showTrueToast(this, "关注成功");
            if (headerView != null) {
                headerView.setFocus();
            }
        } else {
            ToastUtil.showTrueToast(this, "关注失败");
        }
    }

    @Override
    public void onPraiseResult(boolean b, String articleId, String type) {
        if (b) {
            if ("1".equals(type)) {
                if (headerView != null) {
                    headerView.setPraise();
                    if (mPresenter != null) {
                        mPresenter.getPraiseList(id);
                    }
                }
            } else if ("3".equals(type)) {
                doCommentPraise(articleId);
            }

        } else {
            ToastUtil.showTrueToast(this, "点赞失败");
        }
    }

    @Override
    public void onCancelPraiseResult(boolean b, String articleId, String type) {
        if (b) {
            if ("2".equals(type)) {
                if (headerView != null) {
                    headerView.setPraise();
                    if (mPresenter != null) {
                        mPresenter.getPraiseList(id);
                    }
                }
            } else if ("3".equals(type)) {
                doCommentPraise(articleId);
            }
        } else {
            ToastUtil.showTrueToast(this, "取消点赞失败");
        }
    }

    @Override
    public void showPublishActivityComent(boolean b) {
        if (b) {
            commentDialog.setContent("");
            btnComment.setText("");
            btnComment.setHint("我要来发言…");
            ToastUtil.showTrueToast(this, "评论成功");
            rlAll.autoRefresh();
        } else {
            ToastUtil.showTrueToast(this, "评论失败");
        }
    }

    @Override
    public void showReplayComment(boolean isSuccess) {
        if (isSuccess) {
            setReplayData();
            commentDialog.setContent("");
            btnComment.setText(null);
            btnComment.setHint("我要来发言...");
            ToastUtil.showTrueToast(this, "回复成功");
        } else {
            ToastUtil.showTrueToast(this, "回复失败");
        }
    }

    @Override
    public void collectReslut(boolean b) {
        if (b) {
            tvFav.setSelected(true);
        }
    }

    @Override
    public void cancelCollectReslut(boolean b) {
        if (b) {
            tvFav.setSelected(false);

        }
    }

    @Override
    public void showPraiseList(PraiseListResponse response) {
        if (response != null && response.getData() != null) {
            if (headerView != null) {
                headerView.setNameRecy(response.getData().getData());
            }
        }
    }

    @Override
    public void onDetailError(Throwable e) {

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

    private void doCommentPraise(String articleId) {
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (articleId.equals(adapter.getData().get(i).getId() + "")) {
                int num = adapter.getData().get(i).getPraiseNum();
                if (adapter.getData().get(i).getPraiseFlag() == 1) { //已点赞
                    num--;
                    adapter.getData().get(i).setPraiseFlag(0);
                } else {
                    num++;
                    adapter.getData().get(i).setPraiseFlag(1);
                }
                adapter.getData().get(i).setPraiseNum(num);
                adapter.notifyDataSetChanged();
                break;
            }
        }
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
