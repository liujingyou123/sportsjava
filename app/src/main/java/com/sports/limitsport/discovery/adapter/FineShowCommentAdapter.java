package com.sports.limitsport.discovery.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/12.
 */

public class FineShowCommentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FineShowCommentAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_findshow_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.imv_head);
        View viewline = helper.getView(R.id.view_line);
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imageView, 0, 0);
        int postion = helper.getAdapterPosition();
        LinearLayout llCall = helper.getView(R.id.ll_recall);
        llCall.removeAllViews();

        if (postion %2 !=0) {
            viewline.setVisibility(View.VISIBLE);
            for (int i=0; i<2; i++) {
                llCall.addView(getTextView());
            }
        } else {
            viewline.setVisibility(View.GONE);
        }

    }


    private TextView getTextView() {
        TextView textView = new TextView(mContext);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(Color.parseColor("#A5A4A4"));

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 10, 0, 0);
        textView.setLayoutParams(lp);
        textView.setText("三三两来：我也爱滑雪");
        return textView;
    }
}
