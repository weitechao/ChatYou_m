package com.yiliao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.BannerService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;

@Service("bannerService")
public class BannerServiceImpl extends ICommServiceImpl implements
		BannerService {

	private MessageUtil mu = null;
	
	@Override
	public MessageUtil getBannerList(int page) {
		try {
			
			String total = "SELECT count(t_id) AS total FROM t_banner ";
			
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(total);
			
			String sql = "SELECT t_id,t_img_url,t_link_url,t_is_enable,DATE_FORMAT(t_create_time,'%Y-%m-%d %T') AS t_create_time,t_type FROM t_banner LIMIT ?,5";
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, (page-1)*5);
			
			JSONObject json = new JSONObject();
			
			json.put("total", totalMap.get("total"));
			json.put("rows", dataList);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(json);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取banner列表异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 点击启用或者禁用(non-Javadoc)
	 * @see com.yiliao.service.BannerService#bannerEnableOrDisable(int)
	 */
	@Override
	public MessageUtil bannerEnableOrDisable(int t_id) {
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE t_banner SET ") ;
			sql.append("t_is_enable = CASE t_is_enable  ") ;
			sql.append("  WHEN 0 THEN 1  ") ;
			sql.append("  WHEN 1 THEN 0 ") ;
			sql.append("END ") ;
			sql.append("WHERE t_id = ? ") ;
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql.toString(), t_id);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("启用或者禁用Banner!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 删除数据(non-Javadoc)
	 * @see com.yiliao.service.BannerService#delBannerById(int)
	 */
	@Override
	public MessageUtil delBannerById(int t_id) {
		try {
			
			String delSql = "DELETE FROM t_banner WHERE t_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(delSql, t_id);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除Banner!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 添加或者修改banner图(non-Javadoc)
	 * @see com.yiliao.service.BannerService#addOrUpdateBanner(java.lang.Integer, java.lang.String, java.lang.String, int)
	 */
	@Override
	public MessageUtil addOrUpdateBanner(Integer t_id, String t_img_url,
			String t_link_url, int t_is_enable,int t_type) {
		try {
			//新增
			if(null == t_id || 0 == t_id){
			
				String sql = "INSERT INTO t_banner (t_img_url, t_link_url, t_is_enable,t_type, t_create_time) VALUES (?, ?, ?, ?, ?);";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_img_url,t_link_url,t_is_enable,t_type,DateUtils.format(new Date(), DateUtils.FullDatePattern));
		    //修改
			}else{
				
				String upSql = "UPDATE t_banner set t_img_url=? , t_link_url=?, t_is_enable=?, t_create_time=?,t_type = ?  WHERE t_id=?;";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql,t_img_url,t_link_url,t_is_enable,DateUtils.format(new Date(), DateUtils.FullDatePattern),t_type,t_id);
				
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加或者修改banner!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

}
