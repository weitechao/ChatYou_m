package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.ActivityService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 活动控制层
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("admin")
public class ActivityControl {

	@Autowired
	private ActivityService activityService;

	/**
	 * 获取活动列表
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping("getActivityList")
	@ResponseBody
	public void getActivityList(int page, HttpServletResponse response) {

		PrintUtil
				.printWri(this.activityService.getActivityList(page), response);
	}

	/**
	 * 新增或者修改
	 * 
	 * @param t_id
	 * @param t_activity_name
	 * @param t_activity_number
	 * @param t_begin_time
	 * @param t_end_time
	 * @param t_is_enable
	 * @param response
	 */
	@RequestMapping("saveOrUpdateActivity")
	@ResponseBody
	public void saveOrUpdateActivity(Integer t_id, String t_activity_name,
			int t_activity_number, int t_join_term,String t_begin_time, String t_end_time,
			int t_is_enable, HttpServletResponse response) {

		MessageUtil mu = this.activityService.saveOrUpdate(t_id,
				t_activity_name, t_activity_number,t_join_term,t_begin_time, t_end_time,
				t_is_enable);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 删除活动
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delActivity")
	@ResponseBody
	public void delActivity(int t_id, HttpServletResponse response) {
		MessageUtil mu = this.activityService.delActivity(t_id);
		PrintUtil.printWri(mu, response);
	}

	/**
	 * 获取奖品明细
	 * 
	 * @param activityId
	 * @param page
	 * @param response
	 */
	@RequestMapping("getActivityDetailList")
	@ResponseBody
	public void getActivityDetailList(int activityId, int page,
			HttpServletResponse response) {

		JSONObject jsonObject = this.activityService.getActivityDetailList(
				activityId, page);

		PrintUtil.printWri(jsonObject, response);
	}

	/**
	 * 奖品上下架
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("prizeUpdate")
	@ResponseBody
	public void prizeUpdate(int t_id, HttpServletResponse response) {

		MessageUtil mu = this.activityService.prizeUpdate(t_id);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 添加或者修改奖品
	 * @param t_id
	 * @param t_activity_id
	 * @param t_prize_name
	 * @param t_prize_number
	 * @param t_is_join
	 * @param re
	 */
	@RequestMapping("saveOrUpdateDetail")
	@ResponseBody
	public void saveOrUpdateDetail(Integer t_id, int t_activity_id,
			String t_prize_name,String t_prize_size, int t_prize_number, int t_is_join,
			HttpServletResponse response) {

		MessageUtil mu = this.activityService.saveOrUpdateDetail(t_id, t_activity_id, t_prize_name, t_prize_number, t_is_join,t_prize_size);
	    
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 删除奖品明细
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delActivityDetail")
	@ResponseBody
	public void delActivityDetail(int t_id,HttpServletResponse response){
		
		MessageUtil mu  = this.activityService.delActivityDetail(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取中奖明细
	 * @param nickName
	 * @param page
	 * @param response
	 */
	@RequestMapping("getRewardDetailList")
	@ResponseBody
	public void getRewardDetailList(String nickName,int page,HttpServletResponse response){
		
		JSONObject jsonObject = this.activityService.getRewardDetailList(nickName, page);
		
		PrintUtil.printWri(jsonObject, response);
	}

}
