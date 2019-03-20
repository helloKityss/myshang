package com.myshang.server.department.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.Incomebusiness.dao.IncomeBusinessDao;
import com.myshang.server.Incomebusiness.service.IncomeBusinessService;
import com.myshang.server.attendance.dao.AttendanceDao;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.dao.DepartmentDao;
import com.myshang.server.department.model.Department;
import com.myshang.server.department.service.DepartmentService;
import com.myshang.server.reward.service.RewardService;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.roomRecord.dao.RoomRecordDao;
import com.myshang.server.roomRuning.dao.RoomRuningDao;
import com.myshang.server.roomRuning.service.RoomRuningService;
import com.myshang.server.user.controller.UserController;
import com.myshang.server.user.dao.UserDao;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentdao;
	@Autowired
	private UserDao userdao;
	@Autowired
	private AttendanceDao attendancedao;
	@Autowired
	private RoleDao roledao;
	@Autowired
	private RoomRuningService roomRuningService;
	@Autowired
	private IncomeBusinessService incomeBusinessService;
	@Autowired
	RewardService rewardService;
	
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	@Override
	public JsonData getDepartment(Map<String, String> param) {
		String busid=param.get("busid");
		JsonData jsonData = new JsonData();
		try {
			List<Department> list = departmentdao.getDepartment(busid);
			jsonData.setData(list);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询所有部门名称及总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@Override
	public JsonData getDepartmentAll(Map<String, String> param) {
		int busid=Integer.valueOf(param.get("busid"));
//		String attendTime=param.get("attendTime");
//		int depid=Integer.valueOf(param.get("depid"));
//		int roleId=Integer.valueOf(param.get("roleId"));
		JsonData jsonData = new JsonData();
//		List<Department> list=new ArrayList<Department>();
		int staffCount=0;
		int PunchCount=0;
		HashMap<String, Object> ModuleNameMap= new HashMap<String, Object>();
		try {
//			Role role=roledao.getUserRole(roleId);
//			if(role.getCanIssueTask()==1 && role.getCanSeeSale()==1 && role.getCanSeeAttendance()==1){
//				list = departmentdao.getDepartmentAll(busid,0);
				staffCount=userdao.getstaffCount(busid,0);
				PunchCount=attendancedao.getPunchCount(busid,0);
//			}else{
//				list = departmentdao.getDepartmentAll(busid,depid);	
//				staffCount=userdao.getstaffCount(busid,depid);
//				PunchCount=attendancedao.getPunchCount(busid,depid);
//			}
//			for(int i=0;i<list.size();i++){
//				int deoartCount=userdao.getdepartCount(list.get(i).getDepid());
//				int clockCount=attendancedao.getAttendanceCount(list.get(i).getDepid(),attendTime);
//				list.get(i).setDepartNumber(deoartCount);
//				list.get(i).setClockNumber(clockCount);
//			}
//			jsonData.setData(list);
			ModuleNameMap.put("AttendanceInformation", "考勤信息");
			ModuleNameMap.put("AttendanceStatistics", "考勤统计");
			ModuleNameMap.put("AttendanceToday", "今日出勤人数");
			ModuleNameMap.put("TotalNumber", "员工总数");
			Map map=new HashMap();
			map.put("staffCount", staffCount);
			map.put("PunchCount", PunchCount);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 新增部门信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@Override
	public JsonData saveDepartment(Map<String, String> param) {
		String departName=param.get("departName");
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		JsonData jsonData = new JsonData();
		try {
			Department department=new Department();
			department.setDepartName(departName);
			department.setBelongBusid(belongBusid);
			departmentdao.saveDepartment(department);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增部门出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 新增部门信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@Override
	public JsonData deleteDepartment(Map<String, String> param) {
		int depid=Integer.valueOf(param.get("depid"));
		JsonData jsonData = new JsonData();
		try {
			departmentdao.deleteDepartment(depid);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增部门出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 新增部门信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@Override
	public JsonData updateDepartment(Map<String, String> param) {
		int depid=Integer.valueOf(param.get("depid"));
		String departName=param.get("departName");
		JsonData jsonData = new JsonData();
		try {
			departmentdao.updateDepartment(depid,departName);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增部门出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询总经理首页信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@Override
	public JsonData getInformation(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		try {
			Map map=new HashMap();
			map.put("Attendance", getDepartmentAll(param));
			map.put("room", roomRuningService.queryRoom(param));
			map.put("income", incomeBusinessService.selectIncome(param));
			map.put("reward", rewardService.queryRewardMoney(param));
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
