package com.example.facapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author 姜涛
 * @create 2018-04-14 12:55
 * @desc 我们的客户列表
 **/
@Entity
@Table(name = "account")
public class AccountModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xh;//厂家编号
    private String cjmc;//厂家名称
    private String username;//客户名称
    private String password;//厂家密码
    private String cjqx;//厂家权限
    private String wybz;//唯一标志
    private String lxfs;//联系方式

    public Long getXh() {
        return xh;
    }

    public void setXh(Long xh) {
        this.xh = xh;
    }

    public String getCjmc() {
        return cjmc;
    }

    public void setCjmc(String cjmc) {
        this.cjmc = cjmc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCjqx() {
        return cjqx;
    }

    public void setCjqx(String cjqx) {
        this.cjqx = cjqx;
    }

    public String getWybz() {
        return wybz;
    }

    public void setWybz(String wybz) {
        this.wybz = wybz;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }
}
