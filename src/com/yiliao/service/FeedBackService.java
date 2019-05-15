package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface FeedBackService {
	
	/**
	 * 获取举报列表
	 * @param page
	 * @return
	 */
	public JSONObject getFeedbackList(String condition,String beginTime,String endTime,int page);
	
	/**
	 * 处理已经反馈
	 * @param t_id
	 * @param t_handle_comment
	 * @param img_url
	 * @return
	 */
	public MessageUtil upateFeedBack(int t_id,String t_handle_comment,String img_url);
	
	 

}
