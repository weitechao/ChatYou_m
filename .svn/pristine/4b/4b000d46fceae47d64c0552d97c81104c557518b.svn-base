package com.yiliao.control.version1_6;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.version1_6.CommentService;
/**
 * 评论控制层
 * @author Administrator
 *
 */
import com.yiliao.util.MessageUtil;

@Controller
@RequestMapping("admin")
public class CommentControl {

	@Autowired
	private CommentService commentService;
	
	/**
	 * 获取未审核列表
	 * @param page
	 * @return
	 */
	@RequestMapping("getCommList")
	@ResponseBody
	public Map<String, Object> getCommList(int page){
		
		return this.commentService.getCommList(page);
	}
	
	/**
	 * 评论审核通过
	 * @param comId
	 * @return
	 */
	@RequestMapping("viaExamine")
	@ResponseBody
	public MessageUtil viaExamine(int comId) {
		
		return this.commentService.viaExamine(comId);
	}
	
	/**
	 * 驳回评论
	 * @param comId
	 * @return
	 */
	@RequestMapping("rejectComm")
	@ResponseBody
	public MessageUtil rejectComm(int comId) {
		
		return this.commentService.rejectComm(comId);
	}
	
}
