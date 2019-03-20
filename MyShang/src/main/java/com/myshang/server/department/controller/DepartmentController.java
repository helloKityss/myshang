package com.myshang.server.department.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.department.service.DepartmentService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/department/getDepartment")
	public JsonData accountList(@RequestParam Map<String, String> param){
		return departmentService.getDepartment(param);
	}
	/**
	 * 查询所有部门名称及总人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@RequestMapping("/department/getDepartmentAll")
	public JsonData getDepartmentAll(@RequestParam Map<String, String> param){
		return departmentService.getDepartmentAll(param);
	}
	/**
	 * 新增部门
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@RequestMapping("/department/saveDepartment")
	public JsonData saveDepartment(@RequestParam Map<String, String> param){
		return departmentService.saveDepartment(param);
	}
	/**
	 * 删除部门
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@RequestMapping("/department/deleteDepartment")
	public JsonData deleteDepartment(@RequestParam Map<String, String> param){
		return departmentService.deleteDepartment(param);
	}
	/**
	 * 删除部门
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@RequestMapping("/department/updateDepartment")
	public JsonData updateDepartment(@RequestParam Map<String, String> param){
		return departmentService.updateDepartment(param);
	}
	/**
	 * 删除部门
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	@RequestMapping("/homepage/getInformation")
	public JsonData getInformation(@RequestParam Map<String, String> param){
		return departmentService.getInformation(param);
	}
}
