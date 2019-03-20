package com.myshang.server.login.model;

public class Login {
	private Integer logid;
	private String loginName;
	private String password;
	private Integer loginType;
	private Integer belongBusid;
	public Integer getLogid() {
		return logid;
	}
	public void setLogid(Integer logid) {
		this.logid = logid;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getLoginType() {
		return loginType;
	}
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	public Integer getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(Integer belongBusid) {
		this.belongBusid = belongBusid;
	}
	@Override
	public String toString() {
		return "Login [logid=" + logid + ", loginName=" + loginName + ", password=" + password + ", loginType="
				+ loginType + ", belongBusid=" + belongBusid + "]";
	}
}
