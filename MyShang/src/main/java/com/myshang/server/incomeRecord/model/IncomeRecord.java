package com.myshang.server.incomeRecord.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class IncomeRecord {
	private Integer incid;
    private Integer userId;
	private BigDecimal incomeMoney;
	private Timestamp incomeTime;
	private Integer rewardId;
	private BigDecimal sumMoney;
	private String formatTime;
	public Integer getIncid() {
		return incid;
	}
	public void setIncid(Integer incid) {
		this.incid = incid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getIncomeMoney() {
		return incomeMoney;
	}
	public void setIncomeMoney(BigDecimal incomeMoney) {
		this.incomeMoney = incomeMoney;
	}
	public Timestamp getIncomeTime() {
		return incomeTime;
	}
	public void setIncomeTime(Timestamp incomeTime) {
		this.incomeTime = incomeTime;
	}
	public Integer getRewardId() {
		return rewardId;
	}
	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}
	public BigDecimal getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(BigDecimal sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}
	@Override
	public String toString() {
		return "IncomeRecord [incid=" + incid + ", userId=" + userId + ", incomeMoney=" + incomeMoney + ", incomeTime="
				+ incomeTime + ", rewardId=" + rewardId + ", sumMoney=" + sumMoney + ", formatTime=" + formatTime + "]";
	}
}
