package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.EnrollService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 注册红包控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class EnrollControl {
	
	@Autowired
	public EnrollService enrollService;
	
	/**
	 * 获取列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getEnrollList")
	@ResponseBody
	public void getEnrollList(int page , HttpServletResponse response){
		
		PrintUtil.printWri(this.enrollService.getEnrollList(page), response);
	}
	
	/**
	 * 添加注册赠送红包记录
	 * @param t_sex
	 * @param t_gold
	 * @param response
	 */
	@RequestMapping("addEnroll")
	@ResponseBody
	public void addEnroll(int t_sex,int t_gold,HttpServletResponse response){
		
		MessageUtil mu = this.enrollService.addEnroll(t_sex, t_gold);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 删除记录
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delEnroll")
	@ResponseBody
	public  void delEnroll(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.enrollService.delEnroll(t_id);
		
		PrintUtil.printWri(mu, response);
	}
}
