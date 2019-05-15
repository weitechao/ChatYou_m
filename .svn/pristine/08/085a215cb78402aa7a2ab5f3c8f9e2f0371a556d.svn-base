package com.yiliao.service;

import net.sf.json.JSONObject;

import com.yiliao.util.MessageUtil;

public interface CPSService {

	/**
	 * 获取CPS联盟列表
	 * @param t_cps_name
	 * @param page
	 * @return
	 */
	JSONObject getCPSList(String t_cps_name,int page);
	
	/**
	 * 通过审核
	 * @param t_id
	 * @param t_real_name
	 * @param t_phone
	 * @param t_cps_name
	 * @param t_cps
	 * @param t_settlement_type
	 * @param t_bank
	 * @param t_active_user
	 * @param t_proportions
	 * @return
	 */
	MessageUtil examineSuccess(int t_id,String t_real_name,String t_phone,String t_cps_name,String t_cps,int t_settlement_type,
			String t_bank,int t_active_user,int t_proportions);
	
	/**
	 * CPS联盟下架
	 * @param t_id
	 * @return
	 */
	MessageUtil examineError(int t_id);
	/**
	 * 结算CPS
	 * @param t_cps_id
	 * @return
	 */
	MessageUtil settlementCPS(int t_cps_id,String t_order_no,int t_settlement_type);
	
	/**
	 * 获取贡献列表
	 * @param t_cps_id
	 * @param page
	 * @return
	 */
	JSONObject getContributionList(int t_cps_id,int page);
	
	/**
	 * 获取已结算信息
	 * @param t_cps_id
	 * @param page
	 * @return
	 */
	JSONObject getSetmmList(int t_cps_id,int page);
}
