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
<title>排行榜设置</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<!-- //font-awesome icons -->
 
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
			    <button class="btn btn-default" >排行榜设置</button>
			    </br>
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
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-pencil"></span> 举报处理
						</h4>
					</div>
					<div class="modal-body">
					<input type="hidden" id="t_id">
						<div class="form-group" style="height: 40px;">
							<label class="col-md-3 control-label">魅力榜(加载数量):</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="t_charm_number"
										class="form-control1"  placeholder="请输入APP加载数量" onfocus="on_focus('t_charm_number', '请输入APP加载数量.')"
										onblur="on_blur('t_charm_number')">
								</div>
							</div>
							<div class="col-sm-2" id="t_charm_number_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div class="form-group" style="height: 40px;">
							<label class="col-md-3 control-label">消费榜(加载数量):</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="t_consumption_number" 
										class="form-control1"  placeholder="请输入APP加载数量" onfocus="on_focus('t_consumption_number', '请输入APP加载数量.')"
										onblur="on_blur('t_consumption_number')">
								</div>
							</div>
							<div class="col-sm-2" id="t_consumption_number_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
						<div class="form-group" style="height: 40px;">
							<label class="col-md-3 control-label">豪礼榜(加载数量):</label>
							<div class="col-md-7">
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user"></i>
									</span> <input type="text" id="t_courtesy_number"
										class="form-control1"  placeholder="请输入APP加载数量" onfocus="on_focus('t_courtesy_number', '请输入APP加载数量.')"
										onblur="on_blur('t_courtesy_number')">
								</div>
							</div>
							<div class="col-sm-2" id="t_courtesy_number_validate">
								<p class="help-block" style="color: red;">*</p>
							</div>
						</div>
						</br>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_uprank();">
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
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/rankList.js"></script>

</body>
</html>
