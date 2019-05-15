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
				<!-- gallery -->
				<div class="grids-heading gallery-heading">
				    <input type="hidden" id="t_user_id" value="${userId}">
					<input type="hidden" id="page" value="1">
				</div>
				<div class="gallery-grids" id="myHandImg">
					
				</div>
			<!-- //gallery -->

			</div>
			<ul class="pagination" style="margin-left: 80%" id="pageHtml">
				<li><a href="#">&laquo;</a></li>
				<li class="active"><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">&raquo;</a></li>
			</ul>
		</div>
		<!-- 新增或修改模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 41%;">
				<div class="modal-content">
					<div class="modal-header" style="background-color: #87CEFA;">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
						  <span class="glyphicon glyphicon-picture"></span>&nbsp;图片预览
						</h4>
					</div>
					<div class="modal-body">
					    <input type="hidden" id="t_id">
						<img id="preview" src="${pageContext.request.contextPath }/images/g3.jpg" alt="" align="center"/>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>关闭
						</button>
						<button type="button" class="btn btn-primary" onclick="on_click_disable();">
							<span class="glyphicon glyphicon-floppy-disk"></span>禁用
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
	<!-- gallery -->
	<script src="${pageContext.request.contextPath }/javascript/photoScript.js"></script>
	<!-- //gallery -->
	
</body>
</html>

