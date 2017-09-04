package com.sports.limitsport.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.sports.limitsport.discovery.PersonInfoActivity;
import com.sports.limitsport.log.XLog;
import com.sports.limitsport.util.ClickSpan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuworkmac on 17/9/4.
 */

public class AtTextView extends TextView {
    public AtTextView(Context context) {
        super(context);
    }

    public AtTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AtTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStrings(String str) {
        Pattern pattern = Pattern.compile("@[\\S]+?\\s\\[AT\\]\\d+\\[UID\\]");
        Matcher matcher = pattern.matcher(str);
        StringBuilder sbContent = new StringBuilder();

        System.out.println(matcher.matches());

        int end = 0;
        List<HashMap<String, Object>> list = new ArrayList<>();

        while (matcher.find()) {
            String strMatcher = matcher.group();
            XLog.e("strMatcher = " + strMatcher);
            if (sbContent.length() == 0) {
                sbContent.append(str.substring(0, matcher.start()));

            } else {
                sbContent.append(str.substring(end, matcher.start()));
            }
            end = matcher.end();
            HashMap<String, Object> map = new HashMap<>();

            map.put("index", sbContent.length());

            String[] strs = strMatcher.split(" ");
            sbContent.append(strs[0] + " ");
            String strid = strs[1].replace("[AT]", "").replace("[UID]", "");

//            map.put("index", sbContent.length());
            map.put("length", strs[0].length() + 1);
            map.put("id", strid);
            list.add(map);

            XLog.e("sbContent = " + sbContent.toString());
        }

        SpannableString spannable = new SpannableString(sbContent);


        for (int i = 0; i < list.size(); i++) {
            final HashMap<String, Object> mapTmp = list.get(i);
            int index = (int) mapTmp.get("index");
            int length = (int) mapTmp.get("length");
            final String userId = (String) mapTmp.get("id");
            spannable.setSpan(new ClickSpan(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), PersonInfoActivity.class);
                    intent.putExtra("userId", userId);
                    getContext().startActivity(intent);
                }
            }, Color.parseColor("#4899ff")), index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        setText(spannable);
        setMovementMethod(LinkMovementMethod.getInstance());
        setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

}
