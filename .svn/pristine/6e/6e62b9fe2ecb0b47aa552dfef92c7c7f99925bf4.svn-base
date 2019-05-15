package com.yiliao.service;

import java.math.BigDecimal;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface CashValueSetMealService {
	
	
	/**
	 * 获取充值与提现包
	 * @param page
	 * @return
	 */
	public JSONObject getCashValueList(int t_project_type,int t_end_type,int page);
	
	/**
	 * 添加或者修改充值与提现
	 * @param t_id
	 * @param t_project_type
	 * @param t_gold
	 * @param t_money
	 * @param t_is_enable
	 * @return
	 */
	public MessageUtil addOrUpdateCashValue(Integer t_id,int t_project_type,int t_gold,BigDecimal t_money,int t_end_type,int t_is_enable,String t_describe);

	/**
	 * 更新数据状态
	 * @param t_id
	 * @param state
	 * @return
	 */
	public MessageUtil updateCashValueState(int t_id,int state);
	
	/**
	 * 删除数据
	 * @param t_id
	 * @return
	 */
	public MessageUtil delCashValue(int t_id);
	
	
}
