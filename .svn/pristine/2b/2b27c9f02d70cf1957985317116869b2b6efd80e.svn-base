package com.yiliao.control;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.VIPSetMealService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * vip套餐列表
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("admin")
public class VIPSetMealControl {

	@Autowired
	private VIPSetMealService vipSetMealService;

	/**
	 * 获取VIP套餐列表
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping("getVipSetMealList")
	@ResponseBody
	public void getVipSetMealList(int page, HttpServletResponse response) {

		JSONObject json = this.vipSetMealService.getVipSetMealList(page);

		PrintUtil.printWri(json, response);

	}

	/**
	 * 新增VIP套餐
	 * 
	 * @param t_setmeal_name
	 * @param t_cost_price
	 * @param t_money
	 * @param t_duration
	 * @param t_is_enable
	 * @param response
	 */
	@RequestMapping("saveVipSetMeal")
	@ResponseBody
	public void saveVipSetMeal(Integer t_id, String t_setmeal_name,
			BigDecimal t_cost_price, BigDecimal t_money, int t_duration,
			int t_is_enable,int t_gold, HttpServletResponse response) {

		MessageUtil mu = this.vipSetMealService.saveVipSetMeal(t_id,
				t_setmeal_name, t_cost_price, t_money, t_duration, t_is_enable,t_gold);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 删除VIP套餐
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delVipSetMeal")
	@ResponseBody
	public void delVipSetMeal(int t_id, HttpServletResponse response) {
		MessageUtil mu = this.vipSetMealService.delVipSetMeal(t_id);
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 修改状态
	 * @param t_id
	 * @param state
	 * @param response
	 */
	@RequestMapping("upEnableOrDisable")
	@ResponseBody
	public void upEnableOrDisable(int t_id,int state,HttpServletResponse response){
	
		MessageUtil mu = this.vipSetMealService.upEnableOrDisable(t_id, state);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取VIP消费明细
	 * @param vipId
	 * @param page
	 * @param response
	 */
	@RequestMapping("getVIPConsumeDetail")
	@ResponseBody
	public void getVIPConsumeDetail(int vipId,int page,HttpServletResponse response){
		
		PrintUtil.printWri(this.vipSetMealService.getVIPConsumeDetail(vipId, page), response);
	}

}
