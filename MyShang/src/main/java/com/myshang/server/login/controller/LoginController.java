package com.myshang.server.login.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.login.service.LoginService;

/**
 * 标签控制器
 * 
 * @author HL
 * 
 */

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping("/login/loginAction")
	public JsonData login(@RequestParam Map<String, String> param) {
		return loginService.login(param);
}
}
