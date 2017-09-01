package com.sports.limitsport.discovery.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.AtUserList;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.util.ClickSpan;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.view.ScaleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/10.
 */

public class DongTaiAdapter extends BaseQuickAdapter<DongTaiList, BaseViewHolder> {
    public DongTaiAdapter(@Nullable List<DongTaiList> data) {
        super(R.layout.adapter_new_dongtai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DongTaiList item) {
        ScaleImageView imvCover = helper.getView(R.id.imv_cover);
        ImageView imvHead = helper.getView(R.id.imv_head);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTitle = helper.getView(R.id.tv_title);
        ImageView imvZan = helper.getView(R.id.imv_zan);
        TextView tvSan = helper.getView(R.id.tv_san);
        TextView tvStatus = helper.getView(R.id.tv_status);

        helper.addOnClickListener(R.id.imv_cover);
        helper.addOnClickListener(R.id.imv_head);
        helper.addOnClickListener(R.id.tv_name);
        helper.addOnClickListener(R.id.imv_zan);
        helper.addOnClickListener(R.id.tv_san);

        imvCover.setInitSize(item.getWidth(), item.getHeight());
        int position = helper.getAdapterPosition();

        XLog.e("position = " + position + " height = " + item.getHeight() + " width = " + item.getWidth());

//        int position = helper.getAdapterPosition();
//        Batman.getInstance().fromNet(MyTestData.getData().get(position), imvCover, R.mipmap.icon_default_detail, R.mipmap.icon_default_detail, item.getWidth(), item.getHeight());
        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
//        Batman.getInstance().fromNet(MyTestData.getData().get(position), imvCover, R.mipmap.icon_default_pubuliu, R.mipmap.icon_default_pubuliu, item.getWidth(), item.getHeight());

//
        if ("1".equals(item.getResourceType())) { //1 图片 2:视频
            tvStatus.setText("图片");
            Batman.getInstance().fromNet(item.getImgUrl(), imvCover, R.mipmap.icon_default_pubuliu, R.mipmap.icon_default_pubuliu, item.getWidth(), item.getHeight());
        } else {
            tvStatus.setText("视频");
            Batman.getInstance().fromNet(item.getVedioImgUrl(), imvCover, R.mipmap.icon_default_pubuliu, R.mipmap.icon_default_pubuliu, item.getWidth(), item.getHeight());
        }

        if (!TextViewUtil.isEmpty(item.getPublishUserName())) {
            tvName.setText(item.getPublishUserName());
        }

        if (!TextViewUtil.isEmpty(item.getContent())) {
            tvTitle.setText(getContentText(item.getContent(), item.getAtUserList()));
            tvTitle.setMovementMethod(LinkMovementMethod.getInstance());
            tvTitle.setHighlightColor(tvTitle.getResources().getColor(android.R.color.transparent));
//            tvTitle.setText(item.getContent());
        }

        tvSan.setText(item.getPraiseNum() + "");
        if ("1".equals(item.getPraiseFlag())) { //1:已点赞 0:未点赞
            imvZan.setSelected(true);
            tvSan.setSelected(true);
        } else {
            imvZan.setSelected(false);
            tvSan.setSelected(false);
        }
    }

    public Spannable getContentText(String content, List<AtUserList> atUserLists) {
        Spannable spannable = null;
        StringBuilder sb = new StringBuilder(content);

        int offset = 0;
        List<HashMap<String, Object>> list = new ArrayList<>();
        if (atUserLists != null && atUserLists.size() > 0) {
            for (int i = 0; i < atUserLists.size(); i++) {
                AtUserList atUserList = atUserLists.get(i);
                String strMactch = TextViewUtil.stringFormat(atUserList.getName(), atUserList.getUserId());
                String atName = TextViewUtil.stringFormatName(atUserList.getName());
                int index = content.indexOf(strMactch);

                HashMap<String, Object> map = new HashMap<>();
                map.put("index", index + offset);
                map.put("length", atName.length());
                map.put("id", atUserList.getUserId());
                list.add(map);

                sb.delete(index + atName.length() + offset, index + strMactch.length() + offset);
                offset -= (strMactch.length() - atName.length());

            }
        }

        spannable = new SpannableString(sb);


        for (int i = 0; i < list.size(); i++) {
            final HashMap<String, Object> map = list.get(i);
            int index = (int) map.get("index");
            int length = (int) map.get("length");
            final String userId = (String) map.get("id");
            spannable.setSpan(new ClickSpan(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PersonInfoActivity.class);
                    intent.putExtra("userId", userId);
                    mContext.startActivity(intent);
                }
            }, Color.parseColor("#4899ff")), index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }
        return spannable;
    }
}
