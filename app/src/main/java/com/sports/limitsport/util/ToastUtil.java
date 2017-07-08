/**
 *
 */
package com.sports.limitsport.util;

import android.content.Context;
import android.widget.Toast;

import com.sports.limitsport.base.LimitSportApplication;


public class ToastUtil {

    public static void show(Context context, String info) {
        if (context == null) {
            context = LimitSportApplication.getInstance();
        }
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int resId) {
        if (context == null) {
            context = LimitSportApplication.getInstance();
        }
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, String info, int duration) {
        if (context == null) {
            context = LimitSportApplication.getInstance();
        }
        Toast.makeText(context, info, duration > 0 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

}
