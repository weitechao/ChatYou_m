package com.yiliao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.MenuService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

@Service("menuService")
public class MenuServiceImpl extends ICommServiceImpl implements MenuService {

	@Override
	public JSONObject getMenuList(int page) {
		JSONObject json = new JSONObject();
		try {
			
			String contSql = "SELECT count(t_id) AS total FROM t_menu";
			String sql = "SELECT m.t_id,m.t_menu_name,m.t_menu_url,e.t_menu_name AS father_anme,m.t_icon,DATE_FORMAT(m.t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_menu m LEFT JOIN t_menu e ON m.t_father_id=e.t_id  LIMIT ?,10";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(contSql);
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, (page-1)*10);
			
			json.put("total", total.get("total"));
			json.put("rows", dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取菜单列表异常!", e);
		}
		return json;
	}

	@Override
	public MessageUtil getFatherList() {
		MessageUtil mu = null;
		try {
			String sql = "SELECT t_id,t_menu_name FROM t_menu WHERE t_father_id = 0;";
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 添加或者修改菜单(non-Javadoc)
	 * @see com.yiliao.service.MenuService#saveMenu(java.lang.Integer, java.lang.String, java.lang.String, int)
	 */
	@Override
	public MessageUtil saveMenu(Integer t_id, String t_menu_name,
			String t_menu_url, int t_father_id,String t_icon) {
		MessageUtil mu = null;
		try {
			//新增
			if(null == t_id || 0 == t_id){
				
				String sql = "INSERT INTO t_menu (t_menu_name, t_menu_url, t_create_time, t_father_id,t_icon) VALUES ( ?, ?, ?, ?,?);";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_menu_name,t_menu_url,
						DateUtils.format(new Date(), DateUtils.FullDatePattern),t_father_id,t_icon);
				
			}else{ //修改
				String sql = " UPDATE t_menu SET t_menu_name= ?, t_menu_url=?, t_father_id=?,t_icon=? WHERE t_id=?;";
			
			    this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_menu_name,t_menu_url,t_father_id,t_icon,t_id);
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 删除菜单(non-Javadoc)
	 * @see com.yiliao.service.MenuService#delMenu(int)
	 */
	@Override
	public MessageUtil delMenu(int t_id) {
		
		MessageUtil mu = null;
		
		try {
			
			String delSql = "DELETE FROM t_menu WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil getMenuTreeList(int roleId) {
		
		MessageUtil mu = new MessageUtil();
		try {
			
			String sql = "SELECT t_id,t_menu_name AS text FROM t_menu WHERE t_father_id = ?;";
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql,0);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("checked", true);
			
			//迭代
			for(Map<String, Object> m : dataMap){
				
				boolean isOk = false;
				List<Map<String, Object>> sonMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql,m.get("t_id"));
			    if(null != sonMap && !sonMap.isEmpty()){
			    
			    	//迭代 判断当前菜单 改角色是否有操作权限
			    	for(Map<String, Object> sonM : sonMap){
			    		
			    		List<Map<String, Object>> roleM = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
			    				"SELECT * FROM t_authority WHERE t_role_id = ? AND t_menu_id = ?;",
			    				roleId,sonM.get("t_id"));
			    		
			    		if(null !=roleM && !roleM.isEmpty()){
			    			isOk = true;
			    			sonM.put("state", map);
			    		}
			    	}
			    	m.put("nodes", sonMap);
			    }
				List<Map<String, Object>> roleM = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
	    				"SELECT * FROM t_authority WHERE t_role_id = ? AND t_menu_id = ?;",
	    				roleId,m.get("t_id"));
		    	
		    	if(isOk || (null !=roleM && !roleM.isEmpty())){
		    		m.put("state", map);
		    	}
			}
			
			mu.setM_istatus(1);
			mu.setM_object(dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
