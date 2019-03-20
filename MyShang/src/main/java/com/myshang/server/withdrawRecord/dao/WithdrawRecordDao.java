package com.myshang.server.withdrawRecord.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.role.model.Role;
import com.myshang.server.withdrawRecord.model.WithdrawRecord;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface WithdrawRecordDao {
	/**
	 * 根据日期查询提现记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<WithdrawRecord> getWithdraw(@Param("userId") int userId,@Param("startTime") String startTime,@Param("endTime") String endTime);
	/**
	 * 查询三个月内提现记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<WithdrawRecord> getMorthWithdraw(@Param("userId") int userId,@Param("Month") int Month);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void addWithdrawRecord(WithdrawRecord withdrawRecord);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<HashMap<String, Object>> queryExcel();
	/**
	 * 今日总收入
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public BigDecimal getSumWithdraw(@Param("userId") int userId);
	/**
	 * 提现
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<HashMap<String, Object>> getWithdrawRecord(int userId);
	/**
	 * 今日总收入
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public BigDecimal getSumWithdrawMoney(@Param("userId") int userId);
}
