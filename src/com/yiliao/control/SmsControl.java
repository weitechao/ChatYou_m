package com.yiliao.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yiliao.service.SmsService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 短信控制层
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/admin")
public class SmsControl {

	@Autowired
	private SmsService smsService;

	/**
	 * 跳转到短信控制列表
	 * 
	 * @return
	 */
	@RequestMapping("/jumpSmsList")
	public ModelAndView jumpSmsList() {

		List<Map<String, Object>> smsList = this.smsService.getSmsList();

		ModelAndView mv = new ModelAndView("smsList");
		mv.addObject("smsList", smsList);
		
		return mv;
	}

	/**
	 * 启用或者停用短信设置
	 * 
	 * @param smsId
	 * @param response
	 */
	@RequestMapping("enableOrDisableSmsSteup")
	@ResponseBody
	public void enableOrDisableSmsSteup(int smsId, HttpServletResponse response) {

		MessageUtil mu = this.smsService.enableOrDisableSmsSteup(smsId);

		PrintUtil.printWri(mu, response);

	}

	/**
	 * 删除短信设置
	 * 
	 * @param smsId
	 * @param response
	 */
	@RequestMapping("delSmsSteup")
	@ResponseBody
	public void delSmsSteup(int smsId, HttpServletResponse response) {
		MessageUtil mu = this.smsService.delSmsSteup(smsId);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 根据编号查询数据
	 * 
	 * @param smsId
	 * @param response
	 */
	@RequestMapping("getDataById")
	@ResponseBody
	public void getDataById(int smsId, HttpServletResponse response) {
		MessageUtil mu = this.smsService.getDataById(smsId);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 保存或者修改信息
	 * @param t_id 编号 
	 * @param appid
	 * @param appkey
	 * @param templateId 模板Id
	 * @param smsSign  签名
	 * @param t_is_enable
	 * @param t_platform_type
	 * @param response
	 */
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public void saveOrUpdate(Integer t_id, String appid, String appkey,
			String templateId, String smsSign, int t_is_enable,
			int t_platform_type, HttpServletResponse response) {

		MessageUtil mu = this.smsService.saveOrUpdate(t_id, appid, appkey, templateId, smsSign, t_is_enable, t_platform_type);
		
		PrintUtil.printWri(mu, response);
	} 

}
