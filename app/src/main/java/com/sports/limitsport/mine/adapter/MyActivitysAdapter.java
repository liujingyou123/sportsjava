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

public class MyActivitysAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyActivitysAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_myactivity, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imageView);
    }
}
