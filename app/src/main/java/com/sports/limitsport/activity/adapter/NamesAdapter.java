package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.ActivityDetailResponse;
import com.sports.limitsport.model.ApplicantListBean;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/14.
 * 他们也报名了
 */

public class NamesAdapter extends BaseQuickAdapter<ApplicantListBean, BaseViewHolder> {
    public NamesAdapter(@Nullable List<ApplicantListBean> data) {
        super(R.layout.adapter_names, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplicantListBean item) {
        ImageView imageView = helper.getView(R.id.imv_head);
        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imageView, 0, 0);
    }
}
