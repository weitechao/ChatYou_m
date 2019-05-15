package com.yiliao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yiliao.service.AdminService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.Md5Util;
import com.yiliao.util.MessageUtil;
/**
 * 管理员服务层明细
 * @author Administrator
 *
 */
@Service("adminService")
public class AdminServiceImpl extends ICommServiceImpl implements AdminService {

	@Override
	public JSONObject getAdminList(int page) {
		JSONObject json = new JSONObject();
		try {
			
			String countSql = "SELECT count(t_id) AS total FROM t_admin ";
			
			String sql = "SELECT a.t_id,a.t_user_name,a.t_pass_word,r.t_role_name,a.t_is_disable,DATE_FORMAT(a.t_create_time,'%Y-%m-%d %T') AS t_create_time FROM t_admin a LEFT JOIN t_role r ON a.t_role_id=r.t_id  limit ?,10";
			
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, (page-1)*10);
			
			json.put("total", total.get("total"));
			json.put("rows", dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/*
	 * 删除用户数据(non-Javadoc)
	 * @see com.yiliao.service.AdminService#delAdmin(int)
	 */
	@Override
	public MessageUtil delAdmin(int t_id) {
		MessageUtil mu = new MessageUtil();
		try {
			String sql = "DELETE FROM t_admin WHERE t_id=?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id);
			
			mu = new MessageUtil(1,"操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	/*
	 * 新增或者修改(non-Javadoc)
	 * @see com.yiliao.service.AdminService#saveAdmin(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public MessageUtil saveAdmin(Integer t_id, String t_user_name,
			String t_pass_word, String t_is_disable,int t_role_id) {
		
		MessageUtil mu = null ;
		try {
			//新增
			if(null ==  t_id || 0 == t_id){
				
				String sql = " INSERT INTO t_admin (t_user_name, t_pass_word, t_is_disable,t_role_id, t_create_time) VALUES (?, ?, ?, ?,?);";
				this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_user_name,
						Md5Util.encodeByMD5(t_pass_word),t_is_disable,t_role_id,DateUtils.format(new Date(), DateUtils.FullDatePattern));
				
			}else{ //修改
				String upate = " UPDATE t_admin SET t_user_name=?, t_pass_word=?, t_is_disable=? , t_role_id=? WHERE t_id=?; ";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(upate, t_user_name,Md5Util.encodeByMD5(t_pass_word),t_is_disable,t_role_id,t_id);
			}
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil loadNotice(HttpServletRequest request) {
		try {
			
			int roleId =Integer.parseInt(request.getSession().getAttribute("roleId").toString());
			
			//根据权限Id 查询出菜单
			
			List<Map<String, Object>> menuList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT m.t_menu_name FROM t_menu m LEFT JOIN t_authority a ON a.t_menu_id = m.t_id WHERE a.t_role_id = ? ", roleId);
			
			StringBuffer notice = new StringBuffer();
			notice.append("<li>");
            notice.append("<div class=\"notification_header\">");
            notice.append("<h3>您有totalNotice个新的通知</h3>");
            notice.append("</div>");
            notice.append("</li>");
            
            int total = 0;
            
            
            for(Map<String, Object> m : menuList) {
            	String qSql = "";
				//获取待处理事件
				//获取是否有需要封面审核的记录；
				if(m.get("t_menu_name").toString().indexOf("封面")>-1) {
					qSql = "SELECT COUNT(t_id) AS total FROM t_cover_examine WHERE t_is_examine = 0 ";
					Map<String, Object> coverImg = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					
					if(!coverImg.isEmpty()  && !"0".equals(coverImg.get("total").toString()) ) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpCoverList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/cover_img.png\" alt=\"\"  width=\"80%\">");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(coverImg.get("total")).append("个封面需审核</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						
						total = total + Integer.parseInt(coverImg.get("total").toString());
					}
				}
				
				if(m.get("t_menu_name").toString().indexOf("相册")>-1) {
					//获取相册审核
					qSql=" SELECT COUNT(t_id) AS total FROM t_album WHERE t_auditing_type = 0 ;";
					Map<String, Object> albumMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					
					if(!albumMap.isEmpty() && !"0".equals(albumMap.get("total").toString())) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpPrivatePhotoList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/album.png\" alt=\"\" width=\"80%\">");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(albumMap.get("total")).append("个照片或视频需审核</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						total = total + Integer.parseInt(albumMap.get("total").toString());
					}
				}
				if(m.get("t_menu_name").toString().indexOf("实名")>-1) {
					//实名认证请求
					qSql = " SELECT COUNT(t_id) AS total FROM t_certification WHERE t_certification_type = 0  ";
					Map<String, Object> verifyMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					
					if(!verifyMap.isEmpty()  && !"0".equals(verifyMap.get("total").toString()) ) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpidCardList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/verify.png\" alt=\"\" width=\"80%\" >");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(verifyMap.get("total")).append("个主播申请需审核</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						total = total + Integer.parseInt(verifyMap.get("total").toString());
					}
				}
				//提现
				if(m.get("t_menu_name").toString().indexOf("提现记录")>-1) {
					qSql = " SELECT COUNT(t_id) AS total FROM t_put_forward WHERE t_order_state  = 0 " ;
					Map<String, Object> extractMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					
					if(!extractMap.isEmpty()  && !"0".equals(extractMap.get("total").toString())) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpPutForwardList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/extract.png\" alt=\"\" width=\"80%\" >");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(extractMap.get("total")).append("个提现申请需审核</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						total = total + Integer.parseInt(extractMap.get("total").toString());
					}
				}
				
				//意见反馈
				if(m.get("t_menu_name").toString().indexOf("意见反馈")>-1) {
					qSql = " SELECT COUNT(t_id) AS total FROM t_feedback WHERE t_is_handle = 0 ;";
					Map<String, Object> feedbackMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					
					if(!feedbackMap.isEmpty() && !"0".equals(feedbackMap.get("total").toString())) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpFeedbackList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/feedback.png\" alt=\"\" width=\"80%\">");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(feedbackMap.get("total")).append("个意见反馈待处理</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						
						total = total + Integer.parseInt(feedbackMap.get("total").toString());
					}
				}
				
				//举报
				if(m.get("t_menu_name").toString().indexOf("举报")>-1) {
					qSql = " SELECT COUNT(t_id) AS total FROM t_report WHERE t_is_handle = 0 ;";
					Map<String, Object> repoMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					
					if(!repoMap.isEmpty() && !repoMap.get("total").toString().equals("0")) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpReportList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/report.png\" alt=\"\" width=\"80%\">");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(repoMap.get("total")).append("举报事件待处理</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						
						total = total + Integer.parseInt(repoMap.get("total").toString());
					}
				}
				
				//公会
				if(m.get("t_menu_name").toString().indexOf("公会")>-1) {
					qSql = " SELECT COUNT(t_id) AS total FROM t_guild WHERE t_examine = 0 ;";
					Map<String, Object> guildMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					if(!guildMap.isEmpty() && !"0".equals(guildMap.get("total").toString())) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpGuildList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/guild.png\" alt=\"\" width=\"80%\">");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(guildMap.get("total")).append("个公会申请待处理</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						
						total = total + Integer.parseInt(guildMap.get("total").toString());
					}
				}
				//cps
				if(m.get("t_menu_name").toString().indexOf("CPS")>-1) {
					qSql = " SELECT COUNT(t_id) AS total FROM t_cps WHERE t_audit_status = 0 ;";
					Map<String, Object> cpsMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					
					if(!cpsMap.isEmpty() && !"0".equals(cpsMap.get("total").toString())) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpCPSList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/CPS.png\" alt=\"\" width=\"80%\">");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(cpsMap.get("total")).append("个CPS申请待处理</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						
						total = total + Integer.parseInt(cpsMap.get("total").toString());
					}
				}
				//动态
				if(m.get("t_menu_name").toString().indexOf("动态列表")>-1) {
					qSql = " SELECT COUNT(t_id) AS total FROM t_dynamic WHERE t_auditing_type = 0 ;";
					Map<String, Object> cpsMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql);
					
					if(!cpsMap.isEmpty() && !"0".equals(cpsMap.get("total").toString())) {
						notice.append("<li>");
						notice.append("   <a href=\"jumpDynamicList.htm\">");
						notice.append("     <div class=\"user_img\">");
						notice.append("       	<img src=\"../images/dynamic.png\" alt=\"\" width=\"80%\">");
						notice.append("     </div>");
						notice.append("     <div class=\"notification_desc\">");
						notice.append("     	<p>您有").append(cpsMap.get("total")).append("条动态需审核</p>");
						notice.append("     </div>");
						notice.append("     <div class=\"clearfix\"></div>");
						notice.append("   </a>");
						notice.append("</li>");
						
						total = total + Integer.parseInt(cpsMap.get("total").toString());
					}
				}
            }
            
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("totalNotice", total);
            map.put("detail", notice.toString().replaceAll("totalNotice", total+""));
            
            MessageUtil mu = new MessageUtil();
            mu.setM_istatus(1);
            mu.setM_object(map);
            
           return mu ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
