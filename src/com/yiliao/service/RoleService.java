package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface RoleService {
	
	/**
	 * 获取角色列表
	 * @param page
	 * @return
	 */
	public JSONObject  getRoleList(int page);
	
	/**
	 * 添加角色
	 * @param t_id
	 * @param t_role_name
	 * @param t_enable
	 * @return
	 */
	public MessageUtil saveRole(Integer t_id,String t_role_name,int t_enable);
	
	/**
	 * 删除角色
	 * @param t_id
	 * @return
	 */
	public MessageUtil delRole(int t_id);

	/**
	 * 获取启用角色列表
	 * @return
	 */
	public MessageUtil getRoleEnableList(int roleId);
	
	/**
	 * 给角色分配权限
	 * @param t_role_id
	 * @param meunIds
	 * @return
	 */
	public MessageUtil saveAuthority(int t_role_id,String meunIds);
}
