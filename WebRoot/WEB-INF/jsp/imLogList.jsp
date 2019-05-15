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
<title>IM消息记录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fileinput.css" media="all"	type="text/css" />
<script src="${pageContext.request.contextPath}/js/fileinput.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/fileinput_locale_zh.js" type="text/javascript"></script>
<script>
	var path = '<%=basePath%>';
</script>
</head>
<body class="dashboard-page">
	<jsp:include page="/public/menu.jsp" />
	<section class="wrapper scrollable">
		<section class="title-bar">
			 <jsp:include page="/public/top.jsp"><jsp:param value="user" name="pageName"/></jsp:include>
		</section>
		<div class="main-grid">
			<div class="agile-grids">
			    <div style="float: right;width: 60%">
					<button class="btn btn-default" style="float: left;">条件:</button>
				 	<input type="text" class="form-control" id="condition" style="height: 36px; float: left;width: 200px;margin-right: 2%"  placeholder="请输入手机号或者昵称">
			        <span class="btn btn-default" style="float: left;">开始日期:</span>
					<div class="input-group" style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="beginTime" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
					<span class="btn btn-default" style="float: left;">结束时间:</span>
					<div class="input-group" style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="endTime" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
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
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/imLogList.js"></script>

</body>
</html>
