package com.sports.limitsport.main.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.Hobby;

import java.util.List;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class HobbyAdapter extends BaseQuickAdapter<Hobby, BaseViewHolder> {
    public HobbyAdapter(@Nullable List<Hobby> data) {
        super(R.layout.adapter_hobby, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Hobby item) {
        TextView textView = helper.getView(R.id.tv_hobby);
        ImageView imageView = helper.getView(R.id.imv_head);
        helper.addOnClickListener(R.id.tv_hobby);
        if (item.isChecked) {
            textView.setSelected(true);
            textView.setText("");
        } else {
            textView.setSelected(false);
            textView.setText(item.name);
        }
        Batman.getInstance().getImageWithCircle(item.imgUrl, imageView, 0, 0);

    }
}
