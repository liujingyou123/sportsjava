package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.NoticeList;
import com.sports.limitsport.util.TextViewUtil;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeList, BaseViewHolder> {
    public NoticeAdapter(@Nullable List<NoticeList> data) {
        super(R.layout.adapter_noticetwo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeList item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvDes = helper.getView(R.id.tv_des);

        if (!TextViewUtil.isEmpty(item.getCoverUrl())) {
            imageView.setVisibility(View.VISIBLE);
            Batman.getInstance().getImageWithRoundBottom(item.getCoverUrl(), imageView, 0, 0, 0);
        } else {
            imageView.setVisibility(View.GONE);
        }

        if (!TextViewUtil.isEmpty(item.getTitle())) {
            tvTitle.setText(item.getTitle());
        }

        if (!TextViewUtil.isEmpty(item.getDateTime())) {
            tvTime.setText(item.getDateTime());
        }

        if (!TextViewUtil.isEmpty(item.getContent())) {
            tvDes.setText(item.getContent());
        }

    }
}
