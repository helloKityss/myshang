package com.myshang.server.qrcode.dao;

import java.util.HashMap;
import java.util.List;

import com.myshang.server.qrcode.model.Qrcode;

public interface QrcodeDao {

	/**
	 * 根据二维码id查询相关数据
	 * @param qrcid
	 * @return Qrcode
	 */
	public Qrcode getQrcodeById(int qrcid);	
	
	/**
	 * 根据二维码id更新绑定关系
	 * @param qrcode
	 * @return
	 */
	public int updateQrcodeStatusById(Qrcode qrcode);
	
	/**
	 * 根据绑定人员id查询二维码相关数据
	 * @param qrcode
	 * @return
	 */
	public List<Qrcode> getQrcodeByUid(int bindUserId);
	/**
	 * 根据绑定人员id查询二维码相关数据
	 * @param qrcode
	 * @return
	 */
	public Qrcode getqrcode(int bindUserId);
	/**
	 * 根据绑定人员id查询二维码相关数据
	 * @param qrcode
	 * @return
	 */
	public List<Qrcode> getqrcodeAll(int belongBusid);
	/**
	 * 根据绑定人员id查询二维码相关数据
	 * @param qrcode
	 * @return
	 */
	public void saveQRcode(Qrcode qrcode);
	/**
	 * 根据绑定人员id查询二维码相关数据
	 * @param qrcode
	 * @return
	 */
	public int getqrcodeId();
	/**
	 * 根据绑定人员id查询二维码相关数据
	 * @param qrcode
	 * @return
	 */
	public void deleteqrcode(int uid);
}
