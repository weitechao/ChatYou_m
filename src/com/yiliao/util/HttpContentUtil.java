package com.yiliao.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
* CopyRright (c)2016-版权所有: 重庆西信天元数据资讯有限公司                          
* @项目工程名 WXManage                                      
* @Module ID   <(模块)类编号，可以引用系统设计中的类编号>    
    * Comments  <对此类的描述，可以引用系统设计中的描述>                                           
* @JDK 版本(version) JDK1.6.45                           
* @命名空间  com.yiliao.util               
* @作者(Author) 李可           
* @创建日期  2016年4月14日  下午3:38:03 
* @修改人                                            
* @修改时间  <修改日期，格式:YYYY-MM-DD>                                    
    * 修改原因描述：  
* @Version 版本号 V1.0   
* @类名称 HttpContentUtil
* @描述 TODO(调用网络接口)
 */
public class HttpContentUtil {
	public static Logger logger = LoggerFactory.getLogger(HttpContentUtil.class);
	
	public static  String urlresult(String url) {
		 	String result = "";
			try {
				URL submit = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) submit.openConnection();
				connection.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
				connection.setRequestProperty("Connection", "Keep-Alive");
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.setDoOutput(true);   
				   // 设置是否从httpUrlConnection读入，默认情况下是true;   
				connection.setDoInput(true);   
				   // Post 请求不能使用缓存   
				connection.setUseCaches(false);
				connection.connect();
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				in.close();
				connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("与服务器连接发生异常错误:" + e.toString());
				System.out.println("连接地址是:" + url);
			}
			return result;
	}
	
	public static void main(String[] args) {
		System.out.println(urlresult("http://127.0.0.1:8080/chatApp/app/sendSocketNotice.do?userId=93"));
	}
}