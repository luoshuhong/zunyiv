package com.zunyiv.common;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.zunyiv.admin.model.wx.UserEntity;
import com.zunyiv.admin.model.wx.WeixinEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;


public class WeiXinUtil  {
	private static Logger LOGGER = LoggerFactory.getLogger(WeiXinUtil.class);
	private static final String USERURL = "https://api.weixin.qq.com/sns/oauth2/access_token?";
	private static final String GRANTTYPE = "authorization_code";
	private static final String USERENTITYURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
	private static final String INITACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
	private static final String GETJSAPITICKETURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
	private static final String GETUSERINFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=#{accessToken}&openid=#{openId}&lang=zh_CN";

	/**
	 * 初始化一个AccessToken并获取
	 * @return
	 * 
	 * 作者：王辉
	 * 时间：2015-09-01 15:30
	 */
	public static String getInitAccessToken()
	{
		String rtn = null;
		try
		{
			URL urlGet = new URL(INITACCESSTOKEN + WeixinConst.APPID + "&secret=" + WeixinConst.APPSECRET);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			JSONObject json = JSONObject.parseObject(result);
			rtn = json.getString("access_token");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		LOGGER.info("getInitAccessToken() = " + rtn);
		return rtn;
	}


//	public static String getInitJsApiTicket(RedisUtils service) {
//		String rtn = null;
//		String url = "" ,result = "";
//		try {
//			String accessToken = service.getAccessToken();
//			url = GETJSAPITICKETURL + accessToken;
//			URL urlGet = new URL(url);
//			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
//			InputStream is = http.getInputStream();
//			int size = is.available();
//			byte[] jsonBytes = new byte[size];
//			is.read(jsonBytes);
//			result = new String(jsonBytes, "UTF-8");
//			JSONObject json = JSONObject.parseObject(result);
//			rtn = json.getString("ticket");
//		}
//		catch (Exception e) {
//			LOGGER.error("========传入: " + url + ",传出:" + result + "结果:" + e.getMessage());
//			e.printStackTrace();
//		}
//		return rtn;
//	}

	/**
	 * 根据用户code获取AccessToken和openId
	 * 
	 * @param code
	 * @return
	 * 
	 * 作者：王辉
	 * 时间：2015-09-04 02:00
	 */
	public static WeixinEntity getWeiXinEntityByCode(String code) {
		WeixinEntity weiXinEntity = null;
		try {
			URL urlGet = new URL(USERURL + "appid=" + WeixinConst.APPID + "&secret=" + WeixinConst.APPSECRET + "&code=" + code + "&grant_type=" + GRANTTYPE);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			JSONObject json = JSONObject.parseObject(result);
			String accessToken = json.getString("access_token");
			String openid = json.getString("openid");
			weiXinEntity = new WeixinEntity();
			weiXinEntity.setAccessToken(accessToken);
			weiXinEntity.setOpenId(openid);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return weiXinEntity;
	}

	/**
	 * 通过AccessToken和openId从微信服务器获取用户的的头像、昵称等信息并放入UserEntity类中
	 * 
	 * @param openid
	 * @param aToken
	 * @return
	 * 
	 * 作者；张值富
	 * 修改：王辉
	 * 时间：2015-09-04 01:25
	 */
	public static UserEntity getWeixinUserEntity(String openid, String aToken) {
		UserEntity user = new UserEntity();
		String url = "" , result = "";
		try {
			url = USERENTITYURL + aToken + "&openid=" + openid + "&lang=zh_CN";
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			result = new String(jsonBytes, "UTF-8");
			JSONObject json = JSONObject.parseObject(result);
			LOGGER.info("getUserEntity(String openid)==============" + result);
			user.setAccessToken(aToken);
			user.setOpenId(openid);
			user.setNickname(json.getString("nickname"));
			user.setAvatar(json.getString("headimgurl"));
			user.setGender(json.getString("sex"));
            user.setProvince(json.getString("province"));
            user.setCity(json.getString("city"));
            user.setCountry(json.getString("country"));
            user.setSubscribe(json.getString("subscribe"));
            LOGGER.info("========传入: " + url + ",传出:" + result);

		}
		catch (Exception e) {
			LOGGER.error("========传入: " + url + ",传出:" + result + "结果:" + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
	
//	public static String getJsApiTicket(RedisUtils service) {
//		return service.getJsApiTicketi();
//	}

	/**
	 * 通过openId获取关注者的UserInfo
	 * @return
	 */
//	public static UserEntity sgetUserInfo(String openId) {
//		UserEntity user = new UserEntity();
//		String url = "" ,result = "";
//		try {
//			String accessToken = service.getAccessToken();
//			url = GETUSERINFO.replace("#{accessToken}",accessToken).replace("#{openId}",openId);
//			URL urlGet = new URL(url);
//			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
//			InputStream is = http.getInputStream();
//			int size = is.available();
//			byte[] jsonBytes = new byte[size];
//			is.read(jsonBytes);
//			result = new String(jsonBytes, "UTF-8");
//			JSONObject json = JSONObject.parseObject(result);
//			user.setOpenId(openId);
//
//			user.setSubscribe(json.getString("subscribe"));
//			if (SubscribeType.ALREADY_SUBSCRIBE.equals(user.getSubscribe())) {
//				user.setNickname(json.getString("nickname"));
//				user.setAvatar(json.getString("headimgurl"));
//                user.setGender(json.getString("sex"));
//                user.setProvince(json.getString("province"));
//                user.setCity(json.getString("city"));
//                user.setCountry(json.getString("country"));
//			}
//		} catch (Exception e) {
//			LOGGER.error("========传入: " + url + ",传出:" + result + "结果:" + e.getMessage());
//			e.printStackTrace();
//		}
//		return user;
//	}


}
