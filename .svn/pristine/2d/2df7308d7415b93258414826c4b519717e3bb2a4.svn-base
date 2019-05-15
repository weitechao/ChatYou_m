package com.yiliao.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.DomainService;
import com.yiliao.util.MessageUtil;

import cn.jpush.api.push.model.Message;

/**
 * 域名池
 * @author Administrator
 *
 */
@Controller
@RequestMapping("admin")
public class DomainNamePoolControl {

	@Autowired
	private DomainService domainService;
	/**
	 * 获取域名池列表
	 * @param page
	 * @return
	 */
	@RequestMapping("getDomanList")
	@ResponseBody
	public Map<String, Object> getDomanList(int page){
		
		return domainService.getDomainList(page);
	}
	
	/**
	 * 添加域名
	 * @param domainName
	 * @param t_effect_type
	 * @return
	 */
	@RequestMapping("saveDomainName")
	@ResponseBody
	public MessageUtil saveDomainName(String domainName,int t_effect_type) {
		
		return domainService.saveDomainName(domainName, t_effect_type);
	}
	
	/**
	 * 删除域名
	 * @param t_id
	 * @return
	 */
	@RequestMapping("delDomainName")
	@ResponseBody
	public MessageUtil delDomainName(int t_id) {
		
		return domainService.delDomainName(t_id);
	}
	
}
