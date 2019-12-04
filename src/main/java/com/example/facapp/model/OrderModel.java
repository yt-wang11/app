package com.example.facapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 姜涛
 * @create 2018-04-14 13:36
 * @desc 订单表model
 **/
@Entity
@Table(name = "order_form")
public class OrderModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xh;//订单编号
    private Long cjbh;//厂家编号
    private Long khbh;//客户编号
    private String ddh;//订单号
    private String bzjb;//步骤级别
    private Timestamp ddsj;//订单时间

    public Long getXh() {
        return xh;
    }

    public void setXh(Long xh) {
        this.xh = xh;
    }

    public Long getCjbh() {
        return cjbh;
    }

    public void setCjbh(Long cjbh) {
        this.cjbh = cjbh;
    }

    public Long getKhbh() {
        return khbh;
    }

    public void setKhbh(Long khbh) {
        this.khbh = khbh;
    }

    public String getDdh() {
        return ddh;
    }

    public void setDdh(String ddh) {
        this.ddh = ddh;
    }

    public String getBzjb() {
        return bzjb;
    }

    public void setBzjb(String bzjb) {
        this.bzjb = bzjb;
    }

    public Timestamp getDdsj() {
        return ddsj;
    }

    public void setDdsj(Timestamp ddsj) {
        this.ddsj = ddsj;
    }
}
