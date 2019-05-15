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
<title>登陆设置</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<!-- //font-awesome icons -->
<script>
	var path = '<%=basePath%>';
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
				<div class="agile-tables">
					<h3 class="card-title" style="float: left;">登陆设置</h3>
					<button class="btn btn-default"
						style="float: right;background-color: #87CEFA" data-toggle="modal"
						data-target="#myModal">点击添加</button>
				</div>
				<div class="w3l-table-info">
					<table class="table table-hover">
						<tr>
							<th>appId</th>
							<th>AppSecret</th>
							<th>类别</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${data}" var="item">
							<tr>
								<td>${item.t_app_id}</td>
								<td>${item.t_app_secret}</td>
								<td>${item.t_type == 0? 'QQ互联':'微信互联'}</td>
								<td>
								  <c:if test="${item.t_state == 0}">
										<a href="javascript:void(0);" style="color: red;"  onclick="on_click_update_state(this,${item.t_id});" >禁用</a>
									</c:if>
									<c:if test="${item.t_state == 1}">
										<a href="javascript:void(0);" style="color: #00EC00;" onclick="on_click_update_state(this,${item.t_id});">启用</a>
									</c:if>
								</td>
								<td>
								   <button class="btn btn-default"	style="background-color: #87CEFA;height: 25px; line-height: 0.5" onclick="on_click_open_updae_model(${item.t_id});">修改</button>|
								   <button class="btn btn-default"	style="background-color: #BEBEBE;height: 25px; line-height: 0.5;color: 	#ff7575;" onclick="on_click_open_model('myDel',${item.t_id},'del_id');">删除</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<!-- //tables -->
		</div>
		</div>

		<!-- 新增或修改模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 60%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span> 添加或修改
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="t_id">
						<div style="height: 40px;">
							<label class="col-md-2 control-label">APPID:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-grain"></i>
									</span> <input type="text" id="t_app_id" name="t_app_id"
										class="form-control1" placeholder="请出入APPID,请在云平台中获取.">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">AppSecret:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-pushpin"></i>
									</span> <input type="text" id="t_app_secret" name="t_app_secret"
										class="form-control1" placeholder="请出入AppSecret,请在云平台中获取.">
								</div>
							</div>
							<div class="col-sm-1">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">是否启用:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-random"></i>
									</span> <select id="t_state" name="t_state" class="form-control">
										<option value="0">是</option>
										<option value="1">否</option>
									</select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div style="height: 40px;">
							<label class="col-md-2 control-label">平台类别:</label>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-tasks"></i>
									</span> <select id="t_type" name="t_type" class="form-control">
										<option value="0">QQ互联</option>
										<option value="1">微信互联</option>
									</select>
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
						<button type="button" class="btn btn-primary" onclick="on_click_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 删除模态框（Modal） -->
		<div class="modal fade" id="myDel" tabindex="-1" role="dialog" aria-labelledby="myDelLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 18%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color:#FFD700;">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title " id="myModalLabel">
						   <span class="glyphicon glyphicon-warning-sign"></span>
							删除提示
						</h4>
					</div>
					<div class="modal-body">
						确定要删除数据吗？删除后无法恢复.
						<input type="hidden" id="del_id">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_del();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- footer -->
		<jsp:include page="/public/lower.jsp" />
		<!-- //footer -->
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/javascript/loginSetUpList.js"></script>

</body>
</html>
