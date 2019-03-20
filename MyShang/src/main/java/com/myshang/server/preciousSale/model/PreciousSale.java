package com.myshang.server.preciousSale.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PreciousSale {
	private Integer salid;
    private Integer preciousId;
	private String preciousName;
	private BigDecimal preciousPrice;
    private Integer saleCount;
    private Integer saleRoomId;
    private Timestamp saleTime;
    private String preciousImage;
    private BigDecimal sumMoney;
    private String formatTime;
    private BigDecimal totalMoney;
    private Integer belongBusid;
    private String roomName;
	public Integer getSalid() {
		return salid;
	}
	public void setSalid(Integer salid) {
		this.salid = salid;
	}
	public Integer getPreciousId() {
		return preciousId;
	}
	public void setPreciousId(Integer preciousId) {
		this.preciousId = preciousId;
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
	public Integer getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}
	public Integer getSaleRoomId() {
		return saleRoomId;
	}
	public void setSaleRoomId(Integer saleRoomId) {
		this.saleRoomId = saleRoomId;
	}
	public Timestamp getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(Timestamp saleTime) {
		this.saleTime = saleTime;
	}
	public String getPreciousImage() {
		return preciousImage;
	}
	public void setPreciousImage(String preciousImage) {
		this.preciousImage = preciousImage;
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
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(Integer belongBusid) {
		this.belongBusid = belongBusid;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	@Override
	public String toString() {
		return "PreciousSale [salid=" + salid + ", preciousId=" + preciousId + ", preciousName=" + preciousName
				+ ", preciousPrice=" + preciousPrice + ", saleCount=" + saleCount + ", saleRoomId=" + saleRoomId
				+ ", saleTime=" + saleTime + ", preciousImage=" + preciousImage + ", sumMoney=" + sumMoney
				+ ", formatTime=" + formatTime + ", totalMoney=" + totalMoney + ", belongBusid=" + belongBusid
				+ ", roomName=" + roomName + "]";
	}
}
