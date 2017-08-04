package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/4.
 */

public class MyCollectFineShowResponse extends BaseResponse {

    /**
     * data : [{"atUserList":null,"atUsers":null,"attentionFlag":null,"collectionFlag":null,"content":"秀出你的人生","createTime":null,"headPortrait":null,"id":1,"imgUrl":null,"orderNum":null,"praiseFlag":null,"praiseNum":null,"publishUserId":null,"publishUserName":null,"resourceType":null,"showCreateTime":null,"status":1,"thumbnailUrl":null,"title":"精彩秀秀","topFlag":null,"type":2,"vedioThumbnailUrl":null,"vedioUrl":null}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"pageNumber":1,"pageSize":10,"userId":26}
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
         * userId : 26
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * atUserList : null
         * atUsers : null
         * attentionFlag : null
         * collectionFlag : null
         * content : 秀出你的人生
         * createTime : null
         * headPortrait : null
         * id : 1
         * imgUrl : null
         * orderNum : null
         * praiseFlag : null
         * praiseNum : null
         * publishUserId : null
         * publishUserName : null
         * resourceType : null
         * showCreateTime : null
         * status : 1
         * thumbnailUrl : null
         * title : 精彩秀秀
         * topFlag : null
         * type : 2
         * vedioThumbnailUrl : null
         * vedioUrl : null
         */

        private List<FineShowList> data;

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

        public List<FineShowList> getData() {
            return data;
        }

        public void setData(List<FineShowList> data) {
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
