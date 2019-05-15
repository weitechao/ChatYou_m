package com.yiliao.service;

import net.sf.json.JSONObject;

import com.yiliao.util.MessageUtil;

public interface CoverExamineService {

	/**
	 * 分页获取所有未审核的图片信息
	 * 
	 * @param page
	 * @return
	 */
	public JSONObject getCoverExamineList(String condition,int page);

	/**
	 * 用户列表获取封面数据
	 * 
	 * @return
	 */
	public MessageUtil getUserCoverExamineList(int t_user_id);
	
	/**
	 * 获取用户封面列表
	 * @param t_user_id
	 * @return
	 */
	public MessageUtil getCoverList(int t_user_id);

	/**
	 * 设置为第一张图片
	 * 
	 * @param t_id
	 * @param t_user_id
	 * @return
	 */
	public MessageUtil setUpFirst(int t_id, int t_user_id);

	/**
	 * 通过审核
	 * 
	 * @param t_id
	 * @return
	 */
	public MessageUtil throughAudit(int t_id);
	
	/**
	 * 审核失败
	 * @param t_id
	 * @return
	 */
	public MessageUtil delCoverData(int t_id);

}
