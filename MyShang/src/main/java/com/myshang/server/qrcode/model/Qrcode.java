package com.myshang.server.qrcode.model;

import java.sql.Timestamp;

public class Qrcode {
	private int qrcid; // 二维码ID，主键，ID自增
	private String qrcodeUrl; // 二维码链接url，查询参数为qrcid
	private String qrcodeImg;// 二维码图片地址
	private int hasBind; // 0:未绑定，1:已绑定
	private int bindUserId;// 绑定的用户ID
	private int belongBusid; // 商户ID，默认值0
	private Timestamp bindTime;// 绑定时间
	private String userName;
	private String roleName;
	private String departName;
	public int getQrcid() {
		return qrcid;
	}
	public void setQrcid(int qrcid) {
		this.qrcid = qrcid;
	}
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	public String getQrcodeImg() {
		return qrcodeImg;
	}
	public void setQrcodeImg(String qrcodeImg) {
		this.qrcodeImg = qrcodeImg;
	}
	public int getHasBind() {
		return hasBind;
	}
	public void setHasBind(int hasBind) {
		this.hasBind = hasBind;
	}
	public int getBindUserId() {
		return bindUserId;
	}
	public void setBindUserId(int bindUserId) {
		this.bindUserId = bindUserId;
	}
	public int getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(int belongBusid) {
		this.belongBusid = belongBusid;
	}
	public Timestamp getBindTime() {
		return bindTime;
	}
	public void setBindTime(Timestamp bindTime) {
		this.bindTime = bindTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	@Override
	public String toString() {
		return "Qrcode [qrcid=" + qrcid + ", qrcodeUrl=" + qrcodeUrl + ", qrcodeImg=" + qrcodeImg + ", hasBind="
				+ hasBind + ", bindUserId=" + bindUserId + ", belongBusid=" + belongBusid + ", bindTime=" + bindTime
				+ ", userName=" + userName + ", roleName=" + roleName + ", departName=" + departName + "]";
	}
}
