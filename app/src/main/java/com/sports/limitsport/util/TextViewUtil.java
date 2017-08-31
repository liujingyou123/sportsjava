package com.sports.limitsport.util;

import android.graphics.Paint;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本框工具类
 */
public class TextViewUtil {

    //给TextView设置部分大小
    public static void setPartialSize(TextView tv, int start, int end, int textSize) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new AbsoluteSizeSpan(textSize, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //给TextView设置部分颜色
    public static void setPartialColor(TextView tv, int start, int end, int textColor) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new ForegroundColorSpan(textColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //设置TextView部分颜色和点击事件
    public static void setPartAndColorAndClick(TextView tv, View.OnClickListener onClickListener, int start, int end, int textColor) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
//        spannable.setSpan(new ForegroundColorSpan(textColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ClickSpan(onClickListener, textColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setHighlightColor(tv.getResources().getColor(android.R.color.transparent));
    }

    //给TextView设置部分大小和颜色
    public static void setPartialSizeAndColor(TextView tv, int sizeStart, int sizeEnd, int textSize, int colorStart, int colorEnd, int textColor) {
        String s = tv.getText().toString();
        Spannable spannable = new SpannableString(s);
        spannable.setSpan(new AbsoluteSizeSpan(textSize, true), sizeStart, sizeEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(textColor), colorStart, colorEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannable);
    }

    //给TextView设置下划线
    public static void setUnderLine(TextView tv) {
        if (tv.getText() != null) {
            String udata = tv.getText().toString();
            SpannableString content = new SpannableString(udata);
            content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
            tv.setText(content);
            content.setSpan(new UnderlineSpan(), 0, udata.length(), 0);
        } else {
            tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        }
    }

    //取消TextView的置下划线
    public static void clearUnderLine(TextView tv) {
        tv.getPaint().setFlags(0);
    }


    //半角转换为全角
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    //去除特殊字符或将所有中文标号替换为英文标号
    public static String replaceCharacter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":").replaceAll("（", "(").replaceAll("（", ")");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * @param color   关键字颜色
     * @param text    文本
     * @param keyword 关键字
     * @return
     */
    public static SpannableString keyWordHighLighting(int color, String text,
                                                      String keyword) {
        SpannableString s = new SpannableString(text);
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(s);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;

    }

    /**
     * @param color   关键字颜色
     * @param text    文本
     * @param keyword 多个关键字
     * @return
     */
    public static SpannableString keyWordHighLighting(int color, String text,
                                                      String[] keyword) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyword.length; i++) {
            Pattern p = Pattern.compile(keyword[i]);
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color), start, end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

        }
        return s;

    }

    /**
     * @param color   关键字颜色
     * @param text    文本
     * @param keyword 多个关键字
     * @return
     */
    public static SpannableString keyWordHighLighting(int color, String text,
                                                      List<String> keyword) {
        SpannableString s = new SpannableString(text);
        for (int i = 0; i < keyword.size(); i++) {
            Pattern p = Pattern.compile(keyword.get(i));
            Matcher m = p.matcher(s);
            while (m.find()) {
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color), start, end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

        }
        return s;

    }

    /**
     * 限制EditText输入长度
     * @param limtSize
     * @return
     */
    public static InputFilter limitTextLength(final int limtSize) {
        return new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // 删除等特殊字符，直接返回
                if ("".equals(source.toString())) {
                    return null;
                }
                String dValue = dest.toString();
                String[] splitArray = dValue.split("\\.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    int diff = dotValue.length() + 1 - limtSize;
                    if (diff > 0) {
                        return source.subSequence(start, end - diff);
                    }
                }
                return null;
            }
        };
    }

    /**
     * 身份证号输入验证(及格式化)
     * @return
     */
    public static InputFilter idCardInput() {
        return  new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // 删除等特殊字符，直接返回
                if ("".equals(source.toString())) {
                    return null;
                }
                String dValue = dest.toString();
                if (dValue.length() == 21) {
                    return "";
                }
                if (dValue.length() == 20) {
                    if (!(isNumeric(source.toString()) || "x".equals(source.toString().toLowerCase()))) {
                        return "";
                    }
                }
                if (dValue.length() == 6 || dValue.length() == 11 || dValue.length() == 16) {
                    return " "+source;
                }

                if (dValue.length() != 20 && !isNumeric(source.toString())) {
                    return "";
                }
                return null;
            }
        };
    }

    /**
     * 手机号码输入格式化及验证(EditText InputType必须为phone)
     * @return
     */
    public static InputFilter phoneFormat() {
        return  new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // 删除等特殊字符，直接返回
                if ("".equals(source.toString())) {
                    return null;
                }


                String dValue = dest.toString();

                if (dValue.length() == 13 || !(isNumeric(source.toString().replaceAll(" ","")))) {
                    return "";
                }

                if (dValue.length() == 3 || dValue.length() == 8) {
                    return " "+source;
                }

                return null;
            }
        };
    }

    /**
     * 是不是数组
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || "null".equals(str);
    }
}
