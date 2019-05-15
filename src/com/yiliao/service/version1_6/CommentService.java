package com.yiliao.service.version1_6;

import java.util.Map;

import com.yiliao.util.MessageUtil;

public interface CommentService {

	/**
	 * 获取未审核评论列表
	 * @param page
	 * @return
	 */
	Map<String, Object> getCommList(int page);
	
	/**
	 * 评论审核通过
	 * @param comId
	 * @return
	 */
	MessageUtil viaExamine(int comId);
	
	/**
	 * 驳回请求
	 * @param comId
	 * @return
	 */
	MessageUtil rejectComm(int comId);
}
