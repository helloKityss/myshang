package com.myshang.server.department.model;

public class Department {
	private Integer depid;
    private String departName;
	private Integer belongBusid;
	private Integer departNumber;//部门总人数
	private Integer clockNumber;//部门已打卡总人数
	public Integer getDepid() {
		return depid;
	}
	public void setDepid(Integer depid) {
		this.depid = depid;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public Integer getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(Integer belongBusid) {
		this.belongBusid = belongBusid;
	}
	public Integer getDepartNumber() {
		return departNumber;
	}
	public void setDepartNumber(Integer departNumber) {
		this.departNumber = departNumber;
	}
	public Integer getClockNumber() {
		return clockNumber;
	}
	public void setClockNumber(Integer clockNumber) {
		this.clockNumber = clockNumber;
	}
	@Override
	public String toString() {
		return "Department [depid=" + depid + ", departName=" + departName + ", belongBusid=" + belongBusid
				+ ", departNumber=" + departNumber + ", clockNumber=" + clockNumber + "]";
	}
}
