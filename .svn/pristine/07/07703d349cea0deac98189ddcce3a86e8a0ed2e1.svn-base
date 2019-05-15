package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.CPSService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * CPS联盟控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class CPSControl {

	@Autowired
	private CPSService cpsService;
	
	/**
	 * 获取CPS联盟列表
	 * @param t_cps_name
	 * @param page
	 * @param response
	 */
	@RequestMapping("getCPSList")
	@ResponseBody
	public void getCPSList(String t_cps_name,int page,HttpServletResponse response){
		
		JSONObject cpsList = this.cpsService.getCPSList(t_cps_name, page);
		
		PrintUtil.printWri(cpsList, response);
	}
	
	/**
	 * CPS联盟审核通过
	 * @param t_id 编号
	 * @param t_real_name 用户真名
	 * @param t_phone 联系方式
	 * @param t_cps_name 联盟名称
	 * @param t_cps 推广网址
	 * @param t_settlement_type 提现方式
	 * @param t_bank 提现账号
	 * @param t_active_user 预估活跃用户
	 * @param t_proportions 分成比例
	 * @param response
	 */
	@RequestMapping("examineSuccess")
	@ResponseBody
	public void examineSuccess(int t_id,String t_real_name,String t_phone,String t_cps_name,String t_cps,int t_settlement_type,
			String t_bank,int t_active_user,int t_proportions,HttpServletResponse response){
		
		MessageUtil mu = this.cpsService.examineSuccess(t_id, t_real_name, t_phone, t_cps_name, t_cps, t_settlement_type, t_bank, t_active_user, t_proportions);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * CPS联盟审核失败或者下架
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("examineError")
	@ResponseBody
	public void examineError(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.cpsService.examineError(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	/**
	 * 结算CPS
	 * @param t_cps_id
	 * @param response
	 */
	@RequestMapping("settlementCPS")
	@ResponseBody
	public void settlementCPS(int t_cps_id,String t_order_no,int t_settlement_type,HttpServletResponse response){
		
		MessageUtil mu = this.cpsService.settlementCPS(t_cps_id, t_order_no,t_settlement_type);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取贡献列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getContributionList")
	@ResponseBody
	public void getContributionList(int t_cps_id,int page,HttpServletResponse response){
	
		PrintUtil.printWri(this.cpsService.getContributionList(t_cps_id, page), response);
	}

	
}
