package com.yiliao.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.SystemSetUpService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

/**
 * 系统设置服务层实现累
 * 
 * @author Administrator
 *
 */
@Service("systemSetUpService")
public class SystemSetUpServiceImpl extends ICommServiceImpl implements SystemSetUpService {

	private MessageUtil mu = null;

	@Override
	public JSONObject getDivideIntoList(int page) {

		JSONObject json = new JSONObject();

		try {

			String sql = "SELECT t_id,t_project_type,t_extract_ratio,DATE_FORMAT(t_create_time,'%Y-%m-%d') AS t_create_time FROM t_extract limit ?,10";

			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql,
					(page - 1) * 10);

			String totalSql = "SELECT count(t_id) AS total FROM t_extract";

			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(totalSql);

			json.put("total", totalMap.get("total"));
			json.put("rows", dataList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 修改分成比例
	 */
	@Override
	public MessageUtil updateSystem(int t_id, String t_extract_ratio) {
		try {

			String sql = "UPDATE t_extract  SET t_extract_ratio = ? WHERE t_id = ? ;";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_extract_ratio, t_id);

			mu = new MessageUtil(1, "更新成功!");

		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public JSONObject getVersionList(int page) {
		JSONObject json = new JSONObject();
		try {
			String couSql = "SELECT count(t_id) AS total FROM t_version ORDER BY t_id DESC";

			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(couSql);

			// 获取数据列表
			String querySql = "SELECT * FROM t_version LIMIT ?,10;";

			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(querySql,
					(page - 1) * 10);

			json.put("total", totalMap.get("total"));
			json.put("rows", dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 添加版本
	 */
	@Override
	public MessageUtil addVersion(String t_download_url, int t_is_new, String t_version, String t_version_depict,
			String t_version_type, String t_onload_path) {
		try {
			if (t_is_new == 1) {

				// 先已修改原来的版本为低版本
				String uSql = "UPDATE t_version SET t_is_new = 0 WHERE t_version_type= ? ";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_version_type);
			}

			String inSql = "INSERT INTO t_version (t_download_url, t_is_new, t_version,t_version_depict,t_version_type) VALUES (?,?,?,?,?)";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql,
					"android".equals(t_version_type) ? t_download_url : t_onload_path, t_is_new, t_version,
					t_version_depict, t_version_type);

			mu = new MessageUtil(1, "添加成功!");

		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/**
	 * 删除版本
	 */
	@Override
	public MessageUtil delVersion(int t_id) {
		try {

			String delSql = "DELETE FROM t_version WHERE t_id = ? ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, t_id);

			mu = new MessageUtil(1, "删除成功!");

		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public JSONObject getRankingList() {
		try {

			String qSql = "SELECT * FROM t_ranking_control";

			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql);

			JSONObject json = new JSONObject();
			json.put("total", 1);
			json.put("rows", dataList);

			return json;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取排行榜设置异常!", e);
		}
		return null;
	}

	@Override
	public MessageUtil upRankData(int id, int t_charm_number, int t_consumption_number, int t_courtesy_number) {

		try {

			String uSql = " UPDATE t_ranking_control SET  t_charm_number=?, t_consumption_number=?, t_courtesy_number=? WHERE t_id = ? ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_charm_number, t_consumption_number,
					t_courtesy_number, id);

			return new MessageUtil(1, "已修改!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改排行榜加载数据异常!", e);
		}
		return null;
	}

	@Override
	public JSONObject getStyleSetUp(int page) {
		try {

			String qSql = "SELECT * FROM t_style_setup ";

			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql);

			String cSql = " SELECT COUNT(t_id) AS total FROM t_style_setup  ";

			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", dataList);

			return json;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取风格列表异常!", e);
		}
		return null;
	}

	@Override
	public MessageUtil saveSeyleSetUp(Integer t_id, String t_style_name, String t_mark, int t_state) {
		try {

			// 判断当前风格是否启用
			if (t_state == 1) {
				String uSql = " UPDATE t_style_setup SET   t_state = 0 ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql);
			}

			if (null == t_id || 0 == t_id) {
				String inSql = " INSERT INTO t_style_setup (t_style_name, t_mark, t_state) VALUES ( ?,?,? )";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_style_name, t_mark, t_state);
			} else {
				String uSql = " UPDATE t_style_setup SET t_style_name = ?,t_mark = ? ,  t_state = ? WHERE t_id = ? ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_style_name, t_mark, t_state, t_id);
			}
			return new MessageUtil(1, "操作成功!");

		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	@Override
	public MessageUtil delStyleSetUp(int t_id) {
		try {
			String dSql = " DELETE FROM t_style_setup WHERE t_id =  ? ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);

			return new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	@Override
	public JSONObject getSpreadAwardList(int page) {
		try {

			String qSql = "SELECT * FROM t_spread_award ";

			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql);

			String cSql = " SELECT COUNT(t_id) AS total FROM t_spread_award  ";

			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);

			JSONObject json = new JSONObject();
			json.put("total", toMap.get("total"));
			json.put("rows", dataList);

			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil saveOrUpSpreadAward(Integer t_id, int t_gold, int t_rank, int t_sex) {
		try {

			if (null == t_id || 0 == t_id) {

				String inSql = "INSERT INTO t_spread_award (t_gold, t_sex, t_rank) VALUES (?,?,?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_gold, t_sex, t_rank);
			} else {
				String uSql = " UPDATE t_spread_award SET  t_gold=?, t_sex=?, t_rank=? WHERE t_id= ?;";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_gold, t_sex, t_rank, t_id);
			}

			return new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	@Override
	public MessageUtil delSpreadAward(int t_id) {
		try {
			String dSql = " DELETE FROM t_spread_award WHERE t_id = ? ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);

			return new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	@Override
	public MessageUtil getSystemSetUpDateil() {
		try {

			String qSql = " SELECT * FROM t_system_setup ";

			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);

			MessageUtil mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(map);
			return mu;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取系统设置异常!", e);
			return new MessageUtil(0, "程序异常!");
		}
	}

	@Override
	public MessageUtil setSystemSetUp(int t_id, int t_scope, String t_android_download, String t_ios_download,
			String t_system_lang_girl, String t_system_lang_male, BigDecimal t_default_text, BigDecimal t_default_video,
			BigDecimal t_default_phone, BigDecimal t_default_weixin, String t_award_rules, String t_service_qq,
			String t_nickname_filter,String t_video_hint,String t_spreed_hint) {

		try {

			String uSql = " UPDATE t_system_setup SET t_scope = ? ,t_android_download = ? ,t_ios_download = ?, t_system_lang_girl = ?, t_system_lang_male = ?,t_default_text = ? ,t_default_video = ?,t_default_phone = ?,t_default_weixin = ?,t_award_rules = ?,t_service_qq = ? ,t_nickname_filter = ?,t_video_hint = ? ,t_spreed_hint = ?  WHERE t_id = ?  ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_scope, t_android_download, t_ios_download,
					t_system_lang_girl, t_system_lang_male, t_default_text, t_default_video, t_default_phone,
					t_default_weixin, t_award_rules, t_service_qq, t_nickname_filter,t_video_hint,t_spreed_hint, t_id);

			return new MessageUtil(1, "系统设置已更新!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新系统设置异常!", e);
			return new MessageUtil(0, "程序异常!");
		}
	}

	/**
	 * 获取帮助列表
	 */
	@Override
	public Map<String, Object> getHelpConter(int page) {
		try {

			String cSql = "SELECT COUNT(t_id) AS total  FROM t_help_center ";

			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			// 获取数据明细 
			List<Map<String, Object>> list = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
					"SELECT t_id,t_title,t_content,t_sort,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_help_center LIMIT ?,10;",
					(page - 1) * 10);

			map.put("rows", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil addHelpConter(Integer t_id, String title, String content, int t_sort) {
		try {
			// 判断是修改还是新增
			if (null == t_id) {
				String inSql = "INSERT INTO t_help_center (t_title, t_content, t_sort,t_create_time) VALUES (?,?,?,?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, title, content, t_sort,
						DateUtils.format(new Date(), DateUtils.FullDatePattern));
			} else {
				String uSql = "UPDATE t_help_center SET t_title=?, t_content=?,t_sort = ? WHERE t_id = ?;";

				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, title, content, t_sort, t_id);
			}
			return new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除帮助内容
	 */
	@Override
	public MessageUtil delHelpContre(int t_id) {
		try {

			String delSql = "DELETE FROM t_help_center WHERE t_id = ? ";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, t_id);

			return new MessageUtil(1, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
