package com.myshang.server.qrcode.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.myshang.server.common.CmsConstants;
import com.myshang.server.common.JsonData;
import com.myshang.server.common.QRCodeUtil;
import com.myshang.server.common.StringUtil;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.label.model.Label;
import com.myshang.server.label.service.LabelService;
import com.myshang.server.qrcode.model.Qrcode;
import com.myshang.server.qrcode.service.QrcodeService;


/**
 * 标签控制器
 * 
 * @author HL
 * 
 */

@RestController
public class QrcodeController {
	String Meiyuurl = CmsConstants.WEIXIN_URL;// "https://www.meiyumeet.com/"
	private static final Logger LOGGER = Logger.getLogger(Qrcode.class);
	@Autowired
	QrcodeService qrcodeService;
	/**
	 * 查询打赏二维码
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@RequestMapping("/qrcode/getqrcode")
	public JsonData getqrcode(@RequestParam Map<String, String> param){
		return qrcodeService.getqrcode(param);
	}
	/**
	 * 查询打赏二维码
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @return
	 */
	@RequestMapping("/qrcode/getqrcodeAll")
	public JsonData getqrcodeAll(@RequestParam Map<String, String> param){
		return qrcodeService.getqrcodeAll(param);
	}
	@RequestMapping("/qrcode/saveQRcode")
	public JSONObject saveQRcode() {
        JSONObject jsonObject = new JSONObject();
        try {
            QRCodeUtil qrCodeUtil = new QRCodeUtil();
            String result = "";
            int qrcid=qrcodeService.getqrcodeId();
            for (int i = qrcid+1; i <= qrcid+1000; i++) {
                String path;
//					content = shopid+"-"+type+"-考勤-"+(i+1);
//					path = dowloadUrl+checkPath;
                	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                    path =request.getSession().getServletContext().getRealPath("/qrcodeImg");
                    String qrcodeUrl="/MyShang/reward/getStaticCode?qrcid="+i;
                    String qRcodeInfo = qrCodeUtil.createQRcode(path,Meiyuurl+"MyShang/reward/getStaticCode?qrcid="+i, i,260, 260);
//					String chatimgurl = qRcodeInfo.toString().split("\\*")[0];
                    String chatimgurl = "qrcodeImg" + "/"+i + ".png";
        			Qrcode qrcode=new Qrcode();
        			qrcode.setQrcodeImg(chatimgurl);
        			qrcode.setQrcodeUrl(qrcodeUrl);
        			qrcode.setHasBind(0);
        			qrcode.setBelongBusid(0);
        			qrcode.setBindUserId(0);
                    qrcodeService.saveQRcode(qrcode);
                    jsonObject.put("url", chatimgurl);
            }
            //exportQrcode();
            jsonObject.put("code", 1);
            jsonObject.put("msg", result);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return jsonObject;
    }
}
