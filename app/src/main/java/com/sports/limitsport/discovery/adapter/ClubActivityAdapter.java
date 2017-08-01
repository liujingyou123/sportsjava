package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.util.UnitUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/1.
 */

public class ClubActivityAdapter extends BaseQuickAdapter<Act, BaseViewHolder> {
    public ClubActivityAdapter(@Nullable List<Act> data) {
        super(R.layout.item_adapter_mycollectactivity, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Act item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        helper.getView(R.id.tv_status).setVisibility(View.GONE);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvAddress = helper.getView(R.id.tv_address);
        TextView tvPrice = helper.getView(R.id.tv_price);

        tvAddress.setText("活动地：" + item.getAddress());
        tvTime.setText(item.getStartDate()
                + " " + UnitUtil.stringToWeek(item.getWeek()) + " " + item.getStartTime());
        tvName.setText(item.getName());
        Batman.getInstance().fromNet(item.getCoverUrl(), imageView);

        if ("0".equals(item.getMinMoney())) {
            tvPrice.setText("¥0");
        } else {
            tvPrice.setText("¥" + UnitUtil.formatSNum(item.getMinMoney()) + " - ¥" + UnitUtil.formatSNum(item.getMaxMoney()));
        }

    }
}