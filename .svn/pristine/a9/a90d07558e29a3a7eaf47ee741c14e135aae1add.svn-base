package com.yiliao.service;

import com.yiliao.util.MessageUtil;

public interface BannerService {
	
	
	/**
	 * 获取列表
	 * @param page
	 * @return
	 */
	public MessageUtil getBannerList(int page);
	
	/**
	 * 启用或者禁用
	 * @param t_id
	 * @return
	 */
	public MessageUtil bannerEnableOrDisable(int t_id);
	
	/**
	 * 删除banner
	 * @param t_id
	 * @return
	 */
	public MessageUtil delBannerById(int t_id);
	
	/**
	 * 添加或者修改banner
	 * @param t_id
	 * @param t_img_url
	 * @param t_link_url
	 * @param t_is_enable
	 * @return
	 */
	public MessageUtil addOrUpdateBanner(Integer t_id,String t_img_url,String t_link_url,int t_is_enable,int t_type);

}
