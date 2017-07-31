package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class UserSettingInfoResponse extends BaseResponse{

    /**
     * receiveNews : 0
     * receiveNotice : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int receiveNews;
        private int receiveNotice;

        public int getReceiveNews() {
            return receiveNews;
        }

        public void setReceiveNews(int receiveNews) {
            this.receiveNews = receiveNews;
        }

        public int getReceiveNotice() {
            return receiveNotice;
        }

        public void setReceiveNotice(int receiveNotice) {
            this.receiveNotice = receiveNotice;
        }
    }
}
