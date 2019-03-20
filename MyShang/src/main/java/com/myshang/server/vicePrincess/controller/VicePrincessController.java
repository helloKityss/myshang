package com.myshang.server.vicePrincess.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.vicePrincess.service.VicePrincessService;

/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class VicePrincessController {
	@Autowired
	private VicePrincessService vicePrincessService;
	/**
	 * 副理绑定公主
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/viceprincess/createviceprin")
	public JsonData createviceprin(@RequestParam Map<String, String> param){
		return vicePrincessService.createviceprin(param);
	}
	/**
	 * 副理绑定公主
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/viceprincess/getviceprin")
	public JsonData getviceprin(@RequestParam Map<String, String> param){
		return vicePrincessService.getviceprin(param);
	}
	/**
	 * 副理绑定公主
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/viceprincess/deleteviceprin")
	public JsonData deleteviceprin(@RequestParam Map<String, String> param){
		return vicePrincessService.deleteviceprin(param);
	}
}
