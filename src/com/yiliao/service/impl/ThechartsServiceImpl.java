package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.ThechartsService;
import com.yiliao.util.DateUtils;

import net.sf.json.JSONObject;

@Service("thechartsService")
public class ThechartsServiceImpl extends ICommServiceImpl implements ThechartsService {

	@Override
	public JSONObject getOnLineTimeList(int page) {
		try {

			StringBuffer qSql = new StringBuffer();
			qSql.append("SELECT u.t_id,u.t_idcard,t_nickName,t_phone,t_handImg,aa.totalTime ");
			qSql.append("FROM t_user u LEFT JOIN ( ");
			qSql.append("SELECT t_user_id,SUM(t_duration) AS totalTime ");
			qSql.append("FROM t_log_time GROUP BY t_user_id ) aa ");
			qSql.append("ON u.t_id = aa.t_user_id ");
			qSql.append(" WHERE aa.totalTime IS NOT NULL ");

			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap("SELECT COUNT(*) AS total FROM (" + qSql + ") bb");

			List<Map<String, Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap(qSql + "  ORDER BY aa.totalTime DESC LIMIT ?,10", (page - 1) * 10);

			sqltoMap.forEach(s -> {
				if (null == s.get("t_nickName")) {
					s.put("t_nickName",
							"聊友:" + s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				s.remove("t_phone");
				s.put("totalTime", DateUtils.getConvert(Integer.parseInt(s.get("totalTime").toString())));
			});

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", sqltoMap);

			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getInviteUserList(int page) {
		try {

			StringBuffer qSql = new StringBuffer();
			qSql.append("SELECT u.t_id,u.t_idcard,u.t_nickName,t_phone,t_handImg,u.t_sex,aa.total ");
			qSql.append("FROM t_user u LEFT JOIN ( ");
			qSql.append("SELECT COUNT(t_id) AS total,t_referee FROM t_user ");
			qSql.append("WHERE t_referee !=0 GROUP BY t_referee ");
			qSql.append(") aa ON aa.t_referee = u.t_id WHERE aa.total IS NOT NULL ");

			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap("SELECT COUNT(*) AS total FROM (" + qSql + ") bb");

			List<Map<String, Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap(qSql + "ORDER BY aa.total DESC LIMIT ?,10", (page - 1) * 10);

			sqltoMap.forEach(s -> {
				if (null == s.get("t_nickName")) {
					s.put("t_nickName",
							"聊友:" + s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				s.remove("t_phone");
			});

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", sqltoMap);

			return json;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getSpreadUserList(int userId, int page, String beginTime, String endTime) {
		try {

			String cSql = "SELECT COUNT(t_id) AS total FROM t_user WHERE t_referee = ? ";
			String qSql = "SELECT t_id,t_idcard,t_nickName,t_phone,t_sex,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_user WHERE t_referee = ? ";
			if (StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {
				cSql += "t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '" + endTime + " 23:59:59' ";
				qSql += "t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '" + endTime + " 23:59:59' ";
			}

			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql, userId);

			List<Map<String, Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap(qSql + " ORDER BY t_create_time DESC LIMIT ?,10", userId, (page - 1) * 10);

			sqltoMap.forEach(s -> {
				if (null == s.get("t_nickName")) {
					s.put("t_nickName",
							"聊友:" + s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				s.remove("t_phone");
			});

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", sqltoMap);

			return json;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getUserConsumeList(int page) {
		try {
			StringBuffer body = new StringBuffer();
			body.append("SELECT u.t_id,u.t_idcard,u.t_nickName,u.t_phone,u.t_handImg,u.t_sex,bb.totalMoney ");
			body.append("FROM t_user u LEFT JOIN ( ");
			body.append("SELECT t_consume,SUM(t_amount) AS totalMoney ");
			body.append("FROM t_order WHERE t_consume_type BETWEEN 2 AND 9  GROUP BY t_consume ) bb ");
			body.append("ON bb.t_consume = u.t_id WHERE bb.totalMoney IS NOT NULL ");

			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap("SELECT COUNT(*) AS total FROM (" + body + ") aa");

			List<Map<String, Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap(body + " ORDER BY bb.totalMoney DESC LIMIT ?,10", (page - 1) * 10);

			sqltoMap.forEach(s -> {
				if (null == s.get("t_nickName")) {
					s.put("t_nickName",
							"聊友:" + s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				s.remove("t_phone");
			});

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", sqltoMap);

			return json;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getExtractMoney(int page) {
		try {

			StringBuffer body = new StringBuffer();
			body.append("SELECT u.t_id,u.t_idcard,u.t_nickName,u.t_phone,u.t_handImg,u.t_sex,bb.totalMoney ");
			body.append("FROM t_user u LEFT JOIN ( ");
			body.append("SELECT t_user_id,SUM(t_money) AS totalMoney ");
			body.append("FROM t_put_forward WHERE t_order_state = 2 GROUP BY t_user_id ) ");
			body.append("bb ON bb.t_user_id = u.t_id  WHERE bb.totalMoney > 0 ");

			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap("SELECT COUNT(*) AS total FROM (" + body + ") aa");

			List<Map<String, Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap(body + " ORDER BY bb.totalMoney DESC LIMIT ?,10 ", (page - 1) * 10);

			sqltoMap.forEach(s -> {
				if (null == s.get("t_nickName")) {
					s.put("t_nickName",
							"聊友:" + s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				s.remove("t_phone");
			});

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", sqltoMap);

			return json;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getBalanList(int page,int t_sex) {
		try {

			StringBuffer body = new StringBuffer();
			body.append("SELECT aa.balance,u.t_id,u.t_idcard,u.t_nickName,u.t_phone,u.t_handImg,u.t_sex ");
			body.append("FROM (SELECT SUM(b.t_profit_money + b.t_recharge_money + b.t_share_money) AS balance,b.t_user_id FROM t_balance b  GROUP BY b.t_user_id ) ");
			body.append("aa LEFT JOIN t_user u ON aa.t_user_id = u.t_id ");
			if(t_sex > -1) {
				body.append(" WHERE u.t_sex = ").append(t_sex);
			}
			 

			// 查询总数
			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap("SELECT COUNT(t_id) as total FROM (" + body + ") bb");

			
			logger.info(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));
			// 得到数据
			List<Map<String, Object>> sqltoList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
					body + " ORDER BY aa.balance DESC LIMIT ?,10", (page - 1) * 10);
			logger.info(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));
			
			sqltoList.forEach(s -> {
				if (null == s.get("t_nickName")) {
					s.put("t_nickName",
							"聊友:" + s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				s.remove("t_phone");
			});

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", sqltoList);

			return json;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getBankUserList(int page) {
		try {
			
			StringBuffer body = new StringBuffer();
			body.append("SELECT u.t_id,u.t_idcard,u.t_nickName,u.t_phone,u.t_handImg,u.t_sex,SUM(r.t_recharge_money) AS rechMoney ");
			body.append("FROM t_user u LEFT JOIN t_recharge r ON r.t_user_id = u.t_id  WHERE  r.t_order_state = 1 GROUP BY u.t_id ");
			
			
			// 查询总数
			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap("SELECT COUNT(t_id) as total FROM (" + body + ") aa");

			// 得到数据
			List<Map<String, Object>> sqltoList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
					"SELECT * FROM (" + body + ") aa ORDER BY aa.rechMoney DESC LIMIT ?,10", (page - 1) * 10);

			sqltoList.forEach(s -> {
				if (null == s.get("t_nickName")) {
					s.put("t_nickName",
							"聊友:" + s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				s.remove("t_phone");
			});
			
			
			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", sqltoList);

			return json;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
