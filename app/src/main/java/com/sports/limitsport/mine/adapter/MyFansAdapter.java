package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.FansList;
import com.sports.limitsport.util.TextViewUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/6.
 */

public class MyFansAdapter extends BaseQuickAdapter<FansList, BaseViewHolder> {
    public MyFansAdapter(@Nullable List<FansList> data) {
        super(R.layout.adapter_myfocus, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FansList item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imvGender = helper.getView(R.id.imv_gender);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvFocus = helper.getView(R.id.tv_focus);
        TextView tvLocation = helper.getView(R.id.tv_location);
        TextView tvDes = helper.getView(R.id.tv_des);

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);
        if ("0".equals(item.getSex())) { // 男
            imvGender.setVisibility(View.VISIBLE);
            imvGender.setSelected(false);
        } else if ("1".equals(item.getSex())) { //女
            imvGender.setVisibility(View.VISIBLE);
            imvGender.setSelected(true);
        } else {
            imvGender.setVisibility(View.GONE);
        }

        tvName.setText(item.getName());

        if ("0".equals(item.getStatus())) { //0:我不关注他 1:互相关注
            tvFocus.setText("进入主页");
            tvFocus.setSelected(true);
        } else if ("1".equals(item.getStatus())) {
            tvFocus.setText("互相关注");
            tvFocus.setSelected(false);
        } else {
            tvFocus.setVisibility(View.GONE);
        }

        if (!TextViewUtil.isEmpty(item.getCity())) {
            tvLocation.setText(item.getCity());

        }
        tvDes.setText(item.getIntroduction());
    }
}
