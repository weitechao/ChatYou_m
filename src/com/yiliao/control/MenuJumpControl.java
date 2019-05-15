package com.yiliao.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 菜单跳转Control
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/menu")
public class MenuJumpControl {

	/**
	 * 跳转到仪表盘
	 * 
	 * @return
	 */
	@RequestMapping("jumpHome")
	public String jumpHome() {
		return "home";
	}

	/**
	 * 跳转添加用户
	 * 
	 * @return
	 */
	@RequestMapping("addUser")
	public ModelAndView addUser() {
		return new ModelAndView("addUser");
	}

	/**
	 * 跳转到用户列表
	 * 
	 * @return
	 */
	@RequestMapping("userList")
	public ModelAndView userList() {
		return new ModelAndView("userList");
	}

	/**
	 * 跳转到封号页面
	 * 
	 * @return
	 */
	@RequestMapping("jumpFreezeList")
	public ModelAndView jumpFreezeList() {
		return new ModelAndView("freezeList");
	}

	/**
	 * 跳转到禁用页面
	 * 
	 * @return
	 */
	@RequestMapping("disableUserList")
	public ModelAndView jumpDisableUserList() {

		return new ModelAndView("disableUserList");
	}

	/**
	 * 跳转到头像列表
	 * 
	 * @return
	 */
	@RequestMapping("jumpHandList")
	public ModelAndView jumpHandList() {

		return new ModelAndView("handList");
	}

	/**
	 * 跳转到相册列表
	 * 
	 * @return
	 */
	@RequestMapping("jumpPhotoList")
	public ModelAndView jumpPhotoList(int userId) {

		ModelAndView mv = new ModelAndView("photoList");

		mv.addObject("userId", userId);

		return mv;
	}

	/**
	 * 跳转到广告管理页面
	 * 
	 * @return
	 */
	@RequestMapping("jumpBanner")
	public ModelAndView jumpBanner() {

		return new ModelAndView("bannerList");
	}

	/**
	 * 跳转到礼物管理列表
	 * 
	 * @return
	 */
	@RequestMapping("jumpGiftList")
	public ModelAndView jumpGiftList() {
		return new ModelAndView("giftList");
	}

	/**
	 * 跳转到封面审核列表
	 * 
	 * @return
	 */
	@RequestMapping("jumpCoverList")
	public ModelAndView jumpCoverList() {

		return new ModelAndView("coverList");

	}
	
	/**
	 * 跳转到模糊审核
	 * @return
	 */
	@RequestMapping("jumpVagueList")
	public ModelAndView jumpVagueList(){
		return new ModelAndView("vagueList");
	}
	
	/**
	 * 跳转到私密相册审核
	 * @return
	 */
	@RequestMapping("jumpPrivatePhotoList")
	public ModelAndView jumpPrivatePhotoList(){
		return new ModelAndView("privatePhotoList");
	}
	
	/**
	 * 跳转到实名认证列表
	 * @return
	 */
	@RequestMapping("jumpidCardList")
	public ModelAndView jumpidCardList(){
		return new ModelAndView("idCardList");
	}
	
	/**
	 * 跳转到举报列表
	 * @return
	 */
	@RequestMapping("jumpReportList")
	public ModelAndView jumpReportList(){
		return new ModelAndView("reportList");
	}

	
	/**
	 * VIP套餐
	 * @return
	 */
	@RequestMapping("jumpVipSetMealList")
	public ModelAndView jumpVipSetMealList(){
		return new ModelAndView("vipSetMealList");
	}
	
	/**
	 * 跳转到充值与提现列表
	 * @return
	 */
	@RequestMapping("jumpCashValueSetMealList")
	public ModelAndView jumpCashValueSetMealList(){
		return new ModelAndView("cashValueSetMealList");
	}
	
	
	/**
	 * 跳转到意见反馈
	 * @return
	 */
	@RequestMapping("jumpFeedbackList")
	public ModelAndView jumpFeedbackList(){
		return new ModelAndView("feedbackList");
	}
	
	/**
	 * 跳转到系统设置
	 * @return
	 */
	@RequestMapping("jumpSpreadSetUp")
	public ModelAndView jumpSpreadSetUp(){
	   return new ModelAndView("spreadSetUp");
	}
	
	/**
	 * 跳转到充值列表
	 * @return
	 */
	@RequestMapping("jumpRechargeList")
	public ModelAndView jumpRechargeList(){
		return  new ModelAndView("rechargeList");
	}
	/**
	 * 跳转到消费列表
	 * @return
	 */
	@RequestMapping("jumpConsumeList")
	public ModelAndView jumpConsumeList(){
		return new ModelAndView("consumeList");
	}
	
	/**
	 * 跳转到提现列表
	 * @return
	 */
	@RequestMapping("jumpPutForwardList")
	public ModelAndView jumpPutForwardList(){
		return new ModelAndView("putForwardList");
	}
	
	/**
	 * 跳转到财务明细
	 * @return
	 */
	@RequestMapping("jumpFinancialDetails")
	public ModelAndView jumpFinancialDetails(){
		return new ModelAndView("financialDetailsList");
	}
	/**
	 * 跳转到账号管理
	 * @return
	 */
	@RequestMapping("jumpAdminList")
	public ModelAndView jumpAdminList(){
		return new ModelAndView("adminList");
	}
	
	/**
	 * 跳转到角色管理
	 * @return
	 */
	@RequestMapping("jumpRoleList")
	public ModelAndView jumpRoleList(){
		return new ModelAndView("roleList");
	}
	
	/**
	 * 跳转到菜单列表
	 * @return
	 */
	@RequestMapping("jumpMenuList")
	public ModelAndView jumpMenuList(){
		return new ModelAndView("menuList");
	}
	
	/**
	 * 跳转到封号设置列表
	 * @return
	 */
	@RequestMapping("jumpBannedList")
	public ModelAndView jumpBannedList(){
		return new ModelAndView("bannedList");
	}
	
	/**
	 * 跳转到版本控制器
	 * @return
	 */
	@RequestMapping("jumpVersion")
	public ModelAndView jumpVersion(){
		return new ModelAndView("versionList");
	}
	
	/**
	 * 跳转到红包列表
	 * @return
	 */
	@RequestMapping("jumpActivityList")
	public ModelAndView jumpActivityList(){
		return new ModelAndView("activityList");
	}
	
	/**
	 * 跳转到奖励明细
	 * @return
	 */
	@RequestMapping("jumpRewardDetail")
	public ModelAndView jumpRewardDetail(){
		return new ModelAndView("rewardDetailList");
	}
	
	/**
	 * 跳转到推送消息列表
	 * @return
	 */
	@RequestMapping("jumpPushMsgList")
	public ModelAndView jumpPushMsgList(){
		
		return new ModelAndView("pushMsgList");
	}
	
	/**
	 * 注册红包
	 * @return
	 */
	@RequestMapping("jumpRegisterRedBag")
	public ModelAndView jumpRegisterRedBag(){
		                      
		return new ModelAndView("registerRedBagList");
	}
	
	/**
	 * 跳转到公会
	 * @return
	 */
	@RequestMapping("jumpGuildList")
	public ModelAndView jumpGuildList(){
		return new ModelAndView("guildList");
	}
	
	/**
	 * 跳转到CPS联盟列表
	 * @return
	 */
	@RequestMapping("jumpCPSList")
	public ModelAndView jumpCPSList(){
	
		return new ModelAndView("cPSList");
	}
	
	/**
	 * 获取排行榜设置
	 * @return
	 */
	@RequestMapping("jumpRankingList")
	public ModelAndView jumpRanking(){
		
		return new ModelAndView("rankList");
	}
	
	/**
	 * 跳转到微信设置
	 * @return
	 */
	@RequestMapping("jumpWeiXinPaySetUp")
	public ModelAndView jumpWeiXinPaySetUp(){
		return new ModelAndView("weiXinPaySetUp");
	}
	
	/**
	 * 跳转到风格切换
	 * @return
	 */
	@RequestMapping("jumpStyleSetUp")
	public ModelAndView jumpStyleSetUp() {
		return new ModelAndView("styleSetUp");
	}
	
	/**
	 * 跳转到推广奖励列表
	 * @return
	 */
	@RequestMapping("jumpSpreadAward")
	public ModelAndView jumpSpreadAward() {
		
		return new ModelAndView("spreadAward");
	}
	
	/**
	 * 跳转到IM消息记录列表
	 * @return
	 */
	@RequestMapping(value="jumpImLogList",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView jumpImLogList() {
		
		return new ModelAndView("imLogList");
	}
	
	/**
	 * 推广用户排行榜
	 * @return
	 */
	@RequestMapping("jumpSpreadUserList")
	public ModelAndView jumpSpreadUserList() {
		return new ModelAndView("spreadUser");
	}
	/**
	 *  在线时长排行榜
	 * @return
	 */
	@RequestMapping("jumpOnLineTime")
	public ModelAndView jumpOnLineTime() {
		
		return new ModelAndView("onLineTime");
	}
	/**
	 *	  消费排行榜
	 * @return
	 */
	@RequestMapping("jumpConsumeNoticeList")
	public ModelAndView jumpConsumeNoticeList() {
		
		return new ModelAndView("consumeNoticeList");
	}
	/**
	 * 提现排行榜
	 * @return
	 */
	@RequestMapping("jumpExtractMoney")
	public ModelAndView jumpExtractMoney() {
		
		return new ModelAndView("extractMoney");
	}
	/**
	 * 跳转到地图
	 * @return
	 */
	@RequestMapping("jumpMap")
	public ModelAndView jumpMap() {
		return new ModelAndView("userMap");
	}
	/**
	 * 跳转到渠道推荐列表
	 * @return
	 */
	@RequestMapping("jumpSpread")
	public ModelAndView jumpSpread() {
		return new ModelAndView("spreadList");
	}
	/**
	 * 渠道推广个人信息维护
	 * @return
	 */
	@RequestMapping("jumpAddSpread")
	public ModelAndView jumpAddSpread() {
		return new ModelAndView("addSpread");
	}
	
	/**
	 * 跳转到系统设置
	 * @return
	 */
	@RequestMapping("jumpSystemSetUp")
	public ModelAndView jumpSystemSetUp() {
		return new ModelAndView("systemSetUp");
	}
	
	/**
	 * 跳转到支付宝设置
	 * @return
	 */
	@RequestMapping("jumpAlipaySetUp")
	public ModelAndView jumpAlipaySetUp() {
		return new ModelAndView("alipaySetUp");
	}
	
	/**
	 * 跳转到模拟消息列表
	 * @return
	 */
	@RequestMapping("jumpSimulationList")
	public ModelAndView jumpSimulationList() {
		return new ModelAndView("simulationList");
	}
	/**
	 * 跳转到域名池
	 * @return
	 */
	@RequestMapping("jumpDomainList")
	public ModelAndView jumpDomainList() {
		return new ModelAndView("domainList");
	}
	
	/**
	 * 动态审核
	 * @return
	 */
	@RequestMapping("jumpDynamicList")
	public ModelAndView jumpDynamicList() {
		return new ModelAndView("dynamicList");
	}
	
	/**
	 * 跳转到评论列表
	 * @return
	 */
	@RequestMapping("jumpCommentList")
	public ModelAndView jumpCommentList() {
		return new ModelAndView("commList");
	}
	
	/**
	 * 跳转到余额排行榜
	 * @return
	 */
	@RequestMapping("jumpBalanList")
	public ModelAndView jumpBalanList() {
		return new ModelAndView("balanList");
	}
	
	/**
	 * 充值排行榜
	 * @return
	 */
	@RequestMapping("jumpBankRankingList")
	public ModelAndView jumpRankingList() {
		return new ModelAndView("rankingList");
	}
	/**
	 * 跳转到赠送列表
	 * @return
	 */
	@RequestMapping("jumpGiveList")
	public ModelAndView jumpGiveList() {
		return new ModelAndView("giveList");
	}
	/**
	 * 跳转速配列表
	 * @return
	 */
	@RequestMapping(value = {"jumpSpeedDatingList"})
	public ModelAndView jumpSpeedDatingList() {
		return new ModelAndView("speedDatingList");
	}
	
	/**
	 * 跳转到渠道分享图片列表
	 * @return
	 */
	@RequestMapping(value = {"jumpSpreadImgList"})
	public ModelAndView jumpSpreadImgList() {
		return new ModelAndView("spreadImgList");
	}
	
	/**
	 * 跳转到速配管理
	 * @return
	 */
	@RequestMapping(value = {"jumpSpredManageList"})
	public ModelAndView jumpSpredManageList() {
		return new ModelAndView("spredManageList");
	}
	
	/**
	 * 跳转到帮助列表
	 * @return
	 */
	@RequestMapping(value = {"jumpHelpCenter"})
	public ModelAndView jumpHelpCenter() {
		return new ModelAndView("helpCenterList");
	}
	/**
	 * 我的收益
	 * @return
	 */
	@RequestMapping(value = {"jumpMyProfit"})
	public ModelAndView jumpMyProfit() {
		return new ModelAndView("myProfitList");
	}
}
