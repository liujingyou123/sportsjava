package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/2.
 */

public class FansListResponse extends BaseResponse{

    /**
     * data : [{"city":"上海","dateTime":"2017-08-02","headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501656590365.jpg","id":"3","introduction":"这是弄我魔攻","name":"玫琳普通哦哦哦凯","sex":"0","showCreateTime":null,"status":"1"}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"pageNumber":1,"pageSize":10,"userId":14}
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
         * pageNumber : 1
         * pageSize : 10
         * userId : 14
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * city : 上海
         * dateTime : 2017-08-02
         * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501656590365.jpg
         * id : 3
         * introduction : 这是弄我魔攻
         * name : 玫琳普通哦哦哦凯
         * sex : 0
         * showCreateTime : null
         * status : 1
         */

        private List<FansList> data;

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

        public List<FansList> getData() {
            return data;
        }

        public void setData(List<FansList> data) {
            this.data = data;
        }

        public static class QueryBean {
            private int pageNumber;
            private int pageSize;
            private int userId;

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
