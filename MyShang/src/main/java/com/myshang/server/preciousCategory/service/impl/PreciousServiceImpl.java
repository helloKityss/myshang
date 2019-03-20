package com.myshang.server.preciousCategory.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.preciousCategory.dao.PreciousDao;
import com.myshang.server.preciousCategory.model.Precious;
import com.myshang.server.preciousCategory.service.PreciousService;
import com.myshang.server.preciousSale.model.PreciousSale;
@Service
@Transactional
public class PreciousServiceImpl implements PreciousService {
	@Autowired
	private PreciousDao preciousdao;
	@Autowired
	private static final Logger LOGGER = Logger.getLogger(DepartmentServiceImpl.class);
	/**
	 * 查询角色信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData getPreciousinformation(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int preid=Integer.valueOf(param.get("preid"));
		int belongBusid=Integer.valueOf(param.get("belongBusid"));
		try {
			Precious precious=preciousdao.getPreciousinformation(preid,belongBusid);
			jsonData.setData(precious);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询部门或角色出错出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
