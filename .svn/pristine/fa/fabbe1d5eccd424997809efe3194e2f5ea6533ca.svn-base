package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface ReportService {
	
	/**
	 * 获取举报列表
	 * @param page
	 * @return
	 */
	public JSONObject getReportList(String condition,String beginTime,String endTime,int page);
	
	/**
	 * 处理举报
	 * @param t_id
	 * @param t_handle_comment
	 * @return
	 */
	public MessageUtil handleReport(int t_id,String t_handle_comment);

}
