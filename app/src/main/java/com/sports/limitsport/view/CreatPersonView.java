package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.sports.limitsport.R;


/**
 * Created by liuworkmac on 17/7/11.
 * 创始人View
 */

public class CreatPersonView extends LinearLayout{
    public CreatPersonView(Context context) {
        super(context);
        initView();
    }

    public CreatPersonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CreatPersonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_creatperson, this);
    }
}
