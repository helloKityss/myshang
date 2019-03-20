package com.myshang.server.consts;

/**
 * 返回信息代码列举类
 * @author zhangkang
 * 2017年11月22日 下午5:16:03
 */
public enum CodeEnum {
	
	/**
	 * 系统定义返回码
	 */
	ERROR(-1,"执行请求出错"),	//请求失败
	SUCCESS(0,"SUCCESS"), //请求成功
	LOGIN_ERROR(4,"密码不正确"), //请求成功
	USER_ERROR(5,"用户不存在"),
	ERROR_DISTANCE(1,"不在打卡范围"),
	ERROR_MANAGER(9,"商户总经理角色或内推人角色已被注册"),
	ERROR_HASBIND(99,"用户没有绑定打赏二维码"),
	ERROR_ROOM(6,"房间不存在"),
	ERROR_DEPARTMENT(20,"没有对应的部门或角色"),
	ERROR_TOKEN(1,"token验证失败"),
	ERROR_AUTOGRAPH(1,"签名失败"),
	ERROR_IMAGES(66,"图片不存在"),
	ERROR_TRANSFER(22,"调用微信提现接口失败"),
	ERROR_LOGIN_USER(77,"账号已存在"),
	ERROR_IMAGESTO(77,"图片已存在"),
	ERROR_JURISDICTION(15,"没有此权限"),
	CODE_VALID_FAIL(25, "短信验证失败"),
	USER_NOT_EXISTS(14,"用户不存在"),
	VALID_FAIL(3,"验证失败,请检查手机号码或密码"),
	USER_AREADY_REGISTER(2,"用户已注册");

	
	private int code;
	private String msg;
	
	private CodeEnum(int code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
