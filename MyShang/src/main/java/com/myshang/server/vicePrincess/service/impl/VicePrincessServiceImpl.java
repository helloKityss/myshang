package com.myshang.server.vicePrincess.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.dao.DepartmentDao;
import com.myshang.server.department.model.Department;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.role.model.Role;
import com.myshang.server.vicePrincess.dao.VicePrincessDao;
import com.myshang.server.vicePrincess.model.VicePrincess;
import com.myshang.server.vicePrincess.service.VicePrincessService;
@Service
@Transactional
public class VicePrincessServiceImpl implements VicePrincessService{
	@Autowired
	private VicePrincessDao vicePrincessDao;
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	/**
	 * 查询角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData createviceprin(Map<String, String> param) {
		int viceManagerId=Integer.valueOf(param.get("viceManagerId"));
		int bindPrincessId=Integer.valueOf(param.get("bindPrincessId"));
		JsonData jsonData = new JsonData();
		try {
			VicePrincess vicePrincess=new VicePrincess();
			vicePrincess.setViceManagerId(viceManagerId);
			vicePrincess.setBindPrincessId(bindPrincessId);
			vicePrincess.setBindTime(new Timestamp(System.currentTimeMillis()));
			vicePrincessDao.createviceprin(vicePrincess);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("副理绑定公主报错",e);
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
	public JsonData getviceprin(Map<String, String> param) {
		int busid=Integer.valueOf(param.get("busid"));
		JsonData jsonData = new JsonData();
		try {
			List<Map<String,Object>> list=vicePrincessDao.getviceprin(busid);
			jsonData.setData(list);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询副理绑定公主信息报错",e);
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
	public JsonData deleteviceprin(Map<String, String> param) {
		String binids=param.get("binid");
		JsonData jsonData = new JsonData();
		try {
			String substring = binids.substring(0, binids.length());//截取最后一个
			LOGGER.error("截取投诉标签字符串后"+substring);
			String[] split = substring.split(",");//以逗号分割
			for(int i = 0; i<split.length;i++){
				int binid=Integer.valueOf(split[i]);
				vicePrincessDao.deleteviceprin(binid);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询副理绑定公主信息报错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
