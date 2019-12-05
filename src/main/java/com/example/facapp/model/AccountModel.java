package com.example.facapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "account")
public class AccountModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xh;
    private String cjmc;
    private String username;
    private String password;
    private String cjqx;
    private String wybz;
    private String lxfs;

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
