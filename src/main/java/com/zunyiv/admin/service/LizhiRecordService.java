package com.zunyiv.admin.service;

import com.zunyiv.admin.dao.LizhiRecordDao;
import com.zunyiv.admin.model.LizhiRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */
@Service
public class LizhiRecordService {
    @Autowired
    private LizhiRecordDao lizhiRecordDao;

    public List<LizhiRecord> query(int isPush) {
        return this.lizhiRecordDao.query(isPush);
    }

    public boolean push(int id) {
        return this.lizhiRecordDao.push(id);
    }


}
