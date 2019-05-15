package com.yiliao.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yiliao.domain.WalletDetail;
import com.yiliao.service.CertificationService;
import com.yiliao.service.GoldComputeService;
import com.yiliao.util.DateUtils;
import com.yiliao.util.MessageUtil;
import com.yiliao.util.SpringConfig;

import net.sf.json.JSONObject;

@Service("certificationService")
public class CertificationServiceImpl extends ICommServiceImpl implements
		CertificationService {

	
	private GoldComputeService goldComputeService = (GoldComputeService) SpringConfig.getInstance()
			.getBean("goldComputeService");
	/*
	 * 获取实名认证列表(non-Javadoc)
	 * @see com.yiliao.service.CertificationService#getCertificationList(int)
	 */
	@Override
	public JSONObject getCertificationList(String condition,int page) {
		
		JSONObject json = new JSONObject();

		try {

			String countSql = "SELECT count(c.t_id) AS  totalCount FROM t_certification c  LEFT JOIN t_user u ON(c.t_user_id=u.t_id) ";

			String sql = "SELECT u.t_idcard,c.t_user_photo,c.t_nam,c.t_user_video,c.t_id_card,c.t_certification_type,DATE_FORMAT(c.t_create_time,'%Y-%m-%d %T') AS t_create_time,u.t_nickName,u.t_sex,u.t_id,u.t_disable FROM t_certification c LEFT JOIN t_user u ON(c.t_user_id=u.t_id) ";

			if(StringUtils.isNotBlank(condition)){
				countSql = countSql + " WHERE c.t_nam LIKE '%"+condition+"%' OR u.t_nickName LIKE '%"+condition+"%' OR u.t_idcard like '%"+condition+"%'";
				sql = sql +  " WHERE c.t_nam LIKE '%"+condition+"%' OR u.t_nickName LIKE '%"+condition+"%' or u.t_idcard like '%"+condition+"%'";
			}
			
			sql = sql + " order by c.t_create_time DESC  LIMIT ?,10 ";
			
			Map<String, Object> totalCount = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLUniqueResultToMap(countSql);


			List<Map<String, Object>> listMap = this.getFinalDao()
					.getIEntitySQLDAO().findBySQLTOMap(sql, (page - 1) * 10);

			json.put("total", totalCount.get("totalCount"));
			json.put("rows", listMap);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取用户列表异常!", e);
		}

		return json;
	}

	@Override
	public MessageUtil updateDisable(int t_id) {
		try {
			
			String uSql = "UPDATE t_user SET t_disable = 2 WHERE t_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_id);
			
			return new MessageUtil(1, "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MessageUtil verifySuccess(int t_id) {
		try {
			
			String uSql="UPDATE t_certification SET t_certification_type = 1 WHERE t_user_id = ? ";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_id);
			 
			
			String upSql = "UPDATE t_user SET  t_role=1  WHERE t_id= ? ";
			this.getFinalDao().getIEntitySQLDAO().executeSQL(upSql, t_id);

			//查询出系统默认的收费设置
			List<Map<String, Object>> systemSetUp = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLTOMap("SELECT t_default_text,t_default_video,t_default_phone,t_default_weixin FROM t_system_setup");
			
			if(!systemSetUp.isEmpty()) {
				// 给主播插入默认的收费设置
				this.updateAnchorChargeSetup(t_id, 
						new BigDecimal(systemSetUp.get(0).get("t_default_video").toString()),
						new BigDecimal(systemSetUp.get(0).get("t_default_text").toString()), 
						new BigDecimal(systemSetUp.get(0).get("t_default_phone").toString()), 
						new BigDecimal(systemSetUp.get(0).get("t_default_weixin").toString()), 
						null, null);
			}
			
			/************获取认证奖励***************/
			String qSql = "SELECT * FROM t_spread_award WHERE t_sex = 0 ";
			List<Map<String, Object>> querySqlList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql);

			if (!querySqlList.isEmpty()) {
				querySqlList.forEach(s -> {
					if (s.get("t_rank").toString().equals("1")) {
						// 获取用户的直接推广人
						Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap("SELECT t_referee FROM t_user WHERE t_id = ?", t_id);
						if (null != map.get("t_referee") && 0 != Integer.parseInt(map.get("t_referee").toString())) {
							// 获取推广人变动钱的金额
							Map<String, Object> refereeMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(" SELECT t_share_money FROM t_balance WHERE t_user_id = ? ", map.get("t_referee"));

							int orderId = getOrderId();
							// 给用户写入金币
							goldComputeService.saveChangeRecord(Integer.parseInt(map.get("t_referee").toString()),
									new BigDecimal(refereeMap.get("t_share_money").toString()),
									new BigDecimal(s.get("t_gold").toString()), WalletDetail.CHANGE_TYPE_INCOME,
									WalletDetail.CHANGE_CATEGOR_RECOMMENDATION, 3, orderId);
							// 插入订单记录
							saveOrder(orderId, 0, Integer.parseInt(map.get("t_referee").toString()),
									Integer.parseInt(s.get("t_id").toString()),
									WalletDetail.CHANGE_CATEGOR_RECOMMENDATION,
									new BigDecimal(s.get("t_gold").toString()));

						}
					} else if (s.get("t_rank").toString().equals("2")) {
						// 获取用户的直接推广人
						List<Map<String, Object>> sqlList = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(
								"SELECT u.t_referee FROM t_user u LEFT JOIN t_user ul  ON u.t_id = ul.t_referee WHERE ul.t_id = ? ",
								t_id);
						if (!sqlList.isEmpty() && !sqlList.get(0).get("t_referee").toString().equals("0")) {
							// 获取推广人变动钱的金额
							Map<String, Object> refereeMap =this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(
									" SELECT t_share_money FROM t_balance WHERE t_user_id = ? ",
									sqlList.get(0).get("t_referee"));

							int orderId = getOrderId();
							// 给用户写入金币
							goldComputeService.saveChangeRecord(
									Integer.parseInt(sqlList.get(0).get("t_referee").toString()),
									new BigDecimal(refereeMap.get("t_share_money").toString()),
									new BigDecimal(s.get("t_gold").toString()), WalletDetail.CHANGE_TYPE_INCOME,
									WalletDetail.CHANGE_CATEGOR_RECOMMENDATION, 3, orderId);
							// 插入订单记录
							saveOrder(orderId, 0, Integer.parseInt(sqlList.get(0).get("t_referee").toString()),
									Integer.parseInt(s.get("t_id").toString()),
									WalletDetail.CHANGE_CATEGOR_RECOMMENDATION,
									new BigDecimal(s.get("t_gold").toString()));
						}
					}
				});

			}
			/***************获取认证奖励完*******************/
			// 查询用户已分享次数
			qSql = " SELECT count(t_id) AS total FROM t_share_notes WHERE t_user_id = ? ";
			Map<String, Object> totalMap = this.getFinalDao().getIEntitySQLDAO().findBySQLUniqueResultToMap(qSql, t_id);

			// 获取用户的性别
			qSql = " SELECT t_id FROM t_user WHERE t_id = ?  AND t_sex = 1 ";
			List<Map<String, Object>> userMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, t_id);
			// 判断是否已经分享了3次 
			if (Integer.parseInt(totalMap.get("total").toString()) >= 3
					&& !userMap.isEmpty()) {
				// 在次判断用户是否已经领取了奖品了
				qSql = "SELECT t_id  AS total FROM t_award_record WHERE t_user_id = ? ";
				List<Map<String, Object>> dataMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, t_id);
				// 用户未中过奖
				if (null == dataMap || dataMap.isEmpty()) {
					// 判断用户是否已经实名认证了
					qSql = "SELECT * FROM t_certification WHERE t_user_id = ? ";
					List<Map<String, Object>> identificationMap = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, t_id);
					// 用户必须实名认证才能中奖
					if (!identificationMap.isEmpty()) {
						// 获取用户可参与的活动
						qSql = " SELECT t_id  FROM t_activity WHERE t_is_enable = 0 AND t_join_term = 3 ";
						List<Map<String, Object>> actMap = this
								.getFinalDao().getIEntitySQLDAO()
								.findBySQLTOMap(qSql);
						if (!actMap.isEmpty()) {
							this.shareRedPacket(t_id,Integer.parseInt(actMap.get(0).get("t_id").toString()));
						}
					}
				}
			}
			
			return new MessageUtil(1,"操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 设置默认收费设置
	 * @param t_user_id 用户编号
	 * @param t_video_gold 默认视频金币 
	 * @param t_text_gold 默认文本聊天金币
	 * @param t_phone_gold 默认查看手机金币
	 * @param t_weixin_gold 默认查看微信金币
	 * @param t_private_photo_gold 默认查看私密照片金币
	 * @param t_private_video_gold 默认查看私密视频金币
	 */
	private void updateAnchorChargeSetup(int t_user_id,BigDecimal t_video_gold, BigDecimal t_text_gold, BigDecimal t_phone_gold,
			BigDecimal t_weixin_gold, BigDecimal t_private_photo_gold,
			BigDecimal t_private_video_gold) {

		try {

			String query = "select count(t_id) as total from t_anchor_setup where t_user_id = ?";

			Map<String, Object> map = this.getFinalDao().getIEntitySQLDAO()
					.findBySQLUniqueResultToMap(query, t_user_id);
			
			// 新增
			if (0 == Integer.parseInt(map.get("total").toString())) {

				String inSql = "INSERT INTO t_anchor_setup (t_user_id, t_video_gold, t_text_gold, t_phone_gold, t_weixin_gold) VALUES (?,?,?,?,?);";

				this.getFinalDao()
						.getIEntitySQLDAO()
						.executeSQL(inSql, t_user_id, t_video_gold,
								t_text_gold, t_phone_gold, t_weixin_gold);

			} else { // 修改
				String sql = "UPDATE t_anchor_setup SET ";
				if (t_video_gold.compareTo(new BigDecimal(0)) > 0) {
//					//验证主播
//					//金币数修改为10——50这个区间。主播可以选择：10,15,20,25,30,35,40,45,50.
//					//新主播最高只能设置每分钟30，提现达到100后，
//					//才有设置40的选项，提现500后才有设置成50的选项
//                    //获取主播是否存在提现
//					String qSql = " SELECT SUM(t_money) AS totalMoney FROM t_put_forward  WHERE t_user_id  = ? ";
//					Map<String, Object> putMoney = this.getMap(qSql, t_user_id);
//					
//					int totalMoney = new BigDecimal(null == putMoney.get("totalMoney")?"0":putMoney.get("totalMoney").toString()).intValue();
//					
//					if((totalMoney < 100 && t_video_gold<=30 && t_video_gold > 0)
//							|| (totalMoney >=100 && totalMoney < 500 && t_video_gold <=40 && t_video_gold > 0)
//							|| (totalMoney >= 500 && t_video_gold <= 50 && t_video_gold > 0)) {
//					
						sql = sql + " t_video_gold = " + t_video_gold + ",";
//					}else {
//						return new MessageUtil(-1, "视频聊天收费异常!");
//					}
				}
				if (t_text_gold.compareTo(new BigDecimal(0)) > 0) {
					sql = sql + " t_text_gold = " + t_text_gold + ",";
				}
				if (t_weixin_gold.compareTo(new BigDecimal(0)) > 0) {
					sql = sql + " t_weixin_gold = " + t_weixin_gold + ",";
				}
				if (t_private_photo_gold.compareTo(new BigDecimal(0)) > 0) {
					sql = sql + " t_private_photo_gold = "
							+ t_private_photo_gold + ",";
				}
				if (t_private_video_gold.compareTo(new BigDecimal(0)) > 0) {
					sql = sql + " t_private_video_gold = "
							+ t_private_video_gold + ",";
				}
				sql = sql + " t_phone_gold = " + t_phone_gold + ",";

				if (sql.indexOf(",") > 0) {

					sql = sql.substring(0, sql.lastIndexOf(","));

					sql = sql + "  WHERE t_user_id = ?";

					this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_user_id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("修改获取主播收费设置", e);
		}
	}

	@Override
	public MessageUtil verifyFail(int t_id) {
		try {
			String uSql="UPDATE t_certification SET t_certification_type = 2 WHERE t_user_id = ?";
			
			this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, t_id);
			
			return new MessageUtil(1,"操作成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public synchronized void shareRedPacket(int userId,int activityId) {
		try {
			//根据活动编号得到那个奖品明细
			String qSql = "SELECT t_id,t_prize_size,t_surplus_number FROM t_activity_detail WHERE t_activity_id = ? AND t_is_join = 0";
			
			List<Map<String, Object>> prizeData = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap(qSql, activityId);
			
			List<Integer> arr = new ArrayList<Integer>();
			
			for(Map<String, Object> m : prizeData){
				//取得奖励剩余名额
				int Quota = Integer.parseInt(m.get("t_surplus_number").toString());
				
				for (int i = 0; i < Quota; i++) {
					arr.add(Integer.parseInt(m.get("t_id").toString()));
				}
			}
			//判断是否还存在奖励
			if(!arr.isEmpty()){
				//打乱数据
				Collections.shuffle(arr);
				//
			    Random random = new Random();
			    int nextInt = random.nextInt(arr.size());
				 
				for(Map<String, Object> m : prizeData){
					//得到用户以中的奖项
					if(arr.get(nextInt) == Integer.parseInt(m.get("t_id").toString())){
						//计算用户已中奖的金币
						int gold = 0;
						
						String prizeSize = m.get("t_prize_size").toString();	
	                    //查找奖项值是否在区间
	                    if(prizeSize.indexOf(",") > 0){
	                    	String[] str = prizeSize.split(",");
	                    	gold = random.nextInt(Integer.parseInt(str[1]))%(Integer.parseInt(str[1])-Integer.parseInt(str[0])+1) + Integer.parseInt(str[0]);
	                    }else{
	                    	gold = Integer.parseInt(m.get("t_prize_size").toString());
	                    }
	                    
	                    logger.info("当前{}用户中奖了{}个金币",userId,gold);
						
	                    //把数据插入到数据库中
	                    String inSql = "INSERT INTO t_award_record (t_user_id, t_activity_id, t_prizedetai_id, t_award_gold, t_award_time) VALUES (?,?,?,?,?) ";
	                    this.getFinalDao().getIEntitySQLDAO().executeSQL(inSql, userId,activityId,Integer.parseInt(m.get("t_id").toString()),gold,DateUtils.format(new Date(), DateUtils.FullDatePattern));
					    
	                    //判断剩余奖项
	                    if((Integer.parseInt(m.get("t_surplus_number").toString()) -1) == 0){
	                    	//更新当前奖项剩余数量为0 且不在参与抽奖了
	                    	String uSql = "UPDATE t_activity_detail SET t_surplus_number = 0,t_is_join = 1 WHERE t_id = ? ";
	                    	this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, m.get("t_id"));
	                    }else{
	                    	//当前奖项名额减一
	                    	String uSql = "UPDATE t_activity_detail SET t_surplus_number = t_surplus_number -1 WHERE t_id = ? ";
	                    	this.getFinalDao().getIEntitySQLDAO().executeSQL(uSql, m.get("t_id"));
	                    }
	                    
	                    //把数据插入到红包记录中
	                	String  sql = "INSERT INTO t_redpacket_log (t_hair_userId, t_receive_userId, t_redpacket_content, t_redpacket_gold, t_redpacket_draw, t_redpacket_type, t_create_time) VALUES (?, ?, ?, ?, ?, ?, ?);";

	            		this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, 0,userId,"收到官方奖励的新人红包"+gold+"个金币",
	            				gold,0,2,DateUtils.format(new Date(), DateUtils.FullDatePattern));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发放分享红包异常!", e);
		}
	}
	
	/**
	 * 获取订单Id
	 * 
	 * @return
	 */
	public int getOrderId() {
		List<Map<String, Object>> arr = this.getFinalDao().getIEntitySQLDAO().findBySQLTOMap("SELECT t_id FROM t_order ORDER BY t_id DESC LIMIT 1;");
		return arr.isEmpty() ? 1 : (Integer) arr.get(0).get("t_id") + 1;
	}

	/**
	 * 存储订单记录
	 * 
	 * @param t_id          订单号
	 * @param consume消费者
	 * @param cover_consume 被消费者
	 * @param consume_score 消费资源数据编号
	 * @param consume_type  消费类型
	 * @param amount        消费金额
	 */
	private void saveOrder(int t_id, int consume, int cover_consume, int consume_score, int consume_type,
			BigDecimal amount) {

		String sql = "INSERT INTO t_order (t_id,t_consume, t_cover_consume, t_consume_type, t_consume_score, t_amount, t_create_time) VALUES (?, ?, ?, ?, ?, ?, ?)";

		this.getFinalDao().getIEntitySQLDAO().executeSQL(sql, t_id, consume, cover_consume, consume_type, consume_score, amount,
				DateUtils.format(new Date(), DateUtils.FullDatePattern));
	}
	
}
