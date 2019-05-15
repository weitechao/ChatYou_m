package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface RecharageService {
	
	/**
	 * 获取会充值列表
	 * @param type
	 * @param page
	 */
	public JSONObject getRecharageList(int type,String beginTime,String endTime,int page);
	
	/**
	 * 获取统计金额
	 * @param type
	 * @return
	 */
	public MessageUtil getTotalMoney(int type,String beginTime,String endTime);

}
