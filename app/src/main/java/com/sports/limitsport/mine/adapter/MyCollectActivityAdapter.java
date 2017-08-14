package com.sports.limitsport.mine.adapter;

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
import com.sports.limitsport.view.ScaleImageView;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyCollectActivityAdapter extends BaseQuickAdapter<Act, BaseViewHolder> {
    public MyCollectActivityAdapter(@Nullable List<Act> data) {
        super(R.layout.item_adapter_mycollectactivity, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Act item) {
        ImageView imageView = helper.getView(R.id.imv_cover);

        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvAddress = helper.getView(R.id.tv_address);
        TextView tvPrice = helper.getView(R.id.tv_price);
        TextView tvStatus = helper.getView(R.id.tv_status);

        tvStatus.setVisibility(View.GONE);

        if ("0".equals(item.getMinMoney())) {
            tvPrice.setText("¥0");
        } else {
            tvPrice.setText("¥" + UnitUtil.formatSNum(item.getMinMoney()) + " - ¥" + UnitUtil.formatSNum(item.getMaxMoney()));
        }

        if ("1".equals(item.getStatus())) { //报名中

            if (item.getTicketNum() > 0) {
                tvStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("报名中");

            } else {
                tvStatus.setVisibility(View.VISIBLE);
                tvStatus.setText("名额已抢完");
            }

        } else {
            tvStatus.setVisibility(View.GONE);
        }

        //TODO 测试
//        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imageView);

        //正式
        Batman.getInstance().fromNetWithFitCenter(item.getCoverUrl(), imageView);

        tvName.setText(item.getName());
        tvTime.setText(item.getStartDate()
                + " " + UnitUtil.stringToWeek(item.getWeek()) + " " + item.getStartTime());
        tvAddress.setText("活动地:" + item.getAddress());


    }
}
