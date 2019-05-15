package com.yiliao.control.version1_7;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.version1_7.SpeedDatingService;
import com.yiliao.util.MessageUtil;

/**
 * 速配控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class SpeedDatingControl {
	
	@Autowired
	private SpeedDatingService speedDatingService;

	/**
	 * 获取速配汇总
	 * @param page
	 * @return
	 */
	@RequestMapping(value = {"getSpeedDatingTotal"},method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getSpeedDatingTotal(int page,String beginTime,String endTime){
		
		return speedDatingService.getSpeedDatingTotal(page,beginTime,endTime);
	}
	
	/**
	 * 获取速配日明细
	 * @param page
	 * @return
	 */
	@RequestMapping(value = {"getSpeedDatingDayDetail"},method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getSpeedDatingDayDetail(int page,String dayTime,String condition){
		
		return speedDatingService.getSpeedDatingDayDetail(page, dayTime,condition);
	}
	
	
	/**
	 * 主播速配日明细
	 * @param page
	 * @param userId
	 * @param dayTime
	 * @return
	 */
	@RequestMapping(value = {"getAnchorSpDayDetail"},method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAnchorSpDayDetail(int page,int userId,String dayTime){
		
		return speedDatingService.getAnchorSpDayDetail(page, userId, dayTime);
	}
	
	/**
	 * 获取速配管理列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value = {"getSpredManList"},method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getSpredManList(int page){
		
		return speedDatingService.getSpredManList(page);
	}
	
	/**
	 * 添加速配管理
	 * @param t_id
	 * @param anthorId
	 * @param t_begin_time
	 * @param t_end_time
	 * @return
	 */
	@RequestMapping(value = {"saveSpeedManData"},method = {RequestMethod.POST})
	@ResponseBody
	public MessageUtil saveSpeedManData(Integer t_id,int anthorId,String t_begin_time,String t_end_time) {
		
		return speedDatingService.saveSpeedManData(t_id, anthorId, t_begin_time, t_end_time);
	}
	
	/**
	 * 删除速配信息
	 * @param t_id
	 * @return
	 */
	@RequestMapping(value = {"delSpeedMsg"},method = {RequestMethod.POST})
	public MessageUtil delSpeedMsg(int t_id) {
		
		return speedDatingService.delSpeedMsg(t_id);
	}
	
	
}
