package com.sports.limitsport.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;


import com.aigestudio.wheelpicker.WheelPicker;
import com.sports.limitsport.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by css_double on 17/5/2.
 */

public class DateSelectDialog extends BottomSelectDialog implements WheelPicker.OnItemSelectedListener, View.OnClickListener {

    @BindView(R.id.wp_year)
    WheelPicker wpYear;
    @BindView(R.id.wp_month)
    WheelPicker wpMonth;
    @BindView(R.id.wp_day)
    WheelPicker wpDay;
    private int year;
    private int month;
    private int day;

    private SelectResultListener listener;

    public DateSelectDialog(@NonNull Context context, SelectResultListener listener) {
        super(context);
        setContentView(R.layout.dialog_date_select);
        ButterKnife.bind(this);
        this.listener = listener;
        initListener();
        initData();
    }

    private void initListener() {
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        wpYear.setOnItemSelectedListener(this);
        wpMonth.setOnItemSelectedListener(this);
        wpDay.setOnItemSelectedListener(this);

    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        List<String> data_year = new ArrayList<>();
        for (int i = 1950; i < year+1; i++) {
            data_year.add((i) + "年");
        }

        List<String> data_month = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            data_month.add((i > 9 ? i : "0" + i) + "月");
        }

        List<String> data_hour = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            data_hour.add((i > 9 ? i : "0" + i) + "点");
        }

        List<String> data_minute = new ArrayList<>();
//        for (int i = 0; i < 60; i++) {
//            data_minute.add((i > 9 ? i : "0" + i) + "分");
//        }

        data_minute.add("00分");
        data_minute.add("30分");


        wpYear.setData(data_year);
        wpMonth.setData(data_month);


        wpYear.setSelectedItemPosition(0);
        wpMonth.setSelectedItemPosition(month - 1);


        updateDayData();
    }

    private void updateDayData() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);//7月
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        int maxDate = cal.getActualMaximum(Calendar.DATE);

        List<String> data_day = new ArrayList<>();
        for (int i = 1; i <= maxDate; i++) {
            data_day.add((i > 9 ? i : "0" + i) + "日");
        }

        wpDay.setData(data_day);

        if (day > maxDate) {
            day = maxDate;
        }

        wpDay.setSelectedItemPosition(day - 1);
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        String dataStr = (String) data;
        String dataValue = dataStr.substring(0, dataStr.length() - 1);
        int num = Integer.valueOf(dataValue);
        if (picker == wpYear) {
            year = num;
            updateDayData();
        } else if (picker == wpMonth) {
            month = num;
            updateDayData();
        } else if (picker == wpDay) {
            day = num;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnCancel) {
        } else {
            String dateStr = year + "/" + (month > 9 ? month : "0" + month) + "/" + (day > 9 ? day : "0" + day);
            listener.onResult(dateStr);
        }

        dismiss();
    }

    public interface SelectResultListener {
        void onResult(String date);
    }
}
