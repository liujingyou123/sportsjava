package com.sports.limitsport.activity.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.activity.model.Shai;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.util.TextViewUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/6/23.
 */

public class AllShaiAdapter extends BaseQuickAdapter<Shai, BaseViewHolder> {
    public AllShaiAdapter(@Nullable List<Shai> data) {
        super(R.layout.item_adapter_all_shai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shai item) {
        LinearLayout llRecallone = helper.getView(R.id.ll_recallone);
        setRecall(llRecallone);

        helper.addOnClickListener(R.id.tv_more);
        Batman.getInstance().fromNet(item.imageUrl, (ImageView) helper.getView(R.id.imv_cover));
        Batman.getInstance().getImageWithCircle(item.avtorUrl, (ImageView) helper.getView(R.id.imv_head), 0, 0);
    }

    private void setRecall(LinearLayout view) {
        view.addView(getText("三三两两：", "我也爱滑雪"));
        view.addView(getText("三三两两：", "我也爱滑雪"));
        view.addView(getText("三三两两：", "我也爱滑雪"));
    }


    private TextView getText(String text1, String text2) {
        TextView textView = new TextView(mContext);
        textView.setText(text1 + text2);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setTextColor(Color.parseColor("#999999"));
        TextViewUtil.setPartialColor(textView, 0, text1.length(), Color.parseColor("#ffffff"));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);
        return textView;
    }
}
