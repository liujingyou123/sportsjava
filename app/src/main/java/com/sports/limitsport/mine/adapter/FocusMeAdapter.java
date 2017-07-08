package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.model.FocusMe;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/6.
 */

public class FocusMeAdapter extends BaseQuickAdapter<FocusMe, BaseViewHolder> {
    public FocusMeAdapter(@Nullable List<FocusMe> data) {
        super(R.layout.adapter_item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FocusMe item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imageView = helper.getView(R.id.imv_cover);
        TextView textView = helper.getView(R.id.tv_detail);
        textView.setText("在这条动态中@了你");
        helper.getView(R.id.imv_replay).setVisibility(View.GONE);
        Batman.getInstance().getImageWithRoundLeft("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imageView,0,0,0);
        Batman.getInstance().getImageWithCircle("http://up.qqjia.com/z/16/tu17317_45.png", imvHead,0,0);
    }
}
