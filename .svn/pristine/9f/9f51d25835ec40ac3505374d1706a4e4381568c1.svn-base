package com.yiliao.service.impl.version1_7;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.impl.ICommServiceImpl;
import com.yiliao.service.version1_7.SpeedDatingService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

import net.sf.json.JSONObject;

@Service(value = "speedDatingService")
public class SpeedDatingServiceImpl extends ICommServiceImpl implements SpeedDatingService {

	@Override
	public Map<String, Object> getSpeedDatingTotal(int page,String beginTime,String endTime) {
		try {
			Map<String, Object> total = new HashMap<>();		
			 List<String> arbitrarilyDays = null; 
			 
				if(null!=beginTime && !beginTime.trim().isEmpty()  && null!=endTime && !endTime.trim().isEmpty()){
					//验证两个日期之间是否大于10条记录
					if((DateUtils.differentDays(DateUtils.parse(beginTime), DateUtils.parse(endTime))+1) >= 10){
						arbitrarilyDays = DateUtils.arbitrarilyDays(page*10, DateUtils.parse(endTime));
						arbitrarilyDays = arbitrarilyDays.subList((page-1)*10, arbitrarilyDays.indexOf(beginTime)==-1?arbitrarilyDays.size():arbitrarilyDays.indexOf(beginTime)+1);
					}else{
						arbitrarilyDays = DateUtils.arbitrarilyDays((DateUtils.differentDays(DateUtils.parse(beginTime), DateUtils.parse(endTime))+1), DateUtils.parse(endTime));
					}
					
					total.put("total", DateUtils.differentDays(DateUtils.parse(beginTime),DateUtils.parse(endTime))+1);
				}else{
					arbitrarilyDays = DateUtils.arbitrarilyDays(page*10);
					total.put("total", DateUtils.differentDays(DateUtils.parse("2018-07-01"),new Date()));
					arbitrarilyDays = arbitrarilyDays.subList(arbitrarilyDays.size()-10,arbitrarilyDays.size());
				}
			
			StringBuffer body = new StringBuffer();
			body.append("SELECT COUNT(aa.t_user_id) AS total,SUM(aa.time) AS cumulativeTime FROM (");
			body.append("SELECT t_user_id,SUM(t_duration) AS time FROM t_speeddating WHERE t_begin_time BETWEEN ? AND ? GROUP BY t_user_id ");
			body.append(") aa ");
			
			List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
			arbitrarilyDays.forEach( s ->{
				Map<String, Object> data = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(body.toString(),
						s + " 00:00:00",s+" 23:59:59");
				data.put("calendar", s);
				//处理时间 得到换算后的时间
				data.put("cumulativeTime",null == data.get("cumulativeTime")?0:DateUtils.getConvert(Integer.parseInt(data.get("cumulativeTime").toString())));
				rows.add(data);
			});
		
			
			//得到列表数据
			/*List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap("SELECT * FROM ("+body + ") aa ORDER BY aa.calendar DESC LIMIT ?,10 ", (page-1)*10);
			//把秒换算成小时
			sqltoMap.forEach(s -> {
				s.put("cumulativeTime", DateUtils.getConvert(Integer.parseInt(s.get("cumulativeTime").toString())));
			});*/
			total.put("rows", rows);
			return total;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 速配日明细(non-Javadoc)
	 * @see com.yiliao.service.version1_7.SpeedDatingService#getSpeedDatingDayDetail(int)
	 */
	@Override
	public Map<String, Object> getSpeedDatingDayDetail(int page,String dayTime,String condition) {
		try {
			
			StringBuffer body = new StringBuffer();
			body.append("SELECT aa.time,u.t_idcard,u.t_nickName,aa.totalTime,aa.t_user_id FROM ( ");
			body.append("SELECT t_user_id,SUM(t_duration) AS time,DATE_FORMAT(t_begin_time,'%Y-%m-%d') AS totalTime FROM t_speeddating ");
			body.append("WHERE t_begin_time BETWEEN ? AND ? GROUP BY t_user_id ");
			body.append(") aa LEFT JOIN t_user u ON aa.t_user_id = u.t_id ");
			body.append("WHERE 1 = 1 ");
			if(StringUtils.isNotBlank(condition)) {
				body.append("AND (u.t_id = '").append(condition).append("'");
				body.append(" OR u.t_idcard = '").append(condition).append("'");
				body.append(" OR u.t_nickName LIKE '%").append(condition).append("%'");
				body.append(" OR u.t_phone LIKE '%").append(condition).append("%') ");
			}
			
			//统计查询
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT COUNT(*) AS total FROM ("+body+") bb ", dayTime+" 00:00:00",dayTime+" 23:59:59");
			
			//获取明细数据
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().
					findBySQLTOMap(body+" LIMIT ?,10 ;", dayTime+" 00:00:00",dayTime+" 23:59:59",(page-1)*10);
			
			
			//处理时间明细
			sqltoMap.forEach(s -> {
				s.put("summary",null ==s.get("time")?0:DateUtils.getConvert(Integer.parseInt(s.get("time").toString())));
				
				String[] str = dayTime.split("-");
				//获取月累计时长
				Map<String, Object> monthTime = this.getFinalDao().getIEntitySQLDAO().
				findBySQLUniqueResultToMap("SELECT SUM(t_duration) AS totalTime FROM t_speeddating WHERE t_user_id = ? AND  t_begin_time BETWEEN ? AND ? ",
						s.get("t_user_id"),DateUtils.getFirstDayOfMonth(Integer.valueOf(str[0]), Integer.valueOf(str[1])),
						DateUtils.getLastDayOfMonth(Integer.valueOf(str[0]), Integer.valueOf(str[1])));
				
				s.put("monthTime", DateUtils.getConvert(Integer.parseInt(monthTime.get("totalTime").toString())));
				
			});
			
			return new HashMap<String,Object>(){{
				put("total", total.get("total"));
				put("rows", sqltoMap);
			}};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> getAnchorSpDayDetail(int page, int userId, String dayTime) {
		try {
			//获取记录数
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap
			("SELECT COUNT(t_id) AS total FROM t_speeddating  WHERE t_user_id = ? AND t_begin_time BETWEEN ? AND ?",
					userId,dayTime + " 00:00:00" ,dayTime +" 23:59:59");
			
			//获取明细
			StringBuffer body = new StringBuffer();
			body.append("SELECT  u.t_idcard,u.t_nickName,DATE_FORMAT(s.t_begin_time,'%Y-%m-%d %T') AS t_begin_time,");
			body.append("DATE_FORMAT(s.t_end_time,'%Y-%m-%d %T') AS t_end_time,s.t_duration ");
			body.append("FROM t_speeddating s LEFT JOIN t_user u ON s.t_user_id = u.t_id ");
			body.append("WHERE t_user_id = ? AND t_begin_time BETWEEN ? AND ? ORDER BY t_begin_time DESC LIMIT ?,10 ");
			
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(body.toString(),
					userId,dayTime + " 00:00:00" ,dayTime +" 23:59:59",(page-1)*10);
			
			//处理时间明细
			sqltoMap.forEach(s -> {
				s.put("t_duration", null ==s.get("t_duration")?0:DateUtils.getConvert(Integer.parseInt(s.get("t_duration").toString())));
			});
			
			return new HashMap<String,Object>(){{
				put("total", total.get("total"));
				put("rows", sqltoMap);
			}};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取速配管理列表
	 */
	@Override
	public Map<String, Object> getSpredManList(int page) {
		try {
			//获取总记录数
			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(" SELECT COUNT(t_id) AS total FROM t_speed_manage ");
			//获取数据
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT u.t_idcard,u.t_nickName,sp.t_id,sp.t_begin_time,sp.t_end_time FROM t_speed_manage sp LEFT JOIN t_user u ON sp.t_user_id = u.t_id LIMIT ?,10;", (page-1)*10);
			 
			
			return new HashMap<String,Object>(){{
				put("total", toMap.get("total"));
				put("rows", sqltoMap);
			}};
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil saveSpeedManData(Integer t_id, int anthorId, String t_begin_time, String t_end_time) {
		try {
			
			int userId = anthorId - 10000;
			//验证 用户编号是否存在
			
			List<Map<String, Object>> userList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT * FROM t_user WHERE t_id = ?", userId);
			
			if(userList.isEmpty()) {
				return new MessageUtil(2, "用户不存在!");
			}
			//新增
			if(null == t_id) {
				
				String instr = "INSERT INTO t_speed_manage (t_user_id, t_begin_time, t_end_time, t_create_time) VALUES (?,?,?,?)";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(instr, userId,t_begin_time,t_end_time,DateUtils.
						format(new Date(), DateUtils.FullDatePattern));
				
			}else { //修改
				
				String upsql = " UPDATE t_speed_manage SET  t_user_id=?, t_begin_time=?, t_end_time=? WHERE t_id=? ; ";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(upsql, userId,t_begin_time,t_end_time,t_id);
			}
			
			return new MessageUtil(1, "已设置.");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常.");
		}
	}

	@Override
	public MessageUtil delSpeedMsg(int t_id) {
		try {
			
			String del = " DELETE FROM t_speed_manage WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(del, t_id);
			
			
			return new MessageUtil(1, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
