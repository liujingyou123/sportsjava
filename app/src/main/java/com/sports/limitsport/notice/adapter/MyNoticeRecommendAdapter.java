package com.sports.limitsport.notice.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.image.Batman;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/13.
 * 为你推荐
 */

public class MyNoticeRecommendAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyNoticeRecommendAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_mynoticerecommedn, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imvHead = helper.getView(R.id.imv_head);
        ImageView imvOne = helper.getView(R.id.imv_one);
        ImageView imvTwo = helper.getView(R.id.imv_two);
        ImageView imvThr = helper.getView(R.id.imv_three);

        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);


        Batman.getInstance().fromNet("http://img1.imgtn.bdimg.com/it/u=3379243146,3618355193&fm=26&gp=0.jpg", imvOne);
        Batman.getInstance().fromNet("http://img2.imgtn.bdimg.com/it/u=3218537248,3722272286&fm=26&gp=0.jpg", imvTwo);
        Batman.getInstance().fromNet("http://img2.imgtn.bdimg.com/it/u=2213378101,4219446893&fm=26&gp=0.jpg", imvThr);
    }
}
