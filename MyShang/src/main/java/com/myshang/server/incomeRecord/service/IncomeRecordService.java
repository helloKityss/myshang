package com.myshang.server.incomeRecord.service;

import java.util.List;
import java.util.Map;

import com.myshang.server.common.JsonData;
import com.myshang.server.incomeRecord.model.IncomeRecord;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface IncomeRecordService {
	/**
	 * 查询收入信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getincomeRecord(Map<String, String> param);
	
	
	/**
	 * 添加收入记录
	 * @param incomeList
	 * @return 执行行数
	 */
	public int addIncomeInfo(List<IncomeRecord> incomeList) throws Exception; 
	/**
	 * 查询收入信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param 
	 * @return
	 */
	public JsonData getPraise(Map<String, String> param);
	/**
	 * 查询收入信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getPraiseSum(Map<String, String> param);
	/**
	 * 插入收入信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData saveIncome(Map<String, String> param);
	
}
