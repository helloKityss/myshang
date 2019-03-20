package com.myshang.server.preciousCategory.model;

import java.math.BigDecimal;

public class Precious {
	private Integer preid;
    private Integer preciousType;
	private String preciousName;
	private BigDecimal preciousPrice;
    private String preciousImage;
    private Integer belongBusid;
	public Integer getPreid() {
		return preid;
	}
	public void setPreid(Integer preid) {
		this.preid = preid;
	}
	public Integer getPreciousType() {
		return preciousType;
	}
	public void setPreciousType(Integer preciousType) {
		this.preciousType = preciousType;
	}
	public String getPreciousName() {
		return preciousName;
	}
	public void setPreciousName(String preciousName) {
		this.preciousName = preciousName;
	}
	public BigDecimal getPreciousPrice() {
		return preciousPrice;
	}
	public void setPreciousPrice(BigDecimal preciousPrice) {
		this.preciousPrice = preciousPrice;
	}
	public String getPreciousImage() {
		return preciousImage;
	}
	public void setPreciousImage(String preciousImage) {
		this.preciousImage = preciousImage;
	}
	public Integer getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(Integer belongBusid) {
		this.belongBusid = belongBusid;
	}
	@Override
	public String toString() {
		return "Precious [preid=" + preid + ", preciousType=" + preciousType + ", preciousName=" + preciousName
				+ ", preciousPrice=" + preciousPrice + ", preciousImage=" + preciousImage + ", belongBusid="
				+ belongBusid + "]";
	}
}
