package com.sports.limitsport.model;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/26.
 */

public class DongTaiList {
    private String activityId;
    private String activityName;
    private List<AtUserList> atUserList;
    private int attentionFlag;
    private String clubId;
    private String clubName;
    private String content;
    private long createTime;
    private int creater;
    private String headPortrait;
    private int id;
    private String imgUrl;
    private String praiseFlag;
    private int praiseNum;
    private int publishUserId;
    private String publishUserName;
    private String resourceType;
    private String showCreateTime;
    private String thumbnailUrl;
    private String title;
    private String vedioThumbnailUrl;
    private String vedioImgUrl;
    private String vedioUrl;
    private int width;
    private int height;
    /**
     * commentatorName : 廖智明11
     * content : 帅气
     */

    private List<CommentListBean> commentList;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<AtUserList> getAtUserList() {
        return atUserList;
    }

    public void setAtUserList(List<AtUserList> atUserList) {
        this.atUserList = atUserList;
    }

    public int getAttentionFlag() {
        return attentionFlag;
    }

    public void setAttentionFlag(int attentionFlag) {
        this.attentionFlag = attentionFlag;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCreater() {
        return creater;
    }

    public void setCreater(int creater) {
        this.creater = creater;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPraiseFlag() {
        return praiseFlag;
    }

    public void setPraiseFlag(String praiseFlag) {
        this.praiseFlag = praiseFlag;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getPublishUserId() {
        return publishUserId;
    }

    public void setPublishUserId(int publishUserId) {
        this.publishUserId = publishUserId;
    }

    public String getPublishUserName() {
        return publishUserName;
    }

    public void setPublishUserName(String publishUserName) {
        this.publishUserName = publishUserName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getShowCreateTime() {
        return showCreateTime;
    }

    public void setShowCreateTime(String showCreateTime) {
        this.showCreateTime = showCreateTime;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVedioThumbnailUrl() {
        return vedioThumbnailUrl;
    }

    public void setVedioThumbnailUrl(String vedioThumbnailUrl) {
        this.vedioThumbnailUrl = vedioThumbnailUrl;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    public List<CommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean> commentList) {
        this.commentList = commentList;
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

    public String getVedioImgUrl() {
        return vedioImgUrl;
    }

    public void setVedioImgUrl(String vedioImgUrl) {
        this.vedioImgUrl = vedioImgUrl;
    }

    public static class CommentListBean {
        private String commentatorName;
        private String content;

        public String getCommentatorName() {
            return commentatorName;
        }

        public void setCommentatorName(String commentatorName) {
            this.commentatorName = commentatorName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DongTaiList that = (DongTaiList) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
