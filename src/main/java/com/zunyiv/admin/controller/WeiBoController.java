package com.zunyiv.admin.controller;

import com.zunyiv.admin.service.WeiboAccountService;
import com.zunyiv.common.RequestUtils;
import com.zunyiv.common.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by luoshuhong on 2016/12/18.
 */
@Controller
@RequestMapping("/weibo")
public class WeiBoController {
    @Autowired
    private WeiboAccountService weiboAccountService;

    private static Log log = LogFactory.getLog("WeiBoController");
    public static String token = "";
    /**
     * 微博授权回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("success", false);
        String code = request.getParameter("code");
        log.info("weibo  code=" + code );
        if (StringUtils.isEmpty(code)) {
            return "weibo_redirect";
        }

        try {
            //获取token
            Oauth oauth = new Oauth();
            AccessToken accessToken = oauth.getAccessTokenByCode(code);
            token = accessToken.getAccessToken();
            log.info("weibo  token=" + token );

            //获取uid
            String uid = "";
            Account account = new Account(token);
            JSONObject uidJson = account.getUid();
            uid = uidJson.getString("uid");
            log.info("weibo  uid=" + uid);

            Users user = new Users(token);
            User userInfo = user.showUserById(uid);

            //保存用户信息
            if (null == weiboAccountService.queryByNickName(userInfo.getScreenName()) ) {
                weiboAccountService.add(userInfo.getScreenName(), uid, token);
            } else {
                weiboAccountService.updateTokenByNickName(userInfo.getScreenName(), token);
            }

            model.addAttribute("success", true);
            return "weibo_redirect";

            //获取最新微博
//            Timeline timeLint = new Timeline(token);
//            StatusWapper stateWapper = timeLint.getUserTimelineByUid(uid);
//            log.info("微博  uid=" + stateWapper.getStatuses() );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "weibo_redirect";
    }

}
