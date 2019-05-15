package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface UserService {

	/**
	 * 保存用户基础资料
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
	 * @param t_autograph
	 * @param t_role
	 */
	public int saveUser(String nickName,String phone,Integer sex,Integer age,Integer t_height,Integer t_weight,String t_constellation,String t_city,String t_vocation,String t_synopsis,String t_autograph,Integer t_role,int t_state);
	
	/**
	 * 分页获取用户列表
	 * @param page
	 * @return
	 */
	public JSONObject getUserLsit(int t_sex,int t_role,String condition,String beginTime,String endTime,int page);
	
	/**
	 * 启用或者禁用用户
	 * @param t_id
	 * @param state
	 * @return
	 */
	public MessageUtil enableOrDisable(int t_id,int state);
	/**
	 * 用户封号
	 * @param t_id
	 * @param freeze_time
	 * @return
	 */
	public MessageUtil freezeOnesUser(int t_id,int freeze_time,String pushConnent,String user);
	
	/**
	 * 用户解封
	 * @param t_id
	 * @return
	 */
	public MessageUtil unlock(int t_id);
	
	/**
	 * 获取封号用户列表
	 */
	public JSONObject  getFreezeList(String condition,int page);
	
	/**
	 * 获取用户详情
	 * @param t_id
	 * @return
	 */
	public MessageUtil getUserById(int t_id);
	
	/**
	 * 根据用户编号获取用户封号详情
	 * @param u_id
	 * @return
	 */
	public MessageUtil getFreezeTimeList(int u_id);
	
	/**
	 * 获取禁用列表
	 * @param page
	 * @return
	 */
	public JSONObject getDisableList(String condition,int page);
	
	/**
	 * 获取用户头像列表
	 * @param page
	 * @return
	 */
	public MessageUtil getPhotoList(int page,int t_user_id);
	
	/**
	 * 禁用用户头像
	 * @param t_id
	 * @return
	 */
	public MessageUtil setUpPhotoEisable(int t_id);
	
	/**
	 * 上传到相册
	 * @param userId
	 * @param t_title
	 * @param video_img
	 * @param url
	 * @param type
	 * @param gold
	 * @return
	 */
	public MessageUtil savePhoto(int userId,String t_title,String video_img,String url,int type,int gold);
	
	/**
	 * 获取未审核的封面列表
	 * @param page
	 * @return
	 */
	public MessageUtil getUnauditedCoverList(int page);
	
	/**
	 * 获取用户的消费明细
	 * */
	public JSONObject getUserFinancialDetails(int userId,int type,int page);
	
	/**
	 * 统计用户可用余额
	 * @param userId
	 * @return
	 */
	public MessageUtil getUserTotalMoney(int userId);
	
	/**
	 * 验证手机号是否存在
	 * @param phone
	 * @return
	 */
	public MessageUtil getPhoneIsExist(String phone);
	
	/**
	 * 获取用户收费设置
	 * @param id
	 * @return
	 */
	public MessageUtil getChargeSetUp(int userId);
	
	/**
	 * 修改收费设置
	 * @param userId
	 * @param t_video_gold
	 * @param t_text_gold
	 * @param t_phone_gold
	 * @param t_weixin_gold0
	 * @return
	 */
	public MessageUtil replaceSetUp(int userId,int t_video_gold,int t_text_gold,int t_phone_gold,int t_weixin_gold);
	
	/**
	 * 获取标签列表
	 * @param userId
	 * @return
	 */
	public MessageUtil getLableList(int userId);
	
	/**
	 * 更新标签数据
	 * @param userId
	 * @param lables
	 * @return
	 */
	public MessageUtil addLabel(int userId,String lables);
	
	/**
	 * 加载随机用户评论列表
	 * @return
	 */
	public MessageUtil getRandomUserList();
	
	/**
	 * 添加主播评论
	 * @param t_content_user
	 * @param t_anchor_id
	 * @param t_score
	 * @param lables
	 * @return
	 */
	public MessageUtil addUserContent(int t_content_user,int t_anchor_id,int t_score,String lables);
	
	/**
	 * 
	 * @param t_id
	 * @param push_connent
	 * @return
	 */
	public MessageUtil sendPushMsg(int t_id ,String push_connent);
	
	/**
	 * 赠送金币
	 * @param t_id
	 * @param goid
	 * @return
	 */
	public MessageUtil giveUserGold(int t_id,int goid,int role_id);
	
	/**
	 * 获取主播推广设置
	 * @param userId
	 * @return
	 */
	public MessageUtil getNominate(int userId);
	
	/**
	 * 新增或修改推广设置
	 * @param userId
	 * @param t_is_nominate
	 * @return
	 */
	public MessageUtil saveOrUpdateNominate(int userId,int t_is_nominate,int t_sort);
	
	/**
	 * 获取当前主播是否免费
	 * @param userId
	 * @return
	 */
	public MessageUtil getFreeAnchor(int userId);
	
	/**
	 * 改变主播状态
	 * @param userId
	 * @param t_is_free
	 * @return
	 */
	public MessageUtil alterationFreeAnchor(int userId,int t_is_free);
	
	/**
	 *  获取IM聊天记录列表
	 * @param condition
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	public JSONObject getImLogList(String condition,String beginTime,String endTime,int page);
	
	/**
	 *  获取主播在先时长
	 * @param userId
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	public JSONObject  getAnchorOnlineTime(int userId,String beginTime,String endTime,int page);
	
	/**
	  * 设置主播永久在线
	 * @param userId
	 * @return
	 */
	public MessageUtil setUserOnLine(int userId);
	
	/**
	 * 重设推荐人
	 * @param t_id
	 * @param t_referee_id
	 * @return
	 */
	public MessageUtil setRefereeUser(int t_id,int t_referee_id);
	
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
	MessageUtil upUserData(int t_id,String t_nickName,String t_phone,Integer t_modal_sex,
    		Integer t_age,Integer t_height,Integer t_weight,String t_constellation,String t_city,
    		String t_vocation,String t_synopsis,String t_autograph,int t_user_role);
	
	
}
