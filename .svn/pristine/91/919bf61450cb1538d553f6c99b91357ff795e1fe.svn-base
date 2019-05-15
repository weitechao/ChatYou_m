package com.yiliao.service;

import java.math.BigDecimal;
import java.util.Map;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface SystemSetUpService {

	/**
	 * 获取分成设置列表
	 * 
	 * @param page
	 * @return
	 */
	public JSONObject getDivideIntoList(int page);

	/**
	 * 修改分成比例
	 * 
	 * @param t_id
	 * @param t_extract_ratio
	 * @return
	 */
	public MessageUtil updateSystem(int t_id, String t_extract_ratio);

	/**
	 * 获取版本列表
	 * 
	 * @param page
	 * @return
	 */
	public JSONObject getVersionList(int page);

	/**
	 * 添加版本
	 * 
	 * @param t_download_url
	 * @param t_is_new
	 * @param t_version
	 * @return
	 */
	public MessageUtil addVersion(String t_download_url, int t_is_new, String t_version, String t_version_depict,
			String t_version_type, String t_onload_path);

	/**
	 * 删除此版本
	 * 
	 * @param t_id
	 * @return
	 */
	public MessageUtil delVersion(int t_id);

	JSONObject getRankingList();

	/**
	 * 修改排行榜加载数量设置
	 * 
	 * @param id
	 * @param t_charm_number
	 * @param t_consumption_number
	 * @param t_courtesy_number
	 * @return
	 */
	public MessageUtil upRankData(int id, int t_charm_number, int t_consumption_number, int t_courtesy_number);

	/**
	 * 获取风格设置
	 * 
	 * @param page
	 * @return
	 */
	JSONObject getStyleSetUp(int page);

	/**
	 * 添加风格
	 * 
	 * @param t_style_name
	 * @param t_mark
	 * @param t_state
	 * @return
	 */
	MessageUtil saveSeyleSetUp(Integer t_id, String t_style_name, String t_mark, int t_state);

	/**
	 * 删除风格
	 * 
	 * @param t_id
	 * @return
	 */
	MessageUtil delStyleSetUp(int t_id);

	/**
	 * 邀请注册奖励
	 * 
	 * @param page
	 * @return
	 */
	JSONObject getSpreadAwardList(int page);

	/**
	 * 添加或者修改邀请注册奖励
	 * 
	 * @param t_id
	 * @param t_gold
	 * @param t_rank
	 * @param t_sex
	 * @return
	 */
	MessageUtil saveOrUpSpreadAward(Integer t_id, int t_gold, int t_rank, int t_sex);

	/**
	 * 删除推广奖励
	 * 
	 * @param t_id
	 * @return
	 */
	MessageUtil delSpreadAward(int t_id);

	/**
	 * 获取系统设置明细
	 * 
	 * @return
	 */
	MessageUtil getSystemSetUpDateil();

	/**
	 * 更新系统设置
	 * 
	 * @param t_id
	 * @param t_scope
	 * @param t_android_download
	 * @param t_ios_download
	 * @param t_system_lang
	 * @return
	 */
	MessageUtil setSystemSetUp(int t_id, int t_scope, String t_android_download, String t_ios_download,
			String t_system_lang_girl, String t_system_lang_male, BigDecimal t_default_text, BigDecimal t_default_video,
			BigDecimal t_default_phone, BigDecimal t_default_weixin, String t_award_rules, String t_service_qq,
			String t_nickname_filter,String t_video_hint,String t_spreed_hint);

	/**
	 * 获取帮助列表
	 * 
	 * @param page
	 * @return
	 */
	Map<String, Object> getHelpConter(int page);

	/**
	 * 添加或者修改帮助内容
	 * 
	 * @param t_id
	 * @param title
	 * @param content
	 * @return
	 */
	MessageUtil addHelpConter(Integer t_id, String title, String content, int t_sort);

	/**
	 * 删除帮助内容
	 * 
	 * @param t_id
	 * @return
	 */
	MessageUtil delHelpContre(int t_id);

}
