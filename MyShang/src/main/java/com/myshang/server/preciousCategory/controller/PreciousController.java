package com.myshang.server.preciousCategory.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.preciousCategory.service.PreciousService;
import com.myshang.server.role.service.RoleService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class PreciousController {
	@Autowired
	private PreciousService peciousService;
	/**
	 * 根据产品id查询商品信息
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/PreciousCategory/getPreciousinformation")
	public JsonData getPreciousinformation(@RequestParam Map<String, String> param){
		return peciousService.getPreciousinformation(param);
	}
}
