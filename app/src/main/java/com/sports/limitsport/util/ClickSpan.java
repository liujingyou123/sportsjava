package com.sports.limitsport.util;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by liuworkmac on 17/7/21.
 * 富文本点击事件
 */

public class ClickSpan extends ClickableSpan {
    private View.OnClickListener onClickListener;
    private int color;

    public ClickSpan(View.OnClickListener onClickListener, int color) {
        this.onClickListener = onClickListener;
        this.color = color;
    }

    @Override
    public void onClick(View widget) {
        if (onClickListener != null) {
            onClickListener.onClick(widget);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(color);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
