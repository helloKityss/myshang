package com.myshang.server.roomRuning.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myshang.server.user.model.User;

public class RoomRuning {

private int	runid	;//int	运营ID，主键，ID自增
private int	roomId	;//int	房间ID
private int	belongBusid;//	int	商户ID，默认值0
private int	roomState	;//int	1:使用中，0:空闲，2:预定，3:维护中
private int	openRoomUid	;//int	开房人ID
private String	bindPrincesses	;//string	绑定的公主ID集合，格式:"21,23,25"
private int	bindWaiter	;//int	绑定的服务员ID
private BigDecimal sumMoney;
private String money;
private String days;
private String roomName;
private HashMap<String,Object> waiter;
private List<HashMap<String,Object>> Princessess;
public int getRunid() {
	return runid;
}
public void setRunid(int runid) {
	this.runid = runid;
}
public int getRoomId() {
	return roomId;
}
public void setRoomId(int roomId) {
	this.roomId = roomId;
}
public int getBelongBusid() {
	return belongBusid;
}
public void setBelongBusid(int belongBusid) {
	this.belongBusid = belongBusid;
}
public int getRoomState() {
	return roomState;
}
public void setRoomState(int roomState) {
	this.roomState = roomState;
}
public int getOpenRoomUid() {
	return openRoomUid;
}
public void setOpenRoomUid(int openRoomUid) {
	this.openRoomUid = openRoomUid;
}
public String getBindPrincesses() {
	return bindPrincesses;
}
public void setBindPrincesses(String bindPrincesses) {
	this.bindPrincesses = bindPrincesses;
}
public int getBindWaiter() {
	return bindWaiter;
}
public void setBindWaiter(int bindWaiter) {
	this.bindWaiter = bindWaiter;
}
public BigDecimal getSumMoney() {
	return sumMoney;
}
public void setSumMoney(BigDecimal sumMoney) {
	this.sumMoney = sumMoney;
}
public String getDays() {
	return days;
}
public void setDays(String days) {
	this.days = days;
}
public String getRoomName() {
	return roomName;
}
public void setRoomName(String roomName) {
	this.roomName = roomName;
}
public HashMap<String, Object> getWaiter() {
	return waiter;
}
public void setWaiter(HashMap<String, Object> waiter) {
	this.waiter = waiter;
}
public List<HashMap<String, Object>> getPrincessess() {
	return Princessess;
}
public void setPrincessess(List<HashMap<String, Object>> princessess) {
	Princessess = princessess;
}
public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
}
@Override
public String toString() {
	return "RoomRuning [runid=" + runid + ", roomId=" + roomId + ", belongBusid=" + belongBusid + ", roomState="
			+ roomState + ", openRoomUid=" + openRoomUid + ", bindPrincesses=" + bindPrincesses + ", bindWaiter="
			+ bindWaiter + ", sumMoney=" + sumMoney + ", money=" + money + ", days=" + days + ", roomName=" + roomName
			+ ", waiter=" + waiter + ", Princessess=" + Princessess + "]";
}
}
