package com.myshang.server.Incomebusiness.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.Incomebusiness.service.IncomeBusinessService;
import com.myshang.server.common.JsonData;

@RestController
public class IncomeBusinessController {
	@Autowired
	private IncomeBusinessService incomeBusinessService;
	/**
	 * 新增收入记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/incomebusiness/saveIncome")
	public JsonData saveIncome(@RequestParam Map<String, String> param){
		return incomeBusinessService.saveIncome(param);
	}
	/**
	 * 查询商户收入记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/incomebusiness/selectIncome")
	public JsonData selectIncome(@RequestParam Map<String, String> param){
		return incomeBusinessService.selectIncome(param);
	}
}
