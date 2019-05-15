package com.yiliao.control;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.CashValueSetMealService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 充值与提现包
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class CashValueSetMealControl {
	
	@Autowired
	private CashValueSetMealService cashValueSetMealService;
	
	
	/**
	 * 获取充值与提现包
	 * @param page
	 * @param response
	 */
	@RequestMapping("getCashValueList")
	@ResponseBody
	public void getCashValueList(int t_project_type,int t_end_type,int page ,HttpServletResponse response){
		
		JSONObject data = this.cashValueSetMealService.getCashValueList(t_project_type,t_end_type,page);
		
		PrintUtil.printWri(data, response);
		
	}
	
	/**
	 * 添加或者修改充值或者提现设置
	 * @param t_id
	 * @param t_project_type
	 * @param t_gold
	 * @param t_money
	 * @param t_is_enable
	 * @param response
	 */
	@RequestMapping("addOrUpdateCashValue")
	@ResponseBody
	public void addOrUpdateCashValue(Integer t_id,int t_project_type,int t_gold,BigDecimal t_money,int t_end_type,int t_is_enable,String t_describe,HttpServletResponse response){
		
		MessageUtil mu = this.cashValueSetMealService.addOrUpdateCashValue(t_id, t_project_type, t_gold, t_money, t_end_type,t_is_enable,t_describe);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 更新状态
	 * @param t_id
	 * @param state
	 * @param response
	 */
	@RequestMapping("updateCashValueState")
	@ResponseBody
	public void updateCashValueState(int t_id,int state,HttpServletResponse response){
		
		MessageUtil mu = this.cashValueSetMealService.updateCashValueState(t_id, state);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	
	/**
	 * 点击删除充值与提现包
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delCashValue")
	@ResponseBody
	public void delCashValue(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.cashValueSetMealService.delCashValue(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	

}
