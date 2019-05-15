package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface WeiXinPaySetUpService {
	
	/**
	 * 微信支付设置
	 * @param page
	 * @return
	 */
	JSONObject getWeiXinPaySetUpList(int page);
	
	/**
	 * 新增或者修改
	 * @param t_id
	 * @param appId
	 * @param t_mchid
	 * @param t_mchid_key
	 * @param t_certificate_url
	 * @return
	 */
	MessageUtil addOrUpWeiXinPaySetUp(Integer t_id,String appId,String t_mchid,String t_mchid_key,String t_certificate_url);

	/**
	 * 删除数据
	 * @param t_id
	 * @return
	 */
	MessageUtil delWeiXinPaySetUp(int t_id);
	
}
