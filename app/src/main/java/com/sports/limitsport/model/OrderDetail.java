package com.sports.limitsport.model;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/16.
 */

public class OrderDetail {
    private String address;
    private String coverUrl;
    private String createOrderTime;
    private String dateTime;
    private int id;
    private String laveSecond;
    private String moneyNews;
    private String name;
    private int number;
    private String orderInfo;
    private String orderNo;
    private String orderStatus;
    private String payTime;
    private String payType;
    private String receptMoney;
    private int signId;
    private String startDate;
    private String startTime;
    private String ticketsName;
    private String totalMoney;
    private String week;
    private int isJoin;
    /**
     * city : null
     * coutry : null
     * headPortrait : null
     * id : 3
     * idCard : null
     * introduction : null
     * name : 晓晓
     * phone : 13687797324
     * province : null
     * sex : null
     * status : null
     * tickets : null
     */

    private List<ApplicantListBean> applicantList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(String createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLaveSecond() {
        return laveSecond;
    }

    public void setLaveSecond(String laveSecond) {
        this.laveSecond = laveSecond;
    }

    public String getMoneyNews() {
        return moneyNews;
    }

    public void setMoneyNews(String moneyNews) {
        this.moneyNews = moneyNews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getReceptMoney() {
        return receptMoney;
    }

    public void setReceptMoney(String receptMoney) {
        this.receptMoney = receptMoney;
    }

    public int getSignId() {
        return signId;
    }

    public void setSignId(int signId) {
        this.signId = signId;
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

    public String getTicketsName() {
        return ticketsName;
    }

    public void setTicketsName(String ticketsName) {
        this.ticketsName = ticketsName;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public List<ApplicantListBean> getApplicantList() {
        return applicantList;
    }

    public void setApplicantList(List<ApplicantListBean> applicantList) {
        this.applicantList = applicantList;
    }

    public int getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(int isJoin) {
        this.isJoin = isJoin;
    }

    public static class ApplicantListBean {
        private String city;
        private String coutry;
        private String headPortrait;
        private int id;
        private String idCard;
        private String introduction;
        private String name;
        private String phone;
        private String province;
        private String sex;
        private String status;
        private Object tickets;

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

        public Object getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public Object getIntroduction() {
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getTickets() {
            return tickets;
        }

        public void setTickets(Object tickets) {
            this.tickets = tickets;
        }


    }
}
