package com.yiliao.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.github.wxpay.sdk.WXPayUtil;

public class PayUtil {

	/**
	 * 支付宝转账给用户
	 * @param orderNo 订单号
	 * @param alipayId 支付宝账号
	 * @param money 支付金额
	 * @param userName 实名认证
	 */
	public static AlipayFundTransToaccountTransferResponse alipayTransfer(String orderNo,String alipayId,String money,String userName,
			String alipay_appid,String alipay_private_key,String  alipay_public_key){
		
		try {
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
					alipay_appid,
					alipay_private_key,"json","UTF-8",
					alipay_public_key,"RSA2");
			AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
			
			JSONObject json = new JSONObject();
			json.put("out_biz_no", orderNo);
			json.put("payee_type", "ALIPAY_LOGONID");
			json.put("payee_account", alipayId);
			json.put("amount", money);
			json.put("payer_show_name", SystemConfig.getValue("projectName")+"-提现");
			json.put("payee_real_name", userName);
			json.put("remark", SystemConfig.getValue("projectName")+"-提现");
			request.setBizContent(json.toString());
			return alipayClient.execute(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	
	/**
	 * 用户微信提现
	 */
	public static Map<String, String> transferAccounts(String orderNo,String openId,String money,String userName){
		
		try {
			
			WXPayConfigImpl config = WXPayConfigImpl.getInstance();
			
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("mch_appid", config.getAppID());
			data.put("mchid", config.getMchID());
			data.put("nonce_str", WXPayUtil.generateNonceStr());
			data.put("partner_trade_no", orderNo);
			data.put("openid", openId);
			data.put("check_name", "FORCE_CHECK");
			data.put("re_user_name", userName);
			data.put("amount", money);
			data.put("desc", SystemConfig.getValue("projectName")+"-提现");
			data.put("spbill_create_ip", SystemConfig.getValue("spbill_create_ip"));
			data.put("sign", WXPayUtil.generateSignature(data, config.getKey()));
			
			String mapToXml = WXPayUtil.mapToXml(data);
			
			System.out.println(mapToXml);
			
			String result = ClientCustomSSL.doRefund("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", mapToXml);
			
			return WXPayUtil.xmlToMap(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 
}
