package com.yiliao.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.VagueService;
import com.yiliao.util.MessageUtil;

/**
 * 模糊Service实现类
 * 
 * @author Administrator
 * 
 */
@Service("vagueService")
public class VagueServiceImpl extends ICommServiceImpl implements VagueService {

	private MessageUtil mu = null;
	
	@Override
	public MessageUtil getVagueList(int page) {
		try {
			String totalCount = "SELECT COUNT(t_id) AS total  FROM t_vague_check ";

			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap(totalCount);

			// 查询数据
			String sql = "SELECT t_id,t_img_url FROM t_vague_check LIMIT ?,12";
			List<Map<String, Object>> listMap = this.getFinalDao()
					.getIEntitySQLDAO()
					.findBySQLTOMap(sql, (page - 1) * 12);

			JSONArray array = new JSONArray();
			if (null != listMap && !listMap.isEmpty()) {
				int group = listMap.size() % 4 == 0 ? listMap.size() / 4
						: listMap.size() / 4 + 1;
				if (group == 3) {
					array.add(listMap.subList(0, 4));
					array.add(listMap.subList(4, 8));
					array.add(listMap.subList(8, listMap.size()));
				} else if (group == 2) {
					array.add(listMap.subList(0, 4));
					array.add(listMap.subList(4, listMap.size()));
				} else {
					array.add(listMap);
				}
			}

			int pageCount = Integer.parseInt(total.get("total").toString()) % 12 == 0 ? Integer
					.parseInt(total.get("total").toString()) / 12 : Integer
					.parseInt(total.get("total").toString()) / 12 + 1;

			JSONObject json = new JSONObject();
			json.put("pageCount", pageCount);
			json.put("data", array);

			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(json);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取模糊鉴定图片异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 删除模糊认证(non-Javadoc)
	 * @see com.yiliao.service.VagueService#delVagueById(int)
	 */
	@Override
	public MessageUtil delVagueById(int t_id) {
		try {
			
			String sql = "DELETE FROM t_vague_check WHERE t_id= ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 通过审核(non-Javadoc)
	 * @see com.yiliao.service.VagueService#hasVerified(int)
	 */
	@Override
	public MessageUtil hasVerified(int t_id) {
		try {
			String sql = "SELECT * FROM t_vague_check WHERE t_id= ?";
			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, t_id);
			
			int dataType = Integer.parseInt(map.get("t_data_type").toString());
			
			switch (dataType) {
			case 0: //0.头像图片
				sql = "UPDATE t_user SET t_handImg=? WHERE t_id=? ;";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, map.get("t_img_url").toString(),
						Integer.parseInt(map.get("t_user_id").toString()));
				break;
			case 2: //2.相册图片
				String inseSql = "INSERT INTO t_album (t_user_id, t_title, t_addres_url, t_file_type, t_is_private,  t_money, t_is_del,t_auditing_type) VALUES (? ,?, ?, ?, ?, ?, ?,?);";

				this.getFinalDao()
						.getIEntitySQLDAO()
						.executeSQL(inseSql, Integer.parseInt(map.get("t_user_id").toString()), null,
								map.get("t_img_url").toString(), 0,
								Integer.parseInt(map.get("t_gold").toString())>0?1:0, 
										Integer.parseInt(map.get("t_gold").toString()), 0,Integer.parseInt(map.get("t_gold").toString())>0?0:1);
				break;
			}
			
			mu = new MessageUtil(1, "操作完成!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("通过审核!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
