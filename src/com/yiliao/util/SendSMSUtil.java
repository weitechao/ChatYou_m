package com.yiliao.util;

import java.io.IOException;

import org.json.JSONException;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsMultiSenderResult.Detail;
import com.github.qcloudsms.httpclient.HTTPException;

public class SendSMSUtil {

	//APPId
	private static int appid = 1400102374; // 1400开头
	// 短信应用SDK AppKey
    private static  String appkey = "7d982aac805e639eab9dec7eea3d3fc7";
    
    // 短信模板ID，需要在短信应用中申请
    // NOTE: 这里的模板ID`7839`只是一个示例，
    // 真实的模板ID需要在短信控制台中申请
    private static  int templateId = 141866;

    // 签名
    // NOTE: 这里的签名"腾讯云"只是一个示例，
    // 真实的签名需要在短信控制台中申请，另外
    // 签名参数使用的是`签名内容`，而不是`签名ID`
    private static  String smsSign = "一聊高科";
	
	
	public static boolean sendSMS(String phone,String code){

	     // 需要发送短信的手机号码
	     String[] phoneNumbers = {phone};

	     // 指定模板ID单发短信
	        try {
	            String[] params = {code};
	            
	            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
	            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
	                templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
	            
	            System.out.print(result);
	            Detail detail = result.details.get(0);
	            if(0 == detail.result){
	            	System.out.println("发送成功!");
	            	return true;
	            }
	        } catch (HTTPException e) {
	            // HTTP响应码错误
	            e.printStackTrace();
	        } catch (JSONException e) {
	            // json解析错误
	            e.printStackTrace();
	        } catch (IOException e) {
	            // 网络IO错误
	            e.printStackTrace();
	        }
	        return false;
	}
	 
	
	
}
