package com.sports.limitsport.model;


import com.sports.limitsport.base.BaseResponse;

/**
 * Created by jge on 17/6/26.
 */

public class CheckVersionResponse extends BaseResponse {


    /**
     * errCode : 0
     * data : {"needUpdate":true,"updateLevel":0,"title":"我是标题，我是标题，有更新了，有更新了，让你赶快更新啊","desc":"我是描述 ，我是描述，有更新了，<br />有更新了，让你赶快更新啊","downloadUrl":"http://www.baidu.com","version":"v3.0.0","servicePhone":"021-3122 0899"}
     * exception : null
     */

    private DataBean data;
    private Object exception;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getException() {
        return exception;
    }

    public void setException(Object exception) {
        this.exception = exception;
    }

    public static class DataBean {
        /**
         * needUpdate : true
         * updateLevel : 0
         * title : 我是标题，我是标题，有更新了，有更新了，让你赶快更新啊
         * desc : 我是描述 ，我是描述，有更新了，<br />有更新了，让你赶快更新啊
         * downloadUrl : http://www.baidu.com
         * version : v3.0.0
         * servicePhone : 021-3122 0899
         */

        private boolean needUpdate;
        private int updateLevel;
        private String title;
        private String desc;
        private String downloadUrl;
        private String version;
        private String servicePhone;

        public boolean isNeedUpdate() {
            return needUpdate;
        }

        public void setNeedUpdate(boolean needUpdate) {
            this.needUpdate = needUpdate;
        }

        public int getUpdateLevel() {
            return updateLevel;
        }

        public void setUpdateLevel(int updateLevel) {
            this.updateLevel = updateLevel;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getServicePhone() {
            return servicePhone;
        }

        public void setServicePhone(String servicePhone) {
            this.servicePhone = servicePhone;
        }
    }
}
