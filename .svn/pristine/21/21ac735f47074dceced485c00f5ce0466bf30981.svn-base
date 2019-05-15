package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.FinancialDetailsService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 财务明细
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class FinancialDetailsControl {

	
	@Autowired
	private FinancialDetailsService financialDetailsService;
	
	
	@RequestMapping("getFinancialDetailsList")
	@ResponseBody
	public void getFinancialDetailsList(String beginTime,String endTime,int page,HttpServletResponse response){
		
		JSONObject jsonObject = this.financialDetailsService.getFinancialDetailsList(beginTime, endTime, page);
		
		PrintUtil.printWri(jsonObject, response);
	}
	
	/**
	 * 统计收支
	 * @param beginTime
	 * @param endTime
	 * @param response
	 */
	@RequestMapping("getCollectPayTotal")
	@ResponseBody
	public void getCollectPayTotal(String beginTime,String endTime,HttpServletResponse response){
		
		 MessageUtil mu = this.financialDetailsService.getCollectPayTotal(beginTime, endTime);
		 
		 PrintUtil.printWri(mu, response);
	}
	
}
