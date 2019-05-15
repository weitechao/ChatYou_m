package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface PutForwardService {
	
	/**
	 * 获取提现列表
	 * @param type
	 * @param page
	 * @return
	 */
	public JSONObject getPutForwardList(int type,String beginTime,String endTime,int page);
	
	/**
	 * 统计提现额度
	 * @return
	 */
	public MessageUtil getPutForwardTotal(String beginTime,String endTime);
	/**
	 * 修改提现状态
	 * @param t_id
	 * @param state
	 * @param t_duration
	 * @return
	 */
	public MessageUtil updatePutForwardState(int t_id,int state,String t_duration);
	
	/**
	 * 给提现用户打款
	 * @param t_id
	 * @param userId
	 * @return
	 */
	public MessageUtil madeMoney(int t_id,int userId,int handleType,String message);

}
