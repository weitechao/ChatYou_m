package com.yiliao.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yiliao.service.UserService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 用户管理
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/admin")
public class UserControl {

	@Autowired
	private UserService userService;

	@RequestMapping("/getUserList")
	@ResponseBody
	public void getUserList(int t_sex,int t_role,String condition,String beginTime,String endTime,int page, HttpServletResponse response) {

		JSONObject jsonObject = this.userService.getUserLsit(t_sex, t_role, condition, beginTime, endTime, page);

		PrintUtil.printWri(jsonObject, response);
	}

	/**
	 * 保存用户基础资料
	 * 
	 * @param nickName
	 * @param phone
	 * @param sex
	 * @param age
	 * @param t_height
	 * @param t_weight
	 * @param t_constellation
	 * @param t_city
	 * @param t_vocation
	 * @param t_synopsis
	 * @param t_vocation
	 * @param t_autograph
	 * @param t_synopsis
	 * @param t_role
	 * @return
	 */
	@RequestMapping("saveUser")
	public ModelAndView saveUser(String t_nickName, String t_phone,
			Integer t_sex, Integer t_age, Integer t_height, Integer t_weight,
			String t_constellation, String t_city, String t_vocation,
			String t_synopsis, String t_autograph, Integer t_role,int t_state) {
		// 保存用户信息
		 this.userService.saveUser(t_nickName, t_phone, t_sex, t_age,
				t_height, t_weight, t_constellation, t_city, t_vocation,
				t_synopsis, t_autograph, t_role,t_state);
		ModelAndView mv = new ModelAndView("redirect:/menu/userList.htm");
		return mv;
	}
	
	/**
	 * 启用或者禁用用户
	 * @param t_id
	 * @param state
	 * @param response
	 */
	@RequestMapping("enableOrDisable")
	@ResponseBody
	public void enableOrDisable(int t_id,int state,HttpServletResponse response){
		
		MessageUtil mu = this.userService.enableOrDisable(t_id, state);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 用户封号
	 * @param t_id
	 * @param freeze_time
	 * @param response
	 */
	@RequestMapping("freezeOnesUser")
	@ResponseBody
	public void freezeOnesUser(int t_id,int freeze_time,String pushConnent,HttpServletRequest req,HttpServletResponse response){
		
		String user = (String) req.getSession().getAttribute("loginName");
		
		MessageUtil mu = this.userService.freezeOnesUser(t_id, freeze_time,pushConnent,user);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 用户解封
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("unlock")
	@ResponseBody
	public void unlock(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.userService.unlock(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取封号用户列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getFreezeList")
	@ResponseBody
	public void getFreezeList(String condition,int page,HttpServletResponse response){
		
		JSONObject json = this.userService.getFreezeList(condition,page);
		
		PrintUtil.printWri(json, response);
		
	}
	
	/**
	 * 根据编号获取用户信息
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("getUserById")
	@ResponseBody
	public void getUserById(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.userService.getUserById(t_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	/**
	 * 根据用户ID获取用户的封号列表
	 * @param u_id
	 * @param response
	 */
	@RequestMapping("getFreezeTimeList")
	@ResponseBody
	public void getFreezeTimeList(int u_id,HttpServletResponse response){
		
		MessageUtil mu = this.userService.getFreezeTimeList(u_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 获取禁用列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getDisableList")
	@ResponseBody
	public void getDisableList(String condition,int page, HttpServletResponse response){
		
		JSONObject mu = this.userService.getDisableList(condition,page);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 获取用户相册列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getPhotoList")
	@ResponseBody
	public void getPhotoList(int page,int t_user_id,HttpServletResponse response){
		
		MessageUtil mu = this.userService.getPhotoList(page,t_user_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 设置相册的图片或者视频禁用
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("setUpPhotoEisable")
	@ResponseBody
	public void setUpPhotoEisable(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.userService.setUpPhotoEisable(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 上传到相册
	 * @param userId
	 * @param t_title
	 * @param video_img
	 * @param fileId
	 * @param url
	 * @param type
	 * @param gold
	 * @param response
	 */
	@RequestMapping("savePhoto")
	@ResponseBody
	public void savePhoto(int userId,String t_title,String video_img,String url,int gold,HttpServletResponse response){
		
		MessageUtil mu = this.userService.savePhoto(userId, t_title, video_img, url, StringUtils.isNotBlank(video_img)?1:0, gold);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	
	/**
	 * 获取未审核的封面列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getUnauditedCoverList")
	@ResponseBody
	public void getUnauditedCoverList(int page,HttpServletResponse response){
		
		MessageUtil mu = this.userService.getUnauditedCoverList(page);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	/**
	 * 获取用户财务明细
	 * @param userId
	 * @param type
	 * @param page
	 * @param response
	 */
	@RequestMapping("getUserFinancialDetails")
	@ResponseBody
	public void getUserFinancialDetails(int userId,int type,int page,HttpServletResponse response){
		
		JSONObject data = this.userService.getUserFinancialDetails(userId, type, page);
		
		PrintUtil.printWri(data, response);
	}


	/**
	 * 统计用户
	 * @param userId
	 * @param response
	 */
	@RequestMapping("getUserTotalMoney")
	@ResponseBody
	public void getUserTotalMoney(int userId,HttpServletResponse response){
		
		MessageUtil mu = this.userService.getUserTotalMoney(userId);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 验证手机号码是否存在
	 * @param phone
	 * @param response
	 */
	@RequestMapping("getPhoneIsExist")
	@ResponseBody
	public void getPhoneIsExist(String phone,HttpServletResponse response){
		
		MessageUtil mu = this.userService.getPhoneIsExist(phone);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	/**
	 * 获取用户的收费设置
	 * @param id
	 * @param response
	 */
	@RequestMapping("getChargeSetUp")
	@ResponseBody
	public void getChargeSetUp(int userId,HttpServletResponse response){
		
		MessageUtil mu = this.userService.getChargeSetUp(userId);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 修改收费设置
	 * @param userId
	 * @param t_video_gold
	 * @param t_text_gold
	 * @param t_phone_gold
	 * @param t_weixin_gold
	 * @param response
	 */
	@RequestMapping("replaceSetUp")
	@ResponseBody
	public void replaceSetUp(int userId,int t_video_gold,int t_text_gold,int t_phone_gold,int t_weixin_gold,HttpServletResponse response){
		
		MessageUtil mu = this.userService.replaceSetUp(userId, t_video_gold, t_text_gold, t_phone_gold, t_weixin_gold);
	 
	    PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取标签列表
	 * @param userId
	 * @param response
	 */
	@RequestMapping("getLableList")
	@ResponseBody
	public void getLableList(int userId,HttpServletResponse response){
	
		MessageUtil mu = this.userService.getLableList(userId);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 跟新标签
	 * @param userId
	 * @param lables
	 * @param response
	 */
	@RequestMapping("addLabel")
	@ResponseBody
	public void addLabel(int userId,String lables,HttpServletResponse response){
	
		 MessageUtil mu = this.userService.addLabel(userId, lables);
		 
		 PrintUtil.printWri(mu, response);
	}

	/**
	 * 获取随机用户列表
	 * @param response
	 */
	@RequestMapping("getRandomUserList")
	@ResponseBody
	public void getRandomUserList(HttpServletResponse response){
		
		MessageUtil mu = this.userService.getRandomUserList();
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 添加评论
	 * @param t_content_user
	 * @param t_anchor_id
	 * @param t_score
	 * @param lables
	 * @param response
	 */
	@RequestMapping("addUserContent")
	@ResponseBody
	public void addUserContent(int t_content_user,int t_anchor_id,int t_score,String lables,HttpServletResponse response){
		
		MessageUtil mu = this.userService.addUserContent(t_content_user, t_anchor_id, t_score, lables);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 
	 * @param t_id
	 * @param push_connent
	 * @param response
	 */
	@RequestMapping("sendPushMsg")
	@ResponseBody
	public void sendPushMsg(int t_id ,String push_connent,HttpServletResponse response){
		
		MessageUtil mu = this.userService.sendPushMsg(t_id, push_connent);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 赠送金币
	 * @param t_id
	 * @param goid
	 * @param response
	 */
	@RequestMapping("giveUserGold")
	@ResponseBody
	public void giveUserGold(int t_id,int goid,HttpServletRequest req,HttpServletResponse response){
		
		//获取当前登陆用户编号
		Object attribute = req.getSession().getAttribute("loginId");
		
		if(null != attribute) {
			int role_id = Integer.parseInt(attribute.toString());
			PrintUtil.printWri(this.userService.giveUserGold(t_id, goid,role_id), response);
		}
	}
	
	/**
	 * 获取主播的推广设置
	 * @param userId
	 * @param response
	 */
	@RequestMapping("getNominate")
	@ResponseBody
	public void getNominate(int userId,HttpServletResponse response) {
		
		MessageUtil mu = this.userService.getNominate(userId);
		
		PrintUtil.printWri(mu, response);
	}
	
	
	/**
	 * 设置或修改推荐主播
	 * @param userId
	 * @param t_is_nominate
	 * @param response
	 */
	@RequestMapping("saveOrUpdateNominate")
	@ResponseBody
	public void saveOrUpdateNominate(int userId,int t_is_nominate,int t_sort,HttpServletResponse response) {
		
		MessageUtil mu = this.userService.saveOrUpdateNominate(userId, t_is_nominate,t_sort);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取免费主播
	 * @param userId
	 * @param response
	 */
	@RequestMapping("getFreeAnchor")
	@ResponseBody
	public void getFreeAnchor(int userId,HttpServletResponse response) {
		
		MessageUtil mu = this.userService.getFreeAnchor(userId);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 变更用户信息
	 * @param userId
	 * @param t_is_free
	 * @param response
	 */
	@RequestMapping("alterationFreeAnchor")
	@ResponseBody
	public void alterationFreeAnchor(int userId,int t_is_free,HttpServletResponse response) {
	
		MessageUtil mu = this.userService.alterationFreeAnchor(userId, t_is_free);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 *  加载IM消息列表
	 * @param condition
	 * @param beginTime
	 * @param endTime
	 */
	@RequestMapping(value = "getImLogList",method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public void getImLogList(String condition,String beginTime,String endTime,int page,HttpServletResponse response) {
		
		JSONObject imLogList = this.userService.getImLogList(condition, beginTime, endTime, page);
		
		PrintUtil.printWri(imLogList, response);
		
	}
	
	/**
	 * 获取主播在线时长
	 * @param userId
	 * @param beginTime
	 * @param endTime
	 * @param response
	 */
	@RequestMapping("getAnchorOnlineTime")
	@ResponseBody
	public void getAnchorOnlineTime(int userId,String beginTime,String endTime,int page,HttpServletResponse response) {
	
		PrintUtil.printWri(this.userService.getAnchorOnlineTime(userId, beginTime, endTime, page), response);
	}
	
	
	/**
	 * 设置用户永久在线
	 * @param userId
	 * @param response
	 */
	@RequestMapping("setUserOnLine")
	@ResponseBody
	public void setUserOnLine(int userId,HttpServletResponse response) {
		
		MessageUtil mu = this.userService.setUserOnLine(userId);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 重新设置推广人
	 * @param t_id
	 * @param t_referee_id
	 * @return
	 */
	@RequestMapping("setRefereeUser")
	@ResponseBody
	public MessageUtil setRefereeUser(int t_id,int t_referee_id) {
		
		return this.userService.setRefereeUser(t_id, t_referee_id);
	}
	
	
	/**
	 * 修改用户信息
	 * @param t_id
	 * @param t_nickName
	 * @param t_phone
	 * @param t_modal_sex
	 * @param t_age
	 * @param t_height
	 * @param t_weight
	 * @param t_constellation
	 * @param t_city
	 * @param t_vocation
	 * @param t_synopsis
	 * @param t_autograph
	 * @return
	 */
	@RequestMapping(value= {"upUserData"},method = {RequestMethod.POST})
	@ResponseBody
    public MessageUtil upUserData(int t_id,String t_nickName,String t_phone,Integer t_modal_sex,
    		Integer t_age,Integer t_height,Integer t_weight,String t_constellation,String t_city,
    		String t_vocation,String t_synopsis,String t_autograph,int t_user_role) {
    	
		return this.userService.upUserData(t_id, t_nickName, t_phone, t_modal_sex, t_age, t_height,
				t_weight, t_constellation,t_city, t_vocation, t_synopsis, t_autograph,t_user_role);
				
    }
	
}
