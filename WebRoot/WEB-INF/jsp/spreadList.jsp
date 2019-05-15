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
<title>代理管理</title>
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
		<input type="hidden" id="authority" value="${authority}">
		<input type="hidden" id="t_spread_id" value="${spreadLog.t_user_id}">
			<div class="agile-grids">
			   <c:if test="${authority !=2}">
			    <button class="btn btn-default" onclick="on_add_spread();">添加渠道</button>
			   </c:if>
			    <br/>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<table id="utable" class="table table-hover"></table>
					</div>
				</div>
			</div>
		</div>
		<!-- 添加代理（Modal） -->
		<div class="modal fade" id="add_spread_channel" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 45%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-th-list">&nbsp;添加渠道</span>
						  <input id="t_id" type="hidden">
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">登陆账号:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span>
									<input type="text" id="loguser"	class="form-control1"  placeholder="请输入登陆账号.." onfocus="on_focus('loguser','请输入登陆账号..');" onblur="on_blur('loguser')" >
								</div>
							</div>
							<div class="col-sm-2" id="loguser_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">登陆密码:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-link"></i>
									</span> 
									<input type="password" id="logpwd" class="form-control1"  placeholder="请输入登陆密码.." onfocus="on_focus('logpwd','请输入登陆密码..');" onblur="on_blur('logpwd')">
								</div>
							</div>
							<div class="col-sm-2" id="logpwd_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div class="form-group">
							<label class="col-md-2 control-label">角色:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-adjust"></i>
									</span>
									 <select id="t_role_id" style="height: 40px;" class="form-control">
					                    
			                   		 </select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">金币提成:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> 
									   <i class="glyphicon glyphicon-superscript"></i>
									</span> 
									<input type="text" id="t_gold_proportions" class="form-control1"  placeholder="请输金币充值分成比例..(无需输入%)" onfocus="on_focus('t_gold_proportions','请输金币充值分成比例..');" onblur="on_blur('t_gold_proportions')">
								</div>
							</div>
							<div class="col-sm-2" id="t_gold_proportions_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<br/>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">VIP提成:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> 
									  <i class="glyphicon glyphicon-subscript"></i>
									</span> 
									<input type="text" id="t_vip_proportions" class="form-control1"  placeholder="请输入VIP分成比例..(无需输入%)" onfocus="on_focus('t_vip_proportions','请输入VIP分成比例..');" onblur="on_blur('t_vip_proportions')">
								</div>
							</div>
							<div class="col-sm-2" id="t_vip_proportions_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-default" onclick="on_click_add_spread();">
							<span class="glyphicon glyphicon-floppy-disk"></span>保存
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- CPS申请人结算信息（Modal） -->
		<div class="modal fade" id="spread_settlement_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 55%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-piggy-bank">渠道推广结算信息</span>
						</h4>
					</div>
					<div class="modal-body">
						<button class="btn btn-default">推广列表</button>
						 <div class="agile-tables">
							<div class="w3l-table-info">
								<table id="spread_table" class="table table-hover"></table>
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
		<!-- 主播列表 -->
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
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/spreadList.js"></script>

</body>
</html>
