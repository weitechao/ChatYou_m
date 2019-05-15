package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yiliao.service.SmsService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

@Service("smsService")
public class SmsServiceImpl extends ICommServiceImpl implements SmsService {

	
	private MessageUtil mu = null;
	
	@Override
	public List<Map<String, Object>> getSmsList() {
		
		List<Map<String, Object>> listMap = null;
		
		try {
			String sql = "SELECT * FROM t_sms_steup";
			
			listMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("程序异常!", e);
		}
		return listMap;
	}

	/*
	 * 修改短信设置状态(non-Javadoc)
	 * @see com.yiliao.service.SmsService#enableOrDisableSmsSteup(int)
	 */
	@Override
	public MessageUtil enableOrDisableSmsSteup(int smsId) {
		
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE t_sms_steup SET ") ;
			sql.append("t_is_enable = CASE t_is_enable  ") ;
			sql.append("  WHEN 0 THEN 1  ") ;
			sql.append("  WHEN 1 THEN 0 ") ;
			sql.append("END ") ;
			sql.append("WHERE t_id = ? ") ;
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql.toString(), smsId);
			
			mu = new MessageUtil(1, "更新成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改短信设置状态异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil delSmsSteup(int smsId) {
		try {
			
			String sql  = " DELETE FROM t_sms_steup WHERE t_id = ? " ;
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, smsId);
			
			mu = new MessageUtil(1, "删除成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除短信设置记录异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil getDataById(int smsId) {
		try {
			String sql = "SELECT * FROM t_sms_steup WHERE t_id = ?";
			
			Map<String, Object> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, smsId);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询短信设置记录异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil saveOrUpdate(Integer t_id, String appid, String appkey,
			String templateId, String smsSign, int t_is_enable,
			int t_platform_type) {
		try {
			//新增
			if(null == t_id){
				String sql = "INSERT INTO t_sms_steup (appid, appkey, templateId, smsSign, t_is_enable, t_platform_type, t_create_time)"
						  + " VALUES (?, ?, ?, ?, ?, ?, ?);";
			    this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, appid,appkey,templateId,smsSign,t_is_enable,
			    		t_platform_type,DateUtils.format(new Date(), DateUtils.FullDatePattern));
			}else{ //修改
				String sql = "UPDATE t_sms_steup SET appid=?, appkey=?, templateId=?, smsSign=?, t_is_enable=?, t_platform_type=?, t_create_time=? "
						+ "WHERE t_id= ? ;";
			    this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, appid,appkey,templateId,smsSign,t_is_enable,
			    		t_platform_type,DateUtils.format(new Date(), DateUtils.FullDatePattern),t_id);
			}
			
			mu = new MessageUtil(1, "");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或者修改短信设置记录异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
