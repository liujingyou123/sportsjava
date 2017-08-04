package com.sports.limitsport.discovery.adapter;

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
 * Created by liuworkmac on 17/7/12.
 */

public class FineShowCommentAdapter extends BaseQuickAdapter<CommentList, BaseViewHolder> {

    public FineShowCommentAdapter(@Nullable List<CommentList> data) {
        super(R.layout.adapter_findshow_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentList item) {
        View viewline = helper.getView(R.id.view_line);

        ImageView imvHead = helper.getView(R.id.imv_head);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvComment = helper.getView(R.id.tv_comment);
        LinearLayout llRecall = helper.getView(R.id.ll_recall);
        ImageView imvZan = helper.getView(R.id.imv_zan);
        TextView tvSan = helper.getView(R.id.tv_san);

        helper.addOnClickListener(R.id.imv_comment);
        helper.addOnClickListener(R.id.imv_zan);
        helper.addOnClickListener(R.id.tv_san);

        if (1 == item.getPraiseFlag()) { //1:已点赞 0:未点赞
            tvSan.setSelected(true);
            imvZan.setSelected(true);
        } else {
            tvSan.setSelected(false);
            imvZan.setSelected(false);
        }
        tvSan.setText(item.getPraiseNum()+"");
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
            viewline.setVisibility(View.VISIBLE);
            llRecall.removeAllViews();
            llRecall.setVisibility(View.VISIBLE);
            for (int i = 0; i < item.getReplyList().size(); i++) {
                CommentList.ReplyList replyList = item.getReplyList().get(i);
                if (replyList != null) {
                    llRecall.addView(getTextView(replyList.getCommentUserName(), replyList.getReplyUserName(), replyList.getReplyContent()));
                }
            }
        } else {
            viewline.setVisibility(View.GONE);
            llRecall.setVisibility(View.GONE);
        }

    }

    private TextView getTextView(String commenter, String recaller, String content) {
        if (TextViewUtil.isEmpty(commenter)) {
            commenter = "";
        }
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

        if ("官方".equals(commenter)) {
            TextViewUtil.setPartialColor(textView, 0, commenter.length() + 1, Color.parseColor("#4795FB"));
        } else {
            TextViewUtil.setPartialColor(textView, 0, commenter.length() + 1, Color.parseColor("#ffffff"));
        }
        return textView;
    }
}
