package com.yiliao.control;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiliao.service.HomeService;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PrintUtil;

/**
 * CopyRright (c)2016-版权所有:
 * 
 * @项目工程名 WXManage
 * @Module ID <(模块)类编号，可以引用系统设计中的类编号> Comments <对此类的描述，可以引用系统设计中的描述>
 * @JDK 版本(version) JDK1.6.45
 * @命名空间 com.yiliao.control
 * @作者(Author) 石德文
 * @创建日期 2016年3月25日 上午9:45:26
 * @修改人
 * @修改时间 <修改日期，格式:YYYY-MM-DD> 修改原因描述：
 * @Version 版本号 V1.0
 * @类名称 HomeControl
 * @描述 (前端页面接口)
 */
@Controller
@RequestMapping(value = "/admin")
public class HomeControl {

	private MessageUtil mu = null;

	@Autowired
	private HomeService homeService;


	/**
	 * @方法名 obtainTitle
	 * @说明 (获取总充值)
	 * @param 参数
	 *            @param kindId 设定文件
	 * @return void 返回类型
	 * @作者 石德文
	 * @throws 异常
	 */
	@RequestMapping(value = "getTotalRecharge")
	@ResponseBody
	public void getTotalRecharge(HttpServletResponse response) {

		mu = this.homeService.getTotalRecharge();

		PrintUtil.printWri(mu, response);
	}

	/**
	 * @方法名 obtainTitle
	 * @说明 (获取今日充值)
	 * @param 参数
	 * @param kindId 设定文件
	 * @return void 返回类型
	 * @作者 石德文
	 * @throws 异常
	 */
	@RequestMapping(value = "getToDayRecharge")
	@ResponseBody
	public void getToDayRecharge(HttpServletResponse response) {

		mu = this.homeService.getToDayRecharge();

		PrintUtil.printWri(mu, response);
	}

	/**
	 * @方法名 obtainTitle
	 * @说明 (总用户)
	 * @param 参数
	 *            @param kindId 设定文件
	 * @return void 返回类型
	 * @作者 石德文
	 * @throws 异常
	 */
	@RequestMapping(value = "getTotalUser")
	@ResponseBody
	public void getTotalUser(HttpServletResponse response) {

		mu = this.homeService.getTotalUser();

		PrintUtil.printWri(mu, response);
	}

	/**
	 * 今日新增
	 * 
	 * @param response
	 */
	@RequestMapping(value = "getToDayUser")
	@ResponseBody
	public void getToDayUser(HttpServletResponse response) {

		mu = this.homeService.getToDayUser();

		PrintUtil.printWri(mu, response);
	}
	
	/**
	 * 获取男女百分比
	 * @param response
	 */
	@RequestMapping("/getCountSexDistribution")
	@ResponseBody
	public void getCountSexDistribution(HttpServletResponse response){
		
		MessageUtil mu = this.homeService.getCountSexDistribution();
		
		PrintUtil.printWri(mu, response);
		
	}
	
	
	/**
	 * 获取7天以前的日期统计
	 * @param response
	 */
	@RequestMapping("/getSevenDaysList")
	@ResponseBody
	public void getSevenDaysList(HttpServletResponse response){
		
		MessageUtil mu = this.homeService.getSevenDaysList();
		
		PrintUtil.printWri(mu, response);
		
	}
	
	
	/**
	 * 年度充值统计
	 * 
	 * @param response
	 */
	@RequestMapping("/statisticsYearRecharge")
	@ResponseBody
	public void statisticsYearRecharge(HttpServletResponse response){
		
		MessageUtil mu = this.homeService.statisticsYearRecharge();
		
		
		PrintUtil.printWri(mu, response);
		
	}
	
	
	/**
	 * 获取年度会员统计
	 * @param response
	 */
	@RequestMapping("/getYearMembere")
	@ResponseBody
	public void getYearMembere(HttpServletResponse response){
		
		MessageUtil mu = this.homeService.getYearMembere();
		
		PrintUtil.printWri(mu, response);
		
	}
 
	
	 

}
