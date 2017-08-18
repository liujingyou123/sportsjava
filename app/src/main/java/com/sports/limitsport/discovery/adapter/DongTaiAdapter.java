package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.DongTaiList;
import com.sports.limitsport.util.MyTestData;
import com.sports.limitsport.util.TextViewUtil;
import com.sports.limitsport.view.ScaleImageView;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/10.
 */

public class DongTaiAdapter extends BaseQuickAdapter<DongTaiList, BaseViewHolder> {
    public DongTaiAdapter(@Nullable List<DongTaiList> data) {
        super(R.layout.adapter_new_dongtai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DongTaiList item) {
        ScaleImageView imvCover = helper.getView(R.id.imv_cover);
        ImageView imvHead = helper.getView(R.id.imv_head);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvTitle = helper.getView(R.id.tv_title);
        ImageView imvZan = helper.getView(R.id.imv_zan);
        TextView tvSan = helper.getView(R.id.tv_san);

        helper.addOnClickListener(R.id.imv_cover);
        helper.addOnClickListener(R.id.imv_head);
        helper.addOnClickListener(R.id.tv_name);
        helper.addOnClickListener(R.id.imv_zan);
        helper.addOnClickListener(R.id.tv_san);

        imvCover.setInitSize(item.getWidth(), item.getHeight());
        int position = helper.getAdapterPosition();
        Batman.getInstance().fromNet(MyTestData.getData().get(position), imvCover, 0, 0, item.getWidth(), item.getHeight());


//        if (item.getResourceType() == 1) { //1 图片 2:视频
//            Batman.getInstance().fromNet(item.getImgUrl(), imvCover, 0, 0, item.getWidth(), item.getHeight());
//        } else {
//            Batman.getInstance().fromNet(item.getVedioThumbnailUrl(), imvCover, 0, 0, item.getWidth(), item.getHeight());
//        }

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, 0, 0);

        if (!TextViewUtil.isEmpty(item.getPublishUserName())) {
            tvName.setText(item.getPublishUserName());
        }

        if (!TextViewUtil.isEmpty(item.getContent())) {
            tvTitle.setText(item.getContent());
        }

        tvSan.setText(item.getPraiseNum() + "");
        if ("1".equals(item.getPraiseFlag())) { //1:已点赞 0:未点赞
            imvZan.setSelected(true);
            tvSan.setSelected(true);
        } else {
            imvZan.setSelected(false);
            tvSan.setSelected(false);
        }
    }
}
