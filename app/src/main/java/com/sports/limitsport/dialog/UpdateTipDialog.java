package com.sports.limitsport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sports.limitsport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/24.
 */

public class UpdateTipDialog extends Dialog {
    @BindView(R.id.imv_title)
    ImageView imvTitle;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.content)
    TextView desc;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    @BindView(R.id.close)
    ImageView close;
    private Context mContext;
    private OnPreClickListner mOnOkPreClickListner;

    public UpdateTipDialog(@NonNull Context context) {
        super(context, R.style.noticeDialog);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_tip, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(false);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, mContext.getResources().getDisplayMetrics());
        lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 336, mContext.getResources().getDisplayMetrics());
        window.setAttributes(lp);
    }

    @OnClick({R.id.tv_ok,R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                if (mOnOkPreClickListner != null) {
                    mOnOkPreClickListner.onClick();
                }
                dismiss();
                break;
            case R.id.close:
                if (mOnOkPreClickListner != null) {
                    mOnOkPreClickListner.onClose();
                }
                dismiss();
                break;
        }
    }

    public void setMessage(String msg) {
        if(!TextUtils.isEmpty(msg)){

            desc.setText(Html.fromHtml(msg));
        }
    }

    public void setTitle(String msg) {
        title.setText(msg);
    }

    public void setClose(Boolean flag) {
        if(flag){
            close.setVisibility(View.VISIBLE);
        }
        else{
            close.setVisibility(View.GONE);
        }
    }

    public void setOkClickListener(OnPreClickListner onPreClickListner) {
        this.mOnOkPreClickListner = onPreClickListner;
    }
    public interface OnPreClickListner {
        void onClick();
        void onClose();
    }
}
