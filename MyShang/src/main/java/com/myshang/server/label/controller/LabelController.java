package com.myshang.server.label.controller;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.label.model.Label;
import com.myshang.server.label.service.LabelService;

/**
 * 标签控制器
 * 
 * @author HL
 * 
 */

@RestController
public class LabelController {

	
	@Autowired
	LabelService labelService;
	
	@RequestMapping("/label/getLabelList")
	public JsonData getLabelList() {
		return labelService.getLabelList();
	}
	
	@RequestMapping("/label/addLabel")
	public JsonData addLabel(@RequestParam Map<String, String> param) {
		return labelService.addLabel(param);
	}
	@RequestMapping("/label/updateLabel")
	public JsonData updateLabel(@RequestParam Map<String, String> param) {
		return labelService.updateLabel(param);
	}
	@RequestMapping("/label/deleteLabel")
	public JsonData deleteLabel(@RequestParam Map<String, String> param) {
		return labelService.deleteLabel(param);
	}
}
