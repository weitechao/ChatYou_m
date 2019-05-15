package com.yiliao.service;

import java.util.Map;

public interface UploadService {
	
	
	/**
	 * 获取存储对象明细
	 * @return
	 */
	public Map<String, Object> getOssData();
	/**
	 * 文件上传
	 * @param userId 用户编号
	 * @param fileType 文件类型 1.头像 2.封面 3.相册(包含图片和短视频)
	 * @param fileUrl
	 */
	public void uploadFile(int userId,int fileType,String fileUrl);

}
