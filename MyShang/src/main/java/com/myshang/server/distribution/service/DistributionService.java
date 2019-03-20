package com.myshang.server.distribution.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

public interface DistributionService {
	/**
	 * 新增分成比例
	 * @return
	 */
	public JsonData savedistribution(Map<String, String> param);
	/**
	 * 新增分成比例
	 * @return
	 */
	public JsonData getdistribution(Map<String, String> param);
	/**
	 * 新增分成比例
	 * @return
	 */
	public JsonData deletedistribution(Map<String, String> param);
	/**
	 * 修改分成比例
	 * @return
	 */
	public JsonData updatedistribution(Map<String, String> param);
	/**
	 * 新增分成比例
	 * @return
	 */
	public JsonData getdistributionId(Map<String, String> param);
	/**
	 * 新增分成比例
	 * @return
	 */
	public JsonData getbusdistribution(Map<String, String> param);
	/**
	 * 修改分成比例
	 * @return
	 */
	public JsonData updatbusedistribution(Map<String, String> param);
}
