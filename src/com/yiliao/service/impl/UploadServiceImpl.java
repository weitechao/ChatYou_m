package com.yiliao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yiliao.service.UploadService;
import com.yiliao.util.DateUtils;

/**
 * 图片上传
 * 
 * @author Administrator
 * 
 */
@Service("uploadService")
public class UploadServiceImpl extends ICommServiceImpl implements
		UploadService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yiliao.service.UploadService#uploadFile(int, int,
	 * java.lang.String)
	 */
	@Override
	public void uploadFile(int userId, int fileType, String fileUrl) {
		try {

			switch (fileType) {
			case 1:
				uploadHandImg(userId, fileUrl);
				break;
			case 2:
				uploadCoverImg(userId, fileUrl);
				break;
			case 3:
				uploadPhotoFile(userId, fileUrl);
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传图片异常!{}", fileType, e);
		}
	}

	/**
	 * 上传用户头像
	 * 
	 * @param fileUrl
	 */
	public void uploadHandImg(int userId, String fileUrl) {

		String sql = "UPDATE t_user SET t_handImg=? WHERE t_id=?;";

		this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, fileUrl, userId);
	}

	/**
	 * 上传封面图片
	 * 
	 * @param fileUrl
	 */
	public void uploadCoverImg(int userId, String fileUrl) {
		String sql = "INSERT INTO t_cover_examine (t_img_url, t_user_id, t_first,t_is_examine, t_create_time) VALUES (?, ?,1,1,?);";

		this.getFinalDao()
				.getIEntitySQLDAO()
				.executeSQL(sql, fileUrl, userId,
						DateUtils.format(new Date(), DateUtils.FullDatePattern));
	}

	/**
	 * 上传到相册
	 * 
	 * @param fileUrl
	 */
	public void uploadPhotoFile(int userId, String fileUrl) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO t_album (t_user_id,t_addres_url, t_file_type, t_is_private, t_see_count, t_money, t_is_del) ");
		sb.append(" VALUES (?, ?, ?, ?, 0,0, 0)");
		
		this.getFinalDao().getIEntitySQLDAO().executeSQL(sb.toString(), 
				userId,fileUrl,verificationFileType(fileUrl),1);

	}

	/**
	 * 获取文件类型
	 * 
	 * @return
	 */
	public int verificationFileType(String fileUrl) {
		String imgType = "bmp,jpg,png,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp";

		String suffix = fileUrl.substring(fileUrl.indexOf(".") + 1);

		return imgType.indexOf(suffix) >= 0 ? 0 : 1;
	}

	/**
	 * 获取oss对象信息
	 */
	@Override
	public Map<String, Object> getOssData() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			
			String sql = "SELECT * FROM t_object_storage WHERE t_state= 0";
			
			map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
