package com.myshang.server.roomRecord.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myshang.server.common.JsonData;
import com.myshang.server.roomRecord.service.RoomRecordService;

@RestController
public class RoomRecordController {
	@Autowired
	private RoomRecordService roomRecordService;
}
