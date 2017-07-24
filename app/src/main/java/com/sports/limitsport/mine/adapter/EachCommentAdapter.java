package com.sports.limitsport.mine.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.mine.model.Comment;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/6.
 */

public class EachCommentAdapter extends BaseQuickAdapter<Comment, BaseViewHolder> {
    public EachCommentAdapter(@Nullable List<Comment> data) {
        super(R.layout.adapter_item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imageView = helper.getView(R.id.imv_cover);
        Batman.getInstance().getImageWithRoundLeft("http://img1.juimg.com/160806/355860-160P620130540.jpg", imageView,0,0,0);
        Batman.getInstance().getImageWithCircle("http://up.qqjia.com/z/16/tu17317_45.png", imvHead,0,0);
    }
}
