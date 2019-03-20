package com.myshang.server.bankcard.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface BankcardService {
	/**
	 * 绑定卡
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData addBankcard(Map<String, String> param);
	/**
	 * 绑定卡
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getBankcard(Map<String, String> param);
}
