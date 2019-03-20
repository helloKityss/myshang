package com.myshang.server.Incomebusiness.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

public interface IncomeBusinessService {
	/**
	 * 新增商户收入记录
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData saveIncome(Map<String, String> param);
	/**
	 * 新增商户收入记录
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData selectIncome(Map<String, String> param);
}
