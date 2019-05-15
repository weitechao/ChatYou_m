package com.yiliao.service;

import java.util.List;
import java.util.Map;

import com.yiliao.util.MessageUtil;

public interface OSSProjectService {


   /**
	* 获取OSS对象列表
	* @return
	*/
   public List<Map<String,Object>> getOssList();
   
   
   /**
    * 保存或者修改对象存储设置
    * @param t_id
    * @param t_app_id
    * @param t_secret_id
    * @param t_secret_key
    * @param t_bucket
    * @param t_region
    * @param t_state
    * @param t_type
    * @return
    */
   public MessageUtil saveOrUpdateOssSetUp(Integer t_id, String t_app_id,
			String t_secret_id, String t_secret_key, String t_bucket,
			String t_region, int t_state, int t_type,String t_img_url);
   
   /**
    * 根据编号获取详情明细
    * @param t_id
    * @return
    */
   public MessageUtil getOssDataById(int t_id);
   
   /**
    * 删除oss设置
    * @param t_id
    * @return
    */
   public MessageUtil delOssSetUp(int t_id);
   
   /**
    * 修改OSS对象存储状态
    * @param t_id
    * @return
    */
   public MessageUtil updateOssState(int t_id);
}
