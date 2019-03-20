package com.myshang.server.room.model;

public class Room {
	private Integer rooid;
    private String roomName;
	private Integer roomType;
	private Integer belongBusid;
	private String remark;
	public Integer getRooid() {
		return rooid;
	}
	public void setRooid(Integer rooid) {
		this.rooid = rooid;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getRoomType() {
		return roomType;
	}
	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}
	public Integer getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(Integer belongBusid) {
		this.belongBusid = belongBusid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Room [rooid=" + rooid + ", roomName=" + roomName + ", roomType=" + roomType + ", belongBusid="
				+ belongBusid + ", remark=" + remark + "]";
	}
}
