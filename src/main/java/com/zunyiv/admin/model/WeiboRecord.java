package com.zunyiv.admin.model;

import com.zunyiv.common.DateUtils;

import java.util.Date;

/**
 * 微博博文记录
 * tb_weibo_record
 * Created by luoshuhong on 2016/12/20.
 */
public class WeiboRecord {
//    `id` int(11) NOT NULL AUTO_INCREMENT,
//    `userId` int(11) DEFAULT NULL,
//    `weiboId` varchar(30) DEFAULT '' COMMENT '微博id。对应json中id字段',
//            `content` varchar(2048) DEFAULT '' COMMENT '微博内容',
//            `url` varchar(255) DEFAULT '''''' COMMENT '微博链接',
//            `repostsCount` int(10) DEFAULT '0' COMMENT '转发',
//            `commentsCount` int(10) DEFAULT '0' COMMENT '评论',
//            `likeCount` int(10) DEFAULT '0' COMMENT '点赞',
//            `source` varchar(255) DEFAULT '' COMMENT '微博来源（小尾巴）',
//            `retweetedStatus` int(2) DEFAULT '0' COMMENT '0：直发  1：转发',
//            `createDate` date DEFAULT NULL,

    private int userId;         //微博发送人id
    private String userName = "";    //微博发送人名字（展示用）

    private int id;    //记录数据库id

    private String weiboId; //微博id(新浪)
    private String content;  //微博内容
    private String url;      //微博URL
    private int repostsCount; //转发
    private int commentsCount;//评论
    private int likeCount;      //点赞
    private String source;   //来源（小尾巴）
    private int retweetedStatus; // '0：直发  1：转发',
    private Date createDate;   //发送时间
    private String createDateStr; //发送时间（页面展示）

    public String getCreateDateStr() {
        if (null != createDate) {
            return DateUtils.date2Str(createDate);
        }

        return "";
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRepostsCount() {
        return repostsCount;
    }

    public void setRepostsCount(int repostsCount) {
        this.repostsCount = repostsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getRetweetedStatus() {
        return retweetedStatus;
    }

    public void setRetweetedStatus(int retweetedStatus) {
        this.retweetedStatus = retweetedStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
