package com.sports.limitsport.log;


import android.text.TextUtils;

import com.sports.limitsport.BuildConfig;


public class XLogConfig {

    private boolean showThreadInfo = false;
    private boolean debug = BuildConfig.DEBUG;
    private String tag = "winport";


    public XLogConfig setTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            this.tag = tag;
        }
        return this;
    }

    public XLogConfig setShowThreadInfo(boolean showThreadInfo) {
        this.showThreadInfo = showThreadInfo;
        return this;
    }


    public XLogConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }
}
