package com.sports.limitsport.main.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.Hobby;

import java.util.List;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class HobbyAdapter extends BaseSelectionAdapter<Hobby.DataBean, BaseViewHolder> {
    public HobbyAdapter(@Nullable List<Hobby.DataBean> data) {
        super(R.layout.adapter_hobby, data, false);
    }

    @Override
    protected void convert(BaseViewHolder helper, Hobby.DataBean item) {
        TextView textView = helper.getView(R.id.tv_hobby);
        ImageView imageView = helper.getView(R.id.imv_head);
        helper.addOnClickListener(R.id.tv_hobby);


        if (item.isSelect) {
            textView.setSelected(true);
            textView.setText("");
        } else {
            textView.setSelected(false);
            textView.setText(item.getHobby());
        }
        //TODO 正式
//        Batman.getInstance().getImageWithCircle(item.getImageUrl(), imageView, 0, 0);
        //TODO 正式
        Batman.getInstance().getImageWithCircle("http://up.qqjia.com/z/16/tu17317_45.png", imageView, 0, 0);

    }

    @Override
    public int getSelectId() {
        return R.id.tv_hobby;
    }
}
