package com.yiliao.service;

/**
* CopyRright (c)2016-版权所有: 
* @项目工程名 WXManage                                      
* @Module ID   <(模块)类编号，可以引用系统设计中的类编号>    
    * Comments  <对此类的描述，可以引用系统设计中的描述>                                           
* @JDK 版本(version) JDK1.6.45                           
* @命名空间  com.yiliao.service               
* @作者(Author) 石德文           
* @创建日期  2016年3月15日  下午2:39:58 
* @修改人                                            
* @修改时间  <修改日期，格式:YYYY-MM-DD>                                    
    * 修改原因描述：  
* @Version 版本号 V1.0   
* @类名称 BasicsmsgService
* @描述  (微信基础信息接口)
 */
public interface BasicsMsgService {

	/**
	* @方法名  obtaionBasicsMsgList
	* @说明  (获取微信基础信息所有的接口)
	* @param 参数 @return    设定文件
	* @return Object    返回类型
	* @作者 石德文
	* @throws 异常
	 */
	public Object obtaionBasicsMsgList();
	/**
	* @方法名  obtainToken
	* @说明   (判断当前token值是否存在)
	* @param 参数 @param token
	* @param 参数 @return    设定文件
	* @return Object    返回类型
	* @作者 石德文
	* @throws 异常
	 */
	public Object obtainToken(String token);
	
	public Object upageData(String t1,String t2,String t3);
}
