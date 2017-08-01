package com.sports.limitsport.notice.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.Act;
import com.sports.limitsport.util.UnitUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/14.
 */

public class SelectMyJoinAdapter extends BaseSelectionAdapter<Act, BaseViewHolder> {
    public SelectMyJoinAdapter(@Nullable List<Act> data) {
        super(R.layout.adapter_selectmyjoin, data, true);
    }

    @Override
    protected void convert(BaseViewHolder helper, Act item) {
        super.convert(helper, item);
        ImageView imageView = helper.getView(R.id.imv_checked);
        ImageView imvCover = helper.getView(R.id.imv_cover);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTime = helper.getView(R.id.tv_time);


        imageView.setSelected(item.isSelect);
        Batman.getInstance().fromNet(item.getCoverUrl(), imvCover);
        tvName.setText(item.getName());
        tvTime.setText(item.getStartDate() + " " + UnitUtil.stringToWeek(item.getWeek()) + "" + item.getStartTime());

    }

    @Override
    public int getSelectId() {
        return R.id.imv_checked;
    }
}
