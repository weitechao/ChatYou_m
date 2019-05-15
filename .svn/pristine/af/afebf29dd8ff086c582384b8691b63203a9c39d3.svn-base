package com.yiliao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yiliao.service.DomainService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

@Service("domanService")
public class DomanServiceImpl extends ICommServiceImpl implements DomainService {

	@Override
	public Map<String, Object> getDomainList(int page) {
		try {
			
			String cSql = " SELECT COUNT(t_id) AS total FROM t_domainNamePool ";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			String qSql = "SELECT t_id,t_domain_name,t_effect_type,DATE_FORMAT(t_create_time,'%y-%m-%d %T') AS t_create_time FROM t_domainNamePool LIMIT ?,10;";
			
			List<Map<String, Object>> data = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, (page -1)*10 );
			
			
			return new HashMap<String,Object>(){{
				put("total", total.get("total"));
				put("rows", data);
			}};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加域名
	 */
	@Override
	public MessageUtil saveDomainName(String domainName, int t_effect_type) {
		try {
			if(t_effect_type == 1) {
				return new MessageUtil(1,"APP打包域名不能多次添加!"); 
			}else {
               
				String inSql = "INSERT INTO t_domainNamePool (t_domain_name, t_effect_type, t_create_time) VALUES (?, ?, ?); " ;
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, domainName,t_effect_type,DateUtils.format(new Date(), DateUtils.FullDatePattern));
				
				return new MessageUtil(1, "域名已保存!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除域名
	 */
	@Override
	public MessageUtil delDomainName(int t_id) {
		try {
			//查询要删除的域名是否是 APP打包域名
			String qSql = " SELECT * FROM t_domainNamePool WHERE t_id = ? AND t_effect_type = 1 ";
			
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, t_id);
			
			//如果不是打包域名 则可以删除数据 否则提示无法删除
			if(sqltoMap.isEmpty()) {
				String dSql = " DELETE FROM t_domainNamePool WHERE t_id = ? ; ";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
				
				return new MessageUtil(1, "域名已删除!");
				
			}else {
				return new MessageUtil(1, "APP打包域名无法删除!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
