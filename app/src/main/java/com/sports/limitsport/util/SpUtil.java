package com.sports.limitsport.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by liuworkmac on 16/11/10.
 */
public class SpUtil {
    private Context mContext;
    private static SpUtil INSTANCE = new SpUtil();

    private SpUtil() {
    }

    public static SpUtil getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public void setStringData(String key, String value) {
        SharedPreferences mySharedPreferences = mContext.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void setBooleanData(String key, boolean value) {
        SharedPreferences mySharedPreferences = mContext.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBooleanData(String key, boolean defalutValue) {
        //在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        boolean value = sharedPreferences.getBoolean(key, defalutValue);
        return value;
    }

    public void setIntData(String key, int value) {
        SharedPreferences mySharedPreferences = mContext.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public String getStringData(String key, String defalutValue) {
        //在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, defalutValue);
        return value;
    }

    public int getIntData(String key, int defalutValue) {
        //在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        int value = sharedPreferences.getInt(key, defalutValue);
        return value;
    }

    public String getStringDataByJson(String key, String defalutValue, String jsonKey) {
        //在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, defalutValue);
        String ret = null;

        if (!TextUtils.isEmpty(value) && !value.equals(defalutValue)) {
            try {
                JSONObject js = new JSONObject(value);
                ret = js.getString(jsonKey);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public JSONObject getJSONObject(String key, String defalutValue) {
        //在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(key, defalutValue);
        JSONObject ret = null;
        if (!TextUtils.isEmpty(value) && !value.equals(defalutValue)) {
            try {
                ret = new JSONObject(value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
