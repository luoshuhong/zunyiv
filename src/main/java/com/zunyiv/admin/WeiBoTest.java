package com.zunyiv.admin;

import org.apache.commons.logging.Log;
import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/12/9.
 */
public class WeiBoTest {
    public static void main(String [] args) throws WeiboException, IOException {
        Oauth oauth = new Oauth();
        System.out.println(oauth.authorize("code"));
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

    }
}
