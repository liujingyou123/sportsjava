package com.sports.limitsport.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by liuworkmac on 17/7/17.
 */

public class PostionRecyclerView extends RecyclerView{
    private OnScrollChangedListener mOnScrollChangedListener;

    public PostionRecyclerView(Context context) {
        super(context);
    }

    public PostionRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PostionRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
