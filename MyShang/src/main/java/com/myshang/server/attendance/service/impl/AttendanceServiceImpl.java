package com.myshang.server.attendance.service.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.attendance.dao.AttendanceDao;
import com.myshang.server.attendance.model.Attendance;
import com.myshang.server.attendance.service.AttendanceService;
import com.myshang.server.business.dao.BusinessDao;
import com.myshang.server.business.model.Business;
import com.myshang.server.common.GoogleMapsUtil;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.user.dao.UserDao;
import com.myshang.server.user.model.User;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService{
	@Autowired
	private AttendanceDao attendanceDao;
	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	/**
	 * 员工打卡功能
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData addAttendance(Map<String, String> param) {
		int uid=Integer.valueOf(param.get("uid"));
//		int busid=Integer.valueOf(param.get("busid"));
		int attendType=Integer.valueOf(param.get("attendType"));
//		double longitude=Double.parseDouble(param.get("longitude"));
//		double latitude=Double.parseDouble(param.get("latitude"));
		JsonData jsonData = new JsonData();
		try {
//			Business business=businessDao.getLatitudeAndLongitude(busid);
//			double distance=GoogleMapsUtil.GetDistance(latitude, longitude,  business.getLatitude(), business.getLongitude());
//	    	if(distance>=500d){
//	    		jsonData.setCodeEnum(CodeEnum.ERROR_DISTANCE);;
//	    		return jsonData;
//	    	}
			User userEney=userDao.queryBusid(uid);
	    	int belongDepid=userDao.queryDepid(uid);
			Attendance attendance=new Attendance();
			attendance.setUserId(uid);
			attendance.setAttendTime(new Timestamp(System.currentTimeMillis()));
	    	attendance.setAttendType(attendType);
	    	attendance.setBelongBusid(userEney.getBelongBusid());
	    	attendance.setBelongDepid(belongDepid);
			attendanceDao.addAttendance(attendance);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("打卡失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 今日打卡详情
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getDayAttendance(Map<String, String> param) {
		int depid=Integer.valueOf(param.get("depid"));
		String attendTime=param.get("attendTime");
		JsonData jsonData = new JsonData();
		try {
			List<User> userList=attendanceDao.getDayAttendance(depid);
			for(int i=0;i<userList.size();i++){
				String formatTime=attendanceDao.getAttendancego(userList.get(i).getUid(),attendTime);
				String formatTime1=attendanceDao.getAttendanceoff(userList.get(i).getUid(),attendTime);
				userList.get(i).setGoWorkTime(formatTime);
				if(formatTime1!=null){
					userList.get(i).setOffTime(formatTime1);
				}else{
					userList.get(i).setOffTime("");
				}
			}
			jsonData.setData(userList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询打卡情况失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询个人打卡情况
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getOwnAttendance(Map<String, String> param) {
		int userId=Integer.valueOf(param.get("userId"));
		JsonData jsonData = new JsonData();
		try {
			List<Attendance> AttendanceList=attendanceDao.getOwnAttendance(userId);
			for(int i=0;i<AttendanceList.size();i++){
				String goWorkTime=attendanceDao.getTwoAttendance(userId,AttendanceList.get(i).getFormatTime());
				String offTime=attendanceDao.getThreeAttendance(userId,AttendanceList.get(i).getFormatTime());
				AttendanceList.get(i).setGoWorkTime(goWorkTime);
				AttendanceList.get(i).setOffTime(offTime);
			}
			jsonData.setData(AttendanceList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询打卡情况失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getBusinessLtdlog(Map<String, String> param) {
		int busid=Integer.valueOf(param.get("busid"));
		JsonData jsonData = new JsonData();
		try {
			Map<String,Object> businessMap=businessDao.getLatitudeAndLongitude(busid);
			jsonData.setData(businessMap);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询打卡情况失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getRanking(Map<String, String> param) {
		int depid=Integer.valueOf(param.get("depid"));
		int busid=Integer.valueOf(param.get("busid"));
		int roleId=Integer.valueOf(param.get("roleId"));
		JsonData jsonData = new JsonData();
		List<User> userList=new ArrayList<User>();
		try {
			Role role=roleDao.getUserRole(roleId);
			if(role.getCanIssueTask()==1 && role.getCanSeeSale()==1 && role.getCanSeeAttendance()==1){
				userList=userDao.getuseList(busid,0);
				for(int i=0; i<userList.size(); i++){
					int count=attendanceDao.getRanking(userList.get(i).getUid());
					userList.get(i).setAttendanceCount(count);
				}
			}else{
				userList=userDao.getuseList(busid,depid);
				for(int i=0; i<userList.size(); i++){
					int count=attendanceDao.getRanking(userList.get(i).getUid());
					userList.get(i).setAttendanceCount(count);
				}
			}
			jsonData.setData(userList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询打卡情况失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询商户经纬度
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getDepartRanking(Map<String, String> param) {
		int depid=Integer.valueOf(param.get("depid"));
		int busid=Integer.valueOf(param.get("busid"));
		String monthTime=param.get("monthTime");
		String beforeTime=param.get("beforeTime");
		String afterTime=param.get("afterTime");
		JsonData jsonData = new JsonData();
		List<User> userList=new ArrayList<User>();
		try {
			userList=userDao.getuseList(busid,depid);
			if(!monthTime.equals("null")){
				for(int i=0; i<userList.size(); i++){
					int count=attendanceDao.getDepartRanking(userList.get(i).getUid(),monthTime);
					userList.get(i).setAttendanceCount(count);
				}
			}else{
				for(int i=0; i<userList.size(); i++){
					int count=attendanceDao.getDepartdayRanking(userList.get(i).getUid(),beforeTime,afterTime);
					userList.get(i).setAttendanceCount(count);
				}
			}
			jsonData.setData(userList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询打卡情况失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
