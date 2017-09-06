package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.SignUpUser;
import com.sports.limitsport.util.TextViewUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/6/23.
 */

public class SignUpAdapter extends BaseQuickAdapter<SignUpUser, BaseViewHolder> {
    public SignUpAdapter(@Nullable List<SignUpUser> data) {
        super(R.layout.adapter_myfocus, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SignUpUser item) {

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

        tvName.setText(item.getName());

        helper.addOnClickListener(R.id.tv_focus);

        if ("0".equals(item.getStatus()) || "2".equals(item.getStatus())) { //0:互相不关注 1:我关注他 2:他关注我 3:互相关注
            tvFocus.setText("进入主页");
            tvFocus.setEnabled(true);
        } else if ("1".equals(item.getStatus())) {
            tvFocus.setText("已关注");
            tvFocus.setEnabled(false);
        } else if ("3".equals(item.getStatus())) {
            tvFocus.setText("互相关注");
            tvFocus.setEnabled(false);
        } else {
            tvFocus.setVisibility(View.GONE);
        }

        String location = "";
        if (!TextViewUtil.isEmpty(item.getProvince())) {
            location = item.getProvince();
        } else {
            location = "上海 ";
        }

        if (!TextViewUtil.isEmpty(item.getCity())) {
            location = location + item.getCity();
        }
        tvLocation.setText(location);
        tvDes.setText(item.getIntroduction());
    }
}
