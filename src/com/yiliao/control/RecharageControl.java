package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.RecharageService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 充值记录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class RecharageControl {

	@Autowired
	private RecharageService recharageService;
	
	@RequestMapping("getRecharageList")
	@ResponseBody
	public void getRecharageList(int type,int t_payment_type, int t_order_state, String beginTime,String endTime,int page,HttpServletResponse response){
		
		JSONObject json = this.recharageService.getRecharageList(type ,t_payment_type, t_order_state, beginTime, endTime, page);
		
		PrintUtil.printWri(json, response);
	}
	
	/**
	 * 获取统计金额
	 * @param type
	 * @param response
	 */
	@RequestMapping("getTotalMoney")
	@ResponseBody
	public void getTotalMoney(int type,int t_payment_type, int t_order_state, String beginTime,String endTime,HttpServletResponse response){
		
		MessageUtil mu = this.recharageService.getTotalMoney(type,t_payment_type,t_order_state, beginTime, endTime);
		
		PrintUtil.printWri(mu, response);
	}
}
