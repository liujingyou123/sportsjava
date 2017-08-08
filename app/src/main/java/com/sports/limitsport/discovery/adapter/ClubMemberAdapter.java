package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.ClubMemberList;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/8.
 */

public class ClubMemberAdapter extends BaseQuickAdapter<ClubMemberList, BaseViewHolder> {
    public ClubMemberAdapter(@Nullable List<ClubMemberList> data) {
        super(R.layout.adapter_names, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClubMemberList item) {
        ImageView imageView = helper.getView(R.id.imv_head);
        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imageView, 0, 0);
    }
}