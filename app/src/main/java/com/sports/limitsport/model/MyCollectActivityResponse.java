package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/7.
 */

public class MyCollectActivityResponse extends BaseResponse{

    /**
     * data : [{"activityVideo":"http://v.youku.com/v_show/id_XODEzNDI1ODYw.html","activityVideoImg":"www.sohu.com","address":"上海地铁9号线九亭地铁站4号口","coverUrl":"www.e-cology.com.cn","endDate":"2017-07-24","endTime":null,"id":2,"maxMoney":"1300.00","minMoney":"999.00","name":"黄山3天2日旅行","startDate":"2017-07-19","startTime":null,"status":"1","ticketNum":null,"week":"4"}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"club":null,"pageNumber":1,"pageSize":10,"userId":14}
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
         * club : null
         * pageNumber : 1
         * pageSize : 10
         * userId : 14
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * activityVideo : http://v.youku.com/v_show/id_XODEzNDI1ODYw.html
         * activityVideoImg : www.sohu.com
         * address : 上海地铁9号线九亭地铁站4号口
         * coverUrl : www.e-cology.com.cn
         * endDate : 2017-07-24
         * endTime : null
         * id : 2
         * maxMoney : 1300.00
         * minMoney : 999.00
         * name : 黄山3天2日旅行
         * startDate : 2017-07-19
         * startTime : null
         * status : 1
         * ticketNum : null
         * week : 4
         */

        private List<Act> data;

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

        public List<Act> getData() {
            return data;
        }

        public void setData(List<Act> data) {
            this.data = data;
        }

        public static class QueryBean {
            private Object club;
            private int pageNumber;
            private int pageSize;
            private int userId;

            public Object getClub() {
                return club;
            }

            public void setClub(Object club) {
                this.club = club;
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
