package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface CertificationService {
	
	/**
	 * 获取实名认证列表
	 * @param page
	 * @return
	 */
	public JSONObject getCertificationList(String condition,int page);
	
	/**
	 * 设置用户禁用
	 * @param t_id
	 * @return
	 */
	public MessageUtil updateDisable(int t_id);
	
	/**
	 * 认证审核成功
	 * @param t_id
	 * @return
	 */
	public MessageUtil verifySuccess(int t_id);
	
	/**
	 * 认证审核失败
	 * @param t_id
	 * @return
	 */
	public MessageUtil verifyFail(int t_id);

}
