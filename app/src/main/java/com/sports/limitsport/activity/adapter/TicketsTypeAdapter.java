package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.base.SelectEntity;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/17.
 */

public class TicketsTypeAdapter extends BaseSelectionAdapter<SelectEntity, BaseViewHolder> {
    public TicketsTypeAdapter(@Nullable List<SelectEntity> data) {
        super(R.layout.activity_ticket_type, data, true);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectEntity item) {
        super.convert(helper, item);

        TextView textView = helper.getView(R.id.tv_ticket_type_one);
        textView.setSelected(item.isSelect);
    }
}
