package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.model.DongTai;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.view.ScaleImageView;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/10.
 */

public class DongTaiAdapter extends BaseQuickAdapter<DongTai, BaseViewHolder> {
    public DongTaiAdapter(@Nullable List<DongTai> data) {
        super(R.layout.adapter_new_dongtai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DongTai item) {
        helper.addOnClickListener(R.id.imv_cover);
        helper.addOnClickListener(R.id.imv_head);
        helper.addOnClickListener(R.id.tv_name);

        ScaleImageView imvCover = helper.getView(R.id.imv_cover);
        ImageView imvHead = helper.getView(R.id.imv_head);
        imvCover.setInitSize(item.width, item.height);
        Batman.getInstance().fromNetWithFitCenter(item.imageUrl, imvCover);
        Batman.getInstance().getImageWithCircle("http://up.qqjia.com/z/16/tu17317_45.png", imvHead, 0, 0);
    }
}
