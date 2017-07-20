package com.sports.limitsport.notice.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.base.SelectEntity;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/14.
 */

public class SelectMyJoinAdapter extends BaseSelectionAdapter<SelectEntity, BaseViewHolder> {
    public SelectMyJoinAdapter(@Nullable List<SelectEntity> data) {
        super(R.layout.adapter_selectmyjoin, data, false);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectEntity item) {
        super.convert(helper, item);

        ImageView imageView = helper.getView(R.id.imv_checked);
        ImageView imvCover = helper.getView(R.id.imv_cover);
        imageView.setSelected(item.isSelect);
        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imvCover);

    }

    @Override
    public int getSelectId() {
        return R.id.imv_checked;
    }
}
