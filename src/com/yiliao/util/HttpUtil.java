package com.yiliao.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

public class HttpUtil {

	/**
	 * @方法名 httpConnection
	 * @说明 (使用url获取网络数据)
	 * @param 参数
	 * @param URL
	 * @param 参数
	 * @return 设定文件
	 * @return JSONObject 返回类型
	 * @作者 石德文
	 * @throws 异常
	 */
	public static JSONObject httpConnection(String URL) {
		URL url = null;
		HttpURLConnection connection = null;
		// 生成验证码
		try {
			url = new URL(URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// connection.setRequestProperty("Content-type", "text/html");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setUseCaches(false);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			return new JSONObject().fromObject(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	public void text(String content) throws IOException {

		FileWriter fileWriter = new FileWriter("D:\\111\\123456.txt");
		fileWriter.write(content);
		fileWriter.flush();
		fileWriter.close();
	}

	/**
	 * @方法名 getSend
	 * @说明 (短信通道post方法)
	 * @param 参数
	 * @param strUrl
	 * @param 参数
	 * @param param
	 * @param 参数
	 * @return 设定文件
	 * @return String 返回类型
	 * @作者 石德文
	 * @throws 异常
	 */
	public static String postSend(String strUrl, String param) {

		URL url = null;
		HttpURLConnection connection = null;

		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();

			// POST����ʱʹ��
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.writeBytes(param);
			out.flush();
			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}

			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

	}
 
	
	/**
	 * @方法名 httpClent
	 * @说明 (传递参数的方法)
	 * @param 参数
	 *            @return 设定文件
	 * @return JSONObject 返回类型
	 * @作者 石德文
	 * @throws 异常
	 */
	@SuppressWarnings("static-access")
	public static JSONObject httpClent(String httpUrl,String content){
		JSONObject json=null;
		   try {
	            //创建连接
	            URL url = new URL(httpUrl);
	            HttpURLConnection connection = (HttpURLConnection) url
	                    .openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setRequestMethod("POST");
	            connection.setUseCaches(false);
	            connection.setRequestProperty("Accept-Charset", "utf-8");
	            connection.setRequestProperty("contentType", "utf-8");
	            connection.setInstanceFollowRedirects(true);
	            connection.setRequestProperty("Content-Type",
	                    "application/x-www-form-urlencoded");

	            connection.connect();
	            
	            PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(),"utf-8"));  
	            out.println(content);  
	            out.close(); 

	            //读取响应
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream()));
	            String lines;
	            StringBuffer sb = new StringBuffer("");
	            while ((lines = reader.readLine()) != null) {
	                lines = new String(lines.getBytes(), "utf-8");
	                sb.append(lines);
	            }
	            json=JSONObject.fromObject(sb.toString());
	            reader.close();
	            // 断开连接
	            connection.disconnect();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
 
	    return 	json;
	}

	/**
	 * 转为16进制方法
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String paraTo16(String str) throws UnsupportedEncodingException {
		String hs = "";

		byte[] byStr = str.getBytes("UTF-8");
		for (int i = 0; i < byStr.length; i++) {
			String temp = "";
			temp = (Integer.toHexString(byStr[i] & 0xFF));
			if (temp.length() == 1)
				temp = "%0" + temp;
			else
				temp = "%" + temp;
			hs = hs + temp;
		}
		return hs.toUpperCase();

	}
	

}
