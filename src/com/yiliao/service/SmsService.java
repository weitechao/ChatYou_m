package com.yiliao.service;

import java.util.List;
import java.util.Map;

import com.yiliao.util.MessageUtil;

public interface SmsService {

	/**
	 * 获取短信列表
	 * @return
	 */
	public List<Map<String,Object>> getSmsList();
	
	
	/**
	 * 修改SMS的状态
	 * @param smsId
	 * @return
	 */
	public MessageUtil enableOrDisableSmsSteup(int smsId);
	
	/**
	 * 删除短信记录
	 * @param smsId
	 * @return
	 */
	public MessageUtil delSmsSteup(int smsId);
	
	/**
	 * 根据Id查询数据
	 * @param smsId
	 * @return
	 */
	public MessageUtil getDataById(int smsId);
	
	/**
	 * 新增或者修改数据
	 * @param t_id
	 * @param appid
	 * @param appkey
	 * @param templateId
	 * @param smsSign
	 * @param t_is_enable
	 * @param t_platform_type
	 * @return
	 */
	public MessageUtil saveOrUpdate(Integer t_id, String appid, String appkey,
			String templateId, String smsSign, int t_is_enable,
			int t_platform_type);
	
	
}
