package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/8/3.
 */

public class MessageResponse extends BaseResponse{


    /**
     * messageId : aecc11f68f7747c69b083b9f3d1ead92
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String messageId;

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }
    }
}
