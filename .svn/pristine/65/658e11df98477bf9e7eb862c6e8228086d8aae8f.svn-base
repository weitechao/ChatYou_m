package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.VagueService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 模糊鉴定
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class VagueControl {
	
	
	@Autowired
	private VagueService vagueService;

	
	@RequestMapping("getVagueList")
	@ResponseBody
	public void getVagueList(int page,HttpServletResponse response){
		
		MessageUtil mu = this.vagueService.getVagueList(page);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delVagueById")
	@ResponseBody
	public void delVagueById(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.vagueService.delVagueById(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 审核通过
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("hasVerified")
	@ResponseBody
	public void hasVerified(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.vagueService.hasVerified(t_id);
		
		PrintUtil.printWri(mu, response);
	}
}
