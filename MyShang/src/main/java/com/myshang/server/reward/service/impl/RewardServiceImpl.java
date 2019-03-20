package com.myshang.server.reward.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.account.model.Account;
import com.myshang.server.business.dao.BusinessDao;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.RedisUtil;
import com.myshang.server.common.WeiXinUtil;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.model.Department;
import com.myshang.server.distribution.dao.DistributionDao;
import com.myshang.server.distribution.model.Distribution;
import com.myshang.server.extenduser.dao.ExtenduserDao;
import com.myshang.server.incomeRecord.dao.IncomeRecordDao;
import com.myshang.server.incomeRecord.model.IncomeRecord;
import com.myshang.server.reward.dao.RewardDao;
import com.myshang.server.reward.model.Reward;
import com.myshang.server.reward.service.RewardService;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.roomRuning.dao.RoomRuningDao;
import com.myshang.server.roomRuning.model.RoomRuning;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;
import com.myshang.server.user.service.UserService;
import com.myshang.server.vicePrincess.dao.VicePrincessDao;

/**
 * 
 * @author HL 2018.9.13.10:43
 */
@Service
@Transactional
public class RewardServiceImpl implements RewardService {
	private String weixintax = CmsConstants.WEIXINTAX;
	private static final Logger LOGGER = Logger.getLogger(Reward.class);
	@Autowired
	private RewardDao rewardDao;
	@Autowired
	private IncomeRecordDao incomeRecordDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userdao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private DistributionDao distributionDao;
	@Autowired
	private RoleDao roledao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private VicePrincessDao vicePrincessDao;
	@Autowired
	private RoomRuningDao roomRuningDao;
	@Override
	public int addRewardRecord(Reward reward) throws Exception {
		return rewardDao.addRewardRecord(reward);
	}

	/**
	 * 支付回调处理方法
	 * 
	 * @param notityXml
	 * @param key(商户秘钥)
	 * @return
	 * @throws Exception
	 */
	@Override
	public String payCallBackHandle(String notityXml, String key) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		String xml = null;
		try {
			if (StringUtils.isEmpty(notityXml)) {
				System.out.println("支付回调xml为空");
			} else {
				// xml转map
				Map<String, String> resData = WeiXinUtil.readStringXmlOut(notityXml);
				System.out.println(resData);
				if (resData.get("return_code").equals("SUCCESS")) {
					if (resData.get("result_code").equals("SUCCESS")) {
						// 签名验证
						String sign = resData.get("sign");
						resData.remove("sign");
						if (WeiXinUtil.getSign(resData, key).equals(sign)) {
							JSONObject callbackJson = JSONObject.fromObject(RedisUtil.get(resData.get("out_trade_no")));
							// 进行金钱比对
							if (resData.get("total_fee").equals(callbackJson.get("money").toString())) {
								distributionHandle(callbackJson);
								result.put("return_code", "SUCCESS");
								result.put("return_msg", "OK");
								xml = WeiXinUtil.map2XmlString(result);

							} else {
								result.put("return_code", "FAIL");
								result.put("return_msg", "金额被篡改");
								xml = WeiXinUtil.map2XmlString(result);
							}
						} else {
							result.put("return_code", "FAIL");
							result.put("return_msg", "签名错误");
							xml = WeiXinUtil.map2XmlString(result);
						}
					} else {
						result.put("return_code", "FAIL");
						result.put("return_msg", resData.get("err_code_des"));
						xml = WeiXinUtil.map2XmlString(result);
					}
				} else {
					result.put("return_code", "FAIL");
					result.put("return_msg", resData.get("return_msg"));
					xml = WeiXinUtil.map2XmlString(result);
				}
			}
			LOGGER.error("支付成功回调方法执行结果为:" + xml);

		} catch (Exception e) {

			e.printStackTrace();
			LOGGER.error("支付成功回调方法执行结果为:" + xml);

		}
		return xml;
	}

	/**
	 * 打赏分成方法
	 * 
	 * @param callbackJson
	 * @return
	 */
	public JsonData distributionHandle(JSONObject callbackJson) {
		List<IncomeRecord> incomeList = new ArrayList<IncomeRecord>();
		Reward reward = new Reward();
		JsonData json = new JsonData();
		Distribution distribution=new Distribution();
		try {
			String unionid = callbackJson.get("unionid").toString();
			int uid = Integer.parseInt(callbackJson.get("uid").toString());
			String openid = callbackJson.get("openid").toString();
			BigDecimal money = new BigDecimal(callbackJson.get("money").toString()).divide(new BigDecimal(100));
			// 测试用*100000
			money=money.multiply(new BigDecimal(100000));
			reward.setMoney(money);
			rewardDao.addRewardMoney(reward);
			//查询用户角色id
			User user=userdao.queryRolid(uid);
			//查询老板角色id
			int roleId=roledao.getRoleId(user.getBelongBusid());
			//查询用户是老板的id
			int boosUserId=userdao.getuserId(roleId);
			//查询用户角色信息
			Role role=roledao.getUserRole(user.getBelongRolid());
			int rewardCount=rewardDao.getrewardCount(uid);
			int canInternalRunId=userdao.getcanInternalRun(user.getBelongBusid());
			List<Distribution> distributionList=distributionDao.getDistribution(user.getBelongBusid());
			for(int i=0; i<distributionList.size(); i++){
				@SuppressWarnings("unused")
				int minNumber=0;
				@SuppressWarnings("unused")
				int maxNumber=0;
				String substring = distributionList.get(i).getRange().substring(0, distributionList.get(i).getRange().length());//截取最后一个
				LOGGER.error("截取公主字符串后"+substring);
				String[] split = substring.split(",");//以逗号分割
				for(int j=0;j<split.length;j++){
					if(StringUtils.isEmpty(split[j])){
						continue;
					}
					if(j==0){
						minNumber=Integer.valueOf(split[j]);
					}else{
						maxNumber=Integer.valueOf(split[j]);
					}
				}
				if(rewardCount >= minNumber && rewardCount <= maxNumber){
					distribution=distributionList.get(i);
				}
			}
			BigDecimal internalMoney = new BigDecimal(0);
			BigDecimal extendMoney = new BigDecimal(0);
			BigDecimal internalMoney1 = new BigDecimal(0);
			BigDecimal extendMoney1 = new BigDecimal(0);
			BigDecimal result = money
					.subtract(money.multiply(new BigDecimal(distribution.getWeixin())).divide(new BigDecimal(1000)));
			if(canInternalRunId != 0){
				internalMoney=internalMoney.add(result.multiply(new BigDecimal(distribution.getInternal())).divide(new BigDecimal(1000)));
				reward.setInternalMoney(internalMoney);
				reward.setInternalId(canInternalRunId);
			}else{
				internalMoney1=result.multiply(new BigDecimal(distribution.getInternal())).divide(new BigDecimal(1000));
			}
			int extendId=businessDao.getExtendId(user.getBelongBusid());
			if(extendId != 0){
				extendMoney=extendMoney.add(result.multiply(new BigDecimal(distribution.getExtend())).divide(new BigDecimal(1000)));
				reward.setExtendMoney(extendMoney);
				reward.setExtendId(extendId);
			}else{
				extendMoney1=result.multiply(new BigDecimal(distribution.getExtend())).divide(new BigDecimal(1000));
			}
			BigDecimal mamiMoney=result.multiply(new BigDecimal(distribution.getMami())).divide(new BigDecimal(1000));
			BigDecimal employeeMoney=result.multiply(new BigDecimal(distribution.getPrincess())).divide(new BigDecimal(1000));
			BigDecimal bossMoney=result.multiply(new BigDecimal(distribution.getBusiness())).divide(new BigDecimal(1000));
			BigDecimal platformMoney=result.multiply(new BigDecimal(distribution.getPlatform())).divide(new BigDecimal(1000));
			if(role.getCanProxyBusiness() == 1){
				reward.setBossId(boosUserId);
				reward.setBossMoney(bossMoney.add(employeeMoney).add(mamiMoney).add(internalMoney1));
				reward.setPlatformId(0);
				reward.setPlatformMoney(platformMoney.add(extendMoney1));
				reward.setRoomId(0);
			}else if(role.getCanBindPrincess() == 1){
				reward.setMamiId(uid);
				reward.setMamiMoney(mamiMoney.add(employeeMoney));
				reward.setBossId(boosUserId);
				reward.setBossMoney(bossMoney.add(internalMoney1));
				reward.setPlatformId(0);
				reward.setPlatformMoney(platformMoney.add(extendMoney1));
				RoomRuning RoomRuning=roomRuningDao.getRooming(uid);
				if(RoomRuning != null){
					reward.setRoomId(RoomRuning.getRoomId());
				}else{
					reward.setRoomId(0);
				}
			}else if(uid == canInternalRunId){
				reward.setInternalId(canInternalRunId);
				reward.setInternalMoney(internalMoney.add(employeeMoney));
				reward.setBossId(boosUserId);
				reward.setBossMoney(bossMoney.add(mamiMoney));
				reward.setPlatformId(0);
				reward.setPlatformMoney(platformMoney.add(extendMoney1));
				reward.setRoomId(0);
			}else{
				reward.setEmployeeId(uid);
				int MamiId=getBindMami(uid);
				if(MamiId != 0){
					reward.setEmployeeMoney(employeeMoney);
					reward.setMamiId(MamiId);
					reward.setMamiMoney(mamiMoney);
					reward.setBossMoney(bossMoney.add(internalMoney1));
				}else{
					reward.setEmployeeMoney(employeeMoney);
					reward.setBossMoney(bossMoney.add(internalMoney1).add(mamiMoney));
				}
				reward.setBossId(boosUserId);
				reward.setPlatformId(0);
				reward.setPlatformMoney(platformMoney.add(extendMoney1));
				RoomRuning roomRuning = roomRuningDao.getRewardPrincess(uid);
				RoomRuning RoomRuning= roomRuningDao.getRooming(uid);
				if(roomRuning != null){
					reward.setRoomId(roomRuning.getRunid());
				}else if(RoomRuning != null){
					reward.setRoomId(RoomRuning.getRunid());
				}else{
					reward.setRoomId(0);
				}
			}
			reward.setRewardId(uid);
			reward.setBelongBusid(user.getBelongBusid());
			reward.setRewardTime(new Timestamp(System.currentTimeMillis()));
			reward.setCustomOpenid(openid);
			reward.setCustomUnionid(unionid);
			int count = rewardDao.UpdateRewardRecordById(reward);
			LOGGER.error("rewardid为" + reward.getRewid());
			LOGGER.error("添加打赏金额执行行数为" + count);
			boolean isNotVice = false;
			Role Isuser=userdao.getIsManager(uid);
			for (int i = 0; i < 6; i++) {
				IncomeRecord income = new IncomeRecord();
				income.setIncomeTime(reward.getRewardTime());
				switch (i) {
				case 0: {
					if(Isuser.getCanProxyBusiness() == 0 && Isuser.getCanBindPrincess() == 0 && canInternalRunId != uid  && extendId != uid){
						income.setUserId(reward.getEmployeeId());
						income.setIncomeMoney(reward.getEmployeeMoney());
						income.setRewardId(reward.getRewid());
						break;
					}else{
						isNotVice = true;
					}
				}
				case 1: {
					if (reward.getMamiId() != 0) {
						income.setUserId(reward.getMamiId());
						income.setIncomeMoney(reward.getMamiMoney());
						income.setRewardId(reward.getRewid());
					} else {
						isNotVice = true;
					}
					break;
				}
				case 2: {
					income.setUserId(reward.getBossId());
					income.setIncomeMoney(reward.getBossMoney());
					income.setRewardId(reward.getRewid());
					break;
				}
				case 3: {
					income.setUserId(reward.getPlatformId());
					income.setIncomeMoney(reward.getPlatformMoney());
					income.setRewardId(reward.getRewid());
					break;
				}
				case 4: {
					if (reward.getExtendId() != 0 && extendMoney.compareTo(BigDecimal.ZERO)!=0) {
					income.setUserId(reward.getExtendId());
					income.setIncomeMoney(reward.getExtendMoney());
					income.setRewardId(reward.getRewid());
					break;
					} else {
						isNotVice = true;
					}
				}
				case 5: {
					if (reward.getInternalId() != 0 && internalMoney.compareTo(BigDecimal.ZERO)!=0) {
					income.setUserId(reward.getInternalId());
					income.setIncomeMoney(reward.getInternalMoney());
					income.setRewardId(reward.getRewid());
					break;
					} else {
						isNotVice = true;
					}
				}
				}
				if (isNotVice) {
					isNotVice = false;
					continue;
				}
				incomeList.add(income);
			}
			LOGGER.error("incomeList为" + incomeList.size());
			LOGGER.error("EmployeeMoney为" + reward.getEmployeeMoney());
			incomeRecordDao.addIncomeInfo(incomeList);
			if (reward.getEmployeeId() != 0) {
				UpAccount(reward.getEmployeeId(), reward.getEmployeeMoney());
			}
			if (reward.getMamiId() != 0) {
				UpAccount(reward.getMamiId(), reward.getMamiMoney());
			}
			if (canInternalRunId != 0) {
				UpAccount(reward.getInternalId(),reward.getInternalMoney());
			}
			if (extendId != 0) {
				UpAccount(reward.getExtendId(), reward.getExtendMoney());
			}
			UpAccount(reward.getBossId(), reward.getBossMoney());
			UpAccount(0, reward.getPlatformMoney());
			json.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			json.setCodeEnum(CodeEnum.ERROR);
			e.printStackTrace();
		}
		return json;
	}
	//修改账户余额
	@SuppressWarnings("unused")
	private void UpAccount(int id,BigDecimal account){
		BigDecimal money=accountDao.getAccount(id);
		accountDao.UpdateAccount(id, money.add(account));
	}
	@SuppressWarnings("unused")
	private int getBindMami(int uid){
		int viceManagerId=0;
		viceManagerId = vicePrincessDao.getVicePrincess(uid);
		if(viceManagerId == 0){
			RoomRuning roomRuning = roomRuningDao.getRewardPrincess(uid);
			if(roomRuning != null){
				Role role=userdao.getIsManager(roomRuning.getOpenRoomUid());
				if(role.getCanBindPrincess() == 1){
					viceManagerId=roomRuning.getOpenRoomUid();
				}
			}
		}
		return viceManagerId;
	}
	public String getTicket(String appid, String secret) {
		String ticket = "";
		try {
			if ((RedisUtil.get("accesstoken") == "" || RedisUtil.get("accesstoken") == null)
					&& (RedisUtil.get("ticket") == "" || RedisUtil.get("ticket") == null)) {
				String accesstoken = WeiXinUtil.getAccessToken(appid, secret);
				ticket = WeiXinUtil.getTicket(accesstoken);
				RedisUtil.setex("accesstoken", accesstoken, 7150);
				RedisUtil.setex("ticket", ticket, 7150);
				LOGGER.error("ticket为00000" + ticket);
			} else {
				LOGGER.error("ticket为111111");
				return RedisUtil.get("ticket");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.error("ticket为222222" + ticket);
		return ticket;
	}
	/**
	 * 查询服务员绑定的房间
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData queryRewardMoney(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int busid=Integer.valueOf(param.get("busid"));
		HashMap<String, Object> ModuleNameMap= new HashMap<String, Object>();
		try {
			BigDecimal sumMoney=rewardDao.queryRewardMoney(busid);
			int rewardNumber=rewardDao.queryrewardCount(busid);
			ModuleNameMap.put("SweepCode", "扫码活动");
			ModuleNameMap.put("StatisticalDetails", "统计详情");
			ModuleNameMap.put("SweepCodeSize", "扫码次数");
			ModuleNameMap.put("winningPeople", "今日获奖人次");
			ModuleNameMap.put("more","更多");
			Map map=new HashMap();
			map.put("sumMoney", sumMoney);
			map.put("rewardNumber", rewardNumber);
			map.put("ModuleNameMap", ModuleNameMap);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询打赏信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
