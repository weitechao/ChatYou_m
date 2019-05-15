package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.CoverExamineService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 封面审核控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class CoverExamineControl {
	
	@Autowired
	private CoverExamineService coverExamineService;
	
	
	/**
	 * 获取封面图片列表(用户列表获取)
	 * @param page
	 * @param response
	 */
	@RequestMapping("getUserCoverExamineList")
	@ResponseBody
	public void getUserCoverExamineList(int t_user_id,HttpServletResponse response){
		
		MessageUtil mu = this.coverExamineService.getUserCoverExamineList(t_user_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 获取用户封面列表
	 * @param t_user_id
	 * @param response
	 */
	@RequestMapping("getCoverList")
	@ResponseBody
	public void getCoverList(int t_user_id,HttpServletResponse response){
		
		MessageUtil mu = this.coverExamineService.getCoverList(t_user_id);
		
		PrintUtil.printWri(mu, response);
	}

	/**
	 * 获取封面图片列表(审核列表获取)
	 * @param page
	 * @param response
	 */
	@RequestMapping("getCoverExamineList")
	@ResponseBody
	public void getCoverExamineList(String condition,int page,HttpServletResponse response){
		
		JSONObject jsonObject = this.coverExamineService.getCoverExamineList(condition, page);
		
		PrintUtil.printWri(jsonObject, response);
		
	}
	
	/**
	 * 设置为封面第一张
	 * @param t_id
	 * @param t_user_id
	 * @param response
	 */
	@RequestMapping("setUpFirst")
	@ResponseBody
	public void setUpFirst(int t_id,int t_user_id,HttpServletResponse response){
		
		MessageUtil mu = this.coverExamineService.setUpFirst(t_id, t_user_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	
	/**
	 * 通过审核
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("throughAudit")
	@ResponseBody
	public void throughAudit(int t_id ,HttpServletResponse response){
		
		MessageUtil mu = this.coverExamineService.throughAudit(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 审核失败
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delCoverData")
	@ResponseBody
	public void delCoverData(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.coverExamineService.delCoverData(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}

}
