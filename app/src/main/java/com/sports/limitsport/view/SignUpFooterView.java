package com.sports.limitsport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/17.
 */

public class SignUpFooterView extends RelativeLayout {
    @BindView(R.id.tv_ticket_des)
    TextView tvTicketDes;
    @BindView(R.id.tv_ticket_nums)
    TextView tvTicketNums;
    @BindView(R.id.ncv)
    NumCheckView ncv;

    public SignUpFooterView(Context context) {
        super(context);
        initView();
    }

    public SignUpFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SignUpFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.sign_up_footer_view, this);
        ButterKnife.bind(this, this);
    }


    public void setNumCheckViewEnable(boolean enable) {
        ncv.setCanEnable(enable);
    }

    public NumCheckView getNumCheckView() {
        return ncv;
    }

    public void setTvTicketDes(String des) {
        tvTicketDes.setText(des);
    }
}
