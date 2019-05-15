package com.yiliao.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.AdminService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 管理员列表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class AdminControl {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 获取管理员列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getAdminList")
	@ResponseBody
	public void getAdminList(int page ,HttpServletResponse response){
		
		PrintUtil.printWri(this.adminService.getAdminList(page), response);
	}
	
	/**
	 * 修改或者新增管理员
	 * @param t_id
	 * @param t_user_name
	 * @param t_pass_word
	 * @param t_is_disable
	 * @param response
	 * @return
	 */
	@RequestMapping("saveAdmin")
	@ResponseBody
	public void saveAdmin(Integer t_id,String t_user_name,String t_pass_word,String t_is_disable,int t_role_id,HttpServletResponse response){
		
		MessageUtil mu = this.adminService.saveAdmin(t_id, t_user_name, t_pass_word, t_is_disable, t_role_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 删除用户
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delAdmin")
	@ResponseBody
	public void delAdmin(int t_id,HttpServletResponse response){
		
		PrintUtil.printWri(this.adminService.delAdmin(t_id), response);
	}
	
	/**
	 * 加载通知
	 * @param response
	 */
	@RequestMapping("loadNotice")
	@ResponseBody
	public void loadNotice(HttpServletRequest request,HttpServletResponse response) {
		
		MessageUtil mu = this.adminService.loadNotice(request);
		
		PrintUtil.printWri(mu, response);
	}
}
