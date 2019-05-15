package com.yiliao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.datatype.DatatypeAttribute;
import org.springframework.stereotype.Service;

import com.yiliao.service.AlipaySetUpServvice;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

@Service("alipaySetUpServvice")
public class AlipaySetUpServiceImpl extends ICommServiceImpl implements AlipaySetUpServvice {

	@Override
	public MessageUtil setAlipaySetUp(Integer t_id, String t_alipay_appid, String t_alipay_private_key,
			String t_alipay_public_key) {
		try {
			//新增
			if(t_id == null || t_id <=0) {
				String inSql = "INSERT INTO t_alipay_setup (t_alipay_appid, t_alipay_public_key, t_alipay_private_key, t_create_time) VALUES (?, ?, ?, ?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_alipay_appid.trim(),t_alipay_public_key.trim(),t_alipay_private_key.trim(),
						DateUtils.format(new Date(), DateUtils.FullDatePattern));
			}else { //修改
				
				String uSql = "UPDATE t_alipay_setup SET t_alipay_appid=?, t_alipay_public_key=?, t_alipay_private_key=?, t_create_time=? WHERE t_id=?; ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_alipay_appid,t_alipay_public_key,t_alipay_private_key,
						DateUtils.format(new Date(), DateUtils.FullDatePattern),t_id);
			}
			
			return new MessageUtil(1,"更新成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	/*
	 * 删除支付宝设置
	 * (non-Javadoc)
	 * @see com.yiliao.service.AlipaySetUpServvice#delAlipaySetUp(int)
	 */
	@Override
	public MessageUtil delAlipaySetUp(int t_id) {
		try {
			
			String dSql = " DELETE FROM t_alipay_setup WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
			
			return new MessageUtil(1, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	@Override
	public JSONObject getAlipaySetUpList(int page) {
		try {
			
			String qSql = " SELECT t_id,t_alipay_appid,t_alipay_private_key,t_alipay_public_key,DATE_FORMAT(t_create_time,'%y-%m-%d %T') AS t_create_time  FROM t_alipay_setup ";
			
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql);
			
			return JSONObject.fromObject(new HashMap<String,Object>(){{
				put("total", sqltoMap.isEmpty()?0:1);
				put("rows", sqltoMap);
			}});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
