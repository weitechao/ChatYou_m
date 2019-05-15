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
<title>动态列表</title>
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
<%-- 			    <a class="btn btn-default" href="${pagecontext.request.contextpath}/menu/adduser.htm">点击添加</a> --%>
			    <div style="float: right;width: 55%">
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
							警告
						</h4>
					</div>
					<div class="modal-body">
						确定要进行操作吗？
						<input type="hidden" id="del_id">
						<input type="hidden" id="state">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_change_res();">
							<i class="glyphicon glyphicon-ok"></i>确认
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- 动态图片 -->
		<div class="modal fade" id="cover_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-picture"></span>动态文件预览
						</h4>
					</div>
					<div class="modal-body" id="cover_modal_div">
					</div>
					<div class="modal-footer">
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- 动态内容 -->
		<div class="modal fade" id="dynamic_content" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 35%">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-picture"></span>动态内容
						</h4>
					</div>
					<div class="modal-body" id="dynamicDetails">
					</div>
					<div class="modal-footer">
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<div class="modal fade" id="dynamic_comment_list" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 56%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">
						  <span class="glyphicon glyphicon-picture"></span> 评论列表
						</h4>
						<input type="hidden" id="anthor_id">
					</div>
					</br>
					<div style="float: right;width: 68%">
			        <span class="btn btn-default" style="float: left;">开始日期:</span>
					<div class="input-group"  style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="comment_begin_time" style="z-index:100000" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th"></span>
					    </div>
					</div>
					<span class="btn btn-default" style="float: left;">结束时间:</span>
					<div class="input-group" style="width: 200px;float: left;margin-right: 20px;">
					    <input type="text" class="form-control" id="comment_end_time" style="z-index:100000" style="height: 36px;">
					    <div class="input-group-addon">
					        <span class="glyphicon glyphicon-th "></span>
					    </div>
					</div>
					<button class="btn btn-default" onclick="search_anthor_time();" style="float: right;margin-right:20px;"><i class="fa fa-search" aria-hidden="true"></i></button>
			    </div>
					<div class="agile-tables">
						<div class="w3l-table-info">
							<table id="commentTable" class="table table-hover" style="width: 98%;margin-left: 10px;"></table>
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
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/dynamicList.js"></script>

</body>
</html>
