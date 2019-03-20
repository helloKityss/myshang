package com.myshang.server.role.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.role.service.RoleService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class RoleController {
	@Autowired
	private RoleService roleService;
	/**
	 * 查询角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/role/getRole")
	public JsonData getRole(@RequestParam Map<String, String> param){
		return roleService.getRole(param);
	}
	/**
	 * 新增角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/role/saveRole")
	public JsonData saveRole(@RequestParam Map<String, String> param){
		return roleService.saveRole(param);
	}
	/**
	 * 删除角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/role/deleteRole")
	public JsonData deleteRole(@RequestParam Map<String, String> param){
		return roleService.deleteRole(param);
	}
	/**
	 * 修改角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/role/updateRole")
	public JsonData updateRole(@RequestParam Map<String, String> param){
		return roleService.updateRole(param);
	}
}
