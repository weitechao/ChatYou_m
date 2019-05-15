package com.yiliao.service;

import net.sf.json.JSONObject;

import com.yiliao.util.MessageUtil;

public interface PrivatePhotoService {
	
	/**
	 * 获取私密相册列表
	 * @param page
	 * @return
	 */
	public JSONObject getPrivatePhotoList(int page,int search_name,int fileType);
	
	/**
	 * 点击禁用
	 * @param t_id
	 * @return
	 */
	public MessageUtil clickSetUpEisable(int t_id);
	
	/**
	 * 
	 * @param t_id
	 * @return
	 */
	public MessageUtil onclickHasVerified(int t_id);

}
