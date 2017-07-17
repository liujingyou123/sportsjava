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
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.adapter.TagActivityAdapter;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/6/23.
 */

public class AllShaiAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AllShaiAdapter(@Nullable List<String> data) {
        super(R.layout.item_adapter_all_shai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", (ImageView) helper.getView(R.id.imv_head), 0, 0);
        ImageView imageView = helper.getView(R.id.imv_cover);
        TextView tvDes = helper.getView(R.id.tv_des);
        TagCloudLayout tagCloudLayout = helper.getView(R.id.tg_tag);
        LinearLayout llCall = helper.getView(R.id.ll_recall);

        Batman.getInstance().fromNet("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imageView);
        List<String> tags = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tags.add("参见的滑雪第二期");
        }
        tagCloudLayout.setAdapter(new TagActivityAdapter(this.mContext, tags));
        llCall.removeAllViews();
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
