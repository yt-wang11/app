package com.example.facapp.model;

public class SearchResult {
    private String bzmc;
    private String bzts;
    private String bzjb;
    private int now = 0;

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

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }
}
