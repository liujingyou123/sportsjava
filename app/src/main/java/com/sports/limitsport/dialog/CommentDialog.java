package com.sports.limitsport.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sports.limitsport.R;
import com.sports.limitsport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by css_double on 17/5/11.
 */

public class CommentDialog extends BottomDialog {
    @BindView(R.id.tv_send)
    TextView btnOk;
    @BindView(R.id.btn_comment)
    EditText et;

    private Handler mhandler;

    private int type = -1;

    public CommentDialog(@NonNull final Context context) {
        super(context);
        setContentView(R.layout.dialog_work_comment);
        ButterKnife.bind(this);

        mhandler = new Handler(Looper.myLooper());

//        btnOk.setAlpha(0.5f);

        setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((BaseActivity) context).showSoftInput(et);
                    }
                }, 10);
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    btnOk.setEnabled(false);
                } else {
                    btnOk.setEnabled(true);
                }
            }
        });
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setContent(String text) {
        et.setText("");
    }

    public String getContent() {
        return et.getText().toString();
    }

    public void setOkDoneListener(View.OnClickListener onClickListener) {
        btnOk.setOnClickListener(onClickListener);
    }

    public interface OnDoneClickListener {
        void onDoneClick();
    }
}
