package com.sports.limitsport.notice.adapter;

import android.content.Intent;
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
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.adapter.TagActivityAdapter;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/13.
 */

public class MyNoticeDongTaiAdapter extends BaseQuickAdapter<DongTaiList, BaseViewHolder> {
    public MyNoticeDongTaiAdapter(@Nullable List<DongTaiList> data) {
        super(R.layout.adapter_mynotice, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DongTaiList item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imvCover = helper.getView(R.id.imv_cover);

        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvFocus = helper.getView(R.id.tv_focus);
        TextView tvContent = helper.getView(R.id.tv_content);
        LinearLayout llAt = helper.getView(R.id.ll_at);
        TagCloudLayout tg = helper.getView(R.id.tg_tag);
        TextView tvPrise = helper.getView(R.id.tv_san);
        ImageView imvPrise = helper.getView(R.id.imv_zan);
        LinearLayout llCall = helper.getView(R.id.ll_recall);

        helper.addOnClickListener(R.id.imv_pinglun);
        helper.addOnClickListener(R.id.imv_share);
        helper.addOnClickListener(R.id.imv_report);
        helper.addOnClickListener(R.id.tv_focus);
        helper.addOnClickListener(R.id.imv_zan);
        helper.addOnClickListener(R.id.tv_san);
        helper.addOnClickListener(R.id.rl_perison);

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
        tvName.setText(item.getPublishUserName());
        tvTime.setText(item.getShowCreateTime());

        tvFocus.setText("已关注");
        tvFocus.setEnabled(false);

        if (item.getResourceType() == 1) { //1 图片 2:视频
            Batman.getInstance().fromNet(item.getImgUrl(), imvCover, R.mipmap.icon_ver_default, R.mipmap.icon_ver_default);
        } else {
            Batman.getInstance().fromNet(item.getVedioThumbnailUrl(), imvCover, R.mipmap.icon_ver_default, R.mipmap.icon_ver_default);
        }

        if (!TextViewUtil.isEmpty(item.getContent())) {
            tvContent.setText(item.getContent());
        }

        if (item.getAtUserList() != null && item.getAtUserList().size() > 0) {
            for (int i = 0; i < item.getAtUserList().size(); i++) {
                DongTaiList.AtUserList atUserList = item.getAtUserList().get(i);
                if (atUserList != null) {
                    llAt.addView(getAtText(atUserList.getName(), atUserList.getUserId()));
                }
            }
        }

        List<String> tags = new ArrayList<>();
        if (!TextViewUtil.isEmpty(item.getActivityName())) {
            tags.add(item.getActivityName());
        }
        tg.setAdapter(new TagActivityAdapter(this.mContext, tags));

        tvPrise.setText(item.getPraiseNum() + "");
        if ("1".equals(item.getPraiseFlag())) { //1:已点赞 0:未点赞
            tvPrise.setSelected(true);
            imvPrise.setSelected(true);
        } else {
            tvPrise.setSelected(false);
            imvPrise.setSelected(false);
        }

        llCall.removeAllViews();

        if (item.getCommentList() != null && item.getCommentList().size() > 0) {
            int size = item.getCommentList().size();
            if (size > 3) {
                size = 3;
            }
            for (int i = 0; i < size; i++) {
                DongTaiList.CommentListBean commentListBean = item.getCommentList().get(i);
                llCall.addView(getTextView(commentListBean.getCommentatorName(), commentListBean.getContent()));
            }
        }
    }

    private TextView getTextView(String commenter, String content) {
        if (TextViewUtil.isEmpty(commenter)) {
            commenter = "";
        }
        TextView textView = new TextView(mContext);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(Color.parseColor("#FFA5A4A4"));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 0);
        textView.setLayoutParams(lp);

        textView.setText(commenter + "：" + content);
        if ("官方".equals(commenter)) {
            TextViewUtil.setPartialColor(textView, 0, commenter.length() + 1, Color.parseColor("#4795FB"));
        } else {
            TextViewUtil.setPartialColor(textView, 0, commenter.length() + 1, Color.parseColor("#ffffff"));
        }
        return textView;
    }

    private TextView getAtText(String name, final String userId) {
        TextView textView = new TextView(mContext);
        textView.setText("@" + name);
        textView.setTag(userId);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        textView.setTextColor(Color.parseColor("#FF4795FB"));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PersonInfoActivity.class);
                intent.putExtra("userId", userId);
                mContext.startActivity(intent);
            }
        });
        return textView;
    }
}
