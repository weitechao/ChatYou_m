package com.yiliao.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.yiliao.service.HomeService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
/**
* CopyRright (c)2016-版权所有: 重庆西信天元数据资讯有限公司                          
* @项目工程名 WXManage                                      
* @Module ID   <(模块)类编号，可以引用系统设计中的类编号>    
    * Comments  <对此类的描述，可以引用系统设计中的描述>                                           
* @JDK 版本(version) JDK1.6.45                           
* @命名空间  com.yiliao.service.impl               
* @作者(Author) 石德文           
* @创建日期  2016年3月25日  上午9:49:21 
* @修改人                                            
* @修改时间  <修改日期，格式:YYYY-MM-DD>                                    
    * 修改原因描述：  
* @Version 版本号 V1.0   
* @类名称 HomeServiceImpl
* @描述 (前端页面获取数据实现类)
 */
@Service("homeService")
public class HomeServiceImpl extends ICommServiceImpl implements HomeService{

	private MessageUtil mu = null;
	
	/*
	 * 获取总的充值金额(non-Javadoc)
	 * @see com.yiliao.service.HomeService#getTotalRecharge()
	 */
	@Override
	public MessageUtil getTotalRecharge() {
		try {
			
			String sql = "SELECT SUM(t_recharge_money) AS totalMoney FROM t_recharge WHERE t_order_state = 1";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(null == total.get("totalMoney")?0:total.get("totalMoney"));
//			mu.setM_object(1132132134);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计充值异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/*
	 * 获取今日充值金额(non-Javadoc)
	 * @see com.yiliao.service.HomeService#getToDayRecharge()
	 */
	@Override
	public MessageUtil getToDayRecharge() {
		try {
			
			String sql= "SELECT SUM(t_recharge_money) AS totalMoney FROM t_recharge WHERE t_order_state = 1 AND t_create_time BETWEEN ? AND ? ";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql,
					DateUtils.getToday()+" 00:00:00" , DateUtils.getToday()+" 23:59:59 ");
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(null == total.get("totalMoney")?0:total.get("totalMoney"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计今日充值异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/*
	 * 统计总用户数(non-Javadoc)
	 * @see com.yiliao.service.HomeService#getTotalUser()
	 */
	@Override
	public MessageUtil getTotalUser() {
		try {
			String sql = "SELECT count(t_id) AS total FROM t_user ";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(null == total.get("total")?0:total.get("total"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计总用户异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/*
	 * 统计今日新增数(non-Javadoc)
	 * @see com.yiliao.service.HomeService#getToDayUser()
	 */
	@Override
	public MessageUtil getToDayUser() {
		try {
			String sql = "SELECT count(t_id) AS total FROM t_user  WHERE t_create_time BETWEEN ? AND ?" ;
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql,
					DateUtils.getToday()+" 00:00:00" , DateUtils.getToday()+" 23:59:59 ");
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(null == total.get("total")?0:total.get("total"));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计今日新增用户异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil getCountSexDistribution() {
		try {
			//得到总的人数
			String sql = "select count(t_id) total,sum(case when t_sex = 1 then 1 else 0 end ) male,sum(case when t_sex=0 then 1 else 0 end ) girl  from t_user ";
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql);
			
			int totalNumber = Integer.parseInt(total.get("total").toString());
			int male = Integer.parseInt(null == total.get("male")?"0":total.get("male").toString());
			int girl = Integer.parseInt(null == total.get("girl")?"0":total.get("girl").toString());
			
			total.clear();
			
			JSONArray array = new JSONArray();
			total.put("value", male);
			total.put("label", "男");
			if(male == 0 && girl == 0){
				total.put("formatted","0%");
			}else{
				total.put("formatted", calculationPercent(totalNumber, male)+"%");
			}
			
			array.add(total);
			
			total.clear();
			
			
			total.put("value", girl);
			total.put("label", "女");
			if(male == 0 && girl == 0){
				total.put("formatted","0%");
			}else{
				total.put("formatted", calculationPercent(totalNumber, girl)+"%");
			}
			
			array.add(total);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(array);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计男女比例异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}
	
	
	/**
	 * 计算百分比
	 * @param total
	 * @param number
	 * @return
	 */
	public static String calculationPercent(int total,int number){
		
		NumberFormat numberFormat = NumberFormat.getInstance();  
        // 设置精确到小数点后2位  
        numberFormat.setMaximumFractionDigits(2);  
  
        return numberFormat.format((float) number / (float) total * 100);  
	}
	
	public static void main(String[] args) {
		
		System.out.println(calculationPercent(3, 2));
	}

	/*
	 * 统计7天内的数据(non-Javadoc)
	 * @see com.yiliao.service.HomeService#getSevenDaysList()
	 */
	@Override
	public MessageUtil getSevenDaysList() {
		try {
			
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT days,sum(total) AS '日活跃',SUM(money) AS '日充值' FROM ( ");
			sb.append(" SELECT DATE_FORMAT(t_login_time,'%Y-%m-%d') AS days,count(t_id) AS total,0 AS money FROM t_user WHERE t_login_time BETWEEN ? AND ?  GROUP BY days");
			sb.append(" UNION ");
			sb.append(" SELECT DATE_FORMAT(t_create_time,'%Y-%m-%d') AS days,0 AS total,SUM(t_recharge_money) AS money  FROM t_recharge WHERE t_order_state = 1 AND t_create_time BETWEEN ? AND ? GROUP BY days");
			sb.append(" ) TABL  GROUP BY days ");
			//得到过去几天的数据
			ArrayList<String> days = DateUtils.arbitrarilyDays(8);
			
			JSONArray array = new JSONArray();
			
			for (int i = 1; i < days.size(); i++) {
				List<Map<String, Object>> userList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(),
						days.get(i)+" 00:00:00",days.get(i)+" 23:59:59",days.get(i)+" 00:00:00",days.get(i)+" 23:59:59");
				
				if(null == userList || userList.isEmpty()){
				    Map<String, Object> map = new HashMap<String, Object>();
				    map.put("days", days.get(i));
				    map.put("日活跃", 0);
				    map.put("日充值", 0);
				    array.add(map);
				}else{
					array.add(userList.get(0));
				}
			}
			
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("days", days.get(0));
			 map.put("日活跃", 0);
			 map.put("日充值", 0);
			 array.add(map);
			
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(array);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计最近7天的数据!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/*
	 * 统计年度充值(non-Javadoc)
	 * @see com.yiliao.service.HomeService#statisticsYearRecharge()
	 */
	@Override
	public MessageUtil statisticsYearRecharge() {
		try {
			
			String sql = "SELECT DATE_FORMAT(t_create_time,'%Y-%m') AS days,SUM(t_recharge_money) AS '金额'  FROM t_recharge WHERE t_create_time BETWEEN ? AND ? AND t_order_state =1  GROUP BY days" ;
			
			
			int year = Integer.parseInt(DateUtils.format(new Date(), "yyyy"));
			
			
			JSONArray array = new JSONArray();
			
			for (int i = 1; i <= 12; i++) {
				List<Map<String, Object>> listMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, DateUtils.getFirstDayOfMonth(year, i),
						DateUtils.getLastDayOfMonth(year, i));
				
				if(null ==listMap ||  listMap.isEmpty()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("days", year+"-"+(i<10?"0":"")+i);
					map.put("金额", 0);
					array.add(map);
				}else {
					array.add(listMap.get(0));
				}
			}
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(array);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计年度充值异常数据!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/*
	 * 获取年度会员数统计(non-Javadoc)
	 * @see com.yiliao.service.HomeService#getYearMembere()
	 */
	@Override
	public MessageUtil getYearMembere() {
		
		try {
			
			String sql = "SELECT DATE_FORMAT(t_create_time,'%Y-%m') AS days,count(t_id) AS '会员'  FROM t_user WHERE t_create_time BETWEEN ? AND ?  GROUP BY days";
			
			int year = Integer.parseInt(DateUtils.format(new Date(), "yyyy"));
			
			JSONArray array = new JSONArray();
			
			for (int i = 1; i <= 12; i++) {
				List<Map<String, Object>> listMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, DateUtils.getFirstDayOfMonth(year, i),
						DateUtils.getLastDayOfMonth(year, i));
				
				if(null ==listMap ||  listMap.isEmpty()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("days", year+"-"+(i<10?"0":"")+i);
					map.put("会员", 0);
					array.add(map);
				}else {
					array.add(listMap.get(0));
				}
			}
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(array);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("统计年度充值异常数据!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}
	
	
	
}
