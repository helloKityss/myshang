package com.myshang.server.account.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.account.dao.AccountDao;
import com.myshang.server.account.service.AccountService;
import com.myshang.server.common.JsonData;
import com.myshang.server.role.service.RoleService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;
	/**
     * 查询余额
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/account/getaccount")
	public JsonData getaccount(@RequestParam Map<String, String> param) throws Exception{						
		return accountService.getaccount(param);
	}
	/**
     * 查询余额
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping("/account/getUseraccount")
	public JsonData getUseraccount(@RequestParam Map<String, String> param) throws Exception{						
		return accountService.getUseraccount(param);
	}
}
