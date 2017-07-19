package com.sports.limitsport.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.adapter.ActivityDiscussAdapter;
import com.sports.limitsport.base.BaseActivity;
import com.sports.limitsport.dialog.CommentDialog;
import com.sports.limitsport.util.MyTestData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/17.
 * 活动讨论区
 */

public class ActivityDiscussActivity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rlv_discuss)
    RecyclerView rlvDiscuss;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.btn_comment)
    TextView btnComment;
    private ActivityDiscussAdapter adapter;
    private CommentDialog commentDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        ButterKnife.bind(this);
        init();
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

    private void init() {
        tvFocusHouse.setText("活动讨论区");
        View emptyView = LayoutInflater.from(this).inflate(R.layout.empty_noticelist, null);
        TextView textView = (TextView) emptyView.findViewById(R.id.tv_empty);
        textView.setText("还没有留言，快来抢沙发吧～");
        rlvDiscuss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ActivityDiscussAdapter(MyTestData.getData());
        adapter.bindToRecyclerView(rlvDiscuss);
        adapter.setEmptyView(emptyView);

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
}
