/**
 *
 */
package com.sports.limitsport.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sports.limitsport.R;
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


    public static void showToast(Context context, String message, int id) {
        Context context1 = context.getApplicationContext();
        Toast toast = new Toast(context1);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context1).inflate(R.layout.view_toast, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imv_icon);
        imageView.setImageResource(id);
        TextView tv = (TextView) view.findViewById(R.id.tv_notice);
        tv.setText(message);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showTrueToast(Context context, String message) {
        showToast(context, message, R.mipmap.icon_toast_true);
    }

    public static void showFalseToast(Context context, String message) {
        showToast(context, message, R.mipmap.icon_toast_false);

    }

}
