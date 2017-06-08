package com.zunyiv.admin.service;

import com.zunyiv.admin.dao.LizhiRecordDao;
import com.zunyiv.admin.model.LizhiRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lsh on 2017/5/13.
 * 处理荔枝fm的一些定时
 */
@Service
public class QuartzLizhiService {

    @Autowired
    private LizhiRecordDao lizhiRecordDao;
    private static Log log = LogFactory.getLog("QuartzLizhiService");

    public static String [] LI_ZHI_RESOURCE_URL =
            {
                    "https://www.lizhi.fm/97205/",  // 陈老师
                    "https://www.lizhi.fm/952737/",  // 千翻
                    "https://www.lizhi.fm/859730/",   // 瑶瑶
                    "https://www.lizhi.fm/859730/p/2.html"
            };

    /**
     * 抓取荔枝FM 数据
     */
    public void syncLizhiRecord() {
        log.info("[syncLizhiRecord] start!");

        for (String url : LI_ZHI_RESOURCE_URL) {
            try {
                Document doc = Jsoup.connect(url).get();
                List<LizhiRecord> list = LizhiRecord.wrapFromHtml(doc);

                for (LizhiRecord record : list) {
                    try {
                        // 是否存在
                        if (null == lizhiRecordDao.queryByWebUrl(record.webUrl)) {
                            lizhiRecordDao.add(record);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print("over ===============");
    }



}
