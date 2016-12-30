package com.zunyiv.admin;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luoshuhong on 2016/12/19.
 */
public class JsoupTest {

    public static void main(String[] args)  throws  Exception {
        Map<String, String> map = new HashMap<>();
        //map.put请根据自己的微博cookie得到
        map.put("wvr","6");
        map.put("un","luoshuhong.jian@foxmail.com");
        map.put("_s_tentry","www.baidu.com");
        map.put("_ga","GA1.2.662503793.1445606680");
        map.put("__gads","ID=f48cc9c9bf02a9c4:T=1445606676:S=ALNI_MZ7Kf_OBAdLjs-cY_ILTa03s1cRuQ");
        map.put("YF-V5-G0","2da76c0c227d473404dd0efbaccd41ac");
        map.put("YF-Ugrow-G0","8751d9166f7676afdce9885c6d31cd61");
        map.put("YF-Page-G0","046bedba5b296357210631460a5bf1d2");
        map.put("UOR","www.baidu.com");
        map.put("ULV","1482155519092:61:11:3:1639635811260.991.1482155519052:1482128013477");
        map.put("SUHB","00x4pDEhTsfbxV");
        map.put("SUBP","0033WrSXqPxfM725Ws9jqgMF55529P9D9W5NIfGXTpFdY39.mcj_Hq7L5JpX5o2p5NHD95QES05Reoq4SozpWs4DqcjwKPiki--4i-8WiKnci--fiK.7iKyhi--fi-82i-2c");
        map.put("SUB","_2A251U4XFDeTxGeRJ61oT9ifKyT2IHXVWKPANrDV8PUJbmtANLXjTkW9OZ3JxfNpjdMt28Mw4Q1bJMTd_wA..");
        map.put("SSOLoginState","1482154564");
        map.put("SINAGLOBAL","4130269426901.012.1470056483871");
        map.put("SCF","AuZ673VWtfi2CQJF1NdOn2t0Vh1itioh_l2Wu3a__bDXXizJdQgP8sBR9y6ZQ4pfS47g0A0IGP3_UC-6NFaoKs0.");
        map.put("Apache","1639635811260.991.1482155519052");
        map.put("ALF","1513690564");


        Connection.Response res = Jsoup.connect("http://weibo.com/zunyiv")
                .cookies(map).method(Connection.Method.GET).execute();
        String s = res.body();
//        System.out.println(s);
        String[] ss = s.split("<script>FM.view");
        List<String> list = new ArrayList<>();
        for (String x : ss) {
            if (x.contains("\"html\":\"") && x.contains("WB_feed_detail clearfix")) {
                String value = getHtml(x);
                list.add(value);
                System.out.println(value);
                System.out.println();
            }
        }

//        Document document = Jsoup.connect("http://weibo.com/zunyiv").cookies(map).get();
//        System.out.print(document.body());
//        Elements elements = document.getElementsByClass("WB_feed_detail clearfix");
//        if (null == elements || 0 == elements.size()) {
//            System.out.print("elements is null" );
//            return;
//        }
//        Element element = elements.get(0);
//        System.out.print(element.html());


    }

    public static String getHtml(String s) {
        String content = s.split("\"html\":\"")[1]
                .replaceAll("(\\\\t|\\\\n)", "").replaceAll("\\\\\"", "\"")
                .replaceAll("\\\\/", "/");
        content = content.substring(0,
                content.length() <= 13 ? content.length()
                        : content.length() - 13);
        return Native2AsciiUtils.ascii2Native(content);
    }


}
