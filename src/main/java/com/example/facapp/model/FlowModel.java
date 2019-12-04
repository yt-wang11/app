package com.example.facapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 姜涛
 * @create 2018-04-14 13:11
 * @desc 生产流程flow表对应model
 **/
@Entity
@Table(name = "flow")
public class FlowModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xh;//流程编号
    private Long cjbh;//厂家编号
    private String bzjb;//步骤级别
    private String bzmc;//步骤名称
    private String bzts;//步骤提示

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

    public String getBzjb() {
        return bzjb;
    }

    public void setBzjb(String bzjb) {
        this.bzjb = bzjb;
    }

    public String getBzmc() {
        return bzmc;
    }

    public void setBzmc(String bzmc) {
        this.bzmc = bzmc;
    }

    public String getBzts() {
        return bzts;
    }

    public void setBzts(String bzts) {
        this.bzts = bzts;
    }
}
