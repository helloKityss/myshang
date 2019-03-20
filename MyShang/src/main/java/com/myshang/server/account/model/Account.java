package com.myshang.server.account.model;

import java.math.BigDecimal;

public class Account {
	private Integer accid;
    private Integer userId;
	private BigDecimal accountBalance;
	public Integer getAccid() {
		return accid;
	}
	public void setAccid(Integer accid) {
		this.accid = accid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	@Override
	public String toString() {
		return "Account [accid=" + accid + ", userId=" + userId + ", accountBalance=" + accountBalance + "]";
	}
}
