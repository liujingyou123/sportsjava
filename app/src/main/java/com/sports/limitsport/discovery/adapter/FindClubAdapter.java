package com.sports.limitsport.discovery.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.discovery.model.FindClubSection;
import com.sports.limitsport.image.Batman;

import java.util.List;


/**
 * Created by liuworkmac on 17/7/11.
 */

public class FindClubAdapter extends BaseSectionQuickAdapter<FindClubSection, BaseViewHolder> {

    public FindClubAdapter(List<FindClubSection> data) {
        super(R.layout.adapter_myclubs, R.layout.section_head, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, FindClubSection item) {
        TextView textView = helper.getView(R.id.tv_head);
        textView.setText(item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindClubSection item) {
        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", (ImageView) helper.getView(R.id.imv_head), 0, 0);
    }

}
