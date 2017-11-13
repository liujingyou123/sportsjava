package com.sports.limitsport.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.model.DongTaiListResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.SharedPrefsUtil;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jingyouliu on 17/11/13.
 * 举报和删除
 */

public class DelAndReportDialog extends Dialog {
    @BindView(R.id.tv_report)
    TextView tvReport;
    @BindView(R.id.tv_del)
    TextView tvDel;
    @BindView(R.id.tv_report_cancel)
    TextView tvReportCancel;
    @BindView(R.id.view_line)
    View viewLine;
    private String type;//类型0:活动 1:俱乐部 2:动态 3:评论 5:人员
    private String id;
    private String publishId;//发布者ID
    private OnDeleteListener mOnDeleteListener;

    public DelAndReportDialog(@NonNull Context context, String type, String id, String publishId) {
        super(context, R.style.reportDialog);
        this.type = type;
        this.id = id;
        this.publishId = publishId;
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_report_delete, null);
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

    @OnClick({R.id.tv_report, R.id.tv_del, R.id.tv_report_cancel})
    public void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.tv_report:
                showReport();
                break;
            case R.id.tv_del:
                showDel();
                break;
            case R.id.tv_report_cancel:
                break;
        }
    }

    private void showReport() {
        ReportDialog reportDialog = new ReportDialog(getContext(), type, id);
        reportDialog.show();
    }

    private void showDel() {
        NoticeDelDialog dialog = new NoticeDelDialog(getContext());
        if ("2".equals(type)) {
            dialog.setMessage("您确定要删除此条动态吗");
        } else if ("3".equals(type)) {
            dialog.setMessage("您确定要删除此条评论吗");
        }
        dialog.setOkClickListener(new NoticeDelDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                if ("2".equals(type)) {
                    deleteDongtai();
                } else if ("3".equals(type)) {
                    deleteComment();
                }
            }
        });
        dialog.show();
    }


    @Override
    public void show() {
        if (SharedPrefsUtil.getUserInfo() != null && publishId != null && publishId.equals(SharedPrefsUtil.getUserInfo().getData().getUserId() + "")) {
            tvReport.setVisibility(View.GONE);
            tvDel.setVisibility(View.VISIBLE);
            if ("2".equals(type)) {
                tvDel.setText("删除动态");
            } else if ("3".equals(type)) {
                tvDel.setText("删除评论");
            }
//            viewLine.setVisibility(View.VISIBLE);
        } else {
            tvDel.setVisibility(View.GONE);
            tvReport.setVisibility(View.VISIBLE);
//            viewLine.setVisibility(View.GONE);
        }
        super.show();
    }

    private void deleteDongtai() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).deleteDongtai(hashMap), new NoneNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    ToastUtil.showTrueToast(getContext(),"删除成功");
                    if (mOnDeleteListener != null) {
                        mOnDeleteListener.deleteDongtaiRusult(true);
                    }
                } else {
                    ToastUtil.showFalseToast(getContext(),"删除失败");
                    if (mOnDeleteListener != null) {
                        mOnDeleteListener.deleteDongtaiRusult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showFalseToast(getContext(),"删除失败");
                if (mOnDeleteListener != null) {
                    mOnDeleteListener.deleteDongtaiRusult(false);
                }
            }
        });
    }

    private void deleteComment() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).deleteComment(hashMap), new NoneNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (response != null && response.isSuccess()) {
                    ToastUtil.showTrueToast(getContext(),"删除成功");
                    if (mOnDeleteListener != null) {
                        mOnDeleteListener.deleteCommentRusult(true);
                    }
                } else {
                    ToastUtil.showFalseToast(getContext(),"删除失败");
                    if (mOnDeleteListener != null) {
                        mOnDeleteListener.deleteCommentRusult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showFalseToast(getContext(),"删除失败");
                if (mOnDeleteListener != null) {
                    mOnDeleteListener.deleteCommentRusult(false);
                }
            }
        });
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.mOnDeleteListener = onDeleteListener;
    }

    public interface OnDeleteListener{
        void deleteDongtaiRusult(boolean success);
        void deleteCommentRusult(boolean success);
    }
}
