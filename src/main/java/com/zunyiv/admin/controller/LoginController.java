package com.zunyiv.admin.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zunyiv.admin.model.User;
import com.zunyiv.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zunyiv.common.MD5Util;
import com.zunyiv.common.RequestUtils;
import com.zunyiv.common.SessionUtils;

@Controller
@RequestMapping("/admin")
public class LoginController {
	private static Log log = LogFactory.getLog("admin");
	//保存cookie时的cookieName
    private final static String cookieDomainName = "nlsitelsdjl";
    //加密cookie时的网站自定码
    private final static String webKey = "yishengpinan";
    //设置cookie有效期是两个星期，根据需要自定义
    private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;
    
    private static String pwd = "zunyiv";

	@Autowired
	private UserService userService;
    
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String autoLogin = request.getParameter("autoLogin");
		model.addAttribute("username", username);

		String isLogin = (String) SessionUtils.getSession(request, SessionUtils.LONIG_FLAG);
		if ("true".equals(isLogin)) {
			return "index";
		}

		log.info("method=login,userName=" + username + ",password=*****" );
		System.out.println("method=login,userName=" + username + ",password=*****" );
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			model.addAttribute("errMsg", "用户名或密码为空，请重试");
			return "login";
		}

//		if(username.equals("admin") && password.equals(pwd)){
//			return loginSuccess(request, response, autoLogin);
//		} else {
			User user = userService.query(username);
			if (null != user && user.getPassword().equals(MD5Util.md5(password))) {
				SessionUtils.setSession(request, SessionUtils.ROLE, user.getRole());
				SessionUtils.setSession(request, SessionUtils.USERNAME, StringUtils.isEmpty(user.getRealName()) ? user.getPhone() : user.getRealName());
				return loginSuccess(request, response, autoLogin);
			}
//		}
        log.info("method=login,用户名或密码错误" );
		model.addAttribute("errMsg", "用户名或密码错误，请重试");
		return "login";
	}

	/**
	 * 登陆成功
	 * @param request
	 * @param response
	 * @param autoLogin
     * @return
     */
	public String loginSuccess(HttpServletRequest request, HttpServletResponse response, String autoLogin) {
		SessionUtils.setSession(request, SessionUtils.LONIG_FLAG, "true");
		log.info("method=login,登陆成功" );
		if ("0".equals(autoLogin)) {
			saveCookie(response);   //放置cookie
		}
		return "index";
	}

	/**
	 * 放置cookie
	 * @param response
	 */
	public static void saveCookie(HttpServletResponse response) {
        //cookie的有效期
        long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
        //MD5加密用户详细信息
        String cookieValueWithMd5 = "";
		try {
			cookieValueWithMd5 = MD5Util.md5("admin" + ":" + pwd + ":" + validTime + ":" + webKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
        //将要被保存的完整的Cookie值
        String cookieValue = "admin" + ":" + validTime + ":" + cookieValueWithMd5;

        //开始保存Cookie
        Cookie cookie = new Cookie(cookieDomainName, cookieValue);
        //存两年(这个值应该大于或等于validTime)
        cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
        //cookie有效路径是网站根目录
        cookie.setPath("/");
        //向客户端写入
        response.addCookie(cookie);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static boolean readCookieAndLogon(HttpServletRequest request, HttpServletResponse response) {
		// 根据cookieName取cookieValue
		Cookie cookies[] = request.getCookies();
		String cookieValue = null;
		if (null == cookies) {
			return false;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (cookieDomainName.equals(cookies[i].getName())) {
				cookieValue = cookies[i].getValue();
				break;
			}
		}

		// 如果cookieValue为空,返回,
		if (StringUtils.isEmpty(cookieValue)) {
			return false;
		}

		// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆
		String cookieValues[] = cookieValue.split(":");
		if (cookieValues.length != 3) {
			return false;
		}

		// 判断是否在有效期内,过期就删除Cookie
		long validTimeInCookie = new Long(cookieValues[1]);
		if (validTimeInCookie < System.currentTimeMillis()) {
			clearCookie(response);
			return false;
		}
		// 取出cookie中的用户名,并到数据库中检查这个用户名,
		String username = cookieValues[0];
		// 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密
		if (StringUtils.isNoneEmpty(username)) {
			String md5ValueInCookie = cookieValues[2];
			String md5ValueFromUser = "";
			try {
				md5ValueFromUser = MD5Util.md5("admin" + ":" + pwd + ":" + validTimeInCookie + ":" + webKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (md5ValueFromUser.equals(md5ValueInCookie)) {
				SessionUtils.setSession(request, SessionUtils.LONIG_FLAG, "true");
				log.info("method=login,cookie 自动登陆成功");
				return true;
			}
			// 将结果与Cookie中的MD5码相比较,如果相同,写入Session,自动登陆成功,并继续用户请求
			return false;
		}

		return false;
	}

	 //用户注销时,清除Cookie,在需要时可随时调用 
     public static void clearCookie( HttpServletResponse response){
        Cookie cookie = new Cookie(cookieDomainName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
     }

	@RequestMapping("/index")
	public String index(){
		return "index.jsp";
	}

}
