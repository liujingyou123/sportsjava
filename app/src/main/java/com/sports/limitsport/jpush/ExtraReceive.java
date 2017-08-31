package com.sports.limitsport.jpush;

/**
 * Created by liuworkmac on 17/5/26.
 */

public class ExtraReceive {


    /**
     * bizId : 258
     * catalogType : 2
     * bizType : 0
     * url : www.hhh.mmo
     * actionType : 0
     * sendDateTime : 2017-05-26 09:06:00
     * messageId : 454
     */

    private String bizId;
    private String catalogType;
    private String bizType;
    private String url;
    private String actionType;
    private String sendDateTime;
    private String messageId;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(String catalogType) {
        this.catalogType = catalogType;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(String sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
