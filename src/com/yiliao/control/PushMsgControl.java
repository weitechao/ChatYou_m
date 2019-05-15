package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.PushMsgService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * 推送消息控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin")
public class PushMsgControl {

	@Autowired
	public PushMsgService pushMsgService;
	/**
	 * 推送消息列表
	 * @param page
	 * @param response
	 */
	@RequestMapping("getPushMsgList")
	@ResponseBody
	public void getPushMsgList(int page ,HttpServletResponse response){
		
		PrintUtil.printWri(this.pushMsgService.getPushMsgList(page), response);
	}
	
	/**
	 * 全服推送
	 * @param push_mgs
	 * @param response
	 */
	@RequestMapping("addWholeServicePush")
	@ResponseBody
	public MessageUtil addWholeServicePush(Integer t_user_role,String push_msg){
		
		this.pushMsgService.addWholeServicePush(t_user_role,push_msg);
		
		return new MessageUtil(1, "已发送");
	 
	}
}
