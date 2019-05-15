package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.FeedBackService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 举报控制层
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("admin")
public  class FeedbackControl {

	@Autowired
	private FeedBackService feedBackService;

	/**
	 * 获取举报列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getFeedbackList")
	@ResponseBody
	public void getReportList(String condition,String beginTime,String endTime,int page, HttpServletResponse response) {
		JSONObject json = this.feedBackService.getFeedbackList(condition, beginTime, endTime, page);

		PrintUtil.printWri(json, response);

	}
	
	/**
	 * 处理意见反馈
	 * @param t_id
	 * @param t_handle_comment
	 * @param img_url
	 * @param response
	 */
	@RequestMapping("upateFeedBack")
	@ResponseBody
	public void upateFeedBack(int t_id,String t_handle_comment,String img_url,HttpServletResponse response){
		
		MessageUtil mu = this.feedBackService.upateFeedBack(t_id, t_handle_comment, img_url);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	

}
