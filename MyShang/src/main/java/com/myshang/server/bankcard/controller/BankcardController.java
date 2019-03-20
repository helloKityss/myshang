package com.myshang.server.bankcard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.account.service.AccountService;
import com.myshang.server.attendance.service.AttendanceService;
import com.myshang.server.bankcard.service.BankcardService;
import com.myshang.server.common.JsonData;
import com.myshang.server.role.service.RoleService;


/**
 * @author zhangkang
 * 2018年3月5日 上午10:25:21
 */
@RestController
public class BankcardController {
	@Autowired
	private BankcardService bankcardService;
	/**
	 * 绑定卡
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/bankcard/addBankcard")
	public JsonData addBankcard(@RequestParam Map<String, String> param){
		return bankcardService.addBankcard(param);
	}
	/**
	 * 查询是否绑定银行卡
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@RequestMapping("/bankcard/getBankcard")
	public JsonData getBankcard(@RequestParam Map<String, String> param){
		return bankcardService.getBankcard(param);
	}
}
