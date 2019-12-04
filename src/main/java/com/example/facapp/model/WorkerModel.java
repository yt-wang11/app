package com.example.facapp.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author 姜涛
 * @create 2018-04-14 13:52
 * @desc 厂家对应工人表
 **/
@Entity
@Table(name = "worker")
public class WorkerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xh;//订单编号
    private Long cjbh;//厂家编号
    private String grxm;//工人姓名
    private String lxfs;//联系方式
    private Double yzje;//预支金额
    private Timestamp yzsj;//预支时间

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

    public String getGrxm() {
        return grxm;
    }

    public void setGrxm(String grxm) {
        this.grxm = grxm;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public Double getYzje() {
        return yzje;
    }

    public void setYzje(Double yzje) {
        this.yzje = yzje;
    }

    public Timestamp getYzsj() {
        return yzsj;
    }

    public void setYzsj(Timestamp yzsj) {
        this.yzsj = yzsj;
    }
}
