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
 * Created by liuworkmac on 17/8/4.
 */

public class CitySelectDialog extends BottomSelectDialog implements WheelPicker.OnItemSelectedListener, View.OnClickListener {
    private final SelectResultListener listener;
    @BindView(R.id.wp_pro)
    WheelPicker wpPro;
    @BindView(R.id.wp_city)
    WheelPicker wpCity;
    private String city;
    private String province;
    private List<String> mDataPro = new ArrayList<>();
    private List<String> mDataCity = new ArrayList<>();

    public CitySelectDialog(@NonNull Context context, SelectResultListener listener) {
        super(context);
        setContentView(R.layout.dialog_city_select);
        ButterKnife.bind(this);
        this.listener = listener;
        initListener();
        initData();
    }

    private void initListener() {
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        wpPro.setOnItemSelectedListener(this);
        wpCity.setOnItemSelectedListener(this);
    }

    private void initData() {

        city = "黄浦区";
        province = "上海";
        mDataPro.add("上海");

        mDataCity.add("黄浦区");
        mDataCity.add("徐汇区");
        mDataCity.add("长宁区");
        mDataCity.add("静安区");
        mDataCity.add("普陀区");
        mDataCity.add("虹口区");
        mDataCity.add("杨浦区");
        mDataCity.add("闵行区");
        mDataCity.add("宝山区");
        mDataCity.add("嘉定区");
        mDataCity.add("宝山区");
        mDataCity.add("浦东区");
        mDataCity.add("金山区");
        mDataCity.add("松江区");
        mDataCity.add("青浦区");
        mDataCity.add("奉贤区");
        mDataCity.add("崇明区");

        wpPro.setData(mDataPro);

        wpCity.setData(mDataCity);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCancel) {
        } else {
            if (listener != null) {
                listener.onResult(province, city);
            }
        }
        dismiss();
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        if (picker == wpPro) {
            province = (String) data;
        } else if (picker == wpCity) {
            city = (String) data;
        }

    }

    public interface SelectResultListener {
        void onResult(String province, String city);
    }
}
