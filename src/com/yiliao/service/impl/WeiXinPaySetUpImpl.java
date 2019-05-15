package com.yiliao.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.WeiXinPaySetUpService;
import com.yiliao.util.MessageUtil;

@Service("weiXinPaySetUpService")
public class WeiXinPaySetUpImpl extends ICommServiceImpl implements WeiXinPaySetUpService {

	@Override
	public JSONObject getWeiXinPaySetUpList(int page) {
		try {
			
			String qSql = "SELECT * FROM t_weixinpay_setup";
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql);
			
			JSONObject json = new JSONObject();
			
			
			String cSql = "SELECT COUNT(t_id) AS total FROM t_weixinpay_setup";
			
			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			
			json.put("total", toMap.get("total"));
			json.put("rows", dataMap);
			
			return json;
			
		} catch (Exception e) {
			logger.error("获取微信支付设置异常!", e);
		}
		return null;
	}

	
	@Override
	public MessageUtil addOrUpWeiXinPaySetUp(Integer t_id, String appId,
			String t_mchid, String t_mchid_key, String t_certificate_url) {
		
		MessageUtil mu = null ;
		try {
			
			if(null == t_id || 0 == t_id){
				String inSql = " INSERT INTO t_weixinpay_setup (appId, t_mchid, t_mchid_key, t_certificate_url) VALUES (?,?,?,?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, appId,t_mchid,t_mchid_key,t_certificate_url);
			}else{
				String uSql = " UPDATE t_weixinpay_setup SET  appId=?, t_mchid=?, t_mchid_key=?, t_certificate_url=? WHERE t_id= ? ; ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, appId,t_mchid,t_mchid_key,t_certificate_url,t_id);
			}
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或修改异常!", e);
			mu = new MessageUtil(0, "");
		}
		return mu;
	}


	@Override
	public MessageUtil delWeiXinPaySetUp(int t_id) {
		MessageUtil mu = null ;
		try {
			
			String dSql = " DELETE FROM t_weixinpay_setup WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除微信支付设置异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
