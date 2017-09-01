package com.sports.limitsport.activity.adapter;

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
import com.sports.limitsport.model.AtUserList;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.util.ClickSpan;
import com.sports.limitsport.util.TextViewUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ShallAdapter extends BaseQuickAdapter<DongTaiList, BaseViewHolder> {
    public ShallAdapter(@Nullable List<DongTaiList> data) {
        super(R.layout.item_adapter_shai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DongTaiList item) {
        ImageView imvCover = helper.getView(R.id.imv_cover);
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imvZan = helper.getView(R.id.imv_zan);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvDes = helper.getView(R.id.tv_des);
        TextView tvPraise = helper.getView(R.id.tv_praise);
        if ("1".equals(item.getResourceType())) { //1 图片 2:视频
            Batman.getInstance().fromNet(item.getImgUrl(), imvCover, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default);
        } else {
            Batman.getInstance().fromNet(item.getVedioImgUrl(), imvCover, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default);
        }

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);

        tvName.setText(item.getPublishUserName());
        if (!TextViewUtil.isEmpty(item.getContent())) {
//            tvContent.setText(item.getContent());
            tvDes.setText(getContentText(item.getContent(), item.getAtUserList()));
            tvDes.setMovementMethod(LinkMovementMethod.getInstance());
            tvDes.setHighlightColor(tvDes.getResources().getColor(android.R.color.transparent));
        }
//        tvDes.setText(item.getTitle());
        tvPraise.setText(item.getPraiseNum() + "");
        item.setPraiseFlag("1");
        if ("1".equals(item.getPraiseFlag())) { // 已点赞
            tvPraise.setSelected(true);
            imvZan.setSelected(true);
        } else {
            tvPraise.setSelected(false);
            imvZan.setSelected(false);
        }

//        //TODO test
//        Batman.getInstance().fromNet("http://img.sc115.com/wm/xqx/pic1/15011vd5vam10fg.jpg", imvCover);
//        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);
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
            HashMap<String, Object> map = list.get(i);
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
