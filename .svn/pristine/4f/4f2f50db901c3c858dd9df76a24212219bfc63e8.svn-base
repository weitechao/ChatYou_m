<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<head>
<title>Gallery</title>
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
			    <div style="float: right;width: 40%">
					<button class="btn btn-default" style="float: left;">文件类型:</button>
			     	<select id="t_file_type" onchange="on_auditing_onchange();"  style="width: 150px;height: 36px;float: left;margin-right: 50px;" class="form-control">
	                      <option value="-1">全部</option>
	                      <option value="0">照片</option>
	                      <option value="1">视频</option>
					</select>
					<button class="btn btn-default" style="float: left;">审核:</button>
			     	<select id="t_auditing" onchange="on_auditing_onchange();"  style="width: 150px;height: 36px;float: left;margin-right: 50px;" class="form-control">
	                      <option value="-1">全部</option>
	                      <option value="0">未审核</option>
	                      <option value="1">已审核</option>
					</select>
			    </div>
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
			<div class="modal-dialog" style="width: 20%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;" >
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-picture"></span>&nbsp;文件审核
					     <input type="hidden" id="t_photo_id">
						</h4>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-off"></span>&nbsp;关闭窗口
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_has_verified();">
							<span class="glyphicon glyphicon-ok"></span>&nbsp;审核通过
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_disable();">
							<span class="glyphicon glyphicon-remove"></span>&nbsp;审核失败
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- footer -->
		<jsp:include page="/public/lower.jsp"/>
		<!-- //footer -->
	</section>
	<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<!-- gallery -->
	<script src="${pageContext.request.contextPath }/javascript/privatePhotoScript.js"></script>
	<!-- //gallery -->
	
</body>
</html>

