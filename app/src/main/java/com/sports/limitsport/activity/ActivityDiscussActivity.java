package com.sports.limitsport.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ActivityDiscussAdapter;
import com.sports.limitsport.activity.presenter.ActivityDiscussPresenter;
import com.sports.limitsport.activity.ui.IActivityDiscussView;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.CommentList;
import com.sports.limitsport.model.CommentListResponse;
import com.sports.limitsport.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/17.
 * 活动讨论区
 */

public class ActivityDiscussActivity extends BaseActivity implements IActivityDiscussView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv_discuss)
    RecyclerView rlvDiscuss;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.btn_comment)
    TextView btnComment;
    @BindView(R.id.rl_all)
    EasyRefreshLayout rlAll;
    private ActivityDiscussAdapter adapter;
    private CommentDialog commentDialog;
    private List<CommentList> data = new ArrayList<>();
    private ActivityDiscussPresenter mPresenter;
    private String id;// 活动ID
    private int pageNumber = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        ButterKnife.bind(this);
        getIntentData();
        init();

        getData();
    }

    @OnClick({R.id.imv_focus_house_back, R.id.btn_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.btn_comment:
                commentDialog.show();
                break;
        }
    }

    public void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ActivityDiscussPresenter(this);
        }
        mPresenter.getCommentList(id, pageNumber + "");
    }

    private void init() {
        tvFocusHouse.setText("活动讨论区");
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        TextView textView = (TextView) emptyView.findViewById(R.id.tv_empty);
        textView.setText("还没有留言，快来抢沙发吧～");
        rlvDiscuss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ActivityDiscussAdapter(data);
        adapter.bindToRecyclerView(rlvDiscuss);
        adapter.setEmptyView(emptyView);

        adapter.setLoadMoreView(new CustomLoadMoreView());

        adapter.disableLoadMoreIfNotFullPage();
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                XLog.e("onLoadMoreRequested");
                loadMore();
            }
        }, rlvDiscuss);
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

        commentDialog = new CommentDialog(this);
        commentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                String content = commentDialog.getContent();
                if (TextUtils.isEmpty(content)) {
                    btnComment.setText("我要来发言…");
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
//                mPresenter.commentTopic(topicId, commentDialog.getContent());
            }
        });
    }

    private void loadMore() {
        if (mPresenter != null) {
            pageNumber++;
            mPresenter.getCommentList(id, pageNumber+"");
        }
    }

    private void refresh() {
        if (mPresenter != null) {
            pageNumber = 1;
            mPresenter.getCommentList(id, pageNumber+"");
        }
    }


    @Override
    public void showCommentList(CommentListResponse response) {
        if (rlAll.isRefreshing()) {
            data.clear();
            //TODO 等数据结构完全 调用
//            data.addAll(baseStaggeredEntities);
            adapter.notifyDataSetChanged();
            rlAll.refreshComplete();
        } else {
            //TODO 等数据结构完全 调用
//            adapter.addData(baseStaggeredEntities);
//            if (adapter.getData().size() >= totalSize) {
//                adapter.loadMoreEnd();
//            } else {
//                adapter.loadMoreComplete();
//            }
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
