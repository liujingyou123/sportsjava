package com.sports.limitsport.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by liuworkmac on 17/9/4.
 */

public class CustomTypeFaceTextView extends TextView {
    public CustomTypeFaceTextView(Context context) {
        super(context);
    }

    public CustomTypeFaceTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTypeFaceTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCustomText(String text) {
//        Typeface face = Typeface.createFromAsset(getResources().getAssets(), "ashby-black.ttf");
//        setTypeface(face);
        setText(text);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        Typeface face = Typeface.createFromAsset(getResources().getAssets(), "ashby-black.ttf");
        setTypeface(face);
        super.setText(text, type);
    }
}
