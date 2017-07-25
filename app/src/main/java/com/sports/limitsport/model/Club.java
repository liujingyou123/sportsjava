package com.sports.limitsport.model;

/**
 * Created by jingyouliu on 17/7/9.
 */

public class Club {
    private String id;
    private String clubName;
    private String clubType;// 1:滑雪 2:滑板 3：冲浪 4：跑酷
    private String clubTypeName;//俱乐部类型名称
    private String clubIntroduction; //俱乐部简介
    private String logoUrl; //俱乐部logo
    private String resourceType;//图片资源类型 1：图片 2：视频
    private String thumbnailUrl; //俱乐部图片缩略图url
    private String vedioThumbnailUrl; //视频首图缩略图url
    private String clubMemberNum; //俱乐部成员数
    private String isJoin;//是否已参加 1:已加入 0:未加入
    private String isActivity;//是否活动中 1:活动中 0:没有活动

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubType() {
        return clubType;
    }

    public void setClubType(String clubType) {
        this.clubType = clubType;
    }

    public String getClubTypeName() {
        return clubTypeName;
    }

    public void setClubTypeName(String clubTypeName) {
        this.clubTypeName = clubTypeName;
    }

    public String getClubIntroduction() {
        return clubIntroduction;
    }

    public void setClubIntroduction(String clubIntroduction) {
        this.clubIntroduction = clubIntroduction;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVedioThumbnailUrl() {
        return vedioThumbnailUrl;
    }

    public void setVedioThumbnailUrl(String vedioThumbnailUrl) {
        this.vedioThumbnailUrl = vedioThumbnailUrl;
    }

    public String getClubMemberNum() {
        return clubMemberNum;
    }

    public void setClubMemberNum(String clubMemberNum) {
        this.clubMemberNum = clubMemberNum;
    }

    public String getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(String isJoin) {
        this.isJoin = isJoin;
    }

    public String getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(String isActivity) {
        this.isActivity = isActivity;
    }
}
