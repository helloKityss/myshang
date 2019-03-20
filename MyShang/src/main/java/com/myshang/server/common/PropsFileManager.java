package com.myshang.server.common;

import java.util.ResourceBundle;

/**
 * 系统运行相关的模块和功能应用常量的配置读取实现
 * 
 */
public class PropsFileManager {
	private static PropsFileManager _instance = null;
	private static ResourceBundle _smmsProps = null;

	private PropsFileManager() {
		_smmsProps = getSMMSFile();
	}

	public static PropsFileManager getInstance() {
		if (_instance == null) {
			_instance = new PropsFileManager();
		}
		return _instance;
	}

	private ResourceBundle getSMMSFile() {
		_smmsProps = ResourceBundle.getBundle("com.myshang.server.consts.ad_cms");
		return _smmsProps;
	}

	public String getSMMSFile(String fieldName) {
		return _smmsProps.getString(fieldName);
	}
}
