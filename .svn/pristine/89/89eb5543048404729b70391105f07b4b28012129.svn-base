<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<head>
<title>推送消息列表</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/fileinput.css" media="all"
	type="text/css" />
<script type="text/javascript">
	var path = '${pageContext.request.contextPath}';
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
				<button class="btn btn-default">消息列表</button>
				<a class="btn btn-default"
					style="float: right; background-color: #87CEFA"
					href="javascript:on_click_open_update_model();">全服推送</a> </br>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<table id="utable" class="table table-hover"></table>
					</div>
				</div>
			</div>
		</div>

		<!-- 新增或修改模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 50%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<span class="glyphicon glyphicon-pencil"></span> 新增或修改 <input
								type="hidden" id="t_id"> <input type="hidden"
								id="img_url">
						</h4>
					</div>
					<div class="modal-body">
						<div class="form-group" style="height: 40px;">
							<label class="col-md-2 control-label">推送类型:</label>
							<div class="col-md-8">
								<div class="input-group input-icon right">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-star"></i>
									</span>
									 <select id="t_user_role" style="height: 40px;" class="form-control">
										<option value="-1">全服推送</option>
										<option value="1">主播会员</option>
										<option value="0">普通会员</option>
									</select>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						<div class="form-group" style="height: 80px;">
							<label class="col-md-2 control-label">推送内容:</label>
							<div class="col-md-8">
								<div class="input-group input-icon right">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-text-width"></i>
									</span>
									<textarea id="push_msg" class="form-control" rows="5"
										placeholder="Enter ..."></textarea>
								</div>
							</div>
							<div class="col-sm-2">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary"
							onclick="on_click_submit();">
							<span class="glyphicon glyphicon-floppy-disk"></span>提交
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
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/javascript/pushMsgList.js"></script>

</body>
</html>
