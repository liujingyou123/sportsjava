package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/27.
 * 活动报名列表
 */

public class SignUpListResponse extends BaseResponse{

    /**
     * data : [{"city":"上海","coutry":"中国","headPortrait":"http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg","id":8,"idCard":null,"introduction":null,"name":"廖智明121","phone":null,"sex":"0","status":"0","tickets":null}]
     * pageNumber : 1
     * pageSize : 20
     * query : {"activityId":2,"pageNumber":1,"pageSize":20,"userId":8}
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
         * activityId : 2
         * pageNumber : 1
         * pageSize : 20
         * userId : 8
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
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

        private List<SignUpUser> data;

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

        public List<SignUpUser> getData() {
            return data;
        }

        public void setData(List<SignUpUser> data) {
            this.data = data;
        }

        public static class QueryBean {
            private int activityId;
            private int pageNumber;
            private int pageSize;
            private int userId;

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }

    }
}
