package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.imv_sub)
    ImageView imvSub;
    @BindView(R.id.imv_add)
    ImageView imvAdd;
    private OnNumChangedListener mOnNumChangedListener;
    private int minNum = 0;
    private int maxNum = Integer.MAX_VALUE;
    private int num = minNum;


    public NumCheckView(Context context) {
        super(context);
        initView(null);
    }

    public NumCheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public NumCheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.view_numcheck, this);
        ButterKnife.bind(this, this);
        imvSub.setEnabled(false);
        imvAdd.setEnabled(true);
    }

    @OnClick({R.id.imv_sub, R.id.imv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_sub:
                subNum();
                break;
            case R.id.imv_add:
                addNum();
                break;
        }
    }

    private void addNum() {

        if (num < maxNum) {
            num++;
            tvNum.setText(num + "");
        }

        if (num >= maxNum) {
            imvAdd.setEnabled(false);
        } else {
            imvAdd.setEnabled(true);
        }

        if (num > minNum) {
            imvSub.setEnabled(true);
        } else {
            imvSub.setEnabled(false);
        }

        if (mOnNumChangedListener != null) {
            mOnNumChangedListener.onNumChanged(num);
            mOnNumChangedListener.isAdd(true);
        }
    }

    private void subNum() {
        if (num > minNum) {
            num--;
            tvNum.setText(num + "");
        }

        if (num >= maxNum) {
            imvAdd.setEnabled(false);
        } else {
            imvAdd.setEnabled(true);
        }


        if (num > minNum) {
            imvSub.setEnabled(true);
        } else {
            imvSub.setEnabled(false);
        }

        if (mOnNumChangedListener != null) {
            mOnNumChangedListener.onNumChanged(num);
            mOnNumChangedListener.isAdd(false);
        }
    }

    public void setImvAddResource(int ids) {
        imvAdd.setImageResource(ids);
    }

    public void setImvSubResource(int ids) {
        imvSub.setImageResource(ids);
    }

    public void setDefaultNum(int defaultNum) {
        this.num = defaultNum;
        tvNum.setText(""+num);
    }

    public void setNumTextSize(int textSize) {
        if (textSize != 0) {
            tvNum.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }
    }

    public void setNumWitdh(int numWitdh) {
        if (numWitdh != 0) {
            LayoutParams lp = new LayoutParams(numWitdh, LayoutParams.WRAP_CONTENT);
            tvNum.setLayoutParams(lp);
        }
    }

    public int getNum() {
        return num;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public void setCanEnable(boolean enable) {
        if (enable) {
            if (num > minNum) {
                imvSub.setEnabled(true);
            } else {
                imvSub.setEnabled(false);
            }
            if (num < maxNum) {
                imvAdd.setEnabled(true);
            } else {
                imvAdd.setEnabled(false);
            }
        } else {
            imvSub.setEnabled(false);
            imvAdd.setEnabled(false);
        }
    }

    public void setOnNumChangedListener(OnNumChangedListener onNumChangedListener) {
        this.mOnNumChangedListener = onNumChangedListener;
    }

    public interface OnNumChangedListener {
        void onNumChanged(int num);

        void isAdd(boolean isAdd);
    }
}
