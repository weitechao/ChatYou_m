package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.BannedService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 封号control
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class BannedControl {
	
	
	

	@Autowired
	private BannedService bannedService;
	/**
	 * 
	 * 获取封号设置列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getBannedList")
	@ResponseBody
	public void getBannedList(int page,HttpServletResponse response){
		
		JSONObject jsonObject = this.bannedService.getBannedList(page);
		
		PrintUtil.printWri(jsonObject, response);
		
	}
	
	/**
	 * 添加或者修改设置
	 * @param t_id
	 * @param t_count
	 * @param t_hours
	 * @param response
	 */
	@RequestMapping("saveOrUpdateBanned")
	@ResponseBody
	public void saveOrUpdateBanned(Integer t_id,int t_count,Double t_hours,HttpServletResponse response){
		
		MessageUtil mu = this.bannedService.saveOrUpdateBanned(t_id, t_count, t_hours);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 删除禁用设置
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delBannedSetUp")
	@ResponseBody
	public void delBannedSetUp(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.bannedService.delBannedSetUp(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
}
