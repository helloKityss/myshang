package com.myshang.server.login.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.bankcard.model.Bankcard;
import com.myshang.server.business.dao.BusinessDao;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.login.dao.LoginDao;
import com.myshang.server.login.model.Login;
import com.myshang.server.login.service.LoginService;
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private BusinessDao businessDao;
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	/**
	 * 登陆方法
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData login(Map<String, String> param) {
		String loginName=param.get("loginName");
		String passWord=param.get("passWord");
		JsonData jsonData = new JsonData();
		try {
			Login login=loginDao.login(loginName);
			if(login == null){
				jsonData.setCodeEnum(CodeEnum.USER_ERROR);
				return jsonData;
			}
			if(!passWord.equals(login.getPassword())){
				jsonData.setCodeEnum(CodeEnum.LOGIN_ERROR);
				return jsonData;
			}
			String businessByName=businessDao.getbusinessByName(login.getBelongBusid());
			jsonData.setData(login);
			jsonData.setList(businessByName);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("用户登陆失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
