package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.FeedBackService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PushUtil;
/**
 * 举报处理结果
 * @author Administrator
 *
 */
@Service("feedBackService")
public class FeedbackServiceImpl extends ICommServiceImpl implements FeedBackService {

	/*
	 * 获取举报列表(non-Javadoc)
	 * @see com.yiliao.service.ReportService#getReportList(int)
	 */
	@Override
	public JSONObject getFeedbackList(String condition,String beginTime,String endTime,int page) {
		JSONObject json = new JSONObject();

		try {

			String countSql = "SELECT count(f.t_id) AS totalCount FROM t_feedback  f LEFT JOIN t_user u ON f.t_user_id = u.t_id WHERE 1=1 ";
			String sql = "SELECT f.t_id,u.t_nickName,f.t_phone,f.t_content,f.t_img_url,DATE_FORMAT(f.t_create_time,'%Y-%m-%d %T') AS t_create_time,f.t_is_handle,f.t_handle_res,f.t_handle_img,DATE_FORMAT(f.t_handle_time,'%Y-%m-%d %T') AS t_handle_time  FROM t_feedback f LEFT JOIN t_user u ON f.t_user_id = u.t_id WHERE 1=1 ";

			//查询条件
			if(StringUtils.isNotBlank(condition)){
				countSql = countSql + " AND (u.t_nickName LIKE '%"+condition+"%' OR f.t_phone LIKE '%"+condition+"%' OR f.t_content LIKE '%"+condition+"%') ";
			    sql = sql + " AND (u.t_nickName LIKE '%"+condition+"%' OR f.t_phone LIKE '%"+condition+"%' OR f.t_content LIKE '%"+condition+"%') ";
			}
			
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)){
				countSql = countSql + " AND f.t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59'";
				sql = sql +  " AND f.t_create_time BETWEEN '"+beginTime+" 00:00:00' AND '"+endTime+" 23:59:59'";
			}
			
			sql = sql + " ORDER BY f.t_id DESC LIMIT ?,10;";
			
			Map<String, Object> totalCount = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);


			List<Map<String, Object>> listMap = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLTOMap(sql, (page - 1) * 10);

			json.put("total", totalCount.get("totalCount"));
			json.put("rows", listMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户列表异常!", e);
		}

		return json;
	}

	@Override
	public MessageUtil upateFeedBack(int t_id, String t_handle_comment,
			String img_url) {
		
		MessageUtil mu = null;
		try {
			
			String sql = "UPDATE t_feedback SET t_handle_res=?, t_is_handle='1', t_handle_time=?, t_handle_img=? WHERE t_id= ?;";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_handle_comment,DateUtils.format(new Date(), DateUtils.FullDatePattern),
					img_url,t_id);
			
			
			Map<String, Object> userId = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_user_id FROM t_feedback WHERE t_id= ?", t_id);
			
			//存储消息
			String messageSql = "INSERT INTO t_message (t_user_id, t_message_content, t_create_time, t_is_see) VALUES (?, ?, ?, ?);";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(messageSql, userId.get("t_user_id"),
					"您反馈的内容已处理.",
					DateUtils.format(new Date(), DateUtils.FullDatePattern),0);
			
			PushUtil.sendPush(Integer.parseInt(userId.get("t_user_id").toString()), "您反馈的内容已处理.");
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("处理意见反馈", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	

}
