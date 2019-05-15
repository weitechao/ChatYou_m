package com.yiliao.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.CashValueSetMealService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
/**
 * 充值套餐与提现套餐
 * @author Administrator
 *
 */
@Service("cashValueSetMealService")
public class CashValueSetMealServiceImpl extends ICommServiceImpl implements
		CashValueSetMealService {

	private MessageUtil  mu = null;
	
	/*
	 * 获取充值与提现包(non-Javadoc)
	 * @see com.yiliao.service.CashValueSetMealService#getCashValueList(int)
	 */
	@Override
	public JSONObject getCashValueList(int t_project_type,int t_end_type,int page) {
		JSONObject jsonO = new JSONObject();
		try {
			
			String countSql = "SELECT count(t_id) AS totalCount FROM t_set_meal WHERE 1=1 ";
			
			if(t_project_type !=0){
				countSql = countSql + " AND t_project_type = "+ t_project_type;
			}
			if(t_end_type != -1){
				countSql = countSql + " AND t_end_type = "+ t_end_type;
			}

			Map<String, Object> totalCount = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);

			String sql = "SELECT t_id,t_project_type,t_gold,t_money,t_is_enable,t_end_type,t_describe,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time  FROM t_set_meal WHERE 1=1 ";
			
			if(t_project_type !=0){
				sql = sql + " AND t_project_type = "+ t_project_type;
			}
			if(t_end_type != -1){
				sql = sql + " AND t_end_type = "+ t_end_type;
			}
			
			sql = sql + " order by t_money ASC   LIMIT ?,10 ";

			List<Map<String, Object>> listMap = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLTOMap(sql, (page - 1) * 10);

			jsonO.put("total", totalCount.get("totalCount"));
			jsonO.put("rows", listMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取充值与提现包异常!", e);
		}
		return jsonO;
	}

	/*
	 * 新增或修改充值与提现(non-Javadoc)
	 * @see com.yiliao.service.CashValueSetMealService#addOrUpdateCashValue(java.lang.Integer, int, int, java.math.BigDecimal, int)
	 */
	@Override
	public MessageUtil addOrUpdateCashValue(Integer t_id, int t_project_type,
			int t_gold, BigDecimal t_money, int t_end_type,int t_is_enable,String t_describe) {
		try {
			//新增
			if(null == t_id ||  0 == t_id){
				String inseSql = "INSERT INTO t_set_meal (t_project_type, t_gold, t_money,t_end_type,t_describe,t_is_enable, t_create_time) VALUES (?,?,?,?,?,?,?);";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inseSql, t_project_type,t_gold,t_money,t_end_type,t_describe,t_is_enable,
						DateUtils.format(new Date(), DateUtils.FullDatePattern));
				
			}else{ //修改
				
				String upSql = "UPDATE t_set_meal SET  t_project_type=?, t_gold=?, t_money=?,t_end_type = ?, t_is_enable=?, t_describe = ?  WHERE t_id=?";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql, t_project_type,t_gold,t_money,t_end_type,t_is_enable,t_describe,t_id);
				
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或修改充值与提现异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 跟新数据状态
	 * (non-Javadoc)
	 * @see com.yiliao.service.CashValueSetMealService#updateCashValueState(int, int)
	 */
	@Override
	public MessageUtil updateCashValueState(int t_id, int state) {
		
		try {
			
			String upStateSql = "UPDATE t_set_meal SET   t_is_enable=? WHERE t_id= ?;" ; 
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(upStateSql,state,t_id);
			
			mu = new MessageUtil(1, "已更新!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新状态异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 删除数据(non-Javadoc)
	 * @see com.yiliao.service.CashValueSetMealService#delCashValue(int)
	 */
	@Override
	public MessageUtil delCashValue(int t_id) {
		try {
			
			String delSql = "DELETE FROM t_set_meal WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除数据异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
