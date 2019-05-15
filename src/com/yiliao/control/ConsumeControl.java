package com.yiliao.control;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.ConsumeService;
import com.yiliao.util.PrintUtil;

/**
 * 消费控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class ConsumeControl {

	
	@Autowired
	private ConsumeService consumeService;
	
	
	/**
	 * 获取消费列表
	 * @param type
	 * @param page
	 * @param response
	 */
	@RequestMapping("getConsumeList")
	@ResponseBody
	public void getConsumeList(int type,String beginTime,String endTime,int page,HttpServletResponse response){
		
		JSONObject jsonObject = this.consumeService.getConsumeList(type, beginTime, endTime, page);
		
		PrintUtil.printWri(jsonObject, response);
		
	}
	
	/**
	 * 获取消费的额度
	 * @param type
	 * @param response
	 */
	@RequestMapping("getConsumeTotal")
	@ResponseBody
	public void getConsumeTotal(int type,String beginTime,String endTime,HttpServletResponse response){
		
		PrintUtil.printWri(this.consumeService.getConsumeTotal(type, beginTime, endTime), response);
	}
	
	
	/**
	 * 获取赠送列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value= {"getGiveList"},method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getGiveList(int page,String beginTime,String endTime){
		
		return this.consumeService.getGiveList(page, beginTime, endTime);
	}
}
