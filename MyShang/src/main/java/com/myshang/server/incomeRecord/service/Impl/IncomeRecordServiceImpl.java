package com.myshang.server.incomeRecord.service.Impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.account.model.Account;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.incomeRecord.dao.IncomeRecordDao;
import com.myshang.server.incomeRecord.model.IncomeRecord;
import com.myshang.server.incomeRecord.service.IncomeRecordService;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.withdrawRecord.dao.WithdrawRecordDao;
import com.myshang.server.withdrawRecord.model.WithdrawRecord;
@Service
@Transactional
public class IncomeRecordServiceImpl implements IncomeRecordService{
	@Autowired
	private IncomeRecordDao incomeRecordSDao;
	@Autowired
	private WithdrawRecordDao withdrawRecordDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private IncomeRecordDao incomeRecordDao;
	private static final Logger LOGGER = Logger.getLogger(IncomeRecordServiceImpl.class);
	/**
	 * 查询收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getincomeRecord(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int userId=Integer.valueOf(param.get("userId"));
		String querytime = param.get("querytime");
		String startTime="";
		String endTime="";
		List<IncomeRecord> IncomeRecordList=new ArrayList<IncomeRecord>();
		try {
			if(querytime.equals("三个月内")){
				IncomeRecordList=incomeRecordSDao.getMonthincomeRecord(userId,3);
			}else if(querytime.equals("半年内")){
				IncomeRecordList=incomeRecordSDao.getMonthincomeRecord(userId,6);
			}else if(querytime.equals("null")){
				IncomeRecordList=incomeRecordSDao.getMonthincomeRecord(userId,0);
			}else{
				String[] split = querytime.split("~");//以逗号分割
				for(int i=0;i<split.length;i++){
					if(StringUtils.isEmpty(split[i])){
						continue;
					}
				    startTime=split[0];
				    endTime=split[1];
				}
				IncomeRecordList=incomeRecordSDao.getincomeRecord(userId,startTime,endTime);
			}
			BigDecimal IncomesSunmoney=incomeRecordSDao.getSumIncomesMoney(userId);
			Map map=new HashMap();
			map.put("IncomeRecordList", IncomeRecordList);
			map.put("IncomesSunmoney", IncomesSunmoney);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	@Override
	public int addIncomeInfo(List<IncomeRecord> incomeList) throws Exception {
		return incomeRecordDao.addIncomeInfo(incomeList);
	}
	/**
	 * 查询收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getPraise(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		try {
			List<HashMap<String, Object>> incomeList=incomeRecordSDao.getPraise(belongBusid);
			int sumCount=incomeRecordSDao.getPraiseCount(belongBusid);
			List<HashMap<String, Object>> incomeInformationList=incomeRecordSDao.getPraiseInformation(belongBusid);
			Map map=new HashMap();
			map.put("incomeListSize", incomeList.size());
			map.put("sumCount", sumCount);
			map.put("incomeInformationList", incomeInformationList);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getPraiseSum(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		try {
			List<HashMap<String, Object>> incomeList=incomeRecordSDao.getSumInformation(belongBusid);
			jsonData.setData(incomeList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData saveIncome(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		try {
			List<HashMap<String, Object>> incomeList=incomeRecordSDao.getSumInformation(belongBusid);
			jsonData.setData(incomeList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
