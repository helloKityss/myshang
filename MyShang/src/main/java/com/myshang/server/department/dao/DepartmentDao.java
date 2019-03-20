package com.myshang.server.department.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myshang.server.department.model.Department;



/**
 * @author zhangkang
 * 2018年6月10日 下午6:49:50
 */
@Repository
public interface DepartmentDao {
	/**
	 * 查询所有部门名称
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<Department> getDepartment(String belongBusid);
	/**
	 * 查询所有部门名称
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public List<Department> getDepartmentAll(@Param("belongBusid")int belongBusid,@Param("depid")int depid);
	/**
	 * 新增部门信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void saveDepartment(Department department);
	/**
	 * 删除部门信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void deleteDepartment(int depid);
	/**
	 * 删除部门信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param houseNo
	 * @return
	 */
	public void updateDepartment(@Param("depid") int depid,@Param("departName") String departName);
}
