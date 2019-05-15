package com.yiliao.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.CoverExamineService;
import com.yiliao.util.MessageUtil;

@Service("coverExamineService")
public class CoverExamineServiceImpl extends ICommServiceImpl implements
		CoverExamineService {

	private MessageUtil mu = null;

	/*
	 * 获取封面列表(non-Javadoc)
	 * 
	 * @see com.yiliao.service.CoverExamineService#getCoverExamineList(int)
	 */
	@Override
	public JSONObject getCoverExamineList(String condition,int page) {

		JSONObject json = new JSONObject();

		try {

			StringBuffer sb = new StringBuffer();
			sb.append("SELECT ");
			sb.append("u.t_nickName,u.t_sex,u.t_id,u.t_idcard ");
			sb.append("FROM ");
			sb.append("t_cover_examine c ");
			sb.append("LEFT JOIN t_user u ON c.t_user_id = u.t_id ");
			sb.append("WHERE  c.t_is_examine = 0 ");
			if(StringUtils.isNotBlank(condition)){
				sb.append(" AND u.t_nickName LIKE '%"+condition+"%'");
			}
			
			sb.append("GROUP BY u.t_id ");
			sb.append("LIMIT ?,5");

			List<Map<String, Object>> dataList = this.getFinalDao()
					.getIEntitySQLDAO()
					.findBySQLTOMap(sb.toString(), (page - 1) * 5);

			for (Map<String, Object> m : dataList) {
				
				String coverSql  = "SELECT t_id,t_first,t_img_url,t_is_examine FROM t_cover_examine WHERE t_user_id = "+Integer.parseInt(m.get("t_id").toString())+" AND  t_is_examine = 0 ";

				List<Map<String, Object>> coverList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(coverSql);
				
				List<Object> orderBy = orderBy(JSONArray.fromObject(coverList));

				for (int i = 0; i < orderBy.size(); i++) {
					m.put("img_" + i, orderBy.get(i));
				}
			}

			String countSql = "SELECT count(t_id) as total FROM ( SELECT u.t_id  FROM t_cover_examine c LEFT JOIN t_user u ON c.t_user_id=u.t_id WHERE c.t_is_examine = 0 " ;
			
			if(StringUtils.isNotBlank(condition)){
				countSql =  countSql +" AND c.t_nickName LIKE '%"+condition+"%'";
			}
		    countSql = 	countSql	+ "  GROUP BY u.t_id ) tab ";

			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap(countSql);

			json.put("total", total.get("total"));
			json.put("rows", dataList);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取封面列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return json;
	}

	/*
	 * 设置为主封面(non-Javadoc)
	 * 
	 * @see com.yiliao.service.CoverExamineService#setUpFirst(int, int)
	 */
	@Override
	public MessageUtil setUpFirst(int t_id, int t_user_id) {

		try {

			// 修改所有的封面为基础封面
			String sql = "UPDATE t_cover_examine SET t_first = 1 WHERE t_user_id = ? ;";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_user_id);
			// 修改当前封面为主封面
			sql = "UPDATE t_cover_examine SET t_first = 0,t_is_examine=1 WHERE t_id = ?";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);

			// 修改主封面到用户表中封面图片

			sql = "UPDATE t_user u ,t_cover_examine c SET "
					+ "u.t_cover_img = c.t_img_url "
					+ "WHERE 1=1 AND c.t_user_id=u.t_id AND c.t_id=?";

			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);

			mu = new MessageUtil(1, "操作成功!");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("设置为主封面异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}

		return mu;
	}

	/**
	 * 给jsonarray排序 主封面
	 * 
	 * @param array
	 * @return
	 */
	private List<Object> orderBy(JSONArray array) {
		List<Object> arr = new ArrayList<Object>();

		for (int i = 0; i < array.size(); i++) {

			JSONObject json = array.getJSONObject(i);

			if (json.getString("t_first").equals("0")) {
				arr.add(0, json);
			} else {
				arr.add(json);
			}
		}
		return arr;
	}

	@Override
	public MessageUtil getUserCoverExamineList(int t_user_id) {
		try {
			
			// 查询数据
			String sql = "SELECT t_id,t_img_url,t_first FROM t_cover_examine WHERE t_user_id = ? order by t_create_time DESC,t_first asc";
			List<Map<String, Object>> listMap = this.getFinalDao()
					.getIEntitySQLDAO()
					.findBySQLTOMap(sql, t_user_id);

			JSONArray array = new JSONArray();
			if (null != listMap && !listMap.isEmpty()) {
				int group = listMap.size() % 2 == 0 ? listMap.size() / 2
						: listMap.size() / 2 + 1;
				 if (group == 2) {
					array.add(listMap.subList(0, 2));
					array.add(listMap.subList(2, listMap.size()));
				} else {
					array.add(listMap);
				}
			}

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(array);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取封面列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 审核通过(non-Javadoc)
	 * @see com.yiliao.service.CoverExamineService#throughAudit(int)
	 */
	@Override
	public MessageUtil throughAudit(int t_id) {
		try {
			
			String sql = "UPDATE t_cover_examine SET t_is_examine = 1 WHERE t_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			//获取当前更新的数据
		    String qSql = " SELECT t_first,t_user_id,t_img_url FROM t_cover_examine WHERE t_id  = ? ";
		    
		    Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_id);
			
		    if(toMap.get("t_first").toString().equals("0")) {
		    	//更新到用户表中
		    	String uSql = " UPDATE t_user SET t_cover_img = ? WHERE t_id = ?  ";
		    	
		    	this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql,toMap.get("t_img_url") , toMap.get("t_user_id"));
		    	
		    }
		    
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取封面列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/**
	 * 审核失败
	 */
	@Override
	public MessageUtil delCoverData(int t_id) {
		try {
			
			//根据编号得到用户编号
			String qSql = "SELECT t_user_id FROM t_cover_examine WHERE t_id = ? ";
			
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, t_id);
			
			if(sqltoMap.size()  == 1) {
				String uSql = "UPDATE t_user SET t_cover_img = NULL WHERE t_id = ?";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, sqltoMap.get(0).get("t_user_id"));
			}
			
			String sql = "DELETE FROM t_cover_examine WHERE t_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			
			
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取封面列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil getCoverList(int t_user_id) {
try {
			

			// 查询数据
			String sql = "SELECT t_id,t_img_url,t_first FROM t_cover_examine WHERE t_user_id = ? order by t_first asc";
			List<Map<String, Object>> listMap = this.getFinalDao()
					.getIEntitySQLDAO()
					.findBySQLTOMap(sql, t_user_id);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(listMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取封面列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}
	

}
