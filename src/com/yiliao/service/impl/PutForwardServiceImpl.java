package com.yiliao.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.PutForwardService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PushUtil;

import net.sf.json.JSONObject;

/**
 * 服务层实现
 * 
 * @author Administrator
 *
 */
@Service("putForwardService")
public class PutForwardServiceImpl extends ICommServiceImpl implements PutForwardService {

	/**
	 * 获取提现列表
	 */
	@Override
	public JSONObject getPutForwardList(int type, String beginTime, String endTime, int page) {

		JSONObject json = new JSONObject();

		try {

			String sql = " SELECT f.t_id,f.t_user_id,u.t_nickName,u.t_idcard,u.t_disable,d.t_nick_name,d.t_real_name,d.t_type,d.t_account_number,f.t_money,f.t_order_state,f.t_describe,f.t_share_gold+f.t_income_gold AS gold,DATE_FORMAT(f.t_create_time,'%Y-%m-%d %T') AS t_create_time,s.t_gold,s.t_money AS amount,DATE_FORMAT(f.t_handle_time,'%Y-%m-%d %T') AS t_handle_time FROM t_put_forward f  LEFT JOIN t_put_forward_data d ON f.t_data_id = d.t_id LEFT JOIN t_set_meal s ON f.t_setmeal_id = s.t_id LEFT JOIN t_user u ON f.t_user_id=u.t_id WHERE 1=1 ";
			// 统计
			String countSql = "SELECT count(*) AS total FROM t_put_forward f WHERE 1=1 ";
			// 查询条件
			if (type > -1) {
				countSql = countSql + " AND f.t_order_state = " + type;
				sql = sql + " AND f.t_order_state = " + type;
			}
			// 查询条件
			if (StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {

				countSql = countSql + " AND f.t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '" + endTime
						+ " 23:59:59'";
				sql = sql + " AND f.t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '" + endTime + " 23:59:59'";
			}

			sql = sql + " ORDER BY t_create_time DESC  LIMIT ?,10 ;";

			List<Map<String, Object>> data = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, (page - 1) * 10);

			// 得带处理当前数据是否异常
			for (Map<String, Object> m : data) {
				if (null != m.get("gold") && null != m.get("t_gold") && null != m.get("t_money")
						&& null != m.get("amount")
						&& new BigDecimal(m.get("gold").toString())
								.compareTo(new BigDecimal(m.get("t_gold").toString())) == 0
						&& new BigDecimal(m.get("t_money").toString())
								.compareTo(new BigDecimal(m.get("amount").toString())) == 0) {
					m.put("review", 1);
				} else {
					m.put("review", 0);
				}

				m.put("t_gold", new BigDecimal(null == m.get("t_gold")?"0":m.get("t_gold").toString()).intValue());

				// 得到用户的剩余金币
				String qSql = " SELECT SUM(t_profit_money+t_recharge_money+t_share_money) AS surplus FROM t_balance WHERE t_user_id = ? ";
				Map<String, Object> suMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql,
						m.get("t_user_id"));

				m.put("surplus", suMap.get("surplus"));
			}

			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);

			json.put("total", total.get("total"));
			json.put("rows", data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/*
	 * 获取提现统计(non-Javadoc)
	 * 
	 * @see com.yiliao.service.PutForwardService#getPutForwardTotal()
	 */
	@Override
	public MessageUtil getPutForwardTotal(String beginTime, String endTime) {

		MessageUtil mu = new MessageUtil();
		try {
			mu.setM_istatus(1);
			// 统计总额度
			String totalSql = "SELECT sum(t_money) AS total FROM t_put_forward";
			// 统计未审核的
			String unauditedSql = "SELECT sum(t_money) AS total FROM t_put_forward WHERE t_order_state = 0";
			// 统计已提现成功的
			String alreadyPresented = "SELECT sum(t_money) AS total FROM t_put_forward WHERE t_order_state = 2";
			// 统计已失败的
			String failSql = "SELECT sum(t_money) AS total FROM t_put_forward WHERE t_order_state = 3";

			// 查询条件
			if (StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {

				totalSql = totalSql + " WHERE t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '" + endTime
						+ " 23:59:59'";
				unauditedSql = unauditedSql + " AND t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '" + endTime
						+ " 23:59:59'";
				alreadyPresented = alreadyPresented + " AND t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '"
						+ endTime + " 23:59:59'";
				failSql = failSql + " AND t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '" + endTime
						+ " 23:59:59'";
			}

			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(totalSql);

			Map<String, Object> unauditedMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap(unauditedSql);
			total.put("unaudited", unauditedMap.get("total"));

			Map<String, Object> alreadyPresentedMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap(alreadyPresented);
			total.put("alreadyPresented", alreadyPresentedMap.get("total"));

			Map<String, Object> failMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(failSql);
			total.put("fail", failMap.get("total"));

			mu.setM_object(total);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计提现额度异常!", e);
		}
		return mu;
	}

	/*
	 * 修改提现状态(non-Javadoc)
	 * 
	 * @see com.yiliao.service.PutForwardService#updatePutForwardState(int, int,
	 * java.lang.String)
	 */
	@Override
	public MessageUtil updatePutForwardState(int t_id, int state, String t_duration) {
		MessageUtil mu = new MessageUtil();
		try {
			// 提现成功
			if (state == 1) {

				String updateSql = "UPDATE t_put_forward SET t_order_state = 3 ,t_describe = ? WHERE t_id = ?";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(updateSql, t_duration, t_id);
			} else {
				// 提现失败 金币原路退回
				// 根据编号查询出数据
				String sql = "SELECT * FROM t_put_forward WHERE  t_id = ?";
				Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, t_id);

				// 查询用户的余额信息
				String balSql = "SELECT * FROM t_balance WHERE t_user_id = ?";
				Map<String, Object> balanceMap = this.getFinalDao().getIEntitySQLDAO()
						.findBySQLUniqueResultToMap(balSql, Integer.parseInt(toMap.get("t_user_id").toString()));

				// 存储钱包变动记录
				this.getFinalDao().getIEntitySQLDAO().executeSQL(
						"INSERT INTO t_wallet_detail (t_user_id, t_change_type, t_change_category, t_change_front, t_value, t_change_after, t_change_time) "
								+ "VALUES (?,?, '1', '10', ?, ?, ?);",
						Integer.parseInt(toMap.get("t_user_id").toString()), 0, 12,
						new BigDecimal(balanceMap.get("t_profit_money").toString()),
						new BigDecimal(toMap.get("t_income_gold").toString()),
						new BigDecimal(balanceMap.get("t_profit_money").toString())
								.add(new BigDecimal(toMap.get("t_income_gold").toString()))
								.setScale(2, BigDecimal.ROUND_DOWN),
						DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

				// 判断提现的时候 是否扣除了分享金币
				if (new BigDecimal(toMap.get("t_share_gold").toString()).compareTo(new BigDecimal(0)) > 1) {
					// 存储钱包变动记录
					this.getFinalDao().getIEntitySQLDAO().executeSQL(
							"INSERT INTO t_wallet_detail (t_user_id, t_change_type, t_change_category, t_change_front, t_value, t_change_after, t_change_time) "
									+ "VALUES (?,?, '1', '10', ?, ?, ?);",
							Integer.parseInt(toMap.get("t_user_id").toString()), 0, 12,
							new BigDecimal(balanceMap.get("t_share_money").toString()),
							new BigDecimal(toMap.get("t_share_gold").toString()),
							new BigDecimal(balanceMap.get("t_share_money").toString())
									.add(new BigDecimal(toMap.get("t_share_gold").toString())).setScale(2,
											BigDecimal.ROUND_DOWN),
							DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

				}
			}

			mu = new MessageUtil(1, "处理成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改提现状态!", e);
		}
		return mu;
	}

	@Override
	public synchronized MessageUtil madeMoney(int t_id, int userId, int handleType, String message) {
		MessageUtil mu = null;
		try {
			
			String upSql =  " UPDATE t_put_forward SET t_describe = ? ,t_order_state = ?,t_handle_time=? WHERE t_id = ? ";
			
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql, message,handleType,DateUtils.format(new Date(), DateUtils.FullDatePattern),t_id);
			
			//存储消息
			String messageSql = "INSERT INTO t_message (t_user_id, t_message_content, t_create_time, t_is_see) VALUES (?, ?, ?, ?);";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(messageSql, userId,message,
					DateUtils.format(new Date(), DateUtils.FullDatePattern),0);
			
			if(handleType == 3 ) {
				takeOutError(t_id);
			}
			// 给用户推送
			PushUtil.sendPush(userId, message);

			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/**
	 * 提现异常 原路返回金币
	 */
	public void takeOutError(int t_id) {
		// 根据编号查询出数据
		String sql = "SELECT * FROM t_put_forward WHERE  t_id = ?";
		Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, t_id);

		// 查询用户的余额信息
		String balSql = "SELECT * FROM t_balance WHERE t_user_id = ?";
		Map<String, Object> balanceMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(balSql,
				Integer.parseInt(toMap.get("t_user_id").toString()));

		// 存储钱包变动记录
		this.getFinalDao().getIEntitySQLDAO().executeSQL(
				"INSERT INTO t_wallet_detail (t_user_id, t_change_type, t_change_category, t_change_front, t_value, t_change_after, t_change_time,t_sorece_id) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ? );",
				Integer.parseInt(toMap.get("t_user_id").toString()), 0, 12,
				new BigDecimal(balanceMap.get("t_profit_money").toString()),
				new BigDecimal(toMap.get("t_income_gold").toString()),
				new BigDecimal(balanceMap.get("t_profit_money").toString())
						.add(new BigDecimal(toMap.get("t_income_gold").toString())).setScale(2, BigDecimal.ROUND_DOWN),
				DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), toMap.get("t_id"));

		// 修改用户的收入金币
		String uSql = "UPDATE t_balance SET t_profit_money = t_profit_money + ? WHERE t_user_id = ? ";
		this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, new BigDecimal(toMap.get("t_income_gold").toString()),
				toMap.get("t_user_id"));

		// 判断提现的时候 是否扣除了分享金币
		if (new BigDecimal(toMap.get("t_share_gold").toString()).compareTo(new BigDecimal(0)) > 0) {
			// 存储钱包变动记录
			this.getFinalDao().getIEntitySQLDAO().executeSQL(
					"INSERT INTO t_wallet_detail (t_user_id, t_change_type, t_change_category, t_change_front, t_value, t_change_after, t_change_time,t_sorece_id) "
							+ "VALUES (?,?, ?, ?, ?, ?, ?,?);",
					Integer.parseInt(toMap.get("t_user_id").toString()), 0, 12,
					new BigDecimal(balanceMap.get("t_share_money").toString()),
					new BigDecimal(toMap.get("t_share_gold").toString()),
					new BigDecimal(balanceMap.get("t_share_money").toString())
							.add(new BigDecimal(toMap.get("t_share_gold").toString())).setScale(2, BigDecimal.ROUND_DOWN),
					DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"), toMap.get("t_id"));

			// 修改用户的分享金币
			uSql = "UPDATE t_balance SET t_share_money = t_share_money + ? WHERE t_user_id = ? ";
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, new BigDecimal(toMap.get("t_share_gold").toString()),
					toMap.get("t_user_id"));
		}

	}

}
