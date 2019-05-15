package com.yiliao.control;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.SystemSetUpService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 系统设置
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class SystemSetUpControl {

	@Autowired
	private SystemSetUpService systemSetUpService;

	/**
	 * 获取分成比例列表
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping("getDivideIntoList")
	@ResponseBody
	public void getDivideIntoList(int page, HttpServletResponse response) {

		JSONObject mu = this.systemSetUpService.getDivideIntoList(page);

		PrintUtil.printWri(mu, response);

	}

	/**
	 * 修改分成设置
	 * 
	 * @param t_id
	 * @param t_extract_ratio
	 * @param response
	 */
	@RequestMapping("updateSystem")
	@ResponseBody
	public void updateSystem(int t_id, String t_extract_ratio, HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.updateSystem(t_id, t_extract_ratio);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping("getVersionList")
	@ResponseBody
	public void getVersionList(int page, HttpServletResponse response) {

		JSONObject jsonObject = this.systemSetUpService.getVersionList(page);

		PrintUtil.printWri(jsonObject, response);
	}

	/**
	 * 添加版本
	 * 
	 * @param t_download_url
	 * @param t_is_new
	 * @param t_version
	 * @param response
	 */
	@RequestMapping("addVersion")
	@ResponseBody
	public void addVersion(String t_download_url, int t_is_new, String t_version, String t_version_depict,
			String t_version_type, String t_onload_path, HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.addVersion(t_download_url, t_is_new, t_version, t_version_depict,
				t_version_type, t_onload_path);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 删除版本
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delVersion")
	@ResponseBody
	public void delVersion(int t_id, HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.delVersion(t_id);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 获取排行榜设置
	 * 
	 * @param response
	 */
	@RequestMapping("getRankingList")
	@ResponseBody
	public void getRankingList(HttpServletResponse response) {

		PrintUtil.printWri(this.systemSetUpService.getRankingList(), response);
	}

	/**
	 * 修改排行榜数据
	 * 
	 * @param id
	 * @param t_charm_number       魅力排行榜
	 * @param t_consumption_number
	 * @param t_courtesy_number
	 * @param response
	 */
	@RequestMapping("upRankData")
	@ResponseBody
	public void upRankData(int t_id, int t_charm_number, int t_consumption_number, int t_courtesy_number,
			HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.upRankData(t_id, t_charm_number, t_consumption_number,
				t_courtesy_number);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 获取风格列表
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping("getStyleSetUp")
	@ResponseBody
	public void getStyleSetUp(int page, HttpServletResponse response) {

		PrintUtil.printWri(this.systemSetUpService.getStyleSetUp(page), response);
	}

	/**
	 * 添加风格
	 * 
	 * @param t_style_name
	 * @param t_mark
	 * @param t_state
	 * @param response
	 */
	@RequestMapping("saveStyleSetUp")
	@ResponseBody
	public void saveStyleSetUp(Integer t_id, String t_style_name, String t_mark, int t_state,
			HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.saveSeyleSetUp(t_id, t_style_name, t_mark, t_state);

		PrintUtil.printWri(mu, response);

	}

	/**
	 * 删除风格设置
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delStyleSetUp")
	@ResponseBody
	public void delStyleSetUp(int t_id, HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.delStyleSetUp(t_id);

		PrintUtil.printWri(mu, response);

	}

	/**
	 * 获取推广奖励列表
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping("getSpreadAwardList")
	@ResponseBody
	public void getSpreadAwardList(int page, HttpServletResponse response) {

		PrintUtil.printWri(this.systemSetUpService.getSpreadAwardList(page), response);

	}

	/**
	 * 添加或修改邀请注册奖励
	 * 
	 * @param t_id
	 * @param t_gold
	 * @param t_rank
	 * @param t_sex
	 * @param response
	 */
	@RequestMapping("saveOrUpSpreadAward")
	@ResponseBody
	public void saveOrUpSpreadAward(Integer t_id, int t_gold, int t_rank, int t_sex, HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.saveOrUpSpreadAward(t_id, t_gold, t_rank, t_sex);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 删除推广奖励
	 * 
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delSpreadAward")
	@ResponseBody
	public void delSpreadAward(int t_id, HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.delSpreadAward(t_id);

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 获取系统设置明细
	 * 
	 * @param response
	 */
	@RequestMapping("getSystemSetUpDateil")
	@ResponseBody
	public void getSystemSetUpDateil(HttpServletResponse response) {

		MessageUtil mu = this.systemSetUpService.getSystemSetUpDateil();

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 更新系统设置
	 * 
	 * @param t_id
	 * @param t_scope
	 * @param t_android_download
	 * @param t_ios_download
	 * @param t_system_lang
	 * @param response
	 */
	@RequestMapping(value = { "setSystemSetUp" }, method = { RequestMethod.POST })
	@ResponseBody
	public MessageUtil setSystemSetUp(int t_id, int t_scope, String t_android_download, String t_ios_download,
			String t_system_lang_girl, String t_system_lang_male, BigDecimal t_default_text, BigDecimal t_default_video,
			BigDecimal t_default_phone, BigDecimal t_default_weixin, String t_award_rules, String t_service_qq,
			String t_nickname_filter, String t_video_hint, String t_spreed_hint) {

		return this.systemSetUpService.setSystemSetUp(t_id, t_scope, t_android_download, t_ios_download,
				t_system_lang_girl, t_system_lang_male, t_default_text, t_default_video, t_default_phone,
				t_default_weixin, t_award_rules, t_service_qq, t_nickname_filter, t_video_hint, t_spreed_hint);
	}

	/**
	 * 获取帮助中心列表
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = { "getHelpConter" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getHelpConter(int page) {

		return systemSetUpService.getHelpConter(page);
	}

	/**
	 * 添加或者修改帮助内容
	 * 
	 * @param t_id
	 * @param title
	 * @param content
	 */
	@RequestMapping(value = { "addHelpConter" }, method = { RequestMethod.POST })
	@ResponseBody
	public MessageUtil addHelpConter(Integer t_id, String title, String content, int t_sort) {

		return systemSetUpService.addHelpConter(t_id, title, content, t_sort);
	}

	@RequestMapping(value = { "delHelpContre" }, method = { RequestMethod.POST })
	@ResponseBody
	public MessageUtil delHelpContre(int t_id) {

		return systemSetUpService.delHelpContre(t_id);
	}

}
