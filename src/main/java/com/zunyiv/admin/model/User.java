package com.zunyiv.admin.model;

/**
 * Created by Administrator on 2016/12/2.
 */
public class User {
    private Integer id;
    private String openid;
    private String nickName;
    private String realName;
    private String phone;
    private String birthday;
    private String inputTime;
    private int role;         //0：粉丝  1：管理员  2：超级管理员
    private String password;
    private int professional; //0：学生 1：工作
    private String avator;

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

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
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

    public String getBirthday() {
        return birthday;
    }

    public String getInputTime() {
        return inputTime;
    }

    public int getRole() {
        return role;
    }
}
