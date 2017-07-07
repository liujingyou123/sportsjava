package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.model.Fans;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/6.
 */

public class FansAdapter extends BaseQuickAdapter<Fans, BaseViewHolder> {
    public FansAdapter(@Nullable List<Fans> data) {
        super(R.layout.item_adapter_signups, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Fans item) {
        TextView textView = helper.getView(R.id.tv_focus);
        textView.setText("进入主页");
        helper.addOnClickListener(R.id.tv_focus);
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", (ImageView) helper.getView(R.id.imv_head), 0, 0);
    }
}
