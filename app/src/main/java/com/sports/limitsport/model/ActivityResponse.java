package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/24.
 */

public class ActivityResponse extends BaseResponse{


    /**
     * data : [{"activityVideo":"http://v.youku.com/v_show/id_XODEzNDI1ODYw.html","activityVideoImg":"www.sohu.com","address":"上海地铁9号线九亭地铁站4号口","coverUrl":"www.e-cology.com.cn","endDate":"07月24日","endTime":"12:00","id":2,"maxMoney":"1300.00","minMoney":"999.00","name":"黄山3天2日旅行","startDate":"07月19日","startTime":"12:00","status":"1","ticketNum":200,"week":"4"}]
     * pageNumber : 1
     * pageSize : 10
     * query : 1
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
        private int query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * activityVideo : http://v.youku.com/v_show/id_XODEzNDI1ODYw.html
         * activityVideoImg : www.sohu.com
         * address : 上海地铁9号线九亭地铁站4号口
         * coverUrl : www.e-cology.com.cn
         * endDate : 07月24日
         * endTime : 12:00
         * id : 2
         * maxMoney : 1300.00
         * minMoney : 999.00
         * name : 黄山3天2日旅行
         * startDate : 07月19日
         * startTime : 12:00
         * status : 1
         * ticketNum : 200
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

        public int getQuery() {
            return query;
        }

        public void setQuery(int query) {
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

    }
}
