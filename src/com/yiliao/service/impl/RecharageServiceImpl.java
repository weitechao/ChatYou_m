package com.yiliao.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.yiliao.service.RecharageService;
import com.yiliao.util.MessageUtil;

@Service("recharageService")
public class RecharageServiceImpl extends ICommServiceImpl implements RecharageService{
	Log logger = LogFactory.getLog(RecharageServiceImpl.class);
	
	@Override
	public JSONObject getRecharageList(int type,int t_payment_type,int  t_order_state,String beginTime,String endTime, int page) {
		JSONObject json = new JSONObject();
		try {
			//列表sql
			String sql  = "SELECT u.t_nickName,u.t_idcard,u.t_phone,r.t_id,IF(r.t_gold_type = 1, r.t_recharge_money,0) AS rmb,IF(r.t_gold_type = 2, r.t_recharge_money,0) AS tb,r.t_order_no,r.t_recharge_type,r.t_gold_type,r.t_payment_type,DATE_FORMAT(r.t_create_time,'%Y-%m-%d %T') AS t_create_time,r.t_order_state,DATE_FORMAT(r.t_fulfil_time,'%Y-%m-%d %T') AS t_fulfil_time from t_recharge r LEFT JOIN t_user u ON r.t_user_id = u.t_id WHERE 1=1 ";
			//统计总数量
			String countSql = "SELECT COUNT(r.t_id) AS total from t_recharge r WHERE  1 = 1 ";
			if(type>-1){
				sql = sql + " AND  r.t_recharge_type = "+type;
				countSql = countSql + " AND  r.t_recharge_type ="+type;
			}
			
			/*if(t_gold_type>0){
				sql = sql + " AND  r.t_gold_type = "+t_gold_type;
				countSql = countSql + " AND  r.t_gold_type ="+t_gold_type;
			}*/
			
			if(t_payment_type>-1){
				sql = sql + " AND  r.t_payment_type = "+t_payment_type;
				countSql = countSql + " AND  r.t_payment_type ="+t_payment_type;
			}
			
			if(t_order_state>-1){
				sql = sql + " AND  r.t_order_state = "+t_order_state;
				countSql = countSql + " AND  r.t_order_state ="+t_order_state;
			}
			
			//判断时间是否为空
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)){
				sql = sql + " AND r.t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59' ";
				countSql = countSql + " AND r.t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59' ";
			}
			
			sql = sql + " order by r.t_id desc  limit ?,10";
			
			//logger.info("sql="+sql);
			
			List<Map<String, Object>> data = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, (page-1)*10);
			
			for(Map<String, Object> m : data){
			    if(null == m.get("t_nickName")){
			    	m.put("t_nickName", "聊友:"+m.get("t_phone").toString().substring(m.get("t_phone").toString().length()-4));
			    }
			    m.remove("t_phone");
			}
			
			logger.info("countSql="+countSql);
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);
			
			json.put("total", total.get("total"));
			json.put("rows", data);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取充值列表异常", e);
		}
		return json;
		
	}

	@Override
	public MessageUtil getTotalMoney(int type,int t_payment_type,int t_order_state,String beginTime,String endTime) {
		try {
			
			String sql = "SELECT SUM(t_recharge_money) AS total FROM t_recharge WHERE  1=1 AND t_payment_type != 2 AND t_payment_type != 3  AND t_order_state = 1 ";
			String sqlTb = "SELECT SUM(t_recharge_money) AS total FROM t_recharge WHERE  1=1 AND t_payment_type != 0 AND t_payment_type != 1 AND t_payment_type != 4  AND t_order_state = 1 ";
			
			if(type !=-1){
				sql = sql + " AND t_recharge_type ="+type ;
				sqlTb = sqlTb + " AND t_recharge_type ="+type ;
			}
			
			/*if(t_gold_type > 0){
				sql = sql + " AND t_gold_type ="+t_gold_type ;
				sqlTb = sqlTb + " AND t_gold_type ="+t_gold_type ;
			}*/
			if(t_payment_type > -1){
				sql = sql + " AND t_payment_type ="+t_payment_type ;
				sqlTb = sqlTb + " AND t_payment_type ="+t_payment_type ;
			}
			if(t_order_state > -1){
				sql = sql + " AND t_order_state ="+t_order_state ;
				sqlTb = sqlTb + " AND t_order_state ="+t_order_state ;
			}
			
			//判断查询时间是否存在
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)){
				sql = sql + " AND t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59' ";
				sqlTb = sqlTb + " AND t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59' ";
			}
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql);
			//logger.info(sql);
			//logger.info("-----------------------------------------------------------------");
			//logger.info(sqlTb);
			
			MessageUtil mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(total.get("total"));
			
			Map<String, Object> totalTb = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sqlTb);
			mu.setM_strMessage(totalTb.get("total")+"");
			
			return mu;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取统计金额异常!", e);
		}
		return null;
	}

}
