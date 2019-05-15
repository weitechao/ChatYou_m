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
<title>Tables</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
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
				<button class="btn btn-default" >财务记录</button>
				<button class="btn btn-default" onclick="on_click_search();" style="float: right;margin-right: 0;"><i class="fa fa-search" aria-hidden="true"></i></button>
				<div style="float: right;">
					<span class="btn btn-default" style="float: left;">开始日期:</span>
					<div class="input-group" style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="beginTime" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
					<span class="btn btn-default">结束时间:</span>
					<div class="input-group" style="width: 200px;float: right;margin-right: 20px;">
					    <input type="text" class="form-control" id="endTime" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
				</div>
				<br/>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<table id="utable" class="table table-hover"></table>
					</div>
				</div>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<span class="btn btn-default"
							style="float: right;margin-right: 3%;background-color: #2ec9de">总支出:￥<span
							id="pay">5000</span></span>
					</div>
				</div>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<span class="btn btn-default"
							style="float: right;margin-right: 3%;background-color: #2ec9de">总收益:￥<span
							id="profit">5000</span></span>
					</div>
				</div>
			</div>
		</div>
		<!-- 预览图 -->
		<jsp:include page="/public/lower.jsp" />
	</section>
</body>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script	src="${pageContext.request.contextPath}/javascript/financialDetailsList.js"></script>

</html>
