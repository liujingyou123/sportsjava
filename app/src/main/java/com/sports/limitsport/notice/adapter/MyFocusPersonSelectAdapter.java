package com.sports.limitsport.notice.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.image.Batman;
import com.sports.limitsport.notice.model.FocusPerson;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/14.
 */

public class MyFocusPersonSelectAdapter extends BaseSelectionAdapter<FocusPerson, BaseViewHolder> {

    public MyFocusPersonSelectAdapter(@Nullable List<FocusPerson> data) {
        super(R.layout.adpater_select_myfocus, data, true);
    }

    @Override
    protected void convert(BaseViewHolder helper, FocusPerson item) {
        super.convert(helper, item);
        ImageView imageView = helper.getView(R.id.imv_checked);
        ImageView imvHead = helper.getView(R.id.imv_head);
        imageView.setSelected(item.isSelect);

        Batman.getInstance().getImageWithCircle("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2470615589,4205272766&fm=26&gp=0.jpg", imvHead, 0, 0);

    }

    @Override
    public int getSelectId() {
        return R.id.imv_checked;
    }
}
