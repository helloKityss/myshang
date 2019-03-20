package com.myshang.server.label.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.label.dao.LabelDao;
import com.myshang.server.label.model.Label;
import com.myshang.server.label.service.LabelService;



@Service
@Transactional
public class LabelServiceImpl implements LabelService {
	private static final Logger LOGGER = Logger.getLogger(LabelServiceImpl.class);
	@Autowired
	LabelDao labelDao;
	
	@Override
	public JsonData getLabelList() {
	JsonData jsonData = new JsonData();
	try {
		List<Label> labelList= labelDao.getLabelList();
		List<Label> LabelRewardList= labelDao.getLabelRewardList();
		List<Label> EvaluateList= labelDao.getLabelEvaluateList();
		Map map=new HashMap();
		map.put("labelList",labelList);
		map.put("LabelRewardList",LabelRewardList);
		map.put("EvaluateList",EvaluateList);
		jsonData.setData(map);
		jsonData.setCodeEnum(CodeEnum.SUCCESS);
	} catch (Exception e) {
		LOGGER.error("查询标签出错",e);
		jsonData.setCodeEnum(CodeEnum.ERROR);
	}
	return jsonData;
	}
	/*
	 * 新增标签
	 */
	@Override
	public JsonData addLabel(Map<String, String> param) {
		String labelName=param.get("labelName");
		int labelType=Integer.valueOf(param.get("labelType"));
		String value=param.get("value");
		JsonData jsonData = new JsonData();
		try {
			Label label=new Label();
			label.setLabelName(labelName);
			label.setLabelType(labelType);
			label.setValue(new BigDecimal(value));;
			labelDao.addLabel(label);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("新增标签失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 修改标签
	 */
	@Override
	public JsonData updateLabel(Map<String, String> param) {
		int labid=Integer.valueOf(param.get("labid"));
		String labelName=param.get("labelName");
		int labelType=Integer.valueOf(param.get("labelType"));
		String value=param.get("value");
		JsonData jsonData = new JsonData();
		try {
			Label label=new Label();
			label.setLabid(labid);
			label.setLabelName(labelName);
			label.setLabelType(labelType);
			label.setValue(new BigDecimal(value));;
			labelDao.updateLabel(label);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("新增标签失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/*
	 * 修改标签
	 */
	@Override
	public JsonData deleteLabel(Map<String, String> param) {
		String labids=param.get("labid");
		JsonData jsonData = new JsonData();
		try {
			String substring = labids.substring(0, labids.length());//截取最后一个
			LOGGER.error("截取投诉标签字符串后"+substring);
			String[] split = substring.split(",");//以逗号分割
			for(int i = 0; i<split.length;i++){
				int labid=Integer.valueOf(split[i]);
				labelDao.deleteLabel(labid);
			}
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("新增标签失败", e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
