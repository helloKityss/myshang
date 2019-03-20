package com.myshang.server.extenduser.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.account.model.Account;
import com.myshang.server.common.DateUtil;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.extenduser.dao.ExtenduserDao;
import com.myshang.server.extenduser.model.Extenduser;
import com.myshang.server.extenduser.service.ExtenduserService;
import com.myshang.server.role.model.Role;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;
@Service
@Transactional
public class ExtenduserServiceImpl implements ExtenduserService{
	private static final Logger LOGGER = Logger.getLogger(ExtenduserServiceImpl.class);
	@Autowired
	private ExtenduserDao extenduserDao;
	@Autowired
	private AccountDao accountDao;
	@Override
	public JsonData createExtendUser(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		try {
			int uid = Integer.valueOf(param.get("uid"));
			String userName = param.get("userName");
			Extenduser user = new Extenduser();
			user.setExtid(uid);
			user.setUserName(userName);
			user.setHasRegister(1);
			extenduserDao.createExtendUser(user);
			int Acount = accountDao.getAccountCount(uid);
			if (Acount < 1) {
				Account account = new Account();
				account.setUserId(uid);
				accountDao.addAccount(account);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("添加用户信息出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询用户基本信息
	 *
	 * @return
	 * @throws Exception
	 */
	public Extenduser getExtenduserByOpenId(String openid) throws Exception {
		List<Extenduser> rstrst = extenduserDao.getExtendlisMaps(openid);
		Extenduser userInfo = null;
		for (Extenduser user : rstrst) {
			String username = "";
			if (user.getUserName() != null && !user.getUserName().equals("")) {
				username = user.getUserName();
			}
			String mobile = "";
			if (user.getPhoneNumber() != null && !user.getPhoneNumber().equals("")) {
				mobile = user.getPhoneNumber();
			}
			String nikename = "";
			if (user.getNickName() != null && !user.getNickName().equals("")) {
				nikename = user.getNickName();
			}
			int sex = 0;
			if (user.getGender() != null && !user.getGender().equals("")) {
				sex = user.getGender();
			}
			int isregiste = user.getHasRegister();
			String createdatetime = user.getCreateTime();
			String imgurl = "";
			if (user.getAvatarUrl() != null && !user.getAvatarUrl().equals("")) {
				imgurl = user.getAvatarUrl();
			}
			int id = user.getExtid();

			userInfo = new Extenduser();
			userInfo.setExtid(id);
			userInfo.setUserName(username);
			userInfo.setPhoneNumber(mobile);
			userInfo.setNickName(nikename);
			userInfo.setGender(sex);
			userInfo.setCreateTime(createdatetime);
			userInfo.setAvatarUrl(imgurl);
			userInfo.setHasRegister(isregiste);
		}
		return userInfo;
	}

	/**
	 * 用户创建
	 */
	public int createExtendserRespId(Extenduser userInfo) throws Exception {
		try {
			userInfo.setCreateTime(DateUtil.DateToString(new Date(), DateUtil.DEFAULT_DATE_FORMAT2));
			extenduserDao.saveExtenduser(userInfo);
			return userInfo.getExtid();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 保存微信用户个人信息
	 *
	 * @param userInfo
	 * @throws Exception
	 */
	public int updateExtenduserByWeiXin(Extenduser user) throws Exception {
		return extenduserDao.updateExtenduserByWeiXin(user);
	}
	/**
	 * 保存从微信获得的用户电话号码
	 *
	 * @param phoneNum
	 * @throws Exception
	 */
	public int updateExtendMobileByWeiXin(Extenduser user) throws Exception {
		return extenduserDao.updateExtendMobileByWeiXin(user);
	}
}
