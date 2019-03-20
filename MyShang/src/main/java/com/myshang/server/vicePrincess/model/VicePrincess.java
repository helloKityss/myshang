package com.myshang.server.vicePrincess.model;

import java.sql.Timestamp;

public class VicePrincess {

private int 	binid;//	int	绑定ID，主键，ID自增
private int 	viceManagerId	;//int	副理ID
private int 	bindPrincessId;//	int	绑定的公主ID
private Timestamp 	bindTime	;//string	绑定时间
public int getBinid() {
	return binid;
}
public void setBinid(int binid) {
	this.binid = binid;
}
public int getViceManagerId() {
	return viceManagerId;
}
public void setViceManagerId(int viceManagerId) {
	this.viceManagerId = viceManagerId;
}
public int getBindPrincessId() {
	return bindPrincessId;
}
public void setBindPrincessId(int bindPrincessId) {
	this.bindPrincessId = bindPrincessId;
}
public Timestamp getBindTime() {
	return bindTime;
}
public void setBindTime(Timestamp bindTime) {
	this.bindTime = bindTime;
}

	
}
