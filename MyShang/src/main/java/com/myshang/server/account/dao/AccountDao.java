package com.myshang.server.account.dao;


import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.account.model.Account;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface AccountDao {
	/**
	 * 查询余额
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public BigDecimal getAccount(@Param("userId") int userId);
	/**
	 * 查询余额
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getAccountCount(@Param("userId") int userId);
	/**
	 * 用户添加数据
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void addAccount(Account account);
	/**
	 * 查询余额
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int UpdateAccount(@Param("userId") int userId,@Param("accountBalance") BigDecimal accountBalance);
	/**
	 * 查询总经理(商户)余额
	 * @param belongBusid
	 * @return
	 */
    public Account getAccountByGeneralManager(@Param("belongBusid") int belongBusid);
}
