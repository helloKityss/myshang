package com.myshang.server.withdrawRecord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class WithdrawRecord {
	private Integer witid;
    private Integer userId;
	private BigDecimal withdrawMoney;
	private Timestamp withdrawTime;
	private String formatTime;
	private Integer bankcardId;
	public Integer getWitid() {
		return witid;
	}
	public void setWitid(Integer witid) {
		this.witid = witid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getWithdrawMoney() {
		return withdrawMoney;
	}
	public void setWithdrawMoney(BigDecimal withdrawMoney) {
		this.withdrawMoney = withdrawMoney;
	}
	public Timestamp getWithdrawTime() {
		return withdrawTime;
	}
	public void setWithdrawTime(Timestamp withdrawTime) {
		this.withdrawTime = withdrawTime;
	}
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}
	public Integer getBankcardId() {
		return bankcardId;
	}
	public void setBankcardId(Integer bankcardId) {
		this.bankcardId = bankcardId;
	}
	@Override
	public String toString() {
		return "WithdrawRecord [witid=" + witid + ", userId=" + userId + ", withdrawMoney=" + withdrawMoney
				+ ", withdrawTime=" + withdrawTime + ", formatTime=" + formatTime + ", bankcardId=" + bankcardId + "]";
	}
}
