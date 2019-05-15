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
				</div>
				<div class="gallery-grids">
					<div class="show-reel">
						<div class="col-md-3 agile-gallery-grid">
							<div class="agile-gallery">
								<video width="320" height="259" controls autoplay>
									<source src="https://img-1256929999.cos.ap-chengdu.myqcloud.com/201806281152474783.mp4" type="video/ogg">
								</video>
							</div>
						</div>
						<div class="col-md-3 agile-gallery-grid">
							<div class="agile-gallery">
								<a href="${pageContext.request.contextPath }/images/g2.jpg"
									class="lsb-preview" data-lsb-group="header"> <img
									src="${pageContext.request.contextPath }/images/g2.jpg" alt="" />
									<div class="agileits-caption">
										<h4>Consectetur</h4>
										<p>Sed ultricies non sem sit amet laoreet. Ut semper erat
											erat.</p>
									</div>
								</a>
							</div>
						</div>
						<div class="col-md-3 agile-gallery-grid">
							<div class="agile-gallery">
								<a
									href="https://img-1256929999.cos.ap-chengdu.myqcloud.com/201806281152474783.mp4"
									class="lsb-preview" data-lsb-group="header"> <img
									src="${pageContext.request.contextPath }/images/g3.jpg" alt="" />
									<div class="agileits-caption">
										<h4>Consectetur</h4>
										<p>111</p>
									</div>
								</a>
							</div>
						</div>
						<div class="col-md-3 agile-gallery-grid">
							<div class="agile-gallery">
								<a href="${pageContext.request.contextPath }/images/g4.jpg"
									class="lsb-preview" data-lsb-group="header"> <img
									src="${pageContext.request.contextPath }/images/g4.jpg" alt="" />
									<div class="agileits-caption">
										<h4>用户昵称</h4>
										<a href="#"
											style="float: right;margin-right: 10%;margin-top: 5.5%;font-size: 1em;color: #ffffff">点击禁用</a>
									</div>
								</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<script>
						$(window).load(function() {
							$.fn.lightspeedBox();
						});
					</script>
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
				<!-- //gallery -->

			</div>
		</div>

		<!-- footer -->
		<jsp:include page="/public/lower.jsp" />
		<!-- //footer -->
	</section>
	<script src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath }/javascript/photoScript.js"></script>
	<!-- //gallery -->

</body>
</html>

