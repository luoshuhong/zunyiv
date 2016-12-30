package com.zunyiv.admin.service;

import com.zunyiv.admin.dao.WeiboAccountDao;
import com.zunyiv.admin.dao.WeiboRecordDao;
import com.zunyiv.admin.model.WeiboRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weibo4j.Timeline;
import weibo4j.http.HttpClient;
import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.util.WeiboConfig;

import java.util.List;

/**
 * Created by luoshuhong on 2016/12/21.
 */
@Service
public class WeiboService {
    private static Log log = LogFactory.getLog("WeiboService");

    private static String weiboNickName = "遵义同城会";
    @Autowired
    private WeiboRecordDao weiboRecordDao;
    @Autowired
    private WeiboAccountDao weiboAccountDao;


    /**
     * 定时任务同步微博记录
     */
    public void syncWeiboRecord() {
        //0:token 1:uuid
        String[] accountInfo = weiboAccountDao.queryByNickName(weiboNickName);
        if (null == accountInfo) {
            log.info("[syncWeiboRecord] accountInfo is null。 accountInfo:" + accountInfo);
            return;
        }
        try {
            Timeline timeLint = new Timeline(accountInfo[0]);
            StatusWapper stateWapper = timeLint.getUserTimelineByUid(accountInfo[1]);
            for (Status weiStatus : stateWapper.getStatuses()) {
                System.out.println(weiStatus);
                if (null == weiboRecordDao.queryByWeiboId(weiStatus.getId())) {
                    weiboRecordDao.add(weiStatus);
                }
            }
        } catch (Exception e) {
            log.info("[syncWeiboRecord] exceptoin. error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 更新微博记录
     */
    public void updateWeiboRecord() {
        String[] accountInfo = weiboAccountDao.queryByNickName(weiboNickName);
        if (null == accountInfo) {
            log.info("[updateWeiboRecord] accountInfo is null。 accountInfo:" + accountInfo);
            return;
        }
        List<WeiboRecord> list =  weiboRecordDao.queryUpdateWeiRecord(7);
        if (null == list) {
            log.info("[updateWeiboRecord] there is no record need to update:");
            return;
        }
        try {
            Timeline timeLint = new Timeline(accountInfo[0]);
            for (WeiboRecord weiboRecord : list) {
                Status status = timeLint.showStatus(weiboRecord.getWeiboId());
                if (null == status) {
                    continue;
                }
                weiboRecordDao.update(status);
                Thread.sleep(60 * 1000);
            }
        } catch (Exception e) {
            log.info("[syncWeiboRecord] exceptoin. error:" + e.getMessage());
            e.printStackTrace();
        }
    }

}
