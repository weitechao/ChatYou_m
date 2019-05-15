package com.yiliao.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yiliao.util.MessageUtil;

public interface LoginService {
	
	/**
	 * 后台登录
	 * @param userName
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	public MessageUtil login(String userName,String password,HttpServletRequest request);
	
	/**
	 * 获取登陆配置列表
	 * @return
	 */
	public List<Map<String,Object>> loginSetUpList();
	
	/**
	 * 新增或者修改
	 * @param t_id
	 * @param t_app_id
	 * @param t_app_secret
	 * @param t_type
	 * @param t_state
	 * @param response
	 */
	public MessageUtil  saveLoginSetUp(Integer t_id,String t_app_id,String t_app_secret,String t_type,String t_state);
	
	/**
	 * 根据编号获取登陆设置详情
	 * @param t_id
	 * @return
	 */
	public MessageUtil getLoginSetUpById(int t_id);
	
	/**
	 * 删除登陆设置
	 * @param t_id
	 * @return
	 */
	public MessageUtil delLoginSteup(int t_id);
	
	/**
	 * 修改登陆设置状态
	 * @param t_id
	 * @return
	 */
	public MessageUtil updateLoginState(int t_id);
	
	
	/**
	 * 修改密码
	 * @param loginName
	 * @param originalCipher
	 * @param newPassword
	 * @return
	 */
	public MessageUtil updatePassWord(String loginName,String originalCipher,String newPassword);
	

}
