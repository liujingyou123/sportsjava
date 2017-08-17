package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.OrdersList;
import com.sports.limitsport.util.UnitUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyActivitysAdapter extends BaseQuickAdapter<OrdersList, BaseViewHolder> {
    public MyActivitysAdapter(@Nullable List<OrdersList> data) {
        super(R.layout.adapter_myactivity, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrdersList item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        TextView tvOrderNum = helper.getView(R.id.tv_ordernum);
        TextView tvStatus = helper.getView(R.id.tv_order_status);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTicketName = helper.getView(R.id.tv_ticket_type);
        TextView tvTicketPrice = helper.getView(R.id.tv_ticket_price);
        TextView tvTotalPrice = helper.getView(R.id.tv_all_price);
        TextView tvPayPrice = helper.getView(R.id.tv_pay_price);

        Batman.getInstance().fromNet(item.getCoverUrl(), imageView);

        tvOrderNum.setText("订单编号：" + item.getOrderNo());
        tvName.setText(item.getName());
        tvTicketName.setText("票务类型：" + item.getTicketsName());
        tvTicketPrice.setText(UnitUtil.formatSNum(item.getMoneyNews()) + " 元 （" + item.getNumber() + "张）");
        tvTotalPrice.setText("¥" + UnitUtil.formatSNum(item.getTotalMoney()));

        helper.addOnClickListener(R.id.tv_pay_price);
        if (item.getIsJoin() == 1) { //已参加
            tvStatus.setText("已参加");
            tvPayPrice.setText("秀动态");
            tvPayPrice.setVisibility(View.VISIBLE);
        } else {  //(0待支付 1已支付 2订单关闭3:已取消 4:退款中 5:已退款)
            if ("0".equals(item.getOrderStatus())) {
                tvStatus.setText("待支付");
                tvPayPrice.setText("付款" + UnitUtil.formatSNum(item.getReceptMoney()));
                tvPayPrice.setVisibility(View.VISIBLE);
            } else if ("1".equals(item.getOrderStatus())) {
                tvStatus.setText("已报名");
                tvPayPrice.setVisibility(View.GONE);
            } else if ("2".equals(item.getOrderStatus())) {
                tvStatus.setText("已关闭");
                tvPayPrice.setVisibility(View.GONE);
            } else if ("3".equals(item.getOrderStatus())) {
                tvStatus.setText("已取消");
                tvPayPrice.setVisibility(View.GONE);
            } else if ("4".equals(item.getOrderStatus())) {
                tvStatus.setText("退款中");
                tvPayPrice.setVisibility(View.GONE);
            } else if ("5".equals(item.getOrderStatus())) {
                tvStatus.setText("已退款");
                tvPayPrice.setVisibility(View.GONE);
            }
        }
    }
}
