package com.myshang.server.distribution.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.business.dao.BusinessDao;
import com.myshang.server.business.model.Business;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.distribution.dao.DistributionDao;
import com.myshang.server.distribution.model.Distribution;
import com.myshang.server.distribution.service.DistributionService;
import com.myshang.server.label.model.Label;

@Service
@Transactional
public class DistributionServiceImpl implements DistributionService{
	private static final Logger LOGGER = Logger.getLogger(DistributionServiceImpl.class);
	@Autowired
	DistributionDao distributionDao;
	@Autowired
	BusinessDao businessDao;
	/*
	 * 新增分成比例
	 */
	@Override
	public JsonData savedistribution(Map<String, String> param) {
	JsonData jsonData = new JsonData();
	int belongBusid=Integer.valueOf(param.get("belongBusid"));
	int platform=Integer.valueOf(param.get("platform"));
	int extend=Integer.valueOf(param.get("extend"));
	try {

		jsonData.setCodeEnum(CodeEnum.SUCCESS);
	} catch (Exception e) {
		LOGGER.error("查询标签出错",e);
		jsonData.setCodeEnum(CodeEnum.ERROR);
	}
	return jsonData;
	}
	/*
	 * 查询分成比例
	 */
	@Override
	public JsonData getdistribution(Map<String, String> param) {
	JsonData jsonData = new JsonData();
	try {
		List<Map<String,Object>> list=distributionDao.getdistributions();
		List<Map<String,Object>> businessList=businessDao.getbusiness();
		jsonData.setData(list);
		jsonData.setList(businessList);
		jsonData.setCodeEnum(CodeEnum.SUCCESS);
	} catch (Exception e) {
		LOGGER.error("查询分成比例失败",e);
		jsonData.setCodeEnum(CodeEnum.ERROR);
	}
	return jsonData;
	}
	/*
	 * 查询分成比例
	 */
	@Override
	public JsonData getdistributionId(Map<String, String> param) {
	JsonData jsonData = new JsonData();
	int distid=Integer.valueOf(param.get("distid"));
	try {
		List<Distribution> list=distributionDao.getdistributionId(distid);
		jsonData.setData(list);
		jsonData.setCodeEnum(CodeEnum.SUCCESS);
	} catch (Exception e) {
		LOGGER.error("查询分成比例失败",e);
		jsonData.setCodeEnum(CodeEnum.ERROR);
	}
	return jsonData;
	}
	/*
	 * 查询分成比例
	 */
	@Override
	public JsonData deletedistribution(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		String distids=param.get("distid");
		try {
			String substring = distids.substring(0, distids.length());//截取最后一个
			LOGGER.error("截取投诉标签字符串后"+substring);
			String[] split = substring.split(",");//以逗号分割
			for(int i = 0; i<split.length;i++){
				int distid=Integer.valueOf(split[i]);
				distributionDao.deletedistribution(distid);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询分成比例失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
		}
	/*
	 * 查询分成比例
	 */
	@Override
	public JsonData updatedistribution(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int distid=Integer.valueOf(param.get("distid"));
		int platform=Integer.valueOf(param.get("platform"));
		int extend=Integer.valueOf(param.get("extend"));
		try {
			Distribution distribution=new Distribution();
			distribution.setDistid(distid);
			distribution.setExtend(extend*10);
			distribution.setPlatform(platform*10);
			distributionDao.updatedistribution(distribution);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("修改平台分成比例失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
		}
	/*
	 * 查询分成比例
	 */
	@Override
	public JsonData updatbusedistribution(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int distid=Integer.valueOf(param.get("distid"));
		int business=Integer.valueOf(param.get("business"));
		int internal=Integer.valueOf(param.get("internal"));
		int mami=Integer.valueOf(param.get("mami"));
		int princess=Integer.valueOf(param.get("princess"));
		try {
			Distribution distribution=new Distribution();
			distribution.setDistid(distid);
			distribution.setInternal(internal*10);
			distribution.setMami(mami*10);
			distribution.setPrincess(princess*10);
			distribution.setBusiness(business*10);
			distributionDao.updatbusedistribution(distribution);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("修改商户分成比例失败",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
		}
	/*
	 * 查询分成比例
	 */
	@Override
	public JsonData getbusdistribution(Map<String, String> param) {
	JsonData jsonData = new JsonData();
	int busid=Integer.valueOf(param.get("busid"));
	try {
		List<Distribution> list=distributionDao.getbusdistribution(busid);
		jsonData.setData(list);
		jsonData.setCodeEnum(CodeEnum.SUCCESS);
	} catch (Exception e) {
		LOGGER.error("查询分成比例失败",e);
		jsonData.setCodeEnum(CodeEnum.ERROR);
	}
	return jsonData;
	}
}
