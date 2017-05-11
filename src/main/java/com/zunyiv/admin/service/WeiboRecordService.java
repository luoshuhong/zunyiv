package com.zunyiv.admin.service;

import com.zunyiv.admin.dao.WeiboRecordDao;
import com.zunyiv.admin.model.WeiboRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
     * @return
     */
    public List<WeiboRecord> query(String keyWord) {
        return this.weiboRecordDao.query(keyWord);
    }

    /**
     *
     * @param sDate
     * @param eDate
     * @param keyWord
     * @param reposts
     * @param comments
     * @param likes
     * @return
     */
    public List<WeiboRecord> query(String sDate, String eDate, String keyWord,
                                   int reposts, int comments, int likes, String tail ) {
        return this.weiboRecordDao.query(sDate, eDate, keyWord, reposts, comments, likes, tail);
    }

    /**
     * 统计小尾巴发微博数量
     * @param sDate
     * @param eDate
     * @param type 1:发博数 2：转发数 3：评论数  4：点赞数
     * @return
     */
    public List<WeiboRecord> stat(String sDate, String eDate, int type) {
        return this.weiboRecordDao.stat(sDate, eDate, type);
    }

    /**
     * 查询微博小尾巴
     * @return
     */
    public Set<String> queryWeiBoTail() {
        return this.weiboRecordDao.queryWeiBoTail();
    }
}

