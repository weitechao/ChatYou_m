package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.GiftService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 礼物管理列表
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("admin")
public class GiftControl {

	@Autowired
	private GiftService giftService;

	/**
	 * 获取列表
	 * 
	 * @param page
	 * @param response
	 */
	@RequestMapping("getGiftList")
	@ResponseBody
	public void getGiftList(String condition,int page, HttpServletResponse response) {

		JSONObject giftList = this.giftService.getGiftList(condition,page);

		PrintUtil.printWri(giftList, response);

	}

	/**
	 * 新增或者修改礼物
	 * 
	 * @param t_gift_id
	 *            礼物编号
	 * @param t_gift_name
	 *            礼物名称
	 * @param t_gift_gif_url
	 *            礼物GIF图
	 * @param t_gift_still_url
	 *            礼物静图
	 * @param t_gift_gold
	 *            礼物单价
	 * @param t_is_enable
	 *            是否启用
	 * @param response
	 */
	@RequestMapping("addOrUpdateGift")
	@ResponseBody
	public void addOrUpdateGift(Integer t_gift_id, String t_gift_name,
			String t_gift_gif_url, String t_gift_still_url, int t_gift_gold,
			String t_is_enable, HttpServletResponse response) {

		MessageUtil mu = this.giftService.addOrUpdateGift(t_gift_id,
				t_gift_name, t_gift_gif_url, t_gift_still_url, t_gift_gold,
				t_is_enable);
		
		
		PrintUtil.printWri(mu, response);

	}
	
	/**
	 * 礼物启用停用
	 * @param t_gift_id
	 * @param response
	 */
	@RequestMapping("isEnableGift")
	@ResponseBody
	public void isEnableGift(int t_gift_id,HttpServletResponse response){
		
		MessageUtil mu = this.giftService.isEnable(t_gift_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	
	/**
	 * 删除礼物
	 * @param t_gift_id
	 * @param response
	 */
//	@RequestMapping("delGiftById")
//	@ResponseBody
	public void delGiftById(int t_gift_id,HttpServletResponse response){
		
		MessageUtil mu = this.giftService.delGiftById(t_gift_id);
		
		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取礼物赠送明细
	 * @param giftId
	 * @param page
	 * @param response
	 */
	@RequestMapping("getGiveDetail")
	@ResponseBody
	public void getGiveDetail(int giftId,int page ,HttpServletResponse response){
	
		PrintUtil.printWri(this.giftService.getGiveDetail(giftId, page), response);
	}

}
