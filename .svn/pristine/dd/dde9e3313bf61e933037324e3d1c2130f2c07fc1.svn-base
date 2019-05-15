package com.yiliao.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.SpreadService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("admin")
public class SpreadControl {

	@Autowired
	private SpreadService spreadService;
	
	/**
	 * 添加推广账号
	 * @param t_platform
	 * @param loguser
	 * @param logpwd
	 * @param t_gold_proportions
	 * @param t_vip_proportions
	 */
	@RequestMapping("addSpreadUser")
	@ResponseBody
	public void addSpreadUser(String loguser,String logpwd,int t_gold_proportions,int t_vip_proportions,int t_role_id,HttpServletRequest request,HttpServletResponse response) {
	
		MessageUtil mu = this.spreadService.addSpreadUser(loguser, logpwd, t_gold_proportions, t_vip_proportions, t_role_id,request);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取推广用户的信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("getSpreadUserMsg")
	@ResponseBody
	public void getSpreadUserMsg(HttpServletRequest request,HttpServletResponse response) {
		
		MessageUtil mu = this.spreadService.getSpreadUserMsg(request);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 *  获取当前用户是否可以添加渠道推广商
	 */
	@RequestMapping("getSpreadUserMesg")
	@ResponseBody
	public void getSpreadUserMesg(HttpServletRequest request,HttpServletResponse response) {
	
		MessageUtil mu = this.spreadService.getSpreadUserMesg(request);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取渠道推广列表
	 * @param page
	 * @param request
	 * @param response
	 */
	@RequestMapping("getSpreadChannelList")
	@ResponseBody
	public void getSpreadUserList(int page,HttpServletRequest request,HttpServletResponse response) {
		
		JSONObject mu = this.spreadService.getSpreadUserList(page, request);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 保存修改信息
	 * @param t_id
	 * @param loginpwd
	 * @param t_phone
	 * @param t_qq
	 * @param t_weixin
	 * @param t_settlement_type
	 * @param t_bank
	 * @param request
	 * @param response
	 */
	@RequestMapping("updateSpreadMesg")
	@ResponseBody
	public void updateSpreadMesg(int t_id,String loginpwd,String t_phone,String t_qq,String t_weixin,int t_settlement_type,String t_bank,HttpServletRequest request,HttpServletResponse response) {
		
		MessageUtil mu = this.spreadService.updateSpreadMesg(t_id, loginpwd, t_phone, t_qq, t_weixin, t_settlement_type, t_bank, request);
		
		PrintUtil.printWri(mu, response);
	}
	/**
	 * 加载下一级的明细
	 * @param t_spread_id
	 * @param page
	 * @param response
	 */
	@RequestMapping("getNextlLowerLevel")
	@ResponseBody
	public void getNextlLowerLevel(int t_spread_id,int page,HttpServletResponse response) {
		
		JSONObject jsonObject = this.spreadService.getNextlLowerLevel(t_spread_id, page);
		
		PrintUtil.printWri(jsonObject, response);
	}
	
	/**
	 * 取消代理资格
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("cancelSpread")
	@ResponseBody
	public void cancelSpread(int t_id,HttpServletResponse response) {
	
		MessageUtil mu = this.spreadService.cancelSpread(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取结算信息
	 * @param t_user_id
	 * @param page
	 * @param response
	 */
	@RequestMapping("getSettlementList")
	@ResponseBody
	public void getSettlementList(Integer t_user_id,int page,HttpServletResponse response) {
		
		if(null == t_user_id) {
			PrintUtil.printWri(new JSONObject(), response);
		}else  {
			JSONObject jsonObject = this.spreadService.getSettlementList(t_user_id, page);
			
			PrintUtil.printWri(jsonObject, response);
		}
	}
	
	/**
	 * 重置用户短链接
	 * @param t_id
	 * @return
	 */
	@RequestMapping("resetUserUrl")
	@ResponseBody
	public MessageUtil resetUserUrl(int t_id) {
		
		return this.spreadService.resetUserUrl(t_id);
	}
	
	/**
	 *  获取渠道图片列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value = {"getSpreedImgList"})
	@ResponseBody
	public Map<String, Object> getSpreedImgList(int page,HttpServletRequest req){
		
		//获取到当前登陆用户Id
		Map<String, Object> ent =  (Map<String, Object>) req.getSession().getAttribute("spreadLog");
		
		return this.spreadService.getSpreedImgList(page,null == ent?0:Integer.parseInt(ent.get("t_user_id").toString()));
	}
	
	/**
	 * 加载预览图
	 * @param t_id
	 * @param userId
	 * @param response
	 */
	@RequestMapping(value = {"getPreviewImg"})
	@ResponseBody
	public void getPreviewImg(int t_id,int userId,HttpServletResponse response) {
	
		this.spreadService.getPreviewImg(t_id, userId, response);
	}
	
	/**
	 *  保存上传图片
	 * @param img_url
	 * @return
	 */
	@RequestMapping(value = "saveSpreadImg")
	@ResponseBody
	public MessageUtil saveSpreadImg(String img_url) {
		
		return this.spreadService.saveSpreadImg(img_url);
	}
	
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "delSpreadImg"},method = {RequestMethod.POST})
	@ResponseBody
	public MessageUtil delSpreadImg(int id) {
		
		return this.spreadService.delSpreadImg(id);
	}
	
	
	/**
	 * 启用代理
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = {"startSpreed"},method = {RequestMethod.POST})
	@ResponseBody
	public MessageUtil startSpreed(int userId) {
		
		return this.spreadService.startSpreed(userId);
	}
	
}
