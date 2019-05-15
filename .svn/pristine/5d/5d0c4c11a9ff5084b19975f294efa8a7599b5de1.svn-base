package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.PutForwardService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 提现控制
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class PutForwardControl {
	
	@Autowired
	private PutForwardService putForwardService;
	
	
	/**
	 * 分页获取提现数据
	 * @param page
	 * @param response
	 */
	@RequestMapping("getPutForwardList")
	@ResponseBody
	public void getPutForwardList(int type,String beginTime,String endTime,int page, HttpServletResponse response){
		
		
		JSONObject jsonObject = this.putForwardService.getPutForwardList(type, beginTime, endTime, page);
		
		PrintUtil.printWri(jsonObject, response);
	}
	
	/**
	 * 获取统计金额
	 * @param response
	 */
	@RequestMapping("getPutForwardTotal")
	@ResponseBody
	public void getPutForwardTotal(String beginTime,String endTime,HttpServletResponse response){
		
		MessageUtil mu = this.putForwardService.getPutForwardTotal(beginTime, endTime);
		
		PrintUtil.printWri(mu, response);
		
	}
	

	/**
	 * 给用户打款
	 * @param t_id
	 * @param userId
	 * @param response
	 */
	@RequestMapping("madeMoney")
	@ResponseBody
	public void madeMoney(int t_id,int userId,int handleType,String message,HttpServletResponse response){
		
		MessageUtil mu = this.putForwardService.madeMoney(t_id, userId,handleType,message);
		
		PrintUtil.printWri(mu, response);
		
	}

}
