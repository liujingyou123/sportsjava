package com.sports.limitsport.mine.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.model.Dongtai;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/3.
 */

public class MineAdapter extends BaseQuickAdapter<Dongtai, BaseViewHolder> {

    public MineAdapter(@Nullable List<Dongtai> data) {
        super(R.layout.adapter_dongtai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Dongtai item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        TextView tvDes = helper.getView(R.id.tv_des);
        TagCloudLayout tagCloudLayout = helper.getView(R.id.tg_tag);
        LinearLayout llCall = helper.getView(R.id.ll_recall);

        Batman.getInstance().fromNet(item.imgUrl, imageView);
        tagCloudLayout.setAdapter(new TagActivityAdapter(this.mContext, item.activitys));
        llCall.removeAllViews();
        for(int i=0; i<item.recalls.size();i++) {
            TextView tv = getTextView();
            tv.setText(item.recalls.get(i));
            llCall.addView(tv);
        }

    }

    private TextView getTextView() {
        TextView textView = new TextView(mContext);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(Color.parseColor("#A5A4A4"));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 0);
        textView.setLayoutParams(lp);
        return textView;
    }
}
