package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/31.
 */

public class HuDongNoticeListResponse extends BaseResponse {

    /**
     * data : [{"content":"测试6","datetime":"2017-07-31 18:23:02","firstContent":"11111111111111","firstImgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501229948135.jpg","headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501229948135.jpg","id":6,"myName":"吴","name":"廖智明121","type":0}]
     * pageNumber : 1
     * pageSize : 20
     * query : {"pageNumber":1,"pageSize":20,"type":0,"userId":10}
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
         * pageSize : 20
         * type : 0
         * userId : 10
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * content : 测试6
         * datetime : 2017-07-31 18:23:02
         * firstContent : 11111111111111
         * firstImgUrl : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501229948135.jpg
         * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501229948135.jpg
         * id : 6
         * myName : 吴
         * name : 廖智明121
         * type : 0
         */

        private List<HuDongNoticeList> data;

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

        public List<HuDongNoticeList> getData() {
            return data;
        }

        public void setData(List<HuDongNoticeList> data) {
            this.data = data;
        }

        public static class QueryBean {
            private int pageNumber;
            private int pageSize;
            private int type;
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
