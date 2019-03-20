package com.myshang.server.account.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface AccountService {
	/**
	 * 添加打赏记录
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public JsonData getaccount(Map<String, String> param) throws Exception;
	/**
	 * 查询用户余额
	 * 
	 * @param reward
	 * @return 执行操作条数
	 */
	public JsonData getUseraccount(Map<String, String> param) throws Exception;
}
