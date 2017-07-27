package com.sports.limitsport.model;

import java.io.Serializable;

/**
 * Created by liuworkmac on 17/7/27.
 */

public class SelectTicket implements Serializable{
    public String id;
    public int num;
    public String name;
    public String price; // 单价
    public String totalPrice; //总价
    public int maxNum;//最大数量
    public String des;//说明
}
