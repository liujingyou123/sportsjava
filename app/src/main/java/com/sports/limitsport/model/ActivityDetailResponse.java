package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/25.
 * 活动详情
 */

public class ActivityDetailResponse extends BaseResponse {


    /**
     * activityDetail : 黄山位于安徽省南部黄山，尤其以奇松怪石云海温泉著称，是大自然造化中的奇迹，历来享有五岳归来不看.黄山归来不看的美誉。黄山，原称黟山，为道教圣地，遗址遗迹众多，因传说中华民族的始祖轩辕黄帝曾在此修炼升仙而改名为黄山。黄山的美是亿万年的地质演化的产物。它先后经历了汪洋一片，沧海桑田，最后在一次强烈的造山运动中横空。黄山处于亚热带季风气候区内，由于山高谷深，气候呈垂直变化。同时由于北坡和南坡受阳光的辐射差大，局部地形对其气候起主导作用，形成云雾多
     * activityVideo : http://v.youku.com/v_show/id_XODEzNDI1ODYw.html
     * activityVideoImg : www.sohu.com
     * address : 中国上海浦东新区杨高中路2010弄12号601
     * applicantList : [{"city":"上海","coutry":"中国","headPortrait":"http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg","id":8,"idCard":null,"introduction":null,"name":"廖智明121","phone":null,"sex":"0","status":"0","tickets":null}]
     * applicantNumber : 1
     * authStatus : 1
     * collectionLocation : 上海地铁9号线九亭地铁站4号口
     * costIncluded : 保险费300元
     * coverUrl : www.e-cology.com.cn
     * endDate : 2017年07月24日
     * endTime : 00:00
     * insuranceDetail : 保险费300元
     * insuranceInformation : 1
     * lastGroups : 12
     * money :  ￥ 999.0 -  ￥ 1300.0
     * moneyType : 1
     * name : 黄山3天2日旅行
     * organizerAddress : null
     * organizerClub : 冲浪俱乐部
     * organizerClubId : 2
     * organizerHeadPor : ab.img
     * organizerdesc : 冲出亚马逊
     * ownItems : 1
     * ownItemsDetail : 泳帽，泳镜
     * refundDate : null
     * refundRule : 1
     * signEndDate : 2017年07月22日
     * signEndTime : 00:00
     * signStatus : 0
     * startDate : 2017年07月19日
     * startTime : 00:00
     * status : 1
     * ticketsList : [{"activityId":2,"createTime":1500543846000,"creater":null,"descContent":"2122","id":2,"isFree":0,"modifier":null,"modifyTime":null,"money":1300,"name":"尊享票","sequence":null,"soldNumber":100,"status":0,"userPurchase":5,"version":0},{"activityId":2,"createTime":1500543793000,"creater":null,"descContent":"2122","id":1,"isFree":0,"modifier":null,"modifyTime":null,"money":999,"name":"标准票","sequence":null,"soldNumber":100,"status":0,"userPurchase":5,"version":0}]
     * xpostion : 145.65
     * ypostion : 111.32
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String activityDetail;
        private String activityVideo;
        private String activityVideoImg;
        private String address;
        private String applicantNumber;
        private String authStatus;
        private String collectionLocation;
        private String costIncluded;
        private String coverUrl;
        private String endDate;
        private String endTime;
        private String insuranceDetail;
        private String insuranceInformation;
        private int lastGroups;
        private String money;
        private String moneyType;
        private String name;
        private String organizerAddress;
        private String organizerClub;
        private int organizerClubId;
        private String organizerHeadPor;
        private String organizerdesc;
        private int ownItems;
        private String ownItemsDetail;
        private String refundDate;
        private String refundRule;
        private String signEndDate;
        private String signEndTime;
        private int signStatus;
        private String startDate;
        private String startTime;
        private String status;
        private String xpostion;
        private String ypostion;

        private String week;
        private String minMoney;
        private String isCollection;
        private int authEntity;
        /**
         * city : 上海
         * coutry : 中国
         * headPortrait : http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg
         * id : 8
         * idCard : null
         * introduction : null
         * name : 廖智明121
         * phone : null
         * sex : 0
         * status : 0
         * tickets : null
         */

        private List<ApplicantListBean> applicantList;
        /**
         * activityId : 2
         * createTime : 1500543846000
         * creater : null
         * descContent : 2122
         * id : 2
         * isFree : 0
         * modifier : null
         * modifyTime : null
         * money : 1300
         * name : 尊享票
         * sequence : null
         * soldNumber : 100
         * status : 0
         * userPurchase : 5
         * version : 0
         */

        private List<TicketList> ticketsList;

        public String getActivityDetail() {
            return activityDetail;
        }

        public void setActivityDetail(String activityDetail) {
            this.activityDetail = activityDetail;
        }

        public String getActivityVideo() {
            return activityVideo;
        }

        public void setActivityVideo(String activityVideo) {
            this.activityVideo = activityVideo;
        }

        public String getActivityVideoImg() {
            return activityVideoImg;
        }

        public void setActivityVideoImg(String activityVideoImg) {
            this.activityVideoImg = activityVideoImg;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getApplicantNumber() {
            return applicantNumber;
        }

        public void setApplicantNumber(String applicantNumber) {
            this.applicantNumber = applicantNumber;
        }

        public String getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(String authStatus) {
            this.authStatus = authStatus;
        }

        public String getCollectionLocation() {
            return collectionLocation;
        }

        public void setCollectionLocation(String collectionLocation) {
            this.collectionLocation = collectionLocation;
        }

        public String getCostIncluded() {
            return costIncluded;
        }

        public void setCostIncluded(String costIncluded) {
            this.costIncluded = costIncluded;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getInsuranceDetail() {
            return insuranceDetail;
        }

        public void setInsuranceDetail(String insuranceDetail) {
            this.insuranceDetail = insuranceDetail;
        }

        public String getInsuranceInformation() {
            return insuranceInformation;
        }

        public void setInsuranceInformation(String insuranceInformation) {
            this.insuranceInformation = insuranceInformation;
        }

        public int getLastGroups() {
            return lastGroups;
        }

        public void setLastGroups(int lastGroups) {
            this.lastGroups = lastGroups;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMoneyType() {
            return moneyType;
        }

        public void setMoneyType(String moneyType) {
            this.moneyType = moneyType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrganizerAddress() {
            return organizerAddress;
        }

        public void setOrganizerAddress(String organizerAddress) {
            this.organizerAddress = organizerAddress;
        }

        public String getOrganizerClub() {
            return organizerClub;
        }

        public void setOrganizerClub(String organizerClub) {
            this.organizerClub = organizerClub;
        }

        public int getOrganizerClubId() {
            return organizerClubId;
        }

        public void setOrganizerClubId(int organizerClubId) {
            this.organizerClubId = organizerClubId;
        }

        public String getOrganizerHeadPor() {
            return organizerHeadPor;
        }

        public void setOrganizerHeadPor(String organizerHeadPor) {
            this.organizerHeadPor = organizerHeadPor;
        }

        public String getOrganizerdesc() {
            return organizerdesc;
        }

        public void setOrganizerdesc(String organizerdesc) {
            this.organizerdesc = organizerdesc;
        }

        public int getOwnItems() {
            return ownItems;
        }

        public void setOwnItems(int ownItems) {
            this.ownItems = ownItems;
        }

        public String getOwnItemsDetail() {
            return ownItemsDetail;
        }

        public void setOwnItemsDetail(String ownItemsDetail) {
            this.ownItemsDetail = ownItemsDetail;
        }

        public String getRefundDate() {
            return refundDate;
        }

        public void setRefundDate(String refundDate) {
            this.refundDate = refundDate;
        }

        public String getRefundRule() {
            return refundRule;
        }

        public void setRefundRule(String refundRule) {
            this.refundRule = refundRule;
        }

        public String getSignEndDate() {
            return signEndDate;
        }

        public void setSignEndDate(String signEndDate) {
            this.signEndDate = signEndDate;
        }

        public String getSignEndTime() {
            return signEndTime;
        }

        public void setSignEndTime(String signEndTime) {
            this.signEndTime = signEndTime;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getXpostion() {
            return xpostion;
        }

        public void setXpostion(String xpostion) {
            this.xpostion = xpostion;
        }

        public String getYpostion() {
            return ypostion;
        }

        public void setYpostion(String ypostion) {
            this.ypostion = ypostion;
        }

        public List<ApplicantListBean> getApplicantList() {
            return applicantList;
        }

        public void setApplicantList(List<ApplicantListBean> applicantList) {
            this.applicantList = applicantList;
        }

        public List<TicketList> getTicketsList() {
            return ticketsList;
        }

        public void setTicketsList(List<TicketList> ticketsList) {
            this.ticketsList = ticketsList;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getMinMoney() {
            return minMoney;
        }

        public void setMinMoney(String minMoney) {
            this.minMoney = minMoney;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(String isCollection) {
            this.isCollection = isCollection;
        }

        public int getAuthEntity() {
            return authEntity;
        }

        public void setAuthEntity(int authEntity) {
            this.authEntity = authEntity;
        }
    }
}
