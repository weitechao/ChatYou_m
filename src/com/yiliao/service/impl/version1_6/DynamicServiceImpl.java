package com.yiliao.service.impl.version1_6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.service.impl.ICommServiceImpl;
import com.yiliao.service.version1_6.DynamicService;
import com.yiliao.util.MessageUtil;
/**
 * 动态Service实现
 * @author Administrator
 *
 */
@Service("dynamciService")
public class DynamicServiceImpl extends ICommServiceImpl implements DynamicService {

	@Override
	public Map<String, Object> getDynamicList(String condition, String beginTime, String endTime, int page) {
		try {
			StringBuffer body = new StringBuffer();
			body.append("SELECT ");
			body.append("u.t_nickName,u.t_idcard,u.t_phone,d.t_id,");
			body.append("d.t_content,d.t_is_del,d.t_auditing_type,");
			body.append("DATE_FORMAT(d.t_create_time,'%Y-%m-%d %T') AS t_create_time ");
			body.append("FROM t_dynamic d ");
			body.append("LEFT JOIN t_user u ON d.t_user_id = u.t_id ");
			body.append("WHERE 1=1 AND d.t_auditing_type <> 2 AND d.t_is_del = 0 ");
			if(StringUtils.isNotBlank(condition))
			  body.append("AND (u.t_phone LIKE '%").append(condition).append("%' OR u.t_nickName LIKE '%").append(condition).append("%') ");
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime))
			  body.append("AND  d.t_create_time BETWEEN '").append(beginTime).append("' AND '").append(endTime).append("'");
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT COUNT(aa.t_id) AS total FROM ("+body+") aa");
			
			List<Map<String,Object>> sqltoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(body+" ORDER BY d.t_create_time DESC LIMIT ?,10", (page -1)*10 );
			
			sqltoMap.forEach(s ->{
				if(null == s.get("t_nickName")) {
				   s.put("t_nickName", "聊友:"+s.get("t_phone").toString().substring(s.get("t_phone").toString().length()-4));	
				}
				s.remove("t_phone");
				//获取有多少点赞
				Map<String, Object> praise = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT COUNT(t_id) AS total FROM t_praise WHERE t_dynamic_id = ?", s.get("t_id"));
				s.put("praiseCount", praise.get("total"));
				
				//获取有多少评论
				Map<String, Object> commentCount = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT COUNT(t_id) AS total FROM t_comment WHERE t_dynamic_id = ? ", s.get("t_id"));
				s.put("commentCount", commentCount.get("total"));
			});
			
			return new HashMap<String,Object>(){{
				put("total", total.get("total"));
				put("rows", sqltoMap);
			}};
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取动态列表异常!", e);
		}
		return null;
	}

	@Override
	public MessageUtil getDynamicFile(int dynamicId) {
		try {
			
			//获取该动态是否有图片或者视频文件
			List<Map<String, Object>> dynamicFiles = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap("SELECT t_id,t_file_url,t_file_type,t_gold FROM t_dynamic_file WHERE t_dynamic_id = ? AND t_is_del = 0  ", dynamicId);
			
			MessageUtil mu = new MessageUtil();
			mu.setM_istatus(1);
			mu.setM_object(dynamicFiles);
			return mu;
		} catch (Exception e) {
			e.printStackTrace();
			return new MessageUtil(0, "程序异常!");
		}
	}

	/**
	 * 设置文件异常
	 */
	@Override
	public MessageUtil setFileAbnormal(int fileId) {
		try {
			//获取该记录
			Map<String, Object> returnMap = this.returnMap("SELECT f.t_is_private,d.t_auditing_type FROM t_dynamic_file f LEFT JOIN t_dynamic d ON f.t_dynamic_id = d.t_id WHERE f.t_id = ? ", fileId);
			//如果文件是公开文件 那么执行删除
			if(Integer.parseInt(returnMap.get("t_is_private").toString()) == 0) {
				this.executeSql("DELETE FROM t_dynamic_file WHERE t_id = ? ", fileId);
			//动态未审核或者审核失败 那么直接删除文件
			}else if(Integer.parseInt(returnMap.get("t_auditing_type").toString()) != 1 ) {
				this.executeSql("DELETE FROM t_dynamic_file WHERE t_id = ? ", fileId);
			//修改文件未逻辑删除
			}else {
				this.executeSql("UPDATE t_dynamic_file SET t_is_del = 1 WHERE t_id = ? ", fileId);
			}
			
			return new MessageUtil(1, "删除文件成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 审核动态
	 */
	@Override
	public MessageUtil examineDynamic(int dynamicId, int type) {
		try {
			//动态通过审核
			if(type == 1) {
				this.executeSql("UPDATE t_dynamic SET t_auditing_type = 1 WHERE t_id = ? ;", dynamicId);
			}else { //动态未通过审核
				this.executeSql("DELETE FROM t_dynamic WHERE t_id = ? ;", dynamicId);
			}
			return new MessageUtil(1, "动态已审核!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil delDynamic(int dynamicId) {
		try {
			
			//获取该记录
			List<Map<String,Object>> returnList = this.returnList("SELECT f.* FROM t_dynamic_file f LEFT JOIN t_dynamic d ON f.t_dynamic_id = d.t_id WHERE d.t_id = ? AND f.t_is_private = 0  ", dynamicId);
			//动态下的文件没有私密文件
			if(returnList.isEmpty()) {
				//执行删除文件
				this.executeSql("DELETE FROM t_dynamic_file WHERE t_dynamic_id = ? ", dynamicId);
				//执行删除动态
				this.executeSql("DELETE FROM t_dynamic WHERE t_id = ? ;", dynamicId);
				
			//动态下存在私密文件
			}else { 
				//动态文件执行逻辑删除
				this.executeSql("UPDATE t_dynamic_file SET t_is_del = 1 WHERE t_dynamic_id = ? ;", dynamicId);
				//动态执行逻辑删除
				this.executeSql("UPDATE t_dynamic SET t_auditing_type = 2,t_is_del = 1 WHERE t_id = ? ;", dynamicId);
			}
			return new MessageUtil(1, "动态已删除!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取已通过审核的评论列表
	 */
	@Override
	public Map<String, Object> getExamineComment(int dynamicId,String beginTime,String endTime,int page) {
		try {
			
			StringBuffer body = new StringBuffer();
			body.append("SELECT c.t_id,c.t_comment,DATE_FORMAT(c.t_create_time,'%Y-%m-%d %T') AS t_create_time ,u.t_nickName,u.t_phone ");
			body.append("FROM t_comment c LEFT JOIN t_user u ON c.t_com_user_id = u.t_id  WHERE c.t_is_examine  = 1 AND c.t_dynamic_id = ? ");
			if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime))
			body.append(" AND  c.t_create_time BETWEEN '").append(beginTime).append("' AND '").append(endTime).append("'");
		 
			Map<String, Object> total = this.returnMap("SELECT COUNT(t_id) AS total FROM ("+body+") aa",dynamicId);
			
			List<Map<String, Object>> commData = this.returnList(body+" ORDER BY c.t_create_time DESC LIMIT ?,10", dynamicId,(page-1)*10);
			
			commData.forEach(s ->{
				if(!StringUtils.isNotBlank(s.get("t_nickName").toString())) {
					   s.put("t_nickName", "聊友:"+s.get("t_phone").toString().substring(s.get("t_phone").toString().length()-4));	
					}
					s.remove("t_phone");
			});
			
			return new HashMap<String,Object>(){{
				put("total", total.get("total"));
				put("rows", commData);
			}};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除评论
	 */
	@Override
	public MessageUtil delComment(int comId) {
		try {
			
			this.executeSql("DELETE FROM t_comment WHERE t_id = ? ", comId);
			
			return new MessageUtil(1, "评论已删除！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
