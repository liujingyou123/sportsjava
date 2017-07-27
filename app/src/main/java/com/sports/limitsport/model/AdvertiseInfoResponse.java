package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/24.
 */

public class AdvertiseInfoResponse extends BaseResponse {


    /**
     * adPicUrl :
     * adType : 2
     * content : 头条轮播图
     * innerUrlId : 1a
     * name : 头条轮播图
     * orderNum : 1
     * position : 1
     * toUrl : aaa.com
     * toUrlType : 1
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String adPicUrl;
        private int adType;
        private String content;
        private String innerUrlId;
        private String name;
        private int orderNum;
        private int position;
        private String toUrl;
        private int toUrlType;

        public String getAdPicUrl() {
            return adPicUrl;
        }

        public void setAdPicUrl(String adPicUrl) {
            this.adPicUrl = adPicUrl;
        }

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getInnerUrlId() {
            return innerUrlId;
        }

        public void setInnerUrlId(String innerUrlId) {
            this.innerUrlId = innerUrlId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getToUrl() {
            return toUrl;
        }

        public void setToUrl(String toUrl) {
            this.toUrl = toUrl;
        }

        public int getToUrlType() {
            return toUrlType;
        }

        public void setToUrlType(int toUrlType) {
            this.toUrlType = toUrlType;
        }
    }
}
