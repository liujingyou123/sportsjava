package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/24.
 */

public class AdvertiseInfoResponse extends BaseResponse {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String name;
        private String toUrlType; //  0-外部 1-内部连接
        private String adPicUrl;
        private String toUrl;
        private String innerUrlId;
        private String content;
        private String orderNum;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getToUrlType() {
            return toUrlType;
        }

        public void setToUrlType(String toUrlType) {
            this.toUrlType = toUrlType;
        }

        public String getAdPicUrl() {
            return adPicUrl;
        }

        public void setAdPicUrl(String adPicUrl) {
            this.adPicUrl = adPicUrl;
        }

        public String getToUrl() {
            return toUrl;
        }

        public void setToUrl(String toUrl) {
            this.toUrl = toUrl;
        }

        public String getInnerUrlId() {
            return innerUrlId;
        }

        public void setInnerUrlId(String innerUrlId) {
            this.innerUrlId = innerUrlId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }
    }
}
