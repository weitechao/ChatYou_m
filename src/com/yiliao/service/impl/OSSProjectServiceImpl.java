package com.yiliao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yiliao.service.OSSProjectService;
import com.yiliao.util.MessageUtil;

/**
 * 对象存储服务层实现类
 * 
 * @author Administrator
 * 
 */
@Service("oSSProjectService")
public class OSSProjectServiceImpl extends ICommServiceImpl implements
		OSSProjectService {

	
	private MessageUtil mu = null;
	/*
	 * 获取oss对象列表(non-Javadoc)
	 * 
	 * @see com.yiliao.service.OSSProjectService#jumpOssList()
	 */
	@Override
	public List<Map<String, Object>> getOssList() {

		List<Map<String, Object>> dataList = null;

		try {

			String sql = "SELECT * FROM t_object_storage";

			dataList = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap(sql);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取OSS异常", e);
		}

		return dataList;
	}

	/*
	 * 保存或者修改对象存储设置(non-Javadoc)
	 * 
	 * @see
	 * com.yiliao.service.OSSProjectService#saveOrUpdateOssSetUp(java.lang.Integer
	 * , java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, int, int)
	 */
	@Override
	public MessageUtil saveOrUpdateOssSetUp(Integer t_id, String t_app_id,
			String t_secret_id, String t_secret_key, String t_bucket,
			String t_region, int t_state, int t_type,String t_img_url) {
		try {
			
			//新增
			if(null  == t_id || 0 == t_id){
				
				String sql = "INSERT INTO t_object_storage (t_app_id, t_secret_id, t_secret_key, t_bucket, t_region, t_state, t_type,t_img_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_app_id,t_secret_id,t_secret_key,t_bucket,t_region,t_state,t_type,t_img_url);
				
			}else{ //修改
				String sql = "UPDATE t_object_storage SET  t_app_id=?, t_secret_id=?, t_secret_key=?, t_bucket=?, t_region=?, t_state=?, t_type=?, t_img_url =? WHERE t_id=?;";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_app_id,t_secret_id,t_secret_key,t_bucket,t_region,t_state,t_type,t_img_url,t_id);
				
			}
			mu = new MessageUtil(1,"操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存或者修改OSS存储异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/*
	 * 获取OSS存储明细(non-Javadoc)
	 * @see com.yiliao.service.OSSProjectService#getOssDataById(int)
	 */
	@Override
	public MessageUtil getOssDataById(int t_id) {
		try {
			
			String sql = " SELECT * FROM t_object_storage WHERE t_id = ?";
			
			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, t_id);
			
			mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取明细异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/*
	 * 删除OSS对象(non-Javadoc)
	 * @see com.yiliao.service.OSSProjectService#delOssSetUp(int)
	 */
	@Override
	public MessageUtil delOssSetUp(int t_id) {
		try {
			String sql = "DELETE FROM t_object_storage WHERE t_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除OSS对象异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

	/**
	 * 修改OSS对象存储状态
	 */
	@Override
	public MessageUtil updateOssState(int t_id) {
		try {
			
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE t_object_storage SET ") ;
			sql.append("t_state = CASE t_state  ") ;
			sql.append("  WHEN 0 THEN 1  ") ;
			sql.append("  WHEN 1 THEN 0 ") ;
			sql.append("END ") ;
			sql.append("WHERE t_id = ? ") ;
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql.toString(),t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改OSS对象状态异常!", e);
			mu = new MessageUtil(0,"程序异常!");
		}
		return mu;
	}

}
