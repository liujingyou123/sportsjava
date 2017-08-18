package com.sports.limitsport.model;

import com.sports.limitsport.base.SelectEntity;

import java.io.Serializable;

/**
 * Created by liuworkmac on 17/6/21.
 */

public class Act extends SelectEntity implements Serializable{
    private String activityVideo;
    private String activityVideoImg;
    private String address;
    private String coverUrl;
    private String endDate;
    private String endTime;
    private int id;
    private String maxMoney;
    private String minMoney;
    private String name;
    private String startDate;
    private String startTime;
    private String status;
    private int ticketNum;
    private String week;
    private int width;
    private int height;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Act act = (Act) o;

        return id == act.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
