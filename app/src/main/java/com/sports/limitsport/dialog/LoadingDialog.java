package com.sports.limitsport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.sports.limitsport.R;
import com.sports.limitsport.util.UnitUtil;

/**
 * 等待loading的对话框
 *
 * @author xzw
 */
public class LoadingDialog extends Dialog {
    private TextView tip;
    private CircularProgressView loading;
    private Context mContext;
    private CharSequence msg;

    public LoadingDialog(Context context) {
        this(context, R.style.loadingDialog);
        mContext = context;
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading_frame);
        initView();
    }

    AnimationDrawable animationDrawable;
//    ObjectAnimator animator;

    private void initView() {
        tip = (TextView) findViewById(R.id.tip);
        loading = (CircularProgressView) findViewById(R.id.loading);
//        animationDrawable = (AnimationDrawable) loading.getDrawable();
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        if (mContext instanceof BaseActivity){
//            ((BaseActivity) mContext).setWindowAlpha(false);
//        }
    }

    @Override
    public void show() {
        super.show();
//        initSize();
        setTip();
//        animationDrawable.start();
    }

    private void initSize() {
//        WindowManager windowManager = ((Activity) mContext).getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = UnitUtil.getScreenWidthPixels(mContext); //设置宽度
        lp.height = UnitUtil.getScreenHeightPixels(mContext);//设置高度
        getWindow().setAttributes(lp);
    }

    private void setTip() {
        if (tip != null) {
            if (!TextUtils.isEmpty(msg)) {
                tip.setText(msg);
                tip.setVisibility(View.VISIBLE);
            } else {
                tip.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onDetachedFromWindow() {
//        if (animator != null) {
//            animator.cancel();
//            loading.clearAnimation();
//        }
//        if (animationDrawable != null) {
//            animationDrawable.stop();
//        }
        super.onDetachedFromWindow();
    }

    public void setMessage(CharSequence msg) {
        this.msg = msg;
    }

}
