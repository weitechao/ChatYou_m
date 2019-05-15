package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.RoleService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
/**
 * 角色service impl
 * @author Administrator
 *
 */
@Service("roleService")
public class RoleServiceImpl extends ICommServiceImpl implements RoleService {

	@Override
	public JSONObject getRoleList(int page) {
		JSONObject json = new JSONObject();
		try {
			String countSql = "SELECT COUNT(t_id) AS total  FROM t_role ";
			String sql = "SELECT t_id,t_role_name,t_enable,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time  FROM t_role LIMIT ?,10";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, (page-1)*10);
		
			json.put("total", total.get("total"));
			json.put("rows", dataMap);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/*
	 * 添加或者修改角色(non-Javadoc)
	 * @see com.yiliao.service.RoleService#saveRole(java.lang.Integer, java.lang.String, int)
	 */
	@Override
	public MessageUtil saveRole(Integer t_id, String t_role_name, int t_enable) {
		
		MessageUtil mu = null;
		try {
			//新增
			if(null == t_id || 0 == t_id){
				String sql = " INSERT INTO t_role (t_role_name, t_enable, t_create_time) VALUES (?, ?, ?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_role_name,t_enable,DateUtils.format(new Date(),
						DateUtils.FullDatePattern));
				
			}else{ //修改
				
				String sql = " UPDATE t_role SET  t_role_name=?, t_enable=?, t_create_time=? WHERE t_id=?;";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_role_name,t_enable,DateUtils.format(new Date(),
						DateUtils.FullDatePattern),t_id);
				
			}
			
			mu = new MessageUtil(1,"操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 删除数据(non-Javadoc)
	 * @see com.yiliao.service.RoleService#delRole(int)
	 */
	@Override
	public MessageUtil delRole(int t_id) {
		
		MessageUtil mu = null;
		try {
			
			String sql = "DELETE FROM t_role WHERE t_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 获取启用角色列表(non-Javadoc)
	 * @see com.yiliao.service.RoleService#getRoleEnableList()
	 */
	@Override
	public MessageUtil getRoleEnableList(int roleId) {
		MessageUtil mu = null;
		try {
			
			String sql = "SELECT t_id,t_role_name FROM t_role WHERE t_enable = 0";
			
			if(roleId != 1) {
				sql = sql + " AND t_id = "+roleId;
			}
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取启用角色列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil saveAuthority(int t_role_id, String meunIds) {
		
		MessageUtil mu = new  MessageUtil();
		try {
			
			String[] str = meunIds.split(",");
			
			String delSql = "DELETE FROM t_authority WHERE t_role_id = ? ;";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, t_role_id);
		 
			String inseSql = " INSERT INTO t_authority (t_role_id, t_menu_id) VALUES (?, ?);";
			//迭代数据 插入数据
			for(String s : str){
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inseSql,t_role_id,Integer.valueOf(s));
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("分配权限异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
