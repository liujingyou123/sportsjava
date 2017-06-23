package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.model.Shai;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ShallAdapter extends BaseQuickAdapter<Shai, BaseViewHolder> {
    public ShallAdapter(@Nullable List<Shai> data) {
        super(R.layout.item_adapter_shai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shai item) {
        Batman.getInstance().fromNet(item.imageUrl, (ImageView) helper.getView(R.id.imv_cover));
        Batman.getInstance().getImageWithCircle(item.avtorUrl, (ImageView) helper.getView(R.id.imv_head), 0, 0);
    }
}
