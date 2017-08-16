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
        View noData = helper.getView(R.id.rl_nodata);
        View haveData = helper.getView(R.id.ll_havedata);
        if (item == null && getData().size() == 1) {
            noData.setVisibility(View.VISIBLE);
            haveData.setVisibility(View.GONE);
        } else {
            noData.setVisibility(View.GONE);
            haveData.setVisibility(View.VISIBLE);
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

            //TODO
//            Batman.getInstance().fromNet("http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg", imageView);

            Batman.getInstance().fromNet(item.getCoverUrl(), imageView);

            if ("0".equals(item.getMinMoney())) {
                tvPrice.setText("¥0");
            } else {
                tvPrice.setText("¥" + UnitUtil.formatSNum(item.getMinMoney()) + " - ¥" + UnitUtil.formatSNum(item.getMaxMoney()));
            }
        }
    }
}