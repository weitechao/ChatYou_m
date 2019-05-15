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
<title>封号</title>
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
			    <button class="btn btn-default">封号列表</button>
			    <div style="float: right;width: 18%">
					<button class="btn btn-default" style="float: left;">条件:</button>
				 	<input type="text" class="form-control" id="condition" style="height: 36px; float: left;width: 200px;margin-right: 2%"  placeholder="请输入手机号或者昵称">
					<button class="btn btn-default" onclick="on_click_search();" style="float: right;margin-right: 0;"><i class="fa fa-search" aria-hidden="true"></i></button>
			    </div>
			    </br>
				<div class="agile-tables">
					<div class="w3l-table-info">
						<table id="utable" class="table table-hover" ></table>
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
		<jsp:include page="/public/userModel.jsp"/>
		<jsp:include page="/public/lower.jsp" />
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/freezeList.js"></script>

</body>
</html>
