package com.yiliao.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yiliao.service.LoginService;
import com.yiliao.util.Md5Util;
import com.yiliao.util.MessageUtil;


@Service("loginRoomService")
public class LoginServiceImpl extends ICommServiceImpl implements LoginService {
	
	private MessageUtil mu = null;
	 
	/**
	 * 后台登录
	 */
	public MessageUtil login(String userName,String password,HttpServletRequest request){
		MessageUtil mu=new MessageUtil();
		
		StringBuffer loginSql = new StringBuffer();
		
		loginSql.append("SELECT r.t_id AS t_role_id ,a.t_id,0 AS t_user_id  FROM t_role r ");
		loginSql.append("LEFT JOIN t_admin a ON a.t_role_id = r.t_id ");
		loginSql.append("WHERE	a.t_user_name = ? AND a.t_pass_word = ? ");
		loginSql.append("UNION ");
		loginSql.append("SELECT l.t_role_id,l.t_id,l.t_user_id ");
		loginSql.append("FROM t_spread_login l WHERE l.t_user_name = ? AND l.t_pass_word = ? AND t_is_disable = 0 ");
		 
		List<Map<String, Object>> roleId = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(loginSql.toString(),userName,Md5Util.encodeByMD5(password),userName,Md5Util.encodeByMD5(password));
		
		String jumpUrl = "";
		
		if(null != roleId &&  !roleId.isEmpty()){
			
			request.getSession().setMaxInactiveInterval(30*60);
			request.getSession().setAttribute("loginName", userName);
			request.getSession().setAttribute("roleId", roleId.get(0).get("t_role_id"));
			request.getSession().setAttribute("loginId", roleId.get(0).get("t_id"));
			
			Stream<Map<String,Object>> stream = roleId.stream().filter(s ->0 != Integer.parseInt(s.get("t_user_id").toString()));
			
			if(stream.count() > 0) {
				request.getSession().setAttribute("roleId", roleId.get(0).get("t_role_id"));
				request.getSession().setAttribute("spreadLog", roleId.get(0));
				request.getSession().setAttribute("spread_role_id", roleId.get(0).get("t_role_id"));
				
				logger.info("用户编号-->{}",roleId.get(0).get("t_user_id"));
				//根据用户编号来得到当前登陆用户是几级分销商
				Map<String, Object> role = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_spread_id FROM t_spread_channel WHERE t_user_id = ? ", roleId.get(0).get("t_user_id"));
				
				if(role.get("t_spread_id").equals(0)) {
					request.getSession().setAttribute("authority",1);
				}else {
					request.getSession().setAttribute("authority",2);
				}
			}
			
			//得到当前用户的权限菜单
			
			String roSql = "SELECT m.t_id,m.t_menu_name,m.t_menu_url,m.t_icon FROM t_menu m LEFT JOIN  t_authority a ON a.t_menu_id = m.t_id WHERE a.t_role_id =? AND t_father_id = ?";
			
			List<Map<String, Object>> menuMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(roSql, roleId.get(0).get("t_role_id"),0);

			StringBuffer menu = new StringBuffer();
			
			for(Map<String, Object> m : menuMap){
				//表示存在子菜单
				if("#".equals(m.get("t_menu_url"))){
					menu.append("<li class=\"has-subnav\">");
					menu.append("<a href=\"javascript:;\">");
					menu.append(m.get("t_icon"));
					menu.append("<span class=\"nav-text\">").append(m.get("t_menu_name")).append("</span>");
					menu.append("<i class=\"icon-angle-right\"></i>");
					menu.append("<i class=\"icon-angle-down\"></i>");
					menu.append("</a>");
					menu.append("<ul>");
					//获取当前用户权限下的子菜单
					List<Map<String, Object>> sonMenu= this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(roSql, roleId.get(0).get("t_role_id"),m.get("t_id"));
					//循环子菜单
					for(Map<String, Object> sonM : sonMenu){
						// <li><a class="subnav-text" href="${pageContext.request.contextPath}/menu/addUser.htm"> 新增用户 </a></li>
						menu.append("<li><a class=\"subnav-text\" href=\"..");
						menu.append(sonM.get("t_menu_url")).append("\">");
						menu.append(sonM.get("t_menu_name")).append("</a></li>");
						
						if("".equals(jumpUrl)){
							jumpUrl = sonM.get("t_menu_url").toString();
						}
					}
					
					menu.append("</ul>");
					menu.append("</li>");
					
					
				}else{ //没有子菜单
					menu.append("<li>");
					menu.append("<a href=\"..");
					menu.append(m.get("t_menu_url")).append("\">");
					menu.append(m.get("t_icon"));
					menu.append("<span class=\"nav-text\">").append(m.get("t_menu_name"));
					menu.append("</span>");
					menu.append("</a>");
					menu.append("</li>");
					if("".equals(jumpUrl)){
						jumpUrl = m.get("t_menu_url").toString();
					}
				}
				
			}
			
			request.getSession().setAttribute("menu", menu.toString());
			
			mu.setM_istatus(1);
			mu.setM_strMessage("登录成功！");
			mu.setM_object(jumpUrl);
		}else{
			mu.setM_istatus(0);
			mu.setM_strMessage("登录失败！");
		}
		return mu;
	}

	/*
	 * 获取登陆配置列表(non-Javadoc)
	 * @see com.yiliao.service.LoginService#loginSetUpList()
	 */
	@Override
	public List<Map<String, Object>> loginSetUpList() {
		
		List<Map<String,Object>> loginList = null;
		try {
			
			String sql = "SELECT t_id,t_app_id,t_app_secret,t_type,t_state FROM t_login_setup ";
			
			loginList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginList;
	}

	/**
	 * 保存或者修改第三方登陆设置
	 */
	@Override
	public MessageUtil saveLoginSetUp(Integer t_id, String t_app_id,
			String t_app_secret, String t_type, String t_state) {
		try {
			
			//保存或者修改资料
			if(null == t_id || 0 == t_id){
				
				String sql = "INSERT INTO t_login_setup (t_app_id, t_app_secret, t_type, t_state) VALUES (?, ?, ?, ?);";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_app_id,t_app_secret,t_type,t_state);
				
			}else{
				String sql = "UPDATE t_login_setup SET t_app_id=?, t_app_secret=?, t_type=?, t_state=?  WHERE t_id=?";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_app_id,t_app_secret,t_type,t_state,t_id);
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存或者修改异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 根据编号获取登陆详情(non-Javadoc)
	 * @see com.yiliao.service.LoginService#getLoginSetUpById(int)
	 */
	@Override
	public MessageUtil getLoginSetUpById(int t_id) {
		try {
			
			String sql = "SELECT t_id,t_app_id,t_app_secret,t_type,t_state FROM t_login_setup WHERE t_id = ?";
			
			Map<String, Object> resMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, t_id);
			
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(resMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存或者修改异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 根据编号删除登陆设置(non-Javadoc)
	 * @see com.yiliao.service.LoginService#delLoginSteup(int)
	 */
	@Override
	public MessageUtil delLoginSteup(int t_id) {
		try {
			
			String sql = "DELETE FROM t_login_setup WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			mu = new MessageUtil(1, "已删除!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除数据!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 修改登陆设置状态(non-Javadoc)
	 * @see com.yiliao.service.LoginService#updateLoginState(int)
	 */
	@Override
	public MessageUtil updateLoginState(int t_id) {
		
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE t_login_setup SET ") ;
			sql.append("t_state = CASE t_state  ") ;
			sql.append("  WHEN 0 THEN 1  ") ;
			sql.append("  WHEN 1 THEN 0 ") ;
			sql.append("END ") ;
			sql.append("WHERE t_id = ? ") ;
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql.toString(),t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改登陆状态异常!!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		
		return mu;
	}

	@Override
	public MessageUtil updatePassWord(String loginName, String originalCipher,
			String newPassword) {
		try {
			
			String  sql = "SELECT * FROM t_admin WHERE t_user_name = ? AND t_pass_word = ?";
			
			List<Map<String, Object>> adminUser = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, loginName,Md5Util.encodeByMD5(originalCipher));
			
			if(null  !=adminUser && !adminUser.isEmpty()){
				
				String upSql = "UPDATE t_admin SET t_pass_word = ? WHERE t_id = ?";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql, Md5Util.encodeByMD5(newPassword),adminUser.get(0).get("t_id"));
				
				mu = new MessageUtil(1, "修改成功!");
				
			}else{
				mu = new MessageUtil(0,"原密码错误!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改密码异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}
	
	
}
