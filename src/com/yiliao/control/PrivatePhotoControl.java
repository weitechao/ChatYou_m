package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.PrivatePhotoService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 私密相册审核
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin")
public class PrivatePhotoControl {
	
	@Autowired
	private PrivatePhotoService privatePhotoService;
	
	
	/**
	 * 获取私密照片列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getPrivatePhotoList")
	@ResponseBody
	public void getPrivatePhotoList(int page ,int search_name,int fileType, HttpServletResponse response){
		
		PrintUtil.printWri(this.privatePhotoService.getPrivatePhotoList(page,search_name,fileType), response);
	}
	
	/**
	 * 点击禁用
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("clickSetUpEisable")
	@ResponseBody
	public void clickSetUpEisable(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.privatePhotoService.clickSetUpEisable(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 点击通过审核
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("onclickHasVerified")
	@ResponseBody
	public void onclickHasVerified(int t_id,HttpServletResponse response){
		
		System.out.println(11);
		
		MessageUtil mu = this.privatePhotoService.onclickHasVerified(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	

}
