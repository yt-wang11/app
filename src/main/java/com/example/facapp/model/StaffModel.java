package com.example.facapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "staff")
public class StaffModel implements Serializable {
    @Id
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Age")
    private Integer age;
    @Column(name = "Sex")
    private String sex;
    @Column(name = "Workdepartment")
    private String workdepartment;
    @Column(name = "Idcard")
    private String idcard;
    @Column(name = "Education")
    private String education;
    @Column(name = "Title")
    private String title;
    @Column(name = "Entrytime")
    private Timestamp entrytime;
    @Column(name = "Contractlife")
    private String contractlife;
    @Column(name = "Workchangerecord")
    private String workchangerecord;
    @Column(name = "Rewardsandpunishmentrecords")
    private String rewardsandpunishmentrecords;
    @Column(name = "Rankevaluationrecord")
    private String rankevaluationrecord;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkdepartment() {
        return workdepartment;
    }

    public void setWorkdepartment(String workdepartment) {
        this.workdepartment = workdepartment;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Timestamp entrytime) {
        this.entrytime = entrytime;
    }

    public String getContractlife() {
        return contractlife;
    }

    public void setContractlife(String contractlife) {
        this.contractlife = contractlife;
    }

    public String getWorkchangerecord() {
        return workchangerecord;
    }

    public void setWorkchangerecord(String workchangerecord) {
        this.workchangerecord = workchangerecord;
    }

    public String getRewardsandpunishmentrecords() {
        return rewardsandpunishmentrecords;
    }

    public void setRewardsandpunishmentrecords(String rewardsandpunishmentrecords) {
        this.rewardsandpunishmentrecords = rewardsandpunishmentrecords;
    }

    public String getRankevaluationrecord() {
        return rankevaluationrecord;
    }

    public void setRankevaluationrecord(String rankevaluationrecord) {
        this.rankevaluationrecord = rankevaluationrecord;
    }
}
