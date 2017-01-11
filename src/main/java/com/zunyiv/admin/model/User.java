package com.zunyiv.admin.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/2.
 */
public class User {
    private Integer id;
    private String openid;
    private String nickName;
    private String realName;
    private String phone;
    private Date birthday;
    private Date inputTime;
    private int role;         //0：普通 1：管理员  2.超级管理员
    private String password;
    private int professional; //0：学生 1：工作
    private String avator;
    private String weiboTail; //微博小尾巴

    public User(String phone, int role, String pwd) {
        this.phone = phone;
        this.role = role;
        this.password = pwd;
    }
    public User(){

    }

    public String getWeiboTail() {
        return weiboTail;
    }

    public void setWeiboTail(String weiboTail) {
        this.weiboTail = weiboTail;
    }

    public int getProfessional() {
        return professional;
    }

    public void setProfessional(int professional) {
        this.professional = professional;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public void setRole(int role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getOpenid() {
        return openid;
    }

    public String getNickName() {
        return nickName;
    }

    public String getRealName() {
        return realName;
    }

    public String getPhone() {
        return phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public int getRole() {
        return role;
    }
}
