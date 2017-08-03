package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/26.
 * 动态列表
 */

public class DongTaiListResponse extends BaseResponse {

    /**
     * data : [{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":0,"clubId":null,"clubName":null,"commentList":[{"commentatorName":"廖智明11","content":"帅气"},{"commentatorName":"廖智明11","content":"有前途"},{"commentatorName":"廖智明11","content":"帅气"}],"content":"挑战自我，从滑雪开始","createTime":1500563915000,"creater":8,"headPortrait":"http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg","id":1,"imgUrl":null,"praiseFlag":0,"praiseNum":2,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"2017-07-20","thumbnailUrl":"baidu.com","title":"挑战自我","vedioThumbnailUrl":null,"vedioUrl":null}]
     * pageNumber : 1
     * pageSize : 20
     * query : {"activityId":null,"myUserId":8,"pageNumber":1,"pageSize":20,"publicType":1,"status":1,"trendTypes":"1,2","type":0}
     * start : 0
     * totalPage : 1
     * totalSize : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int pageNumber;
        private int pageSize;
        /**
         * activityId : null
         * myUserId : 8
         * pageNumber : 1
         * pageSize : 20
         * publicType : 1
         * status : 1
         * trendTypes : 1,2
         * type : 0
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * activityId : null
         * activityName : null
         * atUserList : null
         * attentionFlag : 0
         * clubId : null
         * clubName : null
         * commentList : [{"commentatorName":"廖智明11","content":"帅气"},{"commentatorName":"廖智明11","content":"有前途"},{"commentatorName":"廖智明11","content":"帅气"}]
         * content : 挑战自我，从滑雪开始
         * createTime : 1500563915000
         * creater : 8
         * headPortrait : http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg
         * id : 1
         * imgUrl : null
         * praiseFlag : 0
         * praiseNum : 2
         * publishUserId : 8
         * publishUserName : 廖智明11
         * resourceType : 1
         * showCreateTime : 2017-07-20
         * thumbnailUrl : baidu.com
         * title : 挑战自我
         * vedioThumbnailUrl : null
         * vedioUrl : null
         */

        private List<DongTaiList> data;

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public List<DongTaiList> getData() {
            return data;
        }

        public void setData(List<DongTaiList> data) {
            this.data = data;
        }

        public static class QueryBean {
            private Object activityId;
            private int myUserId;
            private int pageNumber;
            private int pageSize;
            private int publicType;
            private int status;
            private String trendTypes;
            private int type;

            public Object getActivityId() {
                return activityId;
            }

            public void setActivityId(Object activityId) {
                this.activityId = activityId;
            }

            public int getMyUserId() {
                return myUserId;
            }

            public void setMyUserId(int myUserId) {
                this.myUserId = myUserId;
            }

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPublicType() {
                return publicType;
            }

            public void setPublicType(int publicType) {
                this.publicType = publicType;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTrendTypes() {
                return trendTypes;
            }

            public void setTrendTypes(String trendTypes) {
                this.trendTypes = trendTypes;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

    }
}
