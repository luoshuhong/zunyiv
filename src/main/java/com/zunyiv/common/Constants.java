package com.zunyiv.common;


public class Constants {
	public static final String ENVIRONMENT_KEY="CONFIG_HOME";
    public static final String APP_BASE = "zunyiv/config";
	
    public static final String ROOT_PATH_NEW = PropertyUtils.getProperty(Constants.ENVIRONMENT_KEY,Constants.APP_BASE, "root.path.new");
    public static final String MEMBER_CENTER = PropertyUtils.getProperty(Constants.ENVIRONMENT_KEY,Constants.APP_BASE, "mypage.url");
	public static final String ROOT_PATH_OLD = ROOT_PATH_NEW;
	public static final String APPID = PropertyUtils.getProperty(Constants.ENVIRONMENT_KEY,Constants.APP_BASE, "appId");
	public static final String SIGN_KEY = PropertyUtils.getProperty(Constants.ENVIRONMENT_KEY,Constants.APP_BASE, "sign_key");
	public static final String MESSAGE_URL = PropertyUtils.getProperty(Constants.ENVIRONMENT_KEY,Constants.APP_BASE, "message_url");

    //微信授权地址
    public static final String WX_OAUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID + "&redirect_uri=" + ROOT_PATH_NEW + "dntm_java/wxredirect?&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	//微信验证时使用token
	public static final String WX_TOKEN = PropertyUtils.getProperty(Constants.ENVIRONMENT_KEY,Constants.APP_BASE, "wx.token");

	//微信后台事件
	public static final String WX_SBUSCRIBE = "third_wx_subscribe";			//关注事件
	public static final String WX_UN_SBUSCRIBE = "third_wx_un_subscribe";	    //取关事件
	public static final String WX_TEXT = "third_wx_text";					    //发送文字事件
	public static final String WX_OTHER = "third_wx_other";			//其他事件

	// 荔枝 网站
	public static final String LI_ZHI_HOST = "https://www.lizhi.fm/";
}

