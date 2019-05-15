package com.yiliao.service;

import net.sf.json.JSONObject;

public interface ThechartsService {

	/**
	 * 获取在线时长列表
	 * @param page
	 * @return
	 */
	JSONObject getOnLineTimeList(int page);
	
	/**
	 * 获取邀请用户排行榜
	 * @param page
	 * @return
	 */
	JSONObject getInviteUserList(int page);
	
	
	/**
	 * 获取指定用户的推广用户列表
	 * @param userId
	 * @param page
	 * @return
	 */
	JSONObject getSpreadUserList(int userId,int page,String beginTime,String endTime);
	
	/**
	 * 获取 用户消费列表
	 * @param page
	 * @return
	 */
	JSONObject getUserConsumeList(int page);
	
	/**
	 * 获取用户余额排行榜
	 * @param page
	 * @return
	 */
	JSONObject getBalanList(int page,int t_sex);
	
	/**
	 * 获取提现排行榜
	 * @param page
	 * @return
	 */
	JSONObject getExtractMoney(int page);
	
	/**
	 * 获取用户充值用户排行榜
	 * @param page
	 * @return
	 */
	JSONObject  getBankUserList(int page );
}
