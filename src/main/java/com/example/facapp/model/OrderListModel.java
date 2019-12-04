package com.example.facapp.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 姜涛
 * @create 2018-04-14 14:12
 * @desc 订单列表展示列模型
 **/
public class OrderListModel implements Serializable {
    private Long xh;
    private String orderId;
    private String customerName;
    private String flowName;
    private String orderTime;

    public Long getXh() {
        return xh;
    }

    public void setXh(Long xh) {
        this.xh = xh;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
