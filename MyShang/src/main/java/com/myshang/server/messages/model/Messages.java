package com.myshang.server.messages.model;

import java.sql.Timestamp;

import com.myshang.server.user.model.User;

public class Messages {
	private Integer mesid;
	private Integer issueUserId;
    private Integer messageType;
    private String messageContent;
    private String labelSet;
    private Integer messageState;
    private Timestamp createTime;
    private String roomName;
    private Integer complainId;
    private Integer businessId;
    private String roleName;
	private String formatTime;
    private String avatarUrl;
    private String userName;
	public Integer getMesid() {
		return mesid;
	}
	public void setMesid(Integer mesid) {
		this.mesid = mesid;
	}
	public Integer getIssueUserId() {
		return issueUserId;
	}
	public void setIssueUserId(Integer issueUserId) {
		this.issueUserId = issueUserId;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getLabelSet() {
		return labelSet;
	}
	public void setLabelSet(String labelSet) {
		this.labelSet = labelSet;
	}
	public Integer getMessageState() {
		return messageState;
	}
	public void setMessageState(Integer messageState) {
		this.messageState = messageState;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getComplainId() {
		return complainId;
	}
	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getFormatTime() {
		return formatTime;
	}
	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Messages [mesid=" + mesid + ", issueUserId=" + issueUserId + ", messageType=" + messageType
				+ ", messageContent=" + messageContent + ", labelSet=" + labelSet + ", messageState=" + messageState
				+ ", createTime=" + createTime + ", roomName=" + roomName + ", complainId=" + complainId
				+ ", businessId=" + businessId + ", roleName=" + roleName + ", formatTime=" + formatTime
				+ ", avatarUrl=" + avatarUrl + ", userName=" + userName + "]";
	}
}
