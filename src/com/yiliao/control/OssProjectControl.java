package com.yiliao.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yiliao.service.OSSProjectService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * oss存储对象控制层
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/admin")
public class OssProjectControl {

	@Autowired
	private OSSProjectService oSSProjectService;

	/**
	 * 跳转到oss存储对象页面
	 * 
	 * @return
	 */
	@RequestMapping("jumpOssList")
	public ModelAndView jumpOssList() {

		List<Map<String, Object>> ossList = this.oSSProjectService.getOssList();

		ModelAndView mv = new ModelAndView("ossList");

		mv.addObject("data", ossList);

		return mv;
	}

	/**
	 * 保存或者修改对象存储设置
	 * 
	 * @param t_id
	 * @param t_app_id
	 * @param t_secret_id
	 * @param t_secret_key
	 * @param t_bucket
	 * @param t_region
	 * @param t_state
	 * @param t_type
	 * @param response
	 */
	@RequestMapping("saveOrUpdateOssSetUp")
	@ResponseBody
	public void saveOrUpdateOssSetUp(Integer t_id, String t_app_id,
			String t_secret_id, String t_secret_key, String t_bucket,
			String t_region, int t_state, int t_type,String t_img_url,
			HttpServletResponse response) {

		MessageUtil mu = this.oSSProjectService.saveOrUpdateOssSetUp(t_id,
				t_app_id, t_secret_id, t_secret_key, t_bucket, t_region,
				t_state, t_type,t_img_url);
		
		PrintUtil.printWri(mu, response);

	}
	
	
	/**
	 * 根据编号获取oss存储对象明细
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("getOssDataById")
	@ResponseBody
	public void  getOssDataById(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.oSSProjectService.getOssDataById(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	
	/**
	 * 删除OSS对象设置
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("delOssSetUp")
	@ResponseBody
	public void delOssSetUp(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.oSSProjectService.delOssSetUp(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
	/**
	 * 修改OSS状态
	 * @param t_id
	 * @param response
	 */
	@RequestMapping("updateOssState")
	@ResponseBody
	public void updateOssState(int t_id,HttpServletResponse response){
		
		MessageUtil mu = this.oSSProjectService.updateOssState(t_id);
		
		PrintUtil.printWri(mu, response);
		
	}
	
}
