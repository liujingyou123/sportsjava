package com.sports.limitsport.mine.adapter;

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
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.view.AtTextView;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyCollectDongTaiAdapter extends BaseQuickAdapter<DongTaiList, BaseViewHolder> {
    public MyCollectDongTaiAdapter(@Nullable List<DongTaiList> data) {
        super(R.layout.item_adapter_mycollectdongtai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DongTaiList item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imvCover = helper.getView(R.id.imv_cover);

        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvFocus = helper.getView(R.id.tv_focus);
        AtTextView tvContent = helper.getView(R.id.tv_content);
//        LinearLayout llAt = helper.getView(R.id.ll_at);
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

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
        tvName.setText(item.getPublishUserName());
        tvTime.setText(item.getShowCreateTime());

        if (item.getAttentionFlag() == 0) {
            tvFocus.setText("+关注");
        } else if (item.getAttentionFlag() == 1) {
            tvFocus.setText("进入主页");
        }

        if (!TextViewUtil.isEmpty(item.getResourceType())) {
            imvCover.setVisibility(View.VISIBLE);
            if ("1".equals(item.getResourceType())) { //1 图片 2:视频
                Batman.getInstance().fromNet(item.getImgUrl(), imvCover, R.mipmap.icon_ver_default, R.mipmap.icon_ver_default);
            } else {
                Batman.getInstance().fromNet(item.getVedioThumbnailUrl(), imvCover, R.mipmap.icon_ver_default, R.mipmap.icon_ver_default);
            }
        } else {
            imvCover.setVisibility(View.GONE);
        }


        if (!TextViewUtil.isEmpty(item.getContent())) {
            tvContent.setStrings(item.getContent());

//            tvContent.setText(getContentText(item.getContent(), item.getAtUserList()));
//            tvContent.setMovementMethod(LinkMovementMethod.getInstance());
//            tvContent.setHighlightColor(tvContent.getResources().getColor(android.R.color.transparent));
        }

//        if (item.getAtUserList() != null && item.getAtUserList().size() > 0) {
//            for (int i = 0; i < item.getAtUserList().size(); i++) {
//                AtUserList atUserList = item.getAtUserList().get(i);
//                if (atUserList != null) {
//                    llAt.addView(getAtText(atUserList.getName(), atUserList.getUserId()));
//                }
//            }
//        }

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

//    private TextView getAtText(String name, final String userId) {
//        TextView textView = new TextView(mContext);
//        textView.setText("@" + name);
//        textView.setTag(userId);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
//        textView.setTextColor(Color.parseColor("#FF4795FB"));
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView.setLayoutParams(lp);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, PersonInfoActivity.class);
//                intent.putExtra("userId", userId);
//                mContext.startActivity(intent);
//            }
//        });
//        return textView;
//    }


//    public Spannable getContentText(String content, List<AtUserList> atUserLists) {
//        Spannable spannable = null;
//        StringBuilder sb = new StringBuilder(content);
//
//        int offset = 0;
//        List<HashMap<String, Object>> list = new ArrayList<>();
//        if (atUserLists != null && atUserLists.size() > 0) {
//            for (int i = 0; i < atUserLists.size(); i++) {
//                AtUserList atUserList = atUserLists.get(i);
//                String strMactch = TextViewUtil.stringFormat(atUserList.getName(), atUserList.getUserId());
//                String atName = TextViewUtil.stringFormatName(atUserList.getName());
//                int index = content.indexOf(strMactch);
//
//                HashMap<String, Object> map = new HashMap<>();
//                map.put("index", index + offset);
//                map.put("length", atName.length());
//                map.put("id", atUserList.getUserId());
//                list.add(map);
//
//                sb.delete(index + atName.length() + offset, index + strMactch.length() + offset);
//                offset -= (strMactch.length() - atName.length());
//
//            }
//        }
//
//        spannable = new SpannableString(sb);
//
//
//        for (int i = 0; i < list.size(); i++) {
//            final HashMap<String, Object> map = list.get(i);
//            int index = (int) map.get("index");
//            int length = (int) map.get("length");
//            final String userId = (String) map.get("id");
//            spannable.setSpan(new ClickSpan(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, PersonInfoActivity.class);
//                    intent.putExtra("userId", userId);
//                    mContext.startActivity(intent);
//                }
//            }, Color.parseColor("#4899ff")), index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        }
//        return spannable;
//    }

}
