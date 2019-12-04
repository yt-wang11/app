package com.example.facapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 姜涛
 * @create 2018-04-14 12:22
 * @desc 账户表client的model
 **/
@Entity
@Table(name = "client")
public class ClientModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xh;//客户编号
    private Long cjbh;//厂家编号
    private String khmc;//客户名称
    private String khdz;//客户地址
    private Timestamp hzsj;//合作时间
    private String lxfs;//联系方式

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

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getKhdz() {
        return khdz;
    }

    public void setKhdz(String khdz) {
        this.khdz = khdz;
    }

    public Timestamp getHzsj() {
        return hzsj;
    }

    public void setHzsj(Timestamp hzsj) {
        this.hzsj = hzsj;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }
}
