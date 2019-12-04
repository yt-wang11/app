package com.example.facapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;


public class StaffDto implements Serializable {
    private Integer Id;
    private String Name;
    private Integer Age;
    private String Sex;
    private String Workdepartment;
    private String Idcard;
    private String Education;
    private String Title;
    private String Entrytime;
    private String Contractlife;
    private String Workchangerecord;
    private String Rewardsandpunishmentrecords;
    private String Rankevaluationrecord;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getWorkdepartment() {
        return Workdepartment;
    }

    public void setWorkdepartment(String workdepartment) {
        Workdepartment = workdepartment;
    }

    public String getIdcard() {
        return Idcard;
    }

    public void setIdcard(String idcard) {
        Idcard = idcard;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getEntrytime() {
        return Entrytime;
    }

    public void setEntrytime(String entrytime) {
        Entrytime = entrytime;
    }

    public String getContractlife() {
        return Contractlife;
    }

    public void setContractlife(String contractlife) {
        Contractlife = contractlife;
    }

    public String getWorkchangerecord() {
        return Workchangerecord;
    }

    public void setWorkchangerecord(String workchangerecord) {
        Workchangerecord = workchangerecord;
    }

    public String getRewardsandpunishmentrecords() {
        return Rewardsandpunishmentrecords;
    }

    public void setRewardsandpunishmentrecords(String rewardsandpunishmentrecords) {
        Rewardsandpunishmentrecords = rewardsandpunishmentrecords;
    }

    public String getRankevaluationrecord() {
        return Rankevaluationrecord;
    }

    public void setRankevaluationrecord(String rankevaluationrecord) {
        Rankevaluationrecord = rankevaluationrecord;
    }
}
