package com.zunyiv.admin.model.wx;


public class WeixinEntity
{
	private String accessToken;
	private String openId;

	/* openid */
	public String getOpenId()
	{
		return openId;
	}
	public void setOpenId(String openID)
	{
		this.openId = openID;
	}

	/* access token */
	public String getAccessToken()
	{
		return accessToken;
	}
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("WeiXinEntity [accessToken=");
		builder.append(accessToken);
		builder.append(", openId=");
		builder.append(openId);
		builder.append("]");
		return builder.toString();
	}
}
