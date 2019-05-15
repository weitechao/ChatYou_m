package com.yiliao.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.domain.User;
import com.yiliao.domain.WalletDetail;
import com.yiliao.service.UserService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PushUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户服务实现类
 * 
 * @author Administrator
 * 
 */
@Service("userService")
public class UserServiceImpl extends ICommServiceImpl implements UserService {

	private MessageUtil mu = null;

	/*
	 * 保存用户并返回用户编号(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#saveUser(java.lang.String,
	 * java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public int saveUser(String nickName, String phone, Integer sex, Integer age, Integer t_height, Integer t_weight,
			String t_constellation, String t_city, String t_vocation, String t_synopsis, String t_autograph,
			Integer t_role, int t_state) {

		// 注册用户
		int userId = addUser(nickName, sex, age, t_height, t_weight, null, phone, t_constellation, t_city, t_vocation,
				t_synopsis, t_autograph, t_role.toString(), null, null, null, t_state);

		// 判断当前用户是否是虚拟主播
		if (t_role == 1) {

			String inSql = "INSERT INTO t_virtual (t_user_id, t_create_time) VALUES (?,?);";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, userId,
					DateUtils.format(new Date(), DateUtils.FullDatePattern));

			// 给主播插入默认的收费设置
			inSql = "INSERT INTO t_anchor_setup (t_user_id, t_video_gold, t_text_gold, t_phone_gold, t_weixin_gold) VALUES (?,?,?,?,?);";
			this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, userId, 30, 1, 100, 100);
		}

		// 返回注册编号
		return userId;
	}

	/**
	 * 新增用户
	 * 
	 * @param t_nickName      用户昵称
	 * @param t_sex           用户性别
	 * @param t_age           用户年龄
	 * @param t_handImg       用户
	 * @param t_phone         用户电话
	 * @param t_constellation 用户星座
	 * @param t_city          用户城市
	 * @param t_vocation      用户职业
	 * @param marking         推广标示
	 */
	private int addUser(String t_nickName, Integer t_sex, Integer t_age, Integer t_height, Integer t_weight,
			String t_handImg, String t_phone, String t_constellation, String t_city, String t_vocation,
			String t_synopsis, String t_autograph, String t_role, String openId, String qqopenId, String marking,
			int t_state) {

		int refereeId = 0;

		StringBuffer insert = new StringBuffer();
		StringBuffer value = new StringBuffer();

		insert.append("INSERT INTO t_user (");
		value.append(" VALUES (");
		// 昵称
		if (null != t_nickName && !"".equals(t_nickName.trim())) {
			insert.append("t_nickName,");
			value.append("'").append(t_nickName).append("',");
		}
		// 性别
		if (null != t_sex) {
			insert.append("t_sex,");
			value.append(t_sex).append(",");
		}
		// 年龄
		if (null != t_age) {
			insert.append("t_age,");
			value.append(t_age).append(",");
		}
		// 头像
		if (null != t_handImg && !"".equals(t_handImg.trim())) {
			insert.append("t_handImg,");
			value.append(t_handImg).append(",");
		}
		// 手机号
		if (null != t_phone && !"".equals(t_phone)) {
			insert.append("t_phone,");
			value.append("'").append(t_phone).append("',");
		}
		// 身高
		if (null != t_height && 0 != t_height) {
			insert.append("t_height,");
			value.append(t_height).append(",");
		}
		if (null != t_weight && 0 != t_weight) {
			insert.append("t_weight,");
			value.append(t_weight).append(",");
		}
		// 星座
		if (null != t_constellation && !"".equals(t_constellation.trim())) {
			insert.append("t_constellation,");
			value.append("'").append(t_constellation).append("',");
		}
		// 城市
		if (null != t_city && !"".equals(t_city.trim())) {
			insert.append("t_city,");
			value.append("'").append(t_city).append("',");
		}
		// 用户职业
		if (null != t_vocation && !"".equals(t_vocation.trim())) {
			insert.append("t_vocation,");
			value.append("'").append(t_vocation).append("',");
		}
		if (null != t_synopsis && !"".equals(t_synopsis.trim())) {
			insert.append("t_synopsis,");
			value.append("'").append(t_synopsis).append("',");
		}
		if (null != t_autograph && !"".equals(t_autograph.trim())) {
			insert.append("t_autograph,");
			value.append("'").append(t_autograph).append("',");
		}
		// openid
		if (null != openId && !"".equals(openId.trim())) {
			insert.append("t_open_id,");
			value.append(openId).append(",");
		}
		// QQ openid
		if (null != qqopenId && !"".equals(qqopenId.trim())) {
			insert.append("t_qq_open_id,");
			value.append("'").append(qqopenId).append("',");
		}

		// 其他的字段
		insert.append(
				"t_create_time, t_referee, t_role, t_disable, t_login_time,t_is_vip, t_is_not_disturb, t_browse_sum, t_onLine,t_id)");
		value.append("?,?,?,?,?,?,?,?,?,?)");

		// 拼接字符串
		insert.append(value);

		List<Map<String, Object>> findBySQLTOMap = this.getFinalDao().getIEntitySQLDAO()
				.findBySQLTOMap("SELECT t_id FROM t_user ORDER BY t_id DESC LIMIT 1;");

		int userId = 0;

		if (findBySQLTOMap.isEmpty()) {
			userId = 1;
		} else {
			userId = Integer.parseInt(findBySQLTOMap.get(0).get("t_id").toString()) + 1;
		}

		this.getFinalDao().getIEntitySQLDAO().executeSQL(insert.toString(),
				DateUtils.format(new Date(), DateUtils.FullDatePattern), refereeId, t_role, User.DISABLE_NO,
				DateUtils.format(new Date(), DateUtils.FullDatePattern), User.IS_VIP_NO, User.IS_NOT_DISTURB, 0,
				User.IS_YES_ONLINE, userId);

		// 注册用户为空闲状态
		String sql = "INSERT INTO t_anchor (t_user_id, t_state) VALUES (?, ?);";

		this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, userId, t_state);

		// 给用户分配身份ID
		int idCard = userId + 10000;

		String idCardSql = "UPDATE t_user SET  t_idcard=?  WHERE t_id=?";
		this.getFinalDao().getIEntitySQLDAO().executeSQL(idCardSql, idCard, userId);

		// 注册用户钱包余额
		sql = "INSERT INTO t_balance (t_user_id, t_recharge_money, t_profit_money, t_share_money) VALUES (?, 0, 0, 0);";
		this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, userId);

		return userId;
	}

	/**
	 * 分页获取用户列表
	 */
	@Override
	public JSONObject getUserLsit(int t_sex, int t_role, String condition, String beginTime, String endTime, int page) {

		JSONObject json = new JSONObject();

		try {

			String countSql = " select count(t_id) as totalCount from t_user WHERE 1=1";

			StringBuffer qSql = new StringBuffer();
			qSql.append("SELECT ");
			qSql.append(" u.t_id,u.t_nickName,u.t_idcard,u.t_sex,u.t_handImg,u.t_age,u.t_phone,u.t_is_vip, ");
			qSql.append(" u.t_weixin,u.t_role,DATE_FORMAT(u.t_create_time, '%Y-%m-%d %T') AS t_create_time,");
			qSql.append(
					" u.t_disable,u.t_onLine,r.t_nickName AS refeName,s.t_is_nominate,f.t_is_free,u.t_online_setup ");
			qSql.append("FROM ");
			qSql.append(" t_user u ");
			qSql.append(" LEFT JOIN t_user r ON u.t_referee = r.t_id ");
			qSql.append(" LEFT JOIN t_spread s ON  s.t_user_id = u.t_id ");
			qSql.append(" LEFT JOIN t_free_anthor f ON  f.t_user_id = u.t_id ");
			qSql.append(" WHERE ");
			qSql.append(" 1 = 1 ");
			// 判断性别否需要查询
			if (t_sex != -1) {
				countSql = countSql + " AND t_sex = " + t_sex;
				qSql.append(" AND u.t_sex = ").append(t_sex);
			}
			// 是否加入角色查询
			if (t_role != -1) {
				countSql = countSql + " AND t_role = " + t_role;
				if (t_role != 2 && t_role != 3)
					qSql.append(" AND u.t_role = ").append(t_role);
				else if (t_role == 2)
					qSql.append(" AND s.t_is_nominate = 1 ");
				else if (t_role == 3)
					qSql.append(" AND f.t_id is not null");
			}
			// 判断查询条件是否存在
			if (null != condition && !condition.isEmpty()) {
				countSql = countSql + " AND (t_nickName like '%" + condition + "%' or t_phone like '%" + condition
						+ "%' or t_idcard = '" + condition + "') ";
				qSql.append(" AND (u.t_nickName like '%").append(condition).append("%' or u.t_phone like '%")
						.append(condition).append("%' or ");
				qSql.append(" u.t_idcard ='").append(condition).append("' ) ");
			}

			// 判断是否根据注册时间查询
			if (null != beginTime && !beginTime.isEmpty() && null != endTime && !endTime.isEmpty()) {

				countSql = countSql + " AND t_create_time BETWEEN '" + beginTime + " 00:00:00' AND '" + endTime
						+ " 23:59:59' ";
				qSql.append(" AND u.t_create_time BETWEEN '").append(beginTime).append(" 00:00:00' AND '")
						.append(endTime + " 23:59:59' ");
			}

			qSql.append(" ORDER BY u.t_id DESC  LIMIT ?,10");

			Map<String, Object> totalCount = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);

			List<Map<String, Object>> listMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql.toString(),
					(page - 1) * 10);

			for (Map<String, Object> m : listMap) {
				if (null == m.get("t_nickName")) {
					m.put("t_nickName",
							"聊友:" + m.get("t_phone").toString().substring(m.get("t_phone").toString().length() - 4));
				}

				// 汇总主播用户在先总时长

				qSql = new StringBuffer();
				qSql.append("SELECT SUM(t_duration) AS totalTime FROM t_log_time WHERE t_user_id = ? ;");

				Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO()
						.findBySQLUniqueResultToMap(qSql.toString(), m.get("t_id"));

				if (null == toMap.get("totalTime")) {
					m.put("totalTime", "无记录");
				} else {
					m.put("totalTime", DateUtils.getConvert(Integer.parseInt(toMap.get("totalTime").toString())));
				}

			}

			json.put("total", totalCount.get("totalCount"));
			json.put("rows", listMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户列表异常!", e);
		}
		return json;
	}

	/*
	 * 启用或禁用用户(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#enableOrDisable(int, int)
	 */
	@Override
	public MessageUtil enableOrDisable(int t_id, int state) {
		try {

			String sql = "UPDATE t_user SET t_disable = ? WHERE t_id = ?";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, state, t_id);

			if (state == 2) {

				String messageSql = "INSERT INTO t_message (t_user_id, t_message_content, t_create_time, t_is_see) VALUES (?, ?, ?, ?);";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(messageSql, t_id, "您因违反平台相关协议,已被永久封号.",
						DateUtils.format(new Date(), DateUtils.FullDatePattern), 0);
				// 推送自定义消息
				PushUtil.sendPush(t_id, "您因违反平台相关协议,已被永久封号.", "2");
			}

			mu = new MessageUtil(1, "操作成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("启用或者禁用异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 用户封号 (non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#freezeOnesUser(int, int)
	 */
	@Override
	public MessageUtil freezeOnesUser(int t_id, int freeze_time, String pushConnent,String user) {
		try {
			
			if(freeze_time == -1) {
				//设置用户的状态
				String upSql = "UPDATE t_user SET t_disable = ? WHERE t_id = ? ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql,2, t_id);
				
				// 推送自定义消息
				PushUtil.sendPush(t_id, pushConnent, "1");
			}else {
				// 修改用户记录
				String sql = "UPDATE t_user SET t_disable = 1 WHERE t_id = ?";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);

				// 添加封号记录
				sql = "INSERT INTO t_disable ( t_user_id, t_disable_time, t_start_time, t_end_time,t_state,t_operate_user,t_create_time,t_context) VALUES (?, ?, ?, ?,0,?,?,?);";

				String format = DateUtils.format(new Date(), DateUtils.FullDatePattern);

				long ms = freeze_time * 60 * 1000;

				long time = DateUtils.parse(format, DateUtils.FullDatePattern).getTime();

				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id, freeze_time, format,
						DateUtils.format((ms + time), DateUtils.FullDatePattern),user,DateUtils.format(new Date(), DateUtils.FullDatePattern),pushConnent);

				// 推送还是socket 待定
				String messageSql = "INSERT INTO t_message (t_user_id, t_message_content, t_create_time, t_is_see) VALUES (?, ?, ?, ?);";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(messageSql, t_id, pushConnent,
						DateUtils.format(new Date(), DateUtils.FullDatePattern), 0);
				// 推送自定义消息
				PushUtil.sendPush(t_id, pushConnent, "1");

			}
			mu = new MessageUtil(1, "封号成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("封号异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 用户解封(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#unlock(int)
	 */
	@Override
	public MessageUtil unlock(int t_id) {
		try {
			// 修改用户记录
			String sql = "UPDATE t_user SET t_disable = 0 WHERE t_id = ?";
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);

			//
			sql = "UPDATE t_disable SET t_end_time = ?,t_state=1 WHERE  t_user_id = ?";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql,
					DateUtils.format(new Date(), DateUtils.FullDatePattern), t_id);

			mu = new MessageUtil(1, "解封成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("封号异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 获取封号用户列表(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#getFreezeList(int)
	 */
	@Override
	public JSONObject getFreezeList(String condition, int page) {

		JSONObject json = new JSONObject();

		try {

			StringBuffer totalSql = new StringBuffer();
			totalSql.append("SELECT count(*) AS total FROM (");
			totalSql.append("SELECT u.t_id,u.t_nickName,t_sex,u.t_role,count(d.t_id) AS total ");
			totalSql.append("FROM t_disable d LEFT JOIN t_user u ON d.t_user_id = u.t_id ");
			if (null != condition && !condition.isEmpty()) {
				totalSql.append("WHERE u.t_nickName LIKE '%").append(condition).append("%' ");
				totalSql.append("OR u.t_phone LIKE '%").append(condition).append("%'");
			}
			totalSql.append(" GROUP BY d.t_user_id  ) ta ");

			Map<String, Object> totalCount = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap(totalSql.toString());

			String sql = "SELECT d.t_id,u.t_id AS u_id,u.t_nickName,u.t_idcard,t_sex,u.t_phone,u.t_role,u.t_disable,d.t_disable_time,DATE_FORMAT(d.t_start_time,'%Y-%m-%d %T') AS t_start_time,DATE_FORMAT(d.t_end_time,'%Y-%m-%d %T') AS t_end_time,t_operate_user,DATE_FORMAT(d.t_create_time,'%Y-%m-%d %T') AS t_create_time,t_context FROM t_disable d LEFT JOIN t_user u ON d.t_user_id = u.t_id WHERE  1=1  ";
			if (null != condition && !condition.isEmpty()) {
				sql = sql + "AND (u.t_nickName LIKE '%" + condition + "%' OR u.t_phone LIKE '%" + condition + "%' )";
			}
			sql = sql + "  ORDER BY d.t_start_time DESC LIMIT ?,10";
			List<Map<String, Object>> findBySQLTOMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql,
					(page - 1) * 10);

			for (Map<String, Object> m : findBySQLTOMap) {
				if (null == m.get("t_nickName")) {
					m.put("t_nickName",
							"聊友:" + m.get("t_phone").toString().substring(m.get("t_phone").toString().length() - 4));
				}
			}

			json.put("total", totalCount.get("total"));
			json.put("rows", findBySQLTOMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("封号异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return json;
	}

	/*
	 * 获取用户详情(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#getUserById(int)
	 */
	@Override
	public MessageUtil getUserById(int t_id) {
		try {

			String sql = "SELECT t_nickName,t_sex,t_age,t_phone,t_height,t_weight,t_constellation,t_city,t_synopsis,t_autograph,t_role,t_vocation FROM t_user WHERE t_id = ?";

			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, t_id);

			mu = new MessageUtil();

			mu.setM_istatus(1);

			mu.setM_object(map);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("封号异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 获取封号详情(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#getFreezeTimeList(int)
	 */
	@Override
	public MessageUtil getFreezeTimeList(int u_id) {
		try {

			String sql = "SELECT t_disable_time,DATE_FORMAT(t_start_time,'%Y-%m-%d %T') AS t_start_time,DATE_FORMAT(t_end_time,'%Y-%m-%d %T') AS t_end_time FROM t_disable WHERE t_user_id = ?";

			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, u_id);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(dataList);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("封号异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 获取禁用用户列表(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#getDisableList(int)
	 */
	@Override
	public JSONObject getDisableList(String condition, int page) {

		JSONObject json = new JSONObject();

		try {

			String countSql = " select count(t_id) as totalCount from t_user WHERE t_disable = 2 ";
			String sql = "SELECT u.t_id,u.t_idcard,u.t_nickName,u.t_sex,u.t_age,u.t_phone,u.t_role,u.t_referee,DATE_FORMAT(u.t_create_time,'%Y-%m-%d') AS t_create_time,u.t_disable,s.t_nickName AS refeName FROM t_user u LEFT JOIN t_user s ON u.t_referee=s.t_id  WHERE u.t_disable = 2 ";

			if (StringUtils.isNotBlank(condition)) {
				countSql = countSql + " AND (t_nickName LIKE '%" + condition + "%' OR t_phone LIKE '%" + condition
						+ "%' )";
				sql = sql + " AND (u.t_nickName LIKE '%" + condition + "%' OR u.t_phone LIKE '%" + condition + "%' )";
			}
			sql = sql + " ORDER BY t_create_time DESC LIMIT ?,10";

			Map<String, Object> totalCount = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);

			List<Map<String, Object>> listMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql,
					(page - 1) * 10);

			json.put("total", totalCount.get("totalCount"));
			json.put("rows", listMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("封号异常!", e);
		}
		return json;
	}

	/*
	 * 获取用户相册列表(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#getPhotoList(int)
	 */
	@Override
	public MessageUtil getPhotoList(int page, int t_user_id) {
		try {

			String totalCount = "SELECT COUNT(t_id) AS total  FROM t_album WHERE t_user_id = ? AND t_is_del = 0";

			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(totalCount,
					t_user_id);

			// 查询数据
			String sql = "SELECT t_id,t_addres_url,t_file_type,t_is_private FROM t_album WHERE t_user_id = ? AND t_is_del = 0 ORDER BY t_id DESC LIMIT ?,12";
			List<Map<String, Object>> listMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, t_user_id,
					(page - 1) * 12);

			JSONArray array = new JSONArray();
			if (null != listMap && !listMap.isEmpty()) {
				int group = listMap.size() % 4 == 0 ? listMap.size() / 4 : listMap.size() / 4 + 1;
				if (group == 3) {
					array.add(listMap.subList(0, 4));
					array.add(listMap.subList(4, 8));
					array.add(listMap.subList(8, listMap.size()));
				} else if (group == 2) {
					array.add(listMap.subList(0, 4));
					array.add(listMap.subList(4, listMap.size()));
				} else {
					array.add(listMap);
				}
			}

			int pageCount = Integer.parseInt(total.get("total").toString()) % 12 == 0
					? Integer.parseInt(total.get("total").toString()) / 12
					: Integer.parseInt(total.get("total").toString()) / 12 + 1;

			JSONObject json = new JSONObject();
			json.put("pageCount", pageCount);
			json.put("data", array);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(json);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取相册列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 设置头像禁用(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#setUpPhotoEisable(int)
	 */
	@Override
	public MessageUtil setUpPhotoEisable(int t_id) {
		try {

			String sql = "UPDATE t_album SET t_is_del = 1 WHERE t_id = ? ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);

			mu = new MessageUtil(1, "设置成功!");

		} catch (Exception e) {

			e.printStackTrace();
			logger.error("禁用封面异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 封面列表(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#getUnauditedCoverList(int)
	 */
	@Override
	public MessageUtil getUnauditedCoverList(int page) {
		try {
			// 统计所有未审核的数据
			String totalSql = "SELECT count(t_id) AS total FROM t_cover_examine WHERE t_is_examine = 0";

			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(totalSql);

			int pageCount = Integer.parseInt(total.get("total").toString()) % 12 == 0
					? Integer.parseInt(total.get("total").toString()) / 12
					: Integer.parseInt(total.get("total").toString()) / 12 + 1;

			// 分页获取数据
			String sql = "SELECT t_id,t_img_url FROM t_cover_examine WHERE t_is_examine = 0 LIMIT ?,12;";

			List<Map<String, Object>> listMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql,
					(page - 1) * 12);

			JSONArray array = new JSONArray();
			if (null != listMap && !listMap.isEmpty()) {
				int group = listMap.size() % 4 == 0 ? listMap.size() / 4 : listMap.size() / 4 + 1;
				if (group == 3) {
					array.add(listMap.subList(0, 4));
					array.add(listMap.subList(4, 8));
					array.add(listMap.subList(8, listMap.size()));
				} else if (group == 2) {
					array.add(listMap.subList(0, 4));
					array.add(listMap.subList(4, listMap.size()));
				} else {
					array.add(listMap);
				}
			}

			JSONObject json = new JSONObject();
			json.put("pageCount", pageCount);
			json.put("data", array);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(json);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("禁用封面异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public JSONObject getUserFinancialDetails(int userId, int type, int page) {

		JSONObject json = new JSONObject();

		try {
			StringBuffer count = new StringBuffer();
			count.append("SELECT COUNT(w.t_id) AS total FROM t_wallet_detail w WHERE w.t_user_id = ? ");

			if (type > -1) {
				count.append(" AND w.t_change_type =").append(type).append(" ");
			}

			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap(count.toString(), userId);

			StringBuffer sb = new StringBuffer();
			sb.append(
					" SELECT w.t_id,w.t_change_type,w.t_change_category,w.t_change_front,w.t_value,w.t_change_after,DATE_FORMAT(w.t_change_time,'%Y-%m-%d %T') AS t_change_time,w.t_sorece_id");
			sb.append(" FROM t_wallet_detail w WHERE w.t_user_id = ? ");

			if (type > -1) {
				sb.append(" AND w.t_change_type =").append(type).append(" ");
			}

			sb.append("ORDER BY w.t_change_time DESC LIMIT ?,10");

			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(),
					userId, (page - 1) * 10);

			String qSql = "";

			for (Map<String, Object> m : dataList) {

				int consumptionType = Integer.parseInt(m.get("t_change_category").toString());
				switch (consumptionType) {
				case WalletDetail.CHANGE_CATEGORY_RECHARGE: // 充值 收入
					qSql = "SELECT u.t_nickName,u.t_phone,r.t_order_no,r.t_recharge_money FROM t_user u LEFT JOIN t_recharge r  ON r.t_user_id  = u.t_id WHERE r.t_id = ?";
					Map<String, Object> detailMap = this.getFinalDao().getIEntitySQLDAO()
							.findBySQLUniqueResultToMap(qSql, m.get("t_sorece_id"));

					String detail = "【";

					if (null == detailMap.get("t_nickName")) {
						detail += "聊友:" + detailMap.get("t_phone").toString()
								.substring(detailMap.get("t_phone").toString().length() - 4);
					} else {
						detail += detailMap.get("t_nickName");
					}

					detail += "】充值金额【" + detailMap.get("t_recharge_money") + "】元,订单号:【" + detailMap.get("t_order_no")
							+ "】";

					m.put("detail", detail);

					break;
				case WalletDetail.CHANGE_CATEGORY_TEXT: // 文字聊天 消费

					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					List<Map<String, Object>> detList = this.getFinalDao().getIEntitySQLDAO()
							.findBySQLTOMap(sb.toString(), m.get("t_sorece_id"), m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】对【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】发起了文本聊天";
					m.put("detail", detail);
					break;
				case WalletDetail.CHANGE_CATEGORY_VIDEO: // 视频聊天 消费

					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					detList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), m.get("t_sorece_id"),
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】对【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】发起了视频聊天";
					m.put("detail", detail);

					break;
				case WalletDetail.CHANGE_CATEGORY_PRIVATE_PHOTO: // 私密照片

					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					detList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), m.get("t_sorece_id"),
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】查看了主播【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】的私密照片";
					m.put("detail", detail);

					break;
				case WalletDetail.CHANGE_CATEGORY_PRIVATE_VIDEO: // 私密视频

					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					detList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), m.get("t_sorece_id"),
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】查看了主播【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】的私密视频";
					m.put("detail", detail);

					break;
				case WalletDetail.CHANGE_CATEGORY_PHONE: // 查看手机号

					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					detList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), m.get("t_sorece_id"),
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】查看了主播【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】的手机号";
					m.put("detail", detail);

					break;
				case WalletDetail.CHANGE_CATEGORY_WEIXIN: // 查看微信号

					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					detList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), m.get("t_sorece_id"),
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】查看了主播【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】的微信号";
					m.put("detail", detail);

					break;
				case WalletDetail.CHANGE_CATEGORY_RED_PACKET: // 红包

					String message = "";

					List<Map<String, Object>> pack = null;
					// 判断是收入还是支出
					if ("1".equals(m.get("t_change_type").toString())) {
						qSql = " SELECT * FROM t_redpacket_log WHERE t_order_id = ? ";
					} else {
						qSql = " SELECT * FROM t_redpacket_log r WHERE  r.t_redpacket_id = ? ";
					}
					pack = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, m.get("t_sorece_id"));

					for (Map<String, Object> ls : pack) {
						if (ls.get("t_redpacket_type").toString().equals("0")) {
							StringBuffer quser = new StringBuffer();
							quser.append("SELECT  t_nickName,t_phone FROM t_user WHERE t_id  = ? ");
							quser.append("UNION ");
							quser.append("SELECT  t_nickName,t_phone FROM t_user WHERE t_id  = ? ");

							List<Map<String, Object>> redPackeg = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
									quser.toString(), ls.get("t_hair_userId"), ls.get("t_receive_userId"));
							if (null == redPackeg.get(0).get("t_nickName")) {
								message = "聊友:【" + redPackeg.get(0).get("t_phone").toString()
										.substring(redPackeg.get(0).get("t_phone").toString().length() - 4);
							} else {
								message = "用户:【" + redPackeg.get(0).get("t_nickName");
							}
							message += "】给主播【";

							if (null == redPackeg.get(1).get("t_nickName")) {
								message += "聊友:" + redPackeg.get(1).get("t_phone").toString()
										.substring(redPackeg.get(1).get("t_phone").toString().length() - 4);
							} else {
								message += redPackeg.get(1).get("t_nickName");
							}
							message += "】赠送了红包";
						} else if (ls.get("t_redpacket_type").toString().equals("1")) {
							StringBuffer quser = new StringBuffer();

							quser.append(
									"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
							quser.append("UNION ");
							quser.append(
									"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

							List<Map<String, Object>> redPackeg = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
									quser.toString(), ls.get("t_hair_userId"), ls.get("t_receive_userId"));

							if (null == redPackeg.get(0).get("t_nickName")) {
								message = "聊友:【" + redPackeg.get(0).get("t_phone").toString()
										.substring(redPackeg.get(0).get("t_phone").toString().length() - 4);
							} else {
								message = "用户:【" + redPackeg.get(0).get("t_nickName");
							}
							message += "】给主播【";

							if (null == redPackeg.get(1).get("t_nickName")) {
								message += "聊友:" + redPackeg.get(1).get("t_phone").toString()
										.substring(redPackeg.get(1).get("t_phone").toString().length() - 4);
							} else {
								message += redPackeg.get(1).get("t_nickName");
							}
							message += "】贡献师徒红包";

						} else if (ls.get("t_redpacket_type").toString().equals("2")) {
							qSql = "SELECT  t_nickName,t_phone FROM t_user WHERE t_id  = ? ;";
							Map<String, Object> datMap = this.getFinalDao().getIEntitySQLDAO()
									.findBySQLUniqueResultToMap(qSql, userId);
							message = "系统给认证主播【";
							// 给谁发放了红包
							if (null == datMap.get("t_nickName")) {
								message += "聊友:" + datMap.get("t_phone").toString()
										.substring(datMap.get("t_phone").toString().length() - 4);
							} else {
								message += datMap.get("t_nickName");
							}
							message += "】发放了红包";

						} else if (ls.get("t_redpacket_type").toString().equals("3")) {
							qSql = "SELECT  t_nickName,t_phone FROM t_user WHERE t_id  = ? ;";
							Map<String, Object> datMap = this.getFinalDao().getIEntitySQLDAO()
									.findBySQLUniqueResultToMap(qSql, userId);
							message = "后台手动给【";
							// 给谁发放了红包
							if (null == datMap.get("t_nickName")) {
								message += "聊友:" + datMap.get("t_phone").toString()
										.substring(datMap.get("t_phone").toString().length() - 4);
							} else {
								message += datMap.get("t_nickName");
							}
							message += "】发放了红包";
						}
						m.put("t_redpacket_type", pack.get(0).get("t_redpacket_type"));
					}

					m.put("detail", message);

					break;
				case WalletDetail.CHANGE_CATEGOR_VIP: // 充值VIP
					qSql = "SELECT  t_nickName,t_phone FROM t_user WHERE t_id  = ? ;";
					Map<String, Object> datMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql,
							userId);
					detail = "【";
					if (null == datMap.get("t_nickName")) {
						detail += "聊友:" + datMap.get("t_phone").toString()
								.substring(datMap.get("t_phone").toString().length() - 4);
					} else {
						detail += datMap.get("t_nickName");
					}
					detail += "】充值了VIP";
					m.put("detail", detail);
					break;
				case WalletDetail.CHANGE_CATEGOR_GIFT: // 赠送礼物

					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone,o.t_consume_score FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone,o.t_consume_score FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					detList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), m.get("t_sorece_id"),
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】给主播【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】赠送了";

					qSql = "SELECT t_gift_name,t_gift_gold FROM t_gift WHERE t_gift_id  = ?";
					Map<String, Object> gift = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql,
							detList.get(0).get("t_consume_score"));
					detail += "【" + gift.get("t_gift_name") + "】,单价【" + gift.get("t_gift_gold") + "】";
					m.put("detail", detail);
					break;
				case WalletDetail.CHANGE_CATEGOR_PUT_FORWARD: // 提现

					qSql = " SELECT u.t_nickName,u.t_phone,f.t_money FROM t_put_forward f LEFT JOIN t_user u ON f.t_user_id = u.t_id WHERE f.t_id = ? ";
					detailMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql,
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detailMap.get("t_nickName")) {
						detail += "聊友:" + detailMap.get("t_phone").toString()
								.substring(detailMap.get("t_phone").toString().length() - 4);
					} else {
						detail += detailMap.get("t_nickName");
					}
					detail += "】提现【" + detailMap.get("t_money") + "】元";

					m.put("detail", detail);

					break;
				case WalletDetail.CHANGE_CATEGOR_RECOMMENDATION: // 师徒贡献

					qSql = " SELECT u.t_nickName,u.t_phone,r.t_redpacket_gold FROM t_redpacket_log r LEFT JOIN t_user u ON t_hair_userId = u.t_id  WHERE t_redpacket_id = ?  ";

					List<Map<String, Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql,
							m.get("t_sorece_id"));

					if (sqltoMap.isEmpty()) {
						// 获取邀请注册数据
						sb = new StringBuffer();
						sb.append(
								"SELECT u.t_nickName,u.t_phone,o.t_consume_score FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");

						List<Map<String, Object>> listMap = this.getFinalDao().getIEntitySQLDAO()
								.findBySQLTOMap(sb.toString(), m.get("t_sorece_id"));

						if (!listMap.isEmpty()) {
							detail = "邀请用户注册,系统赠送金币【" + m.get("t_value") + "】";
						} else {
							detail = "无法查询明细!请联系开发人员.";
						}
					} else {
						detailMap = sqltoMap.get(0);
						detail = "收到来自于【";

						if (null == detailMap.get("t_nickName")) {
							detail += "聊友:" + detailMap.get("t_phone").toString()
									.substring(detailMap.get("t_phone").toString().length() - 4);
						} else {
							detail += detailMap.get("t_nickName");
						}

						detail += "】的师徒贡献【" + detailMap.get("t_redpacket_gold") + "】个金币";
					}

					m.put("detail", detail);
					break;
				case WalletDetail.CHANGE_CATEGOR_PUTFORWARD_RETURN: // 提现失败退回

					qSql = "SELECT u.t_nickName,u.t_phone FROM t_wallet_detail w LEFT JOIN t_user u ON w.t_user_id = u.t_id WHERE w.t_id = ? ";
					detailMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, m.get("t_id"));

					detail = "【";

					if (null == detailMap.get("t_nickName")) {
						detail += "聊友:" + detailMap.get("t_phone").toString()
								.substring(detailMap.get("t_phone").toString().length() - 4);
					} else {
						detail += detailMap.get("t_nickName");
					}

					detail += "】提现异常!原路退回【" + m.get("t_value") + "】个金币";

					m.put("detail", detail);
					break;
				case WalletDetail.CHANGE_CATEGOR_GIVE_GOLD: // 注册赠送

					detail = "注册赠送金币【" + m.get("t_value") + "】";
					m.put("detail", detail);
					break;
				case WalletDetail.CHANGE_CATEGOR_GUILD_INCOME: // 公会收益

					qSql = " SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ";

					detailMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql,
							m.get("t_sorece_id"));

					detail = "收到来自于【";

					if (null == detailMap.get("t_nickName")) {
						detail += "聊友:" + detailMap.get("t_phone").toString()
								.substring(detailMap.get("t_phone").toString().length() - 4);
					} else {
						detail += detailMap.get("t_nickName");
					}
					detail += "】的公会贡献!贡献金币【" + m.get("t_value") + "】个";
					m.put("detail", detail);
					break;
				case WalletDetail.CHANGE_CATEGOR_DYNAMIC_PHOTO:
					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					detList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), m.get("t_sorece_id"),
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】查看了主播【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】的动态私密照片";
					m.put("detail", detail);
					break;
				case WalletDetail.CHANGE_CATEGOR_DYNAMIC_VIDEO:
					// 收益
					sb = new StringBuffer();
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_consume WHERE o.t_id = ? ");
					sb.append("UNION ");
					sb.append(
							"SELECT u.t_nickName,u.t_phone FROM t_order o LEFT JOIN t_user u ON u.t_id = o.t_cover_consume WHERE o.t_id = ? ");

					detList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), m.get("t_sorece_id"),
							m.get("t_sorece_id"));

					detail = "【";
					if (null == detList.get(0).get("t_nickName")) {
						detail += "聊友:" + detList.get(0).get("t_phone").toString()
								.substring(detList.get(0).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(0).get("t_nickName");
					}
					detail += "】查看了主播【";
					if (null == detList.get(1).get("t_nickName")) {
						detail += "聊友:" + detList.get(1).get("t_phone").toString()
								.substring(detList.get(1).get("t_phone").toString().length() - 4);
					} else {
						detail += detList.get(1).get("t_nickName");
					}
					detail += "】的动态私密视频";
					m.put("detail", detail);
					break;

				}

				m.remove("t_sorece_id");
			}

			json.put("total", total.get("total"));
			json.put("rows", dataList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/*
	 * 统计用户可用余额(non-Javadoc)
	 * 
	 * @see com.yiliao.service.UserService#getUserTotalMoney(int)
	 */
	@Override
	public MessageUtil getUserTotalMoney(int userId) {
		try {

			String totalSql = "SELECT SUM(t_recharge_money+t_profit_money+t_share_money) AS total FROM t_balance WHERE t_user_id = ? ";

			Map<String, Object> totalMoney = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(totalSql,
					userId);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(totalMoney.get("total"));

		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil getPhoneIsExist(String phone) {
		try {
			String sql = "SELECT * FROM t_user WHERE t_phone  =  ?";

			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, phone);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(dataMap.isEmpty() ? true : false);
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil savePhoto(int userId, String t_title, String video_img, String url, int type, int gold) {
		try {

			// 上传图片
			if (type == 0) {

				String inseSql = "INSERT INTO t_album (t_user_id, t_title, t_addres_url, t_file_type, t_is_private,  t_money, t_is_del,t_auditing_type,t_see_count) VALUES (? ,?, ?, ?, ?, ?, ?,?,0);";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(inseSql, userId, t_title, url, type, gold > 0 ? 1 : 0,
						gold, 0, gold > 0 ? 0 : 1);

			} else { // 上传视频
				// 保存至数据库
				String inseSql = "INSERT INTO t_album (t_user_id, t_title,t_video_img, t_addres_url, t_file_type, t_is_private, t_money, t_is_del, t_auditing_type,t_see_count) VALUES (?,?,?,?,?,?,?,?,?,0);";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(inseSql, userId, t_title, video_img, url, type,
						gold > 0 ? 1 : 0, gold, 0, 1);

			}

			mu = new MessageUtil(1, "上传成功!");

		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常.");
		}
		return mu;
	}

	@Override
	public MessageUtil getChargeSetUp(int userId) {
		try {

			String sql = "SELECT * FROM t_anchor_setup WHERE t_user_id = ? ";

			List<Map<String, Object>> setUp = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, userId);

			mu = new MessageUtil(1, "查询成功!");

			if (!setUp.isEmpty()) {
				mu.setM_object(setUp.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}

		return mu;
	}

	@Override
	public MessageUtil replaceSetUp(int userId, int t_video_gold, int t_text_gold, int t_phone_gold,
			int t_weixin_gold) {
		try {

			String sql = "SELECT * FROM t_anchor_setup WHERE t_user_id = ? ";

			List<Map<String, Object>> setUp = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, userId);
			// 新增
			if (setUp.isEmpty()) {

				String inSql = "INSERT INTO t_anchor_setup (t_user_id, t_video_gold, t_text_gold, t_phone_gold, t_weixin_gold) VALUES (?,?,?,?,?); ";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, userId, t_video_gold, t_text_gold, t_phone_gold,
						t_weixin_gold);
			} else { // 修改

				String upSql = "UPDATE t_anchor_setup SET t_video_gold=?, t_text_gold=?, t_phone_gold= ?, t_weixin_gold=? WHERE t_user_id = ? ";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql, t_video_gold, t_text_gold, t_phone_gold,
						t_weixin_gold, userId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil getLableList(int userId) {
		try {
			String sql = "SELECT t_id,t_label_name FROM t_label WHERE t_sex = (SELECT t_sex FROM t_user WHERE t_id = ?)";

			List<Map<String, Object>> dataLable = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, userId);

			// 获取当前用户是否已经存在标签了
			String usLabSql = "SELECT t_lable_id FROM t_user_label WHERE t_user_id = ? ";

			List<Map<String, Object>> userLable = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(usLabSql,
					userId);

			for (Map<String, Object> m : dataLable) {
				boolean isOk = false;
				for (Map<String, Object> mu : userLable) {
					if (m.get("t_id").equals(mu.get("t_lable_id"))) {
						isOk = true;
					}
				}
				m.put("checked", isOk);
			}

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(dataLable);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取标签异常!", e);
		}
		return mu;
	}

	@Override
	public MessageUtil addLabel(int userId, String lables) {
		try {

			String[] label = lables.split(",");

			String delSql = "DELETE FROM t_user_label WHERE t_user_id= ? ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, userId);

			String sql = "INSERT INTO t_user_label (t_user_id, t_lable_id) VALUES ( ?, ?) ";

			for (int i = 0; i < label.length; i++) {
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, userId, Integer.parseInt(label[i]));
			}

			mu = new MessageUtil(1, "保存标签成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新标签列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/**
	 * 随机切换评论用户
	 */
	@Override
	public MessageUtil getRandomUserList() {
		try {

			String sql = " SELECT t_id,t_nickName,t_phone FROM t_user ORDER BY RAND() LIMIT 10 ";

			List<Map<String, Object>> data = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql);

			for (Map<String, Object> m : data) {
				if (null == m.get("t_nickName")) {
					m.put("t_nickName",
							"聊友:" + m.get("t_phone").toString().substring(m.get("t_phone").toString().length() - 4));
				}
			}

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(data);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取随机用户列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/**
	 * 添加主播评论
	 */
	@Override
	public MessageUtil addUserContent(int t_content_user, int t_anchor_id, int t_score, String lables) {
		try {

			// 根据评论用户查询出用户的头像昵称
			String qSql = "SELECT t_nickName,t_handImg FROM t_user WHERE t_id = ? ";

			Map<String, Object> userMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql,
					t_content_user);

			String inSql = "INSERT INTO t_user_evaluation (t_user_id, t_user_hand, t_user_nick, t_anchor_id, t_score, t_create_time) VALUES (?,?,?,?,?,?) ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_content_user, userMap.get("t_handImg"),
					userMap.get("t_nickName"), t_anchor_id, t_score,
					DateUtils.format(new Date(), DateUtils.FullDatePattern));

			// 查询出当前人的最新评价记录 并取得评价编号
			String sql = "SELECT t_id FROM t_user_evaluation WHERE t_user_id = ? ORDER BY t_create_time DESC LIMIT 0,1";

			Map<String, Object> t_id = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql,
					t_content_user);

			// 分割评价标签
			String[] split = lables.split(",");

			sql = "INSERT INTO t_discuss_record(t_label_id, t_evaluation_id) VALUES ( ?, ?) ";
			// 循环把评价标签插入到数据库中
			for (int i = 0; i < split.length; i++) {
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, split[i],
						Integer.parseInt(t_id.get("t_id").toString()));
			}

			mu = new MessageUtil(1, "更新成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加评论异常.", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil sendPushMsg(int t_id, String push_connent) {
		try {

			String messageSql = "INSERT INTO t_message (t_user_id, t_message_content, t_create_time, t_is_see) VALUES (?, ?, ?, ?);";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(messageSql, t_id, push_connent,
					DateUtils.format(new Date(), DateUtils.FullDatePattern), 0);
			// 推送自定义消息
			PushUtil.sendPush(t_id, push_connent, "0");

			mu = new MessageUtil(1, "发送成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("推送消息异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil giveUserGold(int t_id, int goid,int role_id) {
		try {

			rebateRedPacket(role_id, t_id, "官方赠送[" + goid + "]个金币!", new BigDecimal(goid), 3);

			return new MessageUtil(1, "操作成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[{}]赠送金币异常!", t_id, e);
		}
		return null;
	}

	/**
	 * 存储红包记录
	 * 
	 * @param t_hair_userId       贡献者
	 * @param t_receive_userId    接收人
	 * @param t_redpacket_content 提示内容
	 * @param t_redpacket_gold    金币
	 * @param t_redpacket_type    红包类型 0.赠送红包 1.贡献红包2.主播认证红包 3.后台添加红包
	 */
	private void rebateRedPacket(int t_hair_userId, int t_receive_userId, String t_redpacket_content,
			BigDecimal t_redpacket_gold, int t_redpacket_type) {
		String sql = "INSERT INTO t_redpacket_log (t_hair_userId, t_receive_userId, t_redpacket_content, t_redpacket_gold, t_redpacket_draw, t_redpacket_type, t_create_time) VALUES (?, ?, ?, ?, ?, ?, ?);";

		this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_hair_userId, t_receive_userId, t_redpacket_content,
				t_redpacket_gold, 0, t_redpacket_type, DateUtils.format(new Date(), DateUtils.FullDatePattern));
	}

	@Override
	public MessageUtil getNominate(int userId) {
		try {

			String qSql = " SELECT * FROM t_spread WHERE t_user_id = ? ";
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, userId);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			if (!dataList.isEmpty()) {
				mu.setM_object(dataList.get(0));
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取主播推广设置异常!", e);
		}
		return mu;
	}

	@Override
	public MessageUtil saveOrUpdateNominate(int userId, int t_is_nominate,int t_sort) {
		try {

			//取消推荐
			if(t_is_nominate == 0) {
				String uSql = " DELETE FROM t_spread  WHERE t_user_id = ? ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, userId);
				
			}else { //新增或者修改推荐值
				String qSql = " SELECT * FROM t_spread WHERE t_user_id = ? ";

				List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, userId);

				if (dataList.isEmpty()) {

					String inSql = " INSERT INTO t_spread (t_user_id, t_is_nominate,t_sort) VALUES (?, ?, ?); ";
					this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, userId, t_is_nominate,t_sort);

				} else {
					String uSql="UPDATE t_spread SET  t_sort= ? WHERE t_user_id = ?;";
					this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_sort,userId);
				}
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或者设置主播推广异常!", e);
		}
		return mu;
	}

	@Override
	public MessageUtil getFreeAnchor(int userId) {
		try {

			String qSql = " SELECT * FROM t_free_anthor WHERE t_user_id = ?";

			List<Map<String, Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, userId);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(sqltoMap.isEmpty() ? -1 : 1);

		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil alterationFreeAnchor(int userId, int t_is_free) {

		try {

			if (t_is_free == 1) {

				String qSql = " SELECT * FROM t_free_anthor WHERE t_user_id = ? ";
				List<Map<String, Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, userId);

				if (sqltoMap.isEmpty()) {
					String inSql = " INSERT INTO t_free_anthor (t_user_id, t_is_free) VALUES (?, ?); ";
					this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, userId, t_is_free);
				}
			} else {
				String dSql = " DELETE FROM t_free_anthor WHERE t_user_id = ?  ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, userId);
			}

			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("改变免费主播状态", e);
			mu = new MessageUtil(0, "程序异常!");
		}

		return mu;
	}

	@Override
	public JSONObject getImLogList(String condition, String beginTime, String endTime, int page) {
		try {

			StringBuffer body = new StringBuffer();
			body.append(
					"SELECT u.t_id,u.t_nickName,u.t_phone,u.t_disable,ul.t_nickName  AS coveNickName,ul.t_phone AS covePhone,l.t_content,DATE_FORMAT(l.t_create_time,'%Y-%m-%d %T') AS t_create_time ");
			body.append(
					"FROM t_im_log l LEFT JOIN t_user u ON l.t_send_user_id = u.t_id LEFT JOIN t_user ul ON l.t_accept_user_id = ul.t_id ");
			body.append("WHERE 1 =1 ");

			if (StringUtils.isNotBlank(condition)) {
				body.append("AND (");
				body.append("u.t_idcard = '").append(condition).append("' OR ");
				body.append("u.t_nickName  LIKE '%").append(condition).append("%' OR ");
				body.append("u.t_phone LIKE '%").append(condition).append("%' OR ");
				body.append("ul.t_idcard = '").append(condition).append("' OR ");
				body.append("ul.t_nickName LIKE '%").append(condition).append("%' OR ");
				body.append("ul.t_phone LIKE '%").append(condition).append("%' ");
				body.append(")");
			}

			if (StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {
				body.append(" AND l.t_create_time BETWEEN '").append(beginTime).append(" 00:00:00'");
				body.append(" AND '").append(endTime).append(" 23:59:59'");
			}

			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap("SELECT COUNT(aa.t_id) AS total FROM (" + body + ") aa");

			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
					"SELECT * FROM (" + body + ") aa ORDER BY aa.t_create_time DESC LIMIT ?,10", (page - 1) * 10);

			dataList.forEach(s -> {
				if (null == s.get("t_nickName")) {
					s.put("t_nickName",
							s.get("t_phone").toString().substring(s.get("t_phone").toString().length() - 4));
				}
				if (null == s.get("coveNickName")) {
					s.put("coveNickName",
							s.get("covePhone").toString().substring(s.get("covePhone").toString().length() - 4));
				}
			});

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", dataList);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取IM消息列表异常!", e);
		}
		return null;
	}

	@Override
	public JSONObject getAnchorOnlineTime(int userId, String beginTime, String endTime, int page) {
		try {

			String cSql = "SELECT COUNT(t_id) AS total FROM t_log_time WHERE t_user_id = ? ";

			String qSql = "SELECT DATE_FORMAT(t_login_time,'%Y-%m-%d %T') AS login_time, DATE_FORMAT(t_logout_time,'%Y-%m-%d %T') AS logout_time,t_duration FROM t_log_time WHERE t_user_id = ?";

			if (StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {
				cSql += " AND t_login_time BETWEEN '" + beginTime + " 00:00:00' AND  '" + endTime + " 23:59:59'";
				qSql += " AND t_login_time BETWEEN '" + beginTime + " 00:00:00' AND  '" + endTime + " 23:59:59'";
			}

			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql, userId);

			List<Map<String, Object>> data = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap(qSql + " ORDER BY t_id DESC LIMIT ?,10", userId, (page - 1) * 10);

			data.forEach(s -> {
				if (null != s.get("t_duration"))
					s.put("t_duration", DateUtils.getConvert(Integer.parseInt(s.get("t_duration").toString())));
			});

			return JSONObject.fromObject(new HashMap<String, Object>() {
				{
					put("total", total.get("total"));
					put("rows", data);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil setUserOnLine(int userId) {
		try {

			String qSql = " SELECT t_role,t_online_setup FROM t_user WHERE t_id = ? ";
			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, userId);
			String uSql = null;
			if (null == toMap.get("t_online_setup") || 0 == Integer.parseInt(toMap.get("t_online_setup").toString())) {
				uSql = " UPDATE t_user set t_online_setup = 1 WHERE t_id = ? ";
				// 设置用户在线
				this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_user SET t_onLine = 0 WHERE t_id = ?  ",
						userId);
				// 判断用户是否是主播
				if (1 == Integer.parseInt(toMap.get("t_role").toString())) {
					this.getFinalDao().getIEntitySQLDAO()
							.executeSQL("UPDATE t_anchor SET t_state = 0 WHERE t_user_id = ? ", userId);
				}

			} else if (1 == Integer.parseInt(toMap.get("t_online_setup").toString())) {
				uSql = " UPDATE t_user set t_online_setup = 0 WHERE t_id = ? ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_user SET t_onLine = 1 WHERE t_id = ?  ",
						userId);
				// 判断用户是否是主播
				if (1 == Integer.parseInt(toMap.get("t_role").toString())) {
					this.getFinalDao().getIEntitySQLDAO()
							.executeSQL("UPDATE t_anchor SET t_state = 2 WHERE t_user_id = ? ", userId);
				}
			}
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, userId);

			return new MessageUtil(1, "设置成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	/**
	 * 重置推广人
	 */
	@Override
	public MessageUtil setRefereeUser(int t_id, int t_referee_id) {
		try {

			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_user SET t_referee = ? WHERE t_id = ? ",
					t_referee_id - 10000, t_id);
			return new MessageUtil(1, "已设置!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil upUserData(int t_id, String t_nickName, String t_phone, Integer t_modal_sex, Integer t_age,
			Integer t_height, Integer t_weight, String t_constellation, String t_city, String t_vocation,
			String t_synopsis, String t_autograph,int t_user_role) {
		try {
			StringBuffer body = new StringBuffer();
			body.append("UPDATE t_user SET ");
			if(StringUtils.isNotBlank(t_nickName)) {
				body.append("t_nickName = '").append(t_nickName).append("'");
			}
			if(StringUtils.isNotBlank(t_phone)) {
				body.append(",t_phone = '").append(t_phone).append("'");			
			}
			if(null != t_modal_sex && (t_modal_sex == 0 || t_modal_sex == 1)) {
				body.append(",t_sex = ").append(t_modal_sex);
			}
			if(null != t_age) {
				body.append(",t_age = ").append(t_age);
			}
			if(null != t_height) {
				body.append(",t_height = ").append(t_height);
			}
			if(null != t_weight ) {
				body.append(",t_weight = ").append(t_weight);
			}
			if(StringUtils.isNotBlank(t_constellation)) {
				body.append(",t_constellation = '").append(t_constellation).append("'");
			}
			if(StringUtils.isNotBlank(t_city)) {
				body.append(",t_city = '").append(t_city).append("'");
			}
			if(StringUtils.isNotBlank(t_vocation)) {
				body.append(",t_vocation = '").append(t_vocation).append("'");
			}
			if(StringUtils.isNotBlank(t_synopsis)) {
				body.append(",t_synopsis = '").append(t_synopsis).append("'");
			}
			if(StringUtils.isNotBlank(t_autograph)) {
				body.append(",t_autograph ='").append(t_autograph).append("'");
			}
			//判断用户角色是否修改
			if(t_user_role == 1) {
				//判断当前人是否已经是主播 
				//如果是 那么无须插入数据
				//否则写入默认数据
				List<Map<String, Object>> anthorSetUp = this.getFinalDao().getIEntitySQLDAO()
						.findBySQLTOMap("SELECT * FROM t_anchor_setup WHERE t_user_id = ? ", t_id);
				//如果该用户没有收费设置 那么设置收费设置
				if(anthorSetUp.isEmpty()) {
					
					//查询出系统默认的收费设置
					List<Map<String, Object>> systemSetUp = this.getFinalDao().getIEntitySQLDAO()
							.findBySQLTOMap("SELECT t_default_text,t_default_video,t_default_phone,t_default_weixin FROM t_system_setup");
					
					if(!systemSetUp.isEmpty()) {
						// 给主播插入默认的收费设置
						this.updateAnchorChargeSetup(t_id, 
								new BigDecimal(systemSetUp.get(0).get("t_default_video").toString()),
								new BigDecimal(systemSetUp.get(0).get("t_default_text").toString()), 
								new BigDecimal(systemSetUp.get(0).get("t_default_phone").toString()), 
								new BigDecimal(systemSetUp.get(0).get("t_default_weixin").toString()), 
								null, null);
					}
				}
				
			}
			body.append(",t_role = ").append(t_user_role);
			
		
			body.append(" WHERE t_id = ? ");
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(body.toString(), t_id);
			
			return new MessageUtil(1, "修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 设置默认收费设置
	 * @param t_user_id 用户编号
	 * @param t_video_gold 默认视频金币 
	 * @param t_text_gold 默认文本聊天金币
	 * @param t_phone_gold 默认查看手机金币
	 * @param t_weixin_gold 默认查看微信金币
	 * @param t_private_photo_gold 默认查看私密照片金币
	 * @param t_private_video_gold 默认查看私密视频金币
	 */
	private void updateAnchorChargeSetup(int t_user_id,BigDecimal t_video_gold, BigDecimal t_text_gold, BigDecimal t_phone_gold,
			BigDecimal t_weixin_gold, BigDecimal t_private_photo_gold,
			BigDecimal t_private_video_gold) {

		try {

			String query = "select count(t_id) as total from t_anchor_setup where t_user_id = ?";

			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap(query, t_user_id);
			
			// 新增
			if (0 == Integer.parseInt(map.get("total").toString())) {

				String inSql = "INSERT INTO t_anchor_setup (t_user_id, t_video_gold, t_text_gold, t_phone_gold, t_weixin_gold) VALUES (?,?,?,?,?);";

				this.getFinalDao()
						.getIEntitySQLDAO()
						.executeSQL(inSql, t_user_id, t_video_gold,
								t_text_gold, t_text_gold, t_weixin_gold);

			} else { // 修改
				String sql = "UPDATE t_anchor_setup SET ";
				if (t_video_gold.compareTo(new BigDecimal(0)) > 0) {
//					//验证主播
//					//金币数修改为10——50这个区间。主播可以选择：10,15,20,25,30,35,40,45,50.
//					//新主播最高只能设置每分钟30，提现达到100后，
//					//才有设置40的选项，提现500后才有设置成50的选项
//                    //获取主播是否存在提现
//					String qSql = " SELECT SUM(t_money) AS totalMoney FROM t_put_forward  WHERE t_user_id  = ? ";
//					Map<String, Object> putMoney = this.getMap(qSql, t_user_id);
//					
//					int totalMoney = new BigDecimal(null == putMoney.get("totalMoney")?"0":putMoney.get("totalMoney").toString()).intValue();
//					
//					if((totalMoney < 100 && t_video_gold<=30 && t_video_gold > 0)
//							|| (totalMoney >=100 && totalMoney < 500 && t_video_gold <=40 && t_video_gold > 0)
//							|| (totalMoney >= 500 && t_video_gold <= 50 && t_video_gold > 0)) {
//					
						sql = sql + " t_video_gold = " + t_video_gold + ",";
//					}else {
//						return new MessageUtil(-1, "视频聊天收费异常!");
//					}
				}
				if (t_text_gold.compareTo(new BigDecimal(0)) > 0) {
					sql = sql + " t_text_gold = " + t_text_gold + ",";
				}
				if (t_weixin_gold.compareTo(new BigDecimal(0)) > 0) {
					sql = sql + " t_weixin_gold = " + t_weixin_gold + ",";
				}
				if (t_private_photo_gold.compareTo(new BigDecimal(0)) > 0) {
					sql = sql + " t_private_photo_gold = "
							+ t_private_photo_gold + ",";
				}
				if (t_private_video_gold.compareTo(new BigDecimal(0)) > 0) {
					sql = sql + " t_private_video_gold = "
							+ t_private_video_gold + ",";
				}
				sql = sql + " t_phone_gold = " + t_phone_gold + ",";

				if (sql.indexOf(",") > 0) {

					sql = sql.substring(0, sql.lastIndexOf(","));

					sql = sql + "  WHERE t_user_id = ?";

					this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_user_id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改获取主播收费设置", e);
		}
	}
}
