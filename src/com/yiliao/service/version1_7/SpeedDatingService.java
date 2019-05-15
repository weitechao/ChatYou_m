package com.yiliao.service.version1_7;

import java.util.Map;

import com.yiliao.util.MessageUtil;

public interface SpeedDatingService {

	/**
	 * 获取速配统计
	 * @param page
	 * @return
	 */
	Map<String, Object> getSpeedDatingTotal(int page,String beginTime,String endTime);
	
	/**
	 * 获取速配日明细
	 * @param page
	 * @return
	 */
	Map<String, Object> getSpeedDatingDayDetail(int page,String dayTime,String condition);
	
	/**
	 * 获取主播速配日明细
	 * @param page
	 * @param userId
	 * @param dayTime
	 * @return
	 */
	Map<String, Object> getAnchorSpDayDetail(int page,int userId,String dayTime);
	
	/**
	 * 获取速配管理
	 * @param page
	 * @return
	 */
	Map<String, Object> getSpredManList(int page);
	
	/**
	 * 添加速配管理
	 * @param t_id
	 * @param anthorId
	 * @param t_begin_time
	 * @param t_end_time
	 * @return
	 */
	MessageUtil saveSpeedManData(Integer t_id,int anthorId,String t_begin_time,String t_end_time);
	
	/**
	 * 删除速配管理
	 * @param t_id
	 * @return
	 */
	MessageUtil delSpeedMsg(int t_id);
}
