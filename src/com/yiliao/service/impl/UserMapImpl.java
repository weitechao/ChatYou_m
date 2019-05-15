package com.yiliao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yiliao.service.UserMapService;

@Service("userMapService")
public class UserMapImpl extends ICommServiceImpl implements UserMapService {

	@Override
	public Object getOnLineUserMapList() {
		try {
			
			String qSql = "SELECT u.t_id,u.t_nickName,u.t_phone,u.t_idcard,c.t_lat,c.t_lng FROM t_user u LEFT JOIN t_coordinate c ON c.t_user_id = u.t_id WHERE t_onLine = 0 AND c.t_lat IS NOT NULL AND c.t_lng IS NOT NULL  ";
			
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql);
			
			sqltoMap.forEach(s ->{
				if(null == s.get("t_nickName")) {
					s.put("t_nickName", "聊友:"+s.get("t_phone").toString().substring(s.get("t_phone").toString().length()-4));
				}
				s.remove("t_phone");
				s.put("title", "昵称:"+s.get("t_nickName")+"[ID:"+s.get("t_idcard")+"]");
				s.remove("t_id");
				s.remove("t_nickName");
			});
			
			return sqltoMap ; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
