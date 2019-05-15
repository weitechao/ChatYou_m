package com.yiliao.service;

import java.math.BigDecimal;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface VIPSetMealService {
	
	/**
	 * 获取VIP套餐列表
	 * @param page
	 * @return
	 */
	public JSONObject getVipSetMealList(int page);
	
	/**
	 * 新增
	 * @param t_id
	 * @param t_setmeal_name
	 * @param t_cost_price
	 * @param t_money
	 * @param t_duration
	 * @param t_is_enable
	 * @return
	 */
	public MessageUtil saveVipSetMeal(Integer t_id,String t_setmeal_name,BigDecimal t_cost_price,BigDecimal t_money,
			int t_duration,int t_is_enable,int t_gold);

	
	/**
	 * 删除VIP套餐
	 * @param t_id
	 * @return
	 */
	public MessageUtil delVipSetMeal(int t_id);
	
	/**
	 * 修改状态
	 * @param t_id
	 * @param state
	 * @return
	 */
	public MessageUtil upEnableOrDisable(int t_id,int state);
	
	/**
	 * 获取VIP消费明细
	 * @param vipId
	 * @param page
	 * @return
	 */
	public JSONObject getVIPConsumeDetail(int vipId,int page);
	
}
