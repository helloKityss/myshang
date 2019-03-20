package com.myshang.server.common;

import java.util.List;

import com.myshang.server.consts.CodeEnum;


public class JsonList<T> {
	private int code;
	private String msg;
	private List<T> Tlist;
	
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
	public void setInfo(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public void setCodeEnum(CodeEnum codeEnum){
		this.code = codeEnum.getCode();
		this.msg = codeEnum.getMsg();
	}

	public List<T> getTlist() {
		return Tlist;
	}

	public void setTlist(List<T> tlist) {
		Tlist = tlist;
	}
}
