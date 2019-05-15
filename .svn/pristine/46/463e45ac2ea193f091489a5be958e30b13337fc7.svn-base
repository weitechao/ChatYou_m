package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.BannerService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * Banner控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin")
public class BannerControl {

	
	@Autowired
	private  BannerService bannerService;
	
	
	/**
	 * 获取banner列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getBannerList")
	@ResponseBody
	public void getBannerList(int page,HttpServletResponse response){
		
		MessageUtil mu = this.bannerService.getBannerList(page);
		
		PrintUtil.printWri(mu.getM_object(), response);
		
	}
	
	
	/**
	 * 启用或者禁用
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("bannerEnableOrDisable")
	@ResponseBody
	public void bannerEnableOrDisable(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.bannerService.bannerEnableOrDisable(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	/**
	 * 删除数据
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delBannerById")
	@ResponseBody
	public void delBannerById(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.bannerService.delBannerById(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 添加或者修改banner
	 * @param t_id
	 * @param t_img_url
	 * @param t_link_url
	 * @param t_is_enable
	 * @param response
	 */
	@RequestMapping("addOrUpdateBanner")
	@ResponseBody
	public void addOrUpdateBanner(Integer t_id,String t_img_url,String t_link_url,int t_is_enable,int t_type,HttpServletResponse response){
		
		MessageUtil mu = this.bannerService.addOrUpdateBanner(t_id, t_img_url, t_link_url, t_is_enable,t_type);
		
		PrintUtil.printWri(mu, response);
	}
}
