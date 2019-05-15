package com.yiliao.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yiliao.service.BasicsMsgService;
import com.yiliao.util.ErrorMsgUtil;

/**
 * CopyRright (c)2016-版权所有: 重庆西信天元数据资讯有限公司
 * 
 * @项目工程名 WXManage
 * @Module ID <(模块)类编号，可以引用系统设计中的类编号> Comments <对此类的描述，可以引用系统设计中的描述>
 * @JDK 版本(version) JDK1.6.45
 * @命名空间 com.yiliao.service.impl
 * @作者(Author) 石德文
 * @创建日期 2016年3月15日 下午2:40:48
 * @修改人
 * @修改时间 <修改日期，格式:YYYY-MM-DD> 修改原因描述：
 * @Version 版本号 V1.0
 * @类名称 BasiceMsgServiceImpl
 * @描述 (微信基础信息接口实现)
 */
@Service("basicsMsgService")
public class BasicsMsgServiceImpl extends ICommServiceImpl implements
		BasicsMsgService {

	/*
	 * <p>Title: obtaionBasicsMsgList</p> <p>Description: 获取微信基础信息集合</p>
	 * 
	 * @return
	 * 
	 * @see com.yiliao.service.BasicsMsgService#obtaionBasicsMsgList()
	 */
	@Override
	public Object obtaionBasicsMsgList() {
		List<Map<String, Object>> maplist=new ArrayList<Map<String,Object>>();
		try {
			//定义sql语句
			StringBuffer sb=new StringBuffer();
			//使用公司平台 打开此sql查询
			sb.append("select b.kindid,b.appID,b.appsecret,b.wxtype from wx_basicsmsg b where b.wxtype=1 GROUP BY b.appID");
		    //使用自己的公众号 打开此sql查询
//			sb.append("select b.kindid,b.appID,b.appsecret,b.wxtype from wx_basicsmsg b where b.wxtype=2 GROUP BY b.appID");
			//使用第三方公众号 打开此sql查询
			//sb.append("select b.kindid,b.appID,b.appsecret,b.wxtype from wx_basicsmsg b where b.wxtype=3 GROUP BY b.appID");
			
			//根据sql获取数据 
			maplist=this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(sb.toString());
			
			logger.info(maplist.toString());

		} catch (Exception e) {
			ErrorMsgUtil.error(e, "程序异常!");
		}

		return maplist;
	}

	/*
	 * (非 Javadoc)
	* <p>Title: obtainToken</p>
	* <p>Description: 根据传递的token值 判断是否存在数据</p>
	* @param token
	* @return
	* @see com.yiliao.service.BasicsMsgService#obtainToken(java.lang.String)
	 */
	@Override
	public Object obtainToken(String token) {
		try {
			//拼接sql语句
			StringBuffer sb=new StringBuffer();
			sb.append("select b.token from wx_basicsmsg b where b.kindid=?");
			//查询数据
			List<Map<String, Object>> listmap=this.getFinalDao()
					.getIEntitySQLDAO().findBySQLTOMap(sb.toString(), token);
			//验证数据是否存在
			if(null==listmap || listmap.isEmpty()){
				return null;
			}else{
				return listmap.get(0);
			}
		 
		} catch (Exception e) {
			ErrorMsgUtil.error(e, "程序异常!");
		}
		return null;
	}

	@Override
	public Object upageData(String t1, String t2, String t3) {
		try {
			String sql="update wx_basicsmsg  set wxtype=1 where uuid='314645654'";
		   int count=	this.getFinalDao().getIEntitySQLDAO().executeSQL(sql);
		   System.out.println(count);
		} catch (Exception e) {
			ErrorMsgUtil.error(e, "程序异常!");
		}
		return null;
	}

}
