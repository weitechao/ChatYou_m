package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.GuildService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * Banner控制层
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/admin")
public class GuildControl {

	@Autowired
	private GuildService guildService;

	/**
	 * 获取公会列表
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping("getGuildList")
	@ResponseBody
	public void getGuildList(String guildName, int page,
			HttpServletResponse response) {

		PrintUtil.printWri(this.guildService.getGuildList(guildName, page),
				response);
	}

	/**
	 * 启用或者禁用
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("guildEnableOrDisable")
	@ResponseBody
	public void guildEnableOrDisable(int t_id,HttpServletResponse response) {

		MessageUtil mu = this.guildService
				.guildEnableOrDisable(t_id);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 添加或者修改公会
	 * @param t_id
	 * @param t_img_url
	 * @param t_link_url
	 * @param t_is_enable
	 * @param response
	 */
	@RequestMapping("addOrUpdateGuild")
	@ResponseBody
	public void addOrUpdateGuild(Integer t_id, String t_guild_name,
			String t_admin_name, String t_admin_phone, int t_extract,
			HttpServletResponse response) {

		MessageUtil mu = this.guildService.addOrUpdateGuild(t_id, t_guild_name,
				t_admin_name, t_admin_phone, t_extract);
		//
		PrintUtil.printWri(mu, response);
	}

	/**
	 * 加载公会主播列表
	 * @param guild_id
	 * @param page
	 * @param response
	 */
	@RequestMapping("getGuildAnchorList")
	@ResponseBody
	public void getGuildAnchorList(int guild_id, int page,
			HttpServletResponse response) {

		PrintUtil.printWri(
				this.guildService.getGuildAnchorList(guild_id, page), response);

	}

	// 公会移除主播
	@RequestMapping("delAnchor")
	@ResponseBody
	public void delAnchor(int t_id, HttpServletResponse response) {
		MessageUtil mu = this.guildService.delAnchor(t_id);

		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 加入公会
	 * @param t_guild_id
	 * @param idCards
	 * @param response
	 */
	@RequestMapping("addGuild")
	@ResponseBody
	public void addGuild(int t_guild_id,String idCards,HttpServletResponse response){
		
		MessageUtil mu = this.guildService.addGuild(t_guild_id, idCards);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 公会下架
	 * @param t_guild_id
	 * @param response
	 */
	@RequestMapping("delGuild")
	@ResponseBody
	public void delGuild(int t_guild_id,HttpServletResponse response){
		
		MessageUtil mu = this.guildService.delGuild(t_guild_id);
		
		PrintUtil.printWri(mu, response);
	}

	/**
	 * 加载公会管理者资料
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("loadGuidAdminData")
	@ResponseBody
	public void loadGuidAdminData(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.guildService.loadGuidAdminData(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
}
