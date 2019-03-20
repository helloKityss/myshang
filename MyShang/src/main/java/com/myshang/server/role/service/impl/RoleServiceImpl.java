package com.myshang.server.role.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.dao.DepartmentDao;
import com.myshang.server.department.model.Department;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.preciousSale.model.PreciousSale;
import com.myshang.server.role.dao.RoleDao;
import com.myshang.server.role.model.Role;
import com.myshang.server.role.service.RoleService;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roledao;
	@Autowired
	private DepartmentDao departmentDao;
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	/**
	 * 查询角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getRole(Map<String, String> param) {
		String busid=param.get("busid");
		JsonData jsonData = new JsonData();
		try {
			List<Role> RoleList=roledao.getRole(busid);
			List<Department> departmentList=departmentDao.getDepartment(busid);
			jsonData.setList(departmentList);
			jsonData.setData(RoleList);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门或角色出错出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public Role getRoleInput(int belongRolid) {
		Role roleType=roledao.getRoleInput(belongRolid);
		return roleType;
	}
	/**
	 * 新增角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData saveRole(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		String roleName=param.get("roleName");
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		int belongDepid=Integer.valueOf(param.get("belongDepid"));
		int canProxyBusiness=Integer.valueOf(param.get("canProxyBusiness"));
		int canIssueTask=Integer.valueOf(param.get("canIssueTask"));
		int canSeeSale=Integer.valueOf(param.get("canSeeSale"));
		int canSeeRoomIncome=Integer.valueOf(param.get("canSeeRoomIncome"));
		int canSeeAttendance=Integer.valueOf(param.get("canSeeAttendance"));
		int canToReward=Integer.valueOf(param.get("canToReward"));
		int canBindPrincess=Integer.valueOf(param.get("canBindPrincess"));
		int canOpenRoom=Integer.valueOf(param.get("canOpenRoom"));
		int canBindPrinToRoom=Integer.valueOf(param.get("canBindPrinToRoom"));
		int canBeBindToRoom=Integer.valueOf(param.get("canBeBindToRoom"));
		int canInputWine=Integer.valueOf(param.get("canInputWine"));
		int canInputFood=Integer.valueOf(param.get("canInputFood"));
		int canInternalRun=Integer.valueOf(param.get("canInternalRun"));
		try {
			Role role=new Role();
			role.setRoleName(roleName);
			role.setBelongBusid(belongBusid);
			role.setBelongDepid(belongDepid);
			role.setCanBeBindToRoom(canBeBindToRoom);
			role.setCanBindPrincess(canBindPrincess);
			role.setCanBindPrinToRoom(canBindPrinToRoom);
			role.setCanInputFood(canInputFood);
			role.setCanInputWine(canInputWine);
			role.setCanIssueTask(canIssueTask);
			role.setCanOpenRoom(canOpenRoom);
			role.setCanProxyBusiness(canProxyBusiness);
			role.setCanSeeAttendance(canSeeAttendance);
			role.setCanSeeRoomIncome(canSeeRoomIncome);
			role.setCanSeeSale(canSeeSale);
			role.setCanToReward(canToReward);
			role.setCanInternalRun(canInternalRun);
			roledao.saveRole(role);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增角色信息失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 删除角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData deleteRole(Map<String, String> param) {
		int RoleId=Integer.valueOf(param.get("RoleId"));
		JsonData jsonData = new JsonData();
		try {
			roledao.deleteRole(RoleId);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("删除角色信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 修改角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData updateRole(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int rolid=Integer.valueOf(param.get("rolid"));
		String roleName=param.get("roleName");
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		int belongDepid=Integer.valueOf(param.get("belongDepid"));
		int canProxyBusiness=Integer.valueOf(param.get("canProxyBusiness"));
		int canIssueTask=Integer.valueOf(param.get("canIssueTask"));
		int canSeeSale=Integer.valueOf(param.get("canSeeSale"));
		int canSeeRoomIncome=Integer.valueOf(param.get("canSeeRoomIncome"));
		int canSeeAttendance=Integer.valueOf(param.get("canSeeAttendance"));
		int canToReward=Integer.valueOf(param.get("canToReward"));
		int canBindPrincess=Integer.valueOf(param.get("canBindPrincess"));
		int canOpenRoom=Integer.valueOf(param.get("canOpenRoom"));
		int canBindPrinToRoom=Integer.valueOf(param.get("canBindPrinToRoom"));
		int canBeBindToRoom=Integer.valueOf(param.get("canBeBindToRoom"));
		int canInputWine=Integer.valueOf(param.get("canInputWine"));
		int canInputFood=Integer.valueOf(param.get("canInputFood"));
		int canInternalRun=Integer.valueOf(param.get("canInternalRun"));
		try {
			Role role=new Role();
			role.setRolid(rolid);
			role.setRoleName(roleName);
			role.setBelongBusid(belongBusid);
			role.setBelongDepid(belongDepid);
			role.setCanBeBindToRoom(canBeBindToRoom);
			role.setCanBindPrincess(canBindPrincess);
			role.setCanBindPrinToRoom(canBindPrinToRoom);
			role.setCanInputFood(canInputFood);
			role.setCanInputWine(canInputWine);
			role.setCanIssueTask(canIssueTask);
			role.setCanOpenRoom(canOpenRoom);
			role.setCanProxyBusiness(canProxyBusiness);
			role.setCanSeeAttendance(canSeeAttendance);
			role.setCanSeeRoomIncome(canSeeRoomIncome);
			role.setCanSeeSale(canSeeSale);
			role.setCanToReward(canToReward);
			role.setCanInternalRun(canInternalRun);
			roledao.updateRole(role);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增角色信息失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
