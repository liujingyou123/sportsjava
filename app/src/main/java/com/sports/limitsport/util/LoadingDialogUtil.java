package com.sports.limitsport.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.sports.limitsport.dialog.LoadingDialog;


/**
 * Created by liuworkmac on 17/1/19.
 */
public class LoadingDialogUtil {
    private static LoadingDialogUtil instance;
    private LoadingDialog loading;
    private Context mContext;
    private Dialog dialog;

    private static final int SHOW_DIALOG = -1;
    private static final int SHOW_DIALOG_WITH_MSG = -2;
    private static final int HIDE_DIALOG= -3;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_DIALOG) {
                show("");
            } else if (msg.what == SHOW_DIALOG_WITH_MSG) {
                Bundle bundle = msg.getData();
                if (bundle != null) {
                    show(bundle.getString("msg"));
                }

            } else if (msg.what == HIDE_DIALOG) {
                hide();
            }

        }
    };


    private LoadingDialogUtil() {
    }

    public static LoadingDialogUtil getInstance() {
        if (instance == null) {
            instance = new LoadingDialogUtil();
        }
        return instance;
    }

    public void init(Context context) {
        if (context != this.mContext) {
            this.mContext = context;
        }
    }

    public void showLoading() {
        if (handler != null) {
            handler.sendEmptyMessage(SHOW_DIALOG);
        }
    }

    public void showLoading(String msg) {
        if (handler != null) {
            Message message = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("msg", msg);
            message.setData(bundle);
            message.what = SHOW_DIALOG_WITH_MSG;
            handler.sendMessage(message);
        }
    }

    public void hideLoading() {
        if (handler != null) {
            handler.sendEmptyMessage(HIDE_DIALOG);
        }
    }

    private void show(String msg) {
        Log.e("loadingDialog", "show: ");
        if (loading != null) {
            if (loading.isShowing()) {
                loading.dismiss();
            }
            loading = null;
        }
        if (mContext == null) {
            new IllegalArgumentException("Context is null, please init context");
            return;
        }
        loading = new LoadingDialog(mContext);
//        loading.setMessage(msg);
        loading.show();
    }

    private void hide() {
        Log.e("loadingDialog", "hide: ");
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
        loading = null;
    }


}
