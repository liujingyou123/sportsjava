package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.model.Club;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by jingyouliu on 17/7/9.
 * 头条俱乐部
 */

public class ClubAdapter extends BaseQuickAdapter<Club, BaseViewHolder> {
    public ClubAdapter(@Nullable List<Club> data) {
        super(R.layout.adapter_club, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Club item) {
        ImageView imageView = helper.getView(R.id.imv_cover);
        ImageView imvHead = helper.getView(R.id.imv_head);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvSub = helper.getView(R.id.tv_sub);

        //TODO 正式
//        Batman.getInstance().fromNet(item.getClubImgUrl(), imageView);
//        Batman.getInstance().getImageWithCircle(item.getLogoUrl(), imvHead, 0, 0);
        tvName.setText(item.getClubTypeName());
        tvSub.setText(item.getMemberNum() + "人已加入");

        //TODO  测试用
        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imageView);
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", (ImageView) helper.getView(R.id.imv_head), 0, 0);

    }
}
