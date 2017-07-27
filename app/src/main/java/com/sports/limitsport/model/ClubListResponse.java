package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/24.
 */

public class ClubListResponse extends BaseResponse {


    /**
     * data : [{"authEntity":2,"authStatus":1,"clubImgUrl":"ab.img","clubIntroduction":"冲出亚马逊","clubName":"冲浪俱乐部","clubType":2,"clubTypeName":"滑板","clubVedioUrl":null,"id":2,"isActivity":0,"isJoin":0,"logoUrl":"dongtong.com","memberNum":5,"resourceType":1}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"authStatus":1,"pageNumber":1,"pageSize":10,"status":2,"type":2,"userId":8}
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
         * authStatus : 1
         * pageNumber : 1
         * pageSize : 10
         * status : 2
         * type : 2
         * userId : 8
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * authEntity : 2
         * authStatus : 1
         * clubImgUrl : ab.img
         * clubIntroduction : 冲出亚马逊
         * clubName : 冲浪俱乐部
         * clubType : 2
         * clubTypeName : 滑板
         * clubVedioUrl : null
         * id : 2
         * isActivity : 0
         * isJoin : 0
         * logoUrl : dongtong.com
         * memberNum : 5
         * resourceType : 1
         */

        private List<Club> data;

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

        public List<Club> getData() {
            return data;
        }

        public void setData(List<Club> data) {
            this.data = data;
        }

        public static class QueryBean {
            private int authStatus;
            private int pageNumber;
            private int pageSize;
            private int status;
            private int type;
            private int userId;

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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
