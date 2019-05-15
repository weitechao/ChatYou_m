package com.yiliao.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.yiliao.service.SpreadService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.HttpContentUtil;
import com.yiliao.util.Md5Util;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.SystemConfig;
import com.yiliao.util.ZxingUtils;

import net.sf.json.JSONObject;

@Service("spreadService")
public class SpreadServiceImpl extends ICommServiceImpl implements SpreadService {

	@Override
	public MessageUtil addSpreadUser(String loguser, String logpwd, int t_gold_proportions,
			int t_vip_proportions,int t_role_id,HttpServletRequest request) {
		try {
 	
			
			//验证设置的登陆名和密码是否存在相同的的数据
			List<Map<String,Object>> map = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT * FROM t_spread_login WHERE t_user_name = ? AND t_pass_word = ? ", loguser,Md5Util.encodeByMD5(logpwd));
			
			if(!map.isEmpty()) {
				return new MessageUtil(-1, "登陆账号已存在!请重新设置.");
			}
			//写入用户信息
			this.getFinalDao().getIEntitySQLDAO().executeSQL("INSERT INTO t_user (t_nickName,t_role,t_pass_wrod,t_create_time,t_referee) VALUES (?,?,?,?,0)",
					loguser,2,logpwd,DateUtils.format(new Date(), DateUtils.FullDatePattern));
			
			//获取用户编号
			Map<String, Object> userId_map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_id FROM t_user WHERE t_nickName = ? AND t_pass_wrod = ? ", loguser,logpwd);
			
			if(!userId_map.isEmpty()) {
				
				//更新IDcard
				this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_user SET t_idcard = t_id+10000 WHERE t_id = ? ", userId_map.get("t_id"));
				
				//账号添加到账号列表中
				String sql = " INSERT INTO t_spread_login (t_user_name, t_pass_word, t_role_id, t_user_id, t_is_disable, t_create_time) VALUES (?,?,?,?,?,?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, loguser,
						Md5Util.encodeByMD5(logpwd),t_role_id,userId_map.get("t_id"),0,DateUtils.format(new Date(), DateUtils.FullDatePattern));
				
				//获取推广人ID
				int t_spread_id = 0;
				
				if(null != request.getSession().getAttribute("spreadLog")) {
					t_spread_id = JSONObject.fromObject(request.getSession().getAttribute("spreadLog")).getInt("t_user_id");
				}
				
				String domain_name  = SystemConfig.getValue("project_url");
				
				//随机获取推广域名
				StringBuffer  qSql = new StringBuffer();
				qSql.append("SELECT t_domain_name FROM t_domainNamePool AS t1 JOIN (SELECT ROUND(RAND() * ((SELECT MAX(t_id) FROM t_domainNamePool)-");
				qSql.append("(SELECT MIN(t_id) FROM t_domainNamePool))+(SELECT MIN(t_id) FROM t_domainNamePool)) AS id) AS t2 ");
				qSql.append("WHERE t1.t_id >= t2.id AND t1.t_effect_type = 0 ");
				qSql.append("ORDER BY t1.t_id LIMIT 1;");
				
				List<Map<String, Object>> randm = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql.toString());
				//如果为空 查询出打包域名
				if(randm.isEmpty()) {
					List<Map<String,Object>> arr = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT t_domain_name FROM t_domainNamePool WHERE t_effect_type = 1;");
				    if(!arr.isEmpty()) {
				    	domain_name = arr.get(0).get("t_domain_name").toString();
				    }
				}else {
					domain_name = randm.get(0).get("t_domain_name").toString();
				}
				
				//调用API生成短连接地址
				String res = HttpContentUtil.
				urlresult("http://route.showapi.com/1440-1?showapi_appid="+SystemConfig.getValue("showapi_appid")+"&"
						+ "long="+domain_name+"/share/jumpShare.html?userId="+userId_map.get("t_id")
						+"&showapi_sign="+SystemConfig.getValue("showapi_sign"));
				
				JSONObject jsonObject = JSONObject.fromObject(res).getJSONObject("showapi_res_body");
				
				String inSql = "INSERT INTO t_spread_channel (t_user_id,t_gold_proportions, t_vip_proportions, t_spread_id, t_spread_type,t_create_time,t_short_url) VALUES (?,?,?,?,0,?,?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, userId_map.get("t_id"),t_gold_proportions,t_vip_proportions
						,t_spread_id,DateUtils.format(new Date(), DateUtils.FullDatePattern),jsonObject.getString("short"));
				
				return new MessageUtil(1, "代理账号已创建!");
			}
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL("DELETE FROM t_user WHERE  t_id = ?", userId_map.get("t_id"));
			
			return new MessageUtil(-1, "添加代理失败!");
		} catch (Exception e) {
			logger.error("添加代理异常!", e);
			return new MessageUtil(0, "程序异常");
		}
	}

	@Override
	public MessageUtil getSpreadUserMsg(HttpServletRequest request) {
		try {
			
			if(null != request.getSession().getAttribute("spreadLog")) {
				
				String qSql = " SELECT t_user_name,t_pass_word FROM t_spread_login WHERE t_user_id = ?";
				
				Map<String, Object> resultToMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, JSONObject.fromObject(request.getSession()
						.getAttribute("spreadLog")).getInt("t_user_id"));
				
				//查询用户信息
				
			    qSql = "SELECT t_id,t_phone,t_qq,t_weixin,t_settlement_type,t_bank,t_gold_proportions,t_vip_proportions,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time,t_spread_id,t_short_url FROM t_spread_channel WHERE t_user_id =  ? ";
				
			    Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, JSONObject.fromObject(request.getSession()
						.getAttribute("spreadLog")).getInt("t_user_id"));
			    
			    resultToMap.putAll(toMap);
			    
			    MessageUtil mu =  new MessageUtil();
			    mu.setM_istatus(1);
			    mu.setM_object(resultToMap);
			    
			    return mu ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getSpreadUserList(int page, HttpServletRequest request) {
		try {
			
			Object attribute = request.getSession().getAttribute("spreadLog");
			
			int spreadId = 0;
			if(null != attribute) {
				spreadId = JSONObject.fromObject(attribute).getInt("t_user_id");
			}
			
			String qSql = "SELECT COUNT(t_id) AS total FROM t_spread_channel s WHERE t_spread_id = ? AND s.t_spread_type = 0 ";
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, spreadId);
			
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap("SELECT s.*,u.t_nickName,u.t_idcard  FROM t_spread_channel s LEFT JOIN t_user u ON s.t_user_id = u.t_id WHERE s.t_spread_id =  ? AND s.t_spread_type = 0 LIMIT ?,10", spreadId,(page-1)*10);
			
			JSONObject json = new JSONObject();
			
			json.put("total", total.get("total"));
			json.put("rows", sqltoMap);
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取渠道推广异常!", e);
		}
		return null;
	}

	
	@Override
	public MessageUtil getSpreadUserMesg(HttpServletRequest request) {
		try {
			
			Object attribute = request.getSession().getAttribute("spreadLog");
			MessageUtil mu = new MessageUtil();
			if(null == attribute) {
				mu.setM_istatus(1);
				mu.setM_object(10021);
			}else {
				List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT t_spread_id FROM t_spread_channel WHERE t_user_id  = ? ",
						JSONObject.fromObject(attribute).get("t_user_id"));
				
				if(sqltoMap.get(0).get("t_spread_id").toString().equals("0")) {
					mu.setM_istatus(1);
					mu.setM_object(10021);
				}else {
					mu.setM_istatus(1);
					mu.setM_object(10000);
				}
			}
			return mu;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取渠道推广权限异常!", e);
		}
		return null;
	}

	@Override
	public MessageUtil updateSpreadMesg(int t_id, String loginpwd, String t_phone, String t_qq, String t_weixin,
			int t_settlement_type, String t_bank,HttpServletRequest request) {
		try {
			
			Object attribute = request.getSession().getAttribute("spreadLog");
			//判断是否修改了登陆密码
			List<Map<String, Object>> passList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT * FROM t_spread_login WHERE t_pass_word = ? AND t_id = ? ", loginpwd,JSONObject.fromObject(attribute).getInt("t_id"));
			if(passList.isEmpty()) {
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_login SET t_pass_word = ? WHERE t_id = ? ; ", Md5Util.encodeByMD5(loginpwd)
						,JSONObject.fromObject(attribute).getInt("t_id"));
				
			}
			
			String upSql = " UPDATE t_spread_channel SET t_settlement_type = ? ";
			
			if(StringUtils.isNotBlank(t_phone)) {
				upSql+=",t_phone = '"+t_phone+"'";
			}
			if(StringUtils.isNotBlank(t_qq)) {
				upSql+=",t_qq = '"+t_qq+"'";	
			}
			if(StringUtils.isNotBlank(t_weixin)) {
				upSql+=",t_weixin = '"+t_weixin+"'";	
			}
		
			if(StringUtils.isNotBlank(t_bank)) {
				upSql+=",t_bank = '"+t_bank+"'";	
			}
			
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql+" WHERE t_id = ? ", t_settlement_type,t_id);
			
			return new MessageUtil(1, "信息已保存!");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "信息保存失败!");
		}
	}

	@Override
	public JSONObject getNextlLowerLevel(int t_spread_id, int page) {
		try {
			String cSql="SELECT count(t_id) AS total FROM t_spread_channel WHERE t_spread_id = ? ";
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql, t_spread_id);
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT u.t_nickName,u.t_idcard,(SELECT COUNT(t_id) FROM t_user WHERE t_referee = u.t_id) AS installNum,");
			sql.append("(SELECT SUM(t_value)  FROM t_wallet_detail w LEFT JOIN t_user t ON w.t_user_id = t.t_id ");
			sql.append("WHERE w.t_change_type = 0 AND w.t_change_category = 0 AND t.t_referee = u.t_id) AS money ");
			sql.append(" FROM t_spread_channel c LEFT JOIN t_user u ON c.t_user_id = u.t_id  WHERE t_spread_id = ? ");
			sql.append(" ORDER BY c.t_create_time DESC LIMIT ?,10");
		
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql.toString(), t_spread_id,(page-1)*10);
			
			JSONObject json = new JSONObject();
			json.put("total", total.get("total"));
			json.put("rows", dataList);
			return json;
		} catch (Exception e) {
			logger.error("加载{}的下一级明细异常!",t_spread_id,e);
		}
		return null;
	}

	@Override
	public MessageUtil cancelSpread(int t_id) {
		try {
			/**
			 * 设置当前推广人禁用并禁用推广人登陆账号
			 */
			String uSql = "UPDATE t_spread_channel SET t_spread_type = 1 WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_id);
			
			Map<String, Object> spreadMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_user_id FROM t_spread_channel WHERE t_id = ?", t_id);
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_login SET t_is_disable = 1 WHERE t_user_id = ?;", spreadMap.get("t_user_id"));
			
			//取消他的下一级代理资格
			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_login SET t_is_disable = 1 WHERE t_user_id IN (SELECT t_user_id FROM t_spread_channel WHERE t_spread_id = ?)", spreadMap.get("t_user_id"));
		
			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_channel SET t_spread_type = 1 WHERE t_spread_id = ? ;", spreadMap.get("t_user_id"));
			
			return new MessageUtil(1, "下架成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消代理资格异常!", e);
			return new MessageUtil(0, "程序异常!");
		}
		
	}

	@Override
	public JSONObject getSettlementList(int t_user_id, int page) {
		
		JSONObject json = new JSONObject();
		try {
			
			List<String> arbitrarilyDays = DateUtils.arbitrarilyDays(page*10);
			arbitrarilyDays = arbitrarilyDays.subList(arbitrarilyDays.size()-10,arbitrarilyDays.size());
			
			//得到当前推广人的比例
			Map<String, Object> proportionsMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_gold_proportions,t_vip_proportions FROM t_spread_channel WHERE t_user_id = ?", t_user_id);
			
			
			json.put("total", DateUtils.differentDays(DateUtils.parse("2018-07-01"),new Date()));
			
			String directSql = "SELECT r.t_recharge_type,SUM(r.t_recharge_money) AS total FROM t_recharge r LEFT JOIN t_user u ON r.t_user_id = u.t_id WHERE  u.t_referee = ? AND  r.t_order_state = 1 AND r.t_fulfil_time BETWEEN ? AND ? ";
			
			String indirect = "SELECT r.t_recharge_type,SUM(r.t_recharge_money) AS total FROM t_recharge r LEFT JOIN t_user u ON r.t_user_id = u.t_id WHERE  u.t_referee IN (SELECT t_user_id FROM t_spread_channel WHERE t_spread_id = ? ) AND  r.t_order_state = 1 AND r.t_fulfil_time BETWEEN ? AND ?";
			List<Map<String, Object>> arr = new ArrayList<>();
			
			BigDecimal gold = new BigDecimal(0);
			BigDecimal vip = new BigDecimal(0);
			
			for(String str : arbitrarilyDays) {
				Map<String, Object> map = new HashMap<>();
				map.put("data", str);
				//获取自己直接推荐的用户是否有充值
				List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(directSql+" GROUP BY r.t_recharge_type ;", t_user_id,str+" 00:00:00",str+" 23:59:59");
				
				for (Map<String,Object> s : sqltoMap) {
					if(s.get("t_recharge_type").toString().equals("0")) {
						vip = vip.add(new BigDecimal(s.get("total").toString()));
					}
					if(s.get("t_recharge_type").toString().equals("1")) {
						gold =  gold.add(new BigDecimal(s.get("total").toString()));
					}
				}
				
//				计算下一级的推广渠道用户消费金额
				List<Map<String, Object>> findBySQLTOMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(indirect+" GROUP BY r.t_recharge_type ;", t_user_id,str+" 00:00:00",str+" 23:59:59");
				
				for (Map<String,Object> s : findBySQLTOMap) {
					if(s.get("t_recharge_type").toString().equals("0")) {
						vip = vip.add(new BigDecimal(s.get("total").toString()));
					}
					if(s.get("t_recharge_type").toString().equals("1")) {
						gold =  gold.add(new BigDecimal(s.get("total").toString()));
					}
				}
				
				map.put("gold", gold.setScale(2, BigDecimal.ROUND_DOWN));
				map.put("vip", vip.setScale(2, BigDecimal.ROUND_DOWN));
				
				//计算出应该给用户提多少钱
				map.put("goldMoney", gold.multiply(new BigDecimal(proportionsMap.get("t_gold_proportions").toString()).divide(new BigDecimal(100))));
				map.put("vipMoney", vip.multiply(new BigDecimal(proportionsMap.get("t_vip_proportions").toString()).divide(new BigDecimal(100))));
				
				//统计当天的推广量
				Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT COUNT(t_id) AS total FROM t_user WHERE t_referee = ? AND t_create_time BETWEEN ? AND ? ", t_user_id,str+" 00:00:00",str+" 23:59:59");
				
				Map<String, Object> indirectTotal = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT COUNT(t_id) AS total FROM t_user WHERE t_referee IN ( SELECT t_id FROM t_user WHERE t_referee = ? AND t_create_time BETWEEN ? AND ? )", t_user_id,str+" 00:00:00",str+" 23:59:59");
				
				map.put("installNum", Integer.parseInt(total.get("total").toString())+Integer.parseInt(indirectTotal.get("total").toString()));
				
				//总的提成
				map.put("totalMoney", new BigDecimal(map.get("goldMoney").toString()).add(new BigDecimal(map.get("vipMoney").toString())).setScale(2, BigDecimal.ROUND_DOWN));
				
				arr.add(map);
				gold = new BigDecimal(0);
				vip = new BigDecimal(0);
			}
			
			json.put("rows", arr);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取明细异常!", e);
		}
		return null;
	}

	@Override
	public MessageUtil resetUserUrl(int t_id) {
		try {

			
			Map<String, Object> userId = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_user_id FROM t_spread_channel WHERE t_id = ?", t_id);
			
			String domain_name  = SystemConfig.getValue("project_url") ;
			
			//随机获取推广域名
			StringBuffer  qSql = new StringBuffer();
			qSql.append("SELECT t_domain_name FROM t_domainNamePool AS t1 JOIN (SELECT ROUND(RAND() * ((SELECT MAX(t_id) FROM t_domainNamePool)-");
			qSql.append("(SELECT MIN(t_id) FROM t_domainNamePool))+(SELECT MIN(t_id) FROM t_domainNamePool)) AS id) AS t2 ");
			qSql.append("WHERE t1.t_id >= t2.id AND t1.t_effect_type = 0 ");
			qSql.append("ORDER BY t1.t_id LIMIT 1;");
			
			List<Map<String, Object>> randm = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql.toString());
			//如果为空 查询出打包域名
			if(randm.isEmpty()) {
				List<Map<String,Object>> arr = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT t_domain_name FROM t_domainNamePool WHERE t_effect_type = 1;");
			    if(!arr.isEmpty()) {
			    	domain_name = arr.get(0).get("t_domain_name").toString();
			    }
			}else {
				domain_name = randm.get(0).get("t_domain_name").toString();
			}
			
			//调用API生成短连接地址
			String res = HttpContentUtil.
			urlresult("http://route.showapi.com/1440-1?showapi_appid="+SystemConfig.getValue("showapi_appid")+"&"
					+ "long="+domain_name+"/share/jumpShare.html?userId="+userId.get("t_user_id")
					+"&showapi_sign="+SystemConfig.getValue("showapi_sign"));
			
			JSONObject jsonObject = JSONObject.fromObject(res).getJSONObject("showapi_res_body");
			
			String shortUrl = jsonObject.getString("short");
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_channel SET t_short_url = ? WHERE t_id = ? ", shortUrl,t_id);
			
			MessageUtil mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(shortUrl);
			return mu;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> getSpreedImgList(int page, int userId) {
		try {
			//根据用户编号获取短连接
			List<Map<String, Object>> sortUrls = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT t_short_url FROM t_spread_channel WHERE t_user_id = ? ", userId);
			
			//获取图片列表
			List<Map<String, Object>> spreedImgs = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT t_id,t_img_path,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_spreed_img  LIMIT ?,10 ;", (page-1)*10);
			
			spreedImgs.forEach(s ->{
				if(!sortUrls.isEmpty()) {
					s.put("shortUrl", sortUrls.get(0).get("t_short_url"));
				}
				s.put("t_img_path", "/upload/"+s.get("t_img_path"));
				s.put("userId", userId);
				
				if(userId == 0) {
					s.put("operation", true);
				}
			});
			
			//获取总记录数
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT COUNT(t_id) AS total FROM t_spreed_img ");
			
			total.put("rows", spreedImgs);
			
			return total; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void getPreviewImg(int t_id, int userId, HttpServletResponse response) {
		try {
			//获取图片的物理硬盘地址
			Map<String, Object> imgPath = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_img_path FROM t_spreed_img WHERE t_id = ? ", t_id);
			
			//根据用户编号获取短连接
			List<Map<String, Object>> sortUrls = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT t_short_url FROM t_spread_channel WHERE t_user_id = ? ", userId);
			
			String img_path = "";
			if(sortUrls.isEmpty()) {
				img_path  = "https://www.baidu.com";
			}else {
				img_path = sortUrls.get(0).get("t_short_url").toString();
			}
			// 二维码宽度
			int width = 300;
			// 二维码高度
			int height = 300;
			BufferedImage zxingImage = null;
			try {
				// 二维码图片流
				zxingImage = ZxingUtils.enQRCode(img_path, width, height);
			} catch (WriterException e) {
				e.printStackTrace();
			}
			// 背景图片地址
			String backgroundPath = "";
			if(Config().indexOf("Windows") > -1){
				backgroundPath =SystemConfig.getValue("windows_speed_fileUrl") + imgPath.get("t_img_path");
			}else {
				backgroundPath =SystemConfig.getValue("centos_speed_fileUrl")+  imgPath.get("t_img_path");
			}
			InputStream inputStream = null;
			OutputStream os = null ;
			try {
				// 合成二维码和背景图
				BufferedImage image = ZxingUtils.drawImage(backgroundPath, zxingImage, 225, 774);
				// 绘制文字
//	            Font font = new Font("微软雅黑", Font.BOLD, 35);
//	            String text = "17000";
//	            image = ZxingUtils.drawString(image, text, 375, 647,font,new Color(244,254,189));
				// 图片转inputStream
				inputStream = ZxingUtils.bufferedImageToInputStream(image);
				
				String fielName = imgPath.get("t_img_path").toString().split("/")[1];
				
				response.setHeader("Content-Disposition","attachment;fileName=" + fielName);
				
				os = response.getOutputStream();
				
				int count = 0;
				byte[] buffer = new byte[1024 * 8];
				while ((count = inputStream.read(buffer)) != -1) {
					os.write(buffer, 0, count);
					os.flush();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				inputStream.close();
				os.close();
			}
			// 保存的图片路径 C:\Users\Administrator\Desktop
//			String originalFileName = "C:/Users/Administrator/Desktop/99999.png";
//			try {
//				// 保存为本地图片
//				ZxingUtils.saveFile(inputStream, originalFileName);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 判断操作系统
	 */
	public static String  Config() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress().toString(); // 获取本机ip
			String hostName = addr.getHostName().toString(); // 获取本机计算机名称
			System.out.println("本机IP：" + ip + "\n本机名称:" + hostName);
			Properties props = System.getProperties();
			System.out.println("操作系统的名称：" + props.getProperty("os.name"));
			System.out.println("操作系统的版本号：" + props.getProperty("os.version"));
			
			return props.getProperty("os.name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Centos";
	}

	/**
	 * 上传图片
	 */
	@Override
	public MessageUtil saveSpreadImg(String img_url) {
		try {
			
			if(StringUtils.isNotBlank(img_url)) {
				this.getFinalDao().getIEntitySQLDAO().
				executeSQL("INSERT INTO t_spreed_img (t_img_path, t_create_time) VALUES (?,?);", 
						img_url,DateUtils.format(new Date(), DateUtils.FullDatePattern));
				
				return new MessageUtil(1, "上传成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除文件
	 */
	@Override
	public MessageUtil delSpreadImg(int id) {
		try {
			
			//根据编号查询出文件地址
			
			Map<String, Object> img_path = this.getFinalDao().getIEntitySQLDAO().
			findBySQLUniqueResultToMap("SELECT t_img_path FROM t_spreed_img WHERE t_id = ?;", id);
			
			
			// 背景图片物理地址
			String backgroundPath = "";
			if(Config().indexOf("Windows") > -1){
				backgroundPath =SystemConfig.getValue("windows_speed_fileUrl") + img_path.get("t_img_path");
			}else {
				backgroundPath =SystemConfig.getValue("centos_speed_fileUrl")+  img_path.get("t_img_path");
			}
			
			File file = new File(backgroundPath);
			
			if(file.exists()) {
				file.delete();
			}
		
			//删除记录
			this.getFinalDao().getIEntitySQLDAO().executeSQL("DELETE FROM t_spreed_img WHERE t_id = ? ", id);
		
			return new MessageUtil(1, "文件已删除!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil startSpreed(int userId) {
		try {
			//启用当前用户的代理资格
			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_login SET t_is_disable = 0 WHERE t_user_id = ?", userId);
			
			//启动当前用户的下一级代理资格
			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_login SET t_is_disable = 0 WHERE t_user_id IN (SELECT t_user_id FROM t_spread_channel WHERE t_spread_id = ?)", userId);
			
			//启用相关资料记录
			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_channel SET t_spread_type = 0 WHERE t_user_id = ?;", userId);
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL("UPDATE t_spread_channel SET t_spread_type = 0 WHERE t_spread_id = ? ;", userId);
			
			return new MessageUtil(1, "启用成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
