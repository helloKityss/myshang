package com.myshang.server.bankcard.model;


public class Bankcard {
	private Integer banid;
    private Integer userId;
	private String userName;
	private String cardNumber;
	private String bankName;
	private String bankCode;
	public Integer getBanid() {
		return banid;
	}
	public void setBanid(Integer banid) {
		this.banid = banid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	@Override
	public String toString() {
		return "Bankcard [banid=" + banid + ", userId=" + userId + ", userName=" + userName + ", cardNumber="
				+ cardNumber + ", bankName=" + bankName + ", bankCode=" + bankCode + "]";
	}
}
