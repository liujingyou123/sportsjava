package com.sports.limitsport.model;

import com.sports.limitsport.base.BaseResponse;

/**
 * Created by liuworkmac on 17/8/16.
 */

public class OrderDetailResponse extends BaseResponse {

    /**
     * address : 中国上海浦东新区杨高中路2010弄12号601
     * applicantList : [{"city":null,"coutry":null,"headPortrait":null,"id":3,"idCard":null,"introduction":null,"name":"晓晓","phone":"13687797324","province":null,"sex":null,"status":null,"tickets":null}]
     * coverUrl : http://ex-fans-tst.img-cn-hangzhou.aliyuncs.com/jxlx/merchantweb/2017/08/06/IMG_1501996162353_92924.jpg
     * createOrderTime : 2017-08-12 00:00:00
     * dateTime : 07月19日
     * id : 3
     * laveSecond : 555978
     * moneyNews : 999.00
     * name : 黄山3天2日旅行
     * number : 1
     * orderInfo : 534342343
     * orderNo : D43423233
     * orderStatus : 1
     * payTime : 2017-08-12 00:00:00
     * payType : alipay
     * receptMoney : 2000.00
     * signId : 2
     * startDate : 07月19日
     * startTime : 00:00
     * ticketsName : 标准票
     * totalMoney : 688.00
     * week : 4
     */

    private OrderDetail data;

    public OrderDetail getData() {
        return data;
    }

    public void setData(OrderDetail data) {
        this.data = data;
    }

}
