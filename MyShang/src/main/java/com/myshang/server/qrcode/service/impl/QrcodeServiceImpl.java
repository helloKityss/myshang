package com.myshang.server.qrcode.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.JsonList;
import com.myshang.server.qrcode.dao.QrcodeDao;
import com.myshang.server.qrcode.model.Qrcode;
import com.myshang.server.qrcode.service.QrcodeService;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.roomRuning.dao.RoomRuningDao;
import com.myshang.server.roomRuning.model.RoomRuning;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;
import com.myshang.server.user.service.UserService;
import com.myshang.server.vicePrincess.dao.VicePrincessDao;
import com.myshang.server.vicePrincess.model.VicePrincess;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.incomeRecord.dao.IncomeRecordDao;
import com.myshang.server.incomeRecord.model.IncomeRecord;

/**
 * 二维码Service实现
 * 
 * @author HL
 * 
 */

@Service
@Transactional
public class QrcodeServiceImpl implements QrcodeService {
	private static final Logger LOGGER = Logger.getLogger(Qrcode.class);
	@Autowired
	QrcodeDao qrcodeDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	VicePrincessDao vicePrincessDao;
	@Autowired
	RoomRuningDao roomRuningDao;
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;
	@Autowired
	IncomeRecordDao incomeDao;
	@Autowired
	AccountDao accountDao;

	/**
	 * 根据二维码id查询相关数据
	 */
	@Override
	public Qrcode getQrcodeById(int qrcid) throws Exception {
		return qrcodeDao.getQrcodeById(qrcid);
	}

	/**
	 * 判断扫码人与被扫码人关系
	 */
	@Override
	public JSONObject RelationshipJudgment(String state, int QrcidOrUid, String unionid) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			JsonList<User> jsonList = userService.getOpenId(unionid);
			User scanUser = new User();
			if (jsonList.getCode() == 0) {
				scanUser = jsonList.getTlist().get(0);
			}
			switch (state) {
			case "1": // 二维码已绑定，执行打赏或绑定公主操作
				if (scanUser.getHasRegister() != null && scanUser.getHasRegister() == 1) // 扫码人已注册表示为公司员工
				{
					Role role = roleDao.getRoleName(scanUser.getBelongRolid());
					int scanUid = scanUser.getUid();
					if (userDao.queryBusid(QrcidOrUid).getBelongBusid() == userDao.queryBusid(scanUid).getBelongBusid()) {
						if (role.getCanBindPrincess() == 1) { // 判断是否为副理绑定公主
							if (vicePrincessDao.getVPByVidAndPid(scanUid, QrcidOrUid).size() == 0) {
								jsonObject = ViceBindPrincess(QrcidOrUid, scanUid);
							} else {
								jsonObject.put("type", "ViceBindPrincess");
								jsonObject.put("msg", "HasBinded");
							}
						} else if (role.getCanBindPrinToRoom() == 1) { // 判断是否为服务员绑定公主到房间
							jsonObject = WaiterBindPrincess(QrcidOrUid, scanUid);
						} else if (role.getCanToReward() == 1) { // 判断是否有打赏别人的权限
							jsonObject.put("type", "ToReward");
							jsonObject.put("msg", "Success");
						} else {
							jsonObject.put("type", "Other");
							jsonObject.put("msg", "DontHaveRewardPermission");
						}
					} else {
						jsonObject.put("type", "Other");
						jsonObject.put("msg", "NotInSameBusiness");
					}
				} else // 扫码人非公司员工，执行打赏
				{
					jsonObject.put("type", "ToReward");
					jsonObject.put("msg", "Success");
				}
				break;
			case "0": // 二维码未绑定，执行绑定操作
				if (scanUser.getHasRegister() != null && scanUser.getHasRegister() == 1) // 扫码人已注册表示为公司员工
				{
					jsonObject = BindQrcode(QrcidOrUid, scanUser);
				} else {
					jsonObject.put("type", "Other");
					jsonObject.put("msg", "DontHaveBindPermission");
				}
				break;
			}
		} catch (Exception e) {
			jsonObject.put("type", "Other");
			jsonObject.put("msg", "Exception");
			e.printStackTrace();
		}
		return jsonObject;
	}

	public int DeductLabelCost(int uid, BigDecimal cost) throws Exception {
		// 绑码人扣除标签成本
		IncomeRecord bindCost = new IncomeRecord();
		bindCost.setUserId(uid);
		bindCost.setIncomeMoney(cost.negate());
		bindCost.setRewardId(0);
		bindCost.setIncomeTime(new Timestamp(System.currentTimeMillis()));
		// 红科收回标签成本
		IncomeRecord hongkeRecycle = new IncomeRecord();
		hongkeRecycle.setUserId(0);
		hongkeRecycle.setIncomeMoney(cost);
		hongkeRecycle.setRewardId(0);
		hongkeRecycle.setIncomeTime(new Timestamp(System.currentTimeMillis()));
		if (incomeDao.addAnIncome(bindCost) > 0 && incomeDao.addAnIncome(hongkeRecycle) > 0) {
			BigDecimal oriMoney = accountDao.getAccount(uid);
			BigDecimal nowMoney = oriMoney.subtract(cost);
			accountDao.UpdateAccount(uid, nowMoney);
			oriMoney = accountDao.getAccount(0);
			nowMoney = oriMoney.add(cost);
			accountDao.UpdateAccount(0, nowMoney);
		}
		return 0;
	}

	/**
	 * 绑定二维码方法
	 */
	public JSONObject BindQrcode(int qrcid, User bindUser) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", "BindQrcode");
		try {
			Qrcode qrcodeOld = qrcodeDao.getqrcode(bindUser.getUid());
			if (qrcodeOld != null) {
				qrcodeOld.setHasBind(0);
				qrcodeOld.setBindUserId(0);
				qrcodeOld.setBelongBusid(0);
				qrcodeOld.setBindTime(new Timestamp(System.currentTimeMillis()));
				qrcodeDao.updateQrcodeStatusById(qrcodeOld);
			}
			Qrcode qrcode = new Qrcode();
			qrcode.setQrcid(qrcid);
			qrcode.setHasBind(1);
			qrcode.setBindUserId(bindUser.getUid());
			qrcode.setBelongBusid(bindUser.getBelongBusid());
			qrcode.setBindTime(new Timestamp(System.currentTimeMillis()));
			if (qrcodeDao.updateQrcodeStatusById(qrcode) > 0) {
				jsonObject.put("msg", "Success");
				// 绑一个码，扣1.5元的工本费
//				DeductLabelCost(bindUser.getUid(), new BigDecimal(1.5));
			} else {
				jsonObject.put("msg", "Fail");
			}
		} catch (Exception e) {
			jsonObject.put("msg", "Exception");
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 副理绑定公主方法
	 */
	public JSONObject ViceBindPrincess(int prinUid, int viceUid) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", "ViceBindPrincess");
		try {
			if (roleDao.getRoleUid(prinUid).getCanBeBindToRoom() == 1) {
				VicePrincess vicePrincess = new VicePrincess();
				vicePrincess.setViceManagerId(viceUid);
				vicePrincess.setBindPrincessId(prinUid);
				vicePrincess.setBindTime(new Timestamp(System.currentTimeMillis()));
				if (vicePrincessDao.getVicePrincess(prinUid) != 0) {
					jsonObject.put("msg", "HadBeenBinded");
					return jsonObject;
				}
				if (vicePrincessDao.addVicePrincess(vicePrincess) > 0) {
					jsonObject.put("msg", "Success");
				} else {
					jsonObject.put("msg", "Fail");
				}
			} else {
				jsonObject.put("msg", "NotPrincess");
			}
		} catch (Exception e) {
			jsonObject.put("msg", "Exception");
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 服务员绑定公主方法
	 */
	public JSONObject WaiterBindPrincess(int prinUid, int waiterUid) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("type", "WaiterBindPrincess");
		try {
			if (roleDao.getRoleUid(prinUid).getCanBeBindToRoom() == 1) { // 只有公主可被绑定到房间
				List<RoomRuning> RRList = roomRuningDao.getRoomRuningListByOpenUid(waiterUid);
				if (RRList.size() > 0) { // 服务员已服务房间
					RoomRuning roomRuning = RRList.get(0); // 绑定服务员的房间运营记录
					RoomRuning rrp = roomRuningDao.getRewardPrincess(prinUid); // 绑定公主的房间运营记录
					if (rrp != null) { // 此公主已被绑定到房间
						if (rrp.getRoomId() != roomRuning.getRoomId()) { // 公主与服务员不在同一房间
							String ss = ",";
							String substring = rrp.getBindPrincesses().substring(1, rrp.getBindPrincesses().length() - 1);// 截取最后一个
							LOGGER.error("截取公主字符串后" + substring);
							String[] split = substring.split(",");// 以逗号分割
							for (int i = 0; i < split.length; i++) {
								if (split[i].equals(String.valueOf(prinUid)))
									continue;
								ss += split[i] + ",";
							}
							rrp.setBindPrincesses(ss);
							LOGGER.error("rrpPrincess为:" + rrp.getBindPrincesses());
							if (roomRuningDao.updateRoomRuningPrincess(rrp) > 0) {
								if (roomRuning.getBindPrincesses().length() > 0) {
									roomRuning.setBindPrincesses(
											roomRuning.getBindPrincesses() + String.valueOf(prinUid) + ",");
								} else {
									roomRuning.setBindPrincesses("," + String.valueOf(prinUid) + ",");
								}
								if (roomRuningDao.updateRoomRuningPrincess(roomRuning) > 0) {
									jsonObject.put("msg", "AddSuccess");
								} else {
									jsonObject.put("msg", "AddFail");
								}
							} else {
								jsonObject.put("msg", "DeleteFail");
							}
						} else {
							jsonObject.put("msg", "InSameRoom");
						}
					} else { // 此公主未被绑定到房间
						if (!StringUtils.isEmpty(roomRuning.getBindPrincesses())) {
							roomRuning.setBindPrincesses(roomRuning.getBindPrincesses() + String.valueOf(prinUid) + ",");
						} else {
							roomRuning.setBindPrincesses("," + String.valueOf(prinUid) + ",");
						}
						if (roomRuningDao.updateRoomRuningPrincess(roomRuning) > 0) {
							jsonObject.put("msg", "AddSuccess");
						} else {
							jsonObject.put("msg", "AddFail");
						}
					}
				} else {
					jsonObject.put("msg", "WaiterNotBindRoom");
				}
			} else {
				jsonObject.put("msg", "NotPrincess");
			}
		} catch (Exception e) {
			jsonObject.put("msg", "Exception");
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * 查询打赏二维码
	 */
	@Override
	public JsonData getqrcode(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int bindUserId = Integer.valueOf(param.get("uid"));
		try {
			Qrcode qrcode = qrcodeDao.getqrcode(bindUserId);
			if (qrcode == null) {
				jsonData.setCodeEnum(CodeEnum.ERROR_HASBIND);
				return jsonData;
			}
			jsonData.setData(qrcode);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间列表出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	/**
	 * 查询打赏二维码
	 * 
	 * @author zhangkang 2018年4月24日 上午11:12:37
	 * @return
	 */
	@Override
	public JsonData getqrcodeAll(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int belongBusid = Integer.valueOf(param.get("belongBusid"));
		try {
			List<Qrcode> qrcodeList = qrcodeDao.getqrcodeAll(belongBusid);
			jsonData.setData(qrcodeList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间列表出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	/**
	 * 查询打赏二维码
	 * 
	 * @author zhangkang 2018年4月24日 上午11:12:37
	 * @return
	 */
	@Override
	public JsonData saveQRcode(Qrcode qrcode) {
		JsonData jsonData = new JsonData();
		try {
			qrcodeDao.saveQRcode(qrcode);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询房间列表出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	/**
	 * 查询打赏二维码
	 * 
	 * @author zhangkang 2018年4月24日 上午11:12:37
	 * @return
	 */
	@Override
	public int getqrcodeId() {
		int qrcid = qrcodeDao.getqrcodeId();
		return qrcid;
	}
}
