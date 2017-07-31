package com.sports.limitsport.view.mine;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sports.limitsport.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/5/8.
 */

public class ItemView extends RelativeLayout {
    @BindView(R.id.tv_labelone)
    TextView tvLabelone;
    @BindView(R.id.tv_labeltwo)
    TextView tvLabeltwo;
    @BindView(R.id.imv_go)
    ImageView imvGo;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {
        setBackgroundResource(R.color.bg_title);
        View view = LayoutInflater.from(context).inflate(R.layout.view_shoepdetailitem, this);
        ButterKnife.bind(this, view);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShopDetailItem);

        String labelOne = typedArray.getString(R.styleable.ShopDetailItem_LabelOne);
        String labelTwo = typedArray.getString(R.styleable.ShopDetailItem_LabelTwo);

        tvLabelone.setText(labelOne);
        tvLabeltwo.setText(labelTwo);

    }

    public void setLableOneTip(String tip) {
        tvLabelone.setText(tip);

    }

    public void setLableTwo(String tip) {
        tvLabeltwo.setText(tip);
    }


    public String getLableTwo() {
        return tvLabeltwo.getText() != null ? tvLabeltwo.getText().toString() : null;
    }

    public void setClickListener(OnClickListener onClickListener) {
        tvLabeltwo.setOnClickListener(onClickListener);
        imvGo.setOnClickListener(onClickListener);
    }
}
