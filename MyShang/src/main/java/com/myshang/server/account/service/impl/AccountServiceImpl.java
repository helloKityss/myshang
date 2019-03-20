package com.myshang.server.account.service.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.account.model.Account;
import com.myshang.server.account.service.AccountService;
import com.myshang.server.business.dao.BusinessDao;
import com.myshang.server.business.service.impl.BusinessServiceImpl;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.dao.DepartmentDao;
import com.myshang.server.incomeRecord.dao.IncomeRecordDao;
import com.myshang.server.withdrawRecord.dao.WithdrawRecordDao;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	private static final Logger LOGGER = Logger.getLogger(AccountServiceImpl.class);
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private IncomeRecordDao incomeRecordDao;
	@Autowired
	private WithdrawRecordDao withdrawRecordDao;
	/*
	 * 查询所有商户
	 */
	@Override
	public JsonData getaccount(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			BigDecimal account=accountDao.getAccount(0);
			BigDecimal incomeRecord=incomeRecordDao.getSumMoney(0);
			BigDecimal withdrawRecord=withdrawRecordDao.getSumWithdraw(0);
			List<HashMap<String, Object>> Withdrawlist = withdrawRecordDao.getWithdrawRecord(0);
			BigDecimal Withdrawsunmoney=withdrawRecordDao.getSumWithdrawMoney(0);
			List<HashMap<String, Object>> Incomeslist =incomeRecordDao.getIncomes(0);
			BigDecimal Incomessunmoney=incomeRecordDao.getSumIncomesMoney(0);
			Map map=new HashMap();
			map.put("account", account);
			map.put("incomeRecord", incomeRecord);
			map.put("withdrawRecord", withdrawRecord);
			map.put("Withdrawlist", Withdrawlist);
			map.put("Withdrawsunmoney", Withdrawsunmoney);
			map.put("Incomeslist", Incomeslist);
			map.put("Incomessunmoney", Incomessunmoney);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询账户余额失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 查询用户余额
	 */
	@Override
	public JsonData getUseraccount(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		int userId=Integer.valueOf(param.get("userId"));
		try {
			BigDecimal accountmoney=accountDao.getAccount(userId);
			BigDecimal recordmoney=incomeRecordDao.getRecordMoney(userId);
			Map map=new HashMap();
			map.put("accountmoney", accountmoney);
			map.put("recordmoney", recordmoney);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询账户余额失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
