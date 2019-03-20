package com.myshang.server.business.model;

import java.sql.Timestamp;

public class Business {
	private Integer busid;
	private String businessName;
	private String businessType;
	private String address;
	private String businessBoss;
	private String businessPhone;
	private String qrcodeUrl;
	private Timestamp createTime;
	private double latitude;//纬度
	private double longitude;//精度
	private Integer extendUserId;
	private String businessLogo;
	public Integer getBusid() {
		return busid;
	}
	public void setBusid(Integer busid) {
		this.busid = busid;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusinessBoss() {
		return businessBoss;
	}
	public void setBusinessBoss(String businessBoss) {
		this.businessBoss = businessBoss;
	}
	public String getBusinessPhone() {
		return businessPhone;
	}
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Integer getExtendUserId() {
		return extendUserId;
	}
	public void setExtendUserId(Integer extendUserId) {
		this.extendUserId = extendUserId;
	}
	public String getBusinessLogo() {
		return businessLogo;
	}
	public void setBusinessLogo(String businessLogo) {
		this.businessLogo = businessLogo;
	}
}
