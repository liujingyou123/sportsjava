package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.model.NoticeTwo;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/5.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeTwo, BaseViewHolder> {
    public NoticeAdapter(@Nullable List<NoticeTwo> data) {
        super(R.layout.adapter_noticetwo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeTwo item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        Batman.getInstance().getImageWithRoundBottom("http://pic.jj20.com/up/allimg/911/0P316142450/160P3142450-4.jpg", imageView,0,0,0);

    }
}
