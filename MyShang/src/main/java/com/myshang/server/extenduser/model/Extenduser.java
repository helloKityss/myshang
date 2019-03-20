package com.myshang.server.extenduser.model;

import java.sql.Timestamp;

public class Extenduser {
	private int extid;
	private String userName;
	private String nickName;
	private String phoneNumber;
	private Integer hasRegister;
	private String openId;
	private String unionId;
	private Integer gender;
	private String avatarUrl;
	private String city;
	private String province;
	private String country;
    private String createTime;
    private String extendZone;
	public int getExtid() {
		return extid;
	}
	public void setExtid(int extid) {
		this.extid = extid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getHasRegister() {
		return hasRegister;
	}
	public void setHasRegister(Integer hasRegister) {
		this.hasRegister = hasRegister;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getExtendZone() {
		return extendZone;
	}
	public void setExtendZone(String extendZone) {
		this.extendZone = extendZone;
	}
}
