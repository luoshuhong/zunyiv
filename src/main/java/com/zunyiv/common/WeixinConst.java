package com.zunyiv.common;

public class WeixinConst {
//	public static final String APPID = PropertyUtils.getProperty("config", "appId");
//	public static final String APPSECRET = PropertyUtils.getProperty("config","secret");
//	
//	public static final String BASE= PropertyUtils.getProperty("config","base");
	
	public static final String APPID = PropertyUtils.getProperty(Constants.ENVIRONMENT_KEY,Constants.APP_BASE, "appId");
	public static final String APPSECRET = PropertyUtils.getProperty(Constants.ENVIRONMENT_KEY,Constants.APP_BASE, "secret");

	
    //session key
    public static final String USER_STATUS_SESSION_TOKEN = "user_status";
    public static final String USER_SESSION_TOKEN = "user_open_id";
    //认证地址
//    public static final String AUTHORIZEURL = "https://open.weixin.qq.com/connect/oauth2/authorize?&appid="+APPID+"&redirect_uri=#{redirectUrl}&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    public static final String AUTHORIZEURL = "https://open.weixin.qq.com/connect/oauth2/authorize?&appid="+APPID+"&redirect_uri=#{redirectUrl}&response_type=code&scope=snsapi_base&state=123#wechat_redirect";



}