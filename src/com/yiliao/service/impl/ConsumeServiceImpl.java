package com.yiliao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.ConsumeService;
import com.yiliao.util.MessageUtil;
/**
 * 消费服务实现类
 * @author Administrator
 *
 */
@Service("consumeService")
public class ConsumeServiceImpl extends ICommServiceImpl implements
		ConsumeService {

	@Override
	public JSONObject getConsumeList(int type,String beginTime,String endTime, int page) {
		JSONObject json = new JSONObject();
		try {
			
			String sql = "SELECT  o.t_id,u.t_id AS userId,u.t_idcard,u.t_nickName,u.t_phone,us.t_nickName AS cover_nick_name,us.t_idcard AS cover_id,o.t_consume_type,o.t_amount,DATE_FORMAT(o.t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_order o LEFT JOIN t_user u ON o.t_consume = u.t_id LEFT JOIN t_user us ON o.t_cover_consume=us.t_id WHERE 1=1 ";
            //统计sql
			String countSql ="SELECT COUNT(o.t_id) AS total FROM t_order o LEFT JOIN t_user u ON o.t_consume = u.t_id WHERE 1=1 ";
			//查询条件
			if(type >0){
				sql = sql +" AND o.t_consume_type = "+type;
				countSql = countSql +" AND  o.t_consume_type = "+type;
			}
			//
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)){
				sql = sql +  " AND o.t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59'";
				countSql = countSql + " AND o.t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59'";
			}
			
			sql = sql + " ORDER BY o.t_create_time DESC limit ?,10";
			
			List<Map<String, Object>> data = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql,(page-1)*10);
			
			for(Map<String, Object> m : data ){
				if(null == m.get("userId")) {
					m.put("t_nickName", "系统赠送");
				}else if(null == m.get("t_nickName")){
					m.put("t_nickName","聊友:"+m.get("t_phone").toString().substring(m.get("t_phone").toString().length()-4));
				}
				m.remove("t_phone");
			}
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);
			
			json.put("total", total.get("total"));
			json.put("rows", data);
			
		} catch (Exception e) {
            e.printStackTrace();
            logger.error("获取所有消费列表异常!", e);
		}
		
		return json;
	}

	@Override
	public MessageUtil getConsumeTotal(int type,String beginTime,String endTime) {
		MessageUtil mu = new MessageUtil();
		try {
			
			String sql = "SELECT SUM(t_amount) as total FROM t_order WHERE 1=1 ";
			
			//验证是否存在查询条件
			if(type > 0){
				sql = sql + " AND t_consume_type = "+type;
			}
			
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)){
				sql = sql + " AND t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59'";
			}
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql);
			
			
			mu.setM_istatus(1);
			mu.setM_object(total.get("total"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mu;
	}

	@Override
	public Map<String, Object> getGiveList(int page,String beginTime,String endTime) {
		try {
			
			//查询总记录数
			StringBuffer  sql = new StringBuffer();
			sql.append("SELECT rl.t_redpacket_gold,rl.t_redpacket_draw,rl.t_hair_userId,");
			sql.append("DATE_FORMAT(rl.t_create_time,'%Y-%m-%d %T') AS t_create_time,u.t_nickName,u.t_phone,u.t_idcard ");
			sql.append("FROM t_redpacket_log rl LEFT JOIN t_user u ON rl.t_receive_userId = u.t_id ");
			sql.append("WHERE rl.t_redpacket_type = 3 ");
			//判断是否加入时间条件查询
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {
				sql.append("AND rl.t_create_time BETWEEN '").append(beginTime).append(" 00:00:00' AND ");
				sql.append("'").append(endTime).append(" 23:59:59'");
			}
			//查询总数
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT COUNT(*) AS totals FROM ("+sql+") aa");
			
			sql.append("ORDER BY rl.t_create_time DESC LIMIT ?,10");
			//查询数据
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql.toString(), (page-1)*10);
			
			sqltoMap.forEach(s ->{
				if(null == s.get("t_nickName")) {
					s.put("t_nickName", "聊友:"+s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				//获取谁赠送的红包
				if(0!=Integer.parseInt(s.get("t_hair_userId").toString())) {
					//根据编号查询操作人
				    List<Map<String, Object>> role_user = this.getFinalDao().getIEntitySQLDAO().
				    findBySQLTOMap("SELECT t_user_name FROM t_admin WHERE t_id = ?", s.get("t_hair_userId"));
				    
				    if(role_user.isEmpty()) {
				    	s.put("giveUser", "已删除的操作员("+s.get("t_hair_userId")+")");
				    }else {
				    	s.put("giveUser", role_user.get(0).get("t_user_name"));
				    }
				}else {
					s.put("giveUser", "未记录操作员");
				}
				
			});
			
			return new HashMap<String,Object>() {{
				put("total", total.get("totals"));
				put("rows", sqltoMap);
			}};
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取后台充值异常!");
		}
		return null;
	}

	
	
}
