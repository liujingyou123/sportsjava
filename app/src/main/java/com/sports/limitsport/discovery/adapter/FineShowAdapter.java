package com.sports.limitsport.discovery.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

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

        Batman.getInstance().fromNet("http://img1.juimg.com/160806/355860-160P620130540.jpg", imvCover);
        Batman.getInstance().getImageWithCircle("http://up.qqjia.com/z/16/tu17317_45.png", imvHead, 0, 0);
    }
}
