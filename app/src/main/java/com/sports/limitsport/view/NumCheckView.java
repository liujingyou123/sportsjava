package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sports.limitsport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/6/26.
 */

public class NumCheckView extends LinearLayout {
    @BindView(R.id.tv_sub)
    TextView tvSub;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_add)
    TextView tvAdd;

    private int num;
    public NumCheckView(Context context) {
        super(context);
        initView();
    }

    public NumCheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NumCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.view_numcheck, this);
        ButterKnife.bind(this, this);
    }

    @OnClick({R.id.tv_sub, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sub:
                subNum();
                break;
            case R.id.tv_add:
                addNum();
                break;
        }
    }

    private void addNum() {
        num++;
        tvNum.setText(num+"");

        if (num > 0) {
            tvSub.setEnabled(true);
        }
    }

    private void subNum() {
        if (num > 0) {
            num --;
            tvNum.setText(num+"");
        } else {
            tvSub.setEnabled(false);
        }
    }

    public int getNum() {
        return num;
    }
}
