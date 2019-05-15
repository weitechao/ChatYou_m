package com.yiliao.service;

import javax.servlet.http.HttpServletRequest;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface AdminService {
	
	/**
	 * 获取管理员列表
	 * @param page
	 * @return
	 */
	public JSONObject  getAdminList(int page);
	
	/**
	 * 删除数据
	 * @param t_id
	 * @return
	 */
	public MessageUtil delAdmin(int t_id);
	
	/**
	 * 新增或修改
	 * @param t_id
	 * @param t_user_name
	 * @param t_pass_word
	 * @param t_is_disable
	 * @return
	 */
	public MessageUtil saveAdmin(Integer t_id,String t_user_name,String t_pass_word,String t_is_disable,int t_role_id);
	
	/**
	 * 加载通知
	 * @return
	 */
	public MessageUtil loadNotice(HttpServletRequest request);

}
