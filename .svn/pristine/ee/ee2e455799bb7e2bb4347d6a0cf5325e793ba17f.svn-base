package com.yiliao.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.yiliao.service.CPSService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.PayUtil;

@Service("cpsService")
public class CPSServiceImpl extends ICommServiceImpl implements CPSService {

	@Override
	public JSONObject getCPSList(String t_cps_name, int page) {
		try {
			
			String cSql=" SELECT COUNT(t_id) AS total FROM t_cps  WHERE 1=1 ";
			
			if(StringUtils.isNotBlank(t_cps_name)){
				cSql = cSql + " AND t_cps_name LIKE '%"+t_cps_name+"%' ";
			}
			
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(cSql);
			
			StringBuffer qSql = new StringBuffer();
			qSql.append(" SELECT * FROM ( ");
			qSql.append(" SELECT c.t_id,u.t_nickName,c.t_cps_name,c.t_cps,c.t_active_user,c.t_proportions,SUM(d.t_devote_value) AS totalVale,c.t_settlement_type,c.t_bank,c.t_audit_status");
			qSql.append(",c.t_real_name,c.t_phone,DATE_FORMAT(c.t_create_time,'%Y-%m-%d %T') AS t_create_time ");
			qSql.append(" FROM t_cps c LEFT JOIN t_user u ON u.t_id=c.t_user_id ");
			qSql.append(" LEFT JOIN t_cps_devote d ON d.t_cps_id=c.t_id ");
			qSql.append(" WHERE 1=1 ");
			if(StringUtils.isNotBlank(t_cps_name)){
				qSql.append(" AND c.t_cps_name LIMIT '%").append(t_cps_name).append("%' ");
			}
			qSql.append(" GROUP BY c.t_id ");
			qSql.append(" ) aa ORDER BY aa.t_create_time DESC LIMIT ?,10 ");
			
			List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql.toString(), (page-1)*10);
			
			for(Map<String, Object> m : dataMap){
				if (null == m.get("totalVale")) {
					m.put("totalVale", 0);
				}
				//查询该推广联盟是否已经结算过了
				String sql = "SELECT SUM(t_settlement_amount) AS total FROM t_cps_settlement WHERE t_cps_id = ? ";
				
				Map<String, Object> sett_money = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, m.get("t_id"));
				
				if(null == sett_money.get("total")){
					//设置已经结算了多少钱
					m.put("already", 0);
					m.put("balance", m.get("totalVale"));
				}else{
					//设置已经结算了多少钱
					m.put("already", sett_money.get("total"));
					
					sql="SELECT (SELECT SUM(t_devote_value) FROM t_cps_devote WHERE t_cps_id = ?) - ";
					sql = sql + " (SELECT SUM(t_settlement_amount) FROM t_cps_settlement WHERE t_cps_id = ? AND DATE_FORMAT(t_createt_time,'%Y-%m-%d')+' 00:00:00' AND ?) AS money ";
					
					Map<String, Object> money = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(sql, m.get("t_id"),m.get("t_id"),DateUtils.format(new Date(System.currentTimeMillis()-1000*60*60*24))+" 23:59:59");
				
					m.put("balance", money.get("money"));
				}
			}
			
			JSONObject json = new JSONObject();
			
			json.put("total", totalMap.get("total"));
			json.put("rows", dataMap);
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取CPS联盟异常!", e);
		}
		return null;
	}
	
	 
	@Override
	public MessageUtil examineSuccess(int t_id, String t_real_name,
			String t_phone, String t_cps_name, String t_cps,
			int t_settlement_type, String t_bank, int t_active_user,
			int t_proportions) {
		MessageUtil mu = null;
		try {
			
			String uSql= "UPDATE t_cps SET  t_real_name=?, t_settlement_type=?, t_bank=?, t_phone=?, t_cps_name=?, t_cps=?, t_active_user=?, t_proportions=?, t_audit_status=1 WHERE t_id= ?";
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_real_name,
					t_settlement_type,t_bank,t_phone,t_cps_name,t_cps,t_active_user,t_proportions,t_id);
			
			mu = new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CPS联盟审核通过异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}

	@Override
	public MessageUtil examineError(int t_id) {
		MessageUtil mu = null;
		try {
			
			String uSql=" UPDATE t_cps SET t_audit_status =2 WHERE t_id= ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_id);
			
			mu = new MessageUtil(1, "操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CPS联盟下架异常!", e);
			mu = new MessageUtil(0, "程序异常!");
		}
		return mu;
	}


	/*
	 * 结算CPS(non-Javadoc)
	 * @see com.yiliao.service.CPSService#settlementCPS(int)
	 */
	@Override
	public MessageUtil settlementCPS(int t_cps_id,String t_order_no,int t_settlement_type) {
		MessageUtil mu=null;
		try {
			
			//获取是否结算过
			String qSql=" SELECT * FROM t_cps_settlement WHERE t_cps_id = ? ";
			List<Map<String, Object>> settList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, t_cps_id);
			
			//得到要给CPS结算的金额
			BigDecimal money = null;
			//未结算过 
			if(settList.isEmpty()){
				//得到应该给CPS结算多少钱
				qSql = " SELECT SUM(t_devote_value) AS money FROM t_cps_devote WHERE t_cps_id = ? ";
				List<Map<String, Object>> surplusList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, t_cps_id);
				
				if(!surplusList.isEmpty()){
					if(null != surplusList.get(0).get("money")){
						money = new BigDecimal(surplusList.get(0).get("money").toString());
					}else{
						return new MessageUtil(1, "暂无可结算金额!");
					}
				}else{
                    return new MessageUtil(1, "暂无可结算金额!");					
				}
			}else{
				
				//获取应该给CPS结算多少钱
				qSql="SELECT (SELECT SUM(t_devote_value) FROM t_cps_devote WHERE t_cps_id = ?) - ";
				qSql = qSql + " (SELECT SUM(t_settlement_amount) FROM t_cps_settlement WHERE t_cps_id = ? AND DATE_FORMAT(t_createt_time,'%Y-%m-%d')+' 00:00:00' AND ?) AS money ";
				
				Map<String, Object> surplusMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_cps_id,t_cps_id,DateUtils.format(new Date(System.currentTimeMillis()-1000*60*60*24))+" 23:59:59");
			
				money = new BigDecimal(surplusMap.get("money").toString());
				if(money.compareTo(new BigDecimal(0)) < 1){
					return new MessageUtil(1, "暂无可结算金额.");
				}
			}
			
			//判断是否是
			if(t_settlement_type == 0){
				
				//获取当前CPS结算信息是支付宝还是银行卡转账
				qSql = " SELECT t_real_name,t_bank,t_settlement_type FROM t_cps WHERE t_id = ?";
				
				Map<String, Object> cpsMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_cps_id);
				
				//获取支付宝配置信息
				List<Map<String, Object>> alipaySetUp = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT t_alipay_appid,t_alipay_private_key,t_alipay_public_key FROM t_alipay_setup");
				
				if(alipaySetUp.isEmpty()) {
					return new MessageUtil(-2, "支付信息未配置!");
				}
 
				String orderNo = "zf_"+t_cps_id+"_"+System.currentTimeMillis();
				
				AlipayFundTransToaccountTransferResponse alipayTransfer = PayUtil.alipayTransfer(orderNo, 
						cpsMap.get("t_bank").toString(), 
						money.setScale(2, BigDecimal.ROUND_DOWN).toString(), 
						cpsMap.get("t_real_name").toString(),
						alipaySetUp.get(0).get("t_alipay_appid").toString(),
						alipaySetUp.get(0).get("t_alipay_private_key").toString(),
						alipaySetUp.get(0).get("t_alipay_public_key").toString());
				
				if(alipayTransfer.isSuccess()){
					
					String inSql=" INSERT INTO t_cps_settlement (t_cps_id, t_settlement_amount,t_order_no,t_settlement_type, t_createt_time) VALUES (?,?,?,?,?)";
					
					this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_cps_id,money,orderNo,
							0,DateUtils.format(new Date(), DateUtils.FullDatePattern));
					
					mu = new MessageUtil(1, "已结算!");
				}else{
					return new MessageUtil(1, "支付宝转账异常!请核对信息.");
				}
			}else{
				String inSql=" INSERT INTO t_cps_settlement (t_cps_id, t_settlement_amount,t_order_no,t_settlement_type, t_createt_time) VALUES (?,?,?,?,?)";
				
				this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, t_cps_id,money,t_order_no,
						0,DateUtils.format(new Date(), DateUtils.FullDatePattern));
				
				mu = new MessageUtil(1, "已结算!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CPS结算异常", e);
			mu = new MessageUtil(0, "");
		}
		return mu;
	}


	/*
	 * 获取贡献列表
	 * (non-Javadoc)
	 * @see com.yiliao.service.CPSService#getContributionList(int, int)
	 */
	@Override
	public JSONObject getContributionList(int t_cps_id, int page) {
		try {
			
			//获取总记录数
			String cSql = " SELECT COUNT(t_id) AS total FROM t_cps_devote WHERE t_cps_id = ? GROUP BY t_user_id ";
			List<Map<String,Object>> findBySQLTOMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(cSql, t_cps_id);
			
			Map<String,Object>  totalMap = null ;
			if(!findBySQLTOMap.isEmpty()){
				totalMap = findBySQLTOMap.get(0);
			}
			
			//获取贡献数据
			String qSql = "SELECT u.t_nickName,u.t_phone,SUM(d.t_recharge_money) AS recharge_money ,SUM(d.t_devote_value) AS t_devote_value,d.t_ratio FROM t_cps_devote d LEFT JOIN t_user u ON d.t_user_id=u.t_id WHERE d.t_cps_id = ? GROUP BY u.t_id LIMIT ?,10";
			List<Map<String, Object>>  dataMap= this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, t_cps_id,(page -1)*10);
			
			for(Map<String, Object> m : dataMap){
				if(null == m.get("t_nickName")){
					m.put("t_nickName", "聊友:"+m.get("t_phone").toString().substring(m.get("t_phone").toString().length()-4));
				}
				m.remove("t_phone");
			}
			
			JSONObject json = new JSONObject();
			
			json.put("total",null == totalMap ? 0: totalMap.get("total"));
			json.put("rows", dataMap);
			
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取贡献列表异常!", e);
		}
		return null;
	}


	@Override
	public JSONObject getSetmmList(int t_cps_id, int page) {
		try {
			
			String c_sql = "SELECT COUNT(t_id) AS total FROM t_cps_settlement WHERE t_cps_id = ? AND t_settlement_type = 0 ; ";
			
			Map<String, Object> total = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(c_sql, t_cps_id);
			
			//获取数据列表
			
//			String 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
