package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/26.
 */

public class CommentListResponse extends BaseResponse{


    /**
     * data : [{"articleId":1,"commentType":1,"commentatorId":8,"commentatorName":"廖智明11","commentedUserId":null,"commentedUserName":null,"content":"帅气","createTime":"2017-7-25 18:32:05","headPortrait":"http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg","id":3,"replyList":[],"showCreateTime":null},{"articleId":1,"commentType":1,"commentatorId":8,"commentatorName":"廖智明11","commentedUserId":null,"commentedUserName":null,"content":"有前途","createTime":"2017-7-21 13:38:40","headPortrait":"http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg","id":2,"replyList":[],"showCreateTime":null},{"articleId":1,"commentType":1,"commentatorId":8,"commentatorName":"廖智明11","commentedUserId":null,"commentedUserName":null,"content":"帅气","createTime":"2017-7-21 13:36:24","headPortrait":"http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg","id":1,"replyList":[{"commentUserId":8,"commentUserName":"廖智明11","createTime":1500628799000,"creater":8,"id":1,"replyCommentId":1,"replyContent":"自恋","replyUserId":8,"replyUserName":"廖智明11"}],"showCreateTime":null}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"articleId":1,"pageNumber":1,"pageSize":10}
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
         * articleId : 1
         * pageNumber : 1
         * pageSize : 10
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * articleId : 1
         * commentType : 1
         * commentatorId : 8
         * commentatorName : 廖智明11
         * commentedUserId : null
         * commentedUserName : null
         * content : 帅气
         * createTime : 2017-7-25 18:32:05
         * headPortrait : http://img1.imgtn.bdimg.com/it/u=2670034815,688956640&fm=26&gp=0.jpg
         * id : 3
         * replyList : []
         * showCreateTime : null
         */

        private List<CommentList> data;

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

        public List<CommentList> getData() {
            return data;
        }

        public void setData(List<CommentList> data) {
            this.data = data;
        }

        public static class QueryBean {
            private int articleId;
            private int pageNumber;
            private int pageSize;

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
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
        }

    }
}
