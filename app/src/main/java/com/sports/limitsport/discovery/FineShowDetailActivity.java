package com.sports.limitsport.discovery;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.discovery.adapter.FineShowCommentAdapter;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.view.FineShowDetailHeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/12.
 */

public class FineShowDetailActivity extends BaseActivity {
    @BindView(R.id.rlv_comment)
    RecyclerView rlvComment;
    @BindView(R.id.btn_comment)
    TextView btnComment;
    private FineShowCommentAdapter adapter;
    private CommentDialog commentDialog;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fineshow);
        ButterKnife.bind(this);
        getIntentData();
        initView();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_fav, R.id.btn_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_fav:
                break;
            case R.id.btn_comment:
                commentDialog.show();
                break;
        }
    }


    private void initView() {

        View headerView = new FineShowDetailHeadView(this);

        rlvComment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new FineShowCommentAdapter(null);
        adapter.addHeaderView(headerView);
        adapter.setHeaderAndEmpty(true);
        adapter.bindToRecyclerView(rlvComment);
        adapter.setEmptyView(R.layout.empty_no_comment);

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
    }
}
