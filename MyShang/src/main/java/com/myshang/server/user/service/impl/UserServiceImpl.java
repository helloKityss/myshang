package com.myshang.server.user.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.account.model.Account;
import com.myshang.server.attendance.dao.AttendanceDao;
import com.myshang.server.business.dao.BusinessDao;
import com.myshang.server.business.model.Business;
import com.myshang.server.business.service.impl.BusinessServiceImpl;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.DateUtil;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.JsonList;
import com.myshang.server.common.UUIDGeneratorUtil;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.dao.DepartmentDao;
import com.myshang.server.messages.dao.MessagesDao;
import com.myshang.server.preciousCategory.dao.PreciousDao;
import com.myshang.server.preciousCategory.model.Precious;
import com.myshang.server.preciousSale.dao.PreciousSaleDao;
import com.myshang.server.preciousSale.model.PreciousSale;
import com.myshang.server.qrcode.dao.QrcodeDao;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.room.dao.RoomDao;
import com.myshang.server.room.model.Room;
import com.myshang.server.roomRuning.dao.RoomRuningDao;
import com.myshang.server.roomRuning.model.RoomRuning;
import com.myshang.server.third.util.JiGuangSmsUtil;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;
import com.myshang.server.user.service.UserService;
import com.myshang.server.vicePrincess.dao.VicePrincessDao;

import net.sf.json.JSONObject;

/**
 * @author zhangkang 2018年6月10日 下午6:49:50
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = Logger.getLogger(User.class);
	@Autowired
	private UserDao userdao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PreciousDao preciousDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private RoomRuningDao roomRuningDao;
	@Autowired
	private VicePrincessDao vicePrincessDao;
	@Autowired
	private AttendanceDao attendanceDao;
	@Autowired
	private MessagesDao messagesDao;
	@Autowired
	private QrcodeDao qrcodeDao;

	/**
	 * 保存微信用户个人信息
	 *
	 * @param userInfo
	 * @throws Exception
	 */
	public int updateUserInfoByWeiXin(User user) throws Exception {
		return userdao.updateUserInfoByWeiXin(user);
	}

	/**
	 * 保存从微信获得的用户电话号码
	 *
	 * @param phoneNum
	 * @throws Exception
	 */
	public int updateUserMobileByWeiXin(User user) throws Exception {
		return userdao.updateUserMobileByWeiXin(user);
	}

//	@Override
//	public JsonData createUser(Map<String, String> param) {
//		JsonData jsonData = new JsonData();
//		int belongDepid=0;
//		int belongRolid=0;
//		int belongBusid=0;
//		try {
//			int uid = Integer.valueOf(param.get("uid"));
//			vicePrincessDao.deleteviceprinUser(uid);
//			qrcodeDao.deleteqrcode(uid);
//			messagesDao.deleteMessages(uid);
//			attendanceDao.deleteAttendance(uid);
//			String userName = param.get("userName");
//			belongBusid = Integer.valueOf(param.get("belongBusid"));
//			out:if(belongBusid == 0){
//				User user=userdao.getuseropen(uid);
//				if(user.getHasRegister() == 1){
//					belongDepid=user.getBelongDepid();
//					belongRolid=user.getBelongRolid();
//					belongBusid=user.getBelongBusid();
//				}else{
//					break out;
//				}
//			}else{
//				belongDepid = Integer.valueOf(param.get("belongDepid"));
//				belongRolid = Integer.valueOf(param.get("belongRolid"));
//				int roleIds = roleDao.getRoleId(belongBusid);
//				int roleids = roleDao.getcanInternalRun(belongBusid);
//				User userdata = userdao.getmanagerid(belongBusid, roleIds);
//				User userdata1 = userdao.getmanagerid(belongBusid, roleids);
//				if (userdata != null && roleIds == belongRolid) {
//					jsonData.setCodeEnum(CodeEnum.ERROR_MANAGER);
//					return jsonData;
//				}
//				if (userdata1 != null && roleids == belongRolid) {
//					jsonData.setCodeEnum(CodeEnum.ERROR_MANAGER);
//					return jsonData;
//				}
//			}
//			User user = new User();
//			user.setUid(uid);
//			user.setUserName(userName);
//			user.setBelongBusid(belongBusid);
//			user.setBelongDepid(belongDepid);
//			user.setBelongRolid(belongRolid);
//			user.setHasRegister(1);
//			userdao.createUser(user);
//			int Acount = accountDao.getAccountCount(uid);
//			if (Acount < 1) {
//				Account account = new Account();
//				account.setUserId(uid);
//				accountDao.addAccount(account);
//			}
//			Role role = roleDao.getRoleInput(belongRolid);
//			jsonData.setBelongBusid(belongBusid);
//			jsonData.setBelongDepid(belongDepid);
//			jsonData.setBelongRolid(belongRolid);
//			jsonData.setRole(role);
//			jsonData.setCodeEnum(CodeEnum.SUCCESS);
//		} catch (Exception e) {
//			LOGGER.error("添加用户信息出错", e);
//			jsonData.setCodeEnum(CodeEnum.ERROR);
//		}
//		return jsonData;
//	}

	@Override
	public JsonList<User> getOpenId(String unionId) throws Exception {
		JsonList<User> jsonData = new JsonList<User>();
		try {
			List<User> userArray = userdao.getUserOpenId(unionId);
			if (userArray != null && userArray.size() > 0) {
				jsonData.setTlist(userArray);
				jsonData.setCode(0);
			} else {
				jsonData.setCode(1);
			}
		} catch (Exception e) {
			jsonData.setCode(1);
			LOGGER.error("查询接口信息失败", e);
		}
		return jsonData;
	}

	/*
	 * 获取用户角色
	 */
	@Override
	public JsonData queryRolid(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int uid = Integer.valueOf(param.get("uid"));
		List<Precious> PreciousList = new ArrayList<Precious>();
		try {
			User UserRole = userdao.queryRolid(uid);
			Role RoleType = roleDao.getUserRole(UserRole.getBelongRolid());
			User Userbus = userdao.queryBusid(uid);
			List<Room> RoomList = roomDao.getRoomList(Userbus.getBelongBusid());
			if (RoleType.getCanInputWine() == 1) {
				PreciousList = preciousDao.getPreciousAlcohol(UserRole.getBelongBusid());
			} else if (RoleType.getCanInputFood() == 1) {
				PreciousList = preciousDao.getPreciousFood(UserRole.getBelongBusid());
			}
			jsonData.setData(PreciousList);
			jsonData.setList(RoomList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户角色出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	/*
	 * 通过用户id查询打赏表绑定信息
	 */
	@Override
	public Map<String, Object> getReward(int uid) throws Exception {
		Map<String, Object> rewardMap = new HashMap<String, Object>();
		try {
			int canBindPrinToRoom = userdao.getWaiterBind(uid);
			int canBindPrincess=userdao.getIsCanBind(uid);
			Role Isuser=userdao.getIsManager(uid);
			if (canBindPrinToRoom != 0) {
				rewardMap.put("roomId", 0);
				rewardMap.put("dtbtype", "dtb2");
			}else if(canBindPrincess !=0){
				rewardMap.put("viceManagerId", uid);
				rewardMap.put("dtbtype", "dtb3");
				rewardMap.put("roomId", 0);
			}else if(Isuser.getCanProxyBusiness() != 0 || Isuser.getRoleName().equals("总经理")){
				rewardMap.put("dtbtype", "dtb4");
				rewardMap.put("roomId", 0);
			}else {
				RoomRuning roomRuning = roomRuningDao.getRewardPrincess(uid);
				int viceManagerId = vicePrincessDao.getVicePrincess(uid);
				if (roomRuning != null) {
					rewardMap.put("roomId", roomRuning.getRoomId());
					if (viceManagerId != 0) {
						rewardMap.put("viceManagerId", viceManagerId);
						rewardMap.put("dtbtype", "dtb1");
					} else if (roomRuning.getOpenRoomUid() != 0) {
						User user=userdao.queryRolid(roomRuning.getOpenRoomUid());
						Role role=roleDao.getUserRole(user.getBelongRolid());
						if(role.getCanBindPrincess() == 1){
							rewardMap.put("viceManagerId", roomRuning.getOpenRoomUid());
							rewardMap.put("dtbtype", "dtb1");
						}else{
							rewardMap.put("dtbtype", "dtb2");
						}
					} else {
						rewardMap.put("dtbtype", "dtb2");
					}
				} else {
					rewardMap.put("roomId", 0);
					if (viceManagerId != 0) {
						rewardMap.put("viceManagerId", viceManagerId);
						rewardMap.put("dtbtype", "dtb1");
					} else {
						rewardMap.put("dtbtype", "dtb2");
					}
				}
			}
			User UserBus = userdao.queryBusid(uid);
			rewardMap.put("belongBusid", UserBus.getBelongBusid());
		} catch (Exception e) {
			LOGGER.error("查询打赏码表绑定关系失败", e);
		}
		return rewardMap;
	}

	@Override
	public JsonData findUserByUuid(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int issueUserId = Integer.parseInt(param.get("uid"));
			jsonData.setData(userdao.findUserByUuid(issueUserId));
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询用户信息出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	@Override
	public JsonData getUserinformation(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int issueUserId = Integer.valueOf(param.get("uid"));
			Map<String, Object> userMap = userdao.getUserinformation(issueUserId);
			jsonData.setData(userMap);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询用户信息出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 获取副理绑定公主信息
	 */
	@Override
	public JsonData getAllisCanBind(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int belongBusid = Integer.valueOf(param.get("belongBusid"));
			int viceManagerId = Integer.valueOf(param.get("uid"));
			// int belongRolid=roleDao.getisCanBindRole(belongBusid);
			List<User> isCanBindList = userdao.getUserinfoByViceprincess(belongBusid, viceManagerId);
			jsonData.setData(isCanBindList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	/*
	 * 查询所有员工信息
	 */
	@Override
	public JsonData getstaffAll(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int belongBusid = Integer.valueOf(param.get("belongBusid"));
			// int belongRolid=roleDao.getisCanBindRole(belongBusid);
			List<User> UserList = userdao.getstaffAll(belongBusid);
			jsonData.setData(UserList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}

	/*
	 * 修改员工信息
	 */
	@Override
	public JsonData updateUserinformation(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int uid = Integer.valueOf(param.get("uid"));
			int belongRolid = Integer.valueOf(param.get("belongRolid"));
			int belongDepid = Integer.valueOf(param.get("belongDepid"));
			User user = new User();
			user.setUid(uid);
			user.setBelongRolid(belongRolid);
			user.setBelongDepid(belongDepid);
			userdao.updateUserinformation(user);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 修改员工信息
	 */
	@Override
	public JsonData updateuserName(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int uid = Integer.valueOf(param.get("uid"));
			String userName = param.get("userName");
			User user = new User();
			user.setUid(uid);
			user.setUserName(userName);
			userdao.updateuserName(user);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("修改用户姓名失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 删除员工信息
	 */
	@Override
	public JsonData deleteUser(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			String uids = param.get("uid");
			String substring = uids.substring(0, uids.length());//截取最后一个
			LOGGER.error("截取投诉标签字符串后"+substring);
			String[] split = substring.split(",");//以逗号分割
			for(int i = 0; i<split.length;i++){
				int uid=Integer.valueOf(split[i]);
				userdao.deleteUser(uid);
				vicePrincessDao.deleteviceprinUser(uid);
				qrcodeDao.deleteqrcode(uid);
				messagesDao.deleteMessages(uid);
				attendanceDao.deleteAttendance(uid);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除用户信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 删除员工信息
	 */
	@Override
	public String getqrcodeUrl(int busid) throws Exception {
		String qrcodeUrl=businessDao.getbusinessByUrl(busid);
		return qrcodeUrl;
	}
	/*
	 * 生成小程序注册码
	 */
	@Override
	public JsonData addqrcode(JSONObject jsonResp,int busid) throws Exception {
		JsonData jsonData = new JsonData();
		RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			if (jsonResp != null && !jsonResp.equals("")) {
				String AccessTokenurl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+jsonResp.get("access_token");
				// 进行网络请求,访问url接口
	            Map<String,Object> param = new HashMap<>();
	            param.put("scene", busid);
	            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	            HttpEntity requestEntity = new HttpEntity(param, headers);
	            ResponseEntity<byte[]> entity = rest.exchange(AccessTokenurl, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
	            byte[] result = entity.getBody();
	            inputStream = new ByteArrayInputStream(result);
	            String path =request.getSession().getServletContext().getRealPath("/BusinessRegister");
	            File file = new File(path+"/"+"business_"+busid+".png");
	            String qrcodeUrl="BusinessRegister"+"/"+"business_"+busid+".png";
	            if (!file.exists()){
	                file.createNewFile();
	            }
	            outputStream = new FileOutputStream(file);
	            int len = 0;
	            byte[] buf = new byte[1024];
	            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
	                outputStream.write(buf, 0, len);
	            }
	            outputStream.flush();
				Business business=new Business();
				business.setBusid(busid);
				business.setQrcodeUrl(qrcodeUrl);
				if(businessDao.updateBusiness(business)>0){
					jsonData.setData(qrcodeUrl);
				}
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("注册二维码生成出错", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 删除员工信息
	 */
	@Override
	public JsonData updateuserisOnline(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int uid = Integer.valueOf(param.get("uid"));
			userdao.updateuserisOnline(uid);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除用户信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 删除员工信息
	 */
	@Override
	public JsonData getuserisOnline(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		try {
			int busid = Integer.valueOf(param.get("busid"));
			int depid=Integer.valueOf(param.get("depid"));
			list=userdao.getuserisOnline(busid,depid);
			jsonData.setData(list);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除用户信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 注册
	 */
	@Override
	public JsonData register(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			String phoneNumber=param.get("phoneNumber");
			String passWord=param.get("passWord");
			String registrationCode=param.get("registrationCode");
			String msgId=param.get("msgId");
			String code=param.get("code");
			String userName=param.get("userName");
			String avatarUrl=param.get("avatarUrl");
			int gender=Integer.valueOf(param.get("gender"));
			String birthday=param.get("birthday");
			String province=param.get("province");
			String country=param.get("country");
			int belongBusid=Integer.valueOf(param.get("belongBusid"));
			int belongDepid=Integer.valueOf(param.get("belongDepid"));
			int belongRolid=Integer.valueOf(param.get("belongRolid"));
			int height=Integer.valueOf(param.get("height"));
			String speciality=param.get("speciality");
			String Logo="HeadPicture"+"/"+avatarUrl;
			User user1 = userdao.findUserByPhone(phoneNumber);
			if (user1 != null) {
				jsonData.setCodeEnum(CodeEnum.USER_AREADY_REGISTER);
				return jsonData;
			}
			if (!JiGuangSmsUtil.ValidSMSCode(msgId, code)) {
				jsonData.setCodeEnum(CodeEnum.CODE_VALID_FAIL);
				return jsonData;
			}
			User user =new User();
			user.setPhoneNumber(phoneNumber);
			user.setPassWord(passWord);
			user.setRegistrationCode(registrationCode);
			user.setUserName(userName);
			user.setAvatarUrl(Logo);
			user.setGender(gender);
			user.setBirthday(birthday);
			user.setProvince(province);
			user.setCountry(country);
			user.setHeight(height);
			user.setSpeciality(speciality);
			user.setCreateTime(DateUtil.DateToString(new Date(), DateUtil.DEFAULT_DATE_FORMAT2));
			user.setBelongBusid(belongBusid);
			user.setBelongDepid(belongDepid);
			user.setBelongRolid(belongRolid);
			user.setHasRegister(1);
			user.setIsOnline(1);
     		int userId=userdao.register(user);
     		user.setUid(userId);
			Role role=roleDao.getUserRole(user.getBelongRolid());
			jsonData.setData(user);
			jsonData.setList(role);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("注册用户失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 完善员工信息
	 */
	@Override
	public JsonData perfect(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int uid=Integer.valueOf(param.get("uid"));
			String userName=param.get("userName");
			String avatarUrl=param.get("avatarUrl");
			int gender=Integer.valueOf(param.get("gender"));
			String birthday=param.get("birthday");
			String province=param.get("province");
			String country=param.get("country");
			int height=Integer.valueOf(param.get("height"));
			String Logo="HeadPicture"+"/"+avatarUrl;
			User user =new User();
			user.setUid(uid);
			user.setUserName(userName);
			user.setAvatarUrl(Logo);
			user.setGender(gender);
			user.setBirthday(birthday);
			user.setProvince(province);
			user.setCountry(country);
			user.setHeight(height);
			userdao.perfect(user);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("注册用户失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 完善员工信息
	 */
	@Override
	public JsonData complete(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			int uid=Integer.valueOf(param.get("uid"));
			int belongBusid=Integer.valueOf(param.get("belongBusid"));
			int belongDepid=Integer.valueOf(param.get("belongDepid"));
			int belongRolid=Integer.valueOf(param.get("belongRolid"));
			User user =new User();
			user.setUid(uid);
			user.setBelongBusid(belongBusid);
			user.setBelongDepid(belongDepid);
			user.setBelongRolid(belongRolid);
			userdao.complete(user);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("注册用户失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 查询所有商户
	 */
	@Override
	public JsonData uploadAvatarUrl(MultipartFile multipartFile) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			String path = BusinessServiceImpl.class.getResource("/").getPath();
			String picPath = "HeadPicture/";
			String imagePath = path.substring(0, path.indexOf("WEB-INF")) + picPath;
			if (!multipartFile.isEmpty()) {
				String originalFilename = multipartFile.getOriginalFilename();
				String filename = UUIDGeneratorUtil.generate32Random()
						+ originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
				File file = new File(imagePath, filename);
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				multipartFile.transferTo(new File(imagePath + filename));
				LOGGER.info("用户:" + "1111111111" + "的头像保存地址为:" + imagePath + filename);
				LOGGER.info("更新用户：" + "1111111111" + "头像为:" + picPath + filename);
				Map map=new HashMap();
				map.put("filename", filename);
				jsonData.setData(map);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 验证码登陆
	 */
	@Override
	public JsonData loginCode(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			String phoneNumber=param.get("phoneNumber");
			String msgId=param.get("msgId");
			String code=param.get("code");
			User user1 = userdao.findUserByPhone(phoneNumber);
			if (user1 == null) {
				jsonData.setCodeEnum(CodeEnum.USER_NOT_EXISTS);
				return jsonData;
			}
			if (!JiGuangSmsUtil.ValidSMSCode(msgId, code)) {
				jsonData.setCodeEnum(CodeEnum.CODE_VALID_FAIL);
				return jsonData;
			}
			Role role=roleDao.getUserRole(user1.getBelongRolid());
			jsonData.setData(user1);
			jsonData.setList(role);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("登录失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 密码登陆
	 */
	@Override
	public JsonData loginPassword(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			String phoneNumber=param.get("phoneNumber");
			String passWord=param.get("passWord");
			User user1 = userdao.findUserByPhone(phoneNumber);
			if (user1 == null) {
				jsonData.setCodeEnum(CodeEnum.USER_NOT_EXISTS);
				return jsonData;
			}
			int row = userdao.checkPassword(phoneNumber, passWord);
			if (row <= 0) {
				jsonData.setCodeEnum(CodeEnum.VALID_FAIL);
				return jsonData;
			}
			Role role=roleDao.getUserRole(user1.getBelongRolid());
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
			jsonData.setData(user1);
			jsonData.setList(role);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("登录失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 密码登陆
	 */
	@Override
	public JsonData updatePassword(Map<String, String> param) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			String phoneNumber=param.get("phoneNumber");
			String passWord=param.get("passWord");
			String msgId=param.get("msgId");
			String code=param.get("code");
			if (!JiGuangSmsUtil.ValidSMSCode(msgId, code)) {
				jsonData.setCodeEnum(CodeEnum.CODE_VALID_FAIL);
				return jsonData;
			}
			User user=new User();
			user.setPhoneNumber(phoneNumber);
			user.setPassWord(passWord);
			userdao.updatePassword(user);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("登录失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 修改员工信息
	 */
	@Override
	public JsonData saveOpenId(JSONObject jsonResp,int uid) throws Exception {
		JsonData jsonData = new JsonData();
		try {
			User user = new User();
			user.setUid(uid);
			user.setOpenId(jsonResp.getString("openid"));
			user.setUnionId(jsonResp.getString("unionid"));
			userdao.updateUserinformation(user);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询所有公主信息失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}