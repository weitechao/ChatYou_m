package com.yiliao.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.FinancialDetailsService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

/**
 * 财务明细
 * @author Administrator
 *
 */
@Service("financialDetailsService")
public class FinancialDetailsServiceImpl extends ICommServiceImpl implements
		FinancialDetailsService {

	@Override
	public JSONObject getFinancialDetailsList(String beginTime, String endTime,
			int page) {
		JSONObject json   = new JSONObject();
		try {
			 List<String> arbitrarilyDays = null; 
			 
			if(null!=beginTime && !beginTime.trim().isEmpty()  && null!=endTime && !endTime.trim().isEmpty()){
				//验证两个日期之间是否大于10条记录
				if((DateUtils.differentDays(DateUtils.parse(beginTime), DateUtils.parse(endTime))+1) >= 10){
					arbitrarilyDays = DateUtils.arbitrarilyDays(page*10, DateUtils.parse(endTime));
					arbitrarilyDays = arbitrarilyDays.subList((page-1)*10, arbitrarilyDays.indexOf(beginTime)==-1?arbitrarilyDays.size():arbitrarilyDays.indexOf(beginTime)+1);
				}else{
					arbitrarilyDays = DateUtils.arbitrarilyDays((DateUtils.differentDays(DateUtils.parse(beginTime), DateUtils.parse(endTime))+1), DateUtils.parse(endTime));
				}
				
				json.put("total", DateUtils.differentDays(DateUtils.parse(beginTime),DateUtils.parse(endTime))+1);
			}else{
				arbitrarilyDays = DateUtils.arbitrarilyDays(page*10);
				json.put("total", DateUtils.differentDays(DateUtils.parse("2018-07-01"),new Date()));
				arbitrarilyDays = arbitrarilyDays.subList(arbitrarilyDays.size()-10,arbitrarilyDays.size());
			}
			
			List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
			
			String reSql = "SELECT sum(t_recharge_money) AS totalMoney FROM t_recharge WHERE  t_order_state = 1 AND t_payment_type !=2  AND  t_payment_type !=3  AND t_fulfil_time  BETWEEN  ? and ?  ";
			//RMB
			String reSqll = "SELECT sum(t_recharge_money) AS totalMoney FROM t_recharge WHERE  t_order_state = 1 AND t_payment_type !=0  AND  t_payment_type !=1  AND  t_payment_type !=4 AND t_fulfil_time  BETWEEN  ? and ?  ";
			//TB
			String putSql = "SELECT sum(t_money) AS totalMoney FROM t_put_forward WHERE t_order_state =2 AND t_handle_time BETWEEN ? AND ? ";
			//0.支付宝 1.微信 2.pepay 3.Google pay  4. iPhone 内购
			
			Map<String, Object> map = null;
			
			for(String str : arbitrarilyDays){
				
				map = new HashMap<String, Object>();
				map.put("time", str);
				Map<String, Object> resMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(reSql, str+" 00:00:00", str+" 23:59:59");
				
				map.put("income", null==resMap.get("totalMoney")?0:resMap.get("totalMoney")); //人民币
				
				Map<String, Object> resMapTb = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(reSqll, str+" 00:00:00", str+" 23:59:59");
				
				map.put("tbincome", null==resMapTb.get("totalMoney")?0:resMapTb.get("totalMoney")); //台币
				
				Map<String, Object> putMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(putSql, str+" 00:00:00", str+" 23:59:59");
				
				map.put("expenditure", null==putMap.get("totalMoney")?0:putMap.get("totalMoney"));
				
				BigDecimal balance = new BigDecimal(map.get("income").toString()).subtract(new BigDecimal(map.get("expenditure").toString())).setScale(2, BigDecimal.ROUND_DOWN);
				
				map.put("profit", balance);
				
				
                BigDecimal balanceTb = new BigDecimal(map.get("tbincome").toString()).subtract(new BigDecimal(map.get("expenditure").toString())).setScale(2, BigDecimal.ROUND_DOWN);
				
				map.put("profittb", balanceTb);
				
				/*time 统计日期
				 *income 入账金额
				 *expenditure 支出金额
				 *profit  营业收入
				 * 
				 * */
				
				
				//装载数据
				data.add(map);
			}
		
			json.put("rows", data);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取列表异常!", e);
		}
		return json;
		
	}

	/*
	 * 统计收支(non-Javadoc)
	 * @see com.yiliao.service.FinancialDetailsService#getCollectPayTotal(java.lang.String, java.lang.String)
	 */
	@Override
	public MessageUtil getCollectPayTotal(String beginTime, String endTime) {
		
		MessageUtil mu = new MessageUtil();
		try {
			String reSql = "SELECT sum(t_recharge_money) AS totalMoney FROM t_recharge WHERE  t_order_state = 1 AND t_payment_type != 2 AND t_payment_type != 3 "; //人民币
			String reSqlTb = "SELECT sum(t_recharge_money) AS totalMoney FROM t_recharge WHERE  t_order_state = 1   AND t_payment_type != 0 AND t_payment_type != 1 AND t_payment_type != 4  "; //台币
			String putSql = "SELECT sum(t_money) AS totalMoney FROM t_put_forward WHERE t_order_state = 2 ";
			
			if(null !=beginTime && !beginTime.trim().isEmpty() && null!=endTime && !endTime.trim().isEmpty()){
				
				reSql = reSql+" AND t_fulfil_time  BETWEEN  '"+beginTime+" 00:00:00' and  '"+endTime+" 23:59:59'";
				reSqlTb = reSqlTb+" AND t_fulfil_time  BETWEEN  '"+beginTime+" 00:00:00' and  '"+endTime+" 23:59:59'";
				putSql = putSql + "AND t_handle_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59'";
			}
			/*logger.info(reSql);
			logger.info("------------------------------------------------");
			logger.info(reSqlTb);*/
			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(reSql);
			Map<String, Object> mapTb = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(reSqlTb);
			Map<String, Object> putMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(putSql);
			
			
			
			
			map.put("putPay", putMap.get("totalMoney"));
			map.put("tbtotal", mapTb.get("totalMoney"));
			
			mu.setM_istatus(1);
			mu.setM_object(map);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计收支异常!", e);
		}
		return mu;
	}

	
	
	
}
