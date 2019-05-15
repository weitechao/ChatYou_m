package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.BannedService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

@Service("bannedService")
public class BannedServiceImpl extends ICommServiceImpl implements BannedService {

	@Override
	public JSONObject getBannedList(int page) {
		JSONObject json = new JSONObject();
		try {
			String totalSql = "SELECT count(t_id) FROM t_banned_setup";
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(totalSql);
			
			String sql = "SELECT t_id,t_count,t_hours FROM t_banned_setup ORDER BY t_count ASC  LIMIT ?,10;";
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, (page-1)*10);
			
			json.put("total", totalMap.get("total"));
			json.put("rows", dataList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取封号设置列表异常!", e);
		}
		return json;
	}

	/**
	 * 新增或者修改
	 */
	@Override
	public MessageUtil saveOrUpdateBanned(Integer t_id, int t_count, Double t_hours) {
		
		MessageUtil  mu  =null;
		try {
			
			//新增
			if(null == t_id || 0 == t_id){
				String sql  = "INSERT INTO t_banned_setup (t_count, t_hours, t_createtime) VALUES (?, ?, ?);";
				this.getFinalDao().getIEntitySQLDAO()
				.executeSQL(sql, t_count,t_hours,DateUtils.format(new Date(), DateUtils.FullDatePattern));
				
			}else{ //修改
				
				String sql = "UPDATE t_banned_setup SET t_count=?, t_hours=? WHERE t_id=?;";
				
				this.getFinalDao().getIEntitySQLDAO()
				.executeSQL(sql, t_count,t_hours,t_id);
				
			}
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加封号设置异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/**
	 * 删除封号设置
	 */
	@Override
	public MessageUtil delBannedSetUp(int t_id) {
		
		MessageUtil mu = null;
		try {
			
			String sql = "DELETE FROM t_banned_setup WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除禁用设置异常.", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
