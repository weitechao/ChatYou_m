package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.GiftService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

/**
 * 礼物服务层
 * @author Administrator
 *
 */
@Service("giftService")
public class GiftServiceImpl extends ICommServiceImpl implements GiftService {

	
	private MessageUtil mu = null;
	
	/*
	 * 列表(non-Javadoc)
	 * @see com.yiliao.service.GiftService#getGiftList(int)
	 */
	@Override
	public JSONObject getGiftList(String condition,int page) {
		JSONObject json = new JSONObject();
		try {
			
			//获取总记录数
			String countSql = "SELECT count(t_gift_id) AS total FROM t_gift";
			//分页获取数据
			String querySql = "SELECT t_gift_id,t_gift_name,t_gift_gif_url,t_gift_still_url,t_gift_gold,t_is_enable FROM t_gift ";
			
			//查询条件
			if(StringUtils.isNotBlank(condition)){
				countSql = countSql + " WHERE t_gift_name LIKE '%"+condition+"%' ";
				querySql = querySql + " WHERE t_gift_name LIKE '%"+condition+"%' ";
			}
			
			querySql = querySql + " LIMIT ?,10";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);
			
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(querySql, (page-1)*10);
			
			//迭代统计该礼物销量
			for(Map<String, Object> m : dataList){
				
				String qSql = " SELECT COUNT(t_id) AS totalCount FROM t_order WHERE t_consume_score = ? AND t_consume_type = 9 ";
				
				Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, m.get("t_gift_id"));
				
				m.put("totalCount", toMap.get("totalCount"));
			}
			
			json.put("total", total.get("total"));
			json.put("rows", dataList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("分页获取礼物列表异常!", e);
		}
		return json;
	}

	/*
	 * 新增或修改(non-Javadoc)
	 * @see com.yiliao.service.GiftService#addOrUpdateGift(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public MessageUtil addOrUpdateGift(Integer t_gift_id, String t_gift_name,
			String t_gift_gif_url, String t_gift_still_url, int t_gift_gold,
			String t_is_enable) {
		try {
			//新增
			if(null == t_gift_id || 0 == t_gift_id){
				String sql = "INSERT INTO t_gift (t_gift_name, t_gift_gif_url, t_gift_still_url, t_gift_gold, t_is_enable, t_create_time) VALUES (?, ?, ?, ?, ?, ?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_gift_name,t_gift_gif_url,t_gift_still_url,t_gift_gold,t_is_enable,DateUtils.format(new Date(), DateUtils.FullDatePattern));
			//修改
			}else{
				String sql  = "UPDATE t_gift SET  t_gift_name=?, t_gift_gif_url=?, t_gift_still_url=?, t_gift_gold=?, t_is_enable=?, t_create_time=? WHERE t_gift_id=?;";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_gift_name,t_gift_gif_url,t_gift_still_url,t_gift_gold,t_is_enable,DateUtils.format(new Date(), DateUtils.FullDatePattern),t_gift_id);
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增或修改异常!",e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/**
	 * 礼物启用或者停用
	 */
	@Override
	public MessageUtil isEnable(int t_gift_id) {
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE t_gift SET ") ;
			sql.append("t_is_enable = CASE t_is_enable  ") ;
			sql.append("  WHEN 0 THEN 1  ") ;
			sql.append("  WHEN 1 THEN 0 ") ;
			sql.append("END ") ;
			sql.append("WHERE t_gift_id = ? ") ;
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql.toString(), t_gift_id);
			
			mu = new MessageUtil(1, "更新成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("礼物启用或者停用异常!",e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 删除礼物(non-Javadoc)
	 * @see com.yiliao.service.GiftService#delGiftById(int)
	 */
	@Override
	public MessageUtil delGiftById(int t_gift_id) {
		try {
			
			String delSql = "DELETE FROM t_gift WHERE t_gift_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, t_gift_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除礼物异常!",e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public JSONObject getGiveDetail(int giftId, int page) {
		try {
			
			String cSql = " SELECT COUNT(t_id) AS totalCount FROM t_order WHERE t_consume_score = ? AND t_consume_type = 9 ";
			
			Map<String, Object> toMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql, giftId);
			
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT ");
			sb.append(" u.t_nickName AS nick ,u.t_phone AS uphone,u1.t_nickName AS cNick,u1.t_phone AS cPhone,o.t_amount, ");
			sb.append(" DATE_FORMAT(o.t_create_time,'%Y-%m-%d %T') AS t_create_time ");
			sb.append("FROM ");
			sb.append(" t_order o LEFT JOIN t_user u ON o.t_consume = u.t_id ");
			sb.append(" LEFT JOIN t_user u1  ON o.t_cover_consume = u1.t_id ");
			sb.append("WHERE ");
			sb.append(" t_consume_score = ? ");
			sb.append(" AND t_consume_type = 9 ");
			sb.append(" LIMIT ?, 10;");
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString(), giftId,(page-1)*10);
			
			for(Map<String, Object> m : dataList){
				
				if(null == m.get("nick")){
					m.put("nick", "聊友:"+m.get("uphone").toString().substring(m.get("uphone").toString().length()-4));
				}
				
				if(null == m.get("cNick")){
					m.put("cNick", "聊友:"+m.get("cPhone").toString().substring(m.get("cPhone").toString().length()-4));
				}
			}
			
			JSONObject json = new JSONObject();
			
			json.put("total", toMap.get("totalCount"));
			json.put("rows", dataList);
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
