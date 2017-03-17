package com.zunyiv.admin.model.wx;

public class UserEntity implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	private String openId;
	private String accessToken;
	private String avatar = "";
	private String nickname = "";
	private String nicknameUd = "";
	//关注与否  只在调用微信接口获取消息的时候有值 请注意
	private String subscribe;
	//处理时间
	private Integer processTime;
    private String gender = "0";
    private String province = "";
    private String city = "";
    private String country = "";

    
	public UserEntity() {
		super();
	}
	public UserEntity(String openId) {
		super();
		this.openId = openId;
	}
	/* openID */
	public String getOpenId()
	{
		return openId;
	}
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
	
	/* accessToken */
	public String getAccessToken()
	{
		return accessToken;
	}
	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}
	
	/* 头像 */
	public String getAvatar()
	{
		return avatar;
	}
	public void setAvatar(String avatar)
	{
		this.avatar = avatar;
	}
	
	/* 昵称 */
	public String getNickname() {
        if(nickname != null && (nickname.contains("\\x") || nickname.contains("\\X"))) {
            nickname = nickname.replace("\\x", "x").replace("\\X", "X");
        }
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

    public String getNicknameNoEmoji(){
//        return EmojiUtils.filterEmoji(this.getNickname());
    	return this.getNickname();
    }


	/* 是否关注 只在调用微信接口获取消息的时候有值 请注意 */
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	/* 处理时间 */
	public Integer getProcessTime() {
        if (processTime == null){
            return 0;
        }
		return processTime;
	}

	public void setProcessTime(Integer processTime) {
		this.processTime = processTime;
	}

    public String getGender() {

        return gender;
    }

    public UserEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }


    public String getProvince() {
        return province;
    }

    public UserEntity setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public UserEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getNicknameUd() {
    	return this.getNickname();
//        return UnicodeUtils.string2Unicode(this.getNickname());
    }

    public UserEntity setNicknameUd(String nicknameUd) {
        this.nicknameUd = nicknameUd;
        return this;
    }

    @Override
	public String toString() {
		return "UserEntity{" +
				"openId='" + openId + '\'' +
				", accessToken='" + accessToken + '\'' +
				", avatar='" + avatar + '\'' +
				", nickname='" + nickname + '\'' +
				", subscribe='" + subscribe + '\'' +
				'}';
	}
}
