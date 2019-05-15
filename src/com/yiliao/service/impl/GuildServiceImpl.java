package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.GuildService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PushUtil;

@Service("guildService")
public class GuildServiceImpl extends ICommServiceImpl implements GuildService {

	@Override
	public JSONObject getGuildList(String guildName, int page) {
		try {
			String cSql = " SELECT COUNT(t_id) AS total FROM t_guild WHERE 1=1 AND t_examine != 2 ";
			
			if(StringUtils.isNotBlank(guildName)){
				cSql = cSql + "AND t_guild_name LIKE '%"+guildName+"%' ";
			}
			
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			//查询列表
			String qSql = " SELECT g.t_id,g.t_anchor_number,g.t_guild_name,u.t_nickName,g.t_admin_name,g.t_admin_phone,g.t_extract,g.t_examine,DATE_FORMAT(g.t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_guild g  ";
			qSql = qSql + " LEFT JOIN t_user u ON g.t_user_id=u.t_id WHERE 1 = 1 AND t_examine != 2 ";
			if(StringUtils.isNotBlank(guildName)){
				qSql = qSql + " AND g.t_guild_name LIKE '%"+guildName+"%' ";
			}
			
			qSql = qSql + " ORDER BY g.t_create_time DESC LIMIT ?,10 ";
			
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, (page-1)*10);
			
			qSql = "SELECT COUNT(t_id) AS anchorNumber  FROM t_anchor_guild WHERE t_guild_id= ?";
			
			for(Map<String, Object> m : dataMap){
				
				Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, m.get("t_id"));
			    
				m.put("anchorNumber", total.get("anchorNumber"));
			}
			
			JSONObject json = new JSONObject();
			
			json.put("total", totalMap.get("total"));
			json.put("rows", dataMap);
			
			return json;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public MessageUtil guildEnableOrDisable(int t_id) {
		
		MessageUtil mu = null;
		
		try {
			String uSql = " UPDATE t_guild SET  t_examine= 1  WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql,t_id);
			
			mu = new MessageUtil(1, "公会已启用.");
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}


	@Override
	public MessageUtil addOrUpdateGuild(Integer t_id, String t_guild_name,
			String t_admin_name, String t_admin_phone, int t_extract) {
		MessageUtil mu = null ;
		try {
			if(null != t_id  && 0 != t_id){
			
				String uSql = "UPDATE t_guild SET  t_guild_name=?, t_admin_name=?, t_admin_phone=?,t_extract= ?, t_examine=1  WHERE t_id = ? ";
			    this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_guild_name,t_admin_name,t_admin_phone,t_extract,t_id);
			    
			    mu = new MessageUtil(1, "数据已更新!");
			    
			    //查询公会申请人
			    String qSql = " SELECT t_user_id FROM t_guild WHERE t_id = ? ";
			    Map<String, Object> userMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_id);
			    
			    
			    String message = "您申请的公会已审核.";
				
				//存储消息
				String messageSql = "INSERT INTO t_message (t_user_id, t_message_content, t_create_time, t_is_see) VALUES (?, ?, ?, ?);";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(messageSql, userMap.get("t_user_id"),message,
						DateUtils.format(new Date(), DateUtils.FullDatePattern),
						0);
			    
			    PushUtil.sendPush(Integer.parseInt(userMap.get("t_user_id").toString()), "您申请的公会已审核.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}


	/*
	 * 加载公会下的主播(non-Javadoc)
	 * @see com.yiliao.service.GuildService#getGuildAnchorList(int, int)
	 */
	@Override
	public JSONObject getGuildAnchorList(int guild_id, int page) {
		try {
			
			String qSql = " SELECT g.t_id,u.t_idcard,u.t_nickName,gu.t_guild_name,DATE_FORMAT(g.t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_anchor_guild g LEFT JOIN t_user u ON g.t_anchor_id = u.t_id LEFT JOIN t_guild gu ON g.t_guild_id = gu.t_id  WHERE g.t_guild_id = ?  ORDER BY g.t_create_time DESC LIMIT  ?,10";
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, guild_id,(page-1)*10);
			
			String cSql = " SELECT count(g.t_id) total  FROM t_anchor_guild g LEFT JOIN t_user u ON g.t_anchor_id = u.t_id LEFT JOIN t_guild gu ON g.t_guild_id = gu.t_id  WHERE g.t_guild_id = ? ";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql, guild_id);
			
			JSONObject json = new JSONObject();
			json.put("total", total.get("total"));
			json.put("rows", dataList);
			
			return json;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	/*
	 * 公会移除主播(non-Javadoc)
	 * @see com.yiliao.service.GuildService#delAnchor(int)
	 */
	@Override
	public MessageUtil delAnchor(int t_id) {
		
		MessageUtil mu = null ;
		try {
			
			String dSql = " DELETE FROM t_anchor_guild WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
			
			mu = new MessageUtil(1, "主播已移除.");
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "公会移除主播");
		}
		return mu;
	}


	/*
	 * 加入公会(non-Javadoc)
	 * @see com.yiliao.service.GuildService#addGuild(int, java.lang.String)
	 */
	@Override
	public MessageUtil addGuild(int t_guild_id, String idCards) {
		
		MessageUtil mu = null;
		
		try {
			//记录不能处理的用户
			String errIdCard = "";
			
			String addIdCard = "";
			
			//分割idcared
			String[] str = idCards.split(",");
            //查询该ID号是否是主播			
			String quSql = " SELECT * FROM t_user WHERE t_idcard = ? AND  t_role = 1 ";
			//查询主播是否已经加入了公会
            String qgSql = " SELECT * FROM t_guild_invite WHERE t_anchor_id = ?  ";
            
            String inSql = " INSERT INTO t_guild_invite (t_guild_id, t_anchor_id, t_create_time) VALUES (?,?,?) ";
			
			for (int i = 0; i < str.length; i++) {
				List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(quSql, str[i]);
				if(dataMap.isEmpty()){
					errIdCard = errIdCard + str[i]+",";
				}else{
					List<Map<String, Object>> guilds = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qgSql, str[i]);
				    if(!guilds.isEmpty()){
				    	addIdCard = addIdCard + str[i]+",";
				    }else{
				    	this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_guild_id,dataMap.get(0).get("t_id"),
				    			DateUtils.format(new Date(), DateUtils.FullDatePattern));
				    }
				}
			}
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			
			String comment = StringUtils.isNotBlank(errIdCard)?"其中["+errIdCard+"]不是主播!":"";
			
			comment = comment + (StringUtils.isNotBlank(addIdCard)?"["+addIdCard+"]已加入了公会!":"");
			
			mu.setM_strMessage("数据已加入公会!"+comment);
			
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}


	@Override
	public MessageUtil delGuild(int t_guild_id) {
		MessageUtil mu = null;
		try {
			
			String uSql = " UPDATE t_guild SET  t_examine= 2  WHERE t_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_guild_id);
			
			mu = new MessageUtil(1, "公会已下架!");
			
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "查询异常!");
		}
		return mu;
	}


	/**
	 * 加载公会管理者资料
	 */
	@Override
	public MessageUtil loadGuidAdminData(int t_id) {
		MessageUtil mu = null ;
		try {
			
			String qSql= " SELECT t_admin_name,t_idcard,t_hand_img FROM t_guild WHERE t_id = ? ";
			
			Map<String, Object> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_id);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加载公会管理者资料异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
