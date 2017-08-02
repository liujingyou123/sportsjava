package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/7/28.
 */

public class UserInfo extends BaseResponse {

    /**
     * accessToken : A7CB3A658D92631F1FBD9A3BEA896D3A
     * userId : 8
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String accessToken;
        private int userId;
        private String phone;
        private int isPerfect;//0:已完善 1:未完善
        private String name;


        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getIsPerfect() {
            return isPerfect;
        }

        public void setIsPerfect(int isPerfect) {
            this.isPerfect = isPerfect;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
