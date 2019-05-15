package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface MenuService {
	
	
	public JSONObject getMenuList(int page);
	
	/**
	 * 获取父级菜单
	 * @return
	 */
	public MessageUtil getFatherList();

	/**
	 * 添加菜单
	 * @param t_id
	 * @param t_menu_name
	 * @param t_menu_url
	 * @param t_father_id
	 * @return
	 */
	public MessageUtil saveMenu(Integer t_id,String t_menu_name,String t_menu_url,int t_father_id,String t_icon);
	
	/**
	 * 删除菜单
	 * @param t_id
	 * @return
	 */
	public MessageUtil delMenu(int t_id);
	
	/**
	 * 获取菜单列表
	 * @param roleId
	 * @return
	 */
	public MessageUtil getMenuTreeList(int roleId);
}
