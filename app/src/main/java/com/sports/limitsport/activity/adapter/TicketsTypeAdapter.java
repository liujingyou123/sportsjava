package com.sports.limitsport.activity.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseSelectionAdapter;
import com.sports.limitsport.base.SelectEntity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.model.TicketList;
import com.sports.limitsport.util.UnitUtil;
import com.sports.limitsport.view.CustomTypeFaceTextView;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/17.
 */

public class TicketsTypeAdapter extends BaseSelectionAdapter<TicketList, BaseViewHolder> {
    public TicketsTypeAdapter(@Nullable List<TicketList> data) {
        super(R.layout.activity_ticket_type, data, true);
    }

    @Override
    protected void convert(BaseViewHolder helper, TicketList item) {
        super.convert(helper, item);

        XLog.e("position = " + helper.getAdapterPosition());
        CustomTypeFaceTextView textView = helper.getView(R.id.tv_ticket_type_one);
        textView.setSelected(item.isSelect);
        textView.setCustomText("￥" + UnitUtil.formatSNum(item.getMoney()) + "\n" + item.getName());
    }

    @Override
    public int getSelectId() {
        return R.id.ll_parent;
    }
}
