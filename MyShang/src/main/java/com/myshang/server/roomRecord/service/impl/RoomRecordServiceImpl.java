package com.myshang.server.roomRecord.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.preciousSale.dao.PreciousSaleDao;
import com.myshang.server.preciousSale.model.PreciousSale;
import com.myshang.server.roomRecord.dao.RoomRecordDao;
import com.myshang.server.roomRecord.model.RoomRecord;
import com.myshang.server.roomRecord.service.RoomRecordService;
@Service
@Transactional
public class RoomRecordServiceImpl implements RoomRecordService{
	@Autowired
	private RoomRecordDao roomRecordDao;
	private static final Logger LOGGER = Logger.getLogger(RoomRecordServiceImpl.class);

}
