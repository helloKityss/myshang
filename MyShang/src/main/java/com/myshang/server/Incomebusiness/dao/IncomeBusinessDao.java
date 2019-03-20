package com.myshang.server.Incomebusiness.dao;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.myshang.server.Incomebusiness.model.IncomeBusiness;
import com.myshang.server.incomeRecord.model.IncomeRecord;

@Repository
public interface IncomeBusinessDao {
	/**
	 * 新增商户收入记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public void saveIncome(IncomeBusiness incomebusiness);
	/**
	 * 查询商户收入记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param 
	 * @return
	 */
	public HashMap<String,Object> selectIncome(int busid);
}
