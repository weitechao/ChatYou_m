package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.ActivityService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

@Service("activityService")
public class ActivityServiceImpl extends ICommServiceImpl implements ActivityService {

	/*
	 * 获取活动列表(non-Javadoc)
	 * @see com.yiliao.service.ActivityService#getActivityList(int)
	 */
	@Override
	public JSONObject getActivityList(int page) {
		
		JSONObject jsonObject = new JSONObject();
		try {
			
			String qSql = "SELECT t_id,t_activity_name,t_activity_number,t_join_term,t_is_enable,DATE_FORMAT(t_begin_time,'%Y-%m-%d') AS t_begin_time ,DATE_FORMAT(t_end_time,'%Y-%m-%d') AS t_end_time  FROM t_activity LIMIT ?,10 ";
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, (page-1)*10);
			
			String cSql = "SELECT count(t_id) AS totalCount FROM t_activity ";
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			
			jsonObject.put("total", totalMap.get("totalCount"));
			jsonObject.put("rows", dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取活动列表异常!", e);
		}
		return jsonObject;
	}

	/**
	 * 新增或者修改
	 */
	@Override
	public MessageUtil saveOrUpdate(Integer t_id, String t_activity_name,
			int t_activity_number, int t_join_term,String t_begin_time, String t_end_time,
			int t_is_enable) {
		MessageUtil mu = null;
		try {
			//新增数据
			if(null == t_id || 0 == t_id){
				
				String inSql = "INSERT INTO t_activity (t_activity_name, t_activity_number,t_join_term,t_is_enable, t_begin_time, t_end_time, t_create_time) VALUES (?,?,?,?,?,?,?) ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql,
						t_activity_name,t_activity_number,t_join_term,t_is_enable,t_begin_time+" 00:00:00",
						t_end_time + " 23:59:59",DateUtils.format(new Date(), DateUtils.FullDatePattern));
				mu = new MessageUtil(1, "添加活动成功!");
			}else{
				//根据编号查询出数据
				String qSql= "SELECT DATE_FORMAT(t_begin_time,'%Y-%m-%d %T') AS t_begin_time,DATE_FORMAT(t_end_time,'%Y-%m-%d %T') AS t_end_time FROM t_activity WHERE t_id = ? ";
				
				Map<String, Object> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_id.intValue());
				
				long beginTime = DateUtils.parse(dataMap.get("t_begin_time").toString(), DateUtils.FullDatePattern).getTime();
				//判断活动是否已经开始 只能暂停或者启动活动
				if(beginTime < System.currentTimeMillis()){
					
					String uSql = "UPDATE t_activity SET t_is_enable = ? WHERE t_id = ? ";
					this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_is_enable,t_id);
					mu = new MessageUtil(1, "活动已开始!只能更新启停用.");
				}else{
					
					String uSql = "UPDATE t_activity SET  t_activity_name=?, t_activity_number=?, t_join_term = ?,t_is_enable=?, t_begin_time=?, t_end_time=? WHERE t_id=? ";
					this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_activity_name,
							t_activity_number,t_join_term,t_is_enable,t_begin_time+" 00:00:00",t_end_time + " 23:59:59",t_id);
					
					mu = new MessageUtil(1, "更新完成!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或者修改活动异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil delActivity(int t_id) {
		MessageUtil mu = null ;
		try {
			//根据编号查询出数据
			String qSql= "SELECT DATE_FORMAT(t_begin_time,'%Y-%m-%d %T') AS t_begin_time,DATE_FORMAT(t_end_time,'%Y-%m-%d %T') AS t_end_time FROM t_activity WHERE t_id = ? ";
			
			Map<String, Object> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_id);
			
			long beginTime = DateUtils.parse(dataMap.get("t_begin_time").toString(), DateUtils.FullDatePattern).getTime();
			//判断活动是否已经开始 只能暂停或者启动活动
			if(beginTime < System.currentTimeMillis()){
				mu = new MessageUtil(-1, "活动已开始,无法删除数据.");
			}else{
				String dSql=" DELETE FROM t_activity WHERE t_id =? ";
			    this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
			    
			    //删除活动明细记录
			    dSql = " DELETE FROM t_activity_detail WHERE t_activity_id= ? ";
			    this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
				mu = new MessageUtil(1, "活动已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除数据异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public JSONObject getActivityDetailList(int activityId, int page) {
		
		JSONObject jsonObject = new JSONObject();
		
		try {
			
			String cSql = "SELECT count(t_id) AS total FROM t_activity_detail  WHERE t_activity_id = ?";
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql, activityId);
			
			String qSql = "SELECT * FROM t_activity_detail WHERE t_activity_id = ? limit ?,10";
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, activityId,(page-1)*10);
			
			jsonObject.put("total", totalMap.get("total"));
			jsonObject.put("rows", dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取奖励明细异常!", e);
		}
		return jsonObject;
	}

	@Override
	public MessageUtil prizeUpdate(int t_id) {
		MessageUtil mu = null;
		try {
			
			String qSql = "SELECT t_is_join FROM t_activity_detail WHERE t_id = ? ";
			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_id);
			
			int t_is_join = -1;
			
			if("0".equals(map.get("t_is_join").toString())){
				t_is_join = 1 ; 
				mu = new MessageUtil(1, "奖品已下架!");
			}else{
				t_is_join = 0 ;
				mu = new MessageUtil(1, "奖品已启用!");
			}
			
			String uSql = " UPDATE t_activity_detail SET t_is_join = ? WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_is_join,t_id);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("奖品上下架异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 添加或者修改奖品(non-Javadoc)
	 * @see com.yiliao.service.ActivityService#saveOrUpdateDetail(java.lang.Integer, int, java.lang.String, int, int)
	 */
	@Override
	public MessageUtil saveOrUpdateDetail(Integer t_id, int t_activity_id,
			String t_prize_name, int t_prize_number, int t_is_join,String t_prize_size) {
		
		MessageUtil mu = null;
		try {
			//新增奖品
			if(null == t_id || 0 == t_id){
				
				String inSql = " INSERT INTO t_activity_detail (t_activity_id, t_prize_name, t_prize_number, t_surplus_number, t_is_join,t_prize_size) VALUES (?,?,?,?,?,?) " ;
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_activity_id,t_prize_name,t_prize_number,t_prize_number,t_is_join,t_prize_size);
				mu = new MessageUtil(1, "添加奖项成功!");
			}else{ //修改奖品
				//根据编号查询出数据
				String qSql= "SELECT DATE_FORMAT(t_begin_time,'%Y-%m-%d %T') AS t_begin_time,DATE_FORMAT(t_end_time,'%Y-%m-%d %T') AS t_end_time FROM t_activity WHERE t_id = ? ";
				
				Map<String, Object> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_activity_id);
				
				long beginTime = DateUtils.parse(dataMap.get("t_begin_time").toString(), DateUtils.FullDatePattern).getTime();
				
				if(beginTime < System.currentTimeMillis()){
					mu = new MessageUtil(-1, "活动已开始!无法进行修改操作.");
				}else{
					String upSql = "UPDATE t_activity_detail SET t_is_join = ?,t_prize_name= ? ,t_prize_number = ?,t_surplus_number = ?,t_prize_size= ? WHERE t_id = ? ;";
					this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql, t_is_join,t_prize_name,t_prize_number,t_prize_number,t_prize_size,t_id);
				    
					mu = new MessageUtil(1, "修改成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加或修改奖品异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 删除奖品(non-Javadoc)
	 * @see com.yiliao.service.ActivityService#delActivityDetail(int)
	 */
	@Override
	public MessageUtil delActivityDetail(int t_id) {
		MessageUtil mu = null;
		try {
			
			String qSql = " SELECT DATE_FORMAT(a.t_begin_time,'%Y-%m-%d %T') AS t_begin_time FROM t_activity_detail d LEFT JOIN t_activity a ON d.t_activity_id = a.t_id WHERE d.t_id =  ? ";  
			
			Map<String, Object> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_id);
			
			long beginTime = DateUtils.parse(dataMap.get("t_begin_time").toString(), DateUtils.FullDatePattern).getTime();
			//活动已开始
			if(beginTime < System.currentTimeMillis()){
				mu = new MessageUtil(-1, "活动已开始,无法删除奖品.");
			}else{
				
				String dSql=" DELETE FROM t_activity_detail WHERE t_id = ? ";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
				
				mu = new MessageUtil(1, "删除成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除奖品异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public JSONObject getRewardDetailList(String nickName, int page) {
		
		JSONObject jsonObject = new JSONObject();
		try {
			StringBuffer qSql = new StringBuffer();
			qSql.append(" SELECT r.t_id,u.t_nickName,u.t_phone,d.t_prize_name ,a.t_activity_name,r.t_award_gold,DATE_FORMAT(r.t_award_time,'%Y-%m-%d %T') AS t_award_time ");
			qSql.append(" FROM t_award_record r LEFT JOIN t_user u ON r.t_user_id = u.t_id ");
			qSql.append(" LEFT JOIN t_activity_detail d ON r.t_prizedetai_id = d.t_id  ");
			qSql.append(" LEFT JOIN t_activity a  ON r.t_activity_id = a.t_id ");
			qSql.append(" WHERE 1 = 1 ");
			if(StringUtils.isNotBlank(nickName)){
				qSql.append(" AND (u.t_nickName LIKE '%").append(nickName).append("%' OR u.t_phone LIKE '%")
				.append(nickName).append("%') ");
			}
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO()
			.findBySQLTOMap(" SELECT * FROM ("+qSql+") aa ORDER BY t_award_time DESC LIMIT ?,10", (page -1) * 10);
			
			for(Map<String, Object> m : dataMap){
				if(null == m.get("t_nickName")){
					m.put("t_nickName", "聊友:"+m.get("t_phone").toString().substring(m.get("t_phone").toString().length() -4));
				}
				m.remove("t_phone");
			}
			
			//得到总记录数
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO()
			.findBySQLUniqueResultToMap("SELECT COUNT(t_id) AS total FROM ("+qSql+") aa");
			
			
			jsonObject.put("total", totalMap.get("total"));
			jsonObject.put("rows", dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}


	public static void main(String[] args) {
		System.out.println("AASCBKkwggSlAgEAAoIBAQC8a0RGVPwNEd3yC7DnVXIIQ2DOiidQcuq6whJu3jbvPbU2cqkY9LAj28Hz".length());
	}

}
