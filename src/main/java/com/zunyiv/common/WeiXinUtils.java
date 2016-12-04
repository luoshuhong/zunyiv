package com.zunyiv.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信接口调用工具类
 * @author Luoshuhong
 * @Company  
 * 2015年11月11日
 */
public class WeiXinUtils {
//	static String APPID = "wx7da410e7b5d045fe";// 微信公众号
//	static String SECRET = "296133f3ae1c0fb26daff3596014ec6c";// 应用密钥
	static String APPID = PropertyUtils.getProperty("config.properties", "app.id");// 微信公众号
	static String SECRET = PropertyUtils.getProperty("config.properties", "app.sercet");// 应用密钥
	
	//获取token地址
	static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
	private static Log log = LogFactory.getLog(WeiXinUtils.class);
	/**
	 * 永久二维码（请勿滥用生成，单个公众号，永久二维码有个数限制）
	 * @param channelCode
	 * @param token 微信accessToken
	 * @return
	 * @throws Exception
	 */
	public static String permanentCode(String token, String channelCode)   {
		try {
			if (StringUtils.isEmpty(token)) {
				log.info("method=permanentCode, token is empty.");
				token = JSONObject.parseObject(HttpUtils.doHttpGet(TOKEN_URL)).getString("access_token");
			}
			String codeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
			//永久二维码
			String json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"" + channelCode + "\"}}}";
			String result = HttpUtils.post(codeUrl, json);
			JSONObject resultJob = JSONObject.parseObject(result);
			if (null == resultJob) {
				return "";
			}
			return resultJob.getString("url");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 生成临时二维码
	 * @param channelCode  渠道编码
	 * @param token 微信accessToken
	 * @return 二维码地址
	 */
	public static String tempCode(String token, int channelCode) throws Exception {
		if (StringUtils.isEmpty(token)) {
			log.info("method=permanentCode, token is empty.");
			token = JSONObject.parseObject(HttpUtils.doHttpGet(TOKEN_URL)).getString("access_token");
		}
		String codeUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
		//临时二维码
		String json = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+ channelCode+"}}}";
		String result = HttpUtils.post(codeUrl, json);
		JSONObject resultJob = JSONObject.parseObject(result);
		if (null == resultJob) {
			return "";
		}
		return resultJob.getString("url");
	}
	
	/**
	 * 获取用户增减数据
	 * @param date  日期
	 * @param token 微信accessToken
	 * @return int[0]:新增用户  int[1]:取消关注用户  int[2]:净增关注用户
	 */
	public static Integer[] getUserSummary(String token, String date) throws Exception {
		try {
			if (StringUtils.isEmpty(token)) {
				log.info("method=permanentCode, token is empty.");
				token = JSONObject.parseObject(HttpUtils.doHttpGet(TOKEN_URL)).getString("access_token");
//				return new Integer[]{0,0,0};
			}
			String url = "https://api.weixin.qq.com/datacube/getusersummary?access_token=" + token;
			String json = "{\"begin_date\": \"" + date + "\", \"end_date\": \"" + date + "\"}";
			JSONObject resultJob = JSONObject.parseObject(HttpUtils.post(url, json));
			JSONArray jarr = resultJob.getJSONArray("list");
			
			int increase = 0, cancel = 0;
			for (int i = 0; i < jarr.size(); i++) {
				JSONObject job = jarr.getJSONObject(i);
				cancel += Integer.valueOf(job.get("cancel_user").toString());
				increase += Integer.valueOf(job.get("new_user").toString());
			}
			return new Integer[]{increase,cancel,increase - cancel};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Integer[]{0,0,0};
	}
	
	/**
	 * 获取累计用户数据
	 * @param date  渠道编码
	 * @param token 微信accessToken
	 * @return 二维码地址
	 */
	public static int  getUserCumulate(String token, String date)  {
		try {
			if (StringUtils.isEmpty(token)) {
				log.info("method=permanentCode, token is empty.");
				token = JSONObject.parseObject(HttpUtils.doHttpGet(TOKEN_URL)).getString("access_token");
//				return 0;
			}
			String totalUrl = "https://api.weixin.qq.com/datacube/getusercumulate?access_token=" + token;
			String json = "{\"begin_date\": \"" + date + "\", \"end_date\": \"" + date + "\"}";
			JSONObject resultJob = JSONObject.parseObject(HttpUtils.post(totalUrl, json));
			JSONArray jarr = resultJob.getJSONArray("list");
			int result = 0;
			for (int i = 0; i < jarr.size(); i++) {
				JSONObject job = jarr.getJSONObject(i);
				result += Integer.valueOf(job.get("cumulate_user").toString());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
}
