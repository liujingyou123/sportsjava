package com.sports.limitsport.notice.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.RecommendDongTai;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/10.
 */

public class RecommendDongTaiAdapter extends BaseQuickAdapter<RecommendDongTai, BaseViewHolder> {

    public RecommendDongTaiAdapter(@Nullable List<RecommendDongTai> data) {
        super(R.layout.adapter_recommenddonttai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendDongTai item) {
        ImageView imvCover = helper.getView(R.id.imv_cover);
        if (item.getResourceType() == 1) {
            Batman.getInstance().fromNet(item.getImgUrl(), imvCover, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default);
        } else {
            Batman.getInstance().fromNet(item.getVedioImgUrl(), imvCover, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default);
        }
    }
}
