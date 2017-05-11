package com.zunyiv.admin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by luoshuhong on 2017/5/11.
 */
public class HtmlParse {
    public static void main(String[] args) throws Exception {
//        Document doc = Jsoup.parse(input, "UTF-8", "http://www.dangdang.com");
//        Document doc = Jsoup.connect("https://www.lizhi.fm/952737/").get();
        Document doc = Jsoup.connect("https://www.lizhi.fm/97205/").get();

        Elements elements = doc.getElementsByClass("audioList fontYaHei js-audio-list");
//        for (int i = 0; i < elements.size(); i++) {
//            Element element = elements.get(i);
//            System.out.println(element.html());
//            System.out.println(elements);
//        }
        Elements items = elements.get(0).getElementsByTag("a"); // 获取一列
        for (int i = 0; i < items.size(); i++) {
//            Element item = elements.get(i).getElementsByTag("a").first(); // 获取一列
            Element item = items.get(i);
//            System.out.println(item);

            //url
            String string = item.toString();
            String url = "https://www.lizhi.fm/" + string.substring(string.indexOf("href=\"/") + "href=\"/".length(), string.indexOf("\" title="));
            System.out.println("url=" + url);

            //data url
            String dataurl = string.substring(string.indexOf("data-url=\"") + "data-url=\"".length(), string.indexOf("\" data-id"));
            System.out.println("dataurl=" + dataurl);

            //name
            String name = string.substring(string.indexOf("data-radio-name=\"") + "data-radio-name=\"".length(), string.indexOf("\" data-title"));
            System.out.println("name=" + name);

            // title and time
            Element titleandtime = item.getElementsByTag("div").first();
            System.out.println("title:" + titleandtime.getElementsByTag("p").get(0).html());
            String date = titleandtime.getElementsByTag("p").get(1).html();
            System.out.println("time:" + date.substring(0, date.indexOf("&nbsp")));

            System.out.println();

        }


    }
}

