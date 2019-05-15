package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.ReportService;
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
public  class ReportControl {

	@Autowired
	private ReportService reportService;

	/**
	 * 获取举报列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getReportList")
	@ResponseBody
	public void getReportList(String condition,String beginTime,String endTime,int page, HttpServletResponse response) {
		JSONObject json = this.reportService.getReportList(condition, beginTime, endTime, page);

		PrintUtil.printWri(json, response);

	}
	
	/**
	 * 处理举报
	 * @param t_id
	 * @param t_handle_comment
	 * @param response
	 */
	@RequestMapping("handleReport")
	@ResponseBody
	public void handleReport(int t_id,String t_handle_comment,HttpServletResponse response){
		
		MessageUtil mu = this.reportService.handleReport(t_id, t_handle_comment);
		
		PrintUtil.printWri(mu, response);
	}

}
