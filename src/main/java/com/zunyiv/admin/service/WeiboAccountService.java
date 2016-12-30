package com.zunyiv.admin.service;

import com.zunyiv.admin.dao.WeiboAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class WeiboAccountService {
    @Autowired
    public WeiboAccountDao weiboAccountDao;

    /**
     *
     * @param nickName
     * @param uuid
     * @param token
     * @return
     */
    public boolean add(String nickName, String uuid, String token) {
        return weiboAccountDao.add(nickName, uuid, token);
    }


    /**
     * 更新token
     * @param uuid
     * @param token
     * @return
     */
    public boolean updateTokenByUuid(String uuid, String token) {
       return weiboAccountDao.updateTokenByUuid(uuid, token);
    }

    /**
     * 更新token
     * @param nickName
     * @param token
     * @return
     */
    public boolean updateTokenByNickName(String nickName, String token) {
        return weiboAccountDao.updateTokenByNickName(nickName, token);
    }

    /**
     *
     * @param nickName
     * @return
     */
    public String[] queryByNickName(String nickName) {
       return weiboAccountDao.queryByNickName(nickName);
    }


}
