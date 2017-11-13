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
import com.sports.limitsport.base.BaseResponse;
import com.sports.limitsport.net.IpServices;
import com.sports.limitsport.net.NoneNetSubscriber;
import com.sports.limitsport.util.ToastUtil;
import com.sports.limitsport.util.ToolsUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jingyouliu on 17/11/13.
 */

public class DelCommentDialog extends Dialog {
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    private Context mContext;
    private String id;
    private OnDeleteListener mOnDeleteListener;

    public DelCommentDialog(@NonNull Context context, String id) {
        super(context, R.style.noticeDialog);
        this.id = id;
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_notice_del, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        setCanceledOnTouchOutside(true);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 280, mContext.getResources().getDisplayMetrics());
        lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 138, mContext.getResources().getDisplayMetrics());
        window.setAttributes(lp);
        tvMessage.setText("您确定要删除此条评论回复吗");
    }

    @OnClick({R.id.tv_ok, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ok:
                deleteComment();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }

    private void deleteComment() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        ToolsUtil.subscribe(ToolsUtil.createService(IpServices.class).deleteCommentReply(hashMap), new NoneNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                dismiss();
                if (response != null && response.isSuccess()) {
                    ToastUtil.showTrueToast(getContext(),"删除成功");
                    if (mOnDeleteListener != null) {
                        mOnDeleteListener.deleteRusult(true);
                    }
                } else {
                    ToastUtil.showFalseToast(getContext(),"删除失败");
                    if (mOnDeleteListener != null) {
                        mOnDeleteListener.deleteRusult(false);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                dismiss();
                ToastUtil.showFalseToast(getContext(),"删除失败");
                if (mOnDeleteListener != null) {
                    mOnDeleteListener.deleteRusult(false);
                }
            }
        });
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {
        this.mOnDeleteListener = onDeleteListener;
    }

    public interface OnDeleteListener{
        void deleteRusult(boolean success);
    }
}
