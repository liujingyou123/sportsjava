package com.sports.limitsport.activity.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.ScaleImageView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class ActivitysAdapter extends BaseQuickAdapter<Act, BaseViewHolder> {
    public ActivitysAdapter(List<Act> data) {
        super(R.layout.item_adapter, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Act item) {
        ImageView imvCover = helper.getView(R.id.imv_cover);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_date);
        TextView tvAddress = helper.getView(R.id.tv_address);
        TextView tvFree = helper.getView(R.id.tv_no_money);
        TextView tvPrice = helper.getView(R.id.tv_money);
        TextView tvSigning = helper.getView(R.id.tv_signing);
        TextView tvTip = helper.getView(R.id.tv_status_tip);
        View view = helper.getView(R.id.ll_price);

        tvSigning.setVisibility(View.GONE);
        tvTip.setVisibility(View.GONE);
        tvTime.setVisibility(View.GONE);
        view.setVisibility(View.GONE);

        if ("0".equals(item.getMinMoney())) {
            tvFree.setVisibility(View.VISIBLE);
            tvPrice.setText("¥0");
        } else {
            tvFree.setVisibility(View.GONE);
            tvPrice.setText("¥" + UnitUtil.formatSNum(item.getMinMoney()) + " - ¥" + UnitUtil.formatSNum(item.getMaxMoney()));
        }

        if ("1".equals(item.getStatus())) { //报名中

            if (item.getTicketNum() > 0) {
                tvSigning.setVisibility(View.VISIBLE);
                tvTip.setVisibility(View.GONE);
                tvTime.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);

            } else {
                tvSigning.setVisibility(View.GONE);
                tvTip.setText("名额已抢完");
                tvTip.setVisibility(View.VISIBLE);
                tvTime.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
            }

        } else {
            tvSigning.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
            if ("3".equals(item.getStatus())) { // 已完成
                tvTip.setVisibility(View.VISIBLE);
                tvTip.setText("活动已结束");
            }
        }

//        imvCover.setInitSize(item.getWidth(), item.getHeight());

        if (!TextViewUtil.isEmpty(item.getCoverUrl())) {
            Batman.getInstance().fromNet(item.getCoverUrl(), imvCover);
        } else {
            Batman.getInstance().fromNet(item.getActivityVideoImg(), imvCover);
        }

        tvName.setText(item.getName());
//        Calendar calendar = UnitUtil.dateToCalendar(UnitUtil.stringToDate(item.getStartDate()));
        tvTime.setText(item.getStartDate()
                + " " + UnitUtil.stringToWeek(item.getWeek()) + " " + item.getStartTime());
        tvAddress.setText("活动地:" + item.getAddress());

    }
}
