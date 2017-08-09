package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.ClubMemberList;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/8.
 */

public class ClubMembersAdapter extends BaseQuickAdapter<ClubMemberList, BaseViewHolder> {
    public ClubMembersAdapter(@Nullable List<ClubMemberList> data) {
        super(R.layout.adapter_myfocus, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClubMemberList item) {

        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imvGender = helper.getView(R.id.imv_gender);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvFocus = helper.getView(R.id.tv_focus);
        TextView tvLocation = helper.getView(R.id.tv_location);
        TextView tvDes = helper.getView(R.id.tv_des);

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, 0, 0);
        if ("0".equals(item.getSex())) { // 男
            imvGender.setSelected(false);
        } else if ("1".equals(item.getSex())) { //女
            imvGender.setSelected(true);
        }

        tvName.setText(item.getMemberName());

        if ("0".equals(item.getAttentionStatus()) || "2".equals(item.getAttentionStatus())) { //0:互相不关注 1:我关注他 2:他关注我 3:互相关注
            tvFocus.setText("进入主页");
            tvFocus.setSelected(true);
        } else if ("1".equals(item.getAttentionStatus())) {
            tvFocus.setText("已关注");
            tvFocus.setSelected(false);
        } else if ("3".equals(item.getAttentionStatus())) {
            tvFocus.setText("互相关注");
            tvFocus.setSelected(false);
        } else {
            tvFocus.setVisibility(View.GONE);
        }

        tvLocation.setText(item.getProvince() + " " + item.getCity());
        tvDes.setText(item.getUserIntroduction());
    }
}
