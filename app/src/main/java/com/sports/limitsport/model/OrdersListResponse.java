package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/8/17.
 */

public class OrdersListResponse extends BaseResponse{

    /**
     * data : [{"coverUrl":"http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg","id":2,"moneyNews":"666.00","name":"测试0807","number":2,"orderNo":"Dd332323","orderStatus":"1","receptMoney":"3000.00","ticketsName":"平民票","totalMoney":"666.00"},{"coverUrl":"","id":3,"moneyNews":"999.00","name":"黄山3天2日旅行","number":1,"orderNo":"D43423233","orderStatus":"1","receptMoney":"2000.00","ticketsName":"标准票","totalMoney":"688.00"},{"coverUrl":"http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg","id":20,"moneyNews":"99.00","name":"测试0807","number":1,"orderNo":"A1708160017","orderStatus":"0","receptMoney":"0.01","ticketsName":"尊享票","totalMoney":"0.01"},{"coverUrl":"http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg","id":21,"moneyNews":"99.00","name":"测试0807","number":1,"orderNo":"A1708160018","orderStatus":"0","receptMoney":"0.01","ticketsName":"尊享票","totalMoney":"0.01"},{"coverUrl":"http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg","id":22,"moneyNews":"99.00","name":"测试0807","number":1,"orderNo":"A1708160019","orderStatus":"0","receptMoney":"0.01","ticketsName":"尊享票","totalMoney":"0.01"},{"coverUrl":"http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg","id":23,"moneyNews":"99.00","name":"测试0807","number":1,"orderNo":"A1708160020","orderStatus":"0","receptMoney":"0.01","ticketsName":"尊享票","totalMoney":"0.01"},{"coverUrl":"http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg","id":24,"moneyNews":"99.00","name":"测试0807","number":1,"orderNo":"A1708160021","orderStatus":"0","receptMoney":"0.01","ticketsName":"尊享票","totalMoney":"0.01"},{"coverUrl":"http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg","id":29,"moneyNews":"99.00","name":"测试0807","number":1,"orderNo":"A1708160026","orderStatus":"0","receptMoney":"0.01","ticketsName":"尊享票","totalMoney":"0.01"},{"coverUrl":"http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg","id":38,"moneyNews":"99.00","name":"测试0807","number":1,"orderNo":"A1708160035","orderStatus":"0","receptMoney":"0.01","ticketsName":"尊享票","totalMoney":"0.01"}]
     * pageNumber : 1
     * pageSize : 10
     * query : {"pageNumber":1,"pageSize":10,"userId":30}
     * start : 0
     * totalPage : 1
     * totalSize : 9
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
         * userId : 30
         */

        private QueryBean query;
        private int start;
        private int totalPage;
        private int totalSize;
        /**
         * coverUrl : http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/07/IMG_1502075079892_77855.jpg
         * id : 2
         * moneyNews : 666.00
         * name : 测试0807
         * number : 2
         * orderNo : Dd332323
         * orderStatus : 1
         * receptMoney : 3000.00
         * ticketsName : 平民票
         * totalMoney : 666.00
         */

        private List<OrdersList> data;

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

        public List<OrdersList> getData() {
            return data;
        }

        public void setData(List<OrdersList> data) {
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
