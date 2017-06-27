package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.activity.model.OrderInfo;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.util.UnitUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/6/27.
 */

public class OrderInfoView extends LinearLayout {
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.et_phone)
    ExtendedEditText etPhone;
    @BindView(R.id.et_name)
    ExtendedEditText etName;
    @BindView(R.id.et_ids)
    ExtendedEditText etIds;

    private OrderInfo orderInfo;

    public OrderInfoView(Context context, OrderInfo orderInfo) {
        super(context);
        this.orderInfo = orderInfo;
        initView();
    }

    public OrderInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public OrderInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(VERTICAL);
        int padding = UnitUtil.dip2px(getContext(), 10);
        setPadding(padding, padding, padding, padding);
        LayoutInflater.from(getContext()).inflate(R.layout.item_adapter_orders, this);
        ButterKnife.bind(this, this);

        String position = UnitUtil.formatDec(orderInfo.id);
        tvNum.setText("用户"+position);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                orderInfo.phone = s.toString();
            }
        });

        etName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                orderInfo.names = s.toString();
            }
        });

        etIds.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                orderInfo.idCard = s.toString();
            }
        });
    }


}
