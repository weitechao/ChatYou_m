package com.yiliao.service;

import com.yiliao.util.MessageUtil;

/**
* CopyRright (c)2016-版权所有: 重庆西信天元数据资讯有限公司                          
* @项目工程名 WXManage                                      
* @Module ID   <(模块)类编号，可以引用系统设计中的类编号>    
    * Comments  <对此类的描述，可以引用系统设计中的描述>                                           
* @JDK 版本(version) JDK1.6.45                           
* @命名空间  com.yiliao.service               
* @作者(Author) 石德文           
* @创建日期  2016年3月25日  上午9:47:05 
* @修改人                                            
* @修改时间  <修改日期，格式:YYYY-MM-DD>                                    
    * 修改原因描述：  
* @Version 版本号 V1.0   
* @类名称 HomeService
* @描述  (前端页面获取接口)
 */
public interface HomeService {
	
	/**
	 * 获取总充值金额
	 * @return
	 */
	public MessageUtil getTotalRecharge();
	
	/**
	 * 获取今日充值金额
	 * @return
	 */
	public MessageUtil getToDayRecharge();
	
	/**
	 * 统计总用户数
	 * @return
	 */
	public MessageUtil getTotalUser();
	/**
	 * 统计今日新增用户数
	 * @return
	 */
	public MessageUtil getToDayUser();
	/**
	 * 获取男女比例
	 * @return
	 */
	public MessageUtil getCountSexDistribution();
	
	/**
	 * 统计7天以前的数据
	 * @return
	 */
	public MessageUtil getSevenDaysList();
	
	/**
	 * 统计
	 * @return
	 */
	public MessageUtil statisticsYearRecharge();
	
	
	/**
	 * 年度会员统计
	 * @return
	 */
	public MessageUtil  getYearMembere();
	
	
	

	
}
