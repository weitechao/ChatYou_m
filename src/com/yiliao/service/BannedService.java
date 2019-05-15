package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface BannedService {
	
	/**
	 * 获取封号设置列表
	 * @param page
	 * @return
	 */
	public JSONObject getBannedList(int page);
	
	/**
	 * 添加或者修改封号设置
	 * @param t_id
	 * @param t_count
	 * @param t_hours
	 * @return
	 */
	public MessageUtil saveOrUpdateBanned(Integer t_id,int t_count,Double t_hours);
	
	/**
	 * 
	 * @param t_id
	 * @return
	 */
	public MessageUtil delBannedSetUp(int t_id);

}
