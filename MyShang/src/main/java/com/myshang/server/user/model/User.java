package com.myshang.server.user.model;

import java.sql.Timestamp;

public class User {
	private Integer uid;
    private String userName;
    private String phoneNumber;
    private Integer belongBusid;
    private Integer belongDepid;
    private Integer belongRolid;
    private Integer gender;
    private String avatarUrl;
    private String province;
    private String country;
    private String createTime;
	private String goWorkTime;
	private String offTime;
	private String roleName;
	private String departName;
	private Integer attendanceCount;
	private transient String passWord;
	private Integer hasRegister;
	private Integer isOnline;
	private String birthday;
	private String openId;
	private String unionId;
	private Integer height;
	private String registrationCode;
	private String speciality;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(Integer belongBusid) {
		this.belongBusid = belongBusid;
	}
	public Integer getBelongDepid() {
		return belongDepid;
	}
	public void setBelongDepid(Integer belongDepid) {
		this.belongDepid = belongDepid;
	}
	public Integer getBelongRolid() {
		return belongRolid;
	}
	public void setBelongRolid(Integer belongRolid) {
		this.belongRolid = belongRolid;
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
	public String getGoWorkTime() {
		return goWorkTime;
	}
	public void setGoWorkTime(String goWorkTime) {
		this.goWorkTime = goWorkTime;
	}
	public String getOffTime() {
		return offTime;
	}
	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public Integer getAttendanceCount() {
		return attendanceCount;
	}
	public void setAttendanceCount(Integer attendanceCount) {
		this.attendanceCount = attendanceCount;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getHasRegister() {
		return hasRegister;
	}
	public void setHasRegister(Integer hasRegister) {
		this.hasRegister = hasRegister;
	}
	public Integer getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getRegistrationCode() {
		return registrationCode;
	}
	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
}
