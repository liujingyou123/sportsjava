package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/14.
 * 他们也报名了
 */

public class NamesAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public NamesAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_names, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", (ImageView) helper.getView(R.id.imv_head), 0, 0);
    }
}
