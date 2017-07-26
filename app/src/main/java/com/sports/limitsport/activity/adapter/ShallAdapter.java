package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.DongTaiList;

import java.util.List;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ShallAdapter extends BaseQuickAdapter<DongTaiList, BaseViewHolder> {
    public ShallAdapter(@Nullable List<DongTaiList> data) {
        super(R.layout.item_adapter_shai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DongTaiList item) {
        Batman.getInstance().fromNet("http://img.sc115.com/wm/xqx/pic1/15011vd5vam10fg.jpg", (ImageView) helper.getView(R.id.imv_cover));
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", (ImageView) helper.getView(R.id.imv_head), 0, 0);
    }
}
