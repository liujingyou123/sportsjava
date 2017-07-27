package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.model.FineShowList;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/10.
 * 发现 头条
 */

public class FineShowAdapter extends BaseQuickAdapter<FineShowList, BaseViewHolder> {
    public FineShowAdapter(@Nullable List<FineShowList> data) {
        super(R.layout.fineshow, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FineShowList item) {
        ImageView imvCover = helper.getView(R.id.imv_cover);
        ImageView imvHead = helper.getView(R.id.imv_head);

        TextView tvStatus = helper.getView(R.id.tv_status);
        TextView tvNum = helper.getView(R.id.tv_num);
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvName = helper.getView(R.id.tv_name);

        tvStatus.setVisibility(View.GONE);
        if (item.getResourceType() == 1) { //图片
            //TODO 正式
//            Batman.getInstance().fromNet(item.getImgUrl(), imvCover);

        } else if (item.getResourceType() == 2) { //视频
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText("视频");
            ////TODO 正式
//            Batman.getInstance().fromNet(item.getVedioThumbnailUrl(), imvCover);
        }

        tvNum.setText(item.getPraiseNum() + "");
        tvTitle.setText(item.getTitle());
        tvName.setText(item.getPublishUserName());
        Batman.getInstance().getImageWithCircle(item.getHeadPortrait(), imvHead, 0, 0);

        //TODO 测试用
        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imvCover);
//        Batman.getInstance().getImageWithCircle("http://up.qqjia.com/z/16/tu17317_45.png", imvHead, 0, 0);
    }
}
