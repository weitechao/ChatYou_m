package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.PushMsgService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.PushUtil;

@Service("pushMsgService")
public class PushMsgServiceImpl extends ICommServiceImpl implements PushMsgService {

	@Override
	public JSONObject getPushMsgList(int page) {
		try {
			
			String qSql = " SELECT u.t_nickName,u.t_phone,m.t_message_content,DATE_FORMAT(m.t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_message m LEFT JOIN t_user u ON m.t_user_id = u.t_id ORDER BY t_create_time DESC LIMIT ? ,10;";
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, (page-1)*10);
			
			for(Map<String, Object> m : dataList){
				if(null == m.get("t_nickName")){
					m.put("t_nickName", "聊友:"+m.get("t_phone").toString().substring(m.get("t_phone").toString().length()-4));
				}
				m.remove("t_phone");
			}
			
			String cSql = " SELECT COUNT(t_id) AS total FROM t_message ";
			
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			JSONObject json = new JSONObject();
			
			json.put("total", totalMap.get("total"));
			json.put("rows", dataList);
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 全服推送(non-Javadoc)
	 * @see com.yiliao.service.PushMsgService#addWholeServicePush(java.lang.String)
	 */
	@Override
	public void addWholeServicePush(Integer t_user_role,String push_msg) {
		try {
			
			//统计存在多少用户
			String cSql = " SELECT count(t_id) AS total FROM t_user  ";
			
			if(t_user_role != -1) {
				cSql = cSql + "WHERE t_role = "+ t_user_role;
			}
			
			
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			//得到总用户数
			int total = Integer.parseInt(totalMap.get("total").toString());
			
			int pageCount = total%1000 == 0? total /1000:total/1000+1;
			
			String qSql = "SELECT t_id FROM t_user ";
			
			if(t_user_role != -1) {
				qSql = qSql + "WHERE t_role = "+ t_user_role;
			}
			
			for (int i = 0; i < pageCount; i++) {
				
				List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql + " LIMIT ?,1000 ;", i*10);
				
				for (Map<String, Object> m : dataList) {
					//存储消息
					String messageSql = "INSERT INTO t_message (t_user_id, t_message_content, t_create_time, t_is_see) VALUES (?, ?, ?, ?);";
					
					this.getFinalDao().getIEntitySQLDAO().executeSQL(messageSql, m.get("t_id"),
							push_msg,
							DateUtils.format(new Date(), DateUtils.FullDatePattern),0);
					
					PushUtil.sendPush(Integer.parseInt(m.get("t_id").toString()), push_msg);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
