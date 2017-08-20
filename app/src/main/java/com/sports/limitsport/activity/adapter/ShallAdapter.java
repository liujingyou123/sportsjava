package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.DongTaiList;

import java.util.List;

/**
 * Created by liuworkmac on 17/6/22.
 */

public class ShallAdapter extends BaseQuickAdapter<DongTaiList, BaseViewHolder> {
    public ShallAdapter(@Nullable List<DongTaiList> data) {
        super(R.layout.item_adapter_shai, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DongTaiList item) {
        ImageView imvCover = helper.getView(R.id.imv_cover);
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imvZan = helper.getView(R.id.imv_zan);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvDes = helper.getView(R.id.tv_des);
        TextView tvPraise = helper.getView(R.id.tv_praise);
        if (item.getResourceType() == 1) { //1 图片 2:视频
            Batman.getInstance().fromNet(item.getImgUrl(), imvCover, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default);
        } else {
            Batman.getInstance().fromNet(item.getVedioThumbnailUrl(), imvCover, R.mipmap.icon_ar_default, R.mipmap.icon_ar_default);
        }

        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, R.mipmap.icon_gerenzhuye_morentouxiang, R.mipmap.icon_gerenzhuye_morentouxiang);

        tvName.setText(item.getPublishUserName());
        tvDes.setText(item.getTitle());
        tvPraise.setText(item.getPraiseNum() + "");
        item.setPraiseFlag("1");
        if ("1".equals(item.getPraiseFlag())) { // 已点赞
            tvPraise.setSelected(true);
            imvZan.setSelected(true);
        } else {
            tvPraise.setSelected(false);
            imvZan.setSelected(false);
        }

//        //TODO test
//        Batman.getInstance().fromNet("http://img.sc115.com/wm/xqx/pic1/15011vd5vam10fg.jpg", imvCover);
//        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);
    }
}
