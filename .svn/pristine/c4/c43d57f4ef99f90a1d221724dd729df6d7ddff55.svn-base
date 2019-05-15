package com.yiliao.service;

import java.util.Map;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface ConsumeService {

	
	/**
	 * 获取消费列表
	 * @param type
	 * @param page
	 * @return
	 */
	public JSONObject getConsumeList(int type,String beginTime,String endTime,int page);
	/**
	 * 获取消费统计
	 * @param type
	 * @return
	 */
	public MessageUtil getConsumeTotal(int type,String beginTime,String endTime);
	
	/**
	 * 获取赠送列表
	 */
	Map<String, Object> getGiveList(int page,String beginTime,String endTime);
}
