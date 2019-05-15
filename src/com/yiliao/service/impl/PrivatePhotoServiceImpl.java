package com.yiliao.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.PrivatePhotoService;
import com.yiliao.util.MessageUtil;

/**
 * 私密照片服务实现
 * 
 * @author Administrator
 * 
 */
@Service("privatePhotoService")
public class PrivatePhotoServiceImpl extends ICommServiceImpl implements
		PrivatePhotoService {

	private MessageUtil mu = null;

	/*
	 * 获取私密相册列表(non-Javadoc)
	 * 
	 * @see com.yiliao.service.PrivatePhotoService#getPrivatePhotoList(int)
	 */
	@Override
	public JSONObject getPrivatePhotoList(int page,int search_name,int fileType) {
		JSONObject json = new JSONObject();
		try {
			
			String cSql = "SELECT COUNT(d.t_id) AS total FROM t_album d LEFT JOIN t_user u ON d.t_user_id = u.t_id WHERE  t_is_del = 0 ";
			
			if(search_name != -1){
				cSql = cSql + " AND d.t_auditing_type = "+ search_name ;
			}
			
			if(fileType >=0 && fileType <=1) {
				cSql = cSql + " AND t_file_type = " + fileType;
			}
			
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			
			String qSql = "SELECT d.t_id,d.t_title,d.t_video_img,d.t_addres_url,d.t_file_type,d.t_money,d.t_auditing_type,d.t_is_private,u.t_nickName,u.t_idcard FROM t_album d LEFT JOIN t_user u ON d.t_user_id = u.t_id WHERE  t_is_del = 0 ";
			
			if(search_name != -1){
				qSql = qSql + " AND d.t_auditing_type = "+ search_name ;
			}
			
			if(fileType >=0 && fileType <=1) {
				qSql = qSql + " AND t_file_type = " + fileType;
			}
			
			qSql = qSql + " ORDER BY d.t_id DESC LIMIT ?,10";
			
			List<Map<String, Object>> dataList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, (page-1)*10);
			
			
			json.put("total", totalMap.get("total"));
			json.put("rows", dataList);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取私密相册列表异常!", e);
		}
		return json;
	}

	/*
	 * 点击禁用(non-Javadoc)
	 * @see com.yiliao.service.PrivatePhotoService#clickSetUpEisable(int)
	 */
	@Override
	public MessageUtil clickSetUpEisable(int t_id) {
		try {
			
			String sql = "DELETE FROM t_album WHERE t_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("点击禁用图片异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/**
	 * 点击通过审核
	 */
	@Override
	public MessageUtil onclickHasVerified(int t_id) {
		try {
			
			String sql = "UPDATE t_album SET t_auditing_type = 1 WHERE t_id=?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("点击通过审核异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	
	
	
	
}
