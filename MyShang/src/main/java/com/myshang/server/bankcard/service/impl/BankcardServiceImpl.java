package com.myshang.server.bankcard.service.impl;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.account.model.Account;
import com.myshang.server.attendance.model.Attendance;
import com.myshang.server.bankcard.dao.BankcardDao;
import com.myshang.server.bankcard.model.Bankcard;
import com.myshang.server.bankcard.service.BankcardService;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;
@Service
@Transactional
public class BankcardServiceImpl implements BankcardService {
	@Autowired
	private BankcardDao bankcardDao;
	@Autowired
	private AccountDao accountDao;
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	/**
	 * 绑定卡
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData addBankcard(Map<String, String> param) {
		int uid=Integer.valueOf(param.get("uid"));
		String userName=param.get("userName");
		String cardNumber=param.get("cardNumber");
		String bankName=param.get("bankName");
		String bankCode=param.get("bankCode");
		JsonData jsonData = new JsonData();
		try {
			Bankcard bankcard=new Bankcard();
			bankcard.setUserName(userName);
			bankcard.setBankCode(bankCode);
			bankcard.setBankName(bankName);
			bankcard.setCardNumber(cardNumber);
			bankcard.setUserId(uid);
			bankcardDao.addBankcard(bankcard);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("绑定卡失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询是否绑定银行卡
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getBankcard(Map<String, String> param) {
		int uid=Integer.valueOf(param.get("uid"));
		JsonData jsonData = new JsonData();
		try {
			List<Bankcard> bankcardList=bankcardDao.getBankcard(uid);
//			BigDecimal account=accountDao.getAccount(uid);
			Map map=new HashMap();
			map.put("bankcardList", bankcardList);
//			if(account != null){
//				map.put("account", account);
//			}else{
//				map.put("account", 0);
//			}
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询银行卡失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
