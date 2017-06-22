package com.sports.limitsport.activity.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.model.Act;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class ActivitysAdapter extends BaseQuickAdapter<Act, BaseViewHolder>{
    public ActivitysAdapter(List<Act> data) {
        super(R.layout.item_adapter,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Act item) {
//        int position = helper.getLayoutPosition();
        ImageView imvCover = helper.getView(R.id.imv_cover);
//        RelativeLayout.LayoutParams lp = null;
//        if (position == 0) {
//            lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 560);
//        } else {
//            lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 690);
//        }
//
//        imvCover.setLayoutParams(lp);

        Batman.getInstance().fromNetWithFitCenter(item.imageUrl, imvCover);
    }
}
