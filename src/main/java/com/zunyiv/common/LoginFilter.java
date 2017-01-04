package com.zunyiv.common;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.zunyiv.admin.controller.LoginController;
import com.zunyiv.common.SessionUtils;

/**
 * <p>Description: 登录过滤器</p>
 * <p>Company:  </p>
 * @author luoshuhong
 * @date 2015-9-16 
 * @version 1.0
 */
public class LoginFilter implements Filter {
    
    private String[] excludeUrls ; 

    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String isLogin = (String) SessionUtils.getSession(httpRequest, SessionUtils.LONIG_FLAG);
        // 过滤URL
        if("true".equals(isLogin) || isExclusive(httpRequest.getRequestURL().toString())) {
            chain.doFilter(request, response);
            return;
        }
        
//        super.doFilter(request, response, chain);
//        //验证cookie是否自动登录
        boolean autoLogin = LoginController.readCookieAndLogon(httpRequest, httpResponse) ;

        // 没有登录，跳到 登录页
        if (!autoLogin && (StringUtils.isEmpty(isLogin) || !"true".equals(isLogin))) {
            httpResponse.sendRedirect(httpRequest.getContextPath()+ "/admin/login.jsp");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeUrls = new String[] { "/login.jsp", "/login"};
    }
    
    /**
     * <p>Title: isExclusive</p>
     * <p>Description: isExclusive</p>
     * @param url url
     * @return boolean
     */
    public boolean isExclusive(String url){
    	//这里只拦截/admin下的请求
    	if (url.contains("/admin/login.do")   || url.contains(".css") || url.contains("/admin/login.jsp")
    			|| url.contains(".png") || url.contains(".jpg") 
    			|| (url.contains(".js") && !url.contains(".jsp"))) {   
    		return true;
    	} else {
    		return false;
    	}
    	
    	
//    	if (url.contains("/admin") && !url.contains("/admin/login") 
//    			&& !url.contains("/admin/test.jsp") && !url.contains("/admin/channel/queryEffect")
//    			&& !url.contains("/admin/qrCode/getQrCode")) {   
//    		return false;
//    	} else {
//    		return true;
//    	}
    	
//        for(String exurl:excludeUrls){
//            if (exurl.equals(url) || url.contains(exurl)) {
//                return true;
//            }
//        }
//        return false;
    }

}
