package com.myshang.server.reward.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 打赏记录实体 表名:reward_record
 * 
 * @author HL 2018.9.13.15:23
 */
public class Reward {
	private int rewid;// 打赏ID，主键，ID自增
	private BigDecimal money;// 打赏总金额
	private int roomId;// 房间ID
	private int employeeId;// 员工用户ID
	private BigDecimal employeeMoney; // 员工赏金
	private int mamiId;// 副理用户ID
	private BigDecimal mamiMoney;// 副理赏金
	private int belongBusid; // 商户用户ID
	private int bossId;// 总经理ID
	private BigDecimal bossMoney; // 总经理赏金
	private int platformId = 0; // 红科ID，固定为0
	private BigDecimal platformMoney; // 红科赏金
	private Timestamp rewardTime; // 打赏时间，精确到秒
	private String customOpenid; // 客人的openid
	private String customUnionid;// 客人的unionid
	private int internalId; // 内推人ID
	private BigDecimal internalMoney;// 内推人赏金
	private int extendId; // 外推人ID
	private BigDecimal extendMoney;// 外推人赏金
	private int rewardId;
	public int getRewid() {
		return rewid;
	}
	public void setRewid(int rewid) {
		this.rewid = rewid;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public BigDecimal getEmployeeMoney() {
		return employeeMoney;
	}
	public void setEmployeeMoney(BigDecimal employeeMoney) {
		this.employeeMoney = employeeMoney;
	}
	public int getMamiId() {
		return mamiId;
	}
	public void setMamiId(int mamiId) {
		this.mamiId = mamiId;
	}
	public BigDecimal getMamiMoney() {
		return mamiMoney;
	}
	public void setMamiMoney(BigDecimal mamiMoney) {
		this.mamiMoney = mamiMoney;
	}
	public int getBelongBusid() {
		return belongBusid;
	}
	public void setBelongBusid(int belongBusid) {
		this.belongBusid = belongBusid;
	}
	public int getBossId() {
		return bossId;
	}
	public void setBossId(int bossId) {
		this.bossId = bossId;
	}
	public BigDecimal getBossMoney() {
		return bossMoney;
	}
	public void setBossMoney(BigDecimal bossMoney) {
		this.bossMoney = bossMoney;
	}
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public BigDecimal getPlatformMoney() {
		return platformMoney;
	}
	public void setPlatformMoney(BigDecimal platformMoney) {
		this.platformMoney = platformMoney;
	}
	public Timestamp getRewardTime() {
		return rewardTime;
	}
	public void setRewardTime(Timestamp rewardTime) {
		this.rewardTime = rewardTime;
	}
	public String getCustomOpenid() {
		return customOpenid;
	}
	public void setCustomOpenid(String customOpenid) {
		this.customOpenid = customOpenid;
	}
	public String getCustomUnionid() {
		return customUnionid;
	}
	public void setCustomUnionid(String customUnionid) {
		this.customUnionid = customUnionid;
	}
	public int getInternalId() {
		return internalId;
	}
	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}
	public BigDecimal getInternalMoney() {
		return internalMoney;
	}
	public void setInternalMoney(BigDecimal internalMoney) {
		this.internalMoney = internalMoney;
	}
	public int getExtendId() {
		return extendId;
	}
	public void setExtendId(int extendId) {
		this.extendId = extendId;
	}
	public BigDecimal getExtendMoney() {
		return extendMoney;
	}
	public void setExtendMoney(BigDecimal extendMoney) {
		this.extendMoney = extendMoney;
	}
	public int getRewardId() {
		return rewardId;
	}
	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}
	@Override
	public String toString() {
		return "Reward [rewid=" + rewid + ", money=" + money + ", roomId=" + roomId + ", employeeId=" + employeeId
				+ ", employeeMoney=" + employeeMoney + ", mamiId=" + mamiId + ", mamiMoney=" + mamiMoney
				+ ", belongBusid=" + belongBusid + ", bossId=" + bossId + ", bossMoney=" + bossMoney + ", platformId="
				+ platformId + ", platformMoney=" + platformMoney + ", rewardTime=" + rewardTime + ", customOpenid="
				+ customOpenid + ", customUnionid=" + customUnionid + ", internalId=" + internalId + ", internalMoney="
				+ internalMoney + ", extendId=" + extendId + ", extendMoney=" + extendMoney + ", rewardId=" + rewardId
				+ "]";
	}
}
