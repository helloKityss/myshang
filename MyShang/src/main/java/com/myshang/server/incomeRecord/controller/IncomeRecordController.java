package com.myshang.server.incomeRecord.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.incomeRecord.service.IncomeRecordService;
import com.myshang.server.role.service.RoleService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class IncomeRecordController {
	@Autowired
	private IncomeRecordService incomeRecordService;
	/**
	 * 查询收入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/incomeRecord/getincomeRecord")
	public JsonData getincomeRecord(@RequestParam Map<String, String> param){
		return incomeRecordService.getincomeRecord(param);
	}
	/**
	 * 查询员工好评人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/incomeRecord/getPraise")
	public JsonData getPraise(@RequestParam Map<String, String> param){
		return incomeRecordService.getPraise(param);
	}
	/**
	 * 查询员工好评人数
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/incomeRecord/getPraiseSum")
	public JsonData getPraiseSum(@RequestParam Map<String, String> param){
		return incomeRecordService.getPraiseSum(param);
	}
}
