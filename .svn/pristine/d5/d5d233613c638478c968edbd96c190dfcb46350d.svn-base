<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<title>渠道推广</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content=""/>
<jsp:include page="/public/title.jsp" />
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/javascript/addSpread.js"></script>
<script type="text/javascript">
var path = '${pageContext.request.contextPath}';
</script>
</head>
<body class="dashboard-page">
	<jsp:include page="/public/menu.jsp" />
	<section class="wrapper scrollable">
	    <section class="title-bar">
			 <jsp:include page="/public/top.jsp" />
		</section>
		<div class="main-grid">
			<div class="agile-grids">	
				<!-- input-forms -->
				<div class="grids">
					<div class="panel panel-widget forms-panel w3-last-form">
						<div class="forms">
							<div class="form-three widget-shadow">
								<div class=" panel-body-inputin">
									<form class="form-horizontal" id="myFrom" action="${pageContext.request.contextPath}/admin/saveUser.htm" method="post">
										<input type="hidden" id="t_id" name = "t_id">
										<div class="form-group">
											<label class="col-md-2 control-label">登陆账号:</label>
											<div class="col-md-8">
												<div class="input-group">							
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-user"></i>
													</span>
													<input type="text" id="loginname" name="loginname" disabled="disabled" class="form-control1"  placeholder="请输入登陆账号." >
												</div>
											</div>
											<div class="col-sm-2" id="t_id_card_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">登陆密码:</label>
											<div class="col-md-8">
												<div class="input-group">							
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-lock"></i>
													</span>
													<input type="password" id="loginpwd" name="loginpwd"  class="form-control1" placeholder="请输入您的登陆密码">
												</div>
											</div>
											<div class="col-sm-2" id="t_id_card_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">手机号码:</label>
											<div class="col-md-8">
												<div class="input-group">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-phone"></i>
													</span>
													<input type="text" id="phone" name="t_phone" class="form-control1"  placeholder="请输入手机号码">
												</div>
											</div>
											<div class="col-sm-2" id="phone_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">联系&nbsp;Q&nbsp;&nbsp;Q:</label>
											<div class="col-md-8">
												<div class="input-group">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-sort-by-alphabet"></i>
													</span>
													<input type="text" id="t_qq" name="t_qq"  class="form-control1"  placeholder="请输入联系QQ.">
												</div>
											</div>
											<div class="col-sm-2" id="t_qq_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">联系微信:</label>
											<div class="col-md-8">
												<div class="input-group">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-sort-by-alphabet-alt"></i>
													</span>
													<input type="text" id="t_weixin" name="t_weixin" class="form-control1"  placeholder="请输入联系微信">
												</div>
											</div>
											<div class="col-sm-2" id="phone_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">提现方式:</label>
											<div class="col-md-8">
												<div class="input-group">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-adjust"></i>
													</span>
													 <select id="t_settlement_type" style="height: 40px;" name="t_settlement_type" class="form-control">
									                      <option value="0">支付宝</option>
									                      <option value="1">中国工商银行</option>
									                      <option value="2">中国农业银行</option>
									                      <option value="3">中国银行</option>
									                      <option value="4">中国建设银行</option>
							                   		 </select>
												</div>
											</div>
											<div class="col-sm-2">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">提现账号:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<input type="text" id="t_bank" name="t_bank" class="form-control1" id="exampleInputPassword1" placeholder="提现账号" >
												</div>
											</div>
											<div class="col-sm-2" id="age_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">金币分成比例:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<input type="text" id="t_gold_proportions" name="t_gold_proportions" disabled="disabled" class="form-control1" id="exampleInputPassword1">
												</div>
											</div>
											<div class="col-sm-2" id="t_gold_proportions">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">VIP分成比例:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-indent-left"></i>
													</span>
													<input type="text" id="t_vip_proportions" name="t_vip_proportions" disabled="disabled" class="form-control1" id="exampleInputPassword1">
												</div>
											</div>
											<div class="col-sm-2" id="age_validate">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">推荐人ID号:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-arrow-up"></i>
													</span>
													<input type="text" id="t_spread_id" name="t_spread_id"  disabled="disabled" class="form-control1">
												</div>
											</div>
											<div class="col-sm-2">
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">短连接URL:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-arrow-up"></i>
													</span>
													<input type="text" id="t_short_url" name="t_short_url"  disabled="disabled" class="form-control1">
												</div>
											</div>
											<div class="col-sm-2" >
												<button type="button" class="btn btn-default" onclick="on_click_reset_url();">
													重置链接
												</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">创建时间:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right">
													<span class="input-group-addon">
														<i class="glyphicon glyphicon-th-large"></i>
													</span>
													<input type="text" id="t_create_time" disabled="disabled" name="t_create_time" class="form-control1" >
												</div>
											</div>
											<div class="col-sm-2"  id="t_weight_validate" >
												<p class="help-block" style="color: red;">*</p>
											</div>
										</div>
										<div  class="grid_3 grid_5 wow fadeInUp animated" data-wow-delay=".5s" style="margin-left: 75%;">
											<h4>
												<a href="javascript:void(0);" onclick="on_click_submit();"><span class="label label-success"><i class="glyphicon glyphicon-floppy-save"></i>&nbsp;&nbsp;保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存</span></a>
											</h4>
										</div>
									
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- //input-forms -->
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="/public/lower.jsp" />
		<!-- //footer -->
	</section>
</body>
</html>

