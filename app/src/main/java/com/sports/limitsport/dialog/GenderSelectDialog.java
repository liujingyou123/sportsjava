package com.sports.limitsport.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.aigestudio.wheelpicker.WheelPicker;
import com.sports.limitsport.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class GenderSelectDialog extends BottomSelectDialog implements WheelPicker.OnItemSelectedListener, View.OnClickListener {
    @BindView(R.id.wp_gender)
    WheelPicker wpGender;

    private String gender;
    private int position;

    private SelectResultListener listener;

    public GenderSelectDialog(@NonNull Context context, SelectResultListener listener) {
        super(context);
        setContentView(R.layout.dialog_gender_select);
        ButterKnife.bind(this);
        this.listener = listener;
        initListener();
        initData();
    }

    @Override
    public void onClick(View v) {
        if (v == btnCancel) {
        } else {
            if (listener != null) {
                listener.onResult(gender, position);
            }
        }
        dismiss();
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        gender = (String) data;
        this.position = position;
    }

    private void initListener() {
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        wpGender.setOnItemSelectedListener(this);
    }

    private void initData() {

        List<String> data_gender = new ArrayList<>();
        data_gender.add("男");
        data_gender.add("女");

        wpGender.setData(data_gender);
        wpGender.setSelectedItemPosition(0);

        gender = "男";
        position = 0;
    }

    public interface SelectResultListener {
        void onResult(String data, int position);
    }
}
