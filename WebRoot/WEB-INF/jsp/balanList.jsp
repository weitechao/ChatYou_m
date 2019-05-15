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
<title>余额排行榜</title>
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
			<div style="float: right;width: 15%">
			     	<button class="btn btn-default" style="float: left;">性&nbsp;&nbsp;&nbsp;&nbsp;别:</button>
			     	<select id="t_sex" onchange="on_sex_onchange();"  style="width: 100px;float: left;height: 36px;margin-right: 2%" class="form-control">
                      <option value="-1" >全部</option>
                      <option value="0">女</option>
                      <option value="1">男</option>
					</select>
			    </div>
			    </div>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<table id="utable" class="table table-hover"></table>
					</div>
				</div>
			</div>
		<!-- 头像预览 -->
		<div class="modal fade" id="img_preview" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-picture"></span> 头像预览
						</h4>
					</div>
					<div class="modal-body">
						<div>
							<div class="agile-gallery-grid">
								<div class="agile-gallery">
								   <img id="hand_img" src="${pageContext.request.contextPath }/images/g4.jpg" alt="" />
								</div>
							</div>
						</div>
						</br>
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
		<div class="modal fade" id="spread_user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 56%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-picture"></span> 推广用户
						</h4>
						<input type="hidden" id="t_referee">
					</div>
					</br>
					<div style="float: right;width: 68%">
			        <span class="btn btn-default" style="float: left;">开始日期:</span>
					<div class="input-group"  style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="anthor_begin_time" style="z-index:100000" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th"></span>
					    </div>
					</div>
					<span class="btn btn-default" style="float: left;">结束时间:</span>
					<div class="input-group" style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="anthor_end_time" style="z-index:100000" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
					<button class="btn btn-default" onclick="search_anthor_time();" style="float: right;margin-right:20px;"><i class="fa fa-search" aria-hidden="true"></i></button>
			    </div>
					<div class="agile-tables">
						<div class="w3l-table-info">
							<table id="anthorTable" class="table table-hover" style="width: 98%;margin-left: 10px;"></table>
						</div>
				    </div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color: #87CEFA;">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<jsp:include page="/public/financialdetails.jsp"/>
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/balanList.js"></script>

</body>
</html>
