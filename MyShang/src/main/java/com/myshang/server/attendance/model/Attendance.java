package com.myshang.server.attendance.model;

import java.sql.Timestamp;

import com.myshang.server.user.model.User;

public class Attendance {
	private Integer attid;
    private Integer userId;
	private Integer attendType;
    private Timestamp attendTime;
	private Integer belongBusid;
	private Integer belongDepid;
	private String departName;
	private String goWorkTime;
	private String offTime;
	private String formatTime;
	public Integer getAttid() {
		return attid;
	}
	public void setAttid(Integer attid) {
		this.attid = attid;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAttendType() {
		return attendType;
	}
	public void setAttendType(Integer attendType) {
		this.attendType = attendType;
	}
	public Timestamp getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(Timestamp attendTime) {
		this.attendTime = attendTime;
	}
	public Integer getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(Integer belongBusid) {
		this.belongBusid = belongBusid;
	}
	public Integer getBelongDepid() {
		return belongDepid;
	}
	public void setBelongDepid(Integer belongDepid) {
		this.belongDepid = belongDepid;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public String getGoWorkTime() {
		return goWorkTime;
	}
	public void setGoWorkTime(String goWorkTime) {
		this.goWorkTime = goWorkTime;
	}
	public String getOffTime() {
		return offTime;
	}
	public void setOffTime(String offTime) {
		this.offTime = offTime;
	}
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}
	@Override
	public String toString() {
		return "Attendance [attid=" + attid + ", userId=" + userId + ", attendType=" + attendType + ", attendTime="
				+ attendTime + ", belongBusid=" + belongBusid + ", belongDepid=" + belongDepid + ", departName="
				+ departName + ", goWorkTime=" + goWorkTime + ", offTime=" + offTime + ", formatTime=" + formatTime
				+ "]";
	}
}
