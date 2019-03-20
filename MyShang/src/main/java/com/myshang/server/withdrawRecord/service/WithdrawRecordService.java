package com.myshang.server.withdrawRecord.service;

import java.util.Map;

import com.myshang.server.common.JsonData;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface WithdrawRecordService {
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData addWithdrawRecord(Map<String, String> param);
	/**
	 * 生成提现excel表
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData createExcel(Map<String, String> param);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getWithdrawRecord(Map<String, String> param);
	/**
	 * 企业付款到零钱
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData transfers(Map<String, String> param);
	/**
	 * 企业付款到银行卡
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData paybank(Map<String, String> param) throws Exception;
}
