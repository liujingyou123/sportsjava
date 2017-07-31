package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class UserInfoResponse extends BaseResponse {

    /**
     * activityNum : null
     * attentionNum : null
     * brithDate : 1964-08-25
     * city : 上海
     * coutry : 中国
     * fansNum : null
     * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501234350721.jpg
     * hobby : 2,3
     * introduction : null
     * name : 吴
     * sex : 女
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String activityNum;
        private String attentionNum;
        private String brithDate;
        private String city;
        private String coutry;
        private String fansNum;
        private String headPortrait;
        private String hobby;
        private String introduction;
        private String name;
        private String sex;

        public String getActivityNum() {
            return activityNum;
        }

        public void setActivityNum(String activityNum) {
            this.activityNum = activityNum;
        }

        public String getAttentionNum() {
            return attentionNum;
        }

        public void setAttentionNum(String attentionNum) {
            this.attentionNum = attentionNum;
        }

        public String getBrithDate() {
            return brithDate;
        }

        public void setBrithDate(String brithDate) {
            this.brithDate = brithDate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCoutry() {
            return coutry;
        }

        public void setCoutry(String coutry) {
            this.coutry = coutry;
        }

        public String getFansNum() {
            return fansNum;
        }

        public void setFansNum(String fansNum) {
            this.fansNum = fansNum;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
