package com.myshang.server.incomeRecord.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.incomeRecord.model.IncomeRecord;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface IncomeRecordDao {
	/**
	 * 查询一周内的收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<IncomeRecord> getincomeRecord(@Param("userId") int userId,@Param("startTime") String startTime,@Param("endTime") String endTime);
	/**
	 * 今日总收入
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public BigDecimal getRecordMoney(@Param("userId") int userId);
	/**
	 * 今日总收入
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public BigDecimal getSumMoney(@Param("userId") int userId);
	
	
	/**
	 * 添加多条收入记录
	 * @param incomeList
	 * @return 执行行数
	 */
	public int addIncomeInfo(List<IncomeRecord> incomeList);
	
	/**
	 * 添加一条收入记录
	 * @param income
	 * @return 执行行数
	 */
	public int addAnIncome(IncomeRecord income);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<HashMap<String, Object>> getIncomes(int userId);
	/**
	 * 今日总收入
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public BigDecimal getSumIncomesMoney(@Param("userId") int userId);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<HashMap<String, Object>> getPraise(int belongBusid);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getPraiseCount(int belongBusid);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<HashMap<String, Object>> getSumInformation(int belongBusid);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<HashMap<String, Object>> getPraiseInformation(int belongBusid);
	/**
	 * 查询三月(半年)内的收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<IncomeRecord> getMonthincomeRecord(@Param("userId") int userId,@Param("Month") int Month);
}
