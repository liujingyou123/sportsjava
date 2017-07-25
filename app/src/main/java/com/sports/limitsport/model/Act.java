package com.sports.limitsport.model;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class Act {
    private String id;
    private String status;//状态(0:草稿 1:报名中 2:进行中 3:已完成)
    private String address;
    private String name;
    private String coverUrl;
    private String dateTime;
    private String week;
    private String maxMoney;
    private String minMoney;
    private String activityVideo;
    private String activityVideoImg;
    private long ticketNum;


    private int width;
    private int height;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(String maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
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

    public long getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(long ticketNum) {
        this.ticketNum = ticketNum;
    }
}
