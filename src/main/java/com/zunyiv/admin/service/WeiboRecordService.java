package com.zunyiv.admin.service;

import com.zunyiv.admin.dao.WeiboRecordDao;
import com.zunyiv.admin.model.WeiboRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luoshuhong on 2016/12/27.
 */
@Service
public class WeiboRecordService {
    @Autowired
    private WeiboRecordDao weiboRecordDao;

    /**
     * 根据发微博的时间查询
     * @param sDate
     * @param eDate
     * @return
     */
    public List<WeiboRecord> query(String sDate, String eDate) {
        return this.weiboRecordDao.query(sDate, eDate);
    }

    /**
     * 根据微博内容关键字查询
     * @param keyWord 关键字
     * @param eDate
     * @return
     */
    public List<WeiboRecord> query(String keyWord) {
        return this.weiboRecordDao.query(keyWord);
    }


}
