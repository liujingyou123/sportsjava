package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.HuDongNoticeList;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.AtTextView;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/6.
 */

public class FocusMeAdapter extends BaseQuickAdapter<HuDongNoticeList, BaseViewHolder> {
    public FocusMeAdapter(@Nullable List<HuDongNoticeList> data) {
        super(R.layout.adapter_item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HuDongNoticeList item) {
        helper.getView(R.id.imv_replay).setVisibility(View.GONE);
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imageCover = helper.getView(R.id.imv_cover);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_time);
        AtTextView tvDetail = helper.getView(R.id.tv_detail);
        AtTextView tvReplay = helper.getView(R.id.tv_replay);
        helper.addOnClickListener(R.id.imv_replay);

//        if (!TextViewUtil.isEmpty(item.getFirstImgUrl())) {
            imageCover.setVisibility(View.VISIBLE);
            Batman.getInstance().getImageWithRoundLeft(item.getFirstImgUrl(), imageCover, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default, UnitUtil.dip2px(mContext, 2));
//        } else {
//            imageCover.setVisibility(View.GONE);
//        }

        tvDetail.setText("在这条动态中@了你");

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);

        if (!TextViewUtil.isEmpty(item.getName())) {
            tvName.setText(item.getName());
        }

        if (!TextViewUtil.isEmpty(item.getDatetime())) {
            tvTime.setText(item.getDatetime());
        }

//        if (!TextViewUtil.isEmpty(item.getContent())) {
//            tvDetail.setStrings(item.getContent());
//        }

        String replay = null;
        if (!TextViewUtil.isEmpty(item.getMyName())) {
            replay = "@" + item.getMyName() + ":" ;
        }

        if (!TextViewUtil.isEmpty(item.getFirstContent())) {
            replay = replay+ item.getFirstContent();
        }

        tvReplay.setStrings(replay);
    }
}
