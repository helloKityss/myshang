package com.myshang.server.role.service;

import java.util.Map;

import com.myshang.server.common.JsonData;
import com.myshang.server.role.model.Role;

/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
public interface RoleService {
	/**
	 * 查询用户
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData getRole(Map<String, String> param);
	/**
	 * 查询用户权限
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public Role getRoleInput(int belongRolid);
	/**
	 * 新增角色信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData saveRole(Map<String, String> param);
	/**
	 * 删除角色信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData deleteRole(Map<String, String> param);
	/**
	 * 修改角色信息
	 * @author zhangkang
	 * 2018年4月12日 上午10:52:24
	 * @param param
	 * @return
	 */
	public JsonData updateRole(Map<String, String> param);
	
}
