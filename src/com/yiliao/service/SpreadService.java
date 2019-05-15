package com.yiliao.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface SpreadService {

	/**
	 * 添加渠道账号
	 * 
	 * @param t_platform
	 * @param loguser
	 * @param logpwd
	 * @param t_gold_proportions
	 * @param t_vip_proportions
	 * @return
	 */
	MessageUtil addSpreadUser(String loguser, String logpwd, int t_gold_proportions,
			int t_vip_proportions, int t_role_id, HttpServletRequest request);

	/**
	 * 获取当前登陆的推广人信息
	 * 
	 * @param request
	 * @return
	 */
	MessageUtil getSpreadUserMsg(HttpServletRequest request);

	/**
	 * 获取渠道推广的列表
	 * 
	 * @param page
	 * @param request
	 * @return
	 */
	JSONObject getSpreadUserList(int page, HttpServletRequest request);

	/**
	 * 获取当前用户是否可以添加渠道推广
	 * 
	 * @param request
	 * @return
	 */
	MessageUtil getSpreadUserMesg(HttpServletRequest request);

	/**
	 * 修改推广用户数据
	 * 
	 * @param t_id
	 * @param loginpwd
	 * @param t_phone
	 * @param t_qq
	 * @param t_weixin
	 * @param t_settlement_type
	 * @param t_bank
	 * @return
	 */
	MessageUtil updateSpreadMesg(int t_id, String loginpwd, String t_phone, String t_qq, String t_weixin,
			int t_settlement_type, String t_bank, HttpServletRequest request);

	/**
	 * 加载下一级明细
	 * 
	 * @param t_spread_id
	 * @param page
	 * @return
	 */
	JSONObject getNextlLowerLevel(int t_spread_id, int page);

	/**
	 * 取消代理资格
	 * 
	 * @param t_id
	 * @return
	 */
	MessageUtil cancelSpread(int t_id);

	/**
	 * 结算明细
	 * 
	 * @param t_user_id
	 * @param page
	 * @return
	 */
	JSONObject getSettlementList(int t_user_id, int page);

	/**
	 * 重置url
	 * 
	 * @param t_id
	 * @return
	 */
	MessageUtil resetUserUrl(int t_id);

	/**
	 * 获取渠道推广图片列表
	 * 
	 * @param page
	 * @param userId
	 * @return
	 */
	Map<String, Object> getSpreedImgList(int page, int userId);

	/**
	 * 加载二维码生产图
	 * 
	 * @param t_id
	 * @param shortUrl
	 * @param response
	 */
	void getPreviewImg(int t_id, int userId, HttpServletResponse response);
	/**
	 * 保存上传图片
	 * @param img_url
	 * @return
	 */
	MessageUtil saveSpreadImg(String img_url);
	
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	MessageUtil delSpreadImg(int id);
	/**
	 * 启用代理
	 * @param userId
	 * @return
	 */
	MessageUtil startSpreed(int userId);
}
