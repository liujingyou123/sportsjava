package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/10.
 */

public class RecomendFriendsListResponse extends BaseResponse {

    /**
     * data : [{"attentionFlag":0,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":9,"introduction":"都是","name":"罗志祥","sex":null,"trendInfoDTOList":[{"id":2,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","resourceType":1,"thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg?x-oss-process=image/resize,w_100","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100"},{"id":3,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","resourceType":1,"thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg?x-oss-process=image/resize,w_100","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100"}]},{"attentionFlag":0,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":10,"introduction":"这是弄我魔女幼熙哦哦哦五天","name":"吴","sex":0,"trendInfoDTOList":[]},{"attentionFlag":0,"headPortrait":null,"id":11,"introduction":"阿诗丹顿","name":"订单","sex":null,"trendInfoDTOList":[]}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"attentionUserId":null,"commentType":null,"myUserId":30,"pageNumber":1,"pageSize":10}
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
         * attentionUserId : null
         * commentType : null
         * myUserId : 30
         * pageNumber : 1
         * pageSize : 10
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * attentionFlag : 0
         * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg
         * id : 9
         * introduction : 都是
         * name : 罗志祥
         * sex : null
         * trendInfoDTOList : [{"id":2,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","resourceType":1,"thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg?x-oss-process=image/resize,w_100","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100"},{"id":3,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","resourceType":1,"thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg?x-oss-process=image/resize,w_100","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100"}]
         */

        private List<RecommendFriendsList> data;

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

        public List<RecommendFriendsList> getData() {
            return data;
        }

        public void setData(List<RecommendFriendsList> data) {
            this.data = data;
        }

        public static class QueryBean {
            private Object attentionUserId;
            private Object commentType;
            private int myUserId;
            private int pageNumber;
            private int pageSize;

            public Object getAttentionUserId() {
                return attentionUserId;
            }

            public void setAttentionUserId(Object attentionUserId) {
                this.attentionUserId = attentionUserId;
            }

            public Object getCommentType() {
                return commentType;
            }

            public void setCommentType(Object commentType) {
                this.commentType = commentType;
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
        }
    }
}
