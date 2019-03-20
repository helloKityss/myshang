package com.myshang.server.third.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.third.service.ThirdService;

@RestController
public class ThirdController {
	@Autowired
	private ThirdService thirdService;
	
	@RequestMapping("/third/sendSms")
	public JsonData sendSms(@RequestParam Map<String, String> param){
		return thirdService.sendSms(param);
	}
	
	@RequestMapping("/third/verifyCode")
	public JsonData verifyCode(@RequestParam Map<String, String> param){
		return thirdService.verifyCode(param);
	}
}
