package com.zunyiv.admin;

import com.zunyiv.common.MD5Util;
import org.apache.commons.logging.Log;
import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.http.HttpClient;
import weibo4j.model.*;
import weibo4j.util.BareBonesBrowserLaunch;
import weibo4j.util.WeiboConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/12/9.
 */
public class WeiBoTest {
    public static void main(String [] args) throws Exception {
//        Oauth oauth = new Oauth();
//        System.out.println(oauth.authorize("code"));
//
//        BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
//        System.out.println(oauth.authorize("code"));
//        System.out.print("Hit enter when it's done.[Enter]:");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String code = br.readLine();
//        try{
//            System.out.println(oauth.getAccessTokenByCode(code));
//        } catch (WeiboException e) {
//            if(401 == e.getStatusCode()){
//            }else{
//                e.printStackTrace();
//            }
//        }

        Oauth oauth = new Oauth();
        AccessToken accessToken = oauth.getAccessTokenByCode("a10e797cadc2989a6a5d348ae87f4d72");
        String token = accessToken.getAccessToken();
        System.out.println("weibo  token=" + token );

//        String access_token = "2.00byXr_CpgotND3359807b10FVFYkD";
//        String uid = "2125778041";

//        StatusWapper stateWapper =  Status
//                .constructWapperStatus(new HttpClient().get(
//                        WeiboConfig.getValue("baseURL")
//                                + "statuses/user_timeline.json",
//                        new PostParameter[] {
//                                new PostParameter("uid", uid),
//                                new PostParameter("count", 100),
//                                new PostParameter("trim_user", 1),
//                                new PostParameter("page", 1) },
//                       access_token));
//        for (Status weiStatus : stateWapper.getStatuses()) {
//            System.out.println(weiStatus);
//        }

        //查询单条微博
//        Status status = new Status(new HttpClient().get(WeiboConfig.getValue("baseURL")
//                        + "statuses/show.json",
//                new PostParameter[] { new PostParameter("id", "4055240749312999") },
//                access_token));
//        System.out.println(status);

        System.out.println(MD5Util.md5("123456"));
    }
}
