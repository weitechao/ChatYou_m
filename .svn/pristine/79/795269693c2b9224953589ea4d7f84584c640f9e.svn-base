<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<head>
<title>上传照片</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="../css/fileinput.css" media="all"
	type="text/css" />
<link
	href='https://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font.css" type="text/css" />
<!-- //font-awesome icons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/ladda.min.css">
<script src="../js/jquery-2.0.3.min.js"></script>
<script src="../js/fileinput.js" type="text/javascript"></script>
<script src="../js/fileinput_locale_zh.js" type="text/javascript"></script>
<script src="../js/bootstrap.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/js/modernizr-1.6.min.js"></script>
<script type="text/javascript">
  var projectPath = '${pageContext.request.contextPath}';
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
				<!-- input-forms -->
				<div class="grids">
					<div class="panel panel-widget forms-panel w3-last-form">
						<div class="forms">
							<div class="form-three widget-shadow">
								<div class=" panel-body-inputin">
									<form class="form-horizontal" id="myFrom" action="#"
										method="post" enctype="multipart/form-data">
										<input type="hidden" id="userId" value="${userId}">
										<div class="form-group">
											<label class="col-md-2 control-label">用户头像:</label>
											<div class="col-md-8">
												<div class="input-group">
													<input id="file-0" class="file-loading" type="file"
														multiple data-min-file-count="1">
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">用户封面:</label>
											<div class="col-md-8">
												<div class="input-group">
													<input id="file-1" class="file-loading" type="file"
														multiple data-min-file-count="1">
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">用户相册:</label>
											<div class="col-md-8">
												<div class="input-group">
													<input id="file-2" class="file-loading" type="file"
														multiple data-min-file-count="1">
												</div>
											</div>
										</div>
										<div class="grid_3 grid_5 wow fadeInUp animated"
											data-wow-delay=".5s" style="margin-left: 70%;">
											<h4>
												<a href="${pageContext.request.contextPath}/menu/userList.htm"><span
													class="label label-success">保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存</span></a>
											</h4>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- //input-forms -->
			</div>
		</div>
		<!-- footer -->
		<jsp:include page="/public/lower.jsp" />
		<!-- //footer -->
	</section>
</body>
<script src="${pageContext.request.contextPath}/javascript/addPhoto.js"></script>
</html>

