package com.myshang.server.distribution.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.distribution.service.DistributionService;
import com.myshang.server.label.service.LabelService;

@RestController
public class DistributionController {
	@Autowired
	DistributionService distributionService;
	/*
	 * 新增分成比例
	 */
	@RequestMapping("/distribution/savedistribution")
	public JsonData savedistribution(@RequestParam Map<String, String> param) {
		return distributionService.savedistribution(param);
	}
	/*
	 * 查询分成比例
	 */
	@RequestMapping("/distribution/getdistribution")
	public JsonData getdistribution(@RequestParam Map<String, String> param) {
		return distributionService.getdistribution(param);
	}
	/*
	 * 查询分成比例
	 */
	@RequestMapping("/distribution/getdistributionId")
	public JsonData getdistributionId(@RequestParam Map<String, String> param) {
		return distributionService.getdistributionId(param);
	}
	/*
	 * 查询分成比例
	 */
	@RequestMapping("/distribution/deletedistribution")
	public JsonData deletedistribution(@RequestParam Map<String, String> param) {
		return distributionService.deletedistribution(param);
	}
	/*
	 * 查询分成比例
	 */
	@RequestMapping("/distribution/updatedistribution")
	public JsonData updatedistribution(@RequestParam Map<String, String> param) {
		return distributionService.updatedistribution(param);
	}
	/*
	 * 查询分成比例
	 */
	@RequestMapping("/distribution/getbusdistribution")
	public JsonData getbusdistribution(@RequestParam Map<String, String> param) {
		return distributionService.getbusdistribution(param);
	}
	/*
	 * 查询分成比例
	 */
	@RequestMapping("/distribution/updatbusedistribution")
	public JsonData updatbusedistribution(@RequestParam Map<String, String> param) {
		return distributionService.updatbusedistribution(param);
	}
}
