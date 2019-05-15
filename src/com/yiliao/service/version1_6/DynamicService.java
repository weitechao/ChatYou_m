package com.yiliao.service.version1_6;

import java.util.Map;

import com.yiliao.util.MessageUtil;

public interface DynamicService {

	/**
	 * 获取动态列表
	 * @param condition
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	Map<String, Object> getDynamicList(String condition,String beginTime,String endTime,int page);
	
	/**
	 * 获取动态文件
	 * @param dynamicId
	 * @return
	 */
	MessageUtil  getDynamicFile(int dynamicId);
	
	/**
	 * 设置文件异常
	 * @param fileId
	 * @return
	 */
	MessageUtil setFileAbnormal(int fileId);
	
	/**
	 * 审核动态
	 * @param dynamicId
	 * @param type
	 * @return
	 */
	MessageUtil examineDynamic(int dynamicId,int type);
	/**
	 * 删除动态
	 * @param dynamicId
	 * @return
	 */
	MessageUtil delDynamic(int dynamicId);
	
	/**
	 * 获取已审核通过的评论列表
	 * @param dynamicId
	 * @return
	 */
	Map<String, Object> getExamineComment(int dynamicId,String beginTime,String endTime,int page);
	
	/**
	 * 删除评论
	 * @param comId
	 * @return
	 */
	MessageUtil delComment(int comId);
	
}
