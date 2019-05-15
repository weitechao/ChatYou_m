package com.yiliao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yiliao.service.SimulationService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

@Service("simulationService")
public class SimulationServiceImpl extends ICommServiceImpl implements SimulationService {

	@Override
	public Map<String, Object> getSimulationList(int page) {
		try {
			
			String cSql = " SELECT COUNT(t_id) AS total FROM t_simulation ";
			
			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			String qSql = "SELECT t_id,t_centent,t_sex,DATE_FORMAT(t_create_time,'%y-%m-%d %T') AS t_create_time FROM t_simulation LIMIT ?,10;";
			
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, (page-1)*10);
			
			return new HashMap<String,Object>(){{
				put("total", toMap.get("total"));
				put("rows", sqltoMap);
			}};
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增模拟消息
	 */
	@Override
	public MessageUtil saveSimulation(String content, int sex) {
		try {
			
			String inSql = " INSERT INTO t_simulation (t_centent, t_sex, t_create_time) VALUES (?, ?, ?); ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, content,sex,DateUtils.format(new Date(), DateUtils.FullDatePattern));
			
			return new MessageUtil(1, "模拟消息已保存!");
		}catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	/**
	 * 删除模拟消息
	 */
	@Override
	public MessageUtil delSimulation(int t_id) {
		try {
			
			String dSql = " DELETE FROM t_simulation WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(dSql, t_id);
			return new MessageUtil(1, "已删除!");
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

}
