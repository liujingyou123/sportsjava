package com.sports.limitsport.activity.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.CommentList;
import com.sports.limitsport.util.TextViewUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/17.
 * 活动详情 讨论区
 */

public class ActivityDiscussAdapter extends BaseQuickAdapter<CommentList, BaseViewHolder> {
    public ActivityDiscussAdapter(@Nullable List<CommentList> data) {
        super(R.layout.adapter_activity_discuss, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentList item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvComment = helper.getView(R.id.tv_comment);
        LinearLayout llRecall = helper.getView(R.id.ll_recall);

        tvName.setText(item.getCommentatorName());
        if (!TextViewUtil.isEmpty(item.getShowCreateTime())) {
            tvTime.setText(item.getShowCreateTime());
        }
        if (!TextViewUtil.isEmpty(item.getContent())) {
            tvComment.setText(item.getContent());
        }
        //TODO 正式启用
        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, 0, 0);

        if (item.getReplyList() != null && item.getReplyList().size() > 0) {
            llRecall.removeAllViews();
            llRecall.setVisibility(View.VISIBLE);
            for (int i = 0; i < item.getReplyList().size(); i++) {
                CommentList.ReplyList replyList = item.getReplyList().get(i);
                if (replyList != null) {
                    llRecall.addView(getTextView(replyList.getCommentUserName(), replyList.getReplyUserName(), replyList.getReplyContent()));
                }
            }
        } else {
            llRecall.setVisibility(View.GONE);
        }

//        //TODO 测试用
//        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);
    }

    private TextView getTextView(String commenter, String recaller, String content) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(Color.parseColor("#FFA5A4A4"));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 0);
        textView.setLayoutParams(lp);
        if (TextViewUtil.isEmpty(recaller)) {
            textView.setText(commenter + "：" + content);
        } else {
            textView.setText(commenter + "：@" + recaller + " " + content);
        }

        TextViewUtil.setPartialColor(textView, 0, commenter.length() + 1, Color.parseColor("#ffffff"));
        return textView;
    }
}
