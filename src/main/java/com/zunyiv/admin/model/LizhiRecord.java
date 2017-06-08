package com.zunyiv.admin.model;

import com.zunyiv.common.Constants;
import com.zunyiv.common.DateUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lsh on 2017/5/13.
 * 荔枝记录
 */
public class LizhiRecord {
    private int id;         //
    private Date releaseTime; //资源发布时间

    private String title;     //标题
    public String webUrl;    //对于网页地址
    private String dataUrl;   //对于资源地址
    private String author;    //作者
    private int isPush;       //公号是否已经推送


    private String releaseTimeStr; //发布时间 页面展示

    public LizhiRecord(){

    }

    public LizhiRecord(int id, Date releaseTime, String title, String webUrl, String dataUrl, String author, int isPush) {
        this.id = id;
        this.releaseTime = releaseTime;
        this.title = title;
        this.webUrl = webUrl;
        this.dataUrl = dataUrl;
        this.author = author;
        this.isPush = isPush;
    }

    public static LizhiRecord wrapFromHtml(Element item) {
        String string = item.toString();
        String url = string.substring(string.indexOf("href=\"/") + "href=\"/".length(), string.indexOf("\" title="));
        //data url
        String dataurl = string.substring(string.indexOf("data-url=\"") + "data-url=\"".length(), string.indexOf("\" data-id"));
        //name
        String name = string.substring(string.indexOf("data-radio-name=\"") + "data-radio-name=\"".length(), string.indexOf("\" data-title"));
        // title and time
        Element titleandtime = item.getElementsByTag("div").first();
        String title = titleandtime.getElementsByTag("p").get(0).html();
        String date = titleandtime.getElementsByTag("p").get(1).html();
        String time = date.substring(0, date.indexOf("&nbsp"));

        LizhiRecord record = new LizhiRecord();
        record.setAuthor(name);
        record.setWebUrl(url);
        record.setDataUrl(dataurl);
        record.setReleaseTime(DateUtils.strToSysDate(time));
        record.setTitle(title);

        return record;
    }

    public static List<LizhiRecord> wrapFromHtml(Document doc) {
        List<LizhiRecord> list = new ArrayList<>();
        Elements elements = doc.getElementsByClass("audioList fontYaHei js-audio-list");
        Elements items = elements.get(0).getElementsByTag("a"); // 获取一列
        for (int i = 0; i < items.size(); i++) {
            Element item = items.get(i);
            LizhiRecord record = wrapFromHtml(item);
            list.add(record);
        }
        return list;
    }

    public String getReleaseTimeStr() {
        if (null != releaseTime) {
            return DateUtils.date2Str(releaseTime);
        }

        return "";
    }

    public void setReleaseTimeStr(String releaseTimeStr) {
        this.releaseTimeStr = releaseTimeStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUrl() {
        return Constants.LI_ZHI_HOST + webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsPush() {
        return isPush;
    }

    public void setIsPush(int isPush) {
        this.isPush = isPush;
    }

    @Override
    public String toString() {
        return "LizhiRecord{" +
                "id=" + id +
                ", releaseTime=" + releaseTime +
                ", title='" + title + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", dataUrl='" + dataUrl + '\'' +
                ", author='" + author + '\'' +
                ", isPush=" + isPush +
                '}';
    }
}
