package com.sports.limitsport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sports.limitsport.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/8/21.
 * 退款提示
 */

public class RefundTipDialog extends Dialog {
    @BindView(R.id.tv_done)
    TextView tvDone;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_club_name)
    TextView tvClubName;

    public RefundTipDialog(@NonNull Context context) {
        super(context, R.style.refundDialog);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_refund_tip, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 292, context.getResources().getDisplayMetrics());
        lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 295, context.getResources().getDisplayMetrics());
        window.setAttributes(lp);
    }

    public void setDoneClickListener(View.OnClickListener onClickListener) {
        tvDone.setOnClickListener(onClickListener);
    }

    public void setPhoneText(String phone) {
        tvPhone.setText("联系电话：" + phone);
    }

    public void setClubName(String clubName) {
        tvClubName.setText("俱乐部："+clubName);
    }

    @OnClick(R.id.imv_close)
    public void onViewClicked() {
        dismiss();
    }
}
