package com.sports.limitsport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.sports.limitsport.R;


/**
 * Created by css_double on 17/4/28.
 */

public class XDialog extends Dialog{
    public enum TYPE{
        TYPE_FROM_CENTER,
        TYPE_FROM_RIGHT,
        TYPE_FROM_BOTTOM,
    }

    public XDialog(@NonNull Context context) {
        super(context, R.style.slideDialog);
    }

    protected void setType(TYPE type){
        Window window = getWindow();

        DisplayMetrics outMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);

        WindowManager.LayoutParams lp = window.getAttributes();

        System.out.println(outMetrics);
        switch (type){
            case TYPE_FROM_CENTER:
                lp.windowAnimations = R.style.showFromCenterAnim;
                break;
            case TYPE_FROM_BOTTOM:
                window.setGravity(Gravity.BOTTOM);
                lp.width = outMetrics.widthPixels;
                lp.windowAnimations = R.style.slideFormBottomAnim;
                break;
            case TYPE_FROM_RIGHT:
                window.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                lp.height = outMetrics.heightPixels - getStatusBarHeight();
                lp.windowAnimations = R.style.slideFormRightAnim;
                break;
            default:
                break;
        }

        window.setAttributes(lp);
    }

    private int getStatusBarHeight(){
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = getContext().getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statusBarHeight;
    }
}
