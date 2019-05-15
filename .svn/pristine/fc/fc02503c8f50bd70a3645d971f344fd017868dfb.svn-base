package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface GuildService {
	
	/**
	 * 
	 * @param guildName
	 * @param page
	 * @return
	 */
	public JSONObject getGuildList(String guildName,int page);
	
	/**
	 * 修改公会状态
	 * @param t_id
	 * @param t_extract
	 * @return
	 */
	MessageUtil guildEnableOrDisable(int t_id);
	
	/**
	 * 审核公会
	 * @param t_id
	 * @param t_guild_name
	 * @param t_admin_name
	 * @param t_admin_phone
	 * @param t_extract
	 * @return
	 */
	MessageUtil addOrUpdateGuild(Integer t_id,String t_guild_name,String t_admin_name,String t_admin_phone,int t_extract);

	/**
	 * 加载公会下的主播
	 * @param guild_id
	 * @param page
	 * @return
	 */
	JSONObject  getGuildAnchorList(int guild_id,int page);
	
	/**
	 * 公会移除主播
	 * @param t_id
	 * @return
	 */
	MessageUtil delAnchor(int t_id);
	
	/**
	 * 加入公会
	 * @param t_guild_id
	 * @param idCards
	 * @return
	 */
	MessageUtil addGuild(int t_guild_id,String idCards);
	
	/**
	 * 公会下架
	 * @param t_guild_id
	 * @return
	 */
	MessageUtil delGuild(int t_guild_id);
	
	/**
	 * 加载公会管理者资料
	 * @param t_id
	 * @return
	 */
	MessageUtil loadGuidAdminData(int t_id);
}
