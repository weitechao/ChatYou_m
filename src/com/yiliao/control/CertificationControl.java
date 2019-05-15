package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.CertificationService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 实名认证控制层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class CertificationControl {

	@Autowired
	private CertificationService certificationService;

	/**
	 * 获取实名认证列表
	 * 
	 * @param condition
	 * @param page
	 * @param response
	 */
	@RequestMapping("getCertificationList")
	@ResponseBody
	public void getCertificationList(String condition, int page, HttpServletResponse response) {

		JSONObject mu = this.certificationService.getCertificationList(condition, page);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 设置用户禁用
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("updateDisable")
	@ResponseBody
	public void updateDisable(int t_id, HttpServletResponse response) {

		MessageUtil mu = this.certificationService.updateDisable(t_id);

		PrintUtil.printWri(mu, response);

	}

	/**
	 * 认证审核成功
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("verifySuccess")
	@ResponseBody
	public void verifySuccess(int t_id, HttpServletResponse response) {

		MessageUtil mu = this.certificationService.verifySuccess(t_id);

		PrintUtil.printWri(mu, response);

	}

	/**
	 * 认证审核失败
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("verifyFail")
	@ResponseBody
	public void verifyFail(int t_id, HttpServletResponse response) {

		MessageUtil mu = this.certificationService.verifyFail(t_id);

		PrintUtil.printWri(mu, response);
	}

}
