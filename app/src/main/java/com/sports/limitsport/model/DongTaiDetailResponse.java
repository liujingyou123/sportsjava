package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/26.
 * 动态详情
 */

public class DongTaiDetailResponse extends BaseResponse {

    /**
     * activityId : null
     * activityName : null
     * atUserList : null
     * atUsers : null
     * attentionFlag : 0
     * clubId : null
     * clubName : null
     * collectionFlag : 0
     * content : 挑战自我，从滑雪开始
     * createTime : 1500563915000
     * creater : 8
     * headPortrait : null
     * id : 1
     * imgUrl :
     * praiseFlag : 0
     * praiseNum : null
     * publicType : 1
     * publishUserId : 8
     * publishUserName : 廖智明11
     * resourceType : 1
     * showCreateTime : 2017-07-20
     * thumbnailUrl : baidu.com
     * title : 挑战自我
     * trendType : null
     * vedioImgUrl : null
     * vedioThumbnailUrl : null
     * vedioUrl : null
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String activityId;
        private String activityName;
        private List<AtUserList> atUserList;
        private String atUsers;
        private int attentionFlag;
        private String clubId;
        private String clubName;
        private int collectionFlag;
        private String content;
        private long createTime;
        private int creater;
        private String headPortrait;
        private int id;
        private String imgUrl;
        private String praiseFlag;
        private String praiseNum;
        private int publicType;
        private int publishUserId;
        private String publishUserName;
        private String resourceType;
        private String showCreateTime;
        private String thumbnailUrl;
        private String title;
        private String trendType;
        private String vedioImgUrl;
        private String vedioThumbnailUrl;
        private String vedioUrl;

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

        public String getAtUsers() {
            return atUsers;
        }

        public void setAtUsers(String atUsers) {
            this.atUsers = atUsers;
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

        public int getCollectionFlag() {
            return collectionFlag;
        }

        public void setCollectionFlag(int collectionFlag) {
            this.collectionFlag = collectionFlag;
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

        public String getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(String praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getPublicType() {
            return publicType;
        }

        public void setPublicType(int publicType) {
            this.publicType = publicType;
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

        public String getTrendType() {
            return trendType;
        }

        public void setTrendType(String trendType) {
            this.trendType = trendType;
        }

        public String getVedioImgUrl() {
            return vedioImgUrl;
        }

        public void setVedioImgUrl(String vedioImgUrl) {
            this.vedioImgUrl = vedioImgUrl;
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

        public static class AtUserList {
            private String userId;
            private String name;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
