package com.myshang.server.label.model;

import java.math.BigDecimal;

public class Label {

	private int labid;// int 标签ID，主键，ID自增
	private String labelName; // string 标签名
	private int labelType; // int 标签类型，1:投诉，等...
	private BigDecimal value;
	public int getLabid() {
		return labid;
	}
	public void setLabid(int labid) {
		this.labid = labid;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public int getLabelType() {
		return labelType;
	}
	public void setLabelType(int labelType) {
		this.labelType = labelType;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
