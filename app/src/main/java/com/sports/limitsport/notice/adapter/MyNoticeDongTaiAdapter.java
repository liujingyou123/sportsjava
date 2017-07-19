package com.sports.limitsport.notice.adapter;

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
import com.sports.limitsport.mine.adapter.TagActivityAdapter;
import com.sports.limitsport.view.tagview.TagCloudLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/13.
 */

public class MyNoticeDongTaiAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MyNoticeDongTaiAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_mynotice, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.rl_perison);
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
