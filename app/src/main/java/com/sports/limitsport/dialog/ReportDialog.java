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
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.LoadingNetSubscriber;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/7/11.
 */

public class ReportDialog extends Dialog {
    private String type;//类型(0:活动 1:俱乐部 2:动态 3:评论)
    private String id;

    public ReportDialog(@NonNull Context context, String type, String id) {
        super(context, R.style.reportDialog);
        init(context);
        this.type = type;
        this.id = id;
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
                tipOff("0");
                break;
            case R.id.tv_report_two:
                tipOff("1");
                break;
            case R.id.tv_report_thr:
                tipOff("2");
                break;
            case R.id.tv_report_four:
                tipOff("3");
                break;
            case R.id.tv_report_cancel:
                dismiss();
                break;
        }
    }


    /**
     * 举报
     * @param actionType (0:垃圾广告 1:违法信息 2:淫秽色情 3:其他)
     **/
    public void tipOff(String actionType) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("type", type);
        hashMap.put("actionType", actionType);

        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).tipOff(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    ToastUtil.showTrueToast(getContext(), "举报成功");
                } else {
                    ToastUtil.showTrueToast(getContext(), "举报失败");
                }

                dismiss();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showTrueToast(getContext(), "举报失败");
                dismiss();
            }
        });
    }
}
