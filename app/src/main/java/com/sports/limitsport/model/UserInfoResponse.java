package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class UserInfoResponse extends BaseResponse {


    /**
     * activityNum : 0
     * attentionNum : 0
     * brithDate : 1954/08/01
     * city : 上海
     * clubNum : 0
     * collectionNum : 1
     * coutry : 中国
     * fansNum : 0
     * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501576298933.jpg
     * hobby : 游泳,爬山,打篮球,羽毛球,旅游,写代码
     * id : 14
     * introduction :
     * myActivityNum : 0
     * name : 玫琳普通哦哦哦凯
     * sex : 男
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int activityNum;
        private int attentionNum;
        private String brithDate;
        private String city;
        private int clubNum;
        private int collectionNum;
        private String coutry;
        private int fansNum;
        private String headPortrait;
        private String hobby;
        private int id;
        private String introduction;
        private int myActivityNum;
        private String name;
        private String sex;
        private String isAttenttion; //0:未关注 1已关注

        public int getActivityNum() {
            return activityNum;
        }

        public void setActivityNum(int activityNum) {
            this.activityNum = activityNum;
        }

        public int getAttentionNum() {
            return attentionNum;
        }

        public void setAttentionNum(int attentionNum) {
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

        public int getClubNum() {
            return clubNum;
        }

        public void setClubNum(int clubNum) {
            this.clubNum = clubNum;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public String getCoutry() {
            return coutry;
        }

        public void setCoutry(String coutry) {
            this.coutry = coutry;
        }

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getMyActivityNum() {
            return myActivityNum;
        }

        public void setMyActivityNum(int myActivityNum) {
            this.myActivityNum = myActivityNum;
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

        public String getIsAttenttion() {
            return isAttenttion;
        }

        public void setIsAttenttion(String isAttenttion) {
            this.isAttenttion = isAttenttion;
        }
    }
}
