package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/17.
 * 活动详情 讨论区
 */

public class ActivityDiscussAdapter extends BaseQuickAdapter<String, BaseViewHolder>{
    public ActivityDiscussAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_activity_discuss, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);
    }
}
