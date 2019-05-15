package com.yiliao.service;

import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

public interface GiftService {
	
   /**
    * 分页获取礼物列表
    * @param page
    * @return
    */
   public JSONObject getGiftList(String condition,int page);
   
   
   /**
    * 新增或者修改礼物
    * @param t_gift_id
    * @param t_gift_name
    * @param t_gift_gif_url
    * @param t_gift_still_url
    * @param t_gift_gold
    * @param t_is_enable
    * @return
    */
   public MessageUtil addOrUpdateGift(Integer t_gift_id, String t_gift_name,
			String t_gift_gif_url, String t_gift_still_url, int t_gift_gold,
			String t_is_enable);
   
   
   /**
    * 礼物启用或者停用
    * @param t_gift_id
    * @return
    */
   public MessageUtil isEnable(int t_gift_id);
   
   /**
    * 删除礼物列表
    * @param t_gift_id
    * @return
    */
   public MessageUtil delGiftById(int t_gift_id);
   
   /**
    * 获取赠送礼物明细
    * @param giftId
    * @param page
    * @return
    */
   public JSONObject getGiveDetail(int giftId,int page);

}
