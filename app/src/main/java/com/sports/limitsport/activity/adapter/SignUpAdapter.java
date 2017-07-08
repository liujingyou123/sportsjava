package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.model.SignUpUser;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/6/23.
 */

public class SignUpAdapter extends BaseQuickAdapter<SignUpUser, BaseViewHolder> {
    public SignUpAdapter(@Nullable List<SignUpUser> data) {
        super(R.layout.item_adapter_signups, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SignUpUser item) {
        helper.addOnClickListener(R.id.tv_focus);
        Batman.getInstance().getImageWithCircle(item.imgUser, (ImageView) helper.getView(R.id.imv_head), 0, 0);
    }
}
