package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.PraiseList;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/9.
 */

public class PraiseHeadAdapter extends BaseQuickAdapter<PraiseList, BaseViewHolder> {
    public PraiseHeadAdapter(@Nullable List<PraiseList> data) {
        super(R.layout.adapter_names, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PraiseList item) {
        ImageView imageView = helper.getView(R.id.imv_head);
        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imageView, 0, 0);
    }
}