package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.ReportService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PushUtil;
/**
 * 举报处理结果
 * @author Administrator
 *
 */
@Service("reportService")
public class ReportServiceImpl extends ICommServiceImpl implements
		ReportService {

	/*
	 * 获取举报列表(non-Javadoc)
	 * @see com.yiliao.service.ReportService#getReportList(int)
	 */
	@Override
	public JSONObject getReportList(String condition,String beginTime,String endTime,int page) {
		JSONObject json = new JSONObject();

		try {

			String countSql = "SELECT count(r.t_id) AS totalCount FROM t_report r LEFT JOIN t_user u ON r.t_user_id = u.t_id "+
					" LEFT JOIN t_user u1 ON r.t_cover_user_id = u1.t_id WHERE 1=1 ";

			String sql = "SELECT r.t_id,u.t_nickName,u.t_phone,u1.t_nickName AS coverName,u1.t_phone AS coverPhone,r.t_comment,r.t_img,DATE_FORMAT(r.t_create_time,'%Y-%m-%d %T') AS t_create_time,r.t_is_handle,r.t_handle_comment,DATE_FORMAT(r.t_handle_time,'%Y-%m-%d %T') AS t_handle_time FROM t_report r LEFT JOIN t_user u ON r.t_user_id = u.t_id "+
					" LEFT JOIN t_user u1 ON r.t_cover_user_id = u1.t_id WHERE 1=1 ";

			if(StringUtils.isNotBlank(condition)){
				countSql = countSql + " AND (u.t_nickName LIKE '%"+condition+"%' OR u1.t_nickName LIKE '%"+condition+"%' OR r.t_comment LIKE '%"+condition+"%') ";
			    sql = sql + " AND (u.t_nickName LIKE '%"+condition+"%' OR u1.t_nickName LIKE '%"+condition+"%' OR r.t_comment LIKE '%"+condition+"%') ";
			}
			
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)){
				countSql = countSql + " AND r.t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59'";
				sql = sql + " AND r.t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59'";
			}
			
			sql = sql + " ORDER BY t_is_handle ASC,r.t_id DESC LIMIT ?,10; ";
			
			Map<String, Object> totalCount = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);


			List<Map<String, Object>> listMap = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLTOMap(sql, (page - 1) * 10);
			
			for(Map<String, Object> m : listMap){
				if(null == m.get("t_nickName")){
					m.put("t_nickName", "聊友:"+m.get("t_phone").toString().substring(m.get("t_phone").toString().length()-4));
				}
				if(null == m.get("coverName") && null != m.get("coverPhone")){
					m.put("coverName", "聊友:"+m.get("coverPhone").toString().substring(m.get("coverPhone").toString().length() -4));
				}
				
				m.remove("t_phone");
				m.remove("coverPhone");
			}

			json.put("total", totalCount.get("totalCount"));
			json.put("rows", listMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户列表异常!", e);
		}

		return json;
	}

	@Override
	public MessageUtil handleReport(int t_id, String t_handle_comment) {
		
		MessageUtil mu = null ;
		
		try {
			String upSql ="UPDATE t_report SET  t_is_handle=1, t_handle_comment=?, t_handle_time=? WHERE t_id=?;" ;
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql, t_handle_comment,DateUtils.format(new Date(), DateUtils.FullDatePattern),
					t_id);
			
			
			Map<String, Object> userId = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_user_id FROM t_report WHERE t_id = ?", t_id);
			
			//存储消息
			String messageSql = "INSERT INTO t_message (t_user_id, t_message_content, t_create_time, t_is_see) VALUES (?, ?, ?, ?);";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(messageSql, userId.get("t_user_id"),
					"您反馈的内容已处理.",
					DateUtils.format(new Date(), DateUtils.FullDatePattern),0);
			
			PushUtil.sendPush(Integer.parseInt(userId.get("t_user_id").toString()), "您的举报已处理.");
			
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("处理举报异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
