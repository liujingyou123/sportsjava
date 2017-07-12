package com.sports.limitsport.dialog;

import android.content.Context;
import android.support.annotation.NonNull;


/**
 * Created by css_double on 17/5/2.
 */

public class BottomDialog extends XDialog {

    public BottomDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void show() {
        setType(TYPE.TYPE_FROM_BOTTOM);
        super.show();
    }
}
