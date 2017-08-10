package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/10.
 */

public class DongTaiOrRecommendResponse extends BaseResponse {


    /**
     * page : {"data":[{"activityId":1,"activityName":"阿达","atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"展示帅气的自己","createTime":1502275781000,"creater":8,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":16,"imgUrl":"baidu.com","praiseFlag":0,"praiseNum":0,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"15小时前","thumbnailUrl":"baidu.com?x-oss-process=image/resize,w_100","title":"秀出自我","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100","vedioUrl":null},{"activityId":1,"activityName":"阿达","atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"展示帅气的自己","createTime":1502275720000,"creater":8,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":15,"imgUrl":"baidu.com","praiseFlag":0,"praiseNum":0,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"15小时前","thumbnailUrl":"baidu.com?x-oss-process=image/resize,w_100","title":"秀出自我","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100","vedioUrl":null},{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"测试图片","createTime":1502272146000,"creater":30,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":14,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022721458351502272139057_dongtai.jpg","praiseFlag":0,"praiseNum":0,"publishUserId":30,"publishUserName":"小黑","resourceType":1,"showCreateTime":"16小时前","thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022721458351502272139057_dongtai.jpg?x-oss-process=image/resize,w_100","title":null,"vedioImgUrl":null,"vedioThumbnailUrl":null,"vedioUrl":null},{"activityId":8,"activityName":"测试0807","atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"今天好啊","createTime":1502272057000,"creater":30,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":13,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022720562181502272047713_dongtai.jpg","praiseFlag":0,"praiseNum":0,"publishUserId":30,"publishUserName":"小黑","resourceType":1,"showCreateTime":"16小时前","thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022720562181502272047713_dongtai.jpg?x-oss-process=image/resize,w_100","title":null,"vedioImgUrl":null,"vedioThumbnailUrl":null,"vedioUrl":null},{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"这是之地东","createTime":1502270256000,"creater":30,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":12,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022702555591502270237291_dongtai.jpg","praiseFlag":0,"praiseNum":0,"publishUserId":30,"publishUserName":"小黑","resourceType":1,"showCreateTime":"17小时前","thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022702555591502270237291_dongtai.jpg?x-oss-process=image/resize,w_100","title":null,"vedioImgUrl":null,"vedioThumbnailUrl":null,"vedioUrl":null},{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"囧西游","createTime":1502269956000,"creater":30,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":11,"imgUrl":null,"praiseFlag":0,"praiseNum":0,"publishUserId":30,"publishUserName":"小黑","resourceType":1,"showCreateTime":"17小时前","thumbnailUrl":null,"title":null,"vedioImgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022699555851502269955370.jpg","vedioThumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022699555851502269955370.jpg?x-oss-process=image/resize,w_100","vedioUrl":null},{"activityId":1,"activityName":"阿达","atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"展示帅气的自己","createTime":1502265950000,"creater":8,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":5,"imgUrl":"baidu.com","praiseFlag":0,"praiseNum":0,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"18小时前","thumbnailUrl":"baidu.com?x-oss-process=image/resize,w_100","title":"秀出自我","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100","vedioUrl":null},{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[{"commentatorName":"廖智明11","content":"帅气"},{"commentatorName":"廖智明11","content":"有前途"},{"commentatorName":"廖智明11","content":"帅气"}],"content":"挑战自我，从滑雪开始","createTime":1500563915000,"creater":8,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":1,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","praiseFlag":0,"praiseNum":2,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"2017-07-20","thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg?x-oss-process=image/resize,w_100","title":"挑战自我","vedioImgUrl":null,"vedioThumbnailUrl":null,"vedioUrl":null}],"pageNumber":1,"pageSize":10,"query":{"pageNumber":1,"pageSize":10,"publicType":1,"status":1,"userId":30},"start":0,"totalPage":1,"totalSize":8}
     * type : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * data : [{"activityId":1,"activityName":"阿达","atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"展示帅气的自己","createTime":1502275781000,"creater":8,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":16,"imgUrl":"baidu.com","praiseFlag":0,"praiseNum":0,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"15小时前","thumbnailUrl":"baidu.com?x-oss-process=image/resize,w_100","title":"秀出自我","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100","vedioUrl":null},{"activityId":1,"activityName":"阿达","atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"展示帅气的自己","createTime":1502275720000,"creater":8,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":15,"imgUrl":"baidu.com","praiseFlag":0,"praiseNum":0,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"15小时前","thumbnailUrl":"baidu.com?x-oss-process=image/resize,w_100","title":"秀出自我","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100","vedioUrl":null},{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"测试图片","createTime":1502272146000,"creater":30,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":14,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022721458351502272139057_dongtai.jpg","praiseFlag":0,"praiseNum":0,"publishUserId":30,"publishUserName":"小黑","resourceType":1,"showCreateTime":"16小时前","thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022721458351502272139057_dongtai.jpg?x-oss-process=image/resize,w_100","title":null,"vedioImgUrl":null,"vedioThumbnailUrl":null,"vedioUrl":null},{"activityId":8,"activityName":"测试0807","atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"今天好啊","createTime":1502272057000,"creater":30,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":13,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022720562181502272047713_dongtai.jpg","praiseFlag":0,"praiseNum":0,"publishUserId":30,"publishUserName":"小黑","resourceType":1,"showCreateTime":"16小时前","thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022720562181502272047713_dongtai.jpg?x-oss-process=image/resize,w_100","title":null,"vedioImgUrl":null,"vedioThumbnailUrl":null,"vedioUrl":null},{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"这是之地东","createTime":1502270256000,"creater":30,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":12,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022702555591502270237291_dongtai.jpg","praiseFlag":0,"praiseNum":0,"publishUserId":30,"publishUserName":"小黑","resourceType":1,"showCreateTime":"17小时前","thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022702555591502270237291_dongtai.jpg?x-oss-process=image/resize,w_100","title":null,"vedioImgUrl":null,"vedioThumbnailUrl":null,"vedioUrl":null},{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"囧西游","createTime":1502269956000,"creater":30,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1502087683378.jpg","id":11,"imgUrl":null,"praiseFlag":0,"praiseNum":0,"publishUserId":30,"publishUserName":"小黑","resourceType":1,"showCreateTime":"17小时前","thumbnailUrl":null,"title":null,"vedioImgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022699555851502269955370.jpg","vedioThumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/15022699555851502269955370.jpg?x-oss-process=image/resize,w_100","vedioUrl":null},{"activityId":1,"activityName":"阿达","atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[],"content":"展示帅气的自己","createTime":1502265950000,"creater":8,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":5,"imgUrl":"baidu.com","praiseFlag":0,"praiseNum":0,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"18小时前","thumbnailUrl":"baidu.com?x-oss-process=image/resize,w_100","title":"秀出自我","vedioImgUrl":"baidu.vedio.img","vedioThumbnailUrl":"baidu.vedio.img?x-oss-process=image/resize,w_100","vedioUrl":null},{"activityId":null,"activityName":null,"atUserList":null,"attentionFlag":null,"clubId":null,"clubName":null,"commentList":[{"commentatorName":"廖智明11","content":"帅气"},{"commentatorName":"廖智明11","content":"有前途"},{"commentatorName":"廖智明11","content":"帅气"}],"content":"挑战自我，从滑雪开始","createTime":1500563915000,"creater":8,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":1,"imgUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","praiseFlag":0,"praiseNum":2,"publishUserId":8,"publishUserName":"廖智明11","resourceType":1,"showCreateTime":"2017-07-20","thumbnailUrl":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg?x-oss-process=image/resize,w_100","title":"挑战自我","vedioImgUrl":null,"vedioThumbnailUrl":null,"vedioUrl":null}]
         * pageNumber : 1
         * pageSize : 10
         * query : {"pageNumber":1,"pageSize":10,"publicType":1,"status":1,"userId":30}
         * start : 0
         * totalPage : 1
         * totalSize : 8
         */

        private PageBean page;
        private int type;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public static class PageBean {
            private int pageNumber;
            private int pageSize;
            /**
             * pageNumber : 1
             * pageSize : 10
             * publicType : 1
             * status : 1
             * userId : 30
             */

            private QueryBean query;
            private int start;
            private int totalPage;
            private int totalSize;
            /**
             * activityId : 1
             * activityName : 阿达
             * atUserList : null
             * attentionFlag : null
             * clubId : null
             * clubName : null
             * commentList : []
             * content : 展示帅气的自己
             * createTime : 1502275781000
             * creater : 8
             * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg
             * id : 16
             * imgUrl : baidu.com
             * praiseFlag : 0
             * praiseNum : 0
             * publishUserId : 8
             * publishUserName : 廖智明11
             * resourceType : 1
             * showCreateTime : 15小时前
             * thumbnailUrl : baidu.com?x-oss-process=image/resize,w_100
             * title : 秀出自我
             * vedioImgUrl : baidu.vedio.img
             * vedioThumbnailUrl : baidu.vedio.img?x-oss-process=image/resize,w_100
             * vedioUrl : null
             */

            private Object data;

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

            public Object getData() {
                return data;
            }

            public void setData(Object data) {
                this.data = data;
            }

            public static class QueryBean {
                private int pageNumber;
                private int pageSize;
                private int publicType;
                private int status;
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

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }
            }


        }
    }
}
