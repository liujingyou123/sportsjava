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
        private String userPhone;
        private int isPerfect;//0:已完善 1:未完善
        private String userName;

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

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public int getIsPerfect() {
            return isPerfect;
        }

        public void setIsPerfect(int isPerfect) {
            this.isPerfect = isPerfect;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
