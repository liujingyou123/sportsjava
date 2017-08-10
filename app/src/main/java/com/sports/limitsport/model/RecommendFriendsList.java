package com.sports.limitsport.model;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/10.
 */

public class RecommendFriendsList {
    private int attentionFlag;
    private String headPortrait;
    private int id;
    private String introduction;
    private String name;
    private String sex;
    /**
     * id : 2
     * imgUrl : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg
     * resourceType : 1
     * thumbnailUrl : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg?x-oss-process=image/resize,w_100
     * vedioImgUrl : baidu.vedio.img
     * vedioThumbnailUrl : baidu.vedio.img?x-oss-process=image/resize,w_100
     */

    private List<RecommendDongTai> trendInfoDTOList;

    public int getAttentionFlag() {
        return attentionFlag;
    }

    public void setAttentionFlag(int attentionFlag) {
        this.attentionFlag = attentionFlag;
    }

    public String getHeadPortrait() {
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

    public List<RecommendDongTai> getTrendInfoDTOList() {
        return trendInfoDTOList;
    }

    public void setTrendInfoDTOList(List<RecommendDongTai> trendInfoDTOList) {
        this.trendInfoDTOList = trendInfoDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendFriendsList that = (RecommendFriendsList) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
