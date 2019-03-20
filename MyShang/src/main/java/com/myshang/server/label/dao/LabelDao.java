package com.myshang.server.label.dao;

import java.util.List;

import com.myshang.server.label.model.Label;

public interface LabelDao {

	/**
	 * 返回标签列表
	 * @return
	 */
	public List<Label> getLabelList();
	/**
	 * 返回标签列表
	 * @return
	 */
	public List<Label> getLabelRewardList();
	/**
	 * 返回标签列表
	 * @return
	 */
	public List<Label> getLabelEvaluateList();
	/**
	 * 根据标签id返回对应标签
	 * @param labid
	 * @return
	 */
	public String getLabelForId(int labid);
	/**
	 * 新增标签
	 * @param labid
	 * @return
	 */
	public void addLabel(Label label);
	/**
	 * 新增标签
	 * @param labid
	 * @return
	 */
	public void updateLabel(Label label);
	/**
	 * 新增标签
	 * @param labid
	 * @return
	 */
	public void deleteLabel(int busid);
}
