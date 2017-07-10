package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.model.Club;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class ClubAdapter extends BaseQuickAdapter<Club, BaseViewHolder> {
    public ClubAdapter(@Nullable List<Club> data) {
        super(R.layout.adapter_club, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Club item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imageView);

        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", (ImageView) helper.getView(R.id.imv_head), 0, 0);

    }
}
