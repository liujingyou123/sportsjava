package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/7/27.
 */

public class TicketListResponse extends BaseResponse{

    /**
     * activityId : 2
     * createTime : 1500543846000
     * creater : null
     * descContent : 2122
     * id : 2
     * isFree : 0
     * modifier : null
     * modifyTime : null
     * money : 1300
     * name : 尊享票
     * sequence : null
     * soldNumber : 100
     * status : 0
     * userPurchase : 5
     * version : 0
     */

    private List<TicketList> data;

    public List<TicketList> getData() {
        return data;
    }

    public void setData(List<TicketList> data) {
        this.data = data;
    }

}
