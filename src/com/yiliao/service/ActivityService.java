package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface ActivityService {

	/**
	 * 获取活动列表
	 * 
	 * @param page
	 * @return
	 */
	public JSONObject getActivityList(int page);
	
	/**
	 * 新增或者修改
	 * @param t_id
	 * @param t_activity_name
	 * @param t_activity_number
	 * @param t_begin_time
	 * @param t_end_time
	 * @param t_is_enable
	 * @return
	 */
	public MessageUtil saveOrUpdate(Integer t_id, String t_activity_name,
			int t_activity_number, int t_join_term,String t_begin_time, String t_end_time,
			int t_is_enable);

	/**
	 * 删除数据
	 * @param t_id
	 * @return
	 */
	public MessageUtil delActivity(int t_id);
	
	/**
	 * 查询奖励明细
	 * @param activityId
	 * @param page
	 * @return
	 */
	public JSONObject getActivityDetailList(int activityId,int page);
	
	/**
	 * 奖品上下架
	 * @param t_id
	 * @return
	 */
	public MessageUtil prizeUpdate(int t_id);
	/**
	 * 新增或者修改奖品
	 * @param t_id
	 * @param t_activity_id
	 * @param t_prize_name
	 * @param t_prize_number
	 * @param t_is_join
	 * @return
	 */
	public MessageUtil saveOrUpdateDetail(Integer t_id, int t_activity_id,
			String t_prize_name, int t_prize_number, int t_is_join,String t_prize_size);
	
	/**
	 * 删除奖品
	 * @param t_id
	 * @return
	 */
	public MessageUtil delActivityDetail(int t_id);
	
	/**
	 * 获取中奖明细
	 * @param nickName
	 * @param page
	 * @return
	 */
	public JSONObject getRewardDetailList(String nickName,int page);
}
