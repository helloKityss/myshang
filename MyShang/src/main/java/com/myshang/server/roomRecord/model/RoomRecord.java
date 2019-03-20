package com.myshang.server.roomRecord.model;

import java.sql.Timestamp;

public class RoomRecord {
	private Integer openid;
    private Integer openRoomUid;
    private Timestamp opentime;
	public Integer getOpenid() {
		return openid;
	}
	public void setOpenid(Integer openid) {
		this.openid = openid;
	}
	public Integer getOpenRoomUid() {
		return openRoomUid;
	}
	public void setOpenRoomUid(Integer openRoomUid) {
		this.openRoomUid = openRoomUid;
	}
	public Timestamp getOpentime() {
		return opentime;
	}
	public void setOpentime(Timestamp opentime) {
		this.opentime = opentime;
	}
}
