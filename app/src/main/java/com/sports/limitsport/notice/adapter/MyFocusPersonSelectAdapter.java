package com.sports.limitsport.notice.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.FansList;
import com.sports.limitsport.util.TextViewUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/14.
 */

public class MyFocusPersonSelectAdapter extends BaseSelectionAdapter<FansList, BaseViewHolder> {

    public MyFocusPersonSelectAdapter(@Nullable List<FansList> data) {
        super(R.layout.adpater_select_myfocus, data, false);
    }

    @Override
    protected void convert(BaseViewHolder helper, FansList item) {
        super.convert(helper, item);
        XLog.e("position = " + helper.getAdapterPosition());

        ImageView imageView = helper.getView(R.id.imv_checked);
        imageView.setSelected(item.isSelect);

        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imvGender = helper.getView(R.id.imv_gender);
        TextView tvName = helper.getView(R.id.tv_name);
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

        if (!TextViewUtil.isEmpty(item.getCity())) {
            tvLocation.setText(item.getCity());

        }
        tvDes.setText(item.getIntroduction());
    }

    @Override
    public int getSelectId() {
        return R.id.imv_checked;
    }

}
