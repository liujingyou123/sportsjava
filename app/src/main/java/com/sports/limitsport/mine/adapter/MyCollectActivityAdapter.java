package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyCollectActivityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyCollectActivityAdapter(@Nullable List<String> data) {
        super(R.layout.item_adapter_mycollectactivity, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imageView);

    }
}
