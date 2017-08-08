package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/8.
 */

public class ClubMembersResponse extends BaseResponse{

    /**
     * data : [{"clubId":1,"headPortrait":"http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg","id":1,"memberId":8,"memberName":null,"rule":1,"ruleDesc":"创始人","status":null,"userIntroduction":"我就是我 哈哈","version":null}]
     * pageNumber : 1
     * pageSize : 20
     * query : {"clubId":1,"pageNumber":1,"pageSize":10,"ruleType":0,"ruleTypeList":[1,2,3,4]}
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
         * clubId : 1
         * pageNumber : 1
         * pageSize : 10
         * ruleType : 0
         * ruleTypeList : [1,2,3,4]
         */

        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * clubId : 1
         * headPortrait : http://ex-fans-tst.oss-cn-hangzhou.aliyuncs.com/head/portrait/IMG_1501487889176.jpg
         * id : 1
         * memberId : 8
         * memberName : null
         * rule : 1
         * ruleDesc : 创始人
         * status : null
         * userIntroduction : 我就是我 哈哈
         * version : null
         */

        private List<ClubMemberList> data;

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

        public List<ClubMemberList> getData() {
            return data;
        }

        public void setData(List<ClubMemberList> data) {
            this.data = data;
        }
    }
}
