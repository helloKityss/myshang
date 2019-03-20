package com.myshang.server.role.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myshang.server.role.model.Role;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface RoleDao {
	/**
	 * 查询用户角色
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<Role> getRole(String busid);
	/**
	 * 查询用户角色id
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getRoleId(int busid);
	/**
	 * 查询用户角色id
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public int getcanInternalRun(int busid);
	/**
	 * 查询用户角色
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public Role getRoleInput(int belongRolid);
	/**
	 * 查询用户角色
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public Role getUserRole(int rolid);
	/**
	 * 通过用户id查角色
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public Role getRoleName(int rolid);
	/**
	 * 通过uid查询角色
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public Role getRoleUid(int uid);
	/**
	 * 新增角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public void saveRole(Role role);
	/**
	 * 删除角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public void deleteRole(int rolid);
	/**
	 * 修改角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param
	 * @return
	 */
	public void updateRole(Role role);
}
