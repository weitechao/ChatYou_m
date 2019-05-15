package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.AlipaySetUpServvice;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

@Controller
@RequestMapping("admin")
public class AlipaySetUpContror {

	
	@Autowired
	private AlipaySetUpServvice alipaySetUpContror;
	
	/**
	 * 设置支付宝相关信息
	 * @param t_id 
	 * @param t_alipay_appid 
	 * @param t_alipay_private_key
	 * @param t_alipay_public_key
	 * @param response
	 */
	@RequestMapping("setAlipaySetUp")
	@ResponseBody
	public void setAlipaySetUp(Integer t_id,String t_alipay_appid,String t_alipay_private_key,String t_alipay_public_key,HttpServletResponse response) {
		
		MessageUtil mu = this.alipaySetUpContror.setAlipaySetUp(t_id, t_alipay_appid, t_alipay_private_key, t_alipay_public_key);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 删除支付宝设置
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delAlipaySetUp")
	@ResponseBody
	public void delAlipaySetUp(int t_id,HttpServletResponse response) {
		
		MessageUtil mu = this.alipaySetUpContror.delAlipaySetUp(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取支付宝设置列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getAlipaySetUpList")
	@ResponseBody
	public void getAlipaySetUpList(int page ,HttpServletResponse response) {
		
		PrintUtil.printWri(this.alipaySetUpContror.getAlipaySetUpList(page), response);
	}
	
}
