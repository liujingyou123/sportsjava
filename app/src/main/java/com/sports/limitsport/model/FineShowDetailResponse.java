package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/4.
 */

public class FineShowDetailResponse extends BaseResponse {

    /**
     * atUserList : [{"name":"廖智明11","userId":"8"},{"name":"罗志祥","userId":"9"},{"name":"吴","userId":"10"}]
     * atUsers : null
     * attentionFlag : 0
     * collectionFlag : 1
     * content : 秀出你的人生
     * createTime : 1501748124000
     * headPortrait : http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg
     * id : 1
     * imgUrl : www.baidu.com
     * orderNum : null
     * praiseFlag : 0
     * praiseNum : 2
     * publishUserId : 8
     * publishUserName : 廖智明11
     * resourceType : 1
     * showCreateTime : 18小时前
     * status : 1
     * thumbnailUrl : null
     * title : 精彩秀秀
     * topFlag : 1
     * type : 2
     * vedioThumbnailUrl : null
     * vedioUrl :
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String atUsers;
        private int attentionFlag;
        private int collectionFlag;
        private String content;
        private long createTime;
        private String headPortrait;
        private int id;
        private String imgUrl;
        private String orderNum;
        private String praiseFlag;
        private int praiseNum;
        private int publishUserId;
        private String publishUserName;
        private int resourceType;
        private String showCreateTime;
        private int status;
        private String thumbnailUrl;
        private String title;
        private int topFlag;
        private int type;
        private String vedioThumbnailUrl;
        private String vedioUrl;
        private String vedioImgUrl;
        /**
         * name : 廖智明11
         * userId : 8
         */

        private List<AtUserListBean> atUserList;

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

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
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

        public int getResourceType() {
            return resourceType;
        }

        public void setResourceType(int resourceType) {
            this.resourceType = resourceType;
        }

        public String getShowCreateTime() {
            return showCreateTime;
        }

        public void setShowCreateTime(String showCreateTime) {
            this.showCreateTime = showCreateTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getThumbnailUrl() {
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

        public int getTopFlag() {
            return topFlag;
        }

        public void setTopFlag(int topFlag) {
            this.topFlag = topFlag;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public String getVedioImgUrl() {
            return vedioImgUrl;
        }

        public void setVedioImgUrl(String vedioImgUrl) {
            this.vedioImgUrl = vedioImgUrl;
        }

        public List<AtUserListBean> getAtUserList() {
            return atUserList;
        }

        public void setAtUserList(List<AtUserListBean> atUserList) {
            this.atUserList = atUserList;
        }

        public static class AtUserListBean {
            private String name;
            private String userId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
