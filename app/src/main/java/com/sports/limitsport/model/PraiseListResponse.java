package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/9.
 */

public class PraiseListResponse extends BaseResponse {

    /**
     * data : [{"articleId":1,"city":"杨浦区","headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":36,"name":"小黑","praiseType":2,"province":"上海","sex":0,"userId":30},{"articleId":1,"city":"上海","headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501748784529.jpg","id":6,"name":"哦豁","praiseType":2,"province":null,"sex":0,"userId":25},{"articleId":1,"city":"上海","headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501744700454.jpg","id":3,"name":"刘鑫女","praiseType":2,"province":null,"sex":1,"userId":23}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"pageNumber":1,"pageSize":10,"praiseType":2,"trendId":1}
     * start : 0
     * totalPage : 1
     * totalSize : 3
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
         * praiseType : 2
         * trendId : 1
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * articleId : 1
         * city : 杨浦区
         * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg
         * id : 36
         * name : 小黑
         * praiseType : 2
         * province : 上海
         * sex : 0
         * userId : 30
         */

        private List<PraiseList> data;

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

        public List<PraiseList> getData() {
            return data;
        }

        public void setData(List<PraiseList> data) {
            this.data = data;
        }

        public static class QueryBean {
            private int pageNumber;
            private int pageSize;
            private int praiseType;
            private int trendId;

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

            public int getPraiseType() {
                return praiseType;
            }

            public void setPraiseType(int praiseType) {
                this.praiseType = praiseType;
            }

            public int getTrendId() {
                return trendId;
            }

            public void setTrendId(int trendId) {
                this.trendId = trendId;
            }
        }
    }
}
