package com.myshang.server.label.service;

import java.util.List;
import java.util.Map;

import com.myshang.server.common.JsonData;
import com.myshang.server.label.model.Label;
/**
 * 标签Service接口
 * @author HL
 *
 */

public interface LabelService {

	/**
	 * 返回标签列表
	 * @return
	 */
	public JsonData getLabelList();

	/**
	 * 新增标签列表
	 * @return
	 */
	public JsonData addLabel(Map<String, String> param);
	/**
	 * 新增标签列表
	 * @return
	 */
	public JsonData updateLabel(Map<String, String> param);
	/**
	 * 删除标签列表
	 * @return
	 */
	public JsonData deleteLabel(Map<String, String> param);
}
