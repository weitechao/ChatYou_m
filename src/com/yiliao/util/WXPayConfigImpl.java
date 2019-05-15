package com.yiliao.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class WXPayConfigImpl implements WXPayConfig {

	private String appId = SystemConfig.getValue("appId");
	private String mchId =  SystemConfig.getValue("mchId");// 商户号1518593541
	private String key = SystemConfig.getValue("weixinKey");// 密钥key

	private byte[] certData;
	private static WXPayConfigImpl INSTANCE;

	private WXPayConfigImpl() throws Exception {
//		String certPath = "src/resources/apiclient_cert.p12";// 证书位置
//		File file = new File(certPath);
//		InputStream certStream = new FileInputStream(file);
//		this.certData = new byte[(int) file.length()];
//		certStream.read(this.certData);
//		certStream.close();
	}

	public static WXPayConfigImpl getInstance() throws Exception {
		if (INSTANCE == null) {
			synchronized (WXPayConfigImpl.class) {
				if (INSTANCE == null) {
					INSTANCE = new WXPayConfigImpl();
				}
			}
		}
		return INSTANCE;
	}

	public String getAppID() {
		return appId;
	}

	public String getMchID() {
		return mchId;
	}

	public String getKey() {
		return key;
	}

	public InputStream getCertStream() {
		ByteArrayInputStream certBis;
		certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	public int getHttpConnectTimeoutMs() {
		return 2000;
	}

	public int getHttpReadTimeoutMs() {
		return 10000;
	}
}
