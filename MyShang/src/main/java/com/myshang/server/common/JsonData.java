package com.myshang.server.common;

import java.math.BigDecimal;
import java.util.List;

import com.myshang.server.consts.CodeEnum;
import com.myshang.server.role.model.Role;


public class JsonData {
	private int code;
	private String msg;
	private Object data;
	private Object list;
	public static final int STATUS_OK = 200;
	public static final int STATUS_ERROR = 500;
	
	public JsonData (){
		this.code = CodeEnum.ERROR.getCode();
		this.msg = "";
	}
	
	public JsonData (int code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public JsonData (int code,String msg,Object data, Object list){
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.list = list;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}
	
	public void setInfo(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public void setCodeEnum(CodeEnum codeEnum){
		this.code = codeEnum.getCode();
		this.msg = codeEnum.getMsg();
	}

}
