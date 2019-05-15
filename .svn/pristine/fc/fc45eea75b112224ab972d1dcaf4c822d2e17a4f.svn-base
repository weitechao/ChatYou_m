package com.yiliao.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.VIPSetMealService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

/**
 * VIP服务实现类
 * @author Administrator
 *
 */
@Service("vIPSetMealService")
public class VIPSetMealServiceImpl extends ICommServiceImpl implements
		VIPSetMealService {

	
	private MessageUtil mu = null;
	
	@Override
	public JSONObject getVipSetMealList(int page) {
		
		JSONObject json = new JSONObject();
		try {
			
			String countSql = "SELECT count(t_id) AS totalCount FROM t_vip_setmeal ";

			Map<String, Object> totalCount = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);

			String sql = "SELECT t_id,t_setmeal_name,t_cost_price,t_money,t_duration,t_is_enable,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time  FROM t_vip_setmeal  order by t_id desc   LIMIT ?,10";

			List<Map<String, Object>> listMap = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLTOMap(sql, (page - 1) * 10);
			
			//迭代统计销量
			for(Map<String, Object> m : listMap){
				
				String qSql="SELECT COUNT(*) AS total FROM t_recharge WHERE 	t_recharge_type = 0 AND t_setmeal_id = ? AND t_order_state = 1;";
				
				Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, m.get("t_id"));
			
			    m.put("total", totalMap.get("total"));
			}
			
			
			json.put("total", totalCount.get("totalCount"));
			json.put("rows", listMap);

			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取VIP列表异常!", e);
		}
		return json;
	}

	@Override
	public MessageUtil saveVipSetMeal(Integer t_id, String t_setmeal_name,
			BigDecimal t_cost_price, BigDecimal t_money, int t_duration,
			int t_is_enable,int t_gold) {
		try {
			//新增
			if(null == t_id ||  0 == t_id){
				String inSql = "INSERT INTO t_vip_setmeal (t_setmeal_name, t_cost_price, t_money, t_duration, t_is_enable,t_gold, t_create_time) VALUES (?, ?, ?, ?, ?, ?,?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_setmeal_name,t_cost_price,t_money,t_duration,t_is_enable,t_gold,DateUtils.format(new Date(), DateUtils.FullDatePattern));
			}else{ //修改
				String upSql = "UPDATE t_vip_setmeal SET   t_setmeal_name=?, t_cost_price=?, t_money=?, t_duration=?, t_is_enable=?, t_gold = ? WHERE t_id=?;";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql, t_setmeal_name,t_cost_price,t_money,t_duration,t_is_enable,t_gold,t_id);
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或者修改异常!", e);
			mu = new MessageUtil();
		}
		return mu;
	}

	/*
	 * 删除VIP套餐(non-Javadoc)
	 * @see com.yiliao.service.VIPSetMealService#delVipSetMeal(int)
	 */
	@Override
	public MessageUtil delVipSetMeal(int t_id) {
		try {
			String delSql = "DELETE FROM t_vip_setmeal WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 修改状态(non-Javadoc)
	 * @see com.yiliao.service.VIPSetMealService#upEnableOrDisable(int, int)
	 */
	@Override
	public MessageUtil upEnableOrDisable(int t_id, int state) {
		try {
			String upSql = "UPDATE t_vip_setmeal SET  t_is_enable=? WHERE t_id=?;";
			this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql,state ,t_id);
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public JSONObject getVIPConsumeDetail(int vipId, int page) {
		try {
			
			String qSql="SELECT COUNT(*) AS total FROM t_recharge WHERE 	t_recharge_type = 0 AND t_setmeal_id = ? AND t_order_state = 1;";
			
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, vipId);
			
			StringBuffer qSqlb = new StringBuffer();
			qSqlb.append("SELECT ");
			qSqlb.append(" u.t_nickName, r.t_order_no,u.t_phone,DATE_FORMAT(r.t_fulfil_time,'%Y-%m-%d %T') AS t_fulfil_time,DATE_FORMAT(v.t_end_time,'%Y-%m-%d %T') AS t_end_time ");
			qSqlb.append("FROM ");
			qSqlb.append(" t_recharge r LEFT JOIN t_user u ON r.t_user_id = u.t_id LEFT JOIN t_vip v ON r.t_user_id = v.t_user_id ");
			qSqlb.append("WHERE ");
			qSqlb.append(" t_recharge_type = 0 ");
			qSqlb.append(" AND t_setmeal_id = ? ");
			qSqlb.append(" AND t_order_state = 1 ");
			qSqlb.append(" ORDER BY t_fulfil_time DESC LIMIT ?,10 ");
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSqlb.toString(), vipId,(page-1)*10);
			
			for(Map<String, Object> m : dataList){
				
				if(null == m.get("t_nickName")){
					m.put("t_nickName", "聊友:"+m.get("t_phone").toString().substring(m.get("t_phone").toString().length() - 4 ));
				}
			}
			
			JSONObject json = new JSONObject();
			json.put("total", totalMap.get("total"));
			json.put("rows", dataList);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
