package com.yiliao.control.version1_6;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.version1_6.DynamicService;
import com.yiliao.util.MessageUtil;

@Controller
@RequestMapping("admin")
public class DynamicControl {

	@Autowired
	private DynamicService dynamciService;
	
	/**
	 * 获取动态列表
	 * @param condition
	 * @param beginTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	@RequestMapping("getDynamicList")
	@ResponseBody
	public Map<String, Object> getDynamicList(String condition,String beginTime,String endTime,int page){
		
		return this.dynamciService.getDynamicList(condition, beginTime, endTime, page);
	}
	
	/**
	 * 获取动态文件
	 * @param dynamicId
	 * @return
	 */
	@RequestMapping("getDynamicFile")
	@ResponseBody
	public MessageUtil getDynamicFile(int dynamicId) {
		
		return this.dynamciService.getDynamicFile(dynamicId);
	}
	
	/**
	 * 设置文件异常
	 * @param fileId
	 * @return
	 */
	@RequestMapping("setFileAbnormal")
	@ResponseBody
	public MessageUtil setFileAbnormal(int fileId) {
		
		return this.dynamciService.setFileAbnormal(fileId);
	}
	
	/**
	 * 审核动态
	 * @param dynamicId
	 * @param type
	 * @return
	 */
	@RequestMapping("examineDynamic")
	@ResponseBody
	public MessageUtil examineDynamic(int dynamicId,int type) {
		
		return this.dynamciService.examineDynamic(dynamicId, type);
	}
	
	/**
	 * 删除动态
	 * @param dynamicId
	 * @return
	 */
	@RequestMapping("delDynamic")
	@ResponseBody
	public MessageUtil delDynamic(int dynamicId) {
		
		return this.dynamciService.delDynamic(dynamicId);
	}
	
	/**
	 * 获取已审核的评论列表
	 * @param dynamicId
	 * @return
	 */
	@RequestMapping("getExamineComment")
	@ResponseBody
	public Map<String, Object> getExamineComment(int dynamicId,String beginTime,String endTime,int page){
		
		return this.dynamciService.getExamineComment(dynamicId, beginTime, endTime, page);
	}
	
	/**
	 * 删除评论
	 * @param comId
	 * @return
	 */
	@RequestMapping("delComment")
	@ResponseBody
	public MessageUtil delComment(int comId) {
		
		return this.dynamciService.delComment(comId);
	}
	
}
