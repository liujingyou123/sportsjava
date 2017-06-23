package com.sports.limitsport.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by liuworkmac on 16/12/19.
 */
public class PositionScrollView extends ScrollView{

    private OnScrollChangedListener mOnScrollChangedListener;

    public PositionScrollView(Context context) {
        super(context);
    }

    public PositionScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PositionScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.mOnScrollChangedListener = onScrollChangedListener;
    }


    public interface OnScrollChangedListener{
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
