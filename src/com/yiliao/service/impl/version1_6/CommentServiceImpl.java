package com.yiliao.service.impl.version1_6;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yiliao.service.impl.ICommServiceImpl;
import com.yiliao.service.version1_6.CommentService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.HttpContentUtil;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.SystemConfig;

@Service("commentService")
public class CommentServiceImpl extends ICommServiceImpl implements CommentService {

	@Override
	public Map<String, Object> getCommList(int page) {
		try {
			
			Map<String, Object> totalMap = this.returnMap("SELECT COUNT(t_id) AS total FROM t_comment WHERE t_is_examine = 0 ");
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT u.t_nickName,u.t_idcard,u.t_phone,c.t_id,c.t_comment,d.t_content,DATE_FORMAT(c.t_create_time,'%Y-%m-%d %T') AS t_create_time ");
			sql.append("FROM t_comment c LEFT JOIN t_user u ON c.t_com_user_id = u.t_id ");
			sql.append("LEFT JOIN t_dynamic d ON c.t_dynamic_id = d.t_id ");
			sql.append("WHERE c.t_is_examine = 0 ");
			sql.append("ORDER BY c.t_create_time DESC ");
			sql.append("LIMIT ?,10 ");
			
			List<Map<String, Object>> data = this.returnList(sql.toString(), (page - 1) * 10);
			
			data.forEach(s -> {
				    if(null == s.get("t_nickName")) {
					   s.put("t_nickName", "聊友:"+s.get("t_phone").toString().substring(s.get("t_phone").toString().length()-4));	
					}
					s.remove("t_phone");
			});
			
			return new HashMap<String,Object>(){{
				put("total", totalMap.get("total"));
				put("rows", data);
			}};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 评论通过审核
	 */
	@Override
	public MessageUtil viaExamine(int comId) {
		try {
			
			//设置评论通过审核
			this.executeSql("UPDATE t_comment SET t_is_examine = 1 WHERE t_id = ? ", comId);
			
			//得到当前动态发布人
			Map<String, Object> userData = this.returnMap("SELECT d.t_user_id FROM t_comment c LEFT JOIN t_dynamic d ON c.t_dynamic_id = d.t_id WHERE c.t_id = ? ", comId);
			//写入当前用户有新的评论信息
			List<Map<String, Object>> comCounts = this.returnList("SELECT * FROM t_comment_count WHERE t_user_id = ? ;", userData.get("t_user_id"));
			//新增
			if(comCounts.isEmpty()) {
				 this.executeSql("INSERT INTO t_comment_count (t_user_id, t_mesg_count, t_create_time) VALUES (?,?,?);",
						 userData.get("t_user_id"),1,DateUtils.format(new Date(), DateUtils.FullDatePattern));
			}else { //修改
				 this.executeSql("UPDATE t_comment_count SET   t_mesg_count=t_mesg_count + 1  WHERE t_user_id = ? ;", userData.get("t_user_id"));
			}
			
			//调用APP接口 给用户推送评论记录数
			HttpContentUtil.urlresult(SystemConfig.getValue("noticeUrl")+"?userId="+userData.get("t_user_id"));
			
			return new MessageUtil(1, "评论审核成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 驳回评论
	 */
	@Override
	public MessageUtil rejectComm(int comId) {
		try {
			
			this.executeSql("DELETE FROM t_comment WHERE t_id = ? ", comId);
			
			return new MessageUtil(1, "评论审核成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("驳回评论异常!", e);
		}
		return null;
	}

}
