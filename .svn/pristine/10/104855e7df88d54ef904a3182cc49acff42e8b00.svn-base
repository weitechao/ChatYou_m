package com.yiliao.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.yiliao.util.SystemConfig;

public class HttpClientUtil {
	
	private static String smsPassWord = SystemConfig.getValue("smsPassWord");//非联通
	
	private static String smsPassWordByUnicom = SystemConfig.getValue("smsPassWordByUnicom");//联通
	
	/**
	 * post方式
	 */
	public static String sendSms(String moblePhone,String msgContent){
		
		String responseMsg = "";
		
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		
		httpClient.getParams().setContentCharset("GB2312");
		
		String url = "http://cq.ums86.com:8899/sms/Api/Send.do";
		
		//构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		
		postMethod.addParameter("SpCode","215736");
		postMethod.addParameter("LoginName","admin");
		postMethod.addParameter("Password",smsPassWord);
		postMethod.addParameter("MessageContent",msgContent);
		postMethod.addParameter("UserNumber",moblePhone);
		postMethod.addParameter("SerialNumber","");
		postMethod.addParameter("ScheduleTime","");
		postMethod.addParameter("ExtendAccessNum","");
		postMethod.addParameter("f","1");
		
		//执行postMethod,调用http接口
		try {
			
			httpClient.executeMethod(postMethod);
			
			responseMsg = postMethod.getResponseBodyAsString().trim();
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseMsg;
	}
	
	public static String sendSmsByUnicom(String moblePhone,String msgContent){
		
		String responseMsg = "";
		
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		
		httpClient.getParams().setContentCharset("GB2312");
		
		String url = "http://cq.ums86.com:8899/sms/Api/Send.do";
		
		//构造PostMethod的实例
		PostMethod postMethod = new PostMethod(url);
		
		postMethod.addParameter("SpCode","219286");
		postMethod.addParameter("LoginName","cq_xxty");
		postMethod.addParameter("Password",smsPassWordByUnicom);
		postMethod.addParameter("MessageContent",msgContent);
		postMethod.addParameter("UserNumber",moblePhone);
		postMethod.addParameter("SerialNumber","");
		postMethod.addParameter("ScheduleTime","");
		postMethod.addParameter("ExtendAccessNum","");
		postMethod.addParameter("f","1");
		
		//执行postMethod,调用http接口
		try {
			
			httpClient.executeMethod(postMethod);
			
			responseMsg = postMethod.getResponseBodyAsString().trim();
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseMsg;
	}
}
