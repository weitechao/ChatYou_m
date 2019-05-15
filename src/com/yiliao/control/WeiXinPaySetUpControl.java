package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.WeiXinPaySetUpService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 微信支付设置control
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class WeiXinPaySetUpControl {

	@Autowired
	private WeiXinPaySetUpService weiXinPaySetUpService;
	/**
	 * 获取微信支付设置列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getWeiXinPaySetUpList")
	@ResponseBody
	public void getWeiXinPaySetUpList(int page, HttpServletResponse response){
		
		PrintUtil.printWri(this.weiXinPaySetUpService.getWeiXinPaySetUpList(page), response);
		
	}
	
	/**
	 * 修改或者删除
	 */
	@RequestMapping("addOrUpWeiXinPaySetUp")
	@ResponseBody
	public void addOrUpWeiXinPaySetUp(Integer t_id,String appId,String t_mchid,String t_mchid_key,String t_certificate_url,
			HttpServletResponse response){
		
		MessageUtil mu = this.weiXinPaySetUpService.addOrUpWeiXinPaySetUp(t_id, appId, t_mchid, t_mchid_key, t_certificate_url);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delWeiXinPaySetUp")
	@ResponseBody
	public void delWeiXinPaySetUp(int t_id ,HttpServletResponse response){
		
		MessageUtil mu = this.weiXinPaySetUpService.delWeiXinPaySetUp(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
}
