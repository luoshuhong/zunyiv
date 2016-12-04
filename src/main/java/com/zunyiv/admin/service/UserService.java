package com.zunyiv.admin.service;

import com.zunyiv.admin.dao.UserDao;
import com.zunyiv.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class UserService {
    @Autowired
    public UserDao userDao;

    /**
     * 添加用户
     * @param user 新用户记录
     * @return
     */
    public boolean addUser(User user) {
        return this.userDao.addUser(user);
    }


    /**
     * 微信设置更新用户信息
     * @param user
     * @return
     */
    public boolean updateUserFormWX(User user) {
       return this.userDao.updateUserFormWX(user);
    }

    /**
     * 更新密码
     * @param newpwd 新密码
     * @param phone 电话
     * @return
     */
    public boolean updateUserPwd(String newpwd, String phone) {
        return this.updateUserPwd(newpwd, phone);
    }


    /**
     * 查询所有用户记录
     * @return
     */
    public List<User> query() {
        return userDao.query();
    }

    /**
     * 根据手机号查询用户记录
     * @param phone	手机号
     * @return User对象  不存在为null
     */
    public User query(String phone) {
       return this.userDao.query(phone);
    }


}
