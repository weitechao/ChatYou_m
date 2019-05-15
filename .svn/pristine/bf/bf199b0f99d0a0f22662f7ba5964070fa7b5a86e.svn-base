package com.yiliao.service.impl;
//package com.yiliao.service.impl.backstage;
//
//import java.net.URLDecoder;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.UUID;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
//import org.springframework.stereotype.Service;
//
//import com.yiliao.dao.core.FinalDao;
//import com.yiliao.service.backstage.IWechatService;
//import com.yiliao.util.JSON;
//import com.yiliao.util.MessageUtil;
//import com.yiliao.util.PageInfo;
//import com.yiliao.util.PushThreadUtil;
//import com.yiliao.util.SystemConfig;
//
///**
// * 微信业务
// * 
// * @author zhanghao
// * @time 2015-10-19
// */
//@Service("WechatServiceImpl")
//public class WechatServiceImpl extends HibernateDaoSupport implements IWechatService {
//	private String webPath = SystemConfig.getValue("webPath");
//	private String urlPath = SystemConfig.getValue("urlPath");
//
//	@Autowired
////	@Qualifier("finalDao")
//	private FinalDao finalDao;
//
//
//	public String m_strResult="";
//	
//	private MessageUtil mu=new MessageUtil();
//
//
//	protected Logger logger = LoggerFactory.getLogger(getClass());
//
//	// setter/gette
//	public FinalDao getFinalDao() {
//		return finalDao;
//	}
//
//	public void setFinalDao(FinalDao finalDao) {
//		this.finalDao = finalDao;
//	}
//	/**
//	 * 添加微信栏目
//	 */
//	@Override
//	public String addWechatColumnName(String vpTitle,int vpType,String kindId){
//		
//		return m_strResult;
//	}
//	
//	/**
//	 * 教师信息展示
//	 * @param KNID
//	 * @return
//	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public String showTeachers(String kindId,int piPageSize, int piPageNo, String pfixpar, String pstrOrderField, String pstrOrderType){
//		//查询用户信息总条数
//		String sqlcount = "select count(*) count from wx_teacher where kindid = ?";
//		List<Map<String,Object>> M_Listcount=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sqlcount,kindId);
//		int messCount = 0;
//		if(M_Listcount.size()>0&&M_Listcount!=null){
//			
//			String messageCount = M_Listcount.get(0).get("count").toString();
//			messCount = Integer.parseInt(messageCount);
//		}
//		if(messCount == 0){
//			messCount = 1;
//		}
//		//三目运算符计算总页数
//		int a = messCount/piPageSize;
//		int pageCount = messCount%piPageSize==0?a:a+1;
//		
//		//判断页码是否超出范围
//		if(piPageNo<1){
//			piPageNo = 1;
//		}else if(piPageNo>pageCount){
//			piPageNo = pageCount;
//		}
//		//计算显示页面的起始下标
//		int beginIndex = (piPageNo-1)*piPageSize;
//		
//		StringBuilder sql = new StringBuilder();
//	    sql.append("SELECT * from (");
//	    sql.append("SELECT teacherid TEACHERID,teacherName TEACHERNAME,teacherPost TEACHERPOST");
//	    sql.append(",S.description DESCRIPTION");
//	    sql.append(" FROM wx_teacher S WHERE S.KINDID=? ORDER BY CREATEDATE DESC");
//	    sql.append(" ) sm limit ?,?");
//		
//		List<Map<String,Object>> M_ListMess=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql.toString(),kindId,beginIndex,piPageSize);
//		
//		if(M_ListMess.size()>0 && M_ListMess != null){
//			for (Map<String, Object> map : M_ListMess) {
//				String content = map.get("DESCRIPTION").toString();
//				content = content.replaceAll("(<(\\w)+ \\/>|<(\\/)?(\\w)+>|&nbsp;)","");
//				if(content.length() > 32){
//					content = content.substring(0, 32)+"...";
//				}
//				map.put("DESCRIPTION",content);
//			}
//		}
//		
//		
//		
//		PageInfo pageInfo=new PageInfo();
//		pageInfo.setList(M_ListMess);
//		pageInfo.setPagenum(piPageNo);
//		pageInfo.setRowsCount(messCount);
//		
//		pageInfo.setOperateCaption("编辑,删除");
//		pageInfo.setOperateName("UpWechatNews,DelWechatNews");
//		pageInfo.setOperatePara("TEACHERID,TEACHERID");
//		pageInfo.setShowFun("编辑,删除");
//	
//		
//		try {
//			m_strResult =JSON.addExtraAttr(pageInfo.getList(), JSON.toJosn(pageInfo));
//		}  catch (Exception e) {
//			e.printStackTrace();
//		}
//	 
//	 return m_strResult ;
//	}
//	
//	/**
//	 * @说明  TODO(添加教师)
//	 */
//	@Override
//	public String teacherAdd(String teacherId, String newsTitle,String newsConTent, String photoName, String photoDesc,
//			String DelUrl, String kindId) {
//		//String wechatId = getWechatId(3,kindId);
//		
//		MessageUtil mu=new MessageUtil();
//		
//		try {
//			newsTitle=URLDecoder.decode(newsTitle,"UTF-8");
//			newsConTent=URLDecoder.decode(newsConTent,"UTF-8");
//			 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//实现图片数据插入
//		String[] pnames=photoName.split("@");String PhotoUlr="";
//		String[] pdescs=photoDesc.split("@");String PhotoDes="";
//	    String[] DELFILE=new String[]{};
//	    
//		if(DelUrl.trim().length()>0){
//			DELFILE=DelUrl.trim().split("@");
//		}
//		for (int i = 0; i < pnames.length; i++) {
//			
//			boolean bool=true;
//			
//			for (int j = 0; j < DELFILE.length; j++) {
//				
//				if((Integer.valueOf(i).toString()).equals(DELFILE[j])){
//					
//					bool=false;
//					break;
//				}
//			}
//			
//			if(bool){
//				PhotoUlr+=pnames[i]+"@";
//				PhotoDes+=pdescs[i]+"@";
//			}
//		}
//		
//		String[] PhotoUlrS=PhotoUlr.split("@");
//		String[] PhotoDesS=PhotoDes.split("@");
//		
//		String year = getTimeUrl().get(0).get("YEAR").toString();
//		String month = getTimeUrl().get(0).get("MONTH").toString();
//		String date = getTimeUrl().get(0).get("DATE").toString();
//		String longTime = getTimeUrl().get(0).get("TIME").toString();
//		Random rd = new Random();
//		int number = rd.nextInt(1000);
//		
//		teacherId=UUID.randomUUID().toString().replace("-","").toUpperCase();
//		String WEBLINK = "/KINDWXFILE/"+kindId+"/CreateHtml/"+year+"/"+month+"/"+date+"/"+longTime+number+".html";
//		if(PhotoUlrS.length>0){
//			
//			String SQL="INSERT INTO wx_teacher(teacherid,KINDID,createdate,fileurl,teachername,teacherpost,teacherPhoto,description)"
//					+ "VALUES(?,?,?,?,?,?,?,?)";
//			int ex = 0;
//			for(int y=0;y<PhotoUlrS.length;y++){
//				
//				ex = this.getFinalDao().getIEntitySQLDAO().executeSQL(SQL,teacherId,kindId,new Date(),WEBLINK,newsTitle,PhotoDesS[y],PhotoUlrS[y],newsConTent);
//			}
//			if(ex  != 1){
//				return "";
//			}
//		}
//		try {
//			mu.setM_istatus(1);
//			mu.setM_object(teacherId);
//			
//			mu.setM_strMessage("添加信息成功！");
//			
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		return teacherId;
//	}
//	
//	/**
//	 * @说明  TODO(删除教师)
//	 */
//	@Override
//	public String delTeacher(String teacherId) {
//
//		MessageUtil mu=new MessageUtil();
//		
//		String SQL="DELETE FROM wx_teacher WHERE teacherid=?";
//		
//		int ex = this.getFinalDao().getIEntitySQLDAO().executeSQL(SQL, teacherId);
//		
//		if(ex == 1){
//			mu.setM_istatus(1);
//			mu.setM_strMessage("删除教师信息成功！");
//		}else{
//			mu.setM_istatus(0);
//			mu.setM_strMessage("删除教师信息失败！");
//			
//		}
//		try {
//			
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//			return this.m_strResult;
//	}
//	/**
//	 * 查询单个教师信息（编辑时展示）
//	 * @param WEID
//	 * @return
//	 */
//	public String findTeaById(String TEACHERID) {
//		MessageUtil mu=new MessageUtil();
//		List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//		List<Map<String,Object>> M_ListPhoto=new ArrayList<Map<String,Object>>();
//		Map<String,Object> M_Map=new HashMap<String, Object>();
//		
//		String SQL="SELECT * FROM wx_teacher WHERE teacherid=?";
//		String SQLphoto="SELECT teacherPhoto,teacherPost FROM wx_teacher WHERE teacherid=?";
//		
//		M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(SQL, TEACHERID);
//		M_ListPhoto=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(SQLphoto, TEACHERID);
//		if(M_ListMap.size()>0){
//			
//			mu.setM_istatus(1);
//			M_Map.put("WEBPATH", webPath);
//			M_Map.put("INFO", M_ListMap.get(0));
//			M_Map.put("FILES", M_ListPhoto);
//			mu.setM_object(M_Map);
//		}else{
//			mu.setM_istatus(0);
//			mu.setM_strMessage("获取数据失败");
//		}
//		
//		try {
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return this.m_strResult;
//	}
//	
//	/**
//	 * 查询微信简介信息
//	 * @param KNID
//	 * @return
//	 */
//	public String findWechatBrief(String WECHATID){
//		
//		MessageUtil mu=new MessageUtil();
//		
//		String SQL="SELECT * FROM wx_kindnewmsg WHERE wechatid=?";
//		
//		List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//		 Map<String,Object> M_Map=new HashMap<String, Object>();
//		
//		M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(SQL, WECHATID);
//		if(M_ListMap.size()>0){
//			
//			mu.setM_istatus(1);
//			
//			M_Map.put("INFO", M_ListMap.get(0));
//			
//			mu.setM_object(M_Map);
//		}else{
//        	mu.setM_istatus(1);
//			
//			M_Map.put("INFO", null);
//			
//			mu.setM_object(M_Map);
//		}
//		
//		try {
//		
//			
//			
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//			return this.m_strResult;
//		
//	}
//	
//	/**
//	 * 列表下删除某个页面
//	 * @param KNID
//	 * @return
//	 */
//	public String DelWechatNews(String LEVELSID){
//		
//		MessageUtil mu=new MessageUtil();
//		
//		String SQLPHOTO="DELETE FROM wx_newphoto WHERE levelsid=?";
//		
//		this.getFinalDao().getIEntitySQLDAO().executeSQL(SQLPHOTO, LEVELSID);
//		
//		String SQL="DELETE FROM wx_kindnewmsg WHERE uuid=?";
//		
//		int ex = this.getFinalDao().getIEntitySQLDAO().executeSQL(SQL, LEVELSID);
//		if(ex == 1){
//			mu.setM_istatus(1);
//			mu.setM_strMessage("删除信息成功！");
//		}else{
//			mu.setM_istatus(1);
//			mu.setM_strMessage("删除信息失败！");
//		}
//		
//		try {
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//			return this.m_strResult;
//		
//	}
//	
//
//	/**
//	 * 查询新闻详情
//	 */
//	@Override
//	public String findWechatNews(String LEVELSID,String WECHATID){
//		
//	    Map<String,Object> M_Map=new HashMap<String, Object>();
//		
//		List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//		List<Map<String,Object>> M_ListMapS=new ArrayList<Map<String,Object>>();
//		
//		String SQL="SELECT * FROM wx_kindnewmsg WHERE uuid=?";
//		String sql="SELECT * FROM wx_newphoto WHERE LEVELSID=?";
//		
//		M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(SQL, LEVELSID);
//		M_ListMapS=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, LEVELSID);
//		
//	
//		
//    	if(M_ListMap.size()==1){
//    		M_Map.put("INFO", M_ListMap.get(0));
//    		M_Map.put("FILE", M_ListMap);
//    		
//    		M_Map.put("WEBPATH", webPath);
//    		M_Map.put("FILES", M_ListMapS);
//		}else{
//			Map<String,Object> mmm=new HashMap<String,Object>();
//			M_Map.put("INFO",mmm.put("uuid", ""));
//		}
//		
//		try {
//            mu.setM_istatus(1);
//            
//			mu.setM_object(M_Map);
//			
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return m_strResult;
//	}
//	/**
//	 * 
//	* @方法名  getLevelsIdByWeId
//	* @说明  TODO(根据新闻类型id,获取新闻主键LEVELSID)
//	* @param 参数 @param WECHATID
//	* @param 参数 @return    设定文件
//	* @return String    返回类型
//	* @作者 李可
//	* @throws 异常
//	 */
//	public String getLevelsIdByWeId(String WECHATID){
//		
//		String SQL="SELECT * FROM wx_kindnewmsg WHERE wechatid=?";
//		
//		List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//		
//		M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(SQL, WECHATID);
//		
//	
//		String LEVELSID = "";
//    	if(M_ListMap.size() >0 && M_ListMap != null){
//    		LEVELSID = M_ListMap.get(0).get("uuid").toString();
//    	}
//		
//    		
//		return LEVELSID;
//	}
//	
//	/**
//	 * @说明 TODO 添加二级栏目新闻
//	 * @param KNID
//	 * @param 通知公告、卫生知识、每周食谱、新闻中心
//	 * @return
//	 */
//	@Override
//	public String wechatAddNewS(String LEVELSID,String KNID,String newsTitle,String newsConTent,String photoName,String photoDesc,String DelUrl,String kindId){
//		MessageUtil mu=new MessageUtil();
//			
//			try {
//				newsTitle=URLDecoder.decode(newsTitle,"UTF-8");
//			newsConTent=URLDecoder.decode(newsConTent,"UTF-8");
//			 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//实现图片数据插入
//		String[] pnames=photoName.split("@");String PhotoUlr="";
//		String[] pdescs=photoDesc.split("@");String PhotoDes="";
//		String[] DELFILE=new String[]{};
//      	
//      	
//			if(DelUrl.trim().length()>0){
//				DELFILE=DelUrl.trim().split("@");
//			}
//			for (int i = 0; i < pnames.length; i++) {
//				
//				boolean bool=true;
//				
//				for (int j = 0; j < DELFILE.length; j++) {
//					
//					if((Integer.valueOf(i).toString()).equals(DELFILE[j])){
//						
//						bool=false;
//						break;
//					}
//				}
//				
//				if(bool){
//					PhotoUlr+=pnames[i]+"@";
//					PhotoDes+=pdescs[i]+"@";
//				}
//			}
//			
//			String[] PhotoUlrS=PhotoUlr.split("@");
//			String[] PhotoDesS=PhotoDes.split("@");
//			
//			String SQLPHOTO="INSERT INTO wx_newphoto(uuid,KINDID,CREATETIME,fileurl,levelstitle,levelsid)"
//					+ "VALUES(?,?,?,?,?,?)";
//
//			String year = getTimeUrl().get(0).get("YEAR").toString();
//			String month = getTimeUrl().get(0).get("MONTH").toString();
//			String date = getTimeUrl().get(0).get("DATE").toString();
//			String longTime = getTimeUrl().get(0).get("TIME").toString();
//			Random rd = new Random();
//			int number = rd.nextInt(1000);
//			
//			String WEBLINK = "/KINDWXFILE/"+kindId+"/CreateHtml/"+year+"/"+month+"/"+date+"/"+longTime+number+".html";
//			
//			LEVELSID=UUID.randomUUID().toString().replace("-","").toUpperCase();
//			if(PhotoUlrS.length>0){
//				int ex = 0;
//				for(int y=0;y<PhotoUlrS.length;y++){
//					String photoId=UUID.randomUUID().toString().replace("-","").toUpperCase();
//
//					ex = this.getFinalDao().getIEntitySQLDAO().executeSQL(SQLPHOTO,photoId,kindId,new Date(), PhotoUlrS[y],PhotoDesS[y],LEVELSID);
//				}
//				if(ex != 1){
//					return "";
//				}
//				String SQL="INSERT INTO wx_kindnewmsg(uuid,KINDID,CREATEDATE,FILEURL,LEVELSTITLE,LEVELSCONTENT,WECHATID)"
//						+ "VALUES(?,?,?,?,?,?,?)";
//				
//			    int c =	this.getFinalDao().getIEntitySQLDAO().executeSQL(SQL,LEVELSID,kindId,new Date(),WEBLINK,newsTitle,newsConTent,KNID);
//				if(c==1){
//					//对新闻中心的新闻进行推送
//					List<Map<String,Object>> M_ListMapNews=new ArrayList<Map<String,Object>>();
//					String newaql = "select * from wx_levelstype where datatypeid = ?";
//					M_ListMapNews = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(newaql,KNID);
//					if(M_ListMapNews.size()>0&&M_ListMapNews!=null){
//						String da = M_ListMapNews.get(0).get("datatype").toString();
//						if("10".equals(da)){
//							//添加推送信息
//							String description = newsConTent.replaceAll("(<(\\w)+ \\/>|<(\\/)?(\\w)+>|&nbsp;)","");
//							if(description.length()>7){
//								description = description.substring(0, 7)+"...";
//							}
//							if(newsTitle.length()>7){
//								newsTitle = newsTitle.substring(0, 7)+"...";
//							}
//							List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//							String sqlwxlong ="SELECT * FROM wx_user WHERE KINDID=?";
//							M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sqlwxlong,kindId);
//							if(M_ListMap.size()>0){
//								
//								for(int i=0; i<M_ListMap.size(); i++){
//									String openId= M_ListMap.get(i).get("OPENID").toString();
//									String sqladd = "INSERT INTO wx_kindpush (OPENID,KINDID,createtime,TITLE,description,URL,picurl) VALUES(?,?,?,?,?,?,?)";
//									this.getFinalDao().getIEntitySQLDAO().executeSQL(sqladd,openId,kindId,new Date(), newsTitle,description, urlPath+"/"+WEBLINK,  webPath+"/"+PhotoUlrS[0]);
//								}
//								
//							}
//						}
//					}
//				}
//				
//			}else{//无图新闻
//				String SQL="INSERT INTO wx_kindnewmsg(uuid,KINDID,CREATEDATE,FILEURL,LEVELSTITLE,LEVELSCONTENT,WECHATID)"
//						+ "VALUES(?,?,?,?,?,?,?)";
//				
//			    int c =	this.getFinalDao().getIEntitySQLDAO().executeSQL(SQL,LEVELSID,kindId,new Date(), WEBLINK,newsTitle,newsConTent,KNID);
//				if(c==1){
//					//对新闻中心的新闻进行推送
//					List<Map<String,Object>> M_ListMapNews=new ArrayList<Map<String,Object>>();
//					String newaql = "select * from wx_levelstype where datatypeid = ?";
//					M_ListMapNews = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(newaql,KNID);
//					if(M_ListMapNews.size()>0&&M_ListMapNews!=null){
//						String da = M_ListMapNews.get(0).get("datatype").toString();
//						if("10".equals(da)){
//							//添加推送信息
//							String description = newsConTent.replaceAll("(<(\\w)+ \\/>|<(\\/)?(\\w)+>|&nbsp;)","");
//							if(description.length()>7){
//								description = description.substring(0, 7) + "...";
//							}
//							if(newsTitle.length()>7){
//								newsTitle = newsTitle.substring(0, 7) + "...";
//							}
//							List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//							String sqlwxlong ="SELECT * FROM wx_user WHERE KINDID=?";
//							M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sqlwxlong,kindId);
//							if(M_ListMap.size()>0){
//								
//								for(int i=0; i<M_ListMap.size(); i++){
//									String openId= M_ListMap.get(i).get("OPENID").toString();
//									String sqladd = "INSERT INTO wx_kindpush (OPENID,KINDID,createtime,TITLE,description,URL,picurl) VALUES(?,?,?,?,?,?,?)";
//									this.getFinalDao().getIEntitySQLDAO().executeSQL(sqladd, openId,kindId,new Date(), newsTitle,description, urlPath+"/"+WEBLINK,"");
//								}
//								
//							}
//						}
//					}
//				}
//			}
//			try {
//				mu.setM_istatus(1);
//				mu.setM_object(LEVELSID);
//				
//				mu.setM_strMessage("更新信息成功！");
//				
//				this.m_strResult=JSON.toJosn(mu);
//				
//				PushThreadUtil ptu=new PushThreadUtil();
//				ptu.start();
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			
//			return LEVELSID;
//			
//	}
//	/**
//	 * @说明  TODO(添加单个页面（含图）)
//	 * @param  园所简介、入园需知、招生简章
//	 */
//	@Override
//	public String addNewPic(String LEVELSID,String KNID,String newsTitle,String newsConTent,String photoName,String photoDesc,String DelUrl,String kindId){
//		MessageUtil mu=new MessageUtil();
//		LEVELSID = UUID.randomUUID().toString().replace("-","").toUpperCase();
//		try {
//			newsTitle=URLDecoder.decode(newsTitle,"UTF-8");
//			newsConTent=URLDecoder.decode(newsConTent,"UTF-8");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//实现图片数据插入
//		String[] pnames=photoName.split("@");String PhotoUlr="";
//		String[] pdescs=photoDesc.split("@");String PhotoDes="";
//		String[] DELFILE=new String[]{};
//		
//		if(DelUrl.trim().length()>0){
//			DELFILE=DelUrl.trim().split("@");
//		}
//		for (int i = 0; i < pnames.length; i++) {
//			
//			boolean bool=true;
//			
//			for (int j = 0; j < DELFILE.length; j++) {
//				
//				if((Integer.valueOf(i).toString()).equals(DELFILE[j])){
//					
//					bool=false;
//					break;
//				}
//			}
//			
//			if(bool){
//				PhotoUlr+=pnames[i]+"@";
//				PhotoDes+=pdescs[i]+"@";
//			}
//		}
//		
//		String[] PhotoUlrS=PhotoUlr.split("@");
//		String[] PhotoDesS=PhotoDes.split("@");
//		
//		String SQLPHOTO="INSERT INTO wx_newphoto(uuid,KINDID,CREATETIME,fileurl,levelstitle,levelsid)"
//				+ "VALUES(?,?,?,?,?,?)";
//		
//		String year = getTimeUrl().get(0).get("YEAR").toString();
//		String month = getTimeUrl().get(0).get("MONTH").toString();
//		String date = getTimeUrl().get(0).get("DATE").toString();
//		String longTime = getTimeUrl().get(0).get("TIME").toString();
//		Random rd = new Random();
//		int number = rd.nextInt(1000);
//		
//		String WEBLINK = "/KINDWXFILE/"+kindId+"/CreateHtml/"+year+"/"+month+"/"+date+"/"+longTime+number+".html";
//		
//		if(PhotoUlrS.length>0){
//			
//			for(int y=0;y<PhotoUlrS.length;y++){
//				String photoId=UUID.randomUUID().toString().replace("-","").toUpperCase();
//				this.getFinalDao().getIEntitySQLDAO().executeSQL(SQLPHOTO,photoId,kindId,new Date(), PhotoUlrS[y],PhotoDesS[y],LEVELSID);
//			}
//			
//			String SQL="INSERT INTO wx_kindnewmsg(uuid,KINDID,CREATEDATE,FILEURL,LEVELSTITLE,LEVELSCONTENT,WECHATID)"
//					+ "VALUES(?,?,?,?,?,?,?)";
//			
//			this.getFinalDao().getIEntitySQLDAO().executeSQL(SQL,LEVELSID,kindId,new Date(),WEBLINK,newsTitle,newsConTent,KNID);
//			
//		}else{
//			String SQL="INSERT INTO wx_kindnewmsg(uuid,KINDID,CREATEDATE,FILEURL,LEVELSTITLE,LEVELSCONTENT,WECHATID)"
//					+ "VALUES(?,?,?,?,?,?,?)";
//			
//			this.getFinalDao().getIEntitySQLDAO().executeSQL(SQL,LEVELSID,kindId,new Date(), WEBLINK,newsTitle,newsConTent,KNID);
//		}
//		try {
//			mu.setM_istatus(1);
//			mu.setM_object(LEVELSID);
//			
//			mu.setM_strMessage("更新信息成功！");
//			
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		return LEVELSID;
//		
//	}
//
//	
//		/**
//		 * 获取新闻信息列表
//		 */
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		@Override
//		public String findChatlevelList(String WECHATID,int piPageSize, int piPageNo, String pfixpar, String pstrOrderField, String pstrOrderType, HttpServletRequest request) {
//			//查询用户信息总条数
//			String sqlcount = "select count(*) count from wx_kindnewmsg where wechatid = ?";
//			List<Map<String,Object>> M_Listcount=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sqlcount,WECHATID);
//			String messageCount = M_Listcount.get(0).get("count").toString();
//			int messCount = Integer.parseInt(messageCount);
//			if(messCount == 0){
//				messCount = 1;
//			}
//			//三目运算符计算总页数
//			int a = messCount/piPageSize;
//			int pageCount = messCount%piPageSize==0?a:a+1;
//			
//			//判断页码是否超出范围
//			if(piPageNo<1){
//				piPageNo = 1;
//			}else if(piPageNo>pageCount){
//				piPageNo = pageCount;
//			}
//			//计算显示页面的起始下标
//			int beginIndex = (piPageNo-1)*piPageSize;
//			
//			StringBuilder sql = new StringBuilder();
//		    sql.append("SELECT * from (");
//		    sql.append("SELECT uuid LEVELSID");
//		    sql.append(",(CASE WHEN LENGTH(S.LEVELSTITLE)>'32' THEN CONCAT(SUBSTRING(S.LEVELSTITLE,1,32),'...') ELSE S.LEVELSTITLE END) LEVELSTITLE");
//		    sql.append( ",S.LEVELSCONTENT LEVELSCONTENT ");
//		    sql.append(" FROM wx_kindnewmsg S WHERE S.wechatid=? ORDER BY CREATEDATE DESC");
//		    sql.append(" ) sm limit ?,?");
//			
//			List<Map<String,Object>> M_ListMess=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql.toString(),WECHATID,beginIndex,piPageSize);
//			
//			if(M_ListMess.size()>0 && M_ListMess != null){
//				for (Map<String, Object> map : M_ListMess) {
//					String content = map.get("LEVELSCONTENT").toString();
//					content = content.replaceAll("(<(\\w)+ \\/>|<(\\/)?(\\w)+>|&nbsp;)","");
//					if(content.length() > 40){
//						content = content.substring(0, 40)+"...";
//					}
//					map.put("LEVELSCONTENT",content);
//				}
//			}
//			
//			
//			
//			PageInfo pageInfo=new PageInfo();
//			pageInfo.setList(M_ListMess);
//			pageInfo.setPagenum(piPageNo);
//			pageInfo.setRowsCount(messCount);
//			
//			pageInfo.setOperateCaption("编辑,删除");
//			pageInfo.setOperateName("UpWechatNews,DelWechatNews");
//			pageInfo.setOperatePara("LEVELSID,LEVELSID");
//			pageInfo.setShowFun("编辑,删除");
//		
//			
//			try {
//				m_strResult =JSON.addExtraAttr(pageInfo.getList(), JSON.toJosn(pageInfo));
//			}  catch (Exception e) {
//				e.printStackTrace();
//			}
//		 
//		 return m_strResult ;
//			
//		}
//		/**
//		 * 获取新闻信息列表（新闻置顶）
//		 */
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		@Override
//		public String findNewsTop(String WECHATID,int piPageSize, int piPageNo, String pfixpar, String pstrOrderField, String pstrOrderType, HttpServletRequest request) {
//			//查询用户信息总条数
//			String sqlcount = "select count(*) count from wx_kindnewmsg where wechatid = ?";
//			List<Map<String,Object>> M_Listcount=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sqlcount,WECHATID);
//			String messageCount = M_Listcount.get(0).get("count").toString();
//			int messCount = Integer.parseInt(messageCount);
//			if(messCount == 0){
//				messCount = 1;
//			}
//			//三目运算符计算总页数
//			int a = messCount/piPageSize;
//			int pageCount = messCount%piPageSize==0?a:a+1;
//			
//			//判断页码是否超出范围
//			if(piPageNo<1){
//				piPageNo = 1;
//			}else if(piPageNo>pageCount){
//				piPageNo = pageCount;
//			}
//			//计算显示页面的起始下标
//			int beginIndex = (piPageNo-1)*piPageSize;
//			String img = "<img src='image/settop.png'/>";
//			
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT * from ( ");
//			sql.append("SELECT uuid LEVELSID ");
//			sql.append(",totop");
//			sql.append(",(CASE WHEN LENGTH(S.LEVELSTITLE)>'32' THEN CONCAT(SUBSTRING(S.LEVELSTITLE,1,32),'...') ELSE S.LEVELSTITLE END) LEVELSTITLE");
//			sql.append( ",S.LEVELSCONTENT LEVELSCONTENT ");
//			sql.append(" FROM wx_kindnewmsg S WHERE S.wechatid=? ORDER BY totop desc, CREATEDATE DESC");
//			sql.append(" ) sm limit ?,?");                 
//			
//			List<Map<String,Object>> M_ListMess=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql.toString(),WECHATID,beginIndex,piPageSize);
//
//			if(M_ListMess.size()>0 && M_ListMess != null){
//				for (Map<String, Object> map : M_ListMess) {
//					String content = map.get("LEVELSCONTENT").toString();
//					content = content.replaceAll("(<(\\w)+ \\/>|<(\\/)?(\\w)+>|&nbsp;)","");
//					if(content.length() > 40){
//						content = content.substring(0, 40)+"...";
//					}
//					map.put("LEVELSCONTENT",content);
//					String totop = map.get("totop").toString();
//					if("1".equals(totop)){
//						map.put("TOTOP", "是");
//						//map.put("TOTOP", "<img src='image/settop.png' />");
//					}else{
//						map.put("TOTOP","否");
//					}
//				}
//			}
//			
//			
//			PageInfo pageInfo=new PageInfo();
//			pageInfo.setList(M_ListMess);
//			pageInfo.setPagenum(piPageNo);
//			pageInfo.setRowsCount(messCount);
//			
//			pageInfo.setOperateCaption("编辑,删除,置顶");
//			pageInfo.setOperateName("UpWechatNews,DelWechatNews,SetToTop");
//			pageInfo.setOperatePara("LEVELSID,LEVELSID,LEVELSID");
//			pageInfo.setShowFun("编辑,删除,置顶");
//			
//			
//			try {
//				m_strResult =JSON.addExtraAttr(pageInfo.getList(), JSON.toJosn(pageInfo));
//			}  catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			return m_strResult ;
//			
//		}
//	/**
//	 * @说明  TODO(设置新闻置顶)
//	 */
//	public String setToTop(String LEVELSID){
//		MessageUtil mu=new MessageUtil();
//		List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//		
//		String sql = "select * from wx_kindnewmsg where uuid = ?";
//		M_ListMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql, LEVELSID);
//		if(M_ListMap.size()>0 && M_ListMap != null){
//			String totop = M_ListMap.get(0).get("totop").toString();
//			if("1".equals(totop)){
//				String delsql = "update wx_kindnewmsg set totop = 0 where uuid = ?";
//				int cc = this.getFinalDao().getIEntitySQLDAO().executeSQL(delsql,LEVELSID);
//				mu.setM_strMessage("取消置顶成功！");
//			}else{
//				String delsql = "update wx_kindnewmsg set totop = 1 where uuid = ?";
//				int cc = this.getFinalDao().getIEntitySQLDAO().executeSQL(delsql,LEVELSID);
//				mu.setM_strMessage("设置新闻置顶成功！");
//			}
//			
//			mu.setM_istatus(1);
//		}else{
//			mu.setM_strMessage("数据异常，请联系管理员！");
//			mu.setM_istatus(1);
//		}
//		try {
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return m_strResult;
//	}
//	/**
//	 * @说明  TODO(修改新闻置顶LEVELSID)
//	 */
//	@Override
//	public String updateToTop(String LEVELSID,String totop){
//		MessageUtil mu=new MessageUtil();
//		
//		String delsql = "update wx_kindnewmsg set totop = ? where uuid = ?";
//		int cc = this.getFinalDao().getIEntitySQLDAO().executeSQL(delsql,totop,LEVELSID);
//		if(cc > 0){
//			
//			mu.setM_strMessage("修改置顶成功！");
//			mu.setM_istatus(1);
//		}else{
//			mu.setM_strMessage("修改置顶失败！");
//			mu.setM_istatus(1);
//		}
//		try {
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return m_strResult;
//	}
//
//		
//	/**
//	 * 查看子栏目中数据类型
//	 * 1：院所介绍，2：入院须知,3:教师风采,4:网上看园,5:通知公告,6:招生简章,7:收费公示,8:卫生知识,9:每周食谱,10:新闻中心
//	 */
//	@Override
//	public String findChatlevelCount(String WECHATID) {
//		MessageUtil mu=new MessageUtil();
//		List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//		String SQL = "select *  from wx_levelstype  where datatypeid=?";
//		M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(SQL, WECHATID);
//		String type=M_ListMap.get(0).get("datatype").toString();
//		try {
//			mu.setM_object(type);
//			mu.setM_istatus(1);
//			
//			this.m_strResult=JSON.toJosn(mu);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return this.m_strResult;
//	}
//
//	/**
//	 *  @方法名  getWechatId
//	* @说明  TODO(根据类型，获取WECHATID)
//	* @param 参数 @param DATATYPE
//	* @param 参数 @param request
//	* @param 参数 @return    设定文件
//	* @return String    返回类型
//	* @作者 李可
//	* @throws 异常
//	 */
//	public String getWechatId(int DATATYPE,String kindId){
//		MessageUtil mu=new MessageUtil();
//		List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//		
//		String wechatId="";
//		String dql = "select datatypeid from wx_levelstype where kindid = ? and datatype = ?";
//		M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(dql, kindId,DATATYPE);
//		
//		if(M_ListMap.size()>0 && M_ListMap != null){
//			wechatId = M_ListMap.get(0).get("datatypeid").toString();
//			mu.setM_object(wechatId);
//			mu.setM_istatus(1);
//		}else{
//			mu.setM_strMessage("无数据");
//			mu.setM_istatus(1);
//		}
//		
//		try {
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return wechatId;
//	}
//	
//	/**
//	 * @说明  TODO(查询只含图片页面)
//	 */
//	public String findIndexPhoto(int dataType,String kindId){
//		MessageUtil mu=new MessageUtil();
//		List<Map<String,Object>> M_ListMap=new ArrayList<Map<String,Object>>();
//		Map<String,Object> reListMap=new HashMap<String,Object>();
//		
//		String sql = "select * from wx_kindphotopath where dataType = ? and kindid= ?";
//		M_ListMap=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sql,dataType,kindId);
//		if(M_ListMap.size()>0 && M_ListMap != null){
//			reListMap.put("WEBPATH", webPath);
//			reListMap.put("FILES", M_ListMap);
//			mu.setM_object(reListMap);
//			mu.setM_istatus(1);
//		}else{
//			mu.setM_strMessage("未添加数据！");
//			mu.setM_istatus(1);
//		}
//		
//		try {
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return m_strResult;
//	}
//	
//	/**
//	 * @说明  TODO(添加首页轮播图)
//	 * @param dataType 1:首页轮播图，2：网上看图，3：收费公示
//	 */
//	public String addIndexPhoto(int dataType,String kindId,String photoName,String photoDesc,String DelUrl){
//		MessageUtil mu=new MessageUtil();
//		String photoId = "";
//		int ATYPE = 0;
//		switch (dataType) {
//		case 1:ATYPE = 0;break;
//		case 2:ATYPE = 4;break;
//		case 3:ATYPE = 7;break;
//		}
//		String wechatId = getWechatId(ATYPE,kindId);
//		
//		//实现图片数据插入
//		String[] pnames=photoName.split("@");String PhotoUlr="";
//		String[] pdescs=photoDesc.split("@");String PhotoDes="";
//	    String[] DELFILE=new String[]{};
//  	     
//		if(DelUrl.trim().length()>0){
//			DELFILE=DelUrl.trim().split("@");
//		}
//		for (int i = 0; i < pnames.length; i++) {
//			
//			boolean bool=true;
//			
//			for (int j = 0; j < DELFILE.length; j++) {
//				
//				if((Integer.valueOf(i).toString()).equals(DELFILE[j])){
//					
//					bool=false;
//					break;
//				}
//			}
//			
//			if(bool){
//				PhotoUlr+=pnames[i]+"@";
//				PhotoDes+=pdescs[i]+"@";
//			}
//		}
//		
//		String[] PhotoUlrS=PhotoUlr.split("@");
//		String[] PhotoDesS=PhotoDes.split("@");
//		
//		String delSQLPHOTO="delete from  wx_kindphotopath  where kindid = ? and datatype = ?";
////		if(dataType != 2){
//			int cc = this.getFinalDao().getIEntitySQLDAO().executeSQL(delSQLPHOTO,kindId,dataType);
////		}
//		
//		String SQLPHOTO="INSERT INTO wx_kindphotopath(photoid,KINDID,uploadtime,photoUrl,description,datatype,wechatid)"
//					+ "VALUES(?,?,?,?,?,?,?)";
//		
//		String year = getTimeUrl().get(0).get("YEAR").toString();
//		String month = getTimeUrl().get(0).get("MONTH").toString();
//		String date = getTimeUrl().get(0).get("DATE").toString();
//		String longTime = getTimeUrl().get(0).get("TIME").toString();
//		Random rd = new Random();
//		int number = rd.nextInt(1000);
//		
//		String WEBLINK = "/KINDWXFILE/"+kindId+"/CreateHtml/"+year+"/"+month+"/"+date+"/"+longTime+number+".html";
//		if(PhotoUlrS.length>0){
//			int ex = 0;
//			
//			for(int y=0;y<PhotoUlrS.length;y++){
//				photoId=UUID.randomUUID().toString().replace("-","").toUpperCase();
//
//				//2：网上看图
////				if(dataType == 2){
////					String sql = "update wx_kindphotopath set fileurl = '' where kindid = ? and datatype = ?";
////					this.getFinalDao().getIEntitySQLDAO().executeSQL(sql,kindId,dataType);
////				}
//				ex = this.getFinalDao().getIEntitySQLDAO().executeSQL(SQLPHOTO,photoId,kindId,new Date(), PhotoUlrS[y],PhotoDesS[y],dataType,wechatId);
//			}
//			if(ex > 0){
//				if(dataType == 1){//首页
//					WEBLINK = "/KINDWXFILE/"+kindId+"/CreateHtml/kindindex.html";
//				}
//				
//				String sqladd = "update wx_kindphotopath set fileurl = ? where photoid = ? and kindid = ?";
//				this.getFinalDao().getIEntitySQLDAO().executeSQL(sqladd,WEBLINK,photoId,kindId);
//				
//				mu.setM_istatus(1);
//				mu.setM_strMessage("更新图片成功！");
//				try {
//					this.m_strResult=JSON.toJosn(mu);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return photoId;
//			}
//		}
//			mu.setM_strMessage("更新图片失败！");
//			mu.setM_istatus(0);
//		
//		try {
//			this.m_strResult=JSON.toJosn(mu);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return photoId;
//	}
//
//	/**
//	 * 生成年月日文件夹路径
//	 * @return
//	 */
//	private List<Map<String,Object>> getTimeUrl(){
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		Map<String,Object> map = new HashMap<String,Object>();
//		
//		Calendar cal=Calendar.getInstance();//使用日历类
//		int year=cal.get(Calendar.YEAR);//得到年
//		int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
//		//int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
//		String day=new SimpleDateFormat("dd").format(new Date());
//		Date date = new Date();
//		long time = date.getTime();
//		
//		map.put("YEAR", year);
//		map.put("MONTH", month);
//		map.put("DATE", day);
//		map.put("TIME", time);
//		
//		list.add(map);
//		return list;
//	}
//}