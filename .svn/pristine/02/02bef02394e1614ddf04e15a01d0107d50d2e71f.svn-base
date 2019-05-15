<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<jsp:include page="/public/title.jsp"></jsp:include>
<!-- //font-awesome icons -->
<script >
     var path = '${pageContext.request.contextPath}';
 </script>

<!-- charts -->
<script src="${pageContext.request.contextPath}/js/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/js/morris.js"></script>
<script src="${pageContext.request.contextPath}/js/skycons.js"></script>
</head>
<body class="dashboard-page">
	<jsp:include page="/public/menu.jsp" />
	<section class="wrapper scrollable">
		<section class="title-bar">
			 <jsp:include page="/public/top.jsp" />
		</section>
		<div class="main-grid">
			<div class="social grid">
					<div class="grid-info">
						<div class="col-md-3 top-comment-grid">
						  <a href="${pageContext.request.contextPath}/menu/jumpRechargeList.htm">
							<div class="comments likes">
								<div class="comments-icon">
									<i class="fa fa-facebook"></i>
								</div>
								<div class="comments-info likes-info">
									<h3 id="totalRecharge">0￥</h3>
									<span>总充值</span>
								</div>
								<div class="clearfix"> </div>
							  </div>
						   </a>
						</div>
						<div class="col-md-3 top-comment-grid">
						  <a href="${pageContext.request.contextPath}/menu/jumpRechargeList.htm">
							<div class="comments">
								<div class="comments-icon">
									<i class="fa fa-comments"></i>
								</div>
								<div class="comments-info">
									<h3 id = "toDayRecharge">0K</h3>
									<span>今日充值</span>
								</div>
								<div class="clearfix"> </div>
							</div>
						  </a>
						</div>
						<div class="col-md-3 top-comment-grid">
						     <a href="${pageContext.request.contextPath}/menu/userList.htm">
								<div class="comments tweets">
									<div class="comments-icon">
										<i class="fa fa-twitter"></i>
									</div>
									<div class="comments-info tweets-info">
										<h3 id="totalUser">0</h3>
										<span>总用户</span>
									</div>
									<div class="clearfix"> </div>
								</div>
						     </a>
						</div>
						<div class="col-md-3 top-comment-grid">
							<a href="${pageContext.request.contextPath}/menu/userList.htm">
								<div class="comments views">
									<div class="comments-icon">
										<i class="fa fa-eye"></i>
									</div>
									<div class="comments-info views-info">
										<h3 id="toDayUser">0</h3>
										<span>今日新增</span>
									</div>
									<div class="clearfix"> </div>
								</div>
						     </a>
						</div>
						<div class="clearfix"> </div>
					</div>
			</div>
			
			<div class="agile-grids">
				<div class="col-md-4 charts-right">
					<!-- area-chart -->
					<div class="area-grids">
						<div class="area-grids-heading">
							<h3>性别比例</h3>
						</div>
						<div id="graph4"></div>
					</div>
					<!-- //area-chart -->
				</div>
				<div class="col-md-8 chart-left">
					<!-- updating-data -->
					<div class="agile-Updating-grids">
						<div class="area-grids-heading">
							<h3>日活跃与充值</h3>
						</div>
						<div id="graph1"></div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			
			<div class="agile-bottom-grids">
				<div class="col-md-6 agile-bottom-right">
					<div class="agile-bottom-grid">
						<div class="area-grids-heading">
							<h3>年度充值统计</h3>
						</div>
						<div id="graph6"></div>
					</div>
				</div>
				<div class="col-md-6 agile-bottom-left">
					<div class="agile-bottom-grid">
						<div class="area-grids-heading">
							<h3>年度会员新增</h3>
						</div>
						<div id="graph5"></div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<jsp:include page="/public/lower.jsp"/>
	</section>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/javascript/home.js"></script>
</body>
</html>





