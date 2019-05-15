package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.ThechartsService;
import com.yiliao.util.PrintUtil;

@Controller
@RequestMapping("admin")
public class ThechartsControl {

	
	@Autowired
	private ThechartsService thechartsService;
	/**
	 *  获取在线时长列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getOnLineTimeList")
	@ResponseBody
	public void getOnLineTimeList(int page,HttpServletResponse response) {
		
		PrintUtil.printWri(this.thechartsService.getOnLineTimeList(page), response);
	}
	
	/**
	 *  获取邀请排行榜
	 * @param page
	 * @param response
	 */
	@RequestMapping("getInviteUserList")
	@ResponseBody
	public void getInviteUserList(int page, HttpServletResponse response) {
		
		PrintUtil.printWri(this.thechartsService.getInviteUserList(page), response);
	}
	
	/**
	 * 获取指定用户的推广用户列表
	 * @param userId
	 * @param page
	 * @param response
	 */
	@RequestMapping("getSpreadUserList")
	@ResponseBody
	public void getSpreadUserList(int userId,int page,String beginTime,String endTime,HttpServletResponse response) {
		
		PrintUtil.printWri(this.thechartsService.getSpreadUserList(userId, page,beginTime,endTime), response);
	}
	
	
	@RequestMapping("getUserConsumeList")
	@ResponseBody
	public void getUserConsumeList(int page,HttpServletResponse response) {
	
		PrintUtil.printWri(this.thechartsService.getUserConsumeList(page), response);
		
	}
	
	/**
	 * 获取提现排行榜
	 * @param page
	 * @param response
	 */
	@RequestMapping("getExtractMoney")
	@ResponseBody
	public void getExtractMoney(int page,HttpServletResponse response) {
		
		PrintUtil.printWri(this.thechartsService.getExtractMoney(page), response);
	}
	
	/**
	 * 获取用户余额排行榜
	 * @param page
	 * @param response
	 */
	@RequestMapping("getBalanList")
	@ResponseBody
	public void getBalanList(int page,int t_sex,HttpServletResponse response) {
		
		PrintUtil.printWri(this.thechartsService.getBalanList(page,t_sex), response);
	}
	
	/**
	 * 获取用户充值排行榜
	 * @param page
	 * @param response
	 */
	@RequestMapping("getBankUserList")
	@ResponseBody
	public void getBankUserList(int page ,HttpServletResponse response) {
		
		PrintUtil.printWri(this.thechartsService.getBankUserList(page), response);
	}
}
