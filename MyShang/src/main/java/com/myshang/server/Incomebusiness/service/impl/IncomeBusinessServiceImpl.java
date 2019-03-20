package com.myshang.server.Incomebusiness.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myshang.server.Incomebusiness.dao.IncomeBusinessDao;
import com.myshang.server.Incomebusiness.model.IncomeBusiness;
import com.myshang.server.Incomebusiness.service.IncomeBusinessService;
import com.myshang.server.common.JsonData;
import com.myshang.server.consts.CodeEnum;
import com.myshang.server.department.service.Impl.DepartmentServiceImpl;
import com.myshang.server.incomeRecord.dao.IncomeRecordDao;
import com.myshang.server.incomeRecord.service.IncomeRecordService;

@Service
@Transactional
public class IncomeBusinessServiceImpl implements IncomeBusinessService{
	@Autowired
	private IncomeBusinessDao incomeBusinessDao;
	private static final Logger LOGGER = Logger.getLogger(IncomeBusinessServiceImpl.class);
	/**
	 * 新增商户收入记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData saveIncome(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int busId=Integer.valueOf(param.get("busId"));
		int type=Integer.valueOf(param.get("type"));
		String incomeMoney=param.get("incomeMoney");
		try {
			IncomeBusiness income=new IncomeBusiness();
			income.setBusId(busId);
			income.setIncomeMoney(new BigDecimal(incomeMoney));
			income.setType(type);
			income.setCreateTime(new Timestamp(System.currentTimeMillis()));
			incomeBusinessDao.saveIncome(income);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
	/**
	 * 查询商户收入记录
	 * @author zhangkang
	 * 2018年4月24日 上午11:12:37
	 * @param param
	 * @return
	 */
	@Override
	public JsonData selectIncome(Map<String, String> param) {
		JsonData jsonData = new JsonData();
		int busId=Integer.valueOf(param.get("busid"));
		HashMap<String, Object> ModuleNameMap= new HashMap<String, Object>();
		try {
			HashMap<String,Object> MoneyData=incomeBusinessDao.selectIncome(busId);
			ModuleNameMap.put("BusinessStatement", "营业报表");
			ModuleNameMap.put("UpdateTime", "更新时间");
			ModuleNameMap.put("StatisticalTime", "统计时间");
			ModuleNameMap.put("TotalIncome", "收益总额");
			ModuleNameMap.put("cash", "现金");
			ModuleNameMap.put("WeChat", "微信");
			ModuleNameMap.put("PayCyCard", "刷卡");
			ModuleNameMap.put("Alipay", "支付宝");
			ModuleNameMap.put("MembershipCard", "会员卡");
			ModuleNameMap.put("RechargeAccount", "充值入账");
			ModuleNameMap.put("ConsumptionAccounting", "消费入账");
			ModuleNameMap.put("AccountingData", "挂账数据");
			ModuleNameMap.put("Payment", "今日挂账还款");
			ModuleNameMap.put("Amount", "今日挂账额");
			ModuleNameMap.put("OtherEarnings", "其它收益额");
			ModuleNameMap.put("EntertainmentExpenses", "招待费用");
			Map map=new HashMap();
			map.put("MoneyData", MoneyData);
			map.put("ModuleNameMap", ModuleNameMap);
			jsonData.setData(map);
			jsonData.setCodeEnum(CodeEnum.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询收入信息出错",e);
			jsonData.setCodeEnum(CodeEnum.ERROR);
		}
		return jsonData;
	}
}
