package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.io.Serializable;

/**
 * Created by liuworkmac on 17/8/2.
 */

public class NewNoticeResponse extends BaseResponse {

    /**
     * activity : 0
     * aite : 0
     * comment : 0
     * fans : 0
     * praise : 0
     * system : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private int activity;
        private int aite;
        private int comment;
        private int fans;
        private int praise;
        private int system;
        private int totalUnRead;

        public int getActivity() {
            return activity;
        }

        public void setActivity(int activity) {
            this.activity = activity;
        }

        public int getAite() {
            return aite;
        }

        public void setAite(int aite) {
            this.aite = aite;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getSystem() {
            return system;
        }

        public void setSystem(int system) {
            this.system = system;
        }

        public int getTotalUnRead() {
            return totalUnRead;
        }

        public void setTotalUnRead(int totalUnRead) {
            this.totalUnRead = totalUnRead;
        }
    }
}
