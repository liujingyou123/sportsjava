package com.sports.limitsport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sports.limitsport.R;
import com.sports.limitsport.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 */

public class ReportDialog extends Dialog {
    public ReportDialog(@NonNull Context context) {
        super(context, R.style.reportDialog);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_report, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    @OnClick({R.id.tv_report_one, R.id.tv_report_two, R.id.tv_report_thr, R.id.tv_report_four, R.id.tv_report_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_report_one:
                doReport(1);
                break;
            case R.id.tv_report_two:
                doReport(1);
                break;
            case R.id.tv_report_thr:
                doReport(1);
                break;
            case R.id.tv_report_four:
                doReport(1);
                break;
            case R.id.tv_report_cancel:
                doReport(1);
                break;
        }
    }

    private void doReport(int type) {
        ToastUtil.showTrueToast(getContext(), "举报成功");
        this.dismiss();
    }
}
