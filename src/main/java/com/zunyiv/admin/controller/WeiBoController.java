package com.zunyiv.admin.controller;

import com.zunyiv.common.RequestUtils;
import com.zunyiv.common.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import weibo4j.Oauth;
import weibo4j.http.AccessToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by luoshuhong on 2016/12/18.
 */
@Controller
@RequestMapping("/weibo")
public class WeiBoController {
    private static Log log = LogFactory.getLog("WeiBoController");
    public static String token = "";
    /**
     * 微博授权回调
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/redirect")
    public String redirect(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        log.info("weibo  code=" + code );
        Oauth oauth = new Oauth();

        try {
            AccessToken accessToken = oauth.getAccessTokenByCode(code);
            token = accessToken.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(code)) {
            return RequestUtils.failReturn("error");
        }

        return RequestUtils.failReturn("error");
    }
}
