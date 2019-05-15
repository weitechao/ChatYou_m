package com.yiliao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class ErrorMsgUtil {

    static Logger logger=LoggerFactory.getLogger(ErrorMsgUtil.class);
	
	public static void error(Exception e,String content){
		e.printStackTrace();
		StackTraceElement stackTraceElement= e.getStackTrace()[0];
		logger.info(content);
		logger.info("-------------------------------------------");
		logger.info("错误行号-->"+stackTraceElement.getLineNumber());
		logger.info("错误方法-->"+stackTraceElement.getMethodName());
		logger.info("错误所在类-->"+stackTraceElement.getFileName());
		logger.info("-------------------------------------------");
	}
}
