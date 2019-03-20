package com.myshang.server.preciousSale.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.preciousCategory.service.PreciousService;
import com.myshang.server.preciousSale.service.PreciousSaleService;
import com.myshang.server.role.service.RoleService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class PreciousSaleController {
	@Autowired
	private PreciousSaleService preciousSaleService;
	/**
	 * 查询大件录入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/Precious/createPreciousSale")
	public JsonData createPreciousSale(@RequestParam Map<String, String> param){
		return preciousSaleService.createPreciousSale(param);
	}
	/**
	 * 查询大件录入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/Precious/getPreciousSale")
	public JsonData getPreciousSale(@RequestParam Map<String, String> param){
		return preciousSaleService.getPreciousSale(param);
	}
	/**
	 * 查询当天大件录入信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/Precious/getPreciousDaySale")
	public JsonData getPreciousDaySale(@RequestParam Map<String, String> param){
		return preciousSaleService.getPreciousDaySale(param);
	}
	/**
	 * 查询本周出货价钱
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/Precious/getPreciousSaleMoney")
	public JsonData getPreciousSaleMoney(@RequestParam Map<String, String> param){
		return preciousSaleService.getPreciousSaleMoney(param);
	}
	/**
	 * 删除录入大件
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/Precious/deletePreciousSale")
	public JsonData deletePreciousSale(@RequestParam Map<String, String> param){
		return preciousSaleService.deletePreciousSale(param);
	}
}
