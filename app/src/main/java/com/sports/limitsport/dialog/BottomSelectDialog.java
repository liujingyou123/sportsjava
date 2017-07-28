package com.sports.limitsport.dialog;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sports.limitsport.R;


/**
 * Created by css_double on 17/5/2.
 */

public class BottomSelectDialog extends BottomDialog {
    public TextView btnCancel;
    public TextView btnOk;
    private FrameLayout flContent;

    public BottomSelectDialog(@NonNull Context context) {
        super(context);
        super.setContentView(R.layout.dialog_bottom_select);

        btnCancel = (TextView) findViewById(R.id.btn_cancel);
        btnOk = (TextView) findViewById(R.id.btn_ok);
        flContent = (FrameLayout) findViewById(R.id.fl_content);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater.from(getContext()).inflate(layoutResID, flContent, true);
    }
}
