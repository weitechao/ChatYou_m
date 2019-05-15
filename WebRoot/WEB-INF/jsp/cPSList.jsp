<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<head>
<title>CPS联盟管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<script type="text/javascript">
var path = '<%=basePath%>';
</script>
<!-- //tables -->
</head>
<body class="dashboard-page">

	<jsp:include page="/public/menu.jsp" />
	<section class="wrapper scrollable">
		<section class="title-bar">
			 <jsp:include page="/public/top.jsp" />
		</section>
		<div class="main-grid">
			<div class="agile-grids">
			    <button class="btn btn-default">CPS联盟</button>
			     <div style="float: right;width: 21%">
					<button class="btn btn-default" style="float: left;">条件:</button>
					<input type="text" class="form-control" id="t_cps_name" style="height: 36px; float: left;width: 250px;margin-right: 2%"  placeholder="渠道名称">
					<button class="btn btn-default" onclick="on_click_search();" style="float: right;margin-right: 0;"><i class="fa fa-search" aria-hidden="true"></i></button>
				</div>
			    </br>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<table id="utable" class="table table-hover"></table>
					</div>
				</div>
			</div>
		</div>
		<!-- CPS申请人信息（Modal） -->
		<div class="modal fade" id="cps_admin_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-user">CPS管理者</span>
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">真实姓名:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="t_real_name"
										class="form-control1"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">联系电话:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-phone-alt"></i>
									</span> <input type="text" id="t_phone" 
										class="form-control1"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- CPS申请人结算信息（Modal） -->
		<div class="modal fade" id="cps_settlement_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-piggy-bank">CPS管理者结算信息</span>
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">结算姓名:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="settlement_name"
										class="form-control1"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">结算方式:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-link"></i>
									</span> 
									<input type="text" id="t_settlement_type" class="form-control1"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">结算账号:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-sort-by-alphabet-alt"></i>
									</span> 
									<input type="text" id="t_bank" class="form-control1"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- cps申请信息回显（Modal） -->
		<div class="modal fade" id="cps_update_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 45%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span> 审核CPS
						  <input type="hidden" id="t_cps_id">
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="t_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">申请姓名:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-queen"></i>
									</span> <input type="text" id="update_name"
										class="form-control1"  placeholder="请输入CPS申请者真实姓名." onfocus="on_focus('update_name','请输入CPS申请者真实姓名.');" onblur="on_blur('update_name')">
								</div>
							</div>
							<div class="col-sm-2" id="update_name_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">联系电话:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-phone"></i>
									</span> <input type="text" id="update_phone"
										class="form-control1"  placeholder="请输入CPS申请者联系电话." onfocus="on_focus('update_phone','请输入CPS申请者联系电话.');" onblur="on_blur('update_phone')">
								</div>
							</div>
							<div class="col-sm-2" id="update_phone_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">CPS联盟名称:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-globe"></i>
									</span> <input type="text" id="update_cps_name"
										class="form-control1"  placeholder="请输入CPS联盟名称." onfocus="on_focus('update_cps_name','请输入CPS联盟名称..');" onblur="on_blur('update_cps_name')">
								</div>
							</div>
							<div class="col-sm-2" id="update_cps_name_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">CPS联盟网址:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon">
									  <i	class="glyphicon glyphicon-refresh"></i>
									</span> 
									<input type="text" id="update_cps" class="form-control1"  placeholder="请输入CPS联盟推广网址." >
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">提现方式:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> 
									   <i class="glyphicon glyphicon-piggy-bank"></i>
									</span>
									<select id="upate_settlement_type" style="height: 40px;" class="form-control">
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
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">提现账号:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-paperclip"></i>
									</span> 
									<input type="text" id="update_bank" class="form-control1" placeholder="请输入提现账号." onfocus="on_focus('update_bank','请输入提现账号.');" onblur="on_blur('update_bank')">
								</div>
							</div>
							<div class="col-sm-2" id="update_bank_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">预估活跃(万):</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-hand-up"></i>
									</span> 
									<input type="text" id="update_active_user" class="form-control1" placeholder="请输入预估活跃用户." onfocus="on_focus('update_active_user','请输入预估活跃用户.');" onblur="on_blur('update_active_user')">
								</div>
							</div>
							<div class="col-sm-2" id="update_active_user_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">分成比例(%):</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon">
									  <i class="glyphicon glyphicon-xbt"></i>
									</span> 
									<input type="text" id="update_proportions" class="form-control1" placeholder="请输入分成比例,无需%" onfocus="on_focus('update_proportions','请输入分成比例,无需%');" onblur="on_blur('update_proportions')">
								</div>
							</div>
							<div class="col-sm-2" id="update_proportions_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_examine_success_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>审核通过
						</button>
						<button type="button" class="btn btn-primary" onclick="on_examine_error_submit();">
							<span class="glyphicon glyphicon-floppy-remove"></span>审核失败
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- CPS申请人结算信息（Modal） -->
		<div class="modal fade" id="settlement_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-piggy-bank">CPS管理者结算信息</span>
						</h4>
						<input type="hidden" id="t_cps_settlement">
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">结算姓名:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="settlement_real_name"
										class="form-control1"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">结算方式:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-link"></i>
									</span> 
								    <select id="settlement_type" style="height: 40px;" class="form-control">
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
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">结算账号:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-sort-by-alphabet-alt"></i>
									</span> 
									<input type="text" id="settlement_bank" class="form-control1"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">结算金额:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-usd"></i>
									</span> 
									<input type="text" id="settlement_amount" class="form-control1"  disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">回执单号:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-sort-by-order-alt"></i>
									</span> 
									<input type="text" id="settlement_order_no" class="form-control1" 
									 placeholder="如银行卡转账,请输入银行回执订单号." onfocus="on_focus('settlement_order_no','请输入银行回执订单号.');" onblur="on_blur('settlement_order_no')">
								</div>
							</div>
							<div class="col-sm-2" id="settlement_order_no_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_settlement();">
							<span class="glyphicon glyphicon-floppy-saved"></span>结算
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<!-- 贡献列表 -->
		<div class="modal fade" id="user_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 45%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-user"></span>
						</h4>
					</div>
					<div class="modal-body">
					     <button class="btn btn-default">推广列表</button>
						 <div class="agile-tables">
							<div class="w3l-table-info">
								<table id="user_table" class="table table-hover"></table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<!-- 贡献列表 -->
		<div class="modal fade" id="contribution_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 45%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-user"></span>
						</h4>
					</div>
					<div class="modal-body">
					     <button class="btn btn-default">结算明细</button>
						 <div class="agile-tables">
							<div class="w3l-table-info">
								<table id="contribution_table" class="table table-hover"></table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/cPSList.js"></script>

</body>
</html>
