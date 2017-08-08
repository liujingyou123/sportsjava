package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.Club;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyClubsAdapter extends BaseQuickAdapter<Club, BaseViewHolder> {
    public MyClubsAdapter(@Nullable List<Club> data) {
        super(R.layout.adapter_myclubs, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Club club) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvDes = helper.getView(R.id.tv_location);
        TextView tvNumbers = helper.getView(R.id.tv_des);
        TextView tvTip = helper.getView(R.id.tv_tip);
        TextView tvFocus = helper.getView(R.id.tv_focus);

        Batman.getInstance().getImageWithCircle(club.getLogoUrl(), imvHead, 0, 0);
        tvName.setText(club.getClubName());
        tvDes.setText(club.getClubIntroduction());
        tvNumbers.setText(club.getMemberNum() + "成员");
        if ("1".equals(club.getIsActivity())) {//1:活动中 0:没有活动
            tvTip.setVisibility(View.VISIBLE);
        } else {
            tvTip.setVisibility(View.GONE);
        }

        tvFocus.setEnabled(false);
        tvFocus.setText("已加入");
    }
}
