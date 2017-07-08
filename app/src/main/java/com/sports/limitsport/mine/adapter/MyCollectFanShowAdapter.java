package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyCollectFanShowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyCollectFanShowAdapter(@Nullable List<String> data) {
        super(R.layout.item_adapter_mycollectfanshow, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imvCover = helper.getView(R.id.imv_cover);
        ImageView imvHead = helper.getView(R.id.imv_head);

        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imvCover);
        Batman.getInstance().getImageWithCircle("http://up.qqjia.com/z/16/tu17317_45.png", imvHead, 0, 0);
    }
}
