package com.sports.limitsport.util;


import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sports.limitsport.log.XLog;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class UnitUtil {


    /**
     * 获取屏幕尺寸
     *
     * @param context 上下文
     * @return 屏幕尺寸像素值，下标为0的值为宽，下标为1的值为高
     */
    public static Point getScreenSize(Context context) {

        // 获取屏幕宽高
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point screenSize = new Point();
        wm.getDefaultDisplay().getSize(screenSize);
        return screenSize;
    }

    /**
     * 将dip转换为像素
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将像素转换为dip
     *
     * @param pxvalue
     * @return
     */
    public static float px2dip(Context context, int pxvalue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return pxvalue / scale;
    }

    /**
     * 将像素转换为sp
     *
     * @param
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 获取屏宽度像素数
     *
     * @return
     */
    public static int getScreenWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度像素数
     *
     * @return
     */
    public static int getScreenHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;

        Object obj = null;

        Field field = null;

        int x = 0, sbar = 0;

        try {

            c = Class.forName("com.android.internal.R$dimen");

            obj = c.newInstance();

            field = c.getField("status_bar_height");

            x = Integer.parseInt(field.get(obj).toString());

            sbar = context.getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {

            e1.printStackTrace();

        }

        XLog.e("unitUtil = " + sbar);

        return sbar;
    }

    /**
     * 米转公里
     *
     * @param meters
     * @return
     */
    public static double mToKm(float meters) {
        return Math.round(meters / 100d) / 10d;
    }

    /**
     * 去掉字符串中所有空格
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str != null ? str.replaceAll(" ", "") : "";
    }

    public static String mTokm(String meters) {
        String ret;
        if (TextUtils.isEmpty(meters)) {
            return null;
        }
        BigDecimal bg = new BigDecimal(meters);

        if (bg.doubleValue() > 999) {
            ret = limitKNum(bg.doubleValue()) + "km";
        } else {
            ret = meters + "m";
        }
        return ret;
    }


    public static String limitNum(double num, double limit) {
        String ret;
        if (num > limit) {
            ret = formatNum(num) + "万";
        } else {
            ret = formatDNum(num) + "元";
        }
        return ret;
    }

    public static String limitSNum(String numStr, double limit) {
        if (TextViewUtil.isEmpty(numStr)) {
            return null;
        }
        String ret = null;
        try {
            double num = Double.parseDouble(numStr);
            if (num > limit) {
                ret = formatNum(num) + "万";
            } else {
                ret = formatDNum(num) + "元";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * 数字格式化（千） eg.   1234 = 1.23   1200 = 1.2
     *
     * @param num
     * @return
     */
    public static String limitKNum(double num) {
        String ret;
        int qian = (int) (num / 1000);
        int bai = (int) (num % 1000) / 100;
        int shi = (int) (num % 100) / 10;

        if (shi != 0) {
            ret = qian + "." + bai + "" + shi;
        } else {
            if (bai != 0) {
                ret = qian + "." + bai + "";
            } else {
                ret = qian + "";
            }
        }

        return ret;
    }

    /**
     * 数字格式化（万） eg.   12345 = 1.23   12000 = 1.2
     *
     * @param num
     * @return
     */
    public static String formatNum(double num) {
        String ret;
        int wang = (int) (num / 10000);
        int qian = (int) (num % 10000) / 1000;
        int bai = (int) (num % 100) / 10;

        if (bai != 0) {
            ret = wang + "." + qian + "" + bai;
        } else {
            if (qian != 0) {
                ret = wang + "." + qian + "";
            } else {
                ret = wang + "";
            }
        }

        return ret;
    }

    /**
     * 处理小数点  1.0 ——> 1   1.10 ——> 1.1
     *
     * @param num
     * @return
     */
    public static String formatMNum(float num) {
        String ret;
        DecimalFormat df = new DecimalFormat("#.##");
        ret = df.format(num);
        return ret;

    }

    /**
     * 处理小数点  1.0 ——> 1   1.10 ——> 1.1
     *
     * @param num
     * @return
     */
    public static String formatDNum(double num) {
        String ret;
        DecimalFormat df = new DecimalFormat("#.##");
        ret = df.format(num);
        return ret;

    }

    /**
     * 格式化数字 1->01 ..  11->11
     *
     * @param num
     * @return
     */
    public static String formatDec(double num) {
        String ret;
        DecimalFormat df = new DecimalFormat("00.##");
        ret = df.format(num);
        return ret;
    }

    /**
     * 数字格式化 两位
     *
     * @param num
     * @return
     */
    public static String formatDecDouble(int num) {
        String ret;
        DecimalFormat df = new DecimalFormat("00");
        ret = df.format(num);
        return ret;
    }


    /**
     * 数字格式化 两位
     *
     * @param num
     * @return
     */
    public static String formatSNum(String num) {
        String ret = "";
        if (TextUtils.isEmpty(num)) {
            return "";
        }
        try {
            double d = Double.parseDouble(num);
            ret = formatDNum(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static String formatArea(float in) {
        String number = null;
        int inInt = (int) in;
        if (inInt == in) {
            number = inInt + "";
        } else {
            number = in + "";
        }

        return number;
    }

    public static String formatFloat(float in) {
        String number = null;
        int inInt = (int) in;

        number = inInt + "";

        return number;
    }

    public static String formatString(String num) {
        String number = null;
        try {
            if (!TextUtils.isEmpty(num)) {
                float in = Float.parseFloat(num);
                number = formatFloat(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return number;
    }

    public static String formatStringArea(String num) {
        String number = null;
        try {
            if (!TextUtils.isEmpty(num)) {
                float in = Float.parseFloat(num);
                number = formatArea(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return number;
    }

    public static String formatDouble(double in) {
        String number = null;
        int inInt = (int) in;
        number = inInt + "";
        return number;
    }

    public static String formatDoubleArea(double in) {
        String number = null;
        int inInt = (int) in;
        if (inInt == in) {
            number = inInt + "";
        } else {
            number = in + "";
        }

        return number;
    }

    public static String toJsonString(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        Log.e("params json = ", json);
        return json;
    }

    public static String toJsonStringWithIgnore(Object object) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new FooAnnotationExclusionStrategy()).create();
        String json = gson.toJson(object);
        Log.e("params json = ", json);
        return json;
    }


    public static float stringToFloat(String num) {
        float ret = 0f;
        try {
            if (!TextUtils.isEmpty(num)) {
                ret = Float.parseFloat(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }


    public static Date stringToDate(String timeStr) {
        if (TextUtils.isEmpty(timeStr)) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date time = null;
        try {
            time = df.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static Calendar dateToCalendar(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * 1:周一
     *
     * @return
     */
    public static String stringToWeek(String str) {
        String week = null;

        if ("1".equals(str)) {
            week = "周一";
        } else if ("2".equals(str)) {
            week = "周二";
        } else if ("3".equals(str)) {
            week = "周三";
        } else if ("4".equals(str)) {
            week = "周四";
        } else if ("5".equals(str)) {
            week = "周五";
        } else if ("6".equals(str)) {
            week = "周六";
        } else if ("7".equals(str)) {
            week = "周日";
        }

        return week;
    }


    public static double stringToD(String str) {
        double ret = 0;
        if (TextViewUtil.isEmpty(str)) {
            ret = 0;
            return ret;
        }

        try {
            ret = Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * 秒转时间
     *
     * @param time
     * @return
     */
    public static String secondsToTimeString(long time) {
        String timeStr;
        long hour = 0;
        long minute = 0;
        long second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(long i) {
        String retStr;
        if (i >= 0 && i < 10)
            retStr = "0" + Long.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
}
