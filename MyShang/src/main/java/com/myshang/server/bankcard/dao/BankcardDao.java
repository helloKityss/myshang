package com.myshang.server.bankcard.dao;


import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.account.model.Account;
import com.myshang.server.attendance.model.Attendance;
import com.myshang.server.bankcard.model.Bankcard;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface BankcardDao {
	/**
	 * 绑定卡
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public void addBankcard(Bankcard bankcard);
	/**
	 * 查询是否绑定银行卡
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public List<Bankcard> getBankcard(int userId);
}
