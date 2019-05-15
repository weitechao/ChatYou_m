package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.yiliao.service.EnrollService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

@Service("enrollService")
public class EnrollServiceImpl extends ICommServiceImpl implements
		EnrollService {

	@Override
	public JSONObject getEnrollList(int page) {
		try {
			
			String cSql = " SELECT count(t_id) AS total FROM t_enroll ";
			
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			String qSql = "SELECT t_id,t_sex,t_gold,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_enroll  LIMIT ?, 10 ;";
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, (page-1)*10);
			
			JSONObject json = new JSONObject();
			
			json.put("total", totalMap.get("total"));
			json.put("rows", dataList);
			
			return json;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil addEnroll(int t_sex, int t_gold) {
		
		MessageUtil mu = null;
		try {
			
			String inSql = " INSERT INTO t_enroll (t_sex, t_gold, t_create_time) VALUES (?,?,?) ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_sex,t_gold,
					DateUtils.format(new Date(), DateUtils.FullDatePattern));
			
			mu = new MessageUtil(1, "已添加.");
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil delEnroll(int t_id) {
		
		MessageUtil mu = null ;
		try {
			
			String dSql = "DELETE FROM t_enroll WHERE t_id= ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
			
			mu = new MessageUtil(1, "删除完成!");
			
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
