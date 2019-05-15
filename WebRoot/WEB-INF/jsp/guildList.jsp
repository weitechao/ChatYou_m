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
<title>公会管理</title>
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
			    <button class="btn btn-default">公会列表</button>
			     <div style="float: right;width: 21%">
					<button class="btn btn-default" style="float: left;">条件:</button>
					<input type="text" class="form-control" id="condition" style="height: 36px; float: left;width: 250px;margin-right: 2%"  placeholder="公会名称">
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
		<!-- 公会回显模拟框（Modal） -->
		<div class="modal fade" id="examineModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 45%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span> 审核
						  <input type="hidden" id="t_ex_id">
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="t_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">公会名称:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-queen"></i>
									</span> <input type="text" id="t_guild_name"
										class="form-control1"  placeholder="请输入公会名称." onfocus="on_focus('t_guild_name','请输入公会名称.');" onblur="on_blur('t_guild_name')">
								</div>
							</div>
							<div class="col-sm-2" id="t_guild_name_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">管&nbsp;&nbsp;理&nbsp;&nbsp;员:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="t_admin_name"
										class="form-control1"  placeholder="" onfocus="on_focus('t_admin_name','请输入公会管理者姓名.');" onblur="on_blur('t_admin_name')">
								</div>
							</div>
							<div class="col-sm-2" id="t_admin_name_validate">
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
									</span> <input type="text" id="t_admin_phone" 
										class="form-control1"  placeholder="" onfocus="on_focus('t_admin_phone','请输入公会管理者联系方式.');" onblur="on_blur('t_admin_phone')">
								</div>
							</div>
							<div class="col-sm-2" id="t_admin_phone_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">提成费率:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-yen"></i>
									</span> <input type="text" id="t_extract"
										class="form-control1"  value="10" placeholder="请输入提成比例,无需输入%" onfocus="on_focus('t_extract','请输入提成比例.');" onblur="on_blur('t_extract')">
								</div>
							</div>
							<div class="col-sm-2" id="t_extract_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_modal_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 公会Id -->
		<input type="hidden" id="t_guild_id">
		<!-- 主播列表 -->
		<div class="modal fade" id="anchor_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 45%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span> 主播列表
						</h4>
					</div>
					<div class="modal-body">
					     <button class="btn btn-default">公会主播列表</button>
					      <div style="float: right;width: 53%">
				   			<a class="btn btn-default" style="float: right;"	href="javascript:on_click_join();">点击加入</a> </br>
			              </div>
						 <div class="agile-tables">
							<div class="w3l-table-info">
								<table id="anchor_table" class="table table-hover"></table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_modal_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<!-- 添加主播（Modal） -->
		<div class="modal fade" style="z-index: 1500" id="anchor_join_Modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 45%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span> 邀请
						</h4>
					</div>
					<div class="modal-body">
						 <div class="form-group" style="height: 150px;">
							<label class="col-md-2 control-label">主播IdCard:</label>
							<div class="col-md-8">
								<div class="input-group input-icon right">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-indent-left"></i>
									</span>
									<textarea id="t_anchors" 
										class="form-control" rows="6" placeholder="请输入主播IdCard号,多个组播以,分割!" onfocus="on_focus('t_anchors','请输入要加入的主播宜聊号.');" onblur="on_blur('t_anchors')"></textarea>
								</div>
							</div>
							<div class="col-sm-2" id="t_anchors_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_anchor_join_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<!-- 回显认证资料模拟框（Modal） -->
		<div class="modal fade" id="load_admin_data" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 38%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span>
						</h4>
					</div>
					<div class="modal-body">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">管理员姓名:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-queen"></i>
									</span> <input type="text" id="load_admin_name"
										class="form-control1" disabled="disabled">
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">认证身份证:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="load_idcard"
										class="form-control1" disabled="disabled" >
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 300px;">
							<label class="col-md-2 control-label">认证人头像:</label>
							<div class="col-md-8">
								<div class="input-group">
									<img id="load_hand_img" style="margin-left: 20px;" alt="" src="" width="400px">
								</div>
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
    <script src="${pageContext.request.contextPath}/javascript/guildList.js"></script>

</body>
</html>
